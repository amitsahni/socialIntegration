package com.instaconnect

import android.content.Context
import android.util.Log
import com.instaconnect.model.Profile
import fbconnect.callback.OnError
import fbconnect.callback.OnSuccess
import webconnect.com.webconnect.WebConnect
import webconnect.com.webconnect.listener.OnWebCallback

class RequestBuilder {

    class LoginBuilder(private val context: Context) {
        private var successCallback: OnSuccess<String>? = null
        private var errorCallback: OnError<String>? = null

        fun success(success: String.() -> Unit): LoginBuilder {
            successCallback = object : OnSuccess<String> {
                override fun onSuccess(result: String) {
                    success(result)
                }
            }
            return this
        }

        fun error(e: String.() -> Unit): LoginBuilder {
            errorCallback = object : OnError<String> {
                override fun onError(error: String) {
                    e(error)
                }
            }
            return this
        }

        fun build() {
            AuthenticationDialog(context, InstaConfiguration.clientId, InstaConfiguration.redirectUrl, object : AuthenticationDialog.AuthenticationListener {
                override fun onTokenError(error: String) {
                    errorCallback?.onError(error)
                }

                override fun onTokenReceived(auth_token: String) {
                    successCallback?.onSuccess(auth_token)
                }
            }).show()
        }
    }


    class ProfileBuilder(val context: Context) {
        private var successCallback: OnSuccess<Profile>? = null
        private var errorCallback: OnError<String>? = null

        fun success(success: Profile.() -> Unit): ProfileBuilder {
            successCallback = object : OnSuccess<Profile> {
                override fun onSuccess(result: Profile) {
                    success(result)
                }
            }
            return this
        }

        fun error(e: String.() -> Unit): ProfileBuilder {
            errorCallback = object : OnError<String> {
                override fun onError(error: String) {
                    e(error)
                }
            }
            return this
        }

        fun build() {
            val url = "users/self/?access_token=${InstaConnect.accessToken(context)}"
            WebConnect.with(context, url)
                    .get()
                    .baseUrl("https://api.instagram.com/v1/")
                    .callback(object : OnWebCallback {
                        override fun <T> onSuccess(`object`: T?, taskId: Int) {
                            Log.i(this.javaClass.simpleName, "response =${`object`.toString()}")
                            if (`object` is Profile) {
                                successCallback?.onSuccess(`object`)
                            }
                        }

                        override fun <T> onError(`object`: T?, error: String, taskId: Int) {
                            Log.e(this.javaClass.simpleName, "error =$error")
                            errorCallback?.onError(`object`.toString())
                        }
                    }, Profile::class.java, Any::class.java)
                    .connect()

        }

    }
}