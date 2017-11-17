package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
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
        goToList();
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }


    @Test
    public void test4() {

        setName("Ying");
        add();
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
    }


    @Test
    public void test5() {
        clearList();
        setName("Ying");
        setAge("20");
        add();
        goToList();
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge)).check(matches(withText("20")));
    }


    @Test
    public void test6() {
        clearList();
        setName("Kawin");
        setAge("10");
        add();
        setName("Ladarat");
        setAge("20");
        add();
        goToList();
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge)).check(matches(withText("20")));
    }


    @Test
    public void test7() {
        clearList();
        setName("Kawin");
        setAge("10");
        add();
        setName("Kawin");
        setAge("10");
        add();
        setName("Somkait");
        setAge("80");
        add();
        goToList();
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge)).check(matches(withText("80")));
    }


    public void test8() {
        clearList();
        setName("Kawin");
        setAge("10");
        add();
        setName("Kawin");
        setAge("10");
        add();
        setName("Kawin");
        setAge("10");
        add();
        setName("Somkait");
        setAge("80");
        setName("Prayoch");
        setAge("60");
        add();
        goToList();
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge)).check(matches(withText("60")));
    }

    public void add() {
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
    }

    public void goToList() {
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
    }

    public void clearList() {
        goToList();
        onView(allOf(withId(R.id.btnClearList), withText("CLEAR LIST"))).perform(click());
    }

    public void setName(String name) {
        onView(withId(R.id.editTExtName)).perform(replaceText(name), closeSoftKeyboard());
    }

    public void setAge(String age) {
        onView(withId(R.id.editTextAge)).perform(replaceText(age), closeSoftKeyboard());
    }
}
