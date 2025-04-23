package org.example.project.slider.presentation

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
data class SliderState(
    val error: StringResource? = null,
    val coins: List<UiCoinListItem> = emptyList(),
//    val chartState: UiChartState? = null
)
