package org.example.project.slider.domain


import org.example.project.slider.data.mapper.toCoinModel
import org.example.project.slider.domain.api.CoinsRemoteDataSource
import org.example.project.slider.domain.model.CoinModel
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map

class GetCoinsListUseCase(
    private val client: CoinsRemoteDataSource,
) {

    suspend fun execute(): Result<List<CoinModel>, DataError.Remote> {
        return client.getListOfCoins().map { dto ->
            dto.data.coins.map { it.toCoinModel() }
        }
    }
}