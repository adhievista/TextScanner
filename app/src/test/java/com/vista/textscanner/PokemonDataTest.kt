package com.vista.textscanner

import org.junit.*
import org.mockito.*

class PokemonDataTest {
    @Mock
    var localdata: PokemonSetData? = null

    @Mock
    var externdata: PokemonSetData? = null
    var pokemonsetRepo: PokemonSetRepo? = null
    var pokemonset = mutableListOf<PokemonSet>()

    @Before
    fun init(){
    MockitoAnnotations.initMocks(this)
        pokemonsetRepo = PokemonSetRepo
}
}