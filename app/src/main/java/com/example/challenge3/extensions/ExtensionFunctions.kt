package com.example.challenge3

import android.view.View
import androidx.core.view.isVisible

fun View.toggleVisibility() {
    if (this.isVisible) {
        this.visibility = View.INVISIBLE
    } else {
        this.visibility = View.VISIBLE
    }
}