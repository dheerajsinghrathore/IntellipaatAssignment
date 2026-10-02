package com.android.intellipaat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.intellipaat.presentation.navigation.AppNavGraph
import com.android.intellipaat.ui.theme.IntellipaatAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntellipaatAssignmentTheme {
                AppNavGraph()
            }
        }
    }
}
