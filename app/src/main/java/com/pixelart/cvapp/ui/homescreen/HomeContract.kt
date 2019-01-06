package com.pixelart.cvapp.ui.homescreen

import com.pixelart.cvapp.base.BasePresenter
import com.pixelart.cvapp.base.BaseView
import com.pixelart.cvapp.model.SampleCv

interface HomeContract {
    interface View: BaseView{
        fun showCVs(cvs: List<SampleCv>)
    }

    interface Presenter: BasePresenter{
        fun getCVs()
    }
}