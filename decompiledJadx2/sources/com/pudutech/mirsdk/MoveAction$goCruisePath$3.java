package com.pudutech.mirsdk;

import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$goCruisePath$3", m3970f = "MoveAction.kt", m3971i = {0, 1, 2}, m3972l = {156, 161, 163}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes5.dex */
final class MoveAction$goCruisePath$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MoveTaskMode $mode;
    final /* synthetic */ int $path_id;
    final /* synthetic */ List $stayPoints;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5534p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$goCruisePath$3(MoveAction moveAction, int i, List list, MoveTaskMode moveTaskMode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$path_id = i;
        this.$stayPoints = list;
        this.$mode = moveTaskMode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$goCruisePath$3 moveAction$goCruisePath$3 = new MoveAction$goCruisePath$3(this.this$0, this.$path_id, this.$stayPoints, this.$mode, completion);
        moveAction$goCruisePath$3.f5534p$ = (CoroutineScope) obj;
        return moveAction$goCruisePath$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$goCruisePath$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00f5  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        NavigationInterface navigator;
        CoroutineScope coroutineScope;
        AIDLConnection aIDLConnection3;
        NavigationInterface navigator2;
        AIDLConnection aIDLConnection4;
        NavigationInterface navigator3;
        NavigationInterface navigator4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5534p$;
            if (CoroutineScopeKt.isActive(coroutineScope2)) {
                aIDLConnection = this.this$0.coreService;
                MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                if (mirCoreInterface != null && (navigator4 = mirCoreInterface.getNavigator()) != null) {
                    navigator4.prepareCruiseTask(this.$path_id, this.$stayPoints);
                }
                int i2 = MoveAction.WhenMappings.$EnumSwitchMapping$0[this.$mode.ordinal()];
                if (i2 == 1) {
                    aIDLConnection2 = this.this$0.coreService;
                    MirCoreInterface mirCoreInterface2 = (MirCoreInterface) aIDLConnection2.getInterface();
                    if (mirCoreInterface2 != null && (navigator = mirCoreInterface2.getNavigator()) != null) {
                        navigator.updateSteadyFlag(true);
                    }
                } else if (i2 != 2) {
                    aIDLConnection4 = this.this$0.coreService;
                    MirCoreInterface mirCoreInterface3 = (MirCoreInterface) aIDLConnection4.getInterface();
                    if (mirCoreInterface3 != null && (navigator3 = mirCoreInterface3.getNavigator()) != null) {
                        navigator3.updateSteadyFlag(false);
                    }
                } else {
                    aIDLConnection3 = this.this$0.coreService;
                    MirCoreInterface mirCoreInterface4 = (MirCoreInterface) aIDLConnection3.getInterface();
                    if (mirCoreInterface4 != null && (navigator2 = mirCoreInterface4.getNavigator()) != null) {
                        navigator2.updateSteadyFlag(false);
                    }
                }
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                    return Unit.INSTANCE;
                }
                MoveAction moveAction = this.this$0;
                this.L$0 = coroutineScope2;
                this.label = 1;
                if (MoveAction.stopAndWaitBrake$default(moveAction, false, this, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope2;
            } else {
                return Unit.INSTANCE;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                MoveAction moveAction2 = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 3;
                if (moveAction2.navigation(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            this.this$0.onStateChange(SDKRobotState.Moving, "");
            MoveAction moveAction3 = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 2;
            obj = moveAction3.checkAndClearWheelError(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            if (((Boolean) obj).booleanValue()) {
            }
        } else {
            return Unit.INSTANCE;
        }
    }
}
