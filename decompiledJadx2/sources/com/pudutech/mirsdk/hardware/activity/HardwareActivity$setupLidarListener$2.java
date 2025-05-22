package com.pudutech.mirsdk.hardware.activity;

import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.ILidarState;
import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
import com.pudutech.remotemaintenance.config.IoTConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/HardwareActivity$setupLidarListener$2", "Lcom/pudutech/mirsdk/hardware/ILidarState$Stub;", "onError", "", IoTConfig.PARAM_ERROR_MSG, "", "onFrameTick", "onStartScan", "p0", "", "p1", "onStopScan", "Lcom/pudutech/mirsdk/hardware/serialize/LidarStopReason;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareActivity$setupLidarListener$2 extends ILidarState.Stub {
    final /* synthetic */ HardwareActivity this$0;

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onError(String errorMsg) {
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onFrameTick() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareActivity$setupLidarListener$2(HardwareActivity hardwareActivity) {
        this.this$0 = hardwareActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onStopScan(LidarStopReason p0) {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$setupLidarListener$2$onStopScan$1(this, p0, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarState
    public void onStartScan(boolean p0, String p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$setupLidarListener$2$onStartScan$1(this, p0, null), 2, null);
    }
}
