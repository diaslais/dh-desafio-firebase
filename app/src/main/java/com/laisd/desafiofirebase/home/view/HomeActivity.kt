package com.laisd.desafiofirebase.home.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.laisd.desafiofirebase.AppUtils
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.detail.view.DetailActivity
import com.laisd.desafiofirebase.edit.view.EditActivity
import com.laisd.desafiofirebase.home.model.Game
import com.laisd.desafiofirebase.home.repository.GameRepository
import com.laisd.desafiofirebase.home.viewmodel.GameViewModel
import com.laisd.desafiofirebase.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var viewmodel: GameViewModel

    private val recyclerview: RecyclerView by lazy { findViewById<RecyclerView>(R.id.homeRecyclerView) }
    private val searchBar: SearchView by lazy { findViewById<SearchView>(R.id.homeSearchView) }
    private val addFloatingActionButton: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.addFloatingActionButton) }
    private val logoutButton: ImageButton by lazy { findViewById<ImageButton>(R.id.imgOptions) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        floatingActionButton()
        logoutBtn()

        //pega user id
        val userId = AppUtils.getUserId()

        //instancia viewmodel
        viewmodel = ViewModelProvider(
            this,
            GameViewModel.GameViewModelFactory(GameRepository())
        ).get(GameViewModel::class.java)

        //instancia database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(userId)

        viewmodel.getGames()

        //inicia banco com dados iniciais
        if (isFirstTime()) {
            viewmodel.gamesData.observe(this, Observer {
                it.forEach {
                    AppUtils.addToDatabase(it)
                }
            })
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = mutableListOf<Game>()
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataSnapshot.children.forEach {
                    val value = it.getValue(Game::class.java)

                    if (value != null) {
                        data.add(value)
                    }
                }
                println(data)
                makeRecyclerview(data)
            }
            override fun onCancelled(error: DatabaseError) {}
        })

    }

    private fun makeRecyclerview(gamesList: List<Game>) {
        recyclerview.adapter = HomeAdapter(gamesList) {
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)

            intent.putExtra("id", it.id)
            intent.putExtra("img", it.img)
            intent.putExtra("nome", it.nome)
            intent.putExtra("ano", it.ano)
            intent.putExtra("descricao", it.descricao)

            startActivity(intent)
        }

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = GridLayoutManager(this@HomeActivity, 2)
    }

    private fun floatingActionButton() {
        addFloatingActionButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("id", "")
            intent.putExtra("img", "")
            intent.putExtra("nome", "")
            intent.putExtra("ano", "")
            intent.putExtra("descricao", "")
            startActivity(intent)
        }
    }

    private fun logoutBtn() {
        logoutButton.setOnClickListener {
            val menu = PopupMenu(this, it)
            menu.setOnMenuItemClickListener {item ->
                when (item.itemId) {
                    R.id.sair -> {
                        signOut()
                        true
                    }
                    else -> false
                }
            }
            menu.inflate(R.menu.menu_main)
            menu.show()
        }
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isFirstTime(): Boolean {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val begin = prefs?.getBoolean(SHOWN, true)

        if (begin!!) {
            prefs.edit()?.putBoolean(SHOWN, false)?.apply()
        }
        return begin
    }

    companion object {
        const val PREFS_NAME = "welcome_prefs"
        const val SHOWN = "dialog_shown"
    }
}