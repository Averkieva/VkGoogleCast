package com.example.vkgooglecast.di

import com.example.vkgooglecast.data.repository.CastVideoRepository
import com.example.vkgooglecast.domain.PlayVideoUseCase
import com.example.vkgooglecast.ui.viewmodel.CastVideoViewModel
import com.google.android.gms.cast.framework.CastContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { CastContext.getSharedInstance(get()) }

    single { CastVideoRepository() }

    single { PlayVideoUseCase(get()) }

    viewModel { CastVideoViewModel(get(), get()) }
}
