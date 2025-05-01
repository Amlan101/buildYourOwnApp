package com.amlan.rapidsplit.di

import com.amlan.rapidsplit.data.repository.LoginRepository
import com.amlan.rapidsplit.data.repository.impl.LoginRepositoryImpl
import com.amlan.rapidsplit.domain.usecase.CalculateEstimateUseCase
import com.amlan.rapidsplit.domain.usecase.CalculateSplitUseCase
import com.amlan.rapidsplit.domain.usecase.LoginUseCase
import com.amlan.rapidsplit.domain.usecase.SelectPaymentMethodUseCase
import com.amlan.rapidsplit.ui.presentation.fuelsplit.FuelSplitViewModel
import com.amlan.rapidsplit.ui.presentation.login.LoginViewModel
import com.amlan.rapidsplit.ui.presentation.onboarding.OnboardingViewModel
import com.amlan.rapidsplit.ui.presentation.payment.PaymentViewModel
import com.amlan.rapidsplit.ui.presentation.ride_selection.RideSelectionViewModel
import com.amlan.rapidsplit.ui.presentation.shared.RideSharedViewModel
import com.amlan.rapidsplit.ui.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { RideSelectionViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel { FuelSplitViewModel(get(), get()) }

    single { RideSharedViewModel() }
    single{ LoginUseCase(get()) }
    single<LoginRepository> { LoginRepositoryImpl() }
    single { CalculateEstimateUseCase() }
    single { SelectPaymentMethodUseCase() }
    single { CalculateSplitUseCase() }
}