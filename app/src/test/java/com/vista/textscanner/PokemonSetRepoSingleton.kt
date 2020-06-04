package com.vista.textscanner

class PokemonSetRepoSingleton private constructor(){
    private var setLocalData: com.vista.textscanner.PokemonSetData? = null
    private var setExternData: com.vista.textscanner.PokemonSetData? = null

    fun init(setLocalData: com.vista.textscanner.PokemonSetLocalData, setExternData: com.vista.textscanner.PokemonSetExternData){

    }

}