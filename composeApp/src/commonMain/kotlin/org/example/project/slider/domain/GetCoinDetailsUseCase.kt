package org.example.project.slider.domain

import org.example.project.slider.data.mapper.toCoinModel
import org.example.project.slider.domain.api.CoinsRemoteDataSource
import org.example.project.slider.domain.model.CoinModel
import org.example.project.core.domain.DataError
import org.example.project.core.domain.map
import org.example.project.core.domain.Result

class GetCoinDetailsUseCase(
    private val client: CoinsRemoteDataSource,
) {
    suspend fun execute(coinId: String): Result<CoinModel, DataError.Remote> {
        return client.getCoinById(coinId).map { dto ->
            dto.data.coin.toCoinModel()
        }
    }
}