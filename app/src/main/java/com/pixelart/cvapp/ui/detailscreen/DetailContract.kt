package com.pixelart.cvapp.ui.detailscreen

import com.pixelart.cvapp.base.BasePresenter
import com.pixelart.cvapp.base.BaseView
import com.pixelart.cvapp.model.CareerSummary
import com.pixelart.cvapp.model.SampleCv

interface DetailContract {
    interface View: BaseView{
        fun showCvDetails(name: String, address: String, phoneEmail: String, profile: String,
                          coreSkills: String, careerSummary: String, eduQual: String, references: String)
    }

    interface Presenter:BasePresenter{
        fun getCvDetails(cv: SampleCv)
    }
}