package com.eachut.cylinder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class LanguageSelectionInstrumentedTesting {
    @get : Rule
    val testRule = ActivityScenarioRule(WelcomeActivity::class.java)

    @Test
    fun testLanguageSelectionUI(){

        Espresso.onView(ViewMatchers.withId(R.id.english))
            .perform(ViewActions.click())

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etUsername))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}