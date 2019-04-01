package fbconnect

import android.app.Activity
import android.content.Context

/**
 * Created by amit on 4/2/17.
 */

class Builder() {


    fun login(context: Activity): RequestBuilder.LoginBuilder {
        return RequestBuilder.LoginBuilder(context)
    }

    fun logout(): RequestBuilder.LogoutBuilder {
        return RequestBuilder.LogoutBuilder()
    }

    fun profile(context: Context): RequestBuilder.ProfileBuilder {
        return RequestBuilder.ProfileBuilder(context)
    }
}
