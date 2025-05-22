package com.pudutech.peanut.robot_ui.util.finishapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class ControlActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public static int sAnimationId;
    private List<Activity> activities = new LinkedList();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        addActivity(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }

    public void addActivity(Activity activity) {
        if (this.activities == null) {
            this.activities = new LinkedList();
        }
        if (this.activities.contains(activity)) {
            return;
        }
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (this.activities.contains(activity)) {
            this.activities.remove(activity);
        }
        if (this.activities.size() == 0) {
            this.activities = null;
        }
    }

    public void removeAllActivities() {
        for (Activity activity : this.activities) {
            if (activity != null) {
                activity.finish();
                activity.overridePendingTransition(0, sAnimationId);
            }
        }
    }
}
