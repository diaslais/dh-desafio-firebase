package com.laisd.desafiofirebase

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.laisd.desafiofirebase.home.model.Game

object AppUtils {

    fun getUserId(): String {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser!!.uid
        return userId
    }

    fun getFileExtension(context: Context, imgUri: Uri?): String? {
        val extension = MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(context.contentResolver.getType(imgUri!!))
        return extension
    }

    fun addToDatabase(game: Game) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(getUserId())
        myRef.child(game.id.toString()).setValue(game)
    }
}

