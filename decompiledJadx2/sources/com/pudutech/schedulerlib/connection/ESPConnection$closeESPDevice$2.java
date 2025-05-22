package com.pudutech.schedulerlib.connection;

import com.pudutech.base.Pdlog;
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
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ESPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.ESPConnection$closeESPDevice$2", m3970f = "ESPConnection.kt", m3971i = {0, 1}, m3972l = {73, 76}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes2.dex */
final class ESPConnection$closeESPDevice$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7430p$;
    final /* synthetic */ ESPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESPConnection$closeESPDevice$2(ESPConnection eSPConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eSPConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESPConnection$closeESPDevice$2 eSPConnection$closeESPDevice$2 = new ESPConnection$closeESPDevice$2(this.this$0, completion);
        eSPConnection$closeESPDevice$2.f7430p$ = (CoroutineScope) obj;
        return eSPConnection$closeESPDevice$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ESPConnection$closeESPDevice$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        String str2;
        LimitDequeue limitDequeue;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7430p$;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(50L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    str2 = this.this$0.TAG;
                    Pdlog.m3273d(str2, "put empty bytes to msg queue");
                    limitDequeue = this.this$0.msgQueue;
                    limitDequeue.putFirst(new byte[0]);
                    str3 = this.this$0.TAG;
                    Pdlog.m3273d(str3, "finish close esp connect");
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "start close esp connect");
        ESPScheduleNative.INSTANCE.closeESP();
        this.L$0 = coroutineScope;
        this.label = 2;
        if (DelayKt.delay(50L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "put empty bytes to msg queue");
        limitDequeue = this.this$0.msgQueue;
        limitDequeue.putFirst(new byte[0]);
        str3 = this.this$0.TAG;
        Pdlog.m3273d(str3, "finish close esp connect");
        return Unit.INSTANCE;
    }
}
