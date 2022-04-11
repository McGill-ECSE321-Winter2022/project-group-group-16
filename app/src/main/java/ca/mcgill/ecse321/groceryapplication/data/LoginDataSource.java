package ca.mcgill.ecse321.groceryapplication.data;

import ca.mcgill.ecse321.groceryapplication.HttpUtils;
import ca.mcgill.ecse321.groceryapplication.data.model.LoggedInUser;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    CountDownLatch latch;
    Result<LoggedInUser> result;

    public void setLoginInfo(Result<LoggedInUser> result) {
        this.result = result;
    }

    private void loginRest(String username, String password) {
        Thread request = new Thread() {
            public void run() {
                SyncHttpClient client = new SyncHttpClient();
                client.get(HttpUtils.getAbsoluteUrl("getGroceryUserbyEmail/" + username), new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println(response.toString());

                        try {
                            if (response.get("password").equals(password)) {
                                if (Objects.equals(username, "manager@email.com")) {
                                    LoggedInUser user = new LoggedInUser(username, "manager");
                                    setLoginInfo(new Result.Success<>(user));

                                } else {
                                    client.get(HttpUtils.getAbsoluteUrl("employee/email/" + username), new RequestParams(), new JsonHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                            LoggedInUser user = new LoggedInUser(username, "employee");
                                            setLoginInfo(new Result.Success<>(user));
                                        }

                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                            setLoginInfo(new Result.Error(new IOException("Error logging in")));
                                        }
                                    });
                                }

                            }
                        } catch (JSONException e) {
                            setLoginInfo(new Result.Error(new IOException("Error logging in", e)));
                        }
                        latch.countDown();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        setLoginInfo(new Result.Error(new IOException("Error logging in")));
                        latch.countDown();
                    }
                });
            }
        };
        request.start();

    }

    public Result<LoggedInUser> login(String username, String password) {
        this.latch = new CountDownLatch(1);
        loginRest(username, password);

        try {
            latch.await();
        } catch (Exception ignored) {
        }
        return this.result;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}