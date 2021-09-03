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

class AddMember1InstrumentedTesting {

    @get : Rule

    val testRule = ActivityScenarioRule(AddNewMemberActivity::class.java)

    @Test
    //Testing for adding Employee
    fun testAddMember1UI() {
        onView(withId(R.id.tvMemberP))
            .perform(click())

        onView(withId(R.id.etUsername))
            .perform(typeText("Kanchha"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.lstname))
            .perform(typeText("Sharma"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.memberphonenum))
            .perform(typeText("9841454545"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.memberaddress))
            .perform(typeText("9841454545"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.llemploye))
            .perform(click())

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.commission))
            .perform(typeText("7.22"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.btnAddMember))
            .perform(click())

        onView(withId(R.id.content))
            .check(matches(isDisplayed()))
    }
}