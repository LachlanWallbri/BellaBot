package com.pudutech.schedulerlib.connection;

import com.pudutech.schedulerlib.utils.LimitDequeue;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: UDPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.UDPConnection$closeUdpConnection$2", m3970f = "UDPConnection.kt", m3971i = {0, 1, 2}, m3972l = {193, 195, 197}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking", "$this$runBlocking"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class UDPConnection$closeUdpConnection$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7447p$;
    final /* synthetic */ UDPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UDPConnection$closeUdpConnection$2(UDPConnection uDPConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uDPConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UDPConnection$closeUdpConnection$2 uDPConnection$closeUdpConnection$2 = new UDPConnection$closeUdpConnection$2(this.this$0, completion);
        uDPConnection$closeUdpConnection$2.f7447p$ = (CoroutineScope) obj;
        return uDPConnection$closeUdpConnection$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UDPConnection$closeUdpConnection$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0072 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        DatagramSocket datagramSocket;
        LimitDequeue limitDequeue;
        LimitDequeue limitDequeue2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7447p$;
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (DelayKt.delay(20L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        limitDequeue2 = this.this$0.msgQueue;
                        limitDequeue2.putFirst(new byte[0]);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                limitDequeue = this.this$0.sendQueue;
                limitDequeue.putFirst(new byte[0]);
                this.L$0 = coroutineScope;
                this.label = 3;
                if (DelayKt.delay(50L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                limitDequeue2 = this.this$0.msgQueue;
                limitDequeue2.putFirst(new byte[0]);
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        datagramSocket = this.this$0.datagramSocket;
        if (datagramSocket != null) {
            datagramSocket.close();
        }
        this.L$0 = coroutineScope;
        this.label = 2;
        if (DelayKt.delay(50L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        limitDequeue = this.this$0.sendQueue;
        limitDequeue.putFirst(new byte[0]);
        this.L$0 = coroutineScope;
        this.label = 3;
        if (DelayKt.delay(50L, this) == coroutine_suspended) {
        }
        limitDequeue2 = this.this$0.msgQueue;
        limitDequeue2.putFirst(new byte[0]);
        return Unit.INSTANCE;
    }
}
