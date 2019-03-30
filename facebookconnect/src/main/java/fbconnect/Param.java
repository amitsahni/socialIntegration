package fbconnect;

import android.content.Context;

import com.google.common.base.Optional;

import java.util.LinkedHashMap;
import java.util.Map;

import fbconnect.callback.OnFBCallback;

/**
 * Created by amit on 4/2/17.
 */

public class Param {

    protected Context context;

    protected OnFBCallback fbCallback;

    protected FBAction action;

    protected Map<String, String> map = new LinkedHashMap<>();

    public enum FBAction {
        /**
         * LoginResult
         **/
        LOGIN,

        /**
         * No Model
         **/
        LOGOUT,

        /**
         * ProfileResult
         **/
        PROFILE
    }

    public Context getContext() {
        return context;
    }

    public OnFBCallback getFbCallback() {
        return fbCallback;
    }

    public Map<String, String> getMap() {
        return Optional.fromNullable(map).or(new LinkedHashMap<String, String>());
    }
}
