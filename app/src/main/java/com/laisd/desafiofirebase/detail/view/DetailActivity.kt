package com.laisd.desafiofirebase.detail.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.edit.view.EditActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private val btnBackDetail: Button by lazy { findViewById<Button>(R.id.detailBtnBack) }
    private val editFloatingActionButton: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.editFloatingActionButton) }
    private val detailImg: ImageView by lazy { findViewById<ImageView>(R.id.imgGameLarge) }
    private val detailGameNameLarge: TextView by lazy { findViewById<TextView>(R.id.txtDetailGameName1) }
    private val detailGameNameSmall: TextView by lazy { findViewById<TextView>(R.id.txtDetailGameName2) }
    private val detailGameYear: TextView by lazy { findViewById<TextView>(R.id.txtDetailGameYear) }
    private val detailGameDescription: TextView by lazy { findViewById<TextView>(R.id.txtGameDescription) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        btnBackDetail.setOnClickListener {
            finish()
        }

        //recebe da HomeActivity
        val gameId = intent.getStringExtra("id")
        val gameImg = intent.getStringExtra("img")
        val gameNome = intent.getStringExtra("nome")
        val gameAno = intent.getStringExtra("ano")
        val gameDescricao = intent.getStringExtra("descricao")

        Picasso.get()
            .load(
                gameImg?.replace(
                    "http://",
                    "https://"
                )
            ).into(detailImg)
        detailGameNameLarge.text = gameNome
        detailGameNameSmall.text = gameNome
        detailGameYear.text = gameAno
        detailGameDescription.text = gameDescricao


        editFloatingActionButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)

            intent.putExtra("id", gameId)
            intent.putExtra("img", gameImg)
            intent.putExtra("nome", gameNome)
            intent.putExtra("ano", gameAno)
            intent.putExtra("descricao", gameDescricao)

            startActivity(intent)
        }

    }
}


