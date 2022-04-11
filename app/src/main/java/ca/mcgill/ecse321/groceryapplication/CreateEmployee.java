package ca.mcgill.ecse321.groceryapplication;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CreateEmployee extends AppCompatActivity {

    private String error = null;

    private JSONObject currentEmployee = null;
    private JSONObject newEmployee = null;

    /**
     * Signs up employee with firstName, lastName, email, username, hourly pay and password read from layout, logs
     * @param v View
     */
    public void signup(View v) {
        final EditText firstNameInput = (EditText) findViewById(R.id.efirstname);
        String firstName = firstNameInput.getText().toString();
        final EditText lastNameInput = (EditText) findViewById(R.id.elastname);
        String lastName = lastNameInput.getText().toString();
        final EditText emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String email = emailInput.getText().toString();
        final EditText usernameInput = (EditText) findViewById(R.id.textInputEditText);
        String username = usernameInput.getText().toString();
        final EditText passwordInput = (EditText) findViewById(R.id.editTextTextPassword);
        String password = passwordInput.getText().toString();
        final EditText hourlyPayInput = (EditText) findViewById(R.id.editTextNumberDecimal);
        String hourlyPay = hourlyPayInput.getText().toString();
        final EditText birthDateInput = (EditText) findViewById(R.id.editTextDate) ;
        String birthDate = birthDateInput.getText().toString();

        // auto-generated values on customer creation
        String isVerified = "false";
        String isLocal = "false";

        String createEmployeePath = firstName + "/" + lastName + "/" + password + "/" + email + "/" + isVerified + "/" + isLocal + "/" + username + "/" + "/" + birthDate + "/" +hourlyPay;
        createEmployeePath = createEmployeePath.replaceAll(" ", "%20");

        HttpUtils.post("/employee/" + createEmployeePath, new RequestParams(), new JsonHttpResponseHandler() {

            @Override//signup success: login
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newEmployee = response;
                currentEmployee = response;
                try {
                    error = "";
                    setContentView(R.layout.fragment_employeehomepage);
                    ((TextView) findViewById(R.id.displayEmployeeUsername)).setText(newEmployee.getString("employeeUsername"));
                    ((TextView) findViewById(R.id.displayId)).setText(newEmployee.getString("employeeId"));
                    ((TextView) findViewById(R.id.displayFirstName)).setText(newEmployee.getString("firstName"));
                    ((TextView) findViewById(R.id.displayLastName)).setText(newEmployee.getString("lastName"));
                    ((TextView) findViewById(R.id.displayDateHired)).setText(newEmployee.getString("dateHired"));
                    ((TextView) findViewById(R.id.displayEmail)).setText(newEmployee.getString("email"));
                    ((TextView) findViewById(R.id.displayHourlyPay)).setText(newEmployee.getString("hourLyPay") + "$");
                } catch(Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

            @Override //signup failed, try again
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error = "Invalid input. Please try again.";
                } catch(Exception e) {
                    error = e.getMessage();
                }
                refreshErrorMessage();
            }

        });

    }

    /**
     * navigates to the employee home page
     * @param v view
     */
    public void goToProfile(View v) {
        try {
            setContentView(R.layout.fragment_employeehomepage);
            ((TextView) findViewById(R.id.displayEmployeeUsername)).setText(currentEmployee.getString("employeeUsername"));
            ((TextView) findViewById(R.id.displayId)).setText(currentEmployee.getString("employeeId"));
            ((TextView) findViewById(R.id.displayFirstName)).setText(currentEmployee.getString("firstName"));
            ((TextView) findViewById(R.id.displayLastName)).setText(currentEmployee.getString("lastName"));
            ((TextView) findViewById(R.id.displayDateHired)).setText(currentEmployee.getString("dateHired"));
            ((TextView) findViewById(R.id.displayEmail)).setText(currentEmployee.getString("email"));
            ((TextView) findViewById(R.id.displayHourlyPay)).setText(currentEmployee.getString("hourLyPay") + "$");
        } catch (Exception e) {
            error = e.getMessage();
        }
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
     * navigates to the view shift
     * @param v view
     *
     * */
    public void goToViewShift(View v) {
        try{
            setContentView(R.layout.fragment_viewshift);
        } catch(Exception e) {
            e.getMessage();
        }
    }

}
