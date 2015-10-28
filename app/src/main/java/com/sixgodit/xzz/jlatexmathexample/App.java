package com.sixgodit.xzz.jlatexmathexample;

import android.app.Application;

import org.scilab.forge.jlatexmath.core.AjLatexMath;

import java.util.concurrent.Callable;

import bolts.Task;

/**
 * Created by xuzhenzhou on 15/10/28.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Task.callInBackground(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                AjLatexMath.init(getApplicationContext());
                return null;
            }
        });
    }
}
