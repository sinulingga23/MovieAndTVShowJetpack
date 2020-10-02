package com.example.movieandtvshowjetpack.ui.detail.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.movieandtvshowjetpack.R;
import com.example.movieandtvshowjetpack.data.source.local.entity.TVShowEntity;
import com.example.movieandtvshowjetpack.utils.EspressoIdlingResource;
import com.example.movieandtvshowjetpack.utils.FakeDataDummyTVShow;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DetailsTVShowActivityTest {
    private TVShowEntity dummyTVShow = FakeDataDummyTVShow.generateDummyTVShow().get(0);

    @Rule
    public ActivityTestRule<DetailsTVShowActivity> activityRule = new ActivityTestRule<DetailsTVShowActivity>(DetailsTVShowActivity.class) {
      @Override
      protected Intent getActivityIntent() {
          Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
          Intent result = new Intent(targetContext, DetailsTVShowActivity.class);
          result.putExtra(DetailsTVShowActivity.EXTRA_TV_SHOW, dummyTVShow.getId());
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
    public void loadTVShow() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(withText(dummyTVShow.getName())));

        onView(withId(R.id.date_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.date_detail)).check(matches(withText(dummyTVShow.getFirstAirDate())));

        onView(withId(R.id.vote_average_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.vote_average_detail)).check(matches(withText(String.valueOf((float) dummyTVShow.getVoteAverage()))));


        onView(withId(R.id.overview_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.overview_detail)).check(matches(withText(dummyTVShow.getOverview())));
    }
}