package com.openclassrooms.entrevoisins.utils;

import android.support.annotation.IdRes;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;


public class ClickChildViewWithId implements ViewAction {
    @IdRes
    private final int viewId;

    public ClickChildViewWithId(@IdRes int viewId) {
        this.viewId = viewId;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on a child view with specified id : " + viewId;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View v = view.findViewById(viewId);

        v.performClick();
    }
}
