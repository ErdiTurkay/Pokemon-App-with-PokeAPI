package com.example.challenge3

import android.content.Context
import androidx.room.Room
import com.example.challenge3.databases.PokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDBService(): PokeApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokeApiService::class.java)
    }

    @Provides
    fun provideRoom(@ApplicationContext context: Context): PokeDatabase.AppDatabase{
        return Room.databaseBuilder(
            context,
            PokeDatabase.AppDatabase::class.java, "poke_database"
        ).build()
    }
}