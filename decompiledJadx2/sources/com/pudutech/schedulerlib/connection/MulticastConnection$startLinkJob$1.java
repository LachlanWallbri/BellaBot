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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$startLinkJob$1", m3970f = "MulticastConnection.kt", m3971i = {0, 0, 1, 2}, m3972l = {168, 196, 200}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "num", "$this$launch", "$this$launch"}, m3975s = {"L$0", "I$0", "L$0", "L$0"})
/* loaded from: classes7.dex */
final class MulticastConnection$startLinkJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $oldAddress;
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7441p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MulticastConnection$startLinkJob$1(MulticastConnection multicastConnection, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
        this.$oldAddress = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$startLinkJob$1 multicastConnection$startLinkJob$1 = new MulticastConnection$startLinkJob$1(this.this$0, this.$oldAddress, completion);
        multicastConnection$startLinkJob$1.f7441p$ = (CoroutineScope) obj;
        return multicastConnection$startLinkJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MulticastConnection$startLinkJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01cd A[EDGE_INSN: B:23:0x01cd->B:24:0x01cd BREAK  A[LOOP:0: B:12:0x01ab->B:22:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006e A[Catch: Exception -> 0x00c1, TRY_ENTER, TryCatch #1 {Exception -> 0x00c1, blocks: (B:36:0x0091, B:38:0x006e, B:40:0x0074, B:42:0x0077, B:45:0x0094, B:47:0x00a2, B:48:0x00a5, B:50:0x00ad, B:51:0x00b0, B:53:0x00be), top: B:35:0x0091 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0094 A[Catch: Exception -> 0x00c1, TryCatch #1 {Exception -> 0x00c1, blocks: (B:36:0x0091, B:38:0x006e, B:40:0x0074, B:42:0x0077, B:45:0x0094, B:47:0x00a2, B:48:0x00a5, B:50:0x00ad, B:51:0x00b0, B:53:0x00be), top: B:35:0x0091 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x008e -> B:29:0x0091). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        MulticastConnection$startLinkJob$1 multicastConnection$startLinkJob$1;
        String str;
        MulticastSocket multicastSocket;
        MulticastConnection$startLinkJob$1 multicastConnection$startLinkJob$12;
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
            multicastConnection$startLinkJob$1 = this;
        }
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7441p$;
            multicastSocket4 = this.this$0.multicastSocket;
            if (multicastSocket4 != null) {
                multicastSocket7 = this.this$0.multicastSocket;
                Boolean boxBoolean = multicastSocket7 != null ? Boxing.boxBoolean(multicastSocket7.isClosed()) : null;
                if (boxBoolean == null) {
                    Intrinsics.throwNpe();
                }
                if (!boxBoolean.booleanValue()) {
                    str3 = this.this$0.TAG;
                    Pdlog.m3273d(str3, "send offline before leave group when reset addresss");
                    multicastConnection$startLinkJob$12 = this;
                    i = 3;
                    if (i > 0) {
                    }
                }
            }
            str2 = this.this$0.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("multicast socket ");
            multicastSocket5 = this.this$0.multicastSocket;
            sb.append(multicastSocket5 == null);
            sb.append(", closed? ");
            multicastSocket6 = this.this$0.multicastSocket;
            sb.append(multicastSocket6 != null ? Boxing.boxBoolean(multicastSocket6.isClosed()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str2, objArr);
            multicastConnection$startLinkJob$12 = this;
            coroutineScope2 = coroutineScope;
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
            }
        } else {
            if (i2 != 1) {
                if (i2 == 2 || i2 == 3) {
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    multicastConnection$startLinkJob$12 = this;
                    while (CoroutineScopeKt.isActive(coroutineScope2) && CoroutineScopeKt.isActive(coroutineScope2)) {
                        joinGroup = multicastConnection$startLinkJob$12.this$0.joinGroup();
                        if (!joinGroup) {
                            break;
                        }
                        multicastConnection$startLinkJob$12.L$0 = coroutineScope2;
                        multicastConnection$startLinkJob$12.label = 3;
                        if (DelayKt.delay(10000L, multicastConnection$startLinkJob$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope2)) {
                        return Unit.INSTANCE;
                    }
                    multicastConnection$startLinkJob$12.this$0.startTasks();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            multicastConnection$startLinkJob$12 = this;
            try {
                i--;
            } catch (Exception e2) {
                multicastConnection$startLinkJob$1 = multicastConnection$startLinkJob$12;
                e = e2;
                obj2 = coroutine_suspended;
                str = multicastConnection$startLinkJob$1.this$0.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("reset address exception ");
                sb2.append(e.getLocalizedMessage());
                sb2.append(", force close socket: ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb2.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str, sb2.toString());
                multicastSocket = multicastConnection$startLinkJob$1.this$0.multicastSocket;
                if (multicastSocket != null) {
                    multicastSocket2 = multicastConnection$startLinkJob$1.this$0.multicastSocket;
                    Boolean boxBoolean2 = multicastSocket2 != null ? Boxing.boxBoolean(multicastSocket2.isClosed()) : null;
                    if (boxBoolean2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!boxBoolean2.booleanValue()) {
                        multicastSocket3 = multicastConnection$startLinkJob$1.this$0.multicastSocket;
                        if (multicastSocket3 != null) {
                            multicastSocket3.close();
                        }
                        multicastConnection$startLinkJob$1.this$0.multicastSocket = (MulticastSocket) null;
                    }
                }
                multicastConnection$startLinkJob$12 = multicastConnection$startLinkJob$1;
                coroutine_suspended = obj2;
                coroutineScope2 = coroutineScope;
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                }
            }
            if (i > 0) {
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                MulticastConnection.sendMsg$default(multicastConnection$startLinkJob$12.this$0, MulticastConnection.MSGID.OFFLINE.ordinal(), null, 2, null);
                multicastConnection$startLinkJob$12.L$0 = coroutineScope;
                multicastConnection$startLinkJob$12.I$0 = i;
                multicastConnection$startLinkJob$12.label = 1;
                if (DelayKt.delay(50L, multicastConnection$startLinkJob$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i--;
                if (i > 0) {
                }
            } else {
                InetAddress byName = InetAddress.getByName(multicastConnection$startLinkJob$12.$oldAddress);
                multicastSocket8 = multicastConnection$startLinkJob$12.this$0.multicastSocket;
                if (multicastSocket8 != null) {
                    multicastSocket8.leaveGroup(byName);
                }
                multicastSocket9 = multicastConnection$startLinkJob$12.this$0.multicastSocket;
                if (multicastSocket9 != null) {
                    multicastSocket9.close();
                }
                multicastConnection$startLinkJob$12.this$0.multicastSocket = (MulticastSocket) null;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                coroutineScope2 = coroutineScope;
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                    return Unit.INSTANCE;
                }
                MulticastConnection.MultiCallBack callBack = multicastConnection$startLinkJob$12.this$0.getCallBack();
                if (callBack != null) {
                    callBack.clearIpList();
                }
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                    return Unit.INSTANCE;
                }
                multicastConnection$startLinkJob$12.L$0 = coroutineScope2;
                multicastConnection$startLinkJob$12.label = 2;
                if (DelayKt.delay(200L, multicastConnection$startLinkJob$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                while (CoroutineScopeKt.isActive(coroutineScope2)) {
                    joinGroup = multicastConnection$startLinkJob$12.this$0.joinGroup();
                    if (!joinGroup) {
                    }
                }
                if (CoroutineScopeKt.isActive(coroutineScope2)) {
                }
            }
        }
    }
}
