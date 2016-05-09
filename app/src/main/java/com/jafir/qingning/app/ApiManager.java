package com.jafir.qingning.app;

import android.graphics.drawable.Drawable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jafir on 16/5/5.
 */
public class ApiManager {


    interface xxx{
        Drawable getDra();
    }

    public  static  xxx xxxxxx;

    public static Observable<Drawable> getXXX(final String xx){
        return Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                subscriber.onNext(xxxxxx.getDra());
                subscriber.onCompleted();
            }
        });
    }

}
