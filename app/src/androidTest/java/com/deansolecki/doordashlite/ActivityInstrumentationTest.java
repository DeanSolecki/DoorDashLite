package com.deansolecki.doordashlite;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.deansolecki.doordashlite.api.DoorDashService;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4.class)
public class ActivityInstrumentationTest {
    @Test
    public void loadFeedFragment() {
        ActivityScenario<FeedActivity> scenario = ActivityScenario.launch(FeedActivity.class);
        onView(withId(R.id.feed_recycler_view))
                .check(matches(anything()));
    }

    @Test
    public void transitionToDetailFragment() {
        // Note: requires animations disabled.
        // ToDo: This happens to be the only test that waits for an api call, but idlingResource could be registered with a helper.
        IdlingResource idlingResource = OkHttp3IdlingResource.create("OkHttp", DoorDashService.getOkHttpClient());
        IdlingRegistry.getInstance().register(idlingResource);
        ActivityScenario<FeedActivity> scenario = ActivityScenario.launch(FeedActivity.class);
        onView(withId(R.id.feed_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.detail_constraint_layout))
                .check(matches(anything()));
    }

}