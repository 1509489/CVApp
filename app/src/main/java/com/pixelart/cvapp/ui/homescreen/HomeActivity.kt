package com.pixelart.cvapp.ui.homescreen

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelart.cvapp.adapter.HomeRVAdapter
import com.pixelart.cvapp.base.BaseActivity
import com.pixelart.cvapp.databinding.ActivityHomeBinding
import com.pixelart.cvapp.di.ApplicationModule
import com.pixelart.cvapp.di.DaggerApplicationComponent
import com.pixelart.cvapp.di.NetworkModule
import com.pixelart.cvapp.model.SampleCv
import com.pixelart.cvapp.ui.detailscreen.DetailActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View, HomeRVAdapter.OnItemClickedListener {

    @Inject lateinit var presenter: HomeContract.Presenter
    @Inject lateinit var binder: ActivityHomeBinding

    private lateinit var adapter: HomeRVAdapter
    private lateinit var sampleCvs: ArrayList<SampleCv>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeRVAdapter(this)
        binder.rvHome.layoutManager = LinearLayoutManager(this)
        binder.rvHome.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binder.rvHome.adapter = adapter

        sampleCvs = ArrayList()
    }

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .injectHomeScreen(this)
    }

    override fun getView(): HomeContract.Presenter = presenter

    override fun showCVs(cvs: List<SampleCv>) {
        adapter.submitList(cvs)
        sampleCvs = cvs as ArrayList<SampleCv>
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onItemClicked(position: Int) {
        val cv = sampleCvs[position]

        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("cv", cv)
        })
    }
}
