package com.example.challenge3.feature.pokelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge3.PokeApiService
import com.example.challenge3.databases.PokeDatabase
import com.example.challenge3.models.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val api: PokeApiService,
    private val db: PokeDatabase.AppDatabase
) : ViewModel() {

    var offset = 0
    private var limit = 20

    var pokemonCountText = MutableLiveData<String>()
    var network = MutableLiveData<Boolean>()
    val pokeLiveData = MutableLiveData<List<Pokemon>>()
    private var pokeDataRoom: ArrayList<Pokemon> = arrayListOf()

    fun getPokemonFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                network.postValue(true)
                pokeDataRoom = ArrayList(db.pokeDao().getAll())

                if (pokeDataRoom.size <= offset) {
                    val pokeList = api.getPokemonURL(offset.toString(), limit).results

                    pokeList?.let {
                        for (item in it) {
                            item?.pokeDetail = api.getPokemonDetail(item!!.url)
                            pokeDataRoom.add(item)
                        }

                        db.pokeDao().insertList(it as List<Pokemon>)
                    }
                }

                pokemonCountText.postValue("You are viewing between ${offset + 1} and ${offset + limit}.")
                pokeLiveData.postValue(pokeDataRoom.subList(offset, offset + limit))
            } catch (e: Exception) {
                network.postValue(false)
                offset = pokeDataRoom.size
            }
        }
    }

    fun getNextPageFromAPI() {
        offset += limit

        getPokemonFromAPI()
    }

    fun getPreviousPageFromAPI() {
        if (offset > 0) {
            offset -= limit
            getPokemonFromAPI()
        }
    }
}
