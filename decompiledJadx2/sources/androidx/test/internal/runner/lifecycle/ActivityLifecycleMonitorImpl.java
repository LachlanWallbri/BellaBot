package androidx.test.internal.runner.lifecycle;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.runner.lifecycle.ActivityLifecycleCallback;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor;
import androidx.test.runner.lifecycle.Stage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class ActivityLifecycleMonitorImpl implements ActivityLifecycleMonitor {
    private static final String TAG = "LifecycleMonitor";
    private List<ActivityStatus> activityStatuses;
    private final List<WeakReference<ActivityLifecycleCallback>> callbacks;
    private final boolean declawThreadCheck;

    public ActivityLifecycleMonitorImpl() {
        this(false);
    }

    public ActivityLifecycleMonitorImpl(boolean declawThreadCheck) {
        this.callbacks = new ArrayList();
        this.activityStatuses = new ArrayList();
        this.declawThreadCheck = declawThreadCheck;
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public void addLifecycleCallback(ActivityLifecycleCallback callback) {
        Checks.checkNotNull(callback);
        synchronized (this.callbacks) {
            boolean z = true;
            Iterator<WeakReference<ActivityLifecycleCallback>> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ActivityLifecycleCallback activityLifecycleCallback = it.next().get();
                if (activityLifecycleCallback == null) {
                    it.remove();
                } else if (activityLifecycleCallback == callback) {
                    z = false;
                }
            }
            if (z) {
                this.callbacks.add(new WeakReference<>(callback));
            }
        }
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public void removeLifecycleCallback(ActivityLifecycleCallback callback) {
        Checks.checkNotNull(callback);
        synchronized (this.callbacks) {
            Iterator<WeakReference<ActivityLifecycleCallback>> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ActivityLifecycleCallback activityLifecycleCallback = it.next().get();
                if (activityLifecycleCallback == null) {
                    it.remove();
                } else if (activityLifecycleCallback == callback) {
                    it.remove();
                }
            }
        }
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public Stage getLifecycleStageOf(Activity activity) {
        checkMainThread();
        Checks.checkNotNull(activity);
        Iterator<ActivityStatus> it = this.activityStatuses.iterator();
        while (it.hasNext()) {
            ActivityStatus next = it.next();
            Activity activity2 = (Activity) next.activityRef.get();
            if (activity2 == null) {
                it.remove();
            } else if (activity == activity2) {
                return next.lifecycleStage;
            }
        }
        String valueOf = String.valueOf(activity);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
        sb.append("Unknown activity: ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // androidx.test.runner.lifecycle.ActivityLifecycleMonitor
    public Collection<Activity> getActivitiesInStage(Stage stage) {
        checkMainThread();
        Checks.checkNotNull(stage);
        ArrayList arrayList = new ArrayList();
        Iterator<ActivityStatus> it = this.activityStatuses.iterator();
        while (it.hasNext()) {
            ActivityStatus next = it.next();
            Activity activity = (Activity) next.activityRef.get();
            if (activity == null) {
                it.remove();
            } else if (stage == next.lifecycleStage) {
                arrayList.add(activity);
            }
        }
        return arrayList;
    }

    public void signalLifecycleChange(Stage stage, Activity activity) {
        String valueOf = String.valueOf(activity);
        String valueOf2 = String.valueOf(stage);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30 + String.valueOf(valueOf2).length());
        sb.append("Lifecycle status change: ");
        sb.append(valueOf);
        sb.append(" in: ");
        sb.append(valueOf2);
        Log.d(TAG, sb.toString());
        Iterator<ActivityStatus> it = this.activityStatuses.iterator();
        boolean z = true;
        while (it.hasNext()) {
            ActivityStatus next = it.next();
            Activity activity2 = (Activity) next.activityRef.get();
            if (activity2 == null) {
                it.remove();
            } else if (activity == activity2) {
                next.lifecycleStage = stage;
                z = false;
            }
        }
        if (z) {
            this.activityStatuses.add(new ActivityStatus(activity, stage));
        }
        synchronized (this.callbacks) {
            Iterator<WeakReference<ActivityLifecycleCallback>> it2 = this.callbacks.iterator();
            while (it2.hasNext()) {
                ActivityLifecycleCallback activityLifecycleCallback = it2.next().get();
                if (activityLifecycleCallback == null) {
                    it2.remove();
                } else {
                    try {
                        String valueOf3 = String.valueOf(activityLifecycleCallback);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 18);
                        sb2.append("running callback: ");
                        sb2.append(valueOf3);
                        Log.d(TAG, sb2.toString());
                        activityLifecycleCallback.onActivityLifecycleChanged(activity, stage);
                        String valueOf4 = String.valueOf(activityLifecycleCallback);
                        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf4).length() + 20);
                        sb3.append("callback completes: ");
                        sb3.append(valueOf4);
                        Log.d(TAG, sb3.toString());
                    } catch (RuntimeException e) {
                        Log.e(TAG, String.format("Callback threw exception! (callback: %s activity: %s stage: %s)", activityLifecycleCallback, activity, stage), e);
                    }
                }
            }
        }
    }

    private void checkMainThread() {
        if (!this.declawThreadCheck && !Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Querying activity state off main thread is not allowed.");
        }
    }

    /* loaded from: classes.dex */
    private static class ActivityStatus {
        private final WeakReference<Activity> activityRef;
        private Stage lifecycleStage;

        ActivityStatus(Activity activity, Stage stage) {
            this.activityRef = new WeakReference<>((Activity) Checks.checkNotNull(activity));
            this.lifecycleStage = (Stage) Checks.checkNotNull(stage);
        }
    }
}
