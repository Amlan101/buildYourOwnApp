package com.amlan.rapidsplit.di

import androidx.room.Room
import com.amlan.rapidsplit.data.local.db.AppDatabase
import com.amlan.rapidsplit.data.repository.LoginRepository
import com.amlan.rapidsplit.data.repository.SplitRepository
import com.amlan.rapidsplit.data.repository.impl.LoginRepositoryImpl
import com.amlan.rapidsplit.data.repository.impl.SplitRepositoryImpl
import com.amlan.rapidsplit.domain.usecase.CalculateEstimateUseCase
import com.amlan.rapidsplit.domain.usecase.CalculateSplitUseCase
import com.amlan.rapidsplit.domain.usecase.GetDestinationsUseCase
import com.amlan.rapidsplit.domain.usecase.GetRideHistoryUseCase
import com.amlan.rapidsplit.domain.usecase.GetSplitHistoryUseCase
import com.amlan.rapidsplit.domain.usecase.LoginUseCase
import com.amlan.rapidsplit.domain.usecase.SaveSplitUseCase
import com.amlan.rapidsplit.domain.usecase.SelectPaymentMethodUseCase
import com.amlan.rapidsplit.ui.presentation.fuelsplit.FuelSplitViewModel
import com.amlan.rapidsplit.ui.presentation.history.SplitHistoryViewModel
import com.amlan.rapidsplit.ui.presentation.home.HomeViewModel
import com.amlan.rapidsplit.ui.presentation.login.LoginViewModel
import com.amlan.rapidsplit.ui.presentation.onboarding.OnboardingViewModel
import com.amlan.rapidsplit.ui.presentation.payment.PaymentViewModel
import com.amlan.rapidsplit.ui.presentation.ride_selection.RideSelectionViewModel
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import com.amlan.rapidsplit.ui.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { SplashViewModel() }
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RideSelectionViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel { FuelSplitViewModel(get(), get(), get()) }
    viewModel { SplitHistoryViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }

    // Repositories
    single<SplitRepository> { SplitRepositoryImpl(get()) }

    // Shared ViewModel
    single { RideSharedViewModel() }

    // UseCase
    single{ LoginUseCase(get()) }
    single<LoginRepository> { LoginRepositoryImpl() }
    single { CalculateEstimateUseCase() }
    single { SelectPaymentMethodUseCase() }
    single { CalculateSplitUseCase() }
    single { SaveSplitUseCase(get()) }
    single { GetSplitHistoryUseCase(get()) }
    single { GetDestinationsUseCase() }
    single { GetRideHistoryUseCase() }

    // Room
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "rapid_split.db").build()
    }
    single { get<AppDatabase>().splitDao() }
}