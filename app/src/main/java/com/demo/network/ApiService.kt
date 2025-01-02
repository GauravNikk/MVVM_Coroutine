package com.demo.network

import com.google.gson.JsonObject
import com.demo.network.responseModels.home.ImageResponseModel
import com.demo.network.responseModels.home.ImageResponseModelItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    // https://picsum.photos/v2/list


//    @FormUrlEncoded
//    @POST("get_connectionID")
//    suspend fun getConnection(
//        @Field("api_key") apiKey: String
//    ): Response<ConnectionResponse>




    @GET("list")
    suspend fun getImageList(
       @Query("page") page: String? = null,
       @Query("limit") limit: String? = null
    ): Response< MutableList<ImageResponseModelItem>>

















    companion object {

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): ApiService {

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}