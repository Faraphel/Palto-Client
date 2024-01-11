package com.example.palto.data.network

import com.example.palto.data.network.model.UserCredentials
import com.example.palto.domain.Tokens
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object PaltoApi {

    // Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
    // full Kotlin compatibility.
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun createService(url: String) {
        // Use the Retrofit builder to build a retrofit object using a Moshi converter
        // with our Moshi object.
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(url)
            .build()
        retrofitService = retrofit.create(PaltoApiService::class.java)
    }

    // Retrofit service that Palto will use to do requests.
    // Make sure to call createService once before using it.
    lateinit var retrofitService: PaltoApiService
}


/**
 * Functions to query the API.
 */
interface PaltoApiService {

    @POST("api/auth/jwt/token/")
    suspend fun getTokens(@Body userCredentials: UserCredentials): Tokens
}
