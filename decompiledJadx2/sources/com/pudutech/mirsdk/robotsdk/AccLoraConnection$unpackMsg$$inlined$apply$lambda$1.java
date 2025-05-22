package com.pudutech.mirsdk.robotsdk;

import com.pudutech.mirsdk.robotsdk.AccLoraConnection;
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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccLoraConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdk/robotsdk/AccLoraConnection$unpackMsg$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccLoraConnection$unpackMsg$$inlined$apply$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $accId;
    final /* synthetic */ byte $cmd;
    final /* synthetic */ byte[] $data$inlined;
    final /* synthetic */ String $robotId;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6427p$;
    final /* synthetic */ AccLoraConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccLoraConnection$unpackMsg$$inlined$apply$lambda$1(String str, String str2, byte b, Continuation continuation, AccLoraConnection accLoraConnection, byte[] bArr) {
        super(2, continuation);
        this.$accId = str;
        this.$robotId = str2;
        this.$cmd = b;
        this.this$0 = accLoraConnection;
        this.$data$inlined = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AccLoraConnection$unpackMsg$$inlined$apply$lambda$1 accLoraConnection$unpackMsg$$inlined$apply$lambda$1 = new AccLoraConnection$unpackMsg$$inlined$apply$lambda$1(this.$accId, this.$robotId, this.$cmd, completion, this.this$0, this.$data$inlined);
        accLoraConnection$unpackMsg$$inlined$apply$lambda$1.f6427p$ = (CoroutineScope) obj;
        return accLoraConnection$unpackMsg$$inlined$apply$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AccLoraConnection$unpackMsg$$inlined$apply$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AccLoraConnection.AccLoraListener accLoraListener;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6427p$;
        accLoraListener = this.this$0.accListener;
        if (accLoraListener != null) {
            accLoraListener.onStatus(this.$accId, this.$robotId, this.$cmd);
        }
        return Unit.INSTANCE;
    }
}
