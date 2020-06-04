package com.vista.textscanner

class PokemonSetRepo (
        private val setLocal: PokemonSetData,
        private val setExtern: PokemonSetData
){
    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = setLocal.getSets()
        if (cache != null) return cache

        val response = setExtern.getSets()
        setLocal.addAll(response)
        return response
    }
}
