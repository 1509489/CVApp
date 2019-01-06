package com.pixelart.cvapp.di

import android.app.Activity
import androidx.databinding.DataBindingUtil
import com.pixelart.cvapp.R
import com.pixelart.cvapp.base.BaseActivity
import com.pixelart.cvapp.databinding.ActivityDetailBinding
import com.pixelart.cvapp.databinding.ActivityHomeBinding
import com.pixelart.cvapp.network.ApiService
import com.pixelart.cvapp.ui.detailscreen.DetailActivity
import com.pixelart.cvapp.ui.detailscreen.DetailContract
import com.pixelart.cvapp.ui.detailscreen.DetailPresenterImpl
import com.pixelart.cvapp.ui.homescreen.HomeContract
import com.pixelart.cvapp.ui.homescreen.HomePresenterImpl
import com.pixelart.cvapp.ui.homescreen.HomeActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApplicationModule(private val baseActivity: BaseActivity<*>){
    @Provides
    @Singleton
    fun providesHomeContractPresenter(apiService: ApiService): HomeContract.Presenter = HomePresenterImpl(baseActivity as HomeActivity, apiService)

    @Provides
    @Singleton
    fun providesHomeActivityBinding():ActivityHomeBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_home)

    @Provides
    @Singleton
    fun provideDetailContractPresenter(): DetailContract.Presenter = DetailPresenterImpl(baseActivity as DetailActivity)

    @Provides
    @Singleton
    fun providesDetailActivityBinding():ActivityDetailBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_detail)
}