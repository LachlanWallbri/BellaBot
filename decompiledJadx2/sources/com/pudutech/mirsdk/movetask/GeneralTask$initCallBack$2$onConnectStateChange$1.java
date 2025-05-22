package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.movetask.GeneralTask;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$initCallBack$2$onConnectStateChange$1", m3970f = "GeneralTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class GeneralTask$initCallBack$2$onConnectStateChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6411p$;
    final /* synthetic */ GeneralTask$initCallBack$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$initCallBack$2$onConnectStateChange$1(GeneralTask$initCallBack$2 generalTask$initCallBack$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalTask$initCallBack$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$initCallBack$2$onConnectStateChange$1 generalTask$initCallBack$2$onConnectStateChange$1 = new GeneralTask$initCallBack$2$onConnectStateChange$1(this.this$0, completion);
        generalTask$initCallBack$2$onConnectStateChange$1.f6411p$ = (CoroutineScope) obj;
        return generalTask$initCallBack$2$onConnectStateChange$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$initCallBack$2$onConnectStateChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6411p$;
        if (this.this$0.this$0.isRequestBluetooth && this.this$0.this$0.getBusinessType() == BusinessType.GoCharging) {
            i = this.this$0.this$0.reConnectCount;
            if (i >= 3 && !(GeneralTask.access$getMoveState$p(this.this$0.this$0) instanceof GeneralTask.GoBluetoothChargingState)) {
                Pdlog.m3274e(this.this$0.this$0.TAG, "Bluetooth not connect");
                BuildersKt__BuildersKt.runBlocking$default(null, new C53021(null), 1, null);
                this.this$0.this$0.isRequestBluetooth = false;
                this.this$0.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"CanNotReach\",\"level\":\"Error\",\"detail\":\"Bluetooth Connect Failed\"}");
            } else {
                GeneralTask generalTask = this.this$0.this$0;
                i2 = generalTask.reConnectCount;
                generalTask.reConnectCount = i2 + 1;
                BluetoothBleHelper.INSTANCE.connectDevice(this.this$0.$mac);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$initCallBack$2$onConnectStateChange$1$1", m3970f = "GeneralTask.kt", m3971i = {0}, m3972l = {675}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.mirsdk.movetask.GeneralTask$initCallBack$2$onConnectStateChange$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6412p$;

        C53021(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53021 c53021 = new C53021(completion);
            c53021.f6412p$ = (CoroutineScope) obj;
            return c53021;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6412p$;
                MoveAction action = GeneralTask$initCallBack$2$onConnectStateChange$1.this.this$0.this$0.getAction();
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
