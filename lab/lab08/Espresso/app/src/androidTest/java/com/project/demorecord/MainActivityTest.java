package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void test1() {
        SystemClock.sleep(1000);
        add();
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }


    @Test
    public void test2() {
        SystemClock.sleep(1000);
        setAge("20");
        SystemClock.sleep(1000);
        add();
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }


    @Test
    public void test3() {
        SystemClock.sleep(1000);

        goToList();
        SystemClock.sleep(1000);

    }


    @Test
    public void test4() {
        SystemClock.sleep(1000);
        setName("Ying");
        SystemClock.sleep(1000);
        add();
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }


    @Test
    public void test5() {
        SystemClock.sleep(1000);
        setName("Ying");
        SystemClock.sleep(1000);
        setAge("20");
        SystemClock.sleep(1000);
        add();
        SystemClock.sleep(1000);
        goToList();
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
    }


    @Test
    public void test6() {
        SystemClock.sleep(1000);
        setName("Ladarat");
        SystemClock.sleep(1000);
        setAge("20");
        SystemClock.sleep(1000);
        add();
        SystemClock.sleep(1000);
        goToList();
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
    }


    @Test
    public void test7() {
        SystemClock.sleep(1000);
        setName("Somkait");
        SystemClock.sleep(1000);
        setAge("80");
        SystemClock.sleep(1000);
        add();
        SystemClock.sleep(1000);
        goToList();
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
    }


    public void test8() {
        SystemClock.sleep(1000);
        setName("Prayoch");
        SystemClock.sleep(1000);
        setAge("60");
        add();
        SystemClock.sleep(1000);
        goToList();
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
    }

    public void add() {
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
    }

    public void goToList() {
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
    }

    public void setName(String name) {
        onView(withId(R.id.editTExtName)).perform(replaceText(name), closeSoftKeyboard());
    }

    public void setAge(String age) {
        onView(withId(R.id.editTextAge)).perform(replaceText(age), closeSoftKeyboard());
    }
}
