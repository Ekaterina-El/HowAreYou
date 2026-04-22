package com.ekaterinael.howareyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.ekaterinael.howareyou.ui.DefaultRootComponent
import com.ekaterinael.howareyou.ui.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = DefaultRootComponent(defaultComponentContext())
        setContent {
            RootContent(root)
        }
    }
}