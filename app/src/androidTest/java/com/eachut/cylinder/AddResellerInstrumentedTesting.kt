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

class AddResellerInstrumentedTesting {
    @get : Rule

    val testRule = ActivityScenarioRule(AddNewMemberActivity::class.java)

    @Test
    fun testAddResellerUI() {
        onView(withId(R.id.tvCustomerP))
            .perform(click())

        onView(withId(R.id.etResellerfullname))
            .perform(typeText("Kanchha Sharma"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.pasalname))
            .perform(typeText("Kanchha Kirana Store"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.resellerphonenum))
            .perform(typeText("9841454545"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.reselleraddress))
            .perform(typeText("Taudaha"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.btnAddReseller))
            .perform(click())

        onView(withId(R.id.etresellerRate))
            .perform(typeText("1375"))

        closeSoftKeyboard()
        Thread.sleep(3000)

        onView(withId(R.id.content))
            .check(matches(isDisplayed()))
    }
}