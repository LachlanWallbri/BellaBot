package com.pudutech.mirsdk.activity;

import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SDKServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/mirsdk/activity/SDKServiceConnection$listener$1", "Lcom/pudutech/mirsdk/aidl/mapify/LocalizationListener$Stub;", "updateRobotPosition", "", "p0", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKServiceConnection$listener$1 extends LocalizationListener.Stub {
    SDKServiceConnection$listener$1() {
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.LocalizationListener
    public void updateRobotPosition(Vector3d p0) {
        SDKServiceConnection sDKServiceConnection = SDKServiceConnection.INSTANCE;
        if (p0 == null) {
            Intrinsics.throwNpe();
        }
        sDKServiceConnection.setVectorPose(p0);
        while (SDKServiceConnection.INSTANCE.getRecordFlag()) {
            if (SDKServiceConnection.access$calculateDistance(SDKServiceConnection.INSTANCE, new Vector3d(0.0d, 0.0d, 0.0d, 7, null))) {
                SDKServiceConnection.INSTANCE.getVector3dList().add(new Vector3d(0.0d, 0.0d, 0.0d, 7, null));
            }
        }
    }
}
