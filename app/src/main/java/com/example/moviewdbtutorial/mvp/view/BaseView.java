package com.example.moviewdbtutorial.mvp.view;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public interface BaseView {

    void onShowToast(String message);

    void onShowDialog(String message);

    void onHideDialog();

}
