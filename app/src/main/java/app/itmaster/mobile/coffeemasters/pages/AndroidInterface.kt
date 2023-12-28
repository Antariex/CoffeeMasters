package app.itmaster.mobile.coffeemasters.pages

import android.webkit.JavascriptInterface
import android.webkit.WebView

class AndroidInterface(private val webView: WebView) {

    @JavascriptInterface
    fun submitFeedback() {
        // Manejar la lógica de envío de feedback aquí

        // Llamar a la función de JavaScript para mostrar el Snackbar
        webView.post {
            webView.evaluateJavascript("showFeedbackSnackbar();", null)
        }
    }
}
