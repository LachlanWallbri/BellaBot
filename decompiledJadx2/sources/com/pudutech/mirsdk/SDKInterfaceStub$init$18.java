package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.installerserver.InstallerServer;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.config.InstallationModeConfig;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
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
import org.apache.http.HttpStatus;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$init$18", m3970f = "SDKService.kt", m3971i = {0, 1}, m3972l = {HttpStatus.SC_PARTIAL_CONTENT, HttpStatus.SC_MULTI_STATUS}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
final class SDKInterfaceStub$init$18 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LocateCase $locateCase;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5570p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKInterfaceStub$init$18(LocateCase locateCase, Continuation continuation) {
        super(2, continuation);
        this.$locateCase = locateCase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$init$18 sDKInterfaceStub$init$18 = new SDKInterfaceStub$init$18(this.$locateCase, completion);
        sDKInterfaceStub$init$18.f5570p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$init$18;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$init$18) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00b8, code lost:
    
        if (r9 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00ba, code lost:
    
        r0 = com.pudutech.mirsdk.SDKInterfaceStub.INSTANCE;
        r0 = com.pudutech.mirsdk.SDKInterfaceStub.installationModeConfig;
        r9.resetMachineType(r0.getProductMachineType());
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00c7, code lost:
    
        r9 = com.pudutech.mirsdk.SDKInterfaceStub.INSTANCE;
        r9 = com.pudutech.mirsdk.SDKInterfaceStub.robotHardware;
        r9.notifyInitDone();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0188, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0182, code lost:
    
        if (r9 == null) goto L35;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        InstallationModeConfig installationModeConfig;
        RobotHardware robotHardware;
        ProductMachineType productMachineType;
        InstallationModeConfig installationModeConfig2;
        RobotHardware robotHardware2;
        InstallationModeConfig installationModeConfig3;
        MachineInfo machineInfo;
        String str2;
        String str3;
        InstallationModeConfig installationModeConfig4;
        RobotHardware robotHardware3;
        ProductMachineType productMachineType2;
        InstallationModeConfig installationModeConfig5;
        InstallerServer installerService;
        MachineInfo machineInfo2;
        CoroutineScope coroutineScope;
        String str4;
        String str5;
        InstallationModeConfig installationModeConfig6;
        RobotHardware robotHardware4;
        HardwareInterface hardwareInterface;
        InstallationModeConfig installationModeConfig7;
        MachineInfo machineInfo3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5570p$;
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = sDKInterfaceStub.openHardware(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else if (i == 2) {
                ResultKt.throwOnFailure(obj);
                SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                SDKInterfaceStub.isIniting = false;
                SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
                str5 = SDKInterfaceStub.TAG;
                Pdlog.m3273d(str5, "finish open launch, finally");
                SDKInterfaceStub.INSTANCE.magneticConfigJson();
                SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
                installationModeConfig6 = SDKInterfaceStub.installationModeConfig;
                SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
                robotHardware4 = SDKInterfaceStub.robotHardware;
                hardwareInterface = robotHardware4.getInterface();
                if (hardwareInterface != null || (machineInfo3 = hardwareInterface.getMachineInfo()) == null || (r0 = machineInfo3.getProductMachineType()) == null) {
                    ProductMachineType productMachineType3 = new ProductMachineType(MachineModel.Hls, 0, 0);
                }
                installationModeConfig6.setProductMachineType(productMachineType3);
                SDKInterfaceStub sDKInterfaceStub6 = SDKInterfaceStub.INSTANCE;
                installationModeConfig7 = SDKInterfaceStub.installationModeConfig;
                installerService = installationModeConfig7.getInstallerService();
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (((Boolean) obj).booleanValue()) {
                SDKInterfaceStub sDKInterfaceStub7 = SDKInterfaceStub.INSTANCE;
                LocateCase locateCase = this.$locateCase;
                this.L$0 = coroutineScope;
                this.label = 2;
                if (sDKInterfaceStub7.openCore(locateCase, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                SDKInterfaceStub sDKInterfaceStub8 = SDKInterfaceStub.INSTANCE;
                str4 = SDKInterfaceStub.TAG;
                Pdlog.m3277w(str4, "hardware open fail");
                SDKInterfaceStub.INSTANCE.initStepNotify(InitStep.Finish, StepState.Fail, "{\"error\":\"hardware open fail\"}");
            }
            SDKInterfaceStub sDKInterfaceStub22 = SDKInterfaceStub.INSTANCE;
            SDKInterfaceStub.isIniting = false;
            SDKInterfaceStub sDKInterfaceStub32 = SDKInterfaceStub.INSTANCE;
            str5 = SDKInterfaceStub.TAG;
            Pdlog.m3273d(str5, "finish open launch, finally");
            SDKInterfaceStub.INSTANCE.magneticConfigJson();
            SDKInterfaceStub sDKInterfaceStub42 = SDKInterfaceStub.INSTANCE;
            installationModeConfig6 = SDKInterfaceStub.installationModeConfig;
            SDKInterfaceStub sDKInterfaceStub52 = SDKInterfaceStub.INSTANCE;
            robotHardware4 = SDKInterfaceStub.robotHardware;
            hardwareInterface = robotHardware4.getInterface();
            if (hardwareInterface != null) {
            }
            ProductMachineType productMachineType32 = new ProductMachineType(MachineModel.Hls, 0, 0);
            installationModeConfig6.setProductMachineType(productMachineType32);
            SDKInterfaceStub sDKInterfaceStub62 = SDKInterfaceStub.INSTANCE;
            installationModeConfig7 = SDKInterfaceStub.installationModeConfig;
            installerService = installationModeConfig7.getInstallerService();
        } catch (Throwable th) {
            try {
                SDKInterfaceStub sDKInterfaceStub9 = SDKInterfaceStub.INSTANCE;
                str2 = SDKInterfaceStub.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("init exception: ");
                StackTraceElement[] stackTrace = th.getStackTrace();
                if (stackTrace == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                objArr[0] = sb.toString();
                Pdlog.m3274e(str2, objArr);
                SDKInterfaceStub sDKInterfaceStub10 = SDKInterfaceStub.INSTANCE;
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
                sDKInterfaceStub10.initStepNotify(initStep, stepState, sb2.toString());
                SDKInterfaceStub sDKInterfaceStub11 = SDKInterfaceStub.INSTANCE;
                SDKInterfaceStub.isIniting = false;
                SDKInterfaceStub sDKInterfaceStub12 = SDKInterfaceStub.INSTANCE;
                str3 = SDKInterfaceStub.TAG;
                Pdlog.m3273d(str3, "finish open launch, finally");
                SDKInterfaceStub.INSTANCE.magneticConfigJson();
                SDKInterfaceStub sDKInterfaceStub13 = SDKInterfaceStub.INSTANCE;
                installationModeConfig4 = SDKInterfaceStub.installationModeConfig;
                SDKInterfaceStub sDKInterfaceStub14 = SDKInterfaceStub.INSTANCE;
                robotHardware3 = SDKInterfaceStub.robotHardware;
                HardwareInterface hardwareInterface2 = robotHardware3.getInterface();
                if (hardwareInterface2 == null || (machineInfo2 = hardwareInterface2.getMachineInfo()) == null || (productMachineType2 = machineInfo2.getProductMachineType()) == null) {
                    productMachineType2 = new ProductMachineType(MachineModel.Hls, 0, 0);
                }
                installationModeConfig4.setProductMachineType(productMachineType2);
                SDKInterfaceStub sDKInterfaceStub15 = SDKInterfaceStub.INSTANCE;
                installationModeConfig5 = SDKInterfaceStub.installationModeConfig;
                installerService = installationModeConfig5.getInstallerService();
            } catch (Throwable th2) {
                SDKInterfaceStub sDKInterfaceStub16 = SDKInterfaceStub.INSTANCE;
                SDKInterfaceStub.isIniting = false;
                SDKInterfaceStub sDKInterfaceStub17 = SDKInterfaceStub.INSTANCE;
                str = SDKInterfaceStub.TAG;
                Pdlog.m3273d(str, "finish open launch, finally");
                SDKInterfaceStub.INSTANCE.magneticConfigJson();
                SDKInterfaceStub sDKInterfaceStub18 = SDKInterfaceStub.INSTANCE;
                installationModeConfig = SDKInterfaceStub.installationModeConfig;
                SDKInterfaceStub sDKInterfaceStub19 = SDKInterfaceStub.INSTANCE;
                robotHardware = SDKInterfaceStub.robotHardware;
                HardwareInterface hardwareInterface3 = robotHardware.getInterface();
                if (hardwareInterface3 == null || (machineInfo = hardwareInterface3.getMachineInfo()) == null || (productMachineType = machineInfo.getProductMachineType()) == null) {
                    productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
                }
                installationModeConfig.setProductMachineType(productMachineType);
                SDKInterfaceStub sDKInterfaceStub20 = SDKInterfaceStub.INSTANCE;
                installationModeConfig2 = SDKInterfaceStub.installationModeConfig;
                InstallerServer installerService2 = installationModeConfig2.getInstallerService();
                if (installerService2 != null) {
                    SDKInterfaceStub sDKInterfaceStub21 = SDKInterfaceStub.INSTANCE;
                    installationModeConfig3 = SDKInterfaceStub.installationModeConfig;
                    installerService2.resetMachineType(installationModeConfig3.getProductMachineType());
                }
                SDKInterfaceStub sDKInterfaceStub23 = SDKInterfaceStub.INSTANCE;
                robotHardware2 = SDKInterfaceStub.robotHardware;
                robotHardware2.notifyInitDone();
                throw th2;
            }
        }
    }
}
