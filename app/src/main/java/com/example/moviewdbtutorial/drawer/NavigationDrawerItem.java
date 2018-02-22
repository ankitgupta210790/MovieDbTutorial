package com.example.moviewdbtutorial.drawer;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public class NavigationDrawerItem {

    private boolean isHighlight;
    private String title;

    public NavigationDrawerItem() {
    }

    public NavigationDrawerItem(boolean isHighlight, String title) {
        this.isHighlight = isHighlight;
        this.title = title;
    }

    public boolean isHighlight() {
        return isHighlight;
    }

    public String getTitle() {
        return title;
    }

    public void setHighlight(boolean highlight) {
        isHighlight = highlight;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
