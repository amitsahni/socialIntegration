package fbconnect

import android.app.Application
import fbconnect.controllerT.WebEndPoint
import webconnect.com.webconnect.ApiConfiguration

object FacebookConfiguration {

    private var isDebug: Boolean = true

    @JvmStatic
    fun isDebug(debug: Boolean): FacebookConfiguration {
        isDebug = debug
        return this
    }

    fun config(context: Application) {
        ApiConfiguration.Builder(context)
                .baseUrl(WebEndPoint.BASE)
                .debug(isDebug)
                .config()
    }
}