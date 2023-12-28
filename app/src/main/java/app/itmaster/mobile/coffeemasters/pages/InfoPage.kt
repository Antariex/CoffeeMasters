package app.itmaster.mobile.coffeemasters.pages

import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import app.itmaster.mobile.coffeemasters.pages.AndroidInterface


@Composable
fun InfoPage() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                webChromeClient = object : WebChromeClient() {
                    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                        println("Alerta de JS: ${message}")
                        return true
                    }
                }

                // Agrega la interfaz JavaScript para comunicarse con Android
                addJavascriptInterface(AndroidInterface(this), "AndroidInterface")

                loadUrl("file:///android_asset/index.html")
            }
        }
    )
}
