package com.jafir.qingning;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.TestMethod;
import android.util.Log;

import org.kymjs.kjframe.utils.KJLoger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);



    }


}