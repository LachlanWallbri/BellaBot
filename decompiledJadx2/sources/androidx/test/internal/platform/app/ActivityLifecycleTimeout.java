package androidx.test.internal.platform.app;

import androidx.test.internal.platform.util.InstrumentationParameterUtil;

/* loaded from: classes.dex */
public class ActivityLifecycleTimeout {
    private static final String ACTIVITY_LIFECYCLE_CHANGE_TIMEOUT_MILLIS_KEY = "activityLifecycleChangeTimeoutMillis";
    private static final int DEFAULT_ACTIVITY_LIFECYCLE_CHANGE_TIMEOUT_MILLIS = 45000;

    public static long getMillis() {
        return InstrumentationParameterUtil.getTimeoutMillis(ACTIVITY_LIFECYCLE_CHANGE_TIMEOUT_MILLIS_KEY, 45000L);
    }
}
