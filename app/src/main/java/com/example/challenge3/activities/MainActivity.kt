package com.example.challenge3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge3.R
import com.example.challenge3.feature.pokedetail.PokeDetailFragment
import com.example.challenge3.feature.pokelist.PokeListFragment
import com.example.challenge3.models.Pokemon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToPokeListFragment()
    }

    private fun goToPokeListFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, PokeListFragment())
            .commit()
    }

    fun goToPokeDetailFragment(poke: Pokemon){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, PokeDetailFragment(poke))
            .addToBackStack("detail")
            .commit()
    }
}