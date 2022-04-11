package ca.mcgill.ecse321.groceryapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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

import android.view.Gravity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;



import cz.msebera.android.httpclient.Header;

import ca.mcgill.ecse321.groceryapplication.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String error = null;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private JSONObject currentEmployee = null;
    private JSONObject newEmployee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.fragment_manager_home);

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

    public void backEmployeeList(View v){
        setContentView(R.layout.fragment_login);
    }


    public void initItemTable() {
        //create table
        TableLayout tbl = (TableLayout) findViewById(R.id.list_view);
        tbl.removeAllViews();
        TableRow head = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Employee Email");
        tv0.setTextColor(Color.WHITE);
        tv0.setTextSize(20);
        tv0.setBackgroundColor(Color.BLACK);
        head.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Hourly Pay");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.BLACK);
        tv1.setTextSize(20);
        head.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Status ");
        tv2.setTextColor(Color.WHITE);
        tv2.setBackgroundColor(Color.BLACK);
        tv2.setTextSize(20);
        head.addView(tv2);
        tbl.addView(head);

        //get all items from backend
        HttpUtils.get("/employee/", new RequestParams(), new JsonHttpResponseHandler() {

            @Override//success : add items to the table
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for(int i = 0; i < response.length(); i++) {
                    JSONObject thisItem;
                    int color;
                    if ( i%2 == 0) {
                        color = Color.LTGRAY;
                    } else {
                        color = Color.WHITE;
                    }

                    try {
                        thisItem = response.getJSONObject(i);

                        addItemToTable(thisItem, color);

                    } catch(Exception e) {
                        error = e.getMessage();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {

                    error = errorResponse.get("message").toString();
                } catch(Exception e) {
                    error = e.getMessage();
                }
            }

        });
    }

    public void showTable(View v){
        initItemTable();
    }

    /**
     * helper method to add an item to the Items table
     * @param item  JSONObject
     * @param color int
     */
    private void addItemToTable(JSONObject item, int color) {
        Log.i("test","start");

        String hourlyPay;
        String email;
        String status;
        TableLayout tbl = (TableLayout) findViewById(R.id.list_view);
        //get item info
        Log.i("test","here");
        JSONObject convertUsername;
        try {
            convertUsername = item.getJSONObject("user");
            hourlyPay = item.getString("hourlyPay");
            Log.i("test","here1");
            email= convertUsername.getString("email");
            Log.i("test","here2");
            status = item.getString("status");
            Log.i("test","here3");
        } catch(Exception e) {
            error = e.getMessage();
            return;
        }
        //add row
        TableRow row = new TableRow(this);
        TextView t1v = new TextView(this); //column 1 : item id
        t1v.setText(String.valueOf(email));
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        t1v.setBackgroundColor(color);
        row.addView(t1v);
        TextView t2v = new TextView(this); //column 2: title
        t2v.setText(hourlyPay);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        t2v.setBackgroundColor(color);
        row.addView(t2v);
        TextView t3v = new TextView(this); //column 3 : item status
        t3v.setText(status);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        t3v.setBackgroundColor(color);
        row.addView(t3v);

        tbl.addView(row);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    private String error = null;



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
            @Override//creation success: createemployee
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newEmployee = response;
                //currentEmployee = response;
                try {
                    error = "";
                    Log.i("bread", "smh you done fucked up");
                    //setContentView(R.layout.fragment_employeehomepage);
//
//                    setContentView(R.layout.fragment_test);
//                    //test: delete until catch
//                    ((TextView) findViewById(R.id.displayId)).setText(newEmployee.getString("groceryStoreApplicationId"));
//                    ((TextView) findViewById(R.id.displayLastName)).setText(newEmployee.getString("dateHired"));
//                    ((TextView) findViewById(R.id.displayBalance)).setText(newEmployee.getString("employeeStatus"));
//                    ((TextView) findViewById(R.id.displayFirstName)).setText(newEmployee.getString("hourlyPay") + "$");
//
//                    ((TextView) findViewById(R.id.displayEmail)).setText(newEmployee.getString("email"));


                    setContentView(R.layout.fragment_employeehomepage);

//                    ((TextView) findViewById(R.id.displayId)).setText(newEmployee.getString("groceryStoreApplicationId"));
//                    Log.i("Michelle", newEmployee.toString());
                    ((TextView) findViewById(R.id.displayDateHired)).setText(newEmployee.getString("hiredDate"));
                    Log.i("Abiola", "smh you done fucked up");
                    JSONObject object = newEmployee.getJSONObject("user");
                    ((TextView) findViewById(R.id.displayEmail)).setText(object.getString("email"));
                    Log.i("homo", "smh you done fucked up"); //fucked up
                    ((TextView) findViewById(R.id.displayHourlyPay)).setText(newEmployee.getString("hourlyPay") + "0$");
                    Log.i("sixual", "smh you done fucked up");
                   ((TextView) findViewById(R.id.displayStatus)).setText(newEmployee.getString("status"));
                   Log.i("dickman", "it works! congrats"); //fucked uip
                } catch(Exception e) {
                    error = e.getMessage();
                }
                //refreshErrorMessage();
            }

            @Override //creation failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input. Please try again.";
                    Log.i("cookiejar", "still didnt work but its also a failure sad");
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

    /**
     * Signs the manager or employee out, navigates to the login page
     * @param v View
     */
    public void signout(View v) {
        try {
            setContentView(R.layout.fragment_login);
            currentEmployee = null;
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    /**
     * Signs the manager or employee out, navigates to the login page
     * @param v View
     */
    public void goToCreateEmployee(View v) {
        try {
            setContentView(R.layout.fragment_create_employee);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    /**
     * navigates to the manager home page
     * @param v View
     */
    public void managerHomePage(View v) {
        try {
            setContentView(R.layout.fragment_manager_home);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    /**
     * navigates to the page with all shifts
     * @param v View
     */
    public void goToViewShift(View v) {
        try {
            setContentView(R.layout.fragment_viewshift);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }



    /**
     * navigates to the page with all employees
     * @param v View
     */
    public void goToEmployeeList(View v) {
        try {
            setContentView(R.layout.fragement_employee_list);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    /**
     * navigates to the page with all employees
     * @param v View
     */
    public void goToEmployeeProfile(View v) {
        try {
            setContentView(R.layout.fragment_employeehomepage);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }




}