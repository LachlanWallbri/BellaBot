package com.pudutech.bluetooth;

import com.pudutech.log.ProxyLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, m3961d2 = {"throwOrLog", "", "info", "", "bluetooth_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PdBluetoothManagerKt {
    public static final void throwOrLog(String info) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        ProxyLog proxyLog = ProxyLog.INSTANCE;
        String tag = PdBluetoothManager.INSTANCE.getTAG();
        Intrinsics.checkExpressionValueIsNotNull(tag, "PdBluetoothManager.TAG");
        proxyLog.mo3285i(tag, "throwOrLog > " + info);
    }
}
