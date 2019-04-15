package com.pixelart.cvapp.ui.homescreen

import com.pixelart.cvapp.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val view: HomeContract.View, private val apiService: ApiService): HomeContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun getCVs() {
        compositeDisposable.add(
            apiService.getCvs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe({response -> view.showCVs(response.sampleCvs)},
                    {error -> view.showError("Error: ${error.message}")})
        )

    }

    override fun onResume() {
        getCVs()
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}