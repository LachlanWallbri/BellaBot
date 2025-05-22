package com.pudutech.mirsdk.activity;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import kotlin.Metadata;

/* compiled from: SDKServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/activity/SDKServiceConnection$listener2$1", "Lcom/pudutech/mirsdk/aidl/mapify/LoadCoreListener$Stub;", "coreInitStepResult", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/CoreStepType;", "p1", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKServiceConnection$listener2$1 extends LoadCoreListener.Stub {
    SDKServiceConnection$listener2$1() {
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LoadCoreListener
    public void coreInitStepResult(CoreStepType p0, CoreInitState p1) {
        if (p0 == CoreStepType.LoadLocateMap && p1 == CoreInitState.Success) {
            Pdlog.m3273d("newAlgroim", "i have click p0 is " + p0 + "  and state is " + p1);
        }
        if (p0 == CoreStepType.LoadTopoMap && p1 == CoreInitState.Success) {
            Pdlog.m3273d("newAlgroim", "i have click p0 is " + p0 + "  and state is " + p1);
        }
        Pdlog.m3273d("newAlgroim", "p0 is " + p0 + "  and state is " + p1);
    }
}
