package com.pudutech.gatecontrollerlib;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GateControllerListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/GateControllerListener;", "", "onCommandResult", "", "mac", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/gatecontrollerlib/GateControllerMsg;", "onConnectStateChange", "connectState", "", "onData", "data", "", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface GateControllerListener {

    /* compiled from: GateControllerListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static void onCommandResult(GateControllerListener gateControllerListener, String mac, GateControllerMsg msg) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(msg, "msg");
        }

        public static void onConnectStateChange(GateControllerListener gateControllerListener, String mac, int i) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
        }

        public static void onData(GateControllerListener gateControllerListener, String mac, byte[] data) {
            Intrinsics.checkParameterIsNotNull(mac, "mac");
            Intrinsics.checkParameterIsNotNull(data, "data");
        }
    }

    void onCommandResult(String mac, GateControllerMsg msg);

    void onConnectStateChange(String mac, int connectState);

    void onData(String mac, byte[] data);
}
