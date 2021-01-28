package com.laisd.desafiofirebase.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.home.view.HomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val name: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.registerTxtEdtName) }
    private val nameLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.registerTxtLayoutName) }
    private val email: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.registerTxtEdtEmail) }
    private val emailLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.registerTxtLayoutEmail) }
    private val password: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.registerTxtEdtPassword) }
    private val passwordLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.registerTxtLayoutPassword) }
    private val confirmPassword: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.registerTxtEdtConfirmPassword) }
    private val confirmPasswordLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.registerTxtLayoutConfirmPassword) }
    private val registerBtn: Button by lazy { findViewById<Button>(R.id.registerBtnRegister) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        registerBtn.setOnClickListener {
            if (validate()) {
                createAccount(email.text.toString(), password.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
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
        }
    }

    private fun validate(): Boolean {
        var nothingEmpty = true

        //name
        if (name.text.toString().isEmpty()){
            nameLayout.error = "Name is required"
            nothingEmpty = false
        }
        name.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameLayout.error = ""
            }
        })

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
        } else if (password.text.toString().length < 6){
            passwordLayout.error = "Password must have at least 6 characters"
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

        //confirmPassword
        if (confirmPassword.text.toString().isEmpty()){
            confirmPasswordLayout.error = "Password confirmation is required"
            nothingEmpty = false
        }
        confirmPassword.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                confirmPasswordLayout.error = ""
            }
        })

        //if both passwords are equals
        if (!confirmPassword.text.toString().equals(password.text.toString())){
            confirmPasswordLayout.error = "Password mismatch"
            nothingEmpty = false
        }

        return nothingEmpty
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}