package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class LifecycleDispatcher {
    private static AtomicBoolean sInitialized = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Context context) {
        if (sInitialized.getAndSet(true)) {
            return;
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    static class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        DispatcherActivityCallback() {
        }

        @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            ReportFragment.injectIfNeededIn(activity);
        }
    }

    private LifecycleDispatcher() {
    }
}
