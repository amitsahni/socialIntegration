package fbconnect;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.Map;

import fbconnect.callback.OnFBCallback;
import fbconnect.controller.LoginController;
import fbconnect.controller.ProfileController;

/**
 * Created by amit on 4/2/17.
 */

public class Builder {
    private Param param;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     * @param action  the action
     */
    public Builder(@NonNull Activity context, @NonNull Param.FBAction action) {
        param = new Param();
        param.activityContext = context;
        param.context = context;
        param.action = action;
    }

    public Builder action(@NonNull Param.FBAction action) {
        param.action = action;
        return this;
    }

    public <RESULT, ERROR> Builder callback(@NonNull OnFBCallback<RESULT, ERROR> callback) {
        param.fbCallback = callback;
        return this;
    }

    public Builder requestMap(@NonNull Map<String, String> map) {
        param.map = map;
        return this;
    }

    public void build() {
        switch (param.action) {
            case LOGIN:
                new LoginController().login(param);
                break;
            case LOGOUT:
                new LoginController().logout();
                break;
            case PROFILE:
                new ProfileController().profile(param);
                break;
            default:
                // No Action performs
                break;
        }
    }
}
