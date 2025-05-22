package com.pudutech.gatecontrollerlib;

import android.content.Intent;
import com.pudutech.base.Pdlog;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: GateController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/gatecontrollerlib/GateController$bluetoothStateReceiver$1$onReceive$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.gatecontrollerlib.GateController$bluetoothStateReceiver$1$onReceive$$inlined$apply$lambda$1 */
/* loaded from: classes5.dex */
final class C4592x4eac467f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $intent$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5304p$;
    final /* synthetic */ GateController$bluetoothStateReceiver$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4592x4eac467f(Continuation continuation, GateController$bluetoothStateReceiver$1 gateController$bluetoothStateReceiver$1, Intent intent) {
        super(2, continuation);
        this.this$0 = gateController$bluetoothStateReceiver$1;
        this.$intent$inlined = intent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4592x4eac467f c4592x4eac467f = new C4592x4eac467f(completion, this.this$0, this.$intent$inlined);
        c4592x4eac467f.f5304p$ = (CoroutineScope) obj;
        return c4592x4eac467f;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4592x4eac467f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Set<String> set;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5304p$;
        set = this.this$0.this$0.waitConnectSet;
        for (String str2 : set) {
            str = this.this$0.this$0.TAG;
            Pdlog.m3273d(str, "to connect device " + str2);
            this.this$0.this$0.realConnectDevice(str2);
        }
        return Unit.INSTANCE;
    }
}
