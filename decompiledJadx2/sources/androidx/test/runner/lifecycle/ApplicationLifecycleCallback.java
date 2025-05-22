package androidx.test.runner.lifecycle;

import android.app.Application;

/* loaded from: classes.dex */
public interface ApplicationLifecycleCallback {
    void onApplicationLifecycleChanged(Application app, ApplicationStage stage);
}
