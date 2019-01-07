package com.pixelart.cvapp.network

import com.pixelart.cvapp.model.APIResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("86ca2208b7e186d3436cab8412124cb2/raw/e2ddb435c6195b22e0d7b0cb65dd61aa840019f6/samplecv.json")
    fun getCvs(): Single<APIResponse>
}