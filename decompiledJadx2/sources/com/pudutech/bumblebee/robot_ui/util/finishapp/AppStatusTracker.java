package com.pudutech.bumblebee.robot_ui.util.finishapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    private static final LinkedList<Activity> ACTIVITY_LIST = new LinkedList<>();
    private static final long MAX_INTERVAL = 300000;
    private static Intent intent;
    private static Activity topActivity;
    private static AppStatusTracker tracker;
    private String Tag = getClass().getSimpleName();
    private int activeCount;
    private Application application;
    private boolean isForground;
    private long timestamp;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    private AppStatusTracker(Application application) {
        this.application = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        tracker = new AppStatusTracker(application);
    }

    public static AppStatusTracker getInstance() {
        return tracker;
    }

    public boolean isForground() {
        return this.isForground;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        setTopActivity(activity);
        Pdlog.m3275i(this.Tag, "onActivityCreated ", " activity length:", Integer.valueOf(getActivityList().size()), "  activity:", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        setTopActivity(activity);
        if (this.activeCount == 0) {
            this.timestamp = SystemClock.elapsedRealtime();
        }
        this.activeCount++;
        Pdlog.m3275i(this.Tag, "onActivityStarted activity count:", Integer.valueOf(this.activeCount), " activity length:", Integer.valueOf(getActivityList().size()), "  activity:", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        setTopActivity(activity);
        this.isForground = true;
        intent = activity.getIntent();
        Pdlog.m3275i(this.Tag, "onActivityResumed ", " activity length:", Integer.valueOf(getActivityList().size()), "  activity:", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Pdlog.m3275i(this.Tag, "onActivityPaused ", " activity length:", Integer.valueOf(getActivityList().size()), "  activity:", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.activeCount--;
        if (this.activeCount == 0) {
            this.isForground = false;
            this.timestamp = SystemClock.elapsedRealtime() - this.timestamp;
        }
        Pdlog.m3275i(this.Tag, "onActivityStopped activity length:", Integer.valueOf(getActivityList().size()), "  activity:", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        ACTIVITY_LIST.remove(activity);
        Pdlog.m3275i(this.Tag, "onActivityDestroyed activity length:", Integer.valueOf(getActivityList().size()), "  activity:", activity);
    }

    public static Intent getIntent() {
        return intent;
    }

    private static void setTopActivity(Activity activity) {
        topActivity = activity;
        if (ACTIVITY_LIST.contains(activity)) {
            if (ACTIVITY_LIST.getLast().equals(activity)) {
                return;
            }
            ACTIVITY_LIST.remove(activity);
            ACTIVITY_LIST.addLast(activity);
            return;
        }
        ACTIVITY_LIST.addLast(activity);
    }

    public static void finishAllActivities() {
        finishAllActivities(false);
    }

    public static void finishAllActivities(boolean z) {
        LinkedList<Activity> activityList = getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = activityList.get(size);
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    public static LinkedList<Activity> getActivityList() {
        return ACTIVITY_LIST;
    }

    public static Activity getTopActivity() {
        return topActivity;
    }
}
