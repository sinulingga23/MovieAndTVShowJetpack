package com.example.movieandtvshowjetpack.ui.detail.movie;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.MovieEntity;
import com.example.movieandtvshowjetpack.utils.EspressoIdlingResource;
import com.example.movieandtvshowjetpack.utils.FakeDataDummyMovie;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailsMovieActivityTest {
    private static final String TAG = DetailsMovieActivityTest.class.getSimpleName();

    private MovieEntity dummyMovie = FakeDataDummyMovie.generateDummyMovies().get(0);

    @Rule
    public ActivityTestRule<DetailsMovieActivity> activityRule = new ActivityTestRule<DetailsMovieActivity>(DetailsMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailsMovieActivity.class);
            result.putExtra(DetailsMovieActivity.EXTRA_MOVIE, dummyMovie.getId());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(withText(dummyMovie.getTitle())));

        onView(withId(R.id.date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.date_detail)).check(matches(withText(dummyMovie.getReleaseDate())));

        onView(withId(R.id.vote_average_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.vote_average_detail)).check(matches(withText(String.valueOf((float)dummyMovie.getVoteAverage()))));

        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.overview_detail)).check(matches(withText(dummyMovie.getOverview())));
    }
}