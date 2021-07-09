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

class LoginInstrumentedTest {

    @get : Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginUI(){
        onView(withId(R.id.etUsername))
            .perform(typeText("UnishBhattarai"))


        onView(withId(R.id.etPassword))
            .perform(typeText("Unish12345"))

        closeSoftKeyboard()

        onView(withId(R.id.loginbtn))
            .perform(click())

        onView(withId(R.id.etdefUsername))
            .check(matches(isDisplayed()))
    }
}