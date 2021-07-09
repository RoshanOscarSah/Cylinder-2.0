package com.eachut.cylinder


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
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
            .perform(typeText("AnishNepal"))


        onView(withId(R.id.etPassword))
            .perform(typeText("anish10"))

        closeSoftKeyboard()

        onView(withId(R.id.loginbtn))
            .perform(click())

        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.root_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}