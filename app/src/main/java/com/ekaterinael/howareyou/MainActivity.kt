package com.ekaterinael.howareyou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.ekaterinael.howareyou.ui.DefaultRootComponent
import com.ekaterinael.howareyou.ui.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var defaultRootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as HowAreYouApp).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = defaultRootComponentFactory.create(
            componentContext = defaultComponentContext()
        )

        setContent {
            RootContent(root)
        }
    }
}