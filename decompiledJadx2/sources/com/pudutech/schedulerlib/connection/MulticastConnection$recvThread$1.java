package com.pudutech.schedulerlib.connection;

import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
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
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.MulticastConnection$recvThread$1", m3970f = "MulticastConnection.kt", m3971i = {0, 0, 0}, m3972l = {284}, m3973m = "invokeSuspend", m3974n = {"$this$launch", NotificationCompat.CATEGORY_MESSAGE, "dataPackage"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes2.dex */
public final class MulticastConnection$recvThread$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7437p$;
    final /* synthetic */ MulticastConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MulticastConnection$recvThread$1(MulticastConnection multicastConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicastConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MulticastConnection$recvThread$1 multicastConnection$recvThread$1 = new MulticastConnection$recvThread$1(this.this$0, completion);
        multicastConnection$recvThread$1.f7437p$ = (CoroutineScope) obj;
        return multicastConnection$recvThread$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MulticastConnection$recvThread$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        byte[] bArr;
        DatagramPacket datagramPacket;
        CoroutineScope coroutineScope;
        String str;
        boolean z;
        String str2;
        MulticastSocket multicastSocket;
        String str3;
        boolean parseMsg;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7437p$;
            this.this$0.lastRecvTime = SystemClock.elapsedRealtime();
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                return Unit.INSTANCE;
            }
            bArr = new byte[128];
            datagramPacket = new DatagramPacket(bArr, 128);
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            datagramPacket = (DatagramPacket) this.L$2;
            bArr = (byte[]) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            z = this.this$0.runnable;
            if (!z) {
                break;
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return Unit.INSTANCE;
            }
            try {
                multicastSocket = this.this$0.multicastSocket;
                if (multicastSocket != null) {
                    multicastSocket.receive(datagramPacket);
                }
            } catch (SocketTimeoutException unused) {
            } catch (Exception e) {
                str2 = this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("recv thread exp ");
                sb.append(e.getMessage());
                sb.append(": ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str2, sb.toString());
                return Unit.INSTANCE;
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return Unit.INSTANCE;
            }
            InetAddress address = datagramPacket.getAddress();
            String hostAddress = address != null ? address.getHostAddress() : null;
            str3 = this.this$0.localIp;
            if (Intrinsics.areEqual(hostAddress, str3)) {
                continue;
            } else {
                parseMsg = this.this$0.parseMsg(datagramPacket);
                if (parseMsg) {
                    continue;
                } else {
                    this.L$0 = coroutineScope;
                    this.L$1 = bArr;
                    this.L$2 = datagramPacket;
                    this.label = 1;
                    if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        }
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "multicast recv thread finished");
        return Unit.INSTANCE;
    }
}
