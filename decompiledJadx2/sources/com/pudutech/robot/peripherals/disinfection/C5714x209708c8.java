package com.pudutech.robot.peripherals.disinfection;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/robot/peripherals/disinfection/DisinfectRobotPeripherals$handleCanDataHead84Protocol$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$handleCanDataHead84Protocol$$inlined$let$lambda$1 */
/* loaded from: classes6.dex */
final class C5714x209708c8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $it;
    final /* synthetic */ boolean $switch$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7313p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5714x209708c8(Function1 function1, Continuation continuation, boolean z) {
        super(2, continuation);
        this.$it = function1;
        this.$switch$inlined = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5714x209708c8 c5714x209708c8 = new C5714x209708c8(this.$it, completion, this.$switch$inlined);
        c5714x209708c8.f7313p$ = (CoroutineScope) obj;
        return c5714x209708c8;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5714x209708c8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7313p$;
        Function1 function1 = this.$it;
        if (function1 != null) {
        }
        return Unit.INSTANCE;
    }
}
