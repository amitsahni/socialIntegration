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
import com.instaconnect.InstaConnect;
import com.twitterconnect.TwitterConfiguration;
import com.twitterconnect.TwitterConnect;

import java.util.LinkedHashMap;
import java.util.Map;

import fbconnect.FbConnect;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    private static final String TWITTER_KEY = "mpUVvDLh4EE0376IdQZfGI5vf";
    private static final String TWITTER_SECRET = "L8SEa7dfP1qdCSUTfrkXrI0CjY4uqNUin7tfVc3gn588CUQomm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FbConnect.getKeyHash(this);
        TwitterConfiguration.keys(TWITTER_KEY, TWITTER_SECRET)
                .isDebug(true)
                .config(this);
        InstaConnect.isDebug(getApplication());
        textView = (TextView) findViewById(android.R.id.text1);
        findViewById(R.id.fb).setOnClickListener(view -> {
            if (TextUtils.isEmpty(FbConnect.getToken())) {
                FbConnect.with()
                        .login(MainActivity.this)
                        .success(loginResult -> {
                            Log.i(getLocalClassName(), "LoginResult = " + loginResult.getAccessToken().getToken());
                            profile();
                            return Unit.INSTANCE;
                        })
                        .error(e -> Unit.INSTANCE)
                        .cancel(() -> Unit.INSTANCE)
                        .build();
            } else {
                profile();
                //FbConnect.with(this, Param.FBAction.LOGOUT).build();
            }
        });

        findViewById(R.id.tw).setOnClickListener(view -> {
            if (TwitterConnect.getSession() == null) {
                TwitterConnect.with()
                        .login(MainActivity.this)
                        .success(model -> {
                            Log.i(getLocalClassName(), "getUserName = " + model.getUserName());
                            Log.i(getLocalClassName(), "getUserId = " + model.getUserId());
                            return Unit.INSTANCE;
                        }).error(error -> Unit.INSTANCE).build();
            } else {
                TwitterConnect.with()
                        .profile()
                        .success(s -> {

                            Log.i(getLocalClassName(), "Email = " + s.email);
                            Log.i(getLocalClassName(), "Name = " + s.name);
                            Log.i(getLocalClassName(), "profileImageUrl = " + s.profileImageUrl);
                            StringBuilder builder = new StringBuilder();
                            builder.append("Name = " + s.name + "\n");
                            builder.append("Email = " + s.email + "\n");
                            builder.append("Profile url = " + s.profileImageUrl + "\n");
                            builder.append("Follower Count = " + s.followersCount + "\n");
                            Log.i(getLocalClassName(), "builder = " + s.toString());
                            return Unit.INSTANCE;
                        })
                        .error(error -> {
                            return Unit.INSTANCE;
                        }).build();
            }
        });

        findViewById(R.id.insta).setOnClickListener(view -> {
            if (!InstaConnect.isAlreadyLogin(MainActivity.this)) {
                InstaConnect.with()
                        .login(MainActivity.this, "2487808efe6d4cd0a3feb16e83fa1d25", "http://www.clickapps.co/")
                        .success(token -> {
                            Log.i(getLocalClassName(), "Token = " + token);
                            return Unit.INSTANCE;
                        }).error(error -> {
                    Log.i(getLocalClassName(), "Error = " + error);
                    return Unit.INSTANCE;
                }).build();
            } else {
                InstaConnect.with()
                        .profile(MainActivity.this)
                        .success(profile -> {
                            Log.i(getLocalClassName(), "Model = " + profile.toString());
                            return Unit.INSTANCE;
                        })
                        .build();
            }
        });

    }

    private void profile() {
        Map<String, String> map = new LinkedHashMap<>();
        // map.put(WebEndPoint.USER_ID_KEY, "1549869761707679");
        FbConnect.with()
                .profile(this)
                .success(profileResult -> {
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
                    return Unit.INSTANCE;
                })
                .error(error -> {
                    return Unit.INSTANCE;
                })
                .build();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TwitterConnect.onActivityResult(requestCode, resultCode, data);
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
