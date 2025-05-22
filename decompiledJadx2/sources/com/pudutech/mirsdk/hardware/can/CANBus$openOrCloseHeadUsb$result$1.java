package com.pudutech.mirsdk.hardware.can;

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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$openOrCloseHeadUsb$result$1", m3970f = "CANBus.kt", m3971i = {0, 1}, m3972l = {750, 754}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", "$this$withTimeoutOrNull"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
public final class CANBus$openOrCloseHeadUsb$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ boolean $on;
    final /* synthetic */ Ref.BooleanRef $opened;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6036p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$openOrCloseHeadUsb$result$1(boolean z, Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.$on = z;
        this.$opened = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$openOrCloseHeadUsb$result$1 cANBus$openOrCloseHeadUsb$result$1 = new CANBus$openOrCloseHeadUsb$result$1(this.$on, this.$opened, completion);
        cANBus$openOrCloseHeadUsb$result$1.f6036p$ = (CoroutineScope) obj;
        return cANBus$openOrCloseHeadUsb$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((CANBus$openOrCloseHeadUsb$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f6036p$;
            if (this.$on) {
                coroutineScope2 = coroutineScope3;
                while (!this.$opened.element) {
                }
            } else {
                coroutineScope = coroutineScope3;
                while (this.$opened.element) {
                }
            }
        } else if (i == 1) {
            coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            while (!this.$opened.element) {
                this.L$0 = coroutineScope2;
                this.label = 1;
                if (DelayKt.delay(20L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 2) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            while (this.$opened.element) {
                this.L$0 = coroutineScope;
                this.label = 2;
                if (DelayKt.delay(20L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Boxing.boxBoolean(true);
    }
}
