package com.twitterconnect

import android.app.Activity
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.User
import com.twitterconnect.controller.TwCallback
import com.twitterconnect.controller.TwClient
import fbconnect.callback.OnError
import fbconnect.callback.OnSuccess
import okhttp3.OkHttpClient

/**
 * Created by clickapps on 20/2/18.
 */

class RequestBuilder {

    class LoginBuilder(val activity: Activity) {
        private var successCallback: OnSuccess<TwitterSession>? = null
        private var errorCallback: OnError<TwitterException>? = null

        fun success(success: TwitterSession.() -> Unit): LoginBuilder {
            successCallback = object : OnSuccess<TwitterSession> {
                override fun onSuccess(result: TwitterSession) {
                    success(result)
                }
            }
            return this
        }

        fun error(e: TwitterException.() -> Unit): LoginBuilder {
            errorCallback = object : OnError<TwitterException> {
                override fun onError(error: TwitterException) {
                    e(error)
                }
            }
            return this
        }

        fun build() {
            TwitterConnect.twitterAuthClient.authorize(activity, object : Callback<TwitterSession>() {

                override fun success(twitterSessionResult: Result<TwitterSession>) {
                    // Success
                    val activeSession = TwitterConnect.session
                    val customClient = OkHttpClient.Builder()
                            .addInterceptor(TwitterConfiguration.loggingInterceptor).build()
                    val customApiClient = TwClient(activeSession!!, customClient)
                    TwitterCore.getInstance().addApiClient(activeSession, customApiClient)
                    successCallback?.onSuccess(twitterSessionResult.data)
                }

                override fun failure(e: TwitterException) {
                    errorCallback?.onError(e)
                }
            })
        }

    }

    class EmailBuilder {
        private var successCallback: OnSuccess<String>? = null
        private var errorCallback: OnError<TwitterException>? = null

        fun success(success: String.() -> Unit): EmailBuilder {
            successCallback = object : OnSuccess<String> {
                override fun onSuccess(result: String) {
                    success(result)
                }
            }
            return this
        }

        fun error(e: TwitterException.() -> Unit): EmailBuilder {
            errorCallback = object : OnError<TwitterException> {
                override fun onError(error: TwitterException) {
                    e(error)
                }
            }
            return this
        }

        fun build() {
            TwitterConnect.twitterAuthClient.requestEmail(TwitterConnect.session, object : Callback<String>() {
                override fun success(result: Result<String>) {
                    // Do something with the result, which provides the email address
                    successCallback?.onSuccess(result.data.toString())
                }

                override fun failure(exception: TwitterException) {
                    // Do something on failure
                    errorCallback?.onError(exception)
                }
            })
        }

    }


    class ProfileBuilder {

        private var successCallback: OnSuccess<User>? = null
        private var errorCallback: OnError<TwitterException>? = null

        fun success(success: User.() -> Unit): ProfileBuilder {
            successCallback = object : OnSuccess<User> {
                override fun onSuccess(result: User) {
                    success(result)
                }
            }
            return this
        }

        fun error(e: TwitterException.() -> Unit): ProfileBuilder {
            errorCallback = object : OnError<TwitterException> {
                override fun onError(error: TwitterException) {
                    e(error)
                }
            }
            return this
        }

        fun build() {
            val activeSession = TwitterConnect.session


            /*val customClient = OkHttpClient.Builder()
                    .addInterceptor(TwitterConfiguration.loggingInterceptor).build()
            val customApiClient: TwitterApiClient
            if (activeSession != null) {
                customApiClient = TwClient(activeSession, customClient)
                TwitterCore.getInstance().addApiClient(activeSession, customApiClient)
                val v = TwitterCore.getInstance().getApiClient(activeSession).accountService
                        .verifyCredentials(false, false, true)
//                val twitterApiClient = TwitterCore.getInstance().apiClient as TwClient
                val statusesService = customApiClient.twitterService
                val map = LinkedHashMap<String, String>()
                map[WebEndPoint.USER_ID_KEY] = "" + activeSession.userId
                map["include_email"] = "true"
                v.enqueue(object : TwCallback<User>() {
                    override fun success(result: Result<User>) {
                        successCallback?.onSuccess(result.data)
                    }

                    override fun failure(exception: TwitterException) {
                        errorCallback?.onError(exception)
                    }
                })
            }*/

            val call = TwitterCore.getInstance().getApiClient(activeSession).accountService
                    .verifyCredentials(false, false, true)
            call.enqueue(object : TwCallback<User>() {
                override fun success(result: Result<User>) {
                    successCallback?.onSuccess(result.data)
                }

                override fun failure(exception: TwitterException) {
                    errorCallback?.onError(exception)
                }
            })
        }
    }
}
