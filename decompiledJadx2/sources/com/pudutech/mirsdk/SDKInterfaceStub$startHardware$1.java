package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.SDKInterfaceStub;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$startHardware$1", m3970f = "SDKService.kt", m3971i = {0, 1}, m3972l = {668, 677}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$startHardware$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5579p$;

    SDKInterfaceStub$startHardware$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$startHardware$1 sDKInterfaceStub$startHardware$1 = new SDKInterfaceStub$startHardware$1(completion);
        sDKInterfaceStub$startHardware$1.f5579p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$startHardware$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$startHardware$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00f4 A[Catch: all -> 0x0129, TryCatch #0 {all -> 0x0129, blocks: (B:7:0x0013, B:8:0x009e, B:10:0x00a7, B:12:0x00af, B:14:0x00b5, B:16:0x00bb, B:22:0x00c5, B:25:0x00d1, B:27:0x00f4, B:28:0x00f8, B:34:0x0024, B:35:0x003a, B:37:0x0042, B:40:0x0084, B:42:0x002d), top: B:2:0x0009 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        String str2;
        String str3;
        RobotHardware robotHardware;
        MachineModel model;
        String str4;
        RobotHardware robotHardware2;
        AIDLConnection<MirCoreInterface> aIDLConnection;
        AIDLConnection<MirCoreInterface> aIDLConnection2;
        AIDLConnection<MirCoreInterface> aIDLConnection3;
        HardwareInterface hardwareInterface;
        MachineInfo machineInfo;
        ProductMachineType productMachineType;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
            str = SDKInterfaceStub.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("init exception: ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace == null) {
                Intrinsics.throwNpe();
            }
            sb.append(ArraysKt.contentDeepToString(stackTrace));
            objArr[0] = sb.toString();
            Pdlog.m3274e(str, objArr);
            SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
            InitStep initStep = InitStep.Finish;
            StepState stepState = StepState.Fail;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("{\"error\":\"");
            sb2.append(th.getMessage());
            sb2.append(' ');
            StackTraceElement[] stackTrace2 = th.getStackTrace();
            if (stackTrace2 == null) {
                Intrinsics.throwNpe();
            }
            sb2.append(ArraysKt.contentDeepToString(stackTrace2));
            sb2.append("\"}");
            sDKInterfaceStub2.initStepNotify(initStep, stepState, sb2.toString());
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5579p$;
            SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = sDKInterfaceStub3.openHardware(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
                    robotHardware = SDKInterfaceStub.robotHardware;
                    model = (robotHardware != null || (hardwareInterface = robotHardware.getInterface()) == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) ? null : productMachineType.getModel();
                    if (model != null && SDKInterfaceStub.WhenMappings.$EnumSwitchMapping$2[model.ordinal()] == 1) {
                        SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
                        str4 = SDKInterfaceStub.TAG;
                        Pdlog.m3273d(str4, "Peanut init usb control");
                        UsbControlService access$getMUsbController$p = SDKInterfaceStub.access$getMUsbController$p(SDKInterfaceStub.INSTANCE);
                        SDKInterfaceStub sDKInterfaceStub6 = SDKInterfaceStub.INSTANCE;
                        robotHardware2 = SDKInterfaceStub.robotHardware;
                        HardwareInterface hardwareInterface2 = robotHardware2.getInterface();
                        access$getMUsbController$p.init(hardwareInterface2 != null ? hardwareInterface2.getUsbControlInterface() : null);
                        PersonDetectService access$getMPersonDetectService$p = SDKInterfaceStub.access$getMPersonDetectService$p(SDKInterfaceStub.INSTANCE);
                        SDKInterfaceStub sDKInterfaceStub7 = SDKInterfaceStub.INSTANCE;
                        aIDLConnection = SDKInterfaceStub.coreService;
                        access$getMPersonDetectService$p.init(aIDLConnection);
                        PersonPassedCountService access$getPersonPassCountService$p = SDKInterfaceStub.access$getPersonPassCountService$p(SDKInterfaceStub.INSTANCE);
                        SDKInterfaceStub sDKInterfaceStub8 = SDKInterfaceStub.INSTANCE;
                        aIDLConnection2 = SDKInterfaceStub.coreService;
                        access$getPersonPassCountService$p.init(aIDLConnection2);
                        ChildrenDetectService access$getChildrenDetectService$p = SDKInterfaceStub.access$getChildrenDetectService$p(SDKInterfaceStub.INSTANCE);
                        SDKInterfaceStub sDKInterfaceStub9 = SDKInterfaceStub.INSTANCE;
                        aIDLConnection3 = SDKInterfaceStub.coreService;
                        access$getChildrenDetectService$p.init(aIDLConnection3);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            SDKInterfaceStub sDKInterfaceStub10 = SDKInterfaceStub.INSTANCE;
            str3 = SDKInterfaceStub.TAG;
            Pdlog.m3277w(str3, "hardware open success");
            SDKInterfaceStub.access$setTypeMapdata(SDKInterfaceStub.INSTANCE);
            CamerConfigHelper.createCameraConfig();
            CamerConfigHelper.createMappingJson(SDKInterfaceStub.access$getMapdata$p(SDKInterfaceStub.INSTANCE));
            CamerConfigHelper.createLocalizationJson(SDKInterfaceStub.access$getMapdata$p(SDKInterfaceStub.INSTANCE));
            SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.ConnectRobotHardwareService, StepState.Success, (String) null, 4, (Object) null);
            SDKInterfaceStub sDKInterfaceStub11 = SDKInterfaceStub.INSTANCE;
            this.L$0 = coroutineScope;
            this.label = 2;
            if (sDKInterfaceStub11.clearWheelError(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            SDKInterfaceStub sDKInterfaceStub12 = SDKInterfaceStub.INSTANCE;
            str2 = SDKInterfaceStub.TAG;
            Pdlog.m3277w(str2, "hardware open fail");
            SDKInterfaceStub.INSTANCE.initStepNotify(InitStep.Finish, StepState.Fail, "{\"error\":\"hardware open fail\"}");
        }
        SDKInterfaceStub sDKInterfaceStub42 = SDKInterfaceStub.INSTANCE;
        robotHardware = SDKInterfaceStub.robotHardware;
        if (robotHardware != null) {
        }
        if (model != null) {
            SDKInterfaceStub sDKInterfaceStub52 = SDKInterfaceStub.INSTANCE;
            str4 = SDKInterfaceStub.TAG;
            Pdlog.m3273d(str4, "Peanut init usb control");
            UsbControlService access$getMUsbController$p2 = SDKInterfaceStub.access$getMUsbController$p(SDKInterfaceStub.INSTANCE);
            SDKInterfaceStub sDKInterfaceStub62 = SDKInterfaceStub.INSTANCE;
            robotHardware2 = SDKInterfaceStub.robotHardware;
            HardwareInterface hardwareInterface22 = robotHardware2.getInterface();
            access$getMUsbController$p2.init(hardwareInterface22 != null ? hardwareInterface22.getUsbControlInterface() : null);
            PersonDetectService access$getMPersonDetectService$p2 = SDKInterfaceStub.access$getMPersonDetectService$p(SDKInterfaceStub.INSTANCE);
            SDKInterfaceStub sDKInterfaceStub72 = SDKInterfaceStub.INSTANCE;
            aIDLConnection = SDKInterfaceStub.coreService;
            access$getMPersonDetectService$p2.init(aIDLConnection);
            PersonPassedCountService access$getPersonPassCountService$p2 = SDKInterfaceStub.access$getPersonPassCountService$p(SDKInterfaceStub.INSTANCE);
            SDKInterfaceStub sDKInterfaceStub82 = SDKInterfaceStub.INSTANCE;
            aIDLConnection2 = SDKInterfaceStub.coreService;
            access$getPersonPassCountService$p2.init(aIDLConnection2);
            ChildrenDetectService access$getChildrenDetectService$p2 = SDKInterfaceStub.access$getChildrenDetectService$p(SDKInterfaceStub.INSTANCE);
            SDKInterfaceStub sDKInterfaceStub92 = SDKInterfaceStub.INSTANCE;
            aIDLConnection3 = SDKInterfaceStub.coreService;
            access$getChildrenDetectService$p2.init(aIDLConnection3);
        }
        return Unit.INSTANCE;
    }
}
