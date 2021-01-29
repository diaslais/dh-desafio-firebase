package com.laisd.desafiofirebase.home.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.laisd.desafiofirebase.AppUtils
import com.laisd.desafiofirebase.home.model.Game
import com.laisd.desafiofirebase.home.repository.GameRepository

class GameViewModel (private val repository: GameRepository): ViewModel() {
    val gamesData = MutableLiveData<List<Game>>()
    val imgUrl = MutableLiveData<String>()

    fun getGames() {
        repository.getGames {
            gamesData.value = it
        }
    }

    fun setUrl(context: Context, imgUri: Uri?) {
        val user = FirebaseAuth.getInstance().currentUser

        if (imgUri != null && user != null) {
            val database = FirebaseStorage.getInstance()
            val storage = database.getReference("uploads")
            val extension = AppUtils.getFileExtension(context, imgUri)
            val fileReference = storage.child("${System.currentTimeMillis()}.${extension}")

            fileReference.putFile(imgUri)
                    .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { taskSnapshot ->
                    imgUrl.value = taskSnapshot.toString()
                }.addOnFailureListener {
                    Toast.makeText(context, "Falha no envio da imagem", Toast.LENGTH_LONG).show()
                    imgUrl.value = ""
                }
            }.addOnFailureListener {
                        Toast.makeText(context, "Falha no envio da imagem", Toast.LENGTH_LONG).show()
                    }
        } else {
            imgUrl.value = ""
        }
    }


    class GameViewModelFactory(private val repository: GameRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GameViewModel(repository) as T
        }
    }
}