package com.pudutech.leaselib;

import android.app.Application;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LeaseSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u001a\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001eJ\u0006\u0010&\u001a\u00020\u0010J\u000e\u0010'\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001eJ\u0006\u0010)\u001a\u00020$R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u001e\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u001e0\u001dj\b\u0012\u0004\u0012\u00020\u001e`\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010\u000e¨\u0006*"}, m3961d2 = {"Lcom/pudutech/leaselib/LeaseSdk;", "", "()V", "context", "Landroid/app/Application;", "getContext", "()Landroid/app/Application;", "setContext", "(Landroid/app/Application;)V", "hardVersion", "", "getHardVersion", "()Ljava/lang/String;", "setHardVersion", "(Ljava/lang/String;)V", "isTestServer", "", "()Z", "setTestServer", "(Z)V", "leaseCheckTask", "Lcom/pudutech/leaselib/LeaseCheckTask;", "mac", "getMac", "setMac", "onLeaseStatusChangeListener", "com/pudutech/leaselib/LeaseSdk$onLeaseStatusChangeListener$1", "Lcom/pudutech/leaselib/LeaseSdk$onLeaseStatusChangeListener$1;", "onLeaseStatusChangeListeners", "Ljava/util/ArrayList;", "Lcom/pudutech/leaselib/OnLeaseStatusChangeListener;", "Lkotlin/collections/ArrayList;", "softVersion", "getSoftVersion", "setSoftVersion", "addOnLeaseStatusChangeListener", "", "listener", "checkIsOverdue", "init", "removeOnLeaseStatusChangeListener", "startCheckLease", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LeaseSdk {
    public static Application context;
    private static boolean isTestServer;
    public static final LeaseSdk INSTANCE = new LeaseSdk();
    private static String hardVersion = "";
    private static String softVersion = "";
    private static String mac = "";
    private static final LeaseCheckTask leaseCheckTask = new LeaseCheckTask();
    private static final ArrayList<OnLeaseStatusChangeListener> onLeaseStatusChangeListeners = new ArrayList<>();
    private static final LeaseSdk$onLeaseStatusChangeListener$1 onLeaseStatusChangeListener = new OnLeaseStatusChangeListener() { // from class: com.pudutech.leaselib.LeaseSdk$onLeaseStatusChangeListener$1
        @Override // com.pudutech.leaselib.OnLeaseStatusChangeListener
        public void onStatusChange(UseType useType, long endTime, long leftTime) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(useType, "useType");
            LeaseSdk leaseSdk = LeaseSdk.INSTANCE;
            arrayList = LeaseSdk.onLeaseStatusChangeListeners;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((OnLeaseStatusChangeListener) it.next()).onStatusChange(useType, endTime, leftTime);
            }
        }
    };

    private LeaseSdk() {
    }

    public final String getHardVersion() {
        return hardVersion;
    }

    public final void setHardVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        hardVersion = str;
    }

    public final String getSoftVersion() {
        return softVersion;
    }

    public final void setSoftVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        softVersion = str;
    }

    public final String getMac() {
        return mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        mac = str;
    }

    public final boolean isTestServer() {
        return isTestServer;
    }

    public final void setTestServer(boolean z) {
        isTestServer = z;
    }

    public final Application getContext() {
        Application application = context;
        if (application == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return application;
    }

    public final void setContext(Application application) {
        Intrinsics.checkParameterIsNotNull(application, "<set-?>");
        context = application;
    }

    public final void init(Application context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
    }

    public final void startCheckLease() {
        leaseCheckTask.startCheck(onLeaseStatusChangeListener);
    }

    public final boolean checkIsOverdue() {
        return leaseCheckTask.checkIsOverdue();
    }

    public final void addOnLeaseStatusChangeListener(OnLeaseStatusChangeListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (onLeaseStatusChangeListeners.contains(listener)) {
            return;
        }
        onLeaseStatusChangeListeners.add(listener);
    }

    public final void removeOnLeaseStatusChangeListener(OnLeaseStatusChangeListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        onLeaseStatusChangeListeners.remove(listener);
    }
}
