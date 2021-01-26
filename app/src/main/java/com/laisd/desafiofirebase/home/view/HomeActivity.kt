package com.laisd.desafiofirebase.home.view

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.detail.view.DetailActivity
import com.laisd.desafiofirebase.home.model.Game

class HomeActivity : AppCompatActivity() {

    private val recyclerview: RecyclerView by lazy { findViewById<RecyclerView>(R.id.homeRecyclerView) }
    private val searchBar: SearchView by lazy { findViewById<SearchView>(R.id.homeSearchView) }
    private val addFloatingActionButton: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.addFloatingActionButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list = listOf<Game>(
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho"),
                Game("Mostal Kombat", R.drawable.mortalkombat_large, 222, "joguinho")
        )
        makeRecyclerview(list)

    }

    private fun makeRecyclerview (list: List<Game>) {
        recyclerview.adapter = HomeAdapter(list) {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = GridLayoutManager(this, 2)
    }
}