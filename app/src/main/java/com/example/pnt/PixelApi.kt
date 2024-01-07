package com.example.pnt

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixelApi {
    @GET("api/")
    fun getImage(
        @Query("q")keyWord:String,
       @Query( "key")key:String="25007027-7418deb977c638792f4bfb99f",
        @Query("page")page:Int=1,
        @Query("per_page")perPage:Int=3
    ): Call<PixelModel>
}