package com.example.moviewdbtutorial.mvp.presenter;

import com.example.moviewdbtutorial.mvp.view.BaseView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public class BasePresenter<T extends BaseView> {

    @Inject
    T view;

    @Inject
    CompositeDisposable compositeDisposable;

    public T getView() {
        return view;
    }

    protected <V> void subscribe(Observable<V> observable, Consumer<V> observer) {
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer));

//        compositeDisposable.add(observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer));
    }
}
