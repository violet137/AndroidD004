package vn.com.greenacademy.shopping.Handle.HandleUi;

import android.app.Application;

/**
 * Created by zzzzz on 8/11/2017.
 */

public class ActivityHandler extends Application {

    public static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;// set true when activity resumed

    }

    public static void activityPaused() {
        activityVisible = false;// set false when activity paused

    }
}
