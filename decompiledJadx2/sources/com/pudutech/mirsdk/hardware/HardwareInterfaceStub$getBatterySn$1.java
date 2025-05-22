package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.can.CANBus;
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

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$getBatterySn$1", m3970f = "HardwareInterfaceStub.kt", m3971i = {0}, m3972l = {1802}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$getBatterySn$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $sn;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5909p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareInterfaceStub$getBatterySn$1(Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.$sn = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$getBatterySn$1 hardwareInterfaceStub$getBatterySn$1 = new HardwareInterfaceStub$getBatterySn$1(this.$sn, completion);
        hardwareInterfaceStub$getBatterySn$1.f5909p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$getBatterySn$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$getBatterySn$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [T, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5909p$;
            Ref.ObjectRef objectRef2 = this.$sn;
            CANBus access$getCanBus$p = HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE);
            this.L$0 = coroutineScope;
            this.L$1 = objectRef2;
            this.label = 1;
            obj = access$getCanBus$p.getBatterySn(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef = (Ref.ObjectRef) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        objectRef.element = (String) obj;
        return Unit.INSTANCE;
    }
}
