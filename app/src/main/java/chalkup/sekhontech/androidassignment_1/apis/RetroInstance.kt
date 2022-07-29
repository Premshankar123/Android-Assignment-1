package chalkup.sekhontech.androidassignment_1.apis

import retrofit2.Retrofit
import chalkup.sekhontech.androidassignment_1.apis.RetroInstance
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    var baseurl = "https://app.getswipe.in/" // volley_array.json
    private var retrofit: Retrofit? = null
    @JvmStatic
    val retroClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}