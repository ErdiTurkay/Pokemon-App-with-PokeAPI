package com.example.challenge3.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Pokemon(
    @PrimaryKey @SerializedName("name") val name: String,
    @ColumnInfo @SerializedName("url") val url: String,
    @Embedded var pokeDetail: PokemonDetail
)

@Entity
data class PokemonDetail(
    @ColumnInfo
    @SerializedName("id") val id: String?,

    @Embedded
    @SerializedName("sprites") val sprites: Sprites?,

    @ColumnInfo
    @SerializedName("base_experience") val baseExperience: Int,

    @ColumnInfo
    @SerializedName("height") val height: Int,

    @ColumnInfo
    @SerializedName("weight") val weight: Int
)

@Entity
data class Sprites (
    @Embedded
    @SerializedName("other")
    @Expose
    var other: Other? = null
)

@Entity
data class Other (
    @Embedded
    @SerializedName("home")
    @Expose
    var home: Home? = null
)

@Entity
data class Home (
    @SerializedName("front_default")
    @Expose
    var frontDefault: String? = null
)