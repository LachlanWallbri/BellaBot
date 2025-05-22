package com.pudutech.schedulerlib.connection;

import androidx.core.app.NotificationCompat;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.BaseEspConnection;
import com.pudutech.schedulerlib.utils.LimitDequeue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ESPConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014*\u0001\u0004\b\u0000\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\tH\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\b\u0010\u001f\u001a\u00020\tH\u0016J\b\u0010 \u001a\u00020\u0007H\u0016J\n\u0010!\u001a\u0004\u0018\u00010\u0007H\u0016J\u0018\u0010\"\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\tH\u0016J\b\u0010$\u001a\u00020\u0018H\u0002J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\tH\u0016J\u0010\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\tH\u0016J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010,\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/ESPConnection;", "Lcom/pudutech/schedulerlib/connection/BaseEspConnection;", "()V", "ESPScope", "com/pudutech/schedulerlib/connection/ESPConnection$ESPScope$1", "Lcom/pudutech/schedulerlib/connection/ESPConnection$ESPScope$1;", "TAG", "", "baudRate", "", "callback", "Lcom/pudutech/schedulerlib/connection/ScheduleMsgReceiveInterface;", "channel", "isRunning", "", "msgQueue", "Lcom/pudutech/schedulerlib/utils/LimitDequeue;", "", "openSuccess", "parseJob", "Lkotlinx/coroutines/Job;", "recvJob", "reopenJob", "checkOpenResult", "", "connectStateListener", "Lcom/pudutech/schedulerlib/ScheduleController$OnConnectStateListener;", SpeechUtility.TAG_RESOURCE_RESULT, "version", "closeESPDevice", "espIsRunning", "getCurrentChannel", "getESPErrorCode", "getEspVersion", "openESPDevice", "high_version", "processMsg", "resetChannel", "chl", "sendMsg", NotificationCompat.CATEGORY_MESSAGE, "setBaudRate", "baud", "setMsgCallBack", "startMsgTask", "Companion", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ESPConnection implements BaseEspConnection {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Function1<? super Integer, Unit> onTestListener;
    private static int receiveIndex;
    private ScheduleMsgReceiveInterface callback;
    private boolean isRunning;
    private boolean openSuccess;
    private Job parseJob;
    private Job recvJob;
    private Job reopenJob;
    private final String TAG = "schcom";
    private int channel = 1;
    private int baudRate = 576000;
    private final LimitDequeue<byte[]> msgQueue = new LimitDequeue<>(0, 1, null);
    private final ESPConnection$ESPScope$1 ESPScope = new ESPConnection$ESPScope$1();

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public String bytesToHexString(byte[] bArr) {
        return BaseEspConnection.DefaultImpls.bytesToHexString(this, bArr);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: ESPConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R7\u0010\u0003\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/ESPConnection$Companion;", "", "()V", "onTestListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "index", "", "getOnTestListener", "()Lkotlin/jvm/functions/Function1;", "setOnTestListener", "(Lkotlin/jvm/functions/Function1;)V", "receiveIndex", "getReceiveIndex", "()I", "setReceiveIndex", "(I)V", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getReceiveIndex() {
            return ESPConnection.receiveIndex;
        }

        public final void setReceiveIndex(int i) {
            ESPConnection.receiveIndex = i;
        }

        public final Function1<Integer, Unit> getOnTestListener() {
            return ESPConnection.onTestListener;
        }

        public final void setOnTestListener(Function1<? super Integer, Unit> function1) {
            ESPConnection.onTestListener = function1;
        }
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void resetChannel(int chl) {
        synchronized (Integer.valueOf(this.channel)) {
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("reset channel, current channel ");
            sb.append(this.channel);
            sb.append(", new channel ");
            int i = chl + 1;
            sb.append(i);
            sb.append(" chl=");
            sb.append(chl);
            Pdlog.m3273d(str, sb.toString());
            this.channel = i;
            ESPScheduleNative.INSTANCE.resetChannel(this.channel);
        }
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void setBaudRate(int baud) {
        this.baudRate = baud;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void setMsgCallBack(ScheduleMsgReceiveInterface callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void openESPDevice(ScheduleController.OnConnectStateListener connectStateListener, int high_version) {
        Intrinsics.checkParameterIsNotNull(connectStateListener, "connectStateListener");
        receiveIndex = 0;
        if (this.isRunning) {
            return;
        }
        if (high_version == 2) {
            this.baudRate = 230400;
        }
        checkOpenResult(connectStateListener, ESPScheduleNative.INSTANCE.openESP(this.baudRate, high_version), high_version);
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public void closeESPDevice() {
        receiveIndex = 0;
        if (this.isRunning) {
            BuildersKt__Builders_commonKt.launch$default(this.ESPScope, null, null, new ESPConnection$closeESPDevice$1(this, null), 3, null);
            BuildersKt__BuildersKt.runBlocking$default(null, new ESPConnection$closeESPDevice$2(this, null), 1, null);
            this.openSuccess = false;
        } else {
            ESPScheduleNative.INSTANCE.closeESP();
            BuildersKt__BuildersKt.runBlocking$default(null, new ESPConnection$closeESPDevice$3(this, null), 1, null);
        }
        this.isRunning = false;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public String getEspVersion() {
        int espFirmwareVersion = ESPScheduleNative.INSTANCE.getEspFirmwareVersion();
        String bytesToHexString = bytesToHexString(new byte[]{(byte) (espFirmwareVersion >> 8), (byte) espFirmwareVersion});
        Pdlog.m3273d(this.TAG, "Esp_version:", bytesToHexString);
        return bytesToHexString;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public int sendMsg(byte[] msg) {
        int sendMsg;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        synchronized (Integer.valueOf(this.channel)) {
            sendMsg = ESPScheduleNative.INSTANCE.sendMsg(msg);
        }
        return sendMsg;
    }

    private final void checkOpenResult(ScheduleController.OnConnectStateListener connectStateListener, boolean result, int version) {
        Job launch$default;
        if (result) {
            Pdlog.m3275i(this.TAG, "open uart device, get file handler", Boolean.valueOf(result));
            if (ESPScheduleNative.INSTANCE.checkHardwareHandshake()) {
                connectStateListener.onSuccessful();
                this.isRunning = true;
                startMsgTask();
                return;
            } else {
                connectStateListener.onFailure();
                this.isRunning = false;
                return;
            }
        }
        connectStateListener.onFailure();
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.ESPScope, null, null, new ESPConnection$checkOpenResult$1(this, version, connectStateListener, null), 3, null);
        this.reopenJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startMsgTask() {
        Job launch$default;
        this.msgQueue.clear();
        processMsg();
        ESPConnection$ESPScope$1 eSPConnection$ESPScope$1 = this.ESPScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(eSPConnection$ESPScope$1, eSPConnection$ESPScope$1.getRecvContext(), null, new ESPConnection$startMsgTask$1(this, null), 2, null);
        this.recvJob = launch$default;
    }

    private final void processMsg() {
        Job launch$default;
        ESPConnection$ESPScope$1 eSPConnection$ESPScope$1 = this.ESPScope;
        launch$default = BuildersKt__Builders_commonKt.launch$default(eSPConnection$ESPScope$1, eSPConnection$ESPScope$1.getParseContext(), null, new ESPConnection$processMsg$1(this, null), 2, null);
        this.parseJob = launch$default;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    /* renamed from: espIsRunning */
    public boolean getIsEspRunning() {
        return this.isRunning && ESPScheduleNative.INSTANCE.hasHardwareResponsed() && ESPScheduleNative.INSTANCE.connectStatus();
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    /* renamed from: getCurrentChannel */
    public int getChannel() {
        int channel = ESPScheduleNative.INSTANCE.getChannel();
        Pdlog.m3273d(this.TAG, "native channel id " + channel + ", task channel id " + this.channel);
        return channel;
    }

    @Override // com.pudutech.schedulerlib.connection.BaseEspConnection
    public String getESPErrorCode() {
        int errorCode = ESPScheduleNative.INSTANCE.getErrorCode();
        return errorCode != 2 ? errorCode != 3 ? errorCode != 4 ? errorCode != 5 ? "" : "NoData" : "NoHandshake" : "ErrCRC" : "NoDevice";
    }
}
