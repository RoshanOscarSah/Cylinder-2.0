package com.eachut.cylinder

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.eachut.cylinder.entity.Company
import com.eachut.cylinder.ui.stock.StockFragment
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test

class StockEntryInstrumentedTesting {

    @get : Rule
    val testRule = launchFragmentInContainer<StockFragment>()

    @Test
    fun testStockEntry()
    {
        Espresso.onView(withId(R.id.tvCustomer))
            .perform(ViewActions.click())

        Espresso.closeSoftKeyboard()

        val myArray1 = listOf<Company>()
        val size1 : Int = myArray1.size
        for (i in 0 until size1)
        {
            Espresso.onView(withId(R.id.llSelectCustomer)).perform(ViewActions.click())
            Espresso.onData(CoreMatchers.`is`(myArray1[i])).perform(ViewActions.click())
        }

        Espresso.onView(withId(R.id.llFullSelected))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tvRegular))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.etGas1R))
            .perform(ViewActions.typeText("10"))

        Espresso.onView(withId(R.id.etGas4R))
            .perform(ViewActions.typeText("10"))

        Espresso.onView(withId(R.id.llStockSend))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.ettotalamount))
                    .perform(ViewActions.typeText("100000"))

        Espresso.onView(withId(R.id.etremarks))
            .perform(ViewActions.typeText("100000"))

        Espresso.onView(withId(R.id.llGo))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.llpdf))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}