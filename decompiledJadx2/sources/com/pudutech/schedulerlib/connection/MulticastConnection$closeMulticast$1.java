package com.pudutech.schedulerlib.connection;

import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.connection.MulticastConnection;
import java.net.InetAddress;
import java.net.MulticastSocket;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$closeMulticast$1", m3970f = "MulticastConnection.kt", m3971i = {0, 1, 2}, m3972l = {118, 119, 120}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking", "$this$runBlocking"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MulticastConnection$closeMulticast$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7435p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MulticastConnection$closeMulticast$1(MulticastConnection multicastConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$closeMulticast$1 multicastConnection$closeMulticast$1 = new MulticastConnection$closeMulticast$1(this.this$0, completion);
        multicastConnection$closeMulticast$1.f7435p$ = (CoroutineScope) obj;
        return multicastConnection$closeMulticast$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MulticastConnection$closeMulticast$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x014c, code lost:
    
        if (r9 == null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0087  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Job job;
        Job job2;
        Job job3;
        MulticastSocket multicastSocket;
        MulticastSocket multicastSocket2;
        String str2;
        MulticastSocket multicastSocket3;
        String str3;
        MulticastSocket multicastSocket4;
        String str4;
        String str5;
        MulticastSocket multicastSocket5;
        MulticastSocket multicastSocket6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7435p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "stop link task, keep task and recv task");
            job = this.this$0.linkJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job3 = this.this$0.recvJob;
                    if (job3 != null) {
                        this.L$0 = coroutineScope;
                        this.label = 3;
                        if (JobKt.cancelAndJoin(job3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    multicastSocket = this.this$0.multicastSocket;
                    if (multicastSocket != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    multicastSocket = this.this$0.multicastSocket;
                    if (multicastSocket != null) {
                        multicastSocket2 = this.this$0.multicastSocket;
                        Boolean boxBoolean = multicastSocket2 != null ? Boxing.boxBoolean(multicastSocket2.isClosed()) : null;
                        if (boxBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!boxBoolean.booleanValue()) {
                            str2 = this.this$0.TAG;
                            Pdlog.m3273d(str2, "close multicast when current recv thread is running");
                            MulticastConnection.MultiCallBack callBack = this.this$0.getCallBack();
                            if (callBack != null) {
                                callBack.clearIpList();
                            }
                            MulticastConnection.sendMsg$default(this.this$0, MulticastConnection.MSGID.OFFLINE.ordinal(), null, 2, null);
                            try {
                                try {
                                    str4 = this.this$0.TAG;
                                    Pdlog.m3273d(str4, "leave group, then close socket");
                                    str5 = this.this$0.MULTICAST_ADDRESS;
                                    InetAddress byName = InetAddress.getByName(str5);
                                    multicastSocket5 = this.this$0.multicastSocket;
                                    if (multicastSocket5 != null) {
                                        multicastSocket5.leaveGroup(byName);
                                    }
                                    multicastSocket6 = this.this$0.multicastSocket;
                                    if (multicastSocket6 != null) {
                                        multicastSocket6.close();
                                    }
                                } catch (Exception e) {
                                    str3 = this.this$0.TAG;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("close multicast ex ");
                                    sb.append(e.getLocalizedMessage());
                                    sb.append(": ");
                                    StackTraceElement[] stackTrace = e.getStackTrace();
                                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                                    sb.append(ArraysKt.contentDeepToString(stackTrace));
                                    Pdlog.m3277w(str3, sb.toString());
                                    multicastSocket4 = this.this$0.multicastSocket;
                                }
                            } finally {
                                multicastSocket3 = this.this$0.multicastSocket;
                                if (multicastSocket3 != null) {
                                    multicastSocket3.close();
                                }
                                this.this$0.multicastSocket = (MulticastSocket) null;
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        job2 = this.this$0.keepJob;
        if (job2 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        job3 = this.this$0.recvJob;
        if (job3 != null) {
        }
        multicastSocket = this.this$0.multicastSocket;
        if (multicastSocket != null) {
        }
        return Unit.INSTANCE;
    }
}
