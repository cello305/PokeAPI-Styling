package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PetAdapter (private val petList: List<MainActivity.Pokemon>) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView
        val pokemonName: TextView
        val pokemonStats: TextView

        init {
            // Find our RecyclerView item's ImageView and TextViews for future use
            petImage = view.findViewById(R.id.pet_image)
            pokemonName = view.findViewById(R.id.pokemon_name)
            pokemonStats = view.findViewById(R.id.pokemon_stats)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pet_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPokemon = petList[position]

        Glide.with(holder.itemView)
            .load(currentPokemon.imageUrl)
            .centerCrop()
            .into(holder.petImage)

        holder.pokemonName.text = currentPokemon.name
        holder.pokemonStats.text = "Height: ${currentPokemon.height}, Weight: ${currentPokemon.weight}"
    }

    override fun getItemCount() = petList.size
}
