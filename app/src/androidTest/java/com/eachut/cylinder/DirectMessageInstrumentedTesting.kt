package com.eachut.cylinder

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.eachut.cylinder.entity.Company
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test

class DirectMessageInstrumentedTesting {

    @get : Rule
    val testRule = ActivityScenarioRule(ReceiptActivity::class.java)

    @Test
    fun testDirectMessage()
    {
        Espresso.onView(withId(R.id.btnSendmessage))
            .perform(ViewActions.click())
    }
}