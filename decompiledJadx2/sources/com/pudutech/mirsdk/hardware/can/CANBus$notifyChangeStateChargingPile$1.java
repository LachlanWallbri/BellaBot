package com.pudutech.mirsdk.hardware.can;

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
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$notifyChangeStateChargingPile$1", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {466}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class CANBus$notifyChangeStateChargingPile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6034p$;
    final /* synthetic */ CANBus this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$notifyChangeStateChargingPile$1(CANBus cANBus, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$notifyChangeStateChargingPile$1 cANBus$notifyChangeStateChargingPile$1 = new CANBus$notifyChangeStateChargingPile$1(this.this$0, completion);
        cANBus$notifyChangeStateChargingPile$1.f6034p$ = (CoroutineScope) obj;
        return cANBus$notifyChangeStateChargingPile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CANBus$notifyChangeStateChargingPile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: CANBus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$notifyChangeStateChargingPile$1$1", m3970f = "CANBus.kt", m3971i = {0, 1}, m3972l = {469, 471}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", "$this$withTimeoutOrNull"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.mirsdk.hardware.can.CANBus$notifyChangeStateChargingPile$1$1 */
    /* loaded from: classes2.dex */
    public static final class C51461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6035p$;

        C51461(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C51461 c51461 = new C51461(completion);
            c51461.f6035p$ = (CoroutineScope) obj;
            return c51461;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C51461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0067 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0036  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0065 -> B:7:0x0030). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C51461 c51461;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f6035p$;
            } else if (i == 1) {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope2;
                c51461 = this;
                CANBus$notifyChangeStateChargingPile$1.this.this$0.m4425sendGBYM_sE(new byte[]{-92, 0, 17, 0, 0, 0, 0});
                c51461.L$0 = coroutineScope;
                c51461.label = 2;
                if (DelayKt.delay(45L, c51461) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    CANBus$notifyChangeStateChargingPile$1.this.this$0.m4425sendGBYM_sE(new byte[]{-93, 0, 2, 0, 0, 0, 0});
                    c51461.L$0 = coroutineScope;
                    c51461.label = 1;
                    if (DelayKt.delay(5L, c51461) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    CANBus$notifyChangeStateChargingPile$1.this.this$0.m4425sendGBYM_sE(new byte[]{-92, 0, 17, 0, 0, 0, 0});
                    c51461.L$0 = coroutineScope;
                    c51461.label = 2;
                    if (DelayKt.delay(45L, c51461) == coroutine_suspended) {
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope3;
            }
            c51461 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6034p$;
            C51461 c51461 = new C51461(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(300L, c51461, this) == coroutine_suspended) {
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
