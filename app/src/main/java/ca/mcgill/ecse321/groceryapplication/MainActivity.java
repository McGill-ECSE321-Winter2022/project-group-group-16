package ca.mcgill.ecse321.groceryapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ca.mcgill.ecse321.groceryapplication.databinding.ActivityMainBinding;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {
    private String error = "";
    private JSONObject response = null;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


//    private void refreshErrorMessage() {
//        if (Objects.equals(this.error, "No value for message") || this.error.isEmpty()) return;
//        Toast.makeText(this, this.error,
//                Toast.LENGTH_LONG).show();
//    }


    public void addCustomer(View v) {
        this.error = "";
        final TextView firstName = findViewById(R.id.firstname);
        final TextView lastName = findViewById(R.id.lastName);
        final TextView username = findViewById(R.id.username);
        final TextView email = findViewById(R.id.email);
        final TextView dateOfBirth = findViewById(R.id.dateOfBirth);
        final TextView password = findViewById(R.id.password);
        final TextView streetNumber = findViewById(R.id.streetNumber);
        final TextView streetName = findViewById(R.id.streetName);
        final TextView city = findViewById(R.id.city);
        final TextView country = findViewById(R.id.country);
        final TextView postalCode = findViewById(R.id.postalCode);
        final TextView province = findViewById(R.id.province);
        String parsedDate = null;

        // These three post requests should have an atomic behavior in real world

        @SuppressLint("SimpleDateFormat") SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parsedDate = myFormat.format(Objects.requireNonNull(fromUser.parse(dateOfBirth.getText().toString())));
        } catch (ParseException e) {
            this.error += e.getMessage();
//            refreshErrorMessage();
        }

        String createUserRequest = "gorceryUser/?username=" + username.getText().toString()
                + "&password=" + password.getText().toString()
                + "&firstName=" + firstName.getText().toString()
                + "&lastName=" + lastName.getText().toString()
                + "&email=" + email.getText().toString()
                + "&date=" + parsedDate;
        String createAddressRequest = "address/?streetNumber=" + streetNumber.getText().toString()
                + "&streetName=" + streetName.getText().toString()
                + "&province=" + province.getText().toString()
                + "&city=" + city.getText().toString()
                + "&country=" + country.getText().toString()
                + "&postalCode=" + postalCode.getText().toString();

        CountDownLatch latch = new CountDownLatch(1);
        postWithErrorLog(createUserRequest, latch);
        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }

        latch = new CountDownLatch(1);
        postWithErrorLog(createAddressRequest, latch);
        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }

        String addressId = "";
        try {
             addressId = this.response.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // we need the user and address to first be created
        // have to wait for the 2 previous async calls to finish.
        String createCustomerRequest = "/customer/?applicationId=0"
                + "&addressId="+ addressId
                + "&userEmail=" + email.getText().toString();
        postWithErrorLog(createCustomerRequest, latch);

        this.error = "";
    }

    private void setJsonResponse(JSONObject response) {
        this.response = response;
    }

    private void postWithErrorLog(String request, CountDownLatch latch) {
        new Thread() {
            public void run() {
                SyncHttpClient client = new SyncHttpClient();
                client.post(HttpUtils.getAbsoluteUrl(request), new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        setJsonResponse(response);
                        latch.countDown();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            error += errorResponse.get("message").toString();
                        } catch (JSONException e) {
                            System.out.println(e.getMessage());
                            error += e.getMessage();
                        }
//                        refreshErrorMessage();
                        latch.countDown();
                    }
                });
            }
        }.start();

    }
}