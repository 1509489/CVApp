package com.pixelart.cvapp.ui.homescreen

import com.pixelart.cvapp.model.APIResponse
import com.pixelart.cvapp.network.ApiService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val view: HomeContract.View, private val apiService: ApiService): HomeContract.Presenter {

    override fun getCVs() {
        apiService.getCvs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<APIResponse>{
                override fun onSuccess(t: APIResponse) {
                    view.showCVs(t.sampleCvs)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.showError("Error: ${e.message}")
                }
            })
    }

    override fun onResume() {
        getCVs()
    }

    override fun onStop() {

    }
}