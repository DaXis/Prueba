package com.prueba.utils

import androidx.appcompat.app.AppCompatActivity
import com.prueba.flows.main.MainActivity

fun AppCompatActivity.launchLoginActivity() {
    startActivity(MainActivity.newIntent(this))
}