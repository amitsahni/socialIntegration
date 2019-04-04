package com.firebaseauth.google

import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

object GoogleConfiguration {
    private lateinit var clientId: String
    internal var account: GoogleSignInAccount? = null
        private set

    internal var gso: GoogleSignInOptions? = null
        private set

    val auth = FirebaseAuth.getInstance()

    fun clientId(clientId: String): GoogleConfiguration {
        this.clientId = clientId
        return this
    }

    fun build() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientId)
                .requestEmail()
                .build()
    }

    fun onActivityResult(data: Intent, success: Boolean.() -> Unit) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            account = task.getResult(ApiException::class.java)
            success(true)
        } catch (e: ApiException) {
            Log.w("Login", "Google sign in failed", e)
            account = null
            success(false)
        }
    }
}