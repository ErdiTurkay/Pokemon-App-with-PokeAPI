package com.example.challenge3.databases

import androidx.room.*
import com.example.challenge3.models.Pokemon

class PokeDatabase {
    @Dao
    interface PokeDao{
        @Query("SELECT * FROM pokemon")
        fun getAll(): List<Pokemon>

        @Insert
        fun insert(poke: Pokemon)

        @Insert
        fun insertList(poke: List<Pokemon>)

        @Delete
        fun delete(poke: Pokemon)

        @Update
        fun update(poke: Pokemon)
    }

    @Database(entities = [Pokemon::class], version = 1)
    abstract class AppDatabase : RoomDatabase(){
        abstract fun pokeDao(): PokeDao
    }
}