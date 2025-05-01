package com.amlan.rapidsplit.di

import com.amlan.rapidsplit.data.repository.LoginRepository
import com.amlan.rapidsplit.data.repository.impl.LoginRepositoryImpl
import com.amlan.rapidsplit.domain.usecase.LoginUseCase
import com.amlan.rapidsplit.ui.presentation.login.LoginViewModel
import com.amlan.rapidsplit.ui.presentation.onboarding.OnboardingViewModel
import com.amlan.rapidsplit.ui.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get()) }
    single{
        LoginUseCase(get())
    }
    single<LoginRepository> {
        LoginRepositoryImpl()
    }
}