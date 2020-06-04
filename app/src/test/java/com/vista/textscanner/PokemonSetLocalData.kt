package com.vista.textscanner

class PokemonSetLocalData : com.vista.textscanner.PokemonSetData {
    private var caches = mutableListOf<com.vista.textscanner.PokemonSet>()

    override suspend fun getSets(): MutableList<com.vista.textscanner.PokemonSet>? =
            if (caches.isNotEmpty()) caches else null

    override suspend fun addAll(sets: MutableList<com.vista.textscanner.PokemonSet>?){
        sets?.let { caches = it }
    }
}