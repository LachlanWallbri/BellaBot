package org.hash.mock.debug;

import android.app.Application;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class BubbleContext {
    public static final Application INSTANCE;

    static {
        Application application;
        Throwable th;
        Application application2;
        try {
            application = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
            if (application == null) {
                try {
                    try {
                        throw new IllegalStateException("Static initialization of Applications must be on main thread.");
                    } catch (Throwable th2) {
                        th = th2;
                        INSTANCE = application;
                        throw th;
                    }
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    try {
                        application2 = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
                    } catch (Exception unused) {
                        e.printStackTrace();
                        application2 = application;
                        INSTANCE = application2;
                        return;
                    }
                    INSTANCE = application2;
                    return;
                }
            }
            INSTANCE = application;
        } catch (Exception e2) {
            e = e2;
            application = null;
        } catch (Throwable th3) {
            application = null;
            th = th3;
            INSTANCE = application;
            throw th;
        }
    }

    public static Application getAppContext() {
        return INSTANCE;
    }
}
