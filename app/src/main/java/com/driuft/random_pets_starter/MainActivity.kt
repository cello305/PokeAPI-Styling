package com.driuft.random_pets_starter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var petList: MutableList<Pokemon>
    private lateinit var rvPets: RecyclerView

    data class Pokemon(val name: String, val imageUrl: String, val height: Int, val weight: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPets = findViewById(R.id.pet_list)
        petList = mutableListOf()

        val layoutManager = LinearLayoutManager(this)
        rvPets.layoutManager = layoutManager

        for (i in 1..20) {
            getPokemonData(i)
        }
    }

    private fun getPokemonData(id: Int) {
        val client = AsyncHttpClient()
        val url = "https://pokeapi.co/api/v2/pokemon/$id/"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Pokemon Success", "$json")

                val pokemonName = json.jsonObject.getString("name")
                val pokemonImageURL = json.jsonObject.getJSONObject("sprites").getString("front_default")
                val pokemonHeight = json.jsonObject.getInt("height")
                val pokemonWeight = json.jsonObject.getInt("weight")

                val pokemon = Pokemon(pokemonName, pokemonImageURL, pokemonHeight, pokemonWeight)
                petList.add(pokemon)

                val adapter = PetAdapter(petList)
                rvPets.adapter = adapter
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Pokemon Error", errorResponse)
            }
        })
    }
}