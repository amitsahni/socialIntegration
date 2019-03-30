package test.facebookutil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginResult;

import java.util.LinkedHashMap;
import java.util.Map;

import fbconnect.FbConnect;
import fbconnect.callback.FBException;
import fbconnect.callback.OnCancel;
import fbconnect.callback.OnError;
import fbconnect.callback.OnSuccess;
import fbconnect.model.ProfileResult;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FbConnect.getKeyHash(this);
        textView = (TextView) findViewById(android.R.id.text1);
        if (TextUtils.isEmpty(FbConnect.getToken(this))) {
            FbConnect.with()
                    .login(this)
                    .success(new OnSuccess<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            Log.i(getLocalClassName(), "LoginResult = " + loginResult.getAccessToken().getToken());
                            profile();
                        }
                    })
                    .error(new OnError<FBException>() {
                        @Override
                        public void onError(FBException e) {

                        }
                    })
                    .cancel(new OnCancel() {
                        @Override
                        public void onCancel() {

                        }
                    })
                    .build();
        } else {
            profile();
            //FbConnect.with(this, Param.FBAction.LOGOUT).build();
        }
    }

    private void profile() {
        Map<String, String> map = new LinkedHashMap<>();
        // map.put(WebEndPoint.USER_ID_KEY, "1549869761707679");
        FbConnect.with()
                .profile(this)
                .success(new OnSuccess<ProfileResult>() {
                    @Override
                    public void onSuccess(ProfileResult profileResult) {
                        Log.i(getLocalClassName(), "Email = " + profileResult.getEmail());
                        StringBuilder builder = new StringBuilder();
                        builder.append("Name = " + profileResult.getName() + "\n" + "\n");
                        builder.append("FirstName LastName = " + profileResult.getFirstName() + " " + profileResult.getLastName() + "\n" + "\n");
                        builder.append("Email = " + profileResult.getEmail() + "\n" + "\n");
                        if (profileResult.getPicture() != null && profileResult.getPicture().getData() != null) {
                            builder.append("Image = " + profileResult.getPicture().getData().getProfileImage() + "\n" + "\n");
                        }
                        builder.append("CustomImage = " + "http://graph.facebook.com/" + profileResult.getId() + "/picture?type=square" + "\n" + "\n");
                        builder.append("CustomImage = " + "http://graph.facebook.com/" + profileResult.getId() + "/picture?type=large");
                        textView.setText(builder.toString());
                    }
                })
                .error(new OnError<FBException>() {
                    @Override
                    public void onError(FBException e) {

                    }
                })
                .build();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (FacebookSdk.isFacebookRequestCode(requestCode)) {
                //Facebook activity result
                //Do your stuff here
                //Further you can also check if it's login or Share etc by using
                //CallbackManagerImpl as explained by rajath's answer here
                if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
                    //login
                    Log.i(getLocalClassName(), "Login");
                    FbConnect.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }
}
