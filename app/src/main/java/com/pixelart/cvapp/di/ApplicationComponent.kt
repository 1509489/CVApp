package com.pixelart.cvapp.di

import com.pixelart.cvapp.ui.detailscreen.DetailActivity
import com.pixelart.cvapp.ui.homescreen.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun injectHomeScreen(homeActivity: HomeActivity)
    fun injectDetailScreen(detailActivity: DetailActivity)
}