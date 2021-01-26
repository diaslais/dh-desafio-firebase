package com.laisd.desafiofirebase.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.view.HomeActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name = findViewById<EditText>(R.id.registerTxtEdtName)
        val email = findViewById<EditText>(R.id.registerTxtEdtEmail)
        val password = findViewById<EditText>(R.id.registerTxtEdtName)

        val createAccount = findViewById<Button>(R.id.registerBtnRegister)

        createAccount.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


    }







}