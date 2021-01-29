package com.laisd.desafiofirebase.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.model.Game
import com.laisd.desafiofirebase.home.repository.GameRepository
import com.laisd.desafiofirebase.home.viewmodel.GameViewModel
import com.squareup.picasso.Picasso

class HomeAdapter(
    private val dataSet: List<Game>,
    val listener: (Game) -> Unit
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var fotoGame = itemView.findViewById<ImageView>(R.id.imgGameRecycler)
        private var nomeGame = itemView.findViewById<MaterialTextView>(R.id.txtGameName)
        private var anoGame = itemView.findViewById<MaterialTextView>(R.id.txtGameYear)


        fun bind(game: Game) = with(itemView) {
            Picasso.get()
                .load(
                    game.img?.replace(
                        "http://",
                        "https://"
                    )
                ).into(fotoGame)

            nomeGame.text = game.nome
            anoGame.text = game.ano

            setOnClickListener {
                listener(game)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}