package com.pixelart.cvapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.pixelart.cvapp.model.Address
import com.pixelart.cvapp.model.CareerSummary
import com.pixelart.cvapp.model.SampleCv
import com.pixelart.cvapp.ui.detailscreen.DetailContract
import com.pixelart.cvapp.ui.detailscreen.DetailPresenterImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestDetailPresenter {
    private lateinit var presenter: DetailPresenterImpl
    private lateinit var sampleCv: SampleCv
    private lateinit var address: Address

    private val view: DetailContract.View = mock()

    @Before
    fun setup(){
        presenter = DetailPresenterImpl(view)
        address = Address("", "", "")

        val careerSummary = ArrayList<CareerSummary>()
        val edQual = ArrayList<String>()
        val references = ArrayList<String>()
        val coreSkills = ArrayList<String>()


        sampleCv = SampleCv("Laura Jones", "laura@jones.com", "07070548652", address,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ", coreSkills, careerSummary, edQual, references)
    }

    @Test
    fun testSuccess(){
        presenter.getCvDetails(sampleCv)

        verify(view).showCvDetails(anyString(), anyString(), anyString(), anyString(),
            anyString(), anyString(), anyString(), anyString())
    }
}