package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.slider.presentation.CoinsListScreen
import org.example.project.theme.FilmRailTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    FilmRailTheme {
        CoinsListScreen {

        }
    }
}