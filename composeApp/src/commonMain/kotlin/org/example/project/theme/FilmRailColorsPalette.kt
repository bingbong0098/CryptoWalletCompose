package org.example.project.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class FilmRailColorsPalette(
    val profitGreen: Color = Color.Unspecified,
    val lossRed: Color = Color.Unspecified,
)

val GreenColor = Color(color = 0xFF32de84)
val RedColor = Color(color = 0xFFD2122E)

val DarkGreenColor = Color(color = 0xFF32de84)
val DarkRedColor = Color(color = 0xFFD2122E)

val LightFilmRailColorsPalette = FilmRailColorsPalette(
    profitGreen = GreenColor,
    lossRed = RedColor,
)

val DarkFilmRailColorsPalette = FilmRailColorsPalette(
    profitGreen = DarkGreenColor,
    lossRed = DarkRedColor,
)

val LocalFilmRailColorsPalette = compositionLocalOf { FilmRailColorsPalette() }