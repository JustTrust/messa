package com.belichenko.a.messaga;

import okhttp3.Response;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Belichenko Anton on 27.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MessagePublish {
    private static MessagePublish instance;

    private PublishSubject<String> subject = PublishSubject.create();

    public static MessagePublish getInstance() {
        if (instance == null) {
            instance = new MessagePublish();
        }
        return instance;
    }

    public void setMessage(String msg) {
        if (msg != null && msg.trim().length() > 0) {
            subject.onNext(msg);
        }
    }

    public void setError(Throwable t, Response response) {
        subject.onError(t);
    }

    public Observable<String> getMessage() {
        return subject;
    }

}
