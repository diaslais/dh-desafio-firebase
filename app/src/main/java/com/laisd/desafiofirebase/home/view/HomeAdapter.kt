package com.laisd.desafiofirebase.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.model.Game

class HomeAdapter(private val dataSet: List<Game>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var fotoGame = itemView.findViewById<ImageView>(R.id.imgGameRecycler)
        private var nomeGame = itemView.findViewById<MaterialTextView>(R.id.txtGameName)
        private var anoGame = itemView.findViewById<MaterialTextView>(R.id.txtGameYear)

        fun bind(game: Game){
            fotoGame.setImageResource(game.img)
            nomeGame.text = game.nome
            anoGame.text = game.ano.toString()
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