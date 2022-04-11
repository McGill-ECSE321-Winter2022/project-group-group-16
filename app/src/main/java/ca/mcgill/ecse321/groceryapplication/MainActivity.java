package ca.mcgill.ecse321.groceryapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import ca.mcgill.ecse321.groceryapplication.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String error = null;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.fragment_create_employee);
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

//    private String error = null;

    private JSONObject currentEmployee = null;
    private JSONObject newEmployee = null;

    /**
     * Signs up employee with firstName, lastName, email, username, hourly pay and password read from layout, logs
     * @param v View
     */
    public void createEmployee(View v) {
        final EditText emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String email = emailInput.getText().toString();
        final EditText hourlyPayInput = (EditText) findViewById(R.id.editTextNumberDecimal);
        String hourlyPay = hourlyPayInput.getText().toString();
        final EditText DateHiredInput = (EditText) findViewById(R.id.editTextDate) ;
        String hiredDate = DateHiredInput.getText().toString();
        final EditText AppIdInput = (EditText) findViewById(R.id.editTextNumber) ;
        String appId = AppIdInput.getText().toString();
        final EditText EmployeeStatusInput = (EditText) findViewById(R.id.textInputEditText) ;
        String employeeStatus = EmployeeStatusInput.getText().toString();

        RequestParams rp = new RequestParams();

        rp.add("email", email);
        rp.add("groceryStoreApplicationId", appId);
        rp.add("hiredDate", hiredDate);
        rp.add("hourlyPay", hourlyPay);
        rp.add("employeeStatus", employeeStatus);

//        Log.i("beignet", "all good");
//        String createEmployeePath = email + "/" + appId + "/" + hiredDate + "/" + hourlyPay + "/" + employeeStatus;
//        Log.i("macaron", "path is all good");
//        createEmployeePath = createEmployeePath.replaceAll(" ", "%20");
//        Log.i("cake", "random replace spaces worked woohoo");
//        Log.i("basque", createEmployeePath);
        //+ createEmployeePath, new RequestParams(),
        Log.i("scones", rp.toString());
        HttpUtils.post("/employee/", rp, new JsonHttpResponseHandler() {
            @Override//creation success: login
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newEmployee = response;
                currentEmployee = response;
                try {
                    error = "";
                    Log.i("bread", "smh you done fucked up");
                    setContentView(R.layout.fragment_employeehomepage);
                    ((TextView) findViewById(R.id.displayId)).setText(newEmployee.getString("AppId"));
                    ((TextView) findViewById(R.id.displayDateHired)).setText(newEmployee.getString("dateHired"));
                    ((TextView) findViewById(R.id.displayEmail)).setText(newEmployee.getString("email"));
                    ((TextView) findViewById(R.id.displayHourlyPay)).setText(newEmployee.getString("hourLyPay") + "$");
                    ((TextView) findViewById(R.id.displayStatus)).setText(newEmployee.getString("employeeStatus"));
                    Log.i("test", "it works! congrats");
                } catch(Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

            @Override //creation failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input. Please try again.";
                    Log.i("test", "still didnt work but its also a failure sad");
                } catch(Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }





}