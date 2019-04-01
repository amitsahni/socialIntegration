package com.instaconnect

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.annotation.NonNull
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.auth_dialog.*


class AuthenticationDialog(@NonNull context: Context, clientId: String, redirectUrl: String, listener: AuthenticationListener) : Dialog(context) {
    private val requestUrl: String
    private val baseUrl: String = "https://api.instagram.com/"

    internal var webViewClient: WebViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(redirectUrl)) {
                this@AuthenticationDialog.dismiss()
                return true
            }
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (url.contains("access_token=")) {
                val uri = Uri.parse(url)
                val accessToken = uri.encodedFragment
                accessToken?.let {
                    val token = it.substring(it.lastIndexOf("=") + 1)
                    Log.e("access_token", token)
                    listener.onTokenReceived(token)
                    val pref = P.defaultPrefs(context)
                    pref["access_token"] = token
                }
                dismiss()
            } else if (url.contains("?error")) {
                Log.e("access_token", "getting error fetching access token")
                listener.onTokenError(url)
                dismiss()
            }
        }
    }

    init {
        this.requestUrl = baseUrl +
                "oauth/authorize/?client_id=" + clientId +
                "&redirect_uri=" + redirectUrl +
                "&response_type=token&display=touch&scope=public_content"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.auth_dialog)
        initializeWebView()
    }

    override fun show() {
        super.show()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.clearCache(true)
        webView.clearHistory()
        CookieManager.getInstance().removeAllCookie()
        webView.loadUrl(requestUrl)
        webView.webViewClient = webViewClient
    }

    interface AuthenticationListener {
        fun onTokenReceived(auth_token: String)
        fun onTokenError(error: String)
    }
}