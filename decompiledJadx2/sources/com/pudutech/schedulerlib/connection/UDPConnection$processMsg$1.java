package com.pudutech.schedulerlib.connection;

import com.pudutech.schedulerlib.utils.LimitDequeue;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: UDPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.UDPConnection$processMsg$1", m3970f = "UDPConnection.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class UDPConnection$processMsg$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7448p$;
    final /* synthetic */ UDPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UDPConnection$processMsg$1(UDPConnection uDPConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uDPConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UDPConnection$processMsg$1 uDPConnection$processMsg$1 = new UDPConnection$processMsg$1(this.this$0, completion);
        uDPConnection$processMsg$1.f7448p$ = (CoroutineScope) obj;
        return uDPConnection$processMsg$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UDPConnection$processMsg$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LimitDequeue limitDequeue;
        ScheduleMsgReceiveInterface scheduleMsgReceiveInterface;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7448p$;
        while (CoroutineScopeKt.isActive(coroutineScope) && CoroutineScopeKt.isActive(coroutineScope)) {
            limitDequeue = this.this$0.msgQueue;
            byte[] bArr = (byte[]) limitDequeue.takeFirst();
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                break;
            }
            if (bArr.length == 0) {
                break;
            }
            scheduleMsgReceiveInterface = this.this$0.callback;
            if (scheduleMsgReceiveInterface != null) {
                scheduleMsgReceiveInterface.decodeMsg(bArr);
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                break;
            }
        }
        return Unit.INSTANCE;
    }
}
