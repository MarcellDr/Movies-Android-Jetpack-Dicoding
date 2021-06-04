package com.marcelldr.cinemaz.presentation.activity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.tabs.TabLayout
import com.marcelldr.cinemaz.R
import com.marcelldr.cinemaz.data.dummy.DataDummy
import com.marcelldr.cinemaz.utils.IdlingResource
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesDummy = DataDummy.getMovies()
    private val tvDummy = DataDummy.getTv()
    private val favoriteMoviesDummy = DataDummy.getMoviesFavorite()
    private val favoriteTvDummy = DataDummy.getTvFavorite()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun appBehaviour() {
        //CHECK MOVIES & TV
        loadMovies()
        openDetailMovies()
        loadTv()
        openDetailTv()

        //FAVORITE MOVIES & DELETE FROM LIST
        favoriteMovies()
        loadFavoriteMovies()
        openDetailFavoriteMovies()
        unFavoriteMovies()

        //FAVORITE MOVIES & DELETE FROM DETAIL
        favoriteMovies()
        loadFavoriteMovies()
        openDetailFavoriteMovies()
        unFavoriteDetailMovies()

        //FAVORITE TV & DELETE FROM LIST
        favoriteTv()
        loadFavoriteTv()
        openDetailFavoriteTv()
        unFavoriteTv()

        //FAVORITE TV & DELETE FROM DETAIL
        favoriteTv()
        loadFavoriteTv()
        openDetailFavoriteTv()
        unFavoriteDetailTv()
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.moviesRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.moviesRV))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(moviesDummy.size))
    }

    @Test
    fun openDetailMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.moviesRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailOverview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailBackground))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun favoriteMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.moviesFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.moviesRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailFavorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadTv() {
        Espresso.onView(ViewMatchers.withId(R.id.TVFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.tvRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tvRV))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvDummy.size))
    }

    @Test
    fun openDetailTv() {
        Espresso.onView(ViewMatchers.withId(R.id.TVFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.tvRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailOverview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailBackground))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun favoriteTv() {
        Espresso.onView(ViewMatchers.withId(R.id.TVFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.tvRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailFavorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadFavoriteMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    favoriteMoviesDummy.size
                )
            )
    }

    @Test
    fun openDetailFavoriteMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailOverview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailBackground))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun unFavoriteMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteButton)).perform(click())
    }

    @Test
    fun unFavoriteDetailMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailFavorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun loadFavoriteTv() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.favoriteTabManager))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(favoriteTvDummy.size))
    }

    @Test
    fun openDetailFavoriteTv() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.favoriteTabManager))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailGenre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailRating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailOverview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailPoster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailBackground))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun unFavoriteTv() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.favoriteTabManager))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteButton)).perform(click())
    }

    @Test
    fun unFavoriteDetailTv() {
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.favoriteTabManager))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.favoriteRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.detailFavorite)).perform(click())
        Espresso.pressBack()
    }


    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = AllOf.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(TabLayout::class.java)
            )

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}