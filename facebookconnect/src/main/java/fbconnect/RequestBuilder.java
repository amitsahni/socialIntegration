package fbconnect;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import fbconnect.callback.FBException;
import fbconnect.callback.OnFBCallback;
import fbconnect.controller.WebEndPoint;
import fbconnect.model.ProfileResult;
import webconnect.com.webconnect.WebConnect;
import webconnect.com.webconnect.listener.OnWebCallback;

import static fbconnect.controller.WebEndPoint.ACCESS_TOKEN_KEY;
import static fbconnect.controller.WebEndPoint.FIELD;

/**
 * Created by clickapps on 20/2/18.
 */

public class RequestBuilder {

    @SuppressWarnings("unchecked")
    public static class LoginBuilder {
        private Param param;
        private Activity activity;
        private String[] permissions;

        public LoginBuilder(@NonNull Param param, @NonNull Activity context) {
            this.param = param;
            activity = context;
        }

        public LoginBuilder callback(@NonNull OnFBCallback<LoginResult, FBException> callback) {
            param.fbCallback = callback;
            return this;
        }

        public LoginBuilder permissions(@NonNull String... permissions) {
            this.permissions = permissions;
            return this;
        }

        public void build() {
            if (permissions == null) {
                LoginManager.getInstance()
                        .logInWithReadPermissions(activity, Arrays.asList("public_profile", "user_friends", "email"));
            } else {
                LoginManager.getInstance()
                        .logInWithReadPermissions(activity, Arrays.asList(permissions));
            }
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
    }


    public static class LogoutBuilder {

        public void build() {
            LoginManager.getInstance().logOut();
        }
    }

    @SuppressWarnings("unchecked")
    public static class ProfileBuilder {
        private Param param;
        private String otherUserId;

        public ProfileBuilder(@NonNull Param param) {
            this.param = param;
        }

        public ProfileBuilder callback(@NonNull OnFBCallback<ProfileResult, FBException> callback) {
            param.fbCallback = callback;
            return this;
        }

        public ProfileBuilder otherUserId(@NonNull String userId) {
            otherUserId = userId;
            return this;
        }

        public void build() {

            Map<String, String> map = new LinkedHashMap<>();
            map.put(FIELD, "about,gender,first_name,birthday,last_name,name,link,email,cover,updated_time,timezone,work,picture.type(large)");
            map.put(ACCESS_TOKEN_KEY, FbConnect.get().getToken(param.getContext()));
            if (TextUtils.isEmpty(otherUserId)) {
                otherUserId = "me";
            }
            WebConnect.with(param.getContext(), "/" + otherUserId)
                    .get()
                    .baseUrl(WebEndPoint.BASE)
                    .queryParam(map)
                    .callback(new OnWebCallback() {
                        @Override
                        public <T> void onSuccess(@Nullable T object, int taskId) {
                            if (object instanceof ProfileResult
                                    && param.getFbCallback() != null) {
                                param.getFbCallback().onSuccess(object);
                            }
                        }

                        @Override
                        public <T> void onError(@Nullable T object, String error, int taskId) {

                        }
                    }, ProfileResult.class, Object.class)
                    .connect();
        }
    }
}
