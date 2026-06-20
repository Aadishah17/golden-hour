package com.goldenhour.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openDialer(number: String) {
    startActivity(Intent(Intent.ACTION_DIAL, "tel:$number".toUri()))
}
