package com.pixelart.cvapp.ui.detailscreen

import android.os.Bundle
import com.pixelart.cvapp.base.BaseActivity
import com.pixelart.cvapp.databinding.ActivityDetailBinding
import com.pixelart.cvapp.di.ApplicationModule
import com.pixelart.cvapp.di.DaggerApplicationComponent
import com.pixelart.cvapp.model.SampleCv
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailContract.Presenter>(), DetailContract.View {

    @Inject lateinit var presenter: DetailContract.Presenter
    @Inject lateinit var binder: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cv: SampleCv = intent.getParcelableExtra("cv")
        presenter.getCvDetails(cv)
    }

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .injectDetailScreen(this)
    }

    override fun getView(): DetailContract.Presenter = presenter

    override fun showCvDetails(
        name: String,
        address: String,
        phoneEmail: String,
        profile: String,
        coreSkills: String,
        careerSummary: String,
        eduQual: String,
        references: String
    ) {
        binder.tvName.text = name
        binder.tvAddress.text = address
        binder.tvPhoneEmail.text = phoneEmail
        binder.tvProfile.text = profile
        binder.tvCoreSkills.text = coreSkills
        binder.tvCareerSummary.text = careerSummary
        binder.tvEducationQualifications.text = eduQual
        binder.tvReferences.text = references
    }
}
