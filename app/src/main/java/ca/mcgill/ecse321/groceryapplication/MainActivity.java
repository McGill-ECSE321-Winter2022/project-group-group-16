package ca.mcgill.ecse321.groceryapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import cz.msebera.android.httpclient.Header;

import ca.mcgill.ecse321.groceryapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String error = null;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private JSONObject currentShift = null;
    private JSONObject newShift = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.fragment_manager_shift);

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

    public void initShift() {
        Log.i("test","yo");

        TextView tv = (TextView) findViewById(R.id.day);
        Log.i("danny",tv.toString());
        String day = tv.getText().toString();

        TextView tv2 = (TextView) findViewById(R.id.shiftType);
        String shiftType = tv2.getText().toString();

        TextView tv3 = (TextView) findViewById(R.id.employeeId);
        String employeeId = tv3.getText().toString();

        RequestParams rp = new RequestParams();

        rp.add("shiftType",shiftType);
        rp.add("employeeId",employeeId);
        rp.add("day",day);

        HttpUtils.post("/shift/", rp, new JsonHttpResponseHandler() {

            @Override//signup success: login
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newShift = response;
                currentShift = response;
                try {
                    error = "";
                    setContentView(R.layout.fragment_manager_shift);
                    ((TextView) findViewById(R.id.day)).setText(newShift.getString("day"));
                    ((TextView) findViewById(R.id.shiftType)).setText(newShift.getString("shiftType"));
                    ((TextView) findViewById(R.id.employeeId)).setText(newShift.getString("employeeId"));
                } catch(Exception e) {
                    error = e.getMessage();
                }
                //refreshErrorMessage();
            }

            @Override //signup failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i("test","fail:(");
                try {
                    error = "Invalid input. Please try again.";
                } catch(Exception e) {
                    error = e.getMessage();
                }
                //refreshErrorMessage();
            }

        });

    }

    public void createShift(View v) {
        initShift();
    }

    public enum ShiftType {
        OPENING, CLOSING
    }

    public enum Day {
        MONDAY, THUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }


}