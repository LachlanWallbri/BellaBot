package com.pudutech.disinfect.baselib.ext.lifecycle;

import android.app.Activity;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: KtxActivityManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fJ\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/ext/lifecycle/KtxActivityManager;", "", "()V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "mActivityList", "Ljava/util/LinkedList;", "finishActivity", "", "clazz", "Ljava/lang/Class;", "finishAllActivity", "finishCurrentActivity", "act", "popActivity", "activity", "pushActivity", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class KtxActivityManager {
    public static final KtxActivityManager INSTANCE = new KtxActivityManager();
    private static final LinkedList<Activity> mActivityList = new LinkedList<>();

    private KtxActivityManager() {
    }

    public final Activity getCurrentActivity() {
        if (mActivityList.isEmpty()) {
            return null;
        }
        return mActivityList.getLast();
    }

    public final void pushActivity(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (mActivityList.contains(activity)) {
            if (!Intrinsics.areEqual(mActivityList.getLast(), activity)) {
                mActivityList.remove(activity);
                mActivityList.add(activity);
                return;
            }
            return;
        }
        mActivityList.add(activity);
    }

    public final void popActivity(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        mActivityList.remove(activity);
    }

    public final void finishCurrentActivity(Activity act) {
        Intrinsics.checkParameterIsNotNull(act, "act");
        mActivityList.remove(act);
        act.finish();
    }

    public final void finishActivity(Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Iterator<Activity> it = mActivityList.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (Intrinsics.areEqual(next.getClass(), clazz)) {
                next.finish();
            }
        }
    }

    public final void finishAllActivity() {
        Iterator<Activity> it = mActivityList.iterator();
        while (it.hasNext()) {
            it.next().finish();
        }
    }
}
