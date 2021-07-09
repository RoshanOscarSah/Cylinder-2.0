package com.eachut.cylinder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class ChangePasswordInstrumentedTesting {
    @get : Rule
    val testRule = ActivityScenarioRule(ChangedefpassActivity::class.java)

    @Test
    fun testChangePasswordUI(){
        Espresso.onView(ViewMatchers.withId(R.id.etdefUsername))
            .perform(ViewActions.typeText("UnishBhattarai"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etdefPassword))
            .perform(ViewActions.typeText("Unish1234"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etnewPassword))
            .perform(ViewActions.typeText("Unish12345"))


        Espresso.onView(ViewMatchers.withId(R.id.changepassbtn))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.etUsername))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}