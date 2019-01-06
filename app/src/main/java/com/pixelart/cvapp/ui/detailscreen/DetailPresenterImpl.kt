package com.pixelart.cvapp.ui.detailscreen

import com.pixelart.cvapp.model.SampleCv

class DetailPresenterImpl(private val view: DetailContract.View): DetailContract.Presenter {

    override fun getCvDetails(cv: SampleCv) {

        val name = cv.name
        val address = "${cv.address.street}, ${cv.address.town}, ${cv.address.postCod}"
        val phoneEmail = "Phone: ${cv.phoneNumber}, Email: ${cv.email}"
        val profile = cv.profile
        val coreSkills = (cv.coreSkills).joinToString("\n\u2022","\u2022", "", -1, "")
        val eduQual = (cv.educationQualifications).joinToString("\n\u2022", "\u2022", "",-1, "")
        val references = (cv.references).joinToString("\n\u2022", "\u2022", "",-1, "")

        //Career Summary
        var careerResults: List<String> = ArrayList()

        for (i in 0 until cv.careerSummary.size){
            val fromTo = "${cv.careerSummary[i].from} - ${cv.careerSummary[i].to}"
            val outline = cv.careerSummary[i].outline
            val keyResponsibilities = (cv.careerSummary[i].keyResponsibilities).joinToString("\n\u2022", "\u2022", "",-1, "")

            val output = "$fromTo \n\nOutline: \n$outline \n\nKey Responsibilities: \n$keyResponsibilities"

            (careerResults as ArrayList<String>).add(output)
        }
        val careerSummary = careerResults.joinToString("\n\n", "", "",-1, "")

        view.showCvDetails(name, address, phoneEmail, profile, coreSkills, careerSummary, eduQual, references)
    }

    override fun onResume() {

    }

    override fun onStop() {

    }
}