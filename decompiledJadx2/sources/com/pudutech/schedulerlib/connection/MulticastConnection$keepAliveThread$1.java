package com.pudutech.schedulerlib.connection;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.connection.MulticastConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$keepAliveThread$1", m3970f = "MulticastConnection.kt", m3971i = {0}, m3972l = {326}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class MulticastConnection$keepAliveThread$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7436p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MulticastConnection$keepAliveThread$1(MulticastConnection multicastConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$keepAliveThread$1 multicastConnection$keepAliveThread$1 = new MulticastConnection$keepAliveThread$1(this.this$0, completion);
        multicastConnection$keepAliveThread$1.f7436p$ = (CoroutineScope) obj;
        return multicastConnection$keepAliveThread$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MulticastConnection$keepAliveThread$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        String str;
        long j;
        long j2;
        long j3;
        int i;
        int i2;
        int i3;
        long j4;
        long j5;
        String str2;
        String str3;
        String str4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7436p$;
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            z = this.this$0.runnable;
            if (!z) {
                break;
            }
            try {
                if (!MulticastConnection.sendMsg$default(this.this$0, MulticastConnection.MSGID.KEEPALIVE.ordinal(), null, 2, null)) {
                    str4 = this.this$0.TAG;
                    Pdlog.m3273d(str4, "send keepalive failed, restart task");
                    MulticastConnection.restart$default(this.this$0, null, 1, null);
                }
            } catch (Exception e) {
                str = this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("keep alive thread exp ");
                sb.append(e.getLocalizedMessage());
                sb.append(": ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str, sb.toString());
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return Unit.INSTANCE;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            j2 = this.this$0.lastRecvTime;
            long j6 = elapsedRealtime - j2;
            j3 = this.this$0.KEEP_ALIVE_TIME;
            if (j6 > j3 * 3) {
                MulticastConnection multicastConnection = this.this$0;
                i = multicastConnection.clearTimes;
                multicastConnection.clearTimes = i + 1;
                i2 = this.this$0.clearTimes;
                if (i2 < 3) {
                    str3 = this.this$0.TAG;
                    Pdlog.m3273d(str3, "clear ip list of udp connection, because no multicast recved from them");
                    MulticastConnection.MultiCallBack callBack = this.this$0.getCallBack();
                    if (callBack != null) {
                        callBack.clearIpList();
                    }
                } else {
                    i3 = this.this$0.clearTimes;
                    if (i3 > 30) {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        j4 = this.this$0.lastRecvTime;
                        long j7 = elapsedRealtime2 - j4;
                        j5 = this.this$0.KEEP_ALIVE_TIME;
                        if (j7 > j5 * 30) {
                            str2 = this.this$0.TAG;
                            Pdlog.m3273d(str2, "rejoin group, because not receive other robots' info, restart task");
                            MulticastConnection.restart$default(this.this$0, null, 1, null);
                            this.this$0.clearTimes = 0;
                            this.this$0.lastRecvTime = 0L;
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
            j = this.this$0.KEEP_ALIVE_TIME;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
