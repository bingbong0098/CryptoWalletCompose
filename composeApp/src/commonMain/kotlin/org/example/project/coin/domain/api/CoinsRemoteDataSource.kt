package org.example.project.coin.domain.api

import org.example.project.coin.data.remote.dto.CoinDetailsResponseDto
import org.example.project.coin.data.remote.dto.CoinPriceHistoryResponseDto
import org.example.project.coin.data.remote.dto.CoinsResponseDto
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result

interface CoinsRemoteDataSource {

    suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote>

    suspend fun getPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote>

    suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote>
}