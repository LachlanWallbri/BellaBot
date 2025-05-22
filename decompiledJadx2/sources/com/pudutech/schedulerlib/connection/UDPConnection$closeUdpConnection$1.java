package com.pudutech.schedulerlib.connection;

import com.pudutech.base.Pdlog;
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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: UDPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.UDPConnection$closeUdpConnection$1", m3970f = "UDPConnection.kt", m3971i = {0, 1, 2}, m3972l = {185, 187, 189}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class UDPConnection$closeUdpConnection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7446p$;
    final /* synthetic */ UDPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UDPConnection$closeUdpConnection$1(UDPConnection uDPConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uDPConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UDPConnection$closeUdpConnection$1 uDPConnection$closeUdpConnection$1 = new UDPConnection$closeUdpConnection$1(this.this$0, completion);
        uDPConnection$closeUdpConnection$1.f7446p$ = (CoroutineScope) obj;
        return uDPConnection$closeUdpConnection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UDPConnection$closeUdpConnection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0093  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Job job;
        String str2;
        Job job2;
        String str3;
        Job job3;
        String str4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7446p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start cancel recv job");
            job = this.this$0.recvJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        str4 = this.this$0.TAG;
                        Pdlog.m3273d(str4, "cancelled parse job");
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                str3 = this.this$0.TAG;
                Pdlog.m3273d(str3, "cancelled send job");
                job3 = this.this$0.parseJob;
                if (job3 != null) {
                    this.L$0 = coroutineScope;
                    this.label = 3;
                    if (JobKt.cancelAndJoin(job3, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                str4 = this.this$0.TAG;
                Pdlog.m3273d(str4, "cancelled parse job");
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "cancelled recv job");
        job2 = this.this$0.sendJob;
        if (job2 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        str3 = this.this$0.TAG;
        Pdlog.m3273d(str3, "cancelled send job");
        job3 = this.this$0.parseJob;
        if (job3 != null) {
        }
        str4 = this.this$0.TAG;
        Pdlog.m3273d(str4, "cancelled parse job");
        return Unit.INSTANCE;
    }
}
