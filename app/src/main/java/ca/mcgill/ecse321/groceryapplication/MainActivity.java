package ca.mcgill.ecse321.groceryapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import ca.mcgill.ecse321.groceryapplication.data.LoginRepository;
import ca.mcgill.ecse321.groceryapplication.data.model.LoggedInUser;
import ca.mcgill.ecse321.groceryapplication.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String error = "";
    private JSONObject response = null;
    private AppBarConfiguration appBarConfiguration;
    private JSONObject newEmployee = null;
    private JSONObject currentShift = null;
    private JSONObject employee = null;
    private JSONObject newShift = null;
    private JSONObject currentEmployee = null;

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


    // initialize table
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

        //get all employees from backend
        HttpUtils.get("/employee/", new RequestParams(), new JsonHttpResponseHandler() {

            @Override//success : add items to the table
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject thisItem;
                    int color;
                    if (i % 2 == 0) {
                        color = Color.LTGRAY;
                    } else {
                        color = Color.WHITE;
                    }

                    try {
                        thisItem = response.getJSONObject(i);

                        addItemToTable(thisItem, color);

                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {

                    error = errorResponse.get("message").toString();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }

        });
    }

    /**
     * outputs the table after view employee button is pressed
     *
     * @param v
     */
    public void showTable(View v) {
        initItemTable();
    }

    /**
     * helper method to add an employees to the Items table
     *
     * @param item  JSONObject
     * @param color int
     */
    private void addItemToTable(JSONObject item, int color) {
        String hourlyPay;
        String email;
        String status;
        TableLayout tbl = (TableLayout) findViewById(R.id.list_view);
        //get item info
        JSONObject convertUsername;
        try {
            convertUsername = item.getJSONObject("user");
            hourlyPay = item.getString("hourlyPay");
            email = convertUsername.getString("email");
            status = item.getString("status");
        } catch (Exception e) {
            error = e.getMessage();
            return;
        }
        //add row
        TableRow row = new TableRow(this);
        TextView t1v = new TextView(this); //column 1 : item id
        t1v.setText(email);
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


    public void showProfileInfo(View v) {
        LoggedInUser user = LoginRepository.getInstance(null).getUser();
        HttpUtils.get("/employee/email/" + user.getEmail(), new RequestParams(), new JsonHttpResponseHandler() {

            @Override//success : add items to the table
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                currentEmployee = response;
                try {

                    ((TextView) findViewById(R.id.displayHourlyPay)).setText(currentEmployee.getString("hourlyPay"));
                    ((TextView) findViewById(R.id.displayDateHired)).setText(currentEmployee.getString("hiredDate"));
                    ((TextView) findViewById(R.id.displayStatus)).setText(currentEmployee.getString("status"));
                    ((TextView) findViewById(R.id.displayEmail)).setText(user.getEmail());


                } catch (Exception e) {
                    error = e.getMessage();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {

                    error = errorResponse.get("message").toString();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }

        });

    }


    /**
     * Signs up employee with email, hired date, employee status, grocery application id, hourly pay
     *
     * @param v View
     */
    public void createEmployee(View v) {
        final EditText emailInput = findViewById(R.id.editTextTextEmailAddress);
        String email = emailInput.getText().toString();
        final EditText hourlyPayInput = findViewById(R.id.editTextNumberDecimal);
        String hourlyPay = hourlyPayInput.getText().toString();
        final EditText DateHiredInput = findViewById(R.id.editTextDate);
        String hiredDate = DateHiredInput.getText().toString();
        final EditText EmployeeStatusInput = findViewById(R.id.textInputEditText);
        String employeeStatus = EmployeeStatusInput.getText().toString();
        final TextView firstName = findViewById(R.id.firstNameId);
        final TextView lastName = findViewById(R.id.LastNameId);
        final TextView username = findViewById(R.id.usernameId);
        final TextView dateOfBirth = findViewById(R.id.editTextDate2);
        final TextView password = findViewById(R.id.passwordId);
        final TextView streetNumber = findViewById(R.id.streetNumberId);
        final TextView streetName = findViewById(R.id.streetNameId);
        final TextView city = findViewById(R.id.cityId);
        final TextView country = findViewById(R.id.countryId);
        final TextView postalCode = findViewById(R.id.postalCodeId);
        final TextView province = findViewById(R.id.provinceId);
        String parsedDate = null;
        String parseHired = null;

        // These three post requests should have an atomic behavior in real world

        @SuppressLint("SimpleDateFormat") SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parseHired = myFormat.format(Objects.requireNonNull(fromUser.parse(hiredDate)));
            parsedDate = myFormat.format(Objects.requireNonNull(fromUser.parse(dateOfBirth.getText().toString())));
        } catch (ParseException e) {
            this.error += e.getMessage();
//            refreshErrorMessage();
        }

        String createUserRequest = "/gorceryUser/?username=" + username.getText().toString()
                + "&password=" + password.getText().toString()
                + "&firstName=" + firstName.getText().toString()
                + "&lastName=" + lastName.getText().toString()
                + "&email=" + email
                + "&date=" + parsedDate;
        String createAddressRequest = "/address/?streetNumber=" + streetNumber.getText().toString()
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

        Log.i("userThing", "User was created woohoo");

        latch = new CountDownLatch(1);
        postWithErrorLog(createAddressRequest, latch);
        Log.i("addressCheck", "address has been created");
        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }

        Log.i("addressThing", "address was created woohoo");

        String addressId = "";
        try {
            addressId = this.response.get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestParams rp = new RequestParams();
//
        rp.add("email", email);
        rp.add("groceryStoreApplicationId", "0");
        rp.add("hiredDate", parseHired);
        rp.add("hourlyPay", hourlyPay);
        rp.add("employeeStatus", employeeStatus);

        HttpUtils.post("/employee/", rp, new JsonHttpResponseHandler() {
            @Override//creation success: create employee
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newEmployee = response;
                try {
                    error = "";
                    setContentView(R.layout.fragement_employee_list);
                    ((TextView) findViewById(R.id.displayDateHired)).setText(newEmployee.getString("hiredDate"));
                    JSONObject object = newEmployee.getJSONObject("user");
                    ((TextView) findViewById(R.id.displayEmail)).setText(object.getString("email"));
                    ((TextView) findViewById(R.id.displayHourlyPay)).setText(newEmployee.getString("hourlyPay") + "0$");
                    ((TextView) findViewById(R.id.displayStatus)).setText(newEmployee.getString("status"));
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }

            @Override //creation failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input. Please try again.";
                } catch (Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }

    public void initShift() {
        TextView tv = (TextView) findViewById(R.id.day);
        String day = tv.getText().toString();

        TextView tv2 = (TextView) findViewById(R.id.shiftType);
        String shiftType = tv2.getText().toString();

        TextView tv3 = (TextView) findViewById(R.id.email);
        String email = tv3.getText().toString();

        RequestParams rp = new RequestParams();


        HttpUtils.get("/employee/email/" + email, new RequestParams(), new JsonHttpResponseHandler() {

            @Override // login successful : display account info
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                employee = response;
                try {
                    //test: delete until catch
                    Log.i("test", response.toString());
                    String employeeTemp = employee.getJSONObject("user").getJSONArray("userRole").getString(0);
                    JSONObject obj = new JSONObject(employeeTemp);
                    String employeeId = obj.getString("id");

                    rp.add("shiftType", shiftType);
                    rp.add("employeeId", employeeId);
                    rp.add("day", day);

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

                            } catch (Exception e) {
                                error = e.getMessage();
                            }
                            //refreshErrorMessage();
                        }

                        @Override //signup failed, try again
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Log.i("test", "fail:(");
                            try {
                                error = "Invalid input. Please try again.";
                            } catch (Exception e) {
                                error = e.getMessage();
                            }
                            //refreshErrorMessage();
                        }

                    });


                } catch (Exception e) {
                    error = e.getMessage();
                }

            }

            @Override //login failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input or account does not exist.\nPlease try again.";
                } catch (Exception e) {
                    error = e.getMessage();
                }

            }

        });


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
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }

            @Override //signup failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input. Please try again.";
                } catch (Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    /**
     * Method to view all shifts
     *
     * @param v
     */
    public void showAllShifts(View v) {
        //create table
        TableLayout tbl = (TableLayout) findViewById(R.id.list_view_shifts);
        tbl.removeAllViews();
        TableRow head = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Day       ");
        tv0.setTextColor(Color.WHITE);
        tv0.setTextSize(20);
        tv0.setBackgroundColor(Color.BLACK);
        head.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("    Type    ");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.BLACK);
        tv1.setTextSize(20);
        head.addView(tv1);
        tbl.addView(head);

        //get all employees from backend
        HttpUtils.get("/shift/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject thisItem;
                    int color;
                    if (i % 2 == 0) {
                        color = Color.LTGRAY;
                    } else {
                        color = Color.WHITE;
                    }

                    try {
                        thisItem = response.getJSONObject(i);
                        addShiftToTable(thisItem, color);

                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {

                    error = errorResponse.get("message").toString();
                } catch (Exception e) {
                    error = e.getMessage();
                }
            }

        });
    }

    /**
     * Helper method to add shifts to the shift table
     *
     * @param item  the shift
     * @param color the color
     */
    private void addShiftToTable(JSONObject item, int color) {
        String day;
        String type;
        TableLayout tbl = (TableLayout) findViewById(R.id.list_view_shifts);
        //get item info
        JSONObject convertUsername;
        try {
            day = item.getString("day");
            type = item.getString("shift");
        } catch (Exception e) {
            error = e.getMessage();
            return;
        }
        //add row
        TableRow row = new TableRow(this);
        TextView t1v = new TextView(this); //column 1 : day of the week
        t1v.setText(day);
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        t1v.setBackgroundColor(color);
        row.addView(t1v);
        TextView t2v = new TextView(this); //column 2: type of shift
        t2v.setText(type);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        t2v.setBackgroundColor(color);
        row.addView(t2v);

        //table layout
        tbl.addView(row);
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
     * Signs out the manager or employee out, navigates to the login page
     *
     * @param v View
     */
    public void signout(View v) {
        try {


            setContentView(R.layout.fragment_login);
            JSONObject currentEmployee = null;
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    /**
     * Navigates to the create employee page
     *
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
     *
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
     * navigates to the page with an employee's shifts
     *
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
     * navigates to the page with an employee's shifts
     *
     * @param v View
     */
    public void goToAddShift(View v) {
        try {
            setContentView(R.layout.fragment_manager_shift);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }


    /**
     * navigates to the employee's home page
     *
     * @param v View
     */
    public void goToEmployeeHomePage(View v) {
        try {
            setContentView(R.layout.fragment_employeehomepage);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    /**
     * navigates to the page with all employees
     *
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
     *
     * @param v View
     */
    public void goToCreateCustomer(View v) {
        try {
            setContentView(R.layout.fragment_signup_customer);
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    public void createShift(View v) {
        initShift();
    }

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
                + "&addressId=" + addressId
                + "&userEmail=" + email.getText().toString();
        postWithErrorLog(createCustomerRequest, latch);

        this.error = "";
    }

    private void setJsonResponse(JSONObject response) {
        this.response = response;
    }


//    private void refreshErrorMessage() {
//        if (Objects.equals(this.error, "No value for message") || this.error.isEmpty()) return;
//        Toast.makeText(this, this.error,
//                Toast.LENGTH_LONG).show();
//    }

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

    public enum ShiftType {
        OPENING, CLOSING
    }

    public enum Day {
        MONDAY, THUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}