package com.chow.cleanmvvmhiltmultimodule.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun View.showWithCondition(isShown: Boolean) {
    visibility = if (isShown) View.VISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}
fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Activity.showToast(content: String) {
    Toast.makeText(this , content, Toast.LENGTH_SHORT).show()
}

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?) = true

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }

    })
}