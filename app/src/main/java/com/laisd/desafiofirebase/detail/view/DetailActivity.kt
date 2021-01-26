package com.laisd.desafiofirebase.detail.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.laisd.desafiofirebase.R

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
    }
}