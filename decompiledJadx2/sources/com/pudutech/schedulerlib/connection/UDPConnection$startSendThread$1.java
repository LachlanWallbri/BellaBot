package com.pudutech.schedulerlib.connection;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.utils.LimitDequeue;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
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
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.connection.UDPConnection$startSendThread$1", m3970f = "UDPConnection.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class UDPConnection$startSendThread$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7450p$;
    final /* synthetic */ UDPConnection this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UDPConnection$startSendThread$1(UDPConnection uDPConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uDPConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UDPConnection$startSendThread$1 uDPConnection$startSendThread$1 = new UDPConnection$startSendThread$1(this.this$0, completion);
        uDPConnection$startSendThread$1.f7450p$ = (CoroutineScope) obj;
        return uDPConnection$startSendThread$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UDPConnection$startSendThread$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LimitDequeue limitDequeue;
        HashMap hashMap;
        String str;
        DatagramPacket datagramPacket;
        HashMap hashMap2;
        String str2;
        HashMap hashMap3;
        HashMap hashMap4;
        long j;
        HashMap hashMap5;
        DatagramPacket datagramPacket2;
        DatagramSocket datagramSocket;
        DatagramPacket datagramPacket3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7450p$;
        while (CoroutineScopeKt.isActive(coroutineScope) && CoroutineScopeKt.isActive(coroutineScope)) {
            limitDequeue = this.this$0.sendQueue;
            byte[] bArr = (byte[]) limitDequeue.takeFirst();
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                break;
            }
            hashMap = this.this$0.ipList;
            synchronized (hashMap) {
                try {
                    datagramPacket = this.this$0.sendPacket;
                    datagramPacket.setData(bArr, 0, bArr.length);
                    hashMap2 = this.this$0.ipList;
                    for (Map.Entry entry : hashMap2.entrySet()) {
                        if (!CoroutineScopeKt.isActive(coroutineScope)) {
                            return Unit.INSTANCE;
                        }
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        Object value = entry.getValue();
                        Intrinsics.checkExpressionValueIsNotNull(value, "ip.value");
                        long longValue = elapsedRealtime - ((Number) value).longValue();
                        j = this.this$0.aliveDura;
                        if (longValue > j) {
                            hashMap5 = this.this$0.ipList;
                            hashMap5.remove(entry.getKey());
                        } else {
                            datagramPacket2 = this.this$0.sendPacket;
                            datagramPacket2.setAddress(InetAddress.getByName((String) entry.getKey()));
                            datagramSocket = this.this$0.datagramSocket;
                            if (datagramSocket != null) {
                                datagramPacket3 = this.this$0.sendPacket;
                                datagramSocket.send(datagramPacket3);
                            }
                        }
                    }
                    str2 = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("neighbor number ");
                    hashMap3 = this.this$0.ipList;
                    sb.append(hashMap3.size());
                    sb.append(", msg to ");
                    hashMap4 = this.this$0.ipList;
                    Set keySet = hashMap4.keySet();
                    Intrinsics.checkExpressionValueIsNotNull(keySet, "ipList.keys");
                    sb.append(CollectionsKt.joinToString$default(keySet, null, null, null, 0, null, null, 63, null));
                    Pdlog.m3273d(str2, sb.toString());
                } catch (Exception e) {
                    str = this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("udp message send failed, please check your network connection. ");
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                    sb2.append(ArraysKt.contentDeepToString(stackTrace));
                    Pdlog.m3277w(str, sb2.toString());
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
