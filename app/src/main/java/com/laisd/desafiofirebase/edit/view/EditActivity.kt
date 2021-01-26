package com.laisd.desafiofirebase.edit.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.laisd.desafiofirebase.R

class EditActivity : AppCompatActivity() {

    private val editImg: ImageView by lazy { findViewById<ImageView>(R.id.editImgChange) }
    private val editName: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editTxtEdtCreated) }
    private val editYear: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editTxtEdtName) }
    private val editDescription: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.editTxtEdtDescription) }
    private val btnSaveGame: Button by lazy { findViewById<Button>(R.id.editBtnSaveGame) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }
}