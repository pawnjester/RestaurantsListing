package com.example.restaurants

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
@ExperimentalCoroutinesApi
class RestaurantActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }


    @Test
    fun should_show_initial_state() {
        launchFragmentInHiltContainer<HomeFragment>()
        onView(withId(R.id.search_restaurants_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.header)).check(matches(isDisplayed()))
        onView(withId(R.id.restaurants_count)).check(matches(isDisplayed()))
        onView(withId(R.id.filter_restaurants)).check(matches(isDisplayed()))
        onView(withId(R.id.sort_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun show_show_data_when_search_is_performed() {
        launchFragmentInHiltContainer<HomeFragment>()
        onView(withId(R.id.search_restaurants_edit_text)).perform(ViewActions.typeText("Tanoshii Sushi"))
        onView(withId(R.id.rv_restaurants)).check(RecyclerViewItemCountAssertion(1));
    }

}