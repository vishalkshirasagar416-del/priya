package com.priya.app.avatar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout

class PriyaAvatarView(context: Context) : FrameLayout(context) {
    private val mainHandler = Handler(Looper.getMainLooper())
    private val webView = createWebView(context)
    private var released = false

    init {
        setBackgroundColor(Color.TRANSPARENT)
        addView(webView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        webView.loadUrl(ASSET_URL)
    }

    fun loadAvatar() = evaluate("window.PriyaAvatar && window.PriyaAvatar.loadAvatar()")

    fun setExpression(expressionName: String) {
        evaluate("window.PriyaAvatar && window.PriyaAvatar.setExpression(${expressionName.toJsString()})")
    }

    fun setSpeaking(isSpeaking: Boolean) {
        evaluate("window.PriyaAvatar && window.PriyaAvatar.setSpeaking($isSpeaking)")
    }

    fun setMouthOpen(value: Float) {
        evaluate("window.PriyaAvatar && window.PriyaAvatar.setMouthOpen(${value.coerceIn(0f, 1f)})")
    }

    fun setEmotion(emotion: String) {
        evaluate("window.PriyaAvatar && window.PriyaAvatar.setEmotion(${emotion.toJsString()})")
    }

    fun resetExpression() {
        evaluate("window.PriyaAvatar && window.PriyaAvatar.resetExpression()")
    }

    fun release() {
        if (released) return
        released = true
        mainHandler.removeCallbacksAndMessages(null)
        webView.evaluateJavascript("window.PriyaAvatar && window.PriyaAvatar.dispose()", null)
        webView.stopLoading()
        webView.loadUrl("about:blank")
        webView.removeJavascriptInterface(BRIDGE_NAME)
        webView.destroy()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        evaluate("window.PriyaAvatar && window.PriyaAvatar.resume()")
    }

    override fun onDetachedFromWindow() {
        evaluate("window.PriyaAvatar && window.PriyaAvatar.pause()")
        super.onDetachedFromWindow()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun createWebView(context: Context): WebView {
        return WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = false
            settings.allowFileAccessFromFileURLs = true
            settings.allowUniversalAccessFromFileURLs = false
            settings.domStorageEnabled = false
            settings.mediaPlaybackRequiresUserGesture = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    return request.url.toString() != ASSET_URL
                }
            }
            webChromeClient = WebChromeClient()
            addJavascriptInterface(AvatarBridge(), BRIDGE_NAME)
        }
    }

    private fun evaluate(script: String) {
        if (released) return
        mainHandler.post { webView.evaluateJavascript(script, null) }
    }

    private class AvatarBridge {
        @JavascriptInterface
        fun onRendererError(message: String) = Unit

        @JavascriptInterface
        fun onAvatarLoaded(expressions: String) = Unit
    }

    private fun String.toJsString(): String {
        return "\"${replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n")}\""
    }

    private companion object {
        const val ASSET_URL = "file:///android_asset/avatar/index.html"
        const val BRIDGE_NAME = "PriyaAvatarBridge"
    }
}