package com.vista.textscanner

class PokemonSetExternData(private val PokemonTcgService: PokemonTcgService) : com.vista.textscanner.PokemonSetData {
    override suspend fun getSets(): MutableList<com.vista.textscanner.PokemonSet>? {
        val response = PokemonTcgService.getSets()
        if(response.isSuccessful) return response.body()?.sets
        throw Exception("Request data Error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<com.vista.textscanner.PokemonSet>?) {

    }

}