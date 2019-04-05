package com.firebaseauth.google

import android.app.Activity
import com.firebaseauth.google.callback.OnCancel
import com.firebaseauth.google.callback.OnError
import com.firebaseauth.google.callback.OnSuccess
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class RequestBuilder {

    class ProfileBuilder {
        private var successCallback: OnSuccess<FirebaseUser>? = null
        private var errorCallback: OnError<Exception>? = null
        private var cancelCallback: OnCancel? = null

        fun success(success: FirebaseUser.() -> Unit): ProfileBuilder {
            successCallback = object : OnSuccess<FirebaseUser> {
                override fun onSuccess(result: FirebaseUser) {
                    success(result)
                }
            }
            return this
        }

        fun error(e: Exception.() -> Unit): ProfileBuilder {
            errorCallback = object : OnError<Exception> {
                override fun onError(error: Exception) {
                    e(error)
                }
            }
            return this
        }

        fun cancel(cancel: () -> Unit): ProfileBuilder {
            cancelCallback = object : OnCancel {
                override fun onCancel() {
                    cancel()
                }
            }
            return this
        }


        fun build() {
            val credential = GoogleAuthProvider.getCredential(GoogleConnect.account?.idToken, null)
            GoogleConfiguration.auth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            GoogleConfiguration.auth.currentUser?.let {
                                successCallback?.onSuccess(it)
                            }
                        } else {
                            it.exception?.let {
                                errorCallback?.onError(it)
                            }
                        }
                    }.addOnCanceledListener {
                        cancelCallback?.onCancel()
                    }.addOnFailureListener {
                        errorCallback?.onError(it)
                    }
        }
    }

    class LoginBuilder(private val activity: Activity, private val requestCode: Int) {

        fun build() {
            val client = GoogleSignIn.getClient(activity, GoogleConfiguration.gso!!)
            val signInIntent = client.signInIntent
            activity.startActivityForResult(signInIntent, requestCode)
        }


    }
}