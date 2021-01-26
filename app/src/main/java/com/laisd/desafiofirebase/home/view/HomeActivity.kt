package com.laisd.desafiofirebase.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laisd.desafiofirebase.R
import com.laisd.desafiofirebase.detail.view.DetailActivity
import com.laisd.desafiofirebase.home.model.Game
import com.laisd.desafiofirebase.login.view.RegisterActivity

class HomeActivity : AppCompatActivity() {
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
        val recyclerView = findViewById<RecyclerView>(R.id.homeRecyclerView)

        recyclerView.adapter = HomeAdapter(list) {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}