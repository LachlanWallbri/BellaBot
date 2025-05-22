package androidx.test.runner.lifecycle;

import android.app.Activity;
import java.util.Collection;

/* loaded from: classes.dex */
public interface ActivityLifecycleMonitor {
    void addLifecycleCallback(ActivityLifecycleCallback callback);

    Collection<Activity> getActivitiesInStage(Stage stage);

    Stage getLifecycleStageOf(Activity activity);

    void removeLifecycleCallback(ActivityLifecycleCallback callback);
}
