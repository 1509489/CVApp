package com.pixelart.cvapp

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pixelart.cvapp.adapter.HomeRVAdapter
import com.pixelart.cvapp.common.BASE_URL
import com.pixelart.cvapp.ui.homescreen.HomeActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    private val JSON_FILE = "api_success.json"
    private val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)
    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setup(){

        mockWebServer = MockWebServer()

        try {
            mockWebServer.start()
            mockWebServer.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(TestUtils.getStringFromFile(InstrumentationRegistry.getInstrumentation().context, JSON_FILE)))
        }catch (e: IOException){
            e.printStackTrace()
        }

        BASE_URL = mockWebServer.url("/").toString()
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun testDataFetchingSuccess(){
        val countingIdlingResource: CountingIdlingResource = activityTestRule.activity.getCountingIdlingResources()
        IdlingRegistry.getInstance().register(countingIdlingResource)

        Espresso.onView(ViewMatchers.withId(R.id.rvHome)).check(TestUtils.RecyclerViewItemCountAssertion(5))

        Espresso.onView(ViewMatchers.withId(R.id.rvHome)).check(
            ViewAssertions.matches(TestUtils.atPosition(0,
                ViewMatchers.hasDescendant(ViewMatchers.withText("Laura Jones")))))

        Espresso.onView(ViewMatchers.withId(R.id.rvHome)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeRVAdapter.ViewHolder>(0, ViewActions.click()))
    }
}