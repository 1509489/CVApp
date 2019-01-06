package com.pixelart.cvapp.network

import com.pixelart.cvapp.model.APIResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("86ca2208b7e186d3436cab8412124cb2/raw/482240b342fa8565e45d0f6b4c42dc2297d126e9/samplecv.json")
    fun getCvs(): Single<APIResponse>
}