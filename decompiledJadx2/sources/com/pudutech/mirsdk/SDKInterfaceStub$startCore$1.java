package com.pudutech.mirsdk;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.map.Atlas;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$startCore$1", m3970f = "SDKService.kt", m3971i = {0, 1}, m3972l = {1127, 1135}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$startCore$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LocateCase $locateCase;
    final /* synthetic */ Ref.BooleanRef $success;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5578p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SDKInterfaceStub$startCore$1(LocateCase locateCase, Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.$locateCase = locateCase;
        this.$success = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$startCore$1 sDKInterfaceStub$startCore$1 = new SDKInterfaceStub$startCore$1(this.$locateCase, this.$success, completion);
        sDKInterfaceStub$startCore$1.f5578p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$startCore$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$startCore$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0336  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        RobotHardware robotHardware;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        RobotHardware robotHardware2;
        CameraInterface camera;
        String str6;
        MachineInfo machineInfo;
        AIDLConnection aIDLConnection;
        Context context;
        Object connect$default;
        AIDLConnection aIDLConnection2;
        String str7;
        String str8;
        AIDLConnection aIDLConnection3;
        String str9;
        String str10;
        Object initCore;
        String gitHash;
        String str11;
        RobotHardware robotHardware3;
        ThreadSafeListener<ISDKListener> threadSafeListener;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5578p$;
            SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.CheckMap, StepState.Running, (String) null, 4, (Object) null);
            if (!SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().checkEmptyMap()) {
                SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.EmptyMap, StepState.Success, (String) null, 4, (Object) null);
                SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.CheckMap, StepState.Success, (String) null, 4, (Object) null);
                SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().deleteMappackConfig();
                SDKInterfaceStub.access$setFreeinstall$p(SDKInterfaceStub.INSTANCE, true);
                SDKInterfaceStub.access$setLegalMap$p(SDKInterfaceStub.INSTANCE, false);
            } else if (SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().checkAllTopoMap()) {
                SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.NoToPoMap, StepState.Success, (String) null, 4, (Object) null);
                SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.CheckMap, StepState.Success, (String) null, 4, (Object) null);
                SDKInterfaceStub.access$setFreeinstall$p(SDKInterfaceStub.INSTANCE, true);
                SDKInterfaceStub.access$setLegalMap$p(SDKInterfaceStub.INSTANCE, false);
            } else {
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                robotHardware = SDKInterfaceStub.robotHardware;
                HardwareInterface hardwareInterface = robotHardware.getInterface();
                ProductMachineType productMachineType = (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null) ? null : machineInfo.getProductMachineType();
                Atlas atlas = SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas();
                LocateCase locateCase = this.$locateCase;
                if (productMachineType == null) {
                    Intrinsics.throwNpe();
                }
                if (!atlas.extractMaps(locateCase, productMachineType)) {
                    SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
                    str6 = SDKInterfaceStub.TAG;
                    Pdlog.m3274e(str6, "extract map fail");
                    SDKInterfaceStub.INSTANCE.initStepNotify(InitStep.CheckMap, StepState.Fail, "{\"error\":\"" + SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getErrorReason() + "\"}");
                    SDKInterfaceStub.access$setLegalMap$p(SDKInterfaceStub.INSTANCE, false);
                } else {
                    Integer checkDefaultMap = SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().checkDefaultMap();
                    if (checkDefaultMap != null) {
                        SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
                        str5 = SDKInterfaceStub.TAG;
                        Pdlog.m3275i(str5, "switch camera exposure: " + checkDefaultMap);
                        SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
                        robotHardware2 = SDKInterfaceStub.robotHardware;
                        HardwareInterface hardwareInterface2 = robotHardware2.getInterface();
                        if (hardwareInterface2 != null && (camera = hardwareInterface2.getCamera()) != null) {
                            camera.setCameraExposure(checkDefaultMap.intValue());
                        }
                    }
                    SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
                    str = SDKInterfaceStub.TAG;
                    Pdlog.m3273d(str, "openCore checkDefaultMap result=" + checkDefaultMap);
                    boolean init = SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).init();
                    if (!init) {
                        SDKInterfaceStub.access$initStepNotify(SDKInterfaceStub.INSTANCE, InitStep.CheckMap, init, "{\"error\":\"" + SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getErrorReason() + "\"}");
                        SDKInterfaceStub sDKInterfaceStub6 = SDKInterfaceStub.INSTANCE;
                        str4 = SDKInterfaceStub.TAG;
                        Pdlog.m3274e(str4, "map parse fail");
                    } else {
                        SDKInterfaceStub.access$setFreeinstall$p(SDKInterfaceStub.INSTANCE, false);
                        SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.CheckMap, init, (String) null, 4, (Object) null);
                        SDKInterfaceStub sDKInterfaceStub7 = SDKInterfaceStub.INSTANCE;
                        str2 = SDKInterfaceStub.TAG;
                        Pdlog.m3273d(str2, "openCore wihtoutLocFlag result= " + SDKInterfaceStub.access$getFreeinstall$p(SDKInterfaceStub.INSTANCE) + " and mapInitResult is " + init);
                    }
                    this.$success.element = init;
                    SDKInterfaceStub sDKInterfaceStub8 = SDKInterfaceStub.INSTANCE;
                    str3 = SDKInterfaceStub.TAG;
                    Pdlog.m3276v(str3, "coreService.sssss " + this.$success.element + " and " + SDKInterfaceStub.access$getFreeinstall$p(SDKInterfaceStub.INSTANCE));
                }
            }
            SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.ConnectCoreService, StepState.Running, (String) null, 4, (Object) null);
            SDKInterfaceStub sDKInterfaceStub9 = SDKInterfaceStub.INSTANCE;
            aIDLConnection = SDKInterfaceStub.coreService;
            SDKInterfaceStub sDKInterfaceStub10 = SDKInterfaceStub.INSTANCE;
            context = SDKInterfaceStub.context;
            if (context == null) {
                Intrinsics.throwNpe();
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            connect$default = AIDLConnection.connect$default(aIDLConnection, context, null, this, 2, null);
            if (connect$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    initCore = obj;
                    if (((Boolean) initCore).booleanValue()) {
                        SDKInterfaceStub.INSTANCE.reloadLocalization();
                    }
                    if (!SDKInterfaceStub.access$getFreeinstall$p(SDKInterfaceStub.INSTANCE)) {
                        if (this.$success.element) {
                            SDKInterfaceStub.initStepNotify$default(SDKInterfaceStub.INSTANCE, InitStep.Finish, true, (String) null, 4, (Object) null);
                            SDKInterfaceStub sDKInterfaceStub11 = SDKInterfaceStub.INSTANCE;
                            robotHardware3 = SDKInterfaceStub.robotHardware;
                            SDKInterfaceStub sDKInterfaceStub12 = SDKInterfaceStub.INSTANCE;
                            threadSafeListener = SDKInterfaceStub.sdkListeners;
                            robotHardware3.scheduleCommunicationListener(threadSafeListener);
                        } else {
                            SDKInterfaceStub.access$initStepNotify(SDKInterfaceStub.INSTANCE, InitStep.Finish, false, "{\"error\":\"core init fail\"}");
                        }
                    }
                    SDKInterfaceStub.access$setFreeinstall$p(SDKInterfaceStub.INSTANCE, false);
                    SDKInterfaceStub sDKInterfaceStub13 = SDKInterfaceStub.INSTANCE;
                    SDKInterfaceStub.isIniting = false;
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope2;
            connect$default = obj;
        }
        if (!((Boolean) connect$default).booleanValue()) {
            SDKInterfaceStub sDKInterfaceStub14 = SDKInterfaceStub.INSTANCE;
            str11 = SDKInterfaceStub.TAG;
            Pdlog.m3274e(str11, "coreService.connect fail");
            SDKInterfaceStub.INSTANCE.initStepNotify(InitStep.ConnectCoreService, StepState.Fail, "{\"error\":\"coreService.connect fail\"}");
        }
        SDKInterfaceStub sDKInterfaceStub15 = SDKInterfaceStub.INSTANCE;
        aIDLConnection2 = SDKInterfaceStub.coreService;
        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection2.getInterface();
        String str12 = "{\"core\":\"unknown core\"}";
        if (mirCoreInterface == null || (str7 = mirCoreInterface.getGitHash()) == null) {
            str7 = "{\"core\":\"unknown core\"}";
        }
        Pdlog.resetGitHash(str7);
        SDKInterfaceStub sDKInterfaceStub16 = SDKInterfaceStub.INSTANCE;
        str8 = SDKInterfaceStub.TAG;
        Object[] objArr = new Object[1];
        SDKInterfaceStub sDKInterfaceStub17 = SDKInterfaceStub.INSTANCE;
        aIDLConnection3 = SDKInterfaceStub.coreService;
        MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection3.getInterface();
        if (mirCoreInterface2 != null && (gitHash = mirCoreInterface2.getGitHash()) != null) {
            str12 = gitHash;
        }
        objArr[0] = str12;
        Pdlog.m3273d(str8, objArr);
        SDKInterfaceStub sDKInterfaceStub18 = SDKInterfaceStub.INSTANCE;
        str9 = SDKInterfaceStub.TAG;
        Pdlog.m3276v(str9, "coreService.connected");
        SDKInterfaceStub sDKInterfaceStub19 = SDKInterfaceStub.INSTANCE;
        str10 = SDKInterfaceStub.TAG;
        Pdlog.m3273d(str10, "coreService.connect PDmapFullName is " + SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getPdmapFullName());
        SDKInterfaceStub sDKInterfaceStub20 = SDKInterfaceStub.INSTANCE;
        String pdmapFullName = SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getPdmapFullName();
        this.L$0 = coroutineScope;
        this.label = 2;
        initCore = sDKInterfaceStub20.initCore(pdmapFullName, this);
        if (initCore == coroutine_suspended) {
            return coroutine_suspended;
        }
        if (((Boolean) initCore).booleanValue()) {
        }
        if (!SDKInterfaceStub.access$getFreeinstall$p(SDKInterfaceStub.INSTANCE)) {
        }
        SDKInterfaceStub.access$setFreeinstall$p(SDKInterfaceStub.INSTANCE, false);
        SDKInterfaceStub sDKInterfaceStub132 = SDKInterfaceStub.INSTANCE;
        SDKInterfaceStub.isIniting = false;
        return Unit.INSTANCE;
    }
}
