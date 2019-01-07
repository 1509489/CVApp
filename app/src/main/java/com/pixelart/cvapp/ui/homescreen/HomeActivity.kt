package com.pixelart.cvapp.ui.homescreen

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.idling.CountingIdlingResource
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

    private val idlingResource = CountingIdlingResource("Network_Call")

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

    override fun onResume() {
        super.onResume()
        idlingResource.increment()
        presenter.onResume()
    }

    override fun showCVs(cvs: List<SampleCv>) {
        adapter.submitList(cvs)
        idlingResource.decrement()
        sampleCvs = cvs as ArrayList<SampleCv>
    }

    fun getCountingIdlingResources():CountingIdlingResource = idlingResource

    override fun onItemClicked(position: Int) {
        val cv = sampleCvs[position]

        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("cv", cv)
        })
    }
}
