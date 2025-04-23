package org.example.project.slider.domain


import org.example.project.slider.data.mapper.toPriceModel
import org.example.project.slider.domain.api.CoinsRemoteDataSource
import org.example.project.slider.domain.model.PriceModel
import org.example.project.core.domain.DataError
import org.example.project.core.domain.map
import org.example.project.core.domain.Result


class GetCoinPriceHistoryUseCase(
    private val client: CoinsRemoteDataSource,
) {

    suspend fun execute(coinId: String): Result<List<PriceModel>, DataError.Remote> {
        return client.getPriceHistory(coinId).map { dto ->
            dto.data.history.map { it.toPriceModel() }
        }
    }
}