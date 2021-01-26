package com.laisd.desafiofirebase.login.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

object AuthUtils {


    fun hideKeyboard(view: View) {
        val imm: InputMethodManager =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}