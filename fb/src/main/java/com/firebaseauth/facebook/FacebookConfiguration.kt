package com.firebaseauth.facebook

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

object FacebookConfiguration {
    private lateinit var clientId: String
    internal var account: GoogleSignInAccount? = null
        private set

    internal var gso: GoogleSignInOptions? = null
        private set

    val auth = FirebaseAuth.getInstance()

    fun clientId(clientId: String): FacebookConfiguration {
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
    }
}