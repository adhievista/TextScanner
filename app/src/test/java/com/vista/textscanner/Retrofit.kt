package com.vista.textscanner

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitGet {
    private const val BASE_URL = "https://api.pokemontcg.io/v1/"

    private val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val POKTCGSER: PokemonTcgService = client.create(PokemonTcgService::class.java)
}

interface PokemonTcgService{
    @GET("cards")
    suspend fun getCards(@Query("set") set: String): Response<com.vista.textscanner.PokemonCard.PokemonCardResponse>

    @GET("sets")
    suspend fun getSets(): Response<com.vista.textscanner.PokemonSet.PokemonSetResponse>
}