package org.example.project.slider.domain.model

import org.example.project.core.domain.slider.Coin

data class CoinModel(
    val coin: Coin,
    val price: Double,
    val change: Double,
)