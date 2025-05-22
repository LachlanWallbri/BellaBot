package com.pudutech.mirsdk.mirhardware;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.ILidarState;
import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000e"}, m3961d2 = {"com/pudutech/mirsdk/mirhardware/RobotHardware$lidarListener$1", "Lcom/pudutech/mirsdk/hardware/ILidarState$Stub;", "onError", "", "p0", "", "onFrameTick", "onStartScan", SpeechUtility.TAG_RESOURCE_RESULT, "", "description", "onStopScan", "reason", "Lcom/pudutech/mirsdk/hardware/serialize/LidarStopReason;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotHardware$lidarListener$1 extends ILidarState.Stub {
    final /* synthetic */ RobotHardware this$0;

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onError(String p0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotHardware$lidarListener$1(RobotHardware robotHardware) {
        this.this$0 = robotHardware;
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onFrameTick() {
        this.this$0.getWatchDog().tick("Lidar");
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onStopScan(LidarStopReason reason) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "Lidar onStopScan reason:" + reason);
        this.this$0.getRobotStatus().getLidarOpened().setValue(false);
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onStartScan(boolean result, String description) {
        String str;
        Intrinsics.checkParameterIsNotNull(description, "description");
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "Lidar onStartScan " + result);
        this.this$0.getRobotStatus().getLidarOpened().setValue(Boolean.valueOf(result));
    }
}
