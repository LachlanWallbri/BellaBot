package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SDKInterfaceStub;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$reloadUseMap$7", m3970f = "SDKService.kt", m3971i = {0}, m3972l = {1650}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$reloadUseMap$7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.BooleanRef $coreLoadMapResult;
    final /* synthetic */ String $floor;
    final /* synthetic */ String $mapName;
    final /* synthetic */ String $pdmap;
    final /* synthetic */ boolean $switchResult;
    final /* synthetic */ Ref.BooleanRef $waitCoreLoadMap;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5574p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SDKInterfaceStub$reloadUseMap$7(Ref.BooleanRef booleanRef, String str, String str2, Ref.BooleanRef booleanRef2, boolean z, String str3, Continuation continuation) {
        super(2, continuation);
        this.$waitCoreLoadMap = booleanRef;
        this.$floor = str;
        this.$mapName = str2;
        this.$coreLoadMapResult = booleanRef2;
        this.$switchResult = z;
        this.$pdmap = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$reloadUseMap$7 sDKInterfaceStub$reloadUseMap$7 = new SDKInterfaceStub$reloadUseMap$7(this.$waitCoreLoadMap, this.$floor, this.$mapName, this.$coreLoadMapResult, this.$switchResult, this.$pdmap, completion);
        sDKInterfaceStub$reloadUseMap$7.f5574p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$reloadUseMap$7;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$reloadUseMap$7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        AIDLConnection aIDLConnection;
        String str;
        String str2;
        String str3;
        RobotHardware robotHardware;
        ThreadSafeListener threadSafeListener;
        boolean z;
        AIDLConnection aIDLConnection2;
        String str4;
        RobotHardware robotHardware2;
        CameraInterface camera;
        LocalizationInterface localizer;
        RobotStatus robotStatus;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5574p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope) && this.$waitCoreLoadMap.element) {
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        aIDLConnection = SDKInterfaceStub.coreService;
        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
        if (mirCoreInterface != null && (localizer = mirCoreInterface.getLocalizer()) != null) {
            SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
            robotStatus = SDKInterfaceStub.robotStatus;
            localizer.setLocateListener("function", robotStatus.getLocalizationListener());
        }
        SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).addSchedulerListener();
        SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().updateDefaultMap(this.$floor, this.$mapName);
        Integer parseExposureConfig = SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().parseExposureConfig(this.$mapName);
        if (parseExposureConfig != null) {
            SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
            str4 = SDKInterfaceStub.TAG;
            Pdlog.m3275i(str4, "switch camera exposure: " + parseExposureConfig);
            SDKInterfaceStub sDKInterfaceStub4 = SDKInterfaceStub.INSTANCE;
            robotHardware2 = SDKInterfaceStub.robotHardware;
            HardwareInterface hardwareInterface = robotHardware2.getInterface();
            if (hardwareInterface != null && (camera = hardwareInterface.getCamera()) != null) {
                camera.setCameraExposure(parseExposureConfig.intValue());
            }
        } else {
            SDKInterfaceStub sDKInterfaceStub5 = SDKInterfaceStub.INSTANCE;
            str = SDKInterfaceStub.TAG;
            Pdlog.m3273d(str, "switch map cameraExposure is null");
        }
        SDKInterfaceStub sDKInterfaceStub6 = SDKInterfaceStub.INSTANCE;
        str2 = SDKInterfaceStub.TAG;
        Pdlog.m3273d(str2, "switch map moveActionInterface.atlas.locateCase=" + SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getLocateCase());
        if (SDKInterfaceStub.WhenMappings.$EnumSwitchMapping$12[SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getLocateCase().ordinal()] == 1) {
            SDKInterfaceStub sDKInterfaceStub7 = SDKInterfaceStub.INSTANCE;
            aIDLConnection2 = SDKInterfaceStub.coreService;
            MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface();
            if (mirCoreInterface2 != null) {
                mirCoreInterface2.switchAutoExposure(SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getAutoexp());
            }
        }
        SDKInterfaceStub sDKInterfaceStub8 = SDKInterfaceStub.INSTANCE;
        str3 = SDKInterfaceStub.TAG;
        Pdlog.m3273d(str3, "switch map coreLoadMapResult=" + this.$coreLoadMapResult.element + ",switchResult=" + this.$switchResult);
        SDKInterfaceStub sDKInterfaceStub9 = SDKInterfaceStub.INSTANCE;
        robotHardware = SDKInterfaceStub.robotHardware;
        HardwareInterface hardwareInterface2 = robotHardware.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.controlSlamCorePower(SDKInterfaceStub.access$getMoveActionInterface$p(SDKInterfaceStub.INSTANCE).getAtlas().getLocateCase() == LocateCase.Slamware);
        }
        if (this.$coreLoadMapResult.element) {
            SDKInterfaceStub.INSTANCE.reloadLocalization();
        }
        SDKInterfaceStub sDKInterfaceStub10 = SDKInterfaceStub.INSTANCE;
        threadSafeListener = SDKInterfaceStub.sdkListeners;
        threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$reloadUseMap$7.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str5) {
                invoke2(iSDKListener, str5);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISDKListener it, String str5) {
                SwitchMapResult switchMapResult;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                if (SDKInterfaceStub$reloadUseMap$7.this.$switchResult) {
                    switchMapResult = !SDKInterfaceStub$reloadUseMap$7.this.$coreLoadMapResult.element ? SwitchMapResult.MapReloadFailed : SwitchMapResult.Finish;
                } else {
                    switchMapResult = SwitchMapResult.MapExtractFailed;
                }
                it.onSwitchPdmapResult(switchMapResult, SDKInterfaceStub$reloadUseMap$7.this.$pdmap);
            }
        });
        SDKInterfaceStub sDKInterfaceStub11 = SDKInterfaceStub.INSTANCE;
        z = SDKInterfaceStub.reloadingMap;
        synchronized (Boxing.boxBoolean(z)) {
            Pdlog.m3273d("reloadingMap", "reloadingMap is false");
            SDKInterfaceStub sDKInterfaceStub12 = SDKInterfaceStub.INSTANCE;
            SDKInterfaceStub.reloadingMap = false;
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
