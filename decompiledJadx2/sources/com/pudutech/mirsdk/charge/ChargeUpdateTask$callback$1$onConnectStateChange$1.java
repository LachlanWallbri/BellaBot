package com.pudutech.mirsdk.charge;

import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.charge.ChargeUpdateTask;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChargeUpdateTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeUpdateTask$callback$1$onConnectStateChange$1", m3970f = "ChargeUpdateTask.kt", m3971i = {0}, m3972l = {56}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class ChargeUpdateTask$callback$1$onConnectStateChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5742p$;
    final /* synthetic */ ChargeUpdateTask$callback$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeUpdateTask$callback$1$onConnectStateChange$1(ChargeUpdateTask$callback$1 chargeUpdateTask$callback$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chargeUpdateTask$callback$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeUpdateTask$callback$1$onConnectStateChange$1 chargeUpdateTask$callback$1$onConnectStateChange$1 = new ChargeUpdateTask$callback$1$onConnectStateChange$1(this.this$0, completion);
        chargeUpdateTask$callback$1$onConnectStateChange$1.f5742p$ = (CoroutineScope) obj;
        return chargeUpdateTask$callback$1$onConnectStateChange$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeUpdateTask$callback$1$onConnectStateChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        int i3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5742p$;
            i = this.this$0.this$0.curUpdateStep;
            if (i != -1) {
                ChargeUpdateTask chargeUpdateTask = this.this$0.this$0;
                i2 = chargeUpdateTask.reTryConnectCount;
                chargeUpdateTask.reTryConnectCount = i2 + 1;
                i3 = this.this$0.this$0.reTryConnectCount;
                if (i3 >= 3) {
                    this.this$0.this$0.resetIdle();
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new C48871(null), 2, null);
                } else {
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        }
        if (i4 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BluetoothBleHelper.INSTANCE.connectDevice(this.this$0.getMac());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ChargeUpdateTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeUpdateTask$callback$1$onConnectStateChange$1$1", m3970f = "ChargeUpdateTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.charge.ChargeUpdateTask$callback$1$onConnectStateChange$1$1 */
    /* loaded from: classes5.dex */
    public static final class C48871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5743p$;

        C48871(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48871 c48871 = new C48871(completion);
            c48871.f5743p$ = (CoroutineScope) obj;
            return c48871;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5743p$;
            ChargeUpdateTask.onChargePileUpdateListener chargePileUpdateListener = ChargeUpdateTask$callback$1$onConnectStateChange$1.this.this$0.this$0.getChargePileUpdateListener();
            if (chargePileUpdateListener != null) {
                chargePileUpdateListener.onError(ChargeUpdateTask$callback$1$onConnectStateChange$1.this.this$0.getMac(), 1, "connect device fail");
            }
            return Unit.INSTANCE;
        }
    }
}
