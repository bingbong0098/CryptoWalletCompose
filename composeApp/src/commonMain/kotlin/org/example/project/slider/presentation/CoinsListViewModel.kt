package org.example.project.slider.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.project.slider.domain.GetCoinsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.example.project.core.domain.Result
import kotlinx.coroutines.flow.update

class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SliderState())
    val state = _state
        .onStart {
            getAllCoins()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SliderState()
        )

    private suspend fun getAllCoins() {
        when(val coinsResponse = getCoinsListUseCase.execute()) {
            is Result.Success -> {
                _state.update {
                    SliderState(
                        coins = coinsResponse.data.map { coinItem ->
                            UiCoinListItem(
                                id = coinItem.coin.id,
                                name = coinItem.coin.name,
                                iconUrl = coinItem.coin.iconUrl,
                                symbol = coinItem.coin.symbol,
                                formattedPrice = coinItem.price.toString(),
                                formattedChange = coinItem.change.toString(),
                                isPositive = coinItem.change >= 0,
                            )
                        }
                    )
                }
            }
            is Result.Error -> {
                _state.update {
                    it.copy(
                        coins = emptyList(),
                        error = null,
                    )
                }
            }
        }
    }

//    fun onCoinLongPressed(coinId: String) {
//        _state.update {
//            it.copy(
//                chartState = UiChartState(
//                    sparkLine = emptyList(),
//                    isLoading = true,
//                )
//            )
//        }
//
//        viewModelScope.launch {
//            when(val priceHistory = getCoinPriceHistoryUseCase.execute(coinId)) {
//                is Result.Success -> {
//                    _state.update { currentState ->
//                        currentState.copy(
//                            chartState = UiChartState(
//                                sparkLine = priceHistory.data.sortedBy { it.timestamp }.map { it.price },
//                                isLoading = false,
//                                coinName = _state.value.coins.find { it.id == coinId }?.name.orEmpty(),
//                            )
//                        )
//                    }
//                }
//                is Result.Error -> {
//                    _state.update { currentState ->
//                        currentState.copy(
//                            chartState = UiChartState(
//                                sparkLine = emptyList(),
//                                isLoading = false,
//                                coinName = "",
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    }

//    fun onDismissChart() {
//        _state.update {
//            it.copy(chartState = null)
//        }
//    }
}