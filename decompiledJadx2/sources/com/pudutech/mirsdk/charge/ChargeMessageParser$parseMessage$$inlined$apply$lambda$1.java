package com.pudutech.mirsdk.charge;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChargeMessageParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdk/charge/ChargeMessageParser$parseMessage$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeMessageParser$parseMessage$$inlined$apply$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $data$inlined;
    final /* synthetic */ byte[] $tmpBuffer;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5741p$;
    final /* synthetic */ ChargeMessageParser this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeMessageParser$parseMessage$$inlined$apply$lambda$1(byte[] bArr, Continuation continuation, ChargeMessageParser chargeMessageParser, byte[] bArr2) {
        super(2, continuation);
        this.$tmpBuffer = bArr;
        this.this$0 = chargeMessageParser;
        this.$data$inlined = bArr2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeMessageParser$parseMessage$$inlined$apply$lambda$1 chargeMessageParser$parseMessage$$inlined$apply$lambda$1 = new ChargeMessageParser$parseMessage$$inlined$apply$lambda$1(this.$tmpBuffer, completion, this.this$0, this.$data$inlined);
        chargeMessageParser$parseMessage$$inlined$apply$lambda$1.f5741p$ = (CoroutineScope) obj;
        return chargeMessageParser$parseMessage$$inlined$apply$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeMessageParser$parseMessage$$inlined$apply$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5741p$;
        this.this$0.parseMessageByCmd(this.$tmpBuffer);
        return Unit.INSTANCE;
    }
}
