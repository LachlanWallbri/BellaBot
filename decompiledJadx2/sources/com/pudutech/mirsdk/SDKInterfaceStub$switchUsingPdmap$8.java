package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mircore.ReloadMapResultListener;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$switchUsingPdmap$8", "Lcom/pudutech/mirsdk/mircore/ReloadMapResultListener$Stub;", "reloadMapFail", "", "reloadMapSuccess", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKInterfaceStub$switchUsingPdmap$8 extends ReloadMapResultListener.Stub {
    final /* synthetic */ Ref.BooleanRef $coreLoadMapResult;
    final /* synthetic */ String $mapFullName;
    final /* synthetic */ Ref.BooleanRef $waitCoreLoadMap;

    SDKInterfaceStub$switchUsingPdmap$8(Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, String str) {
        this.$waitCoreLoadMap = booleanRef;
        this.$coreLoadMapResult = booleanRef2;
        this.$mapFullName = str;
    }

    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
    public void reloadMapFail() {
        String str;
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        str = SDKInterfaceStub.TAG;
        Pdlog.m3273d(str, "coreInterface reloadMapFail ");
        this.$waitCoreLoadMap.element = false;
        this.$coreLoadMapResult.element = false;
        SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).clearTopoData();
        SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().checkDefaultMap();
    }

    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
    public void reloadMapSuccess() {
        String str;
        RobotHardware robotHardware;
        RobotHardware robotHardware2;
        CameraInterface camera;
        CameraInterface camera2;
        RobotHardware robotHardware3;
        RobotHardware robotHardware4;
        CameraInterface camera3;
        CameraInterface camera4;
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        str = SDKInterfaceStub.TAG;
        Pdlog.m3273d(str, "coreInterface reloadMapSuccess ");
        SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().checkConfigMap(this.$mapFullName);
        if (CamerConfigHelper.cameraType == CameraType.FACE_CAMERA) {
            SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
            robotHardware3 = SDKInterfaceStub.robotHardware;
            HardwareInterface hardwareInterface = robotHardware3.getInterface();
            if (hardwareInterface != null && (camera4 = hardwareInterface.getCamera()) != null) {
                camera4.openMonocularCamera();
            }
            SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
            robotHardware4 = SDKInterfaceStub.robotHardware;
            HardwareInterface hardwareInterface2 = robotHardware4.getInterface();
            if (hardwareInterface2 != null && (camera3 = hardwareInterface2.getCamera()) != null) {
                camera3.closeMarkerCamera();
            }
        } else {
            SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
            robotHardware = SDKInterfaceStub.robotHardware;
            HardwareInterface hardwareInterface3 = robotHardware.getInterface();
            if (hardwareInterface3 != null && (camera2 = hardwareInterface3.getCamera()) != null) {
                camera2.closeMonocularCamera();
            }
            SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
            robotHardware2 = SDKInterfaceStub.robotHardware;
            HardwareInterface hardwareInterface4 = robotHardware2.getInterface();
            if (hardwareInterface4 != null && (camera = hardwareInterface4.getCamera()) != null) {
                camera.openMarkerCamera();
            }
        }
        this.$waitCoreLoadMap.element = false;
        this.$coreLoadMapResult.element = true;
    }
}
