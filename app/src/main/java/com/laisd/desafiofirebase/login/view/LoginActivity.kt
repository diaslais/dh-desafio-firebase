package com.laisd.desafiofirebase.login.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.view.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val email: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.loginTxtEdtEmail) }
    private val emailLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.loginTxtLayoutEmail) }
    private val password: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.loginTxtEdtPassword) }
    private val passwordLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.loginTxtLayoutPassword) }
    private val registerBtn: Button by lazy { findViewById<Button>(R.id.loginBtnRegister) }
    private val loginBtn: Button by lazy { findViewById<Button>(R.id.loginBtnLogIn) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            if (validate()){
                signIn(email.text.toString(), password.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validate(): Boolean {
        var nothingEmpty = true

        //email
        if (email.text.toString().isEmpty()){
            emailLayout.error = "Email is required"
            nothingEmpty = false
        }
        email.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailLayout.error = ""
            }
        })

        //password
        if (password.text.toString().isEmpty()){
            passwordLayout.error = "Password is required"
            nothingEmpty = false
        }
        password.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordLayout.error = ""
            }
        })

        return nothingEmpty
    }
}
