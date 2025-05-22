package com.pudutech.schedulerlib.connection;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.schedulerlib.utils.LimitDequeue;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
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
/* compiled from: UDPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/schedulerlib/connection/UDPConnection$startRecvThread$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.schedulerlib.connection.UDPConnection$startRecvThread$1$invokeSuspend$$inlined$let$lambda$1 */
/* loaded from: classes2.dex */
public final class C5733x9a4bf630 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DatagramSocket $it;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7445p$;
    final /* synthetic */ UDPConnection$startRecvThread$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5733x9a4bf630(DatagramSocket datagramSocket, Continuation continuation, UDPConnection$startRecvThread$1 uDPConnection$startRecvThread$1) {
        super(2, continuation);
        this.$it = datagramSocket;
        this.this$0 = uDPConnection$startRecvThread$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5733x9a4bf630 c5733x9a4bf630 = new C5733x9a4bf630(this.$it, completion, this.this$0);
        c5733x9a4bf630.f7445p$ = (CoroutineScope) obj;
        return c5733x9a4bf630;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5733x9a4bf630) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0131 -> B:6:0x0134). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        byte[] bArr;
        DatagramPacket datagramPacket;
        C5733x9a4bf630 c5733x9a4bf630;
        Exception e;
        byte[] bArr2;
        DatagramPacket datagramPacket2;
        String str;
        String str2;
        LimitDequeue limitDequeue;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7445p$;
            bArr = new byte[256];
            datagramPacket = new DatagramPacket(bArr, 256);
            c5733x9a4bf630 = this;
            while (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i == 1) {
            Exception exc = (Exception) this.L$3;
            datagramPacket2 = (DatagramPacket) this.L$2;
            bArr2 = (byte[]) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            e = exc;
            coroutineScope = coroutineScope2;
            c5733x9a4bf630 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                UDPConnection uDPConnection = c5733x9a4bf630.this$0.this$0;
                c5733x9a4bf630.L$0 = coroutineScope;
                c5733x9a4bf630.L$1 = bArr2;
                c5733x9a4bf630.L$2 = datagramPacket2;
                c5733x9a4bf630.L$3 = e;
                c5733x9a4bf630.label = 2;
                if (uDPConnection.createSocket(c5733x9a4bf630) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                byte[] bArr3 = bArr2;
                datagramPacket = datagramPacket2;
                bArr = bArr3;
                while (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            return Unit.INSTANCE;
        }
        if (i == 2) {
            DatagramPacket datagramPacket3 = (DatagramPacket) this.L$2;
            byte[] bArr4 = (byte[]) this.L$1;
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
            bArr2 = bArr4;
            datagramPacket2 = datagramPacket3;
            c5733x9a4bf630 = this;
            byte[] bArr32 = bArr2;
            datagramPacket = datagramPacket2;
            bArr = bArr32;
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                try {
                } catch (Exception e2) {
                    e = e2;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        str = c5733x9a4bf630.this$0.this$0.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("recv exception ");
                        sb.append(e.getLocalizedMessage());
                        sb.append(": ");
                        StackTraceElement[] stackTrace = e.getStackTrace();
                        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                        sb.append(ArraysKt.contentDeepToString(stackTrace));
                        Pdlog.m3277w(str, sb.toString());
                        c5733x9a4bf630.L$0 = coroutineScope;
                        c5733x9a4bf630.L$1 = bArr;
                        c5733x9a4bf630.L$2 = datagramPacket;
                        c5733x9a4bf630.L$3 = e;
                        c5733x9a4bf630.label = 1;
                        if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, c5733x9a4bf630) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        DatagramPacket datagramPacket4 = datagramPacket;
                        bArr2 = bArr;
                        datagramPacket2 = datagramPacket4;
                    }
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                c5733x9a4bf630.$it.receive(datagramPacket);
                str2 = c5733x9a4bf630.this$0.this$0.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("udp recv msg length ");
                sb2.append(datagramPacket.getLength());
                sb2.append(", ");
                InetAddress address = datagramPacket.getAddress();
                Intrinsics.checkExpressionValueIsNotNull(address, "recvPacket.address");
                sb2.append(address.getHostAddress());
                Pdlog.m3273d(str2, sb2.toString());
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                limitDequeue = c5733x9a4bf630.this$0.this$0.msgQueue;
                byte[] data = datagramPacket.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "recvPacket.data");
                limitDequeue.putFirst(data);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
