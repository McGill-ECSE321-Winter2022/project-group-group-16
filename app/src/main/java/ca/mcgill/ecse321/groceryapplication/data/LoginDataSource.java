package ca.mcgill.ecse321.groceryapplication.data;

import ca.mcgill.ecse321.groceryapplication.HttpUtils;
import ca.mcgill.ecse321.groceryapplication.data.model.LoggedInUser;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {


    public Result<LoggedInUser> login(String username, String password) {
//        OnJSONResponseCallback callback = new OnJSONResponseCallback(){
//            @Override
//            public Result<LoggedInUser> onJSONResponse(boolean success, JSONObject response){
//               return new Result.Error(new IOException("Error logging in"));
//            }
//        };
//
//        JsonHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                callback.onJSONResponse(true, response);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                callback.onJSONResponse(false, errorResponse);
//            }
//        };
//
//        HttpUtils.get("getGroceryUserbyEmail/" + username, new RequestParams(), responseHandler);



//        try {
//            HttpUtils.post("persons/" + tv.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                    refreshErrorMessage();
//                    tv.setText("");
//                }
//                @Override
//                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                    try {
//                        error += errorResponse.get("message").toString();
//                    } catch (JSONException e) {
//                        error += e.getMessage();
//                    }
//                    refreshErrorMessage();
//                }
//            });


        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}