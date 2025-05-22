package com.pudutech.mirsdk.mirhardware;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IMarkerCameraState;
import kotlin.Metadata;

/* compiled from: RobotHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/mirsdk/mirhardware/RobotHardware$faceCameraStateListener$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraState$Stub;", "onCameraFrameTick", "", "onOpened", "p0", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotHardware$faceCameraStateListener$1 extends IMarkerCameraState.Stub {
    final /* synthetic */ RobotHardware this$0;

    RobotHardware$faceCameraStateListener$1(RobotHardware robotHardware) {
        this.this$0 = robotHardware;
    }

    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
    public void onOpened(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "faceCameraStateListener camera open " + p0);
        this.this$0.getFaceCameraEvent().emit(Boolean.valueOf(p0));
    }

    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
    public void onCameraFrameTick() {
        this.this$0.getWatchDog().tick("FaceCamera");
    }
}
