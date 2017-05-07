package com.buildone.rxbus;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Alessandro Pryds on 12/04/2017.
 */

public class RxBus {

    private static RxBus instance;

    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    /**
     * Pass any event down to event listeners.
     */
    public void publish(Object object) {
        subject.onNext(object);
    }

    /**
     * Subscribe to this Observable. On event, do something
     * e.g. replace a fragment
     */
    public Observable<Object> getEvents() {
        return subject;
    }
}
