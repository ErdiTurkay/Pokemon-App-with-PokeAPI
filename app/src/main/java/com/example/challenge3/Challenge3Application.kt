package com.example.challenge3

import android.app.Application
import com.example.challenge3.models.PokemonDetail
import com.example.challenge3.models.PokemonURL
import dagger.hilt.android.HiltAndroidApp
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

@HiltAndroidApp
class Challenge3Application : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}

interface PokeApiService{
    @GET("pokemon")
    suspend fun getPokemonURL(@Query("offset") offset: String, @Query("limit") limit: Int): PokemonURL

    @GET
    suspend fun getPokemonDetail(@Url url: String): PokemonDetail
}