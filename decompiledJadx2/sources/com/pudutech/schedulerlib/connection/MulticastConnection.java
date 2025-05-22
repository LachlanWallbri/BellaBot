package com.pudutech.schedulerlib.connection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.schedulerlib.ScheduleConstant;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.proto.SchedulerInfo;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\b\u0018\u00002\u00020\u0001:\u0002FGB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020&H\u0002J\r\u0010.\u001a\u00020/H\u0000¢\u0006\u0002\b0J\r\u00101\u001a\u00020\u0006H\u0000¢\u0006\u0002\b2J\b\u00103\u001a\u00020&H\u0002J\b\u00104\u001a\u00020/H\u0002J\u0010\u00105\u001a\u00020&2\u0006\u00106\u001a\u00020*H\u0002J\b\u00107\u001a\u00020/H\u0002J\u0015\u00108\u001a\u00020/2\u0006\u00109\u001a\u00020\u000bH\u0000¢\u0006\u0002\b:J\u0012\u0010;\u001a\u00020/2\b\b\u0002\u0010<\u001a\u00020\u0006H\u0002J\u001a\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020\u000b2\b\b\u0002\u0010?\u001a\u00020\u0006H\u0002J\u001d\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020CH\u0000¢\u0006\u0002\bDJ\b\u0010E\u001a\u00020/H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u00108@@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n !*\u0004\u0018\u00010 0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010'\u001a\n !*\u0004\u0018\u00010 0 ¢\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006H"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/MulticastConnection;", "", "()V", "KEEP_ALIVE_TIME", "", "MULTICAST_ADDRESS", "", "MulticastScope", "com/pudutech/schedulerlib/connection/MulticastConnection$MulticastScope$1", "Lcom/pudutech/schedulerlib/connection/MulticastConnection$MulticastScope$1;", "RECV_TIMEOUT", "", "TAG", "application", "Landroid/content/Context;", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/schedulerlib/connection/MulticastConnection$MultiCallBack;", "callBack", "getCallBack$schedulerlib_release", "()Lcom/pudutech/schedulerlib/connection/MulticastConnection$MultiCallBack;", "setCallBack$schedulerlib_release", "(Lcom/pudutech/schedulerlib/connection/MulticastConnection$MultiCallBack;)V", "clearTimes", "keepJob", "Lkotlinx/coroutines/Job;", "lastRecvTime", "linkJob", "localIp", "msgSeq", "multicastSocket", "Ljava/net/MulticastSocket;", "recvBuilder", "Lcom/pudutech/schedulerlib/proto/SchedulerInfo$MulticastProtocol$Builder;", "kotlin.jvm.PlatformType", "getRecvBuilder", "()Lcom/pudutech/schedulerlib/proto/SchedulerInfo$MulticastProtocol$Builder;", "recvJob", "runnable", "", "sendBuilder", "getSendBuilder", "sendPacket", "Ljava/net/DatagramPacket;", "getSendPacket", "()Ljava/net/DatagramPacket;", "checkWifi", "closeMulticast", "", "closeMulticast$schedulerlib_release", "getMulticastAddress", "getMulticastAddress$schedulerlib_release", "joinGroup", "keepAliveThread", "parseMsg", "dataPackage", "recvThread", "resetAddress", "id", "resetAddress$schedulerlib_release", "restart", "new_address", "sendMsg", "msgId", "originIp", "start", InternalConstant.KEY_APP, "connectStateListener", "Lcom/pudutech/schedulerlib/ScheduleController$OnConnectStateListener;", "start$schedulerlib_release", "startTasks", "MSGID", "MultiCallBack", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MulticastConnection {
    private Context application;
    private MultiCallBack callBack;
    private int clearTimes;
    private Job keepJob;
    private long lastRecvTime;
    private Job linkJob;
    private long msgSeq;
    private MulticastSocket multicastSocket;
    private Job recvJob;
    private boolean runnable;
    private final String TAG = "multiconn";
    private String MULTICAST_ADDRESS = "230.10.10.10";
    private String localIp = "0.0.0.0";
    private final long KEEP_ALIVE_TIME = SolicitService.CAMERA_OPEN_TIME_OUT;
    private final int RECV_TIMEOUT = (int) SolicitService.CAMERA_OPEN_TIME_OUT;
    private final MulticastConnection$MulticastScope$1 MulticastScope = new MulticastConnection$MulticastScope$1();
    private final SchedulerInfo.MulticastProtocol.Builder recvBuilder = SchedulerInfo.MulticastProtocol.newBuilder();
    private final SchedulerInfo.MulticastProtocol.Builder sendBuilder = SchedulerInfo.MulticastProtocol.newBuilder();
    private final DatagramPacket sendPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(this.MULTICAST_ADDRESS), 11201);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: MulticastConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/MulticastConnection$MSGID;", "", "(Ljava/lang/String;I)V", "UNKNOWN_MSG", "ONLINE", "ONLINE_REBACK", "KEEPALIVE", "OFFLINE", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public enum MSGID {
        UNKNOWN_MSG,
        ONLINE,
        ONLINE_REBACK,
        KEEPALIVE,
        OFFLINE
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: MulticastConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/MulticastConnection$MultiCallBack;", "", "clearIpList", "", "removeIP", "ip", "", "sendIP", "alive_time", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface MultiCallBack {
        void clearIpList();

        void removeIP(String ip);

        void sendIP(String ip, long alive_time);
    }

    public static final /* synthetic */ Context access$getApplication$p(MulticastConnection multicastConnection) {
        Context context = multicastConnection.application;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("application");
        }
        return context;
    }

    /* renamed from: getCallBack$schedulerlib_release, reason: from getter */
    public final MultiCallBack getCallBack() {
        return this.callBack;
    }

    public final void setCallBack$schedulerlib_release(MultiCallBack multiCallBack) {
        this.callBack = multiCallBack;
    }

    public final void start$schedulerlib_release(Context app, ScheduleController.OnConnectStateListener connectStateListener) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(connectStateListener, "connectStateListener");
        this.application = app;
        this.runnable = true;
        SharedPreferences sharedPreferences = app.getSharedPreferences("mirsdk", 0);
        int i = sharedPreferences.getInt(ScheduleConstant.PREFERENCE_KEY, -1);
        if (i == -1) {
            i = SpUtils.get(app, ScheduleConstant.PREFERENCE_KEY, 1);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ScheduleConstant.PREFERENCE_KEY, i);
            edit.apply();
        }
        this.MULTICAST_ADDRESS = ScheduleConstant.INSTANCE.getMULTICAST_ADDRESS_LIST()[i];
        Pdlog.m3275i(this.TAG, "multicast connect start with address " + this.MULTICAST_ADDRESS);
        this.msgSeq = 0L;
        this.clearTimes = 0;
        this.lastRecvTime = SystemClock.elapsedRealtime();
        if (joinGroup()) {
            Pdlog.m3275i(this.TAG, "finish join group, address " + this.MULTICAST_ADDRESS);
            connectStateListener.onSuccessful();
            startTasks();
            return;
        }
        Pdlog.m3275i(this.TAG, "First join group failed, re-join test");
        connectStateListener.onFailure();
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.MulticastScope, null, null, new MulticastConnection$start$1(this, connectStateListener, null), 3, null);
        this.linkJob = launch$default;
    }

    public final void resetAddress$schedulerlib_release(int id) {
        Pdlog.m3275i(this.TAG, "reset multicast address, " + ScheduleConstant.INSTANCE.getMULTICAST_ADDRESS_LIST()[id] + ", current address " + this.MULTICAST_ADDRESS);
        restart(ScheduleConstant.INSTANCE.getMULTICAST_ADDRESS_LIST()[id]);
    }

    public final void closeMulticast$schedulerlib_release() {
        this.runnable = false;
        BuildersKt__BuildersKt.runBlocking$default(null, new MulticastConnection$closeMulticast$1(this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void restart$default(MulticastConnection multicastConnection, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = multicastConnection.MULTICAST_ADDRESS;
        }
        multicastConnection.restart(str);
    }

    private final void restart(String new_address) {
        String str = this.MULTICAST_ADDRESS;
        this.MULTICAST_ADDRESS = new_address;
        if (this.runnable) {
            BuildersKt__Builders_commonKt.launch$default(this.MulticastScope, null, null, new MulticastConnection$restart$1(this, str, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean joinGroup() {
        try {
            if (!checkWifi()) {
                return false;
            }
            InetAddress byName = InetAddress.getByName(this.MULTICAST_ADDRESS);
            if (this.multicastSocket != null) {
                MulticastSocket multicastSocket = this.multicastSocket;
                Boolean valueOf = multicastSocket != null ? Boolean.valueOf(multicastSocket.isClosed()) : null;
                if (valueOf == null) {
                    Intrinsics.throwNpe();
                }
                if (!valueOf.booleanValue()) {
                    Pdlog.m3273d(this.TAG, "socket not closed, first close socket, leave group " + this.MULTICAST_ADDRESS);
                    MulticastSocket multicastSocket2 = this.multicastSocket;
                    if (multicastSocket2 != null) {
                        multicastSocket2.leaveGroup(byName);
                    }
                    MulticastSocket multicastSocket3 = this.multicastSocket;
                    if (multicastSocket3 != null) {
                        multicastSocket3.close();
                    }
                }
            }
            Pdlog.m3273d(this.TAG, "socket should recreated");
            MulticastSocket multicastSocket4 = new MulticastSocket(11201);
            this.multicastSocket = multicastSocket4;
            if (multicastSocket4 != null) {
                multicastSocket4.setReceiveBufferSize(256);
            }
            MulticastSocket multicastSocket5 = this.multicastSocket;
            if (multicastSocket5 != null) {
                multicastSocket5.setSendBufferSize(64);
            }
            MulticastSocket multicastSocket6 = this.multicastSocket;
            if (multicastSocket6 != null) {
                multicastSocket6.setSoTimeout(this.RECV_TIMEOUT);
            }
            MulticastSocket multicastSocket7 = this.multicastSocket;
            if (multicastSocket7 != null) {
                multicastSocket7.joinGroup(byName);
            }
            Pdlog.m3273d(this.TAG, "finish join group " + this.MULTICAST_ADDRESS);
            return true;
        } catch (Exception e) {
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("udp open failed,force close socket, please check your network connection.");
            sb.append(e.getLocalizedMessage());
            sb.append(": ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb.append(ArraysKt.contentDeepToString(stackTrace));
            Pdlog.m3277w(str, sb.toString());
            MulticastSocket multicastSocket8 = this.multicastSocket;
            if (multicastSocket8 != null) {
                Boolean valueOf2 = multicastSocket8 != null ? Boolean.valueOf(multicastSocket8.isClosed()) : null;
                if (valueOf2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!valueOf2.booleanValue()) {
                    MulticastSocket multicastSocket9 = this.multicastSocket;
                    if (multicastSocket9 != null) {
                        multicastSocket9.close();
                    }
                    this.multicastSocket = (MulticastSocket) null;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTasks() {
        BuildersKt__BuildersKt.runBlocking$default(null, new MulticastConnection$startTasks$1(this, null), 1, null);
        recvThread();
        keepAliveThread();
    }

    private final void recvThread() {
        Job launch$default;
        MulticastConnection$MulticastScope$1 multicastConnection$MulticastScope$1 = this.MulticastScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(multicastConnection$MulticastScope$1, multicastConnection$MulticastScope$1.getRecvContext(), null, new MulticastConnection$recvThread$1(this, null), 2, null);
        this.recvJob = launch$default;
    }

    private final void keepAliveThread() {
        Job launch$default;
        MulticastConnection$MulticastScope$1 multicastConnection$MulticastScope$1 = this.MulticastScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(multicastConnection$MulticastScope$1, multicastConnection$MulticastScope$1.getKeepContext(), null, new MulticastConnection$keepAliveThread$1(this, null), 2, null);
        this.keepJob = launch$default;
    }

    public final SchedulerInfo.MulticastProtocol.Builder getRecvBuilder() {
        return this.recvBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean parseMsg(DatagramPacket dataPackage) {
        try {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("receive data package info host address ");
            InetAddress address = dataPackage.getAddress();
            sb.append(address != null ? address.getHostAddress() : null);
            sb.append(", data length ");
            sb.append(dataPackage.getLength());
            objArr[0] = sb.toString();
            Pdlog.m3276v(str, objArr);
            if (dataPackage.getAddress() == null) {
                return false;
            }
            SchedulerInfo.MulticastProtocol.Builder builder = this.recvBuilder;
            byte[] data = dataPackage.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "dataPackage.data");
            builder.mergeFrom(ArraysKt.sliceArray(data, RangesKt.until(0, dataPackage.getLength())));
            SchedulerInfo.MulticastProtocol.Builder recvBuilder = this.recvBuilder;
            Intrinsics.checkExpressionValueIsNotNull(recvBuilder, "recvBuilder");
            if (Intrinsics.areEqual(recvBuilder.getIp(), this.localIp)) {
                return false;
            }
            SchedulerInfo.MulticastProtocol.Builder recvBuilder2 = this.recvBuilder;
            Intrinsics.checkExpressionValueIsNotNull(recvBuilder2, "recvBuilder");
            if (!Intrinsics.areEqual(recvBuilder2.getIp(), this.localIp)) {
                SchedulerInfo.MulticastProtocol.Builder recvBuilder3 = this.recvBuilder;
                Intrinsics.checkExpressionValueIsNotNull(recvBuilder3, "recvBuilder");
                int msgId = recvBuilder3.getMsgId();
                if (msgId == MSGID.ONLINE.ordinal()) {
                    int ordinal = MSGID.ONLINE_REBACK.ordinal();
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder4 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder4, "recvBuilder");
                    String ip = recvBuilder4.getIp();
                    Intrinsics.checkExpressionValueIsNotNull(ip, "recvBuilder.ip");
                    sendMsg(ordinal, ip);
                    MultiCallBack callBack = getCallBack();
                    if (callBack != null) {
                        SchedulerInfo.MulticastProtocol.Builder recvBuilder5 = this.recvBuilder;
                        Intrinsics.checkExpressionValueIsNotNull(recvBuilder5, "recvBuilder");
                        String ip2 = recvBuilder5.getIp();
                        Intrinsics.checkExpressionValueIsNotNull(ip2, "recvBuilder.ip");
                        callBack.sendIP(ip2, this.KEEP_ALIVE_TIME * 3);
                    }
                    String str2 = this.TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("receive online notify from ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder6 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder6, "recvBuilder");
                    sb2.append(recvBuilder6.getIp());
                    sb2.append(" host ");
                    InetAddress address2 = dataPackage.getAddress();
                    sb2.append(address2 != null ? address2.getHostAddress() : null);
                    sb2.append(", msg seq ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder7 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder7, "recvBuilder");
                    sb2.append(recvBuilder7.getMsgSeq());
                    objArr2[0] = sb2.toString();
                    Pdlog.m3273d(str2, objArr2);
                } else if (msgId == MSGID.ONLINE_REBACK.ordinal()) {
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder8 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder8, "recvBuilder");
                    if (recvBuilder8.getOriginIp().equals(this.localIp)) {
                        this.lastRecvTime = SystemClock.elapsedRealtime();
                        this.clearTimes = 0;
                        MultiCallBack callBack2 = getCallBack();
                        if (callBack2 != null) {
                            SchedulerInfo.MulticastProtocol.Builder recvBuilder9 = this.recvBuilder;
                            Intrinsics.checkExpressionValueIsNotNull(recvBuilder9, "recvBuilder");
                            String ip3 = recvBuilder9.getIp();
                            Intrinsics.checkExpressionValueIsNotNull(ip3, "recvBuilder.ip");
                            callBack2.sendIP(ip3, this.KEEP_ALIVE_TIME * 3);
                        }
                        String str3 = this.TAG;
                        Object[] objArr3 = new Object[1];
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("receive online reback from ");
                        SchedulerInfo.MulticastProtocol.Builder recvBuilder10 = this.recvBuilder;
                        Intrinsics.checkExpressionValueIsNotNull(recvBuilder10, "recvBuilder");
                        sb3.append(recvBuilder10.getIp());
                        sb3.append(" host ");
                        InetAddress address3 = dataPackage.getAddress();
                        sb3.append(address3 != null ? address3.getHostAddress() : null);
                        sb3.append(", msg seq ");
                        SchedulerInfo.MulticastProtocol.Builder recvBuilder11 = this.recvBuilder;
                        Intrinsics.checkExpressionValueIsNotNull(recvBuilder11, "recvBuilder");
                        sb3.append(recvBuilder11.getMsgSeq());
                        objArr3[0] = sb3.toString();
                        Pdlog.m3273d(str3, objArr3);
                    }
                } else if (msgId == MSGID.KEEPALIVE.ordinal()) {
                    MultiCallBack callBack3 = getCallBack();
                    if (callBack3 != null) {
                        SchedulerInfo.MulticastProtocol.Builder recvBuilder12 = this.recvBuilder;
                        Intrinsics.checkExpressionValueIsNotNull(recvBuilder12, "recvBuilder");
                        String ip4 = recvBuilder12.getIp();
                        Intrinsics.checkExpressionValueIsNotNull(ip4, "recvBuilder.ip");
                        callBack3.sendIP(ip4, this.KEEP_ALIVE_TIME * 3);
                    }
                    String str4 = this.TAG;
                    Object[] objArr4 = new Object[1];
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("receive keepalive msg from ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder13 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder13, "recvBuilder");
                    sb4.append(recvBuilder13.getIp());
                    sb4.append(" host ");
                    InetAddress address4 = dataPackage.getAddress();
                    sb4.append(address4 != null ? address4.getHostAddress() : null);
                    sb4.append(", msg seq ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder14 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder14, "recvBuilder");
                    sb4.append(recvBuilder14.getMsgSeq());
                    objArr4[0] = sb4.toString();
                    Pdlog.m3273d(str4, objArr4);
                } else if (msgId == MSGID.OFFLINE.ordinal()) {
                    MultiCallBack callBack4 = getCallBack();
                    if (callBack4 != null) {
                        SchedulerInfo.MulticastProtocol.Builder recvBuilder15 = this.recvBuilder;
                        Intrinsics.checkExpressionValueIsNotNull(recvBuilder15, "recvBuilder");
                        String ip5 = recvBuilder15.getIp();
                        Intrinsics.checkExpressionValueIsNotNull(ip5, "recvBuilder.ip");
                        callBack4.removeIP(ip5);
                    }
                    String str5 = this.TAG;
                    Object[] objArr5 = new Object[1];
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("receive remove ip msg from ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder16 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder16, "recvBuilder");
                    sb5.append(recvBuilder16.getIp());
                    sb5.append(" host ");
                    InetAddress address5 = dataPackage.getAddress();
                    sb5.append(address5 != null ? address5.getHostAddress() : null);
                    sb5.append(", msg seq ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder17 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder17, "recvBuilder");
                    sb5.append(recvBuilder17.getMsgSeq());
                    objArr5[0] = sb5.toString();
                    Pdlog.m3273d(str5, objArr5);
                } else {
                    String str6 = this.TAG;
                    Object[] objArr6 = new Object[1];
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("not waited msg ");
                    MSGID[] values = MSGID.values();
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder18 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder18, "recvBuilder");
                    sb6.append(values[recvBuilder18.getMsgId()].name());
                    sb6.append(", ip ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder19 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder19, "recvBuilder");
                    sb6.append(recvBuilder19.getIp());
                    sb6.append(" host ");
                    InetAddress address6 = dataPackage.getAddress();
                    sb6.append(address6 != null ? address6.getHostAddress() : null);
                    sb6.append(", msg seq ");
                    SchedulerInfo.MulticastProtocol.Builder recvBuilder20 = this.recvBuilder;
                    Intrinsics.checkExpressionValueIsNotNull(recvBuilder20, "recvBuilder");
                    sb6.append(recvBuilder20.getMsgSeq());
                    objArr6[0] = sb6.toString();
                    Pdlog.m3273d(str6, objArr6);
                }
                this.lastRecvTime = SystemClock.elapsedRealtime();
                this.clearTimes = 0;
            }
            return true;
        } catch (Exception e) {
            String str7 = this.TAG;
            StringBuilder sb7 = new StringBuilder();
            sb7.append("parse msg error: ");
            sb7.append(e.getLocalizedMessage());
            sb7.append(": ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb7.append(ArraysKt.contentDeepToString(stackTrace));
            Pdlog.m3277w(str7, sb7.toString());
            return false;
        }
    }

    public final SchedulerInfo.MulticastProtocol.Builder getSendBuilder() {
        return this.sendBuilder;
    }

    public final DatagramPacket getSendPacket() {
        return this.sendPacket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean sendMsg$default(MulticastConnection multicastConnection, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "";
        }
        return multicastConnection.sendMsg(i, str);
    }

    private final boolean sendMsg(int msgId, String originIp) {
        SchedulerInfo.MulticastProtocol.Builder sendBuilder = this.sendBuilder;
        Intrinsics.checkExpressionValueIsNotNull(sendBuilder, "sendBuilder");
        sendBuilder.setMsgId(msgId);
        SchedulerInfo.MulticastProtocol.Builder sendBuilder2 = this.sendBuilder;
        Intrinsics.checkExpressionValueIsNotNull(sendBuilder2, "sendBuilder");
        sendBuilder2.setIp(this.localIp);
        SchedulerInfo.MulticastProtocol.Builder sendBuilder3 = this.sendBuilder;
        Intrinsics.checkExpressionValueIsNotNull(sendBuilder3, "sendBuilder");
        sendBuilder3.setOriginIp(originIp);
        SchedulerInfo.MulticastProtocol.Builder sendBuilder4 = this.sendBuilder;
        Intrinsics.checkExpressionValueIsNotNull(sendBuilder4, "sendBuilder");
        sendBuilder4.setMsgSeq(this.msgSeq);
        this.msgSeq++;
        byte[] byteArray = this.sendBuilder.build().toByteArray();
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("multicast send msg: ");
        sb.append(MSGID.values()[msgId].name());
        sb.append(", from ");
        sb.append(this.localIp);
        sb.append(" to ");
        sb.append(originIp);
        sb.append(" seq ");
        SchedulerInfo.MulticastProtocol.Builder sendBuilder5 = this.sendBuilder;
        Intrinsics.checkExpressionValueIsNotNull(sendBuilder5, "sendBuilder");
        sb.append(sendBuilder5.getMsgSeq());
        Pdlog.m3276v(str, sb.toString());
        try {
            DatagramPacket datagramPacket = this.sendPacket;
            if (byteArray == null) {
                Intrinsics.throwNpe();
            }
            datagramPacket.setData(byteArray, 0, byteArray.length);
            this.sendPacket.setAddress(InetAddress.getByName(this.MULTICAST_ADDRESS));
            this.sendPacket.setPort(11201);
            MulticastSocket multicastSocket = this.multicastSocket;
            if (multicastSocket != null) {
                multicastSocket.send(this.sendPacket);
            }
            return true;
        } catch (Exception e) {
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("udp message send failed, please check your network connection. ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb2.append(ArraysKt.contentDeepToString(stackTrace));
            Pdlog.m3277w(str2, sb2.toString());
            return false;
        }
    }

    private final boolean checkWifi() {
        Object runBlocking$default;
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new MulticastConnection$checkWifi$1(this, null), 1, null);
        return ((Boolean) runBlocking$default).booleanValue();
    }

    /* renamed from: getMulticastAddress$schedulerlib_release, reason: from getter */
    public final String getMULTICAST_ADDRESS() {
        return this.MULTICAST_ADDRESS;
    }
}
