package com.example.challenge3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge3.*
import com.example.challenge3.interfaces.PokeClickListener
import com.example.challenge3.models.Pokemon

class PokeAdapter(var context: Context, var pokemonList: List<Pokemon>, var listener: PokeClickListener) : RecyclerView.Adapter<PokeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pokemonNameText.text = pokemonList[position].name

        Glide.with(context.applicationContext)
            .load(pokemonList[position].pokeDetail.sprites?.other?.home?.frontDefault)
            .placeholder(R.drawable.poke)
            .fitCenter()
            .into(holder.pokemonImage)
    }

    override fun getItemCount() = pokemonList.size

    inner class ViewHolder(_itemView: View) : RecyclerView.ViewHolder(_itemView) {
        val pokemonNameText: TextView = _itemView.findViewById(R.id.poke_name_text)
        val pokemonImage: ImageView = _itemView.findViewById(R.id.imageView)
        private var card: CardView = _itemView.findViewById(R.id.card)

        init {
            card.setOnClickListener {
                listener.onClickPoke(pokemonList[adapterPosition])
            }
        }
    }
}