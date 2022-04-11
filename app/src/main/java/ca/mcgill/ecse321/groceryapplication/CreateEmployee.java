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

//            @Override//signup success: login
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                newCustomer = response;
//                currentCustomer = response;
//                try {
//                    error = "";
//                    setContentView(R.layout.customer_home_page);
//                    ((TextView) findViewById(R.id.displayId)).setText(newCustomer.getString("customerId"));
//                    ((TextView) findViewById(R.id.displayFirstName)).setText(newCustomer.getString("firstName"));
//                    ((TextView) findViewById(R.id.displayLastName)).setText(newCustomer.getString("lastName"));
//                    ((TextView) findViewById(R.id.displayAddress)).setText(newCustomer.getString("address"));
//                    ((TextView) findViewById(R.id.displayEmail)).setText(newCustomer.getString("email"));
//                    ((TextView) findViewById(R.id.displayBalance)).setText(newCustomer.getString("accountBalance") + "$");
//                } catch(Exception e) {
//                    error = e.getMessage();
//                }
//                refreshErrorMessage();
//            }

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
