package com.pudutech.importmusic.p056ui;

import android.app.Activity;
import android.content.Context;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class ImPModuleActivityManager {
    private int visibleActivityCount = 0;
    private static final String TAG = ImPModuleActivityManager.class.getSimpleName();
    private static final ImPModuleActivityManager INSTANCE = new ImPModuleActivityManager();
    private static final Stack<Activity> activityStack = new Stack<>();

    private ImPModuleActivityManager() {
    }

    public static ImPModuleActivityManager getInstance() {
        return INSTANCE;
    }

    public boolean isAppVisibility() {
        return this.visibleActivityCount > 0;
    }

    public void setAppVisibility(boolean z) {
        this.visibleActivityCount += z ? 1 : -1;
    }

    public int getActivityCount() {
        return activityStack.size();
    }

    public void addActivityToStack(Activity activity) {
        activityStack.add(activity);
    }

    public void removeActivityFromStack(Activity activity) {
        if (activityStack.size() <= 0 || !activityStack.contains(activity)) {
            return;
        }
        activityStack.remove(activity);
    }

    public Activity getTopActivityInStack() {
        if (activityStack.isEmpty()) {
            return null;
        }
        return activityStack.lastElement();
    }

    public Activity findActivity(Class<?> cls) {
        Iterator<Activity> it = activityStack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next.getClass().equals(cls)) {
                return next;
            }
        }
        return null;
    }

    public void finishActivity() {
        if (activityStack.size() > 0) {
            Activity lastElement = activityStack.lastElement();
            Pdlog.m3273d(TAG, "finishActivity " + lastElement);
            finishActivity(lastElement);
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        Iterator<Activity> it = activityStack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next.getClass().equals(cls)) {
                arrayList.add(next);
            }
        }
        while (arrayList.size() > 0) {
            finishActivity((Activity) arrayList.get(0));
            arrayList.remove(0);
        }
    }

    public void finishAllActivity() {
        Iterator<Activity> it = activityStack.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (next != null) {
                next.finish();
            }
        }
    }

    public void finishOtherActivity(Activity activity) {
        for (int size = activityStack.size() - 1; size >= 0; size--) {
            Activity activity2 = activityStack.get(size);
            if (activity2 != null && activity != activity2) {
                activity2.finish();
            }
        }
    }

    public void finishOtherActivity(Class<?> cls) {
        for (int size = activityStack.size() - 1; size >= 0; size--) {
            Activity activity = activityStack.get(size);
            if (activity != null && !activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    public boolean activityIsExist(Class<?> cls) {
        for (int size = activityStack.size() - 1; size >= 0; size--) {
            Activity activity = activityStack.get(size);
            if (activity != null && activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public void appExit(Context context) {
        try {
            finishAllActivity();
            Runtime.getRuntime().exit(0);
        } catch (Exception unused) {
            Runtime.getRuntime().exit(-1);
        }
    }
}
