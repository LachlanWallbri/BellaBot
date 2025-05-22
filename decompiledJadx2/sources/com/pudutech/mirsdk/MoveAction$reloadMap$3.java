package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.map.Atlas;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$reloadMap$3", m3970f = "MoveAction.kt", m3971i = {0}, m3972l = {1183}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class MoveAction$reloadMap$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $floor;
    final /* synthetic */ Function0 $result;
    final /* synthetic */ Ref.BooleanRef $waitCoreLoadMap;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5549p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$reloadMap$3(MoveAction moveAction, Ref.BooleanRef booleanRef, String str, Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$waitCoreLoadMap = booleanRef;
        this.$floor = str;
        this.$result = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$reloadMap$3 moveAction$reloadMap$3 = new MoveAction$reloadMap$3(this.this$0, this.$waitCoreLoadMap, this.$floor, this.$result, completion);
        moveAction$reloadMap$3.f5549p$ = (CoroutineScope) obj;
        return moveAction$reloadMap$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$reloadMap$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        AIDLConnection aIDLConnection;
        RobotHardware robotHardware;
        AIDLConnection aIDLConnection2;
        String str;
        RobotHardware robotHardware2;
        CameraInterface camera;
        LocalizationInterface localizer;
        RobotStatus robotStatus;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5549p$;
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
            if (DelayKt.delay(50L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        aIDLConnection = this.this$0.coreService;
        MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
        if (mirCoreInterface != null && (localizer = mirCoreInterface.getLocalizer()) != null) {
            robotStatus = this.this$0.robotStatus;
            localizer.setLocateListener("function", robotStatus.getLocalizationListener());
        }
        this.this$0.addSchedulerListener();
        this.this$0.addPoseListener();
        Atlas.updateDefaultMap$default(this.this$0.getAtlas(), this.$floor, null, 2, null);
        Integer parseExposureConfig = this.this$0.getAtlas().parseExposureConfig(this.this$0.getAtlas().getMapFileName());
        if (parseExposureConfig != null) {
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "switch camera exposure: " + parseExposureConfig);
            robotHardware2 = this.this$0.robotHardware;
            HardwareInterface hardwareInterface = robotHardware2.getInterface();
            if (hardwareInterface != null && (camera = hardwareInterface.getCamera()) != null) {
                camera.setCameraExposure(parseExposureConfig.intValue());
            }
        }
        if (MoveAction.WhenMappings.$EnumSwitchMapping$2[this.this$0.getAtlas().getLocateCase().ordinal()] == 1) {
            aIDLConnection2 = this.this$0.coreService;
            MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface();
            if (mirCoreInterface2 != null) {
                mirCoreInterface2.switchAutoExposure(this.this$0.getAtlas().getAutoexp());
            }
        }
        robotHardware = this.this$0.robotHardware;
        HardwareInterface hardwareInterface2 = robotHardware.getInterface();
        if (hardwareInterface2 != null) {
            hardwareInterface2.controlSlamCorePower(this.this$0.getAtlas().getLocateCase() == LocateCase.Slamware);
        }
        this.$result.invoke();
        return Unit.INSTANCE;
    }
}
