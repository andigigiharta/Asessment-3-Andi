package com.example.asessment3andi.network

import com.example.asessment3andi.model.Barang
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/andigigiharta/json-mobpro/refs/heads/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HewanApiService {
    @GET("barang.json")
    suspend fun getBarang(): List<Barang>
}

object BarangApi {
    val service: HewanApiService by lazy {
        retrofit.create(HewanApiService::class.java)
    }

    fun getBarangUrl(gambar: String): String {
        return "$BASE_URL$gambar.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }

