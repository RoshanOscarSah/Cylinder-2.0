package com.eachut.cylinder

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class AddCompanyInstrumentedTesting {
    @get : Rule

    val testRule = ActivityScenarioRule(AddNewMemberActivity::class.java)

    @Test
    fun testAddCompanyUI() {
        onView(withId(R.id.tvCompanyP))
            .perform(click())

        onView(withId(R.id.etCompanyname))
            .perform(typeText("Bhakta Gas and Cylinder Udhyog Pvt. Ltd."))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.cylindername))
            .perform(typeText("Bhanu Gas"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.companyphonenum))
            .perform(typeText("9841565656"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.companyaddress))
            .perform(typeText("Chitwan"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.btnAddCompany))
            .perform(click())

        onView(withId(R.id.content))
            .check(matches(isDisplayed()))
    }
}