package com.pudutech.schedulerlib.connection;

import java.net.DatagramSocket;
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
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: UDPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.UDPConnection$startRecvThread$1", m3970f = "UDPConnection.kt", m3971i = {0}, m3972l = {105}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class UDPConnection$startRecvThread$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7449p$;
    final /* synthetic */ UDPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UDPConnection$startRecvThread$1(UDPConnection uDPConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uDPConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UDPConnection$startRecvThread$1 uDPConnection$startRecvThread$1 = new UDPConnection$startRecvThread$1(this.this$0, completion);
        uDPConnection$startRecvThread$1.f7449p$ = (CoroutineScope) obj;
        return uDPConnection$startRecvThread$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UDPConnection$startRecvThread$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        DatagramSocket datagramSocket;
        UDPConnection$UDPScope$1 uDPConnection$UDPScope$1;
        UDPConnection$UDPScope$1 uDPConnection$UDPScope$12;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7449p$;
            this.this$0.processMsg();
            UDPConnection uDPConnection = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (uDPConnection.createSocket(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        datagramSocket = this.this$0.datagramSocket;
        if (datagramSocket != null) {
            UDPConnection uDPConnection2 = this.this$0;
            uDPConnection$UDPScope$1 = uDPConnection2.UDPScope;
            UDPConnection$UDPScope$1 uDPConnection$UDPScope$13 = uDPConnection$UDPScope$1;
            uDPConnection$UDPScope$12 = this.this$0.UDPScope;
            launch$default = BuildersKt__Builders_commonKt.launch$default(uDPConnection$UDPScope$13, uDPConnection$UDPScope$12.getRecvContext(), null, new C5733x9a4bf630(datagramSocket, null, this), 2, null);
            uDPConnection2.recvJob = launch$default;
        }
        return Unit.INSTANCE;
    }
}
