package org.example.project.slider.data.mapper

import org.example.project.slider.data.remote.dto.CoinItemDto
import org.example.project.slider.data.remote.dto.CoinPriceDto
import org.example.project.slider.domain.model.CoinModel
import org.example.project.slider.domain.model.PriceModel
import org.example.project.core.domain.slider.Coin

fun CoinItemDto.toCoinModel() = CoinModel(
    coin = Coin(
        id = uuid,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
    ),
    price = price,
    change = change,
)

fun CoinPriceDto.toPriceModel() = PriceModel(
    price = price ?: 0.0,
    timestamp = timestamp,
)