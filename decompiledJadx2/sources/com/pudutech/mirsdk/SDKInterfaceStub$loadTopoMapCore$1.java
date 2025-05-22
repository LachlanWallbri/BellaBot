package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SDKInterfaceStub;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$loadTopoMapCore$1", "Lcom/pudutech/mirsdk/mircore/InitServiceListener$Stub;", "initCoreServiceState", "", "p0", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitStep;", "p1", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "p2", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKInterfaceStub$loadTopoMapCore$1 extends InitServiceListener.Stub {
    final /* synthetic */ Ref.ObjectRef $result;
    final /* synthetic */ Ref.ObjectRef $state;
    final /* synthetic */ Ref.ObjectRef $step;

    SDKInterfaceStub$loadTopoMapCore$1(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3) {
        this.$state = objectRef;
        this.$result = objectRef2;
        this.$step = objectRef3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [T, java.lang.String] */
    @Override // com.pudutech.mirsdk.mircore.InitServiceListener
    public void initCoreServiceState(CoreInitStep p0, CoreInitState p1, String p2) {
        ThreadSafeListener threadSafeListener;
        ThreadSafeListener threadSafeListener2;
        RobotHardware robotHardware;
        ThreadSafeListener<ISDKListener> threadSafeListener3;
        RobotHardware robotHardware2;
        RobotHardware robotHardware3;
        CameraInterface camera;
        RobotHardware robotHardware4;
        RobotHardware robotHardware5;
        CameraInterface camera2;
        if (p0 != 0 && SDKInterfaceStub.WhenMappings.$EnumSwitchMapping$4[p0.ordinal()] == 1) {
            if (p1 == CoreInitState.Success) {
                this.$state.element = p1;
            }
            SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
            threadSafeListener2 = SDKInterfaceStub.loadCoreListener;
            threadSafeListener2.notify(new Function2<LoadCoreListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$loadTopoMapCore$1$initCoreServiceState$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LoadCoreListener loadCoreListener, String str) {
                    invoke2(loadCoreListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoadCoreListener it, String name) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    it.coreInitStepResult(CoreStepType.LoadTopoMap, CoreInitState.Success);
                }
            });
            SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
            robotHardware = SDKInterfaceStub.robotHardware;
            SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
            threadSafeListener3 = SDKInterfaceStub.sdkListeners;
            robotHardware.scheduleCommunicationListener(threadSafeListener3);
            if (CamerConfigHelper.cameraType == CameraType.MARKER_CAMERA) {
                SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
                robotHardware4 = SDKInterfaceStub.robotHardware;
                robotHardware4.switchWatchCamera("FaceCamera");
                SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
                robotHardware5 = SDKInterfaceStub.robotHardware;
                HardwareInterface hardwareInterface = robotHardware5.getInterface();
                if (hardwareInterface != null && (camera2 = hardwareInterface.getCamera()) != null) {
                    camera2.openMarkerCamera();
                }
            } else {
                SDKInterfaceStub sDKInterfaceStub6 = SDKInterfaceStub.INSTANCE;
                robotHardware2 = SDKInterfaceStub.robotHardware;
                HardwareInterface hardwareInterface2 = robotHardware2.getInterface();
                if (hardwareInterface2 != null && (camera = hardwareInterface2.getCamera()) != null) {
                    camera.closeMonocularCamera();
                }
                SDKInterfaceStub sDKInterfaceStub7 = SDKInterfaceStub.INSTANCE;
                robotHardware3 = SDKInterfaceStub.robotHardware;
                robotHardware3.removeAllCamera();
            }
            SDKInterfaceStub sDKInterfaceStub8 = SDKInterfaceStub.INSTANCE;
            SDKInterfaceStub.isIniting = false;
            Pdlog.m3276v("newAlgroim", "coreService.connecting  3 ");
        } else if (p1 == CoreInitState.Fail) {
            this.$state.element = p1;
            Ref.ObjectRef objectRef = this.$result;
            objectRef.element = ((String) objectRef.element) + p2;
            SDKInterfaceStub sDKInterfaceStub9 = SDKInterfaceStub.INSTANCE;
            threadSafeListener = SDKInterfaceStub.loadCoreListener;
            threadSafeListener.notify(new Function2<LoadCoreListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$loadTopoMapCore$1$initCoreServiceState$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LoadCoreListener loadCoreListener, String str) {
                    invoke2(loadCoreListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoadCoreListener it, String name) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    it.coreInitStepResult(CoreStepType.LoadTopoMap, CoreInitState.Fail);
                }
            });
            Pdlog.m3276v("newAlgroim", "coreService.connecting  3 fail");
        }
        this.$step.element = p0;
    }
}
