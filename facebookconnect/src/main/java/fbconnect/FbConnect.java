package fbconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by amit on 4/2/17.
 */

public class FbConnect {

    private static volatile FbConnect sFbConnect;
    private static volatile CallbackManager sCallbackManager;

    private FbConnect() {

    }

    /**
     * Get web connect.
     *
     * @return the web connect
     */
    public static FbConnect get() {
        if (sFbConnect == null) {
            synchronized (FbConnect.class) {
                if (sFbConnect == null) {
                    sFbConnect = new FbConnect();
                    sCallbackManager = CallbackManager.Factory.create();
                }
            }
        }
        return sFbConnect;
    }

    public CallbackManager getCallBackManager() {
        return sCallbackManager;
    }

    /**
     * Initialize Facebook Sdk & Generate HashKey
     *
     * @param context
     */
    public void getKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i(getClass().getSimpleName(), "KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    /**
     * onActivityResult in your Activity, pass the result to the CallbackManager:
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (sCallbackManager != null)
            sCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public String getToken(@NonNull Context context) {
        if (AccessToken.getCurrentAccessToken() != null) {
            return AccessToken.getCurrentAccessToken().getToken();
        }
        return "";
    }

    public
    @Nullable
    AccessToken getAccessToken() {
        return AccessToken.getCurrentAccessToken();
    }

    /**
     * With builder.
     *
     * @param context the context
     * @param action  the action
     * @return the builder
     */
    public static Builder with(@NonNull Activity context, @NonNull Param.FBAction action) {
        return new Builder(context, action);

    }

}
