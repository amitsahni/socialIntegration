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

    internal var gso: GoogleSignInOptions? = null
        private set

    val auth = FirebaseAuth.getInstance()

    @JvmStatic
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
}