package com.pudutech.schedulerlib.connection;

import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.utils.LimitDequeue;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: UDPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0016*\u0001\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001d\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\tH\u0000¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u001eH\u0000¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\u001eH\u0000¢\u0006\u0002\b%J\u0011\u0010&\u001a\u00020\u001eH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u001eH\u0002J\u0015\u0010)\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0004H\u0000¢\u0006\u0002\b*J\u0015\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u0013H\u0000¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b/J\b\u00100\u001a\u00020\u001eH\u0002J\b\u00101\u001a\u00020\u001eH\u0002J\r\u00102\u001a\u00020\u001eH\u0000¢\u0006\u0002\b3R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u000fj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/UDPConnection;", "", "()V", "TAG", "", "UDPScope", "com/pudutech/schedulerlib/connection/UDPConnection$UDPScope$1", "Lcom/pudutech/schedulerlib/connection/UDPConnection$UDPScope$1;", "aliveDura", "", "callback", "Lcom/pudutech/schedulerlib/connection/ScheduleMsgReceiveInterface;", "datagramSocket", "Ljava/net/DatagramSocket;", "ipList", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "msgQueue", "Lcom/pudutech/schedulerlib/utils/LimitDequeue;", "", "parseJob", "Lkotlinx/coroutines/Job;", "recvJob", "running", "", "sendJob", "sendPacket", "Ljava/net/DatagramPacket;", "sendQueue", "addRemoteIp", "", "ip", "alive", "addRemoteIp$schedulerlib_release", "clearIpList", "clearIpList$schedulerlib_release", "closeUdpConnection", "closeUdpConnection$schedulerlib_release", "createSocket", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processMsg", "removeIp", "removeIp$schedulerlib_release", "sendScheduleMsg", NotificationCompat.CATEGORY_MESSAGE, "sendScheduleMsg$schedulerlib_release", "setMsgDecodeCallBack", "setMsgDecodeCallBack$schedulerlib_release", "startRecvThread", "startSendThread", "startWorkingThread", "startWorkingThread$schedulerlib_release", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UDPConnection {
    private long aliveDura;
    private ScheduleMsgReceiveInterface callback;
    private DatagramSocket datagramSocket;
    private Job parseJob;
    private Job recvJob;
    private boolean running;
    private Job sendJob;
    private final String TAG = "udpconn";
    private final HashMap<String, Long> ipList = new HashMap<>();
    private final LimitDequeue<byte[]> msgQueue = new LimitDequeue<>(0, 1, null);
    private final LimitDequeue<byte[]> sendQueue = new LimitDequeue<>(3);
    private final UDPConnection$UDPScope$1 UDPScope = new UDPConnection$UDPScope$1();
    private final DatagramPacket sendPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName("0.0.0.0"), 11202);

    public final void setMsgDecodeCallBack$schedulerlib_release(ScheduleMsgReceiveInterface callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    public final void addRemoteIp$schedulerlib_release(String ip, long alive) {
        Intrinsics.checkParameterIsNotNull(ip, "ip");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.aliveDura = alive;
        synchronized (this.ipList) {
            this.ipList.put(ip, Long.valueOf(elapsedRealtime));
            Iterator<Map.Entry<String, Long>> it = this.ipList.entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                Long l = this.ipList.get(key);
                if (l == null) {
                    Intrinsics.throwNpe();
                }
                if (l.longValue() - elapsedRealtime > alive) {
                    Pdlog.m3273d(this.TAG, "remove ip " + this.ipList.get(key) + " from link list");
                    it.remove();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearIpList$schedulerlib_release() {
        synchronized (this.ipList) {
            this.ipList.clear();
            Unit unit = Unit.INSTANCE;
        }
        Pdlog.m3273d(this.TAG, "clear ip list, maybe close connection or reset multicast ip, ip list size " + this.ipList.size() + " : " + this.ipList);
    }

    public final void removeIp$schedulerlib_release(String ip) {
        Intrinsics.checkParameterIsNotNull(ip, "ip");
        Pdlog.m3273d(this.TAG, ip + " maybe outline, multicast request remove it");
        synchronized (this.ipList) {
            if (this.ipList.containsKey(ip)) {
                this.ipList.remove(ip);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object createSocket(Continuation<? super Unit> continuation) {
        UDPConnection$createSocket$1 uDPConnection$createSocket$1;
        int i;
        UDPConnection uDPConnection;
        if (continuation instanceof UDPConnection$createSocket$1) {
            uDPConnection$createSocket$1 = (UDPConnection$createSocket$1) continuation;
            if ((uDPConnection$createSocket$1.label & Integer.MIN_VALUE) != 0) {
                uDPConnection$createSocket$1.label -= Integer.MIN_VALUE;
                Object obj = uDPConnection$createSocket$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = uDPConnection$createSocket$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    uDPConnection = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    uDPConnection = (UDPConnection) uDPConnection$createSocket$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                while (uDPConnection.running) {
                    try {
                    } catch (Exception e) {
                        String str = uDPConnection.TAG;
                        StackTraceElement[] stackTrace = e.getStackTrace();
                        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                        Pdlog.m3277w(str, "UDPSocket Create Exception:", e.getMessage(), ArraysKt.contentDeepToString(stackTrace));
                        uDPConnection$createSocket$1.L$0 = uDPConnection;
                        uDPConnection$createSocket$1.L$1 = e;
                        uDPConnection$createSocket$1.label = 1;
                        if (DelayKt.delay(5000L, uDPConnection$createSocket$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    if (uDPConnection.datagramSocket != null) {
                        DatagramSocket datagramSocket = uDPConnection.datagramSocket;
                        Boolean boxBoolean = datagramSocket != null ? Boxing.boxBoolean(datagramSocket.isClosed()) : null;
                        if (boxBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!boxBoolean.booleanValue()) {
                            continue;
                        }
                    }
                    DatagramSocket datagramSocket2 = uDPConnection.datagramSocket;
                    if (datagramSocket2 != null) {
                        datagramSocket2.close();
                    }
                    DatagramSocket datagramSocket3 = new DatagramSocket((SocketAddress) null);
                    uDPConnection.datagramSocket = datagramSocket3;
                    if (datagramSocket3 != null) {
                        datagramSocket3.setReuseAddress(true);
                    }
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(11202);
                    DatagramSocket datagramSocket4 = uDPConnection.datagramSocket;
                    if (datagramSocket4 != null) {
                        datagramSocket4.bind(inetSocketAddress);
                    }
                    String str2 = uDPConnection.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("UDP Buffers: send ");
                    DatagramSocket datagramSocket5 = uDPConnection.datagramSocket;
                    sb.append(datagramSocket5 != null ? Boxing.boxInt(datagramSocket5.getSendBufferSize()) : null);
                    sb.append(" recv ");
                    DatagramSocket datagramSocket6 = uDPConnection.datagramSocket;
                    sb.append(datagramSocket6 != null ? Boxing.boxInt(datagramSocket6.getReceiveBufferSize()) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str2, objArr);
                    DatagramSocket datagramSocket7 = uDPConnection.datagramSocket;
                    if (datagramSocket7 != null) {
                        datagramSocket7.setSendBufferSize(1024);
                    }
                    DatagramSocket datagramSocket8 = uDPConnection.datagramSocket;
                    if (datagramSocket8 != null) {
                        datagramSocket8.setReceiveBufferSize(2560);
                    }
                    String str3 = uDPConnection.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("UDP Buffers: send ");
                    DatagramSocket datagramSocket9 = uDPConnection.datagramSocket;
                    sb2.append(datagramSocket9 != null ? Boxing.boxInt(datagramSocket9.getSendBufferSize()) : null);
                    sb2.append(" recv ");
                    DatagramSocket datagramSocket10 = uDPConnection.datagramSocket;
                    sb2.append(datagramSocket10 != null ? Boxing.boxInt(datagramSocket10.getReceiveBufferSize()) : null);
                    objArr2[0] = sb2.toString();
                    Pdlog.m3273d(str3, objArr2);
                }
                return Unit.INSTANCE;
            }
        }
        uDPConnection$createSocket$1 = new UDPConnection$createSocket$1(this, continuation);
        Object obj2 = uDPConnection$createSocket$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = uDPConnection$createSocket$1.label;
        if (i != 0) {
        }
        while (uDPConnection.running) {
        }
        return Unit.INSTANCE;
    }

    public final void startWorkingThread$schedulerlib_release() {
        this.running = true;
        startRecvThread();
        startSendThread();
    }

    private final void startRecvThread() {
        BuildersKt__Builders_commonKt.launch$default(this.UDPScope, null, null, new UDPConnection$startRecvThread$1(this, null), 3, null);
    }

    public final void sendScheduleMsg$schedulerlib_release(byte[] msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3276v(this.TAG, "udp send msg length " + msg.length);
        if (msg.length > 250) {
            Pdlog.m3273d(this.TAG, "msg length should be not too long than 250, because esp device can not send msg longer than 250");
        } else {
            this.sendQueue.putFirst(msg);
        }
    }

    private final void startSendThread() {
        Job launch$default;
        UDPConnection$UDPScope$1 uDPConnection$UDPScope$1 = this.UDPScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(uDPConnection$UDPScope$1, uDPConnection$UDPScope$1.getSendContext(), null, new UDPConnection$startSendThread$1(this, null), 2, null);
        this.sendJob = launch$default;
    }

    public final void closeUdpConnection$schedulerlib_release() {
        BuildersKt__Builders_commonKt.launch$default(this.UDPScope, null, null, new UDPConnection$closeUdpConnection$1(this, null), 3, null);
        BuildersKt__BuildersKt.runBlocking$default(null, new UDPConnection$closeUdpConnection$2(this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processMsg() {
        Job launch$default;
        UDPConnection$UDPScope$1 uDPConnection$UDPScope$1 = this.UDPScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(uDPConnection$UDPScope$1, uDPConnection$UDPScope$1.getParseContext(), null, new UDPConnection$processMsg$1(this, null), 2, null);
        this.parseJob = launch$default;
    }
}
