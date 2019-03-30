package fbconnect;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by amit on 4/2/17.
 */

public class Builder {
    private Param param;

    public Builder(Context context) {
        param.context = context;
    }

    public RequestBuilder.LoginBuilder login(@NonNull Activity context) {
        param = new Param();
        param.action = Param.FBAction.LOGIN;
        return new RequestBuilder.LoginBuilder(param, context);
    }

    public RequestBuilder.LogoutBuilder logout() {
        return new RequestBuilder.LogoutBuilder();
    }

    public RequestBuilder.ProfileBuilder profile() {
        param = new Param();
        param.action = Param.FBAction.PROFILE;
        return new RequestBuilder.ProfileBuilder(param);
    }
}
