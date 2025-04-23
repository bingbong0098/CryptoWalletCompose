package org.example.project.di

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import org.example.project.core.network.HttpClientFactory
import org.example.project.slider.data.remote.impl.KtorCoinsRemoteDataSource
import org.example.project.slider.domain.GetCoinDetailsUseCase
import org.example.project.slider.domain.GetCoinsListUseCase
import org.example.project.slider.domain.api.CoinsRemoteDataSource
import org.example.project.slider.presentation.CoinsListViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule,
        )
    }

expect val platformModule: Module
val sharedModule = module {

    // core
    single<HttpClient> { HttpClientFactory.create(get()) }

//    // portfolio
//    single {
//        getPortfolioDatabase(get<RoomDatabase.Builder<PortfolioDatabase>>())
//    }
//    singleOf(::PortfolioRepositoryImpl).bind<PortfolioRepository>()
//    single { get<PortfolioDatabase>().portfolioDao() }
//    single { get<PortfolioDatabase>().userBalanceDao() }
//    viewModel { PortfolioViewModel(get()) }
//
    // coins list
    viewModel { CoinsListViewModel(get()) }
    singleOf(::GetCoinsListUseCase)
    singleOf(::KtorCoinsRemoteDataSource).bind<CoinsRemoteDataSource>()
    singleOf(::GetCoinDetailsUseCase)
//    singleOf(::GetCoinPriceHistoryUseCase)
//
//    // trade
//    singleOf(::BuyCoinUseCase)
//    singleOf(::SellCoinUseCase)
//    viewModel { (coinId: String) -> BuyViewModel(get(), get(), get(), coinId) }
//    viewModel { (coinId: String) -> SellViewModel(get(), get(), get(), coinId) }
}