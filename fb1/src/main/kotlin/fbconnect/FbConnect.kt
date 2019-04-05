package fbconnect

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object FbConnect {
    @JvmStatic
    val callbackManager = CallbackManager.Factory.create()

    @JvmStatic
    fun getKeyHash(context: Context) {
        try {
            val info = context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.i(javaClass.getSimpleName(), "KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    @JvmStatic
    fun with(): Builder {
        return Builder()

    }

    @JvmStatic
    val accessToken = AccessToken.getCurrentAccessToken()

    @JvmStatic
    val token: String
        get() {
            AccessToken.getCurrentAccessToken()?.let {
                return it.token
            }
            return ""
        }

    @JvmStatic
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

}