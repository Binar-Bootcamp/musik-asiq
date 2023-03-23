package com.binaracademy.musikasiq.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Extension function to hide the soft keyboard for a view.
 */
fun View.hideSoftKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputMethodManager.isAcceptingText) {
        inputMethodManager.hideSoftInputFromWindow(
            windowToken,
            0
        )
    }
}

fun ImageView.load(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}