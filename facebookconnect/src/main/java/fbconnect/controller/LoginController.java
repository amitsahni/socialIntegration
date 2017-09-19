package fbconnect.controller;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import fbconnect.FbConnect;
import fbconnect.Param;
import fbconnect.callback.FBException;

/**
 * Created by amit on 4/2/17.
 */

public class LoginController {

    /**
     * @param param set {@linkplain Activity} Param
     */
    public void login(@NonNull final Param param) {
        LoginManager.getInstance().logInWithReadPermissions(param.getActivityContext(), Arrays.asList("public_profile", "user_friends", "email"));
        LoginManager.getInstance().registerCallback(FbConnect.get().getCallBackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken.setCurrentAccessToken(loginResult.getAccessToken());
                if (param.getFbCallback() != null)
                    param.getFbCallback().onSuccess(loginResult);
            }

            @Override
            public void onCancel() {
                if (param.getFbCallback() != null)
                    param.getFbCallback().onCancel();
            }

            @Override
            public void onError(FacebookException e) {
                    if (param.getFbCallback() != null) {
                        FBException exception = new FBException(e.getMessage());
                        param.getFbCallback().onError(exception);
                    }
            }
        });
    }

    /**
     * @param param set {@linkplain Activity} Param
     */
    public void login(@NonNull final Param param, String... permissions) {
        AccessToken token = AccessToken.getCurrentAccessToken();
        boolean isPermission = true;
        if (token != null) {
            for (String per : permissions) {
                if (!token.getPermissions().contains(per)) {
                    isPermission = false;
                }
            }
        }
        if (!isPermission) {
            LoginManager.getInstance().logInWithReadPermissions(param.getActivityContext(), Arrays.asList(permissions));
        }
    }

    public void logout() {
        LoginManager.getInstance().logOut();
    }
}
