package com.laisd.desafiofirebase.edit.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.laisd.desafiofirebase.AppUtils
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.model.Game
import com.laisd.desafiofirebase.home.repository.GameRepository
import com.laisd.desafiofirebase.home.view.HomeActivity
import com.laisd.desafiofirebase.home.viewmodel.GameViewModel
import com.squareup.picasso.Picasso

class EditActivity : AppCompatActivity() {

    private var imageUri: Uri? = null
    private lateinit var viewmodel: GameViewModel

    private val editImg: ImageView by lazy { findViewById<ImageView>(R.id.editImgChange) }
    private val editName: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editTxtEdtName) }
    private val editYear: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editTxtEdtCreated) }
    private val editDescription: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editTxtEdtDescription) }
    private val btnSaveGame: Button by lazy { findViewById<Button>(R.id.editBtnSaveGame) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val userId = AppUtils.getUserId()

        viewmodel = ViewModelProvider(
            this,
            GameViewModel.GameViewModelFactory(GameRepository())
        ).get(GameViewModel::class.java)


        editImg.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                        this@EditActivity,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    searchFile()
                }
                else -> ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1
                )
            }
        }

        //recebe da DetailActivity ou da HomeActivity
        var gameId = intent.getStringExtra("id")
        val gameImg = intent.getStringExtra("img")
        val gameNome = intent.getStringExtra("nome")
        val gameAno = intent.getStringExtra("ano")
        val gameDescricao = intent.getStringExtra("descricao")

        if (!gameImg!!.isEmpty()) {
            Picasso.get()
                .load(
                    gameImg?.replace(
                        "http://",
                        "https://"
                    )
                ).into(editImg)
        }
        editName.setText(gameNome)
        editYear.setText(gameAno)
        editDescription.setText(gameDescricao)


        btnSaveGame.setOnClickListener {
            if (gameId == null || gameId!!.isEmpty()) {
                gameId = "${System.currentTimeMillis()}"
            }

            val newGame = Game(
                    gameId,
                    editName.text.toString(),
                    gameImg,
                    editYear.text.toString(),
                    editDescription.text.toString()
            )

            viewmodel.imgUrl.observe(this, Observer {
                newGame.img = it
            })

            AppUtils.addToDatabase(newGame)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun searchFile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, CONTENT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //para saber se o request code é o mesmo que enviou em search file e se a requisição está ok (nao foi cancelada)
        if (requestCode == CONTENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            editImg.setImageURI(imageUri)
            viewmodel.setUrl(this, imageUri)
        }
    }

    companion object {
        const val CONTENT_REQUEST_CODE = 1
    }
}