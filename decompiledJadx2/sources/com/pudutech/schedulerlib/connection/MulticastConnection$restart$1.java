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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
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
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$restart$1", m3970f = "MulticastConnection.kt", m3971i = {0, 1, 2}, m3972l = {151, 152, 153}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class MulticastConnection$restart$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $oldAddress;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7438p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MulticastConnection$restart$1(MulticastConnection multicastConnection, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
        this.$oldAddress = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$restart$1 multicastConnection$restart$1 = new MulticastConnection$restart$1(this.this$0, this.$oldAddress, completion);
        multicastConnection$restart$1.f7438p$ = (CoroutineScope) obj;
        return multicastConnection$restart$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MulticastConnection$restart$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
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
        MulticastConnection$MulticastScope$1 multicastConnection$MulticastScope$1;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7438p$;
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "waiting last start thread");
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
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        MulticastConnection multicastConnection = this.this$0;
                        multicastConnection$MulticastScope$1 = multicastConnection.MulticastScope;
                        launch$default = BuildersKt__Builders_commonKt.launch$default(multicastConnection$MulticastScope$1, null, null, new C57281(null), 3, null);
                        multicastConnection.linkJob = launch$default;
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
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
                MulticastConnection multicastConnection2 = this.this$0;
                multicastConnection$MulticastScope$1 = multicastConnection2.MulticastScope;
                launch$default = BuildersKt__Builders_commonKt.launch$default(multicastConnection$MulticastScope$1, null, null, new C57281(null), 3, null);
                multicastConnection2.linkJob = launch$default;
                return Unit.INSTANCE;
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
        MulticastConnection multicastConnection22 = this.this$0;
        multicastConnection$MulticastScope$1 = multicastConnection22.MulticastScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(multicastConnection$MulticastScope$1, null, null, new C57281(null), 3, null);
        multicastConnection22.linkJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* compiled from: MulticastConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$restart$1$1", m3970f = "MulticastConnection.kt", m3971i = {0, 0, 1, 2}, m3972l = {163, 188, 193}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "num", "$this$launch", "$this$launch"}, m3975s = {"L$0", "I$0", "L$0", "L$0"})
    /* renamed from: com.pudutech.schedulerlib.connection.MulticastConnection$restart$1$1 */
    /* loaded from: classes2.dex */
    public static final class C57281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7439p$;

        C57281(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57281 c57281 = new C57281(completion);
            c57281.f7439p$ = (CoroutineScope) obj;
            return c57281;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x01e4  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x01f1 A[EDGE_INSN: B:23:0x01f1->B:24:0x01f1 BREAK  A[LOOP:0: B:12:0x01cd->B:22:?], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x01f7  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x01fa  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0074 A[Catch: Exception -> 0x00d1, TRY_ENTER, TryCatch #1 {Exception -> 0x00d1, blocks: (B:36:0x0099, B:38:0x0074, B:40:0x007a, B:42:0x007d, B:45:0x009c, B:47:0x00ae, B:48:0x00b1, B:50:0x00bb, B:51:0x00be, B:53:0x00ce), top: B:35:0x0099 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x009c A[Catch: Exception -> 0x00d1, TryCatch #1 {Exception -> 0x00d1, blocks: (B:36:0x0099, B:38:0x0074, B:40:0x007a, B:42:0x007d, B:45:0x009c, B:47:0x00ae, B:48:0x00b1, B:50:0x00bb, B:51:0x00be, B:53:0x00ce), top: B:35:0x0099 }] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x01a7  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01aa  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0096 -> B:29:0x0099). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            C57281 c57281;
            String str;
            MulticastSocket multicastSocket;
            C57281 c572812;
            MulticastSocket multicastSocket2;
            MulticastSocket multicastSocket3;
            CoroutineScope coroutineScope;
            CoroutineScope coroutineScope2;
            boolean joinGroup;
            MulticastSocket multicastSocket4;
            String str2;
            MulticastSocket multicastSocket5;
            MulticastSocket multicastSocket6;
            MulticastSocket multicastSocket7;
            String str3;
            int i;
            MulticastSocket multicastSocket8;
            MulticastSocket multicastSocket9;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            try {
            } catch (Exception e) {
                e = e;
                obj2 = coroutine_suspended;
                c57281 = this;
            }
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f7439p$;
                multicastSocket4 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                if (multicastSocket4 != null) {
                    multicastSocket7 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                    Boolean boxBoolean = multicastSocket7 != null ? Boxing.boxBoolean(multicastSocket7.isClosed()) : null;
                    if (boxBoolean == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!boxBoolean.booleanValue()) {
                        str3 = MulticastConnection$restart$1.this.this$0.TAG;
                        Pdlog.m3273d(str3, "send offline before leave group when reset addresss");
                        c572812 = this;
                        i = 3;
                        if (i > 0) {
                        }
                    }
                }
                str2 = MulticastConnection$restart$1.this.this$0.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("multicast socket ");
                multicastSocket5 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                sb.append(multicastSocket5 == null);
                sb.append(", closed? ");
                multicastSocket6 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                sb.append(multicastSocket6 != null ? Boxing.boxBoolean(multicastSocket6.isClosed()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str2, objArr);
                c572812 = this;
                coroutineScope2 = coroutineScope;
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                }
            } else {
                if (i2 != 1) {
                    if (i2 == 2 || i2 == 3) {
                        coroutineScope2 = (CoroutineScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        c572812 = this;
                        while (CoroutineScopeKt.isActive(coroutineScope2) && CoroutineScopeKt.isActive(coroutineScope2)) {
                            joinGroup = MulticastConnection$restart$1.this.this$0.joinGroup();
                            if (!joinGroup) {
                                break;
                            }
                            c572812.L$0 = coroutineScope2;
                            c572812.label = 3;
                            if (DelayKt.delay(10000L, c572812) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        if (CoroutineScopeKt.isActive(coroutineScope2)) {
                            return Unit.INSTANCE;
                        }
                        MulticastConnection$restart$1.this.this$0.startTasks();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c572812 = this;
                try {
                    i--;
                } catch (Exception e2) {
                    c57281 = c572812;
                    e = e2;
                    obj2 = coroutine_suspended;
                    str = MulticastConnection$restart$1.this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("reset address exception ");
                    sb2.append(e.getLocalizedMessage());
                    sb2.append(", force close socket: ");
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                    sb2.append(ArraysKt.contentDeepToString(stackTrace));
                    Pdlog.m3277w(str, sb2.toString());
                    multicastSocket = MulticastConnection$restart$1.this.this$0.multicastSocket;
                    if (multicastSocket != null) {
                        multicastSocket2 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                        Boolean boxBoolean2 = multicastSocket2 != null ? Boxing.boxBoolean(multicastSocket2.isClosed()) : null;
                        if (boxBoolean2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!boxBoolean2.booleanValue()) {
                            multicastSocket3 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                            if (multicastSocket3 != null) {
                                multicastSocket3.close();
                            }
                            MulticastConnection$restart$1.this.this$0.multicastSocket = (MulticastSocket) null;
                        }
                    }
                    c572812 = c57281;
                    coroutine_suspended = obj2;
                    coroutineScope2 = coroutineScope;
                    if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                    }
                }
                if (i > 0) {
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                    MulticastConnection.sendMsg$default(MulticastConnection$restart$1.this.this$0, MulticastConnection.MSGID.OFFLINE.ordinal(), null, 2, null);
                    c572812.L$0 = coroutineScope;
                    c572812.I$0 = i;
                    c572812.label = 1;
                    if (DelayKt.delay(50L, c572812) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i--;
                    if (i > 0) {
                    }
                } else {
                    InetAddress byName = InetAddress.getByName(MulticastConnection$restart$1.this.$oldAddress);
                    multicastSocket8 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                    if (multicastSocket8 != null) {
                        multicastSocket8.leaveGroup(byName);
                    }
                    multicastSocket9 = MulticastConnection$restart$1.this.this$0.multicastSocket;
                    if (multicastSocket9 != null) {
                        multicastSocket9.close();
                    }
                    MulticastConnection$restart$1.this.this$0.multicastSocket = (MulticastSocket) null;
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                    coroutineScope2 = coroutineScope;
                    if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                        return Unit.INSTANCE;
                    }
                    MulticastConnection.MultiCallBack callBack = MulticastConnection$restart$1.this.this$0.getCallBack();
                    if (callBack != null) {
                        callBack.clearIpList();
                    }
                    if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                        return Unit.INSTANCE;
                    }
                    c572812.L$0 = coroutineScope2;
                    c572812.label = 2;
                    if (DelayKt.delay(200L, c572812) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    while (CoroutineScopeKt.isActive(coroutineScope2)) {
                        joinGroup = MulticastConnection$restart$1.this.this$0.joinGroup();
                        if (!joinGroup) {
                        }
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope2)) {
                    }
                }
            }
        }
    }
}
