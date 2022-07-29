package chalkup.sekhontech.androidassignment_1.apis

import retrofit2.http.GET
import chalkup.sekhontech.androidassignment_1.models.ProductModel
import retrofit2.Call

interface APIServices {
    @get:GET("/api/public/get")
    val movieList: Call<List<ProductModel?>?>?
}