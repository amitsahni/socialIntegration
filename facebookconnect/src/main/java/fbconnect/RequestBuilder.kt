package fbconnect

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import fbconnect.callback.FBException
import fbconnect.callback.OnCancel
import fbconnect.callback.OnError
import fbconnect.callback.OnSuccess
import fbconnect.controllerT.WebEndPoint
import fbconnect.controllerT.WebEndPoint.ACCESS_TOKEN_KEY
import fbconnect.controllerT.WebEndPoint.FIELD
import fbconnect.model.ProfileResult
import webconnect.com.webconnect.WebConnect
import webconnect.com.webconnect.listener.OnWebCallback
import java.util.*

/**
 * Created by clickapps on 20/2/18.
 */

class RequestBuilder {

    class LoginBuilder(private val activity: Activity) {
        private var permissions: List<String> = listOf("public_profile", "user_friends", "email")
        private var successCallback: OnSuccess<LoginResult>? = null
        private var errorCallback: OnError<FBException>? = null
        private var cancelCallback: OnCancel? = null

        fun success(success: OnSuccess<LoginResult>): LoginBuilder {
            successCallback = object : OnSuccess<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    success.onSuccess(result)
                }
            }
            return this
        }

        fun error(error: OnError<FBException>): LoginBuilder {
            errorCallback = object : OnError<FBException> {
                override fun onError(e: FBException) {
                    error.onError(e)
                }
            }
            return this
        }

        fun cancel(cancel: OnCancel): LoginBuilder {
            cancelCallback = object : OnCancel {
                override fun onCancel() {
                    cancel.onCancel()
                }
            }
            return this
        }

        fun permissions(permissions: List<String>): LoginBuilder {
            this.permissions = permissions
            return this
        }

        fun build() {
            LoginManager.getInstance()
                    .logInWithReadPermissions(activity, permissions)
            LoginManager.getInstance().registerCallback(FbConnect.callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    AccessToken.setCurrentAccessToken(loginResult.accessToken)
                    successCallback?.onSuccess(loginResult)
                }

                override fun onCancel() {
                    cancelCallback?.onCancel()
                }

                override fun onError(e: FacebookException) {
                    val exception = FBException(e.message.toString())
                    errorCallback?.onError(exception)
                }
            })
        }
    }


    class LogoutBuilder {

        fun build() {
            LoginManager.getInstance().logOut()
        }
    }

    class ProfileBuilder(val context: Context) {
        private var otherUserId: String? = null
        private var successCallback: OnSuccess<ProfileResult>? = null
        private var errorCallback: OnError<FBException>? = null

        fun success(success: OnSuccess<ProfileResult>): ProfileBuilder {
            successCallback = object : OnSuccess<ProfileResult> {
                override fun onSuccess(result: ProfileResult) {
                    success.onSuccess(result)
                }
            }
            return this
        }

        fun error(error: OnError<FBException>): ProfileBuilder {
            errorCallback = object : OnError<FBException> {
                override fun onError(e: FBException) {
                    error.onError(e)
                }
            }
            return this
        }

        fun otherUserId(userId: String): ProfileBuilder {
            otherUserId = userId
            return this
        }

        fun build() {
            val map = LinkedHashMap<String, String>()
            map[FIELD] = "about,gender,first_name,birthday,last_name,name,link,email,cover,updated_time,timezone,work,picture.type(large)"
            map[ACCESS_TOKEN_KEY] = FbConnect.getToken(context)
            if (TextUtils.isEmpty(otherUserId)) {
                otherUserId = "me"
            }
            WebConnect.with(context, "/" + otherUserId!!)
                    .get()
                    .baseUrl(WebEndPoint.BASE)
                    .queryParam(map)
                    .callback(object : OnWebCallback {
                        override fun <T> onSuccess(`object`: T?, taskId: Int) {
                            if (`object` is ProfileResult) {
                                successCallback?.onSuccess(`object`)
                            }
                        }

                        override fun <T> onError(`object`: T?, error: String, taskId: Int) {
                            val fbException = FBException(error)
                            errorCallback?.onError(fbException)
                        }
                    }, ProfileResult::class.java, Any::class.java)
                    .connect()
        }
    }
}
