package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.charge.ChargeMessageCenter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$doBluetoothVersionCheck$1", m3970f = "GeneralTask.kt", m3971i = {0, 0}, m3972l = {638}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "count"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes6.dex */
public final class GeneralTask$doBluetoothVersionCheck$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6408p$;
    final /* synthetic */ GeneralTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$doBluetoothVersionCheck$1(GeneralTask generalTask, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalTask;
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$doBluetoothVersionCheck$1 generalTask$doBluetoothVersionCheck$1 = new GeneralTask$doBluetoothVersionCheck$1(this.this$0, this.$mac, completion);
        generalTask$doBluetoothVersionCheck$1.f6408p$ = (CoroutineScope) obj;
        return generalTask$doBluetoothVersionCheck$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$doBluetoothVersionCheck$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x004d -> B:5:0x0050). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        GeneralTask$doBluetoothVersionCheck$1 generalTask$doBluetoothVersionCheck$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 0;
            coroutineScope = this.f6408p$;
            generalTask$doBluetoothVersionCheck$1 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            generalTask$doBluetoothVersionCheck$1 = this;
            i++;
            if (!CoroutineScopeKt.isActive(coroutineScope) && BluetoothBleHelper.INSTANCE.getConnectState(generalTask$doBluetoothVersionCheck$1.$mac) == 2 && i < 5) {
                ChargeMessageCenter.INSTANCE.sendVersionReq(generalTask$doBluetoothVersionCheck$1.$mac);
                generalTask$doBluetoothVersionCheck$1.L$0 = coroutineScope;
                generalTask$doBluetoothVersionCheck$1.I$0 = i;
                generalTask$doBluetoothVersionCheck$1.label = 1;
                if (DelayKt.delay(500L, generalTask$doBluetoothVersionCheck$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i++;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    generalTask$doBluetoothVersionCheck$1.this$0.disconnectChargePile();
                    BuildersKt__BuildersKt.runBlocking$default(null, new C53011(null), 1, null);
                    generalTask$doBluetoothVersionCheck$1.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"CanNotReach\",\"level\":\"Error\",\"detail\":\"Version check timeout\"}");
                }
                return Unit.INSTANCE;
            }
            if (CoroutineScopeKt.isActive(coroutineScope) && i >= 5) {
                generalTask$doBluetoothVersionCheck$1.this$0.disconnectChargePile();
                BuildersKt__BuildersKt.runBlocking$default(null, new C53011(null), 1, null);
                generalTask$doBluetoothVersionCheck$1.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"CanNotReach\",\"level\":\"Error\",\"detail\":\"Version check timeout\"}");
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$doBluetoothVersionCheck$1$1", m3970f = "GeneralTask.kt", m3971i = {0}, m3972l = {644}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.movetask.GeneralTask$doBluetoothVersionCheck$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6409p$;

        C53011(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53011 c53011 = new C53011(completion);
            c53011.f6409p$ = (CoroutineScope) obj;
            return c53011;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6409p$;
                MoveAction action = GeneralTask$doBluetoothVersionCheck$1.this.this$0.getAction();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }
}
