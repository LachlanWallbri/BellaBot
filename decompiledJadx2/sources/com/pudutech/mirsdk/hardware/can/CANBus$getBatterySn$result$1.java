package com.pudutech.mirsdk.hardware.can;

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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$getBatterySn$result$1", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {1026}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class CANBus$getBatterySn$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Ref.ObjectRef $batterySn;
    final /* synthetic */ Ref.IntRef $len;
    final /* synthetic */ List $snByteList;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6024p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$getBatterySn$result$1(List list, Ref.IntRef intRef, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.$snByteList = list;
        this.$len = intRef;
        this.$batterySn = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$getBatterySn$result$1 cANBus$getBatterySn$result$1 = new CANBus$getBatterySn$result$1(this.$snByteList, this.$len, this.$batterySn, completion);
        cANBus$getBatterySn$result$1.f6024p$ = (CoroutineScope) obj;
        return cANBus$getBatterySn$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((CANBus$getBatterySn$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6024p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (this.$snByteList.size() <= this.$len.element) {
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(20L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return (String) this.$batterySn.element;
    }
}
