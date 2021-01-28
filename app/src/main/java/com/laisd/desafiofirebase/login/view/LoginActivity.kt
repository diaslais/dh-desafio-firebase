package com.laisd.desafiofirebase.login.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
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
    private lateinit var prefs: SharedPreferences

    private val email: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.loginTxtEdtEmail) }
    private val emailLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.loginTxtLayoutEmail) }
    private val password: TextInputEditText by lazy { findViewById<TextInputEditText>(R.id.loginTxtEdtPassword) }
    private val passwordLayout: TextInputLayout by lazy { findViewById<TextInputLayout>(R.id.loginTxtLayoutPassword) }
    private val registerBtn: Button by lazy { findViewById<Button>(R.id.loginBtnRegister) }
    private val loginBtn: Button by lazy { findViewById<Button>(R.id.loginBtnLogIn) }
    private val remember: CheckBox by lazy { findViewById<CheckBox>(R.id.loginCheckbox) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


        registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            if (validate()){
                rememberChecked(prefs)
                signIn(email.text.toString(), password.text.toString())
            }
        }
    }

    private fun rememberChecked(sp: SharedPreferences): Boolean {
        val checkRemember = sp.getBoolean(REMEMBER, false)

        if (remember.isChecked) {
            sp.edit().putBoolean(REMEMBER, true).apply()
        } else {
            sp.edit().putBoolean(REMEMBER, false).apply()
        }
        return checkRemember
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        if (rememberChecked(prefs)) {
            val currentUser = auth.currentUser
            updateUI(currentUser)
        }
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    companion object {
        const val PREFS_NAME = "remember_prefs"
        const val REMEMBER = "remember"
    }
}
