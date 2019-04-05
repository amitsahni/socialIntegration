package com.firebaseauth.google

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

object GoogleConfiguration {
    private lateinit var clientId: String

    internal var gso: GoogleSignInOptions? = null
        private set

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