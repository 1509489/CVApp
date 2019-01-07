package com.pixelart.cvapp

import com.pixelart.cvapp.model.APIResponse
import com.pixelart.cvapp.network.ApiService
import com.pixelart.cvapp.ui.homescreen.HomeContract
import com.pixelart.cvapp.ui.homescreen.HomePresenterImpl
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class TestHomePresenter {
    private lateinit var presenter: HomePresenterImpl
    private lateinit var apiResponse: APIResponse

    @Mock private lateinit var view: HomeContract.View
    @Mock private lateinit var apiService: ApiService

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupSchedulers(){
            val scheduler = object : Scheduler(){
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        }
    }

    @Before
    fun setup(){
        presenter = HomePresenterImpl(view, apiService)
        apiResponse = APIResponse(Collections.emptyList())
    }

    @Test
    fun testApiSuccess(){
        Mockito.`when`(apiService.getCvs()).thenReturn(Single.just(apiResponse))
        presenter.getCVs()
        Mockito.verify(view).showCVs(Mockito.anyList())
    }

    @Test
    fun testApiFailure(){
        Mockito.`when`(apiService.getCvs()).thenReturn(Single.error(Throwable()))
        presenter.getCVs()
        Mockito.verify(view).showError("Error: ${Throwable().message}")
    }
}