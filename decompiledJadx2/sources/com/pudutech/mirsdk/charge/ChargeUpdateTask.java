package com.pudutech.mirsdk.charge;

import android.os.SystemClock;
import android.util.Log;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.config.SDKConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ChargeUpdateTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001c*\u0001\b\u0018\u0000 @2\u00020\u0001:\u0004@ABCB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150#2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u001cH\u0002J\b\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020(H\u0002J\b\u0010*\u001a\u00020(H\u0002J\b\u0010+\u001a\u00020(H\u0002J\b\u0010,\u001a\u00020(H\u0002J\b\u0010-\u001a\u00020(H\u0002J\u0006\u0010.\u001a\u00020\u0006J\u000e\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u001cJ\b\u00101\u001a\u00020\u0015H\u0002J\b\u00102\u001a\u00020\u0015H\u0002J\b\u00103\u001a\u00020\u0015H\u0002J\b\u00104\u001a\u00020(H\u0002J \u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u001c2\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u0006H\u0002J \u00109\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u001c2\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u0006H\u0002J\u0010\u0010:\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u001cH\u0002J\u0018\u0010;\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u0006H\u0002J\b\u0010<\u001a\u00020\u0015H\u0002J\b\u0010=\u001a\u00020(H\u0002J\u0006\u0010>\u001a\u00020(J\u0006\u0010?\u001a\u00020(R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeUpdateTask;", "", "mac", "", "(Ljava/lang/String;)V", "blockIndex", "", "callback", "com/pudutech/mirsdk/charge/ChargeUpdateTask$callback$1", "Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$callback$1;", "chargePileUpdateListener", "Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$onChargePileUpdateListener;", "getChargePileUpdateListener", "()Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$onChargePileUpdateListener;", "setChargePileUpdateListener", "(Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$onChargePileUpdateListener;)V", "curUpdateStep", "fileInputStream", "Ljava/io/InputStream;", "fileSize", "isRunningEnable", "", "isTestFile", "getMac", "()Ljava/lang/String;", "reTryConnectCount", "receiveQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "", "recvBytesBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "targetPath", "targetVersion", "checkIsInMode", "Lkotlin/Pair;", "mode", "checkVersion", "receiveData", "doAfterConnectTask", "", "doCheckVersionAfterTask", "doCheckVersionBeforeTask", "doRequestAPPModeTask", "doRequestIAPModeTask", "doUpdateTask", "getCurUpdateStep", "parseMessage", "data", "readyUpdateInfo", "requestToAPPMode", "requestToIAPMode", "resetIdle", "sendPackage", SpeechEvent.KEY_EVENT_TTS_BUFFER, "len", "index", "sendPackageCrc32Info", "sendPackageData", "sendPackageInfo", "sendUpdateTotalInfo", "startConnectChargePile", "startUpdateMcu", "stopTask", "Companion", "UpdateErrorCode", "UpdateStep", "onChargePileUpdateListener", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeUpdateTask {
    public static final int MODE_APP = 0;
    public static final int MODE_IAP = 1;
    private static final long READ_WAIT_CHK_VERSION_TIME_MS = 500;
    public static final String TAG = "ChargeUpdateTask";
    public static final String TEST_PATH = "/sdcard/pudu/bluetooth_charge_bin";
    public static final int TRY_TIMES = 3;
    public static final int WAIT_CHK_VERSION_TIME_MS = 1000;
    private int blockIndex;
    private ChargeUpdateTask$callback$1 callback;
    private onChargePileUpdateListener chargePileUpdateListener;
    private volatile int curUpdateStep;
    private InputStream fileInputStream;
    private int fileSize;
    private volatile boolean isRunningEnable;
    private boolean isTestFile;
    private final String mac;
    private volatile int reTryConnectCount;
    private final ArrayBlockingQueue<byte[]> receiveQueue;
    private final ByteBuffer recvBytesBuffer;
    private String targetPath;
    private byte[] targetVersion;

    /* compiled from: ChargeUpdateTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H&¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$onChargePileUpdateListener;", "", "onCheckingVersionBefore", "", "mac", "", "onConnectingDevice", "onError", "error", "", "describe", "onRequestToAPPMode", "onRequestToIAPMode", "onSendingPack", "total", "progress", "onUpdateSuccess", "targetVersion", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface onChargePileUpdateListener {
        void onCheckingVersionBefore(String mac);

        void onConnectingDevice(String mac);

        void onError(String mac, int error, String describe);

        void onRequestToAPPMode(String mac);

        void onRequestToIAPMode(String mac);

        void onSendingPack(String mac, int total, int progress);

        void onUpdateSuccess(String mac, String targetVersion);
    }

    public ChargeUpdateTask(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.mac = mac;
        this.curUpdateStep = -1;
        this.callback = new ChargeUpdateTask$callback$1(this, this.mac);
        this.receiveQueue = new ArrayBlockingQueue<>(512);
        this.targetPath = "";
        this.recvBytesBuffer = ByteBuffer.allocate(512);
    }

    public final String getMac() {
        return this.mac;
    }

    /* compiled from: ChargeUpdateTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$UpdateStep;", "", "()V", "CheckVersionAfter", "", "CheckVersionBefore", "Idle", "RequestToAPP", "RequestToIAP", "SendData", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class UpdateStep {
        public static final int CheckVersionAfter = 4;
        public static final int CheckVersionBefore = 0;
        public static final UpdateStep INSTANCE = new UpdateStep();
        public static final int Idle = -1;
        public static final int RequestToAPP = 3;
        public static final int RequestToIAP = 1;
        public static final int SendData = 2;

        private UpdateStep() {
        }
    }

    public final void startUpdateMcu() {
        if (this.curUpdateStep != -1) {
            Pdlog.m3273d(TAG, "is ready in update");
            return;
        }
        if (!readyUpdateInfo()) {
            Pdlog.m3273d(TAG, "readyUpdateInfo fail");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$startUpdateMcu$1(this, null), 2, null);
            return;
        }
        Pdlog.m3273d(TAG, "start update work targetPath:" + this.targetPath);
        this.isRunningEnable = true;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeUpdateTask$startUpdateMcu$2(this, null), 3, null);
    }

    public final void stopTask() {
        resetIdle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetIdle() {
        this.isRunningEnable = false;
        this.curUpdateStep = -1;
        BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(this.callback);
        BluetoothBleHelper.INSTANCE.disconnectDevice(this.mac);
    }

    public final int getCurUpdateStep() {
        return this.curUpdateStep;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean readyUpdateInfo() {
        String[] list;
        StringBuilder sb;
        String str;
        File file = new File(TEST_PATH);
        if (file.exists() && file.isDirectory()) {
            String[] list2 = file.list();
            Intrinsics.checkExpressionValueIsNotNull(list2, "testFile.list()");
            if (!(list2.length == 0)) {
                this.isTestFile = true;
                list = file.list();
                if (list != null) {
                    for (String fileName : list) {
                        Intrinsics.checkExpressionValueIsNotNull(fileName, "fileName");
                        if (StringsKt.startsWith$default(fileName, "PDCharge-", false, 2, (Object) null)) {
                            List split$default = StringsKt.split$default((CharSequence) fileName, new String[]{".", "-"}, false, 0, 6, (Object) null);
                            if (split$default.size() == 3) {
                                List split$default2 = StringsKt.split$default((CharSequence) split$default.get(1), new String[]{"_"}, false, 0, 6, (Object) null);
                                if (split$default2.size() == 3) {
                                    this.targetVersion = new byte[]{Byte.parseByte((String) split$default2.get(0)), Byte.parseByte((String) split$default2.get(1)), Byte.parseByte((String) split$default2.get(2))};
                                    if (this.isTestFile) {
                                        sb = new StringBuilder();
                                        str = "/sdcard/pudu/bluetooth_charge_bin/";
                                    } else {
                                        sb = new StringBuilder();
                                        str = "bluetooth_charge/";
                                    }
                                    sb.append(str);
                                    sb.append(fileName);
                                    this.targetPath = sb.toString();
                                    return true;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                return false;
            }
        }
        this.isTestFile = false;
        list = SDKConfig.INSTANCE.getProcessContext().getAssets().list("bluetooth_charge");
        if (list != null) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doAfterConnectTask() {
        int i = this.curUpdateStep;
        if (i == 0) {
            doCheckVersionBeforeTask();
            return;
        }
        if (i == 1 || i == 2) {
            doRequestIAPModeTask();
        } else if (i == 3 || i == 4) {
            doRequestAPPModeTask();
        }
    }

    private final void doCheckVersionBeforeTask() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doCheckVersionBeforeTask$1(this, null), 2, null);
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doCheckVersionBeforeTask$4(this, null), 2, null);
                return;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "doCheckVersionBeforeTask not running enable");
                return;
            }
            this.receiveQueue.clear();
            ChargeMessageCenter.INSTANCE.sendVersionReqInUpdate(this.mac);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "doCheckVersionBeforeTask receive is null");
                } else {
                    Pdlog.m3273d(TAG, "doCheckVersionBeforeTask receive " + CommonKt.toHexString(poll));
                    if (poll.length >= 4 && poll[0] == ((byte) 3)) {
                        if (checkVersion(poll)) {
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doCheckVersionBeforeTask$3(this, null), 2, null);
                            return;
                        }
                        this.curUpdateStep = 1;
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doCheckVersionBeforeTask$2(this, null), 2, null);
                        doRequestIAPModeTask();
                        return;
                    }
                }
            }
        }
    }

    private final void doRequestIAPModeTask() {
        Pair<Boolean, Boolean> checkIsInMode = checkIsInMode(1);
        if (checkIsInMode.getFirst().booleanValue()) {
            if (checkIsInMode.getSecond().booleanValue()) {
                doUpdateTask();
                return;
            }
            if (requestToIAPMode()) {
                if (!this.isRunningEnable) {
                    Pdlog.m3273d(TAG, "doRequestIAPModeTask not running enable");
                    return;
                }
                if (this.curUpdateStep == 1) {
                    this.reTryConnectCount = 0;
                }
                this.curUpdateStep = 2;
                BluetoothBleHelper.INSTANCE.disconnectDevice(this.mac);
                return;
            }
            return;
        }
        Pdlog.m3274e(TAG, "not reply message");
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doRequestIAPModeTask$1(this, null), 2, null);
    }

    private final void doUpdateTask() {
        if (!this.isRunningEnable) {
            return;
        }
        this.curUpdateStep = 2;
        this.blockIndex = 0;
        if (!sendUpdateTotalInfo()) {
            if (this.isRunningEnable) {
                Pdlog.m3273d(TAG, "send update total info fail");
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doUpdateTask$1(this, null), 2, null);
                return;
            }
            return;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        byte[] bArr = new byte[1024];
        Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = 0;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doUpdateTask$2(this, null), 2, null);
        while (true) {
            InputStream inputStream = this.fileInputStream;
            Integer valueOf = inputStream != null ? Integer.valueOf(inputStream.read(bArr)) : null;
            intRef.element = valueOf != null ? valueOf.intValue() : -1;
            if (valueOf == null || valueOf.intValue() != -1) {
                if (!this.isRunningEnable) {
                    return;
                }
                if (sendPackage(bArr, intRef.element, this.blockIndex)) {
                    this.blockIndex++;
                    intRef2.element += intRef.element;
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doUpdateTask$4(this, intRef2, null), 2, null);
                } else {
                    Pdlog.m3273d(TAG, "send Pack Fail");
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doUpdateTask$5(this, null), 2, null);
                    return;
                }
            } else {
                this.curUpdateStep = 3;
                doRequestAPPModeTask();
                return;
            }
        }
    }

    private final void doRequestAPPModeTask() {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doRequestAPPModeTask$1(this, null), 2, null);
        Pair<Boolean, Boolean> checkIsInMode = checkIsInMode(0);
        if (!this.isRunningEnable) {
            Pdlog.m3273d(TAG, "doRequestAPPModeTask not running enable");
            return;
        }
        if (checkIsInMode.getFirst().booleanValue()) {
            if (checkIsInMode.getSecond().booleanValue()) {
                doCheckVersionAfterTask();
                return;
            }
            if (requestToAPPMode()) {
                if (this.curUpdateStep == 3) {
                    this.reTryConnectCount = 0;
                }
                this.curUpdateStep = 4;
                BluetoothBleHelper.INSTANCE.disconnectDevice(this.mac);
                return;
            }
            if (this.isRunningEnable) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doRequestAPPModeTask$2(this, null), 2, null);
                return;
            }
            return;
        }
        Pdlog.m3274e(TAG, "not reply message");
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doRequestAPPModeTask$3(this, null), 2, null);
    }

    private final void doCheckVersionAfterTask() {
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                return;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "doCheckVersionAfterTask not running enable");
                return;
            }
            this.receiveQueue.clear();
            ChargeMessageCenter.INSTANCE.sendVersionReqInUpdate(this.mac);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "doCheckVersionAfterTask receive is null");
                } else {
                    Pdlog.m3273d(TAG, "doCheckVersionAfterTask receive " + CommonKt.toHexString(poll));
                    if (poll.length >= 4 && poll[0] == ((byte) 3)) {
                        if (checkVersion(poll)) {
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doCheckVersionAfterTask$2(this, null), 2, null);
                            return;
                        } else {
                            Pdlog.m3273d(TAG, "doCheckVersionAfterTask fail");
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$doCheckVersionAfterTask$1(this, null), 2, null);
                            return;
                        }
                    }
                }
            }
        }
    }

    private final boolean sendUpdateTotalInfo() {
        InputStream inputStream = this.fileInputStream;
        if (inputStream != null) {
            inputStream.close();
        }
        this.fileInputStream = this.isTestFile ? new FileInputStream(this.targetPath) : SDKConfig.INSTANCE.getProcessContext().getAssets().open(this.targetPath);
        InputStream inputStream2 = this.fileInputStream;
        if (inputStream2 != null) {
            this.fileSize = 0;
            try {
                this.fileSize = inputStream2.available();
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "exception " + e.getLocalizedMessage() + " :" + Log.getStackTraceString(e));
            }
            if (this.fileSize != 0) {
                int i = 10;
                while (true) {
                    i--;
                    if (i <= 0) {
                        break;
                    }
                    if (!this.isRunningEnable) {
                        Pdlog.m3273d(TAG, "sendUpdateTotalInfo not running enable");
                        return false;
                    }
                    this.receiveQueue.clear();
                    byte[] sendTotalSize = ChargeMessageCenter.INSTANCE.sendTotalSize(this.mac, this.fileSize);
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                        byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                        if (poll == null) {
                            Pdlog.m3273d(TAG, "sendUpdateTotalInfo receive is null");
                        } else {
                            Pdlog.m3273d(TAG, "sendUpdateTotalInfo receive " + CommonKt.toHexString(poll));
                            if (poll.length >= 4 && poll[0] == ((byte) 5)) {
                                return Arrays.equals(poll, sendTotalSize);
                            }
                        }
                    }
                }
            } else {
                Pdlog.m3274e(TAG, "file size is 0");
                return false;
            }
        }
        return false;
    }

    private final boolean sendPackage(byte[] buffer, int len, int index) {
        boolean z;
        int i = 1;
        Pdlog.m3273d(TAG, "sendPackage len:" + len + " index:" + index);
        int i2 = 10;
        while (true) {
            i2--;
            if (i2 <= 0) {
                return false;
            }
            if (!this.isRunningEnable) {
                Object[] objArr = new Object[i];
                objArr[0] = "sendPackage not running enable";
                Pdlog.m3273d(TAG, objArr);
                return false;
            }
            if (!sendPackageInfo(len, index)) {
                return false;
            }
            int i3 = len / 64;
            int i4 = len % 64;
            Object[] objArr2 = new Object[i];
            objArr2[0] = "sendPackage buffer " + CommonKt.toHexString(buffer);
            Pdlog.m3273d(TAG, objArr2);
            int i5 = 0;
            while (true) {
                if (i5 >= i3) {
                    z = true;
                    break;
                }
                int i6 = i5 * 64;
                byte[] copyOfRange = ArraysKt.copyOfRange(buffer, i6, i6 + 64);
                Object[] objArr3 = new Object[i];
                objArr3[0] = "sendPackage index:" + index + " page" + i5 + " curPackData: " + CommonKt.toHexString(copyOfRange);
                Pdlog.m3273d(TAG, objArr3);
                if (!sendPackageData(copyOfRange)) {
                    z = false;
                    break;
                }
                i5++;
                i = 1;
            }
            if (z) {
                if (i4 > 0) {
                    byte[] copyOfRange2 = ArraysKt.copyOfRange(buffer, len - i4, len);
                    Pdlog.m3273d(TAG, "sendPackage index:" + index + " lastData" + i4 + " page last one curPackData: " + CommonKt.toHexString(copyOfRange2));
                    if (!sendPackageData(copyOfRange2)) {
                        z = false;
                    }
                }
                if (z) {
                    Pdlog.m3273d(TAG, "sendPackageCrc32Info len:" + len + " index:" + index + " data:" + CommonKt.toHexString(buffer));
                    if (sendPackageCrc32Info(ArraysKt.copyOfRange(buffer, 0, len), len, index)) {
                        return true;
                    }
                } else {
                    continue;
                }
            }
            i = 1;
        }
    }

    private final boolean sendPackageInfo(int len, int index) {
        Pdlog.m3273d(TAG, "sendPackageInfo len:" + len + " index:" + index);
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "sendPackageInfo not running enable");
                return false;
            }
            this.receiveQueue.clear();
            byte[] sendPackSizeInfo = ChargeMessageCenter.INSTANCE.sendPackSizeInfo(this.mac, len, index);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "sendPackageInfo receive is null");
                } else {
                    Pdlog.m3273d(TAG, "sendPackageInfo receive " + CommonKt.toHexString(poll) + " checkData" + CommonKt.toHexString(sendPackSizeInfo));
                    if (poll.length >= 5 && poll[0] == ((byte) 6)) {
                        return Arrays.equals(poll, sendPackSizeInfo);
                    }
                }
            }
        }
    }

    private final boolean sendPackageData(byte[] buffer) {
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "sendPackageData not running enable");
                return false;
            }
            this.receiveQueue.clear();
            ChargeMessageCenter.INSTANCE.sendPackData(this.mac, buffer);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "sendPackageData receive is null");
                } else {
                    Pdlog.m3273d(TAG, "sendPackageData receive " + CommonKt.toHexString(poll));
                    if (poll.length >= 2 && poll[0] == ((byte) 7)) {
                        return true;
                    }
                }
            }
        }
    }

    private final boolean sendPackageCrc32Info(byte[] buffer, int len, int index) {
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "sendPackageCrc32Info not running enable");
                return false;
            }
            this.receiveQueue.clear();
            ChargeMessageCenter.INSTANCE.sendPackCrc32Data(this.mac, buffer, index, len);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "sendPackageCrc32Info receive is null");
                } else {
                    Pdlog.m3273d(TAG, "sendPackageCrc32Info receive " + CommonKt.toHexString(poll));
                    if (poll.length >= 2 && poll[0] == ((byte) 8)) {
                        return poll[1] == ((byte) 1);
                    }
                }
            }
        }
    }

    private final boolean requestToIAPMode() {
        Pdlog.m3273d(TAG, "requestToIAPMode");
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "startConnectChargePile not running enable");
                return false;
            }
            this.receiveQueue.clear();
            ChargeMessageCenter.INSTANCE.sendDeviceToIAPMode(this.mac);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "requestToIAPMode receive is null");
                } else {
                    Pdlog.m3273d(TAG, "requestToIAPMode receive " + CommonKt.toHexString(poll));
                    if (poll.length >= 1 && poll[0] == ((byte) 1)) {
                        return true;
                    }
                }
            }
        }
    }

    private final boolean requestToAPPMode() {
        int i = 10;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            if (!this.isRunningEnable) {
                Pdlog.m3273d(TAG, "requestToAPPMode not running enable");
                return false;
            }
            this.receiveQueue.clear();
            ChargeMessageCenter.INSTANCE.sendDeviceToAPPMode(this.mac);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                if (poll == null) {
                    Pdlog.m3273d(TAG, "requestToAPPMode receive is null");
                } else {
                    Pdlog.m3273d(TAG, "requestToAPPMode receive " + CommonKt.toHexString(poll));
                    if (poll.length >= 1 && poll[0] == ((byte) 9)) {
                        return true;
                    }
                }
            }
        }
    }

    private final Pair<Boolean, Boolean> checkIsInMode(int mode) {
        int i = 10;
        while (true) {
            i--;
            if (i > 0) {
                if (!this.isRunningEnable) {
                    Pdlog.m3273d(TAG, "checkIsInMode not running enable");
                    return new Pair<>(false, false);
                }
                this.receiveQueue.clear();
                ChargeMessageCenter.INSTANCE.sendModeReq(this.mac);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                    byte[] poll = this.receiveQueue.poll(500L, TimeUnit.MILLISECONDS);
                    if (poll == null) {
                        Pdlog.m3273d(TAG, "checkIsInMode receive is null");
                    } else {
                        Pdlog.m3273d(TAG, "checkIsInMode receive " + CommonKt.toHexString(poll));
                        if (poll.length >= 2 && poll[0] == ((byte) 2)) {
                            return new Pair<>(true, Boolean.valueOf((poll[1] & 255) == mode));
                        }
                    }
                }
            } else {
                return new Pair<>(false, false);
            }
        }
    }

    private final boolean checkVersion(byte[] receiveData) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("checkVersion cur:");
        sb.append(CommonKt.toHexString(receiveData));
        sb.append(" target:");
        byte[] bArr = this.targetVersion;
        sb.append(bArr != null ? CommonKt.toHexString(bArr) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        byte[] bArr2 = this.targetVersion;
        if (bArr2 != null) {
            return Arrays.equals(ArraysKt.copyOfRange(receiveData, 1, 4), bArr2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startConnectChargePile() {
        if (!this.isRunningEnable) {
            Pdlog.m3273d(TAG, "startConnectChargePile not running enable");
            return;
        }
        BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
        BluetoothBleHelper.INSTANCE.addBluetoothBleListeners(this.callback);
        if (BluetoothBleHelper.INSTANCE.getConnectState(this.mac) == 2) {
            Pdlog.m3273d(TAG, "device already connected");
            doAfterConnectTask();
        } else {
            this.reTryConnectCount = 0;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getIO(), null, new ChargeUpdateTask$startConnectChargePile$1(this, null), 2, null);
            BluetoothBleHelper.INSTANCE.connectDevice(this.mac);
        }
    }

    public final void parseMessage(byte[] data) {
        int i;
        Intrinsics.checkParameterIsNotNull(data, "data");
        ByteBuffer byteBuffer = this.recvBytesBuffer;
        byteBuffer.put(data);
        byteBuffer.flip();
        if (byteBuffer.remaining() < ChargeMessageCenter.INSTANCE.getHEAD().length) {
            byteBuffer.position(byteBuffer.limit());
            byteBuffer.limit(byteBuffer.capacity());
            return;
        }
        while (byteBuffer.remaining() >= ChargeMessageCenter.INSTANCE.getHEAD().length) {
            int i2 = 0;
            while (i2 < ChargeMessageCenter.INSTANCE.getHEAD().length && ChargeMessageCenter.INSTANCE.getHEAD()[i2] == byteBuffer.get()) {
                i2++;
            }
            if (i2 != ChargeMessageCenter.INSTANCE.getHEAD().length) {
                if (byteBuffer.hasRemaining()) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                    byteBuffer.put(new byte[byteBuffer.capacity()]);
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                    byteBuffer.put(bArr);
                    byteBuffer.flip();
                } else {
                    byteBuffer.clear();
                    return;
                }
            } else {
                if (byteBuffer.hasRemaining()) {
                    int i3 = byteBuffer.get() & 255;
                    if (i3 == 248) {
                        Pdlog.m3274e(TAG, "command fail");
                        if (byteBuffer.hasRemaining()) {
                            byte[] bArr2 = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bArr2);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(new byte[byteBuffer.capacity()]);
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(bArr2);
                            byteBuffer.flip();
                        } else {
                            byteBuffer.position(0);
                            byteBuffer.limit(byteBuffer.capacity());
                            byteBuffer.put(new byte[byteBuffer.capacity()]);
                            byteBuffer.position(0);
                            byteBuffer.clear();
                            return;
                        }
                    } else if (i3 == 192 && byteBuffer.hasRemaining()) {
                        byteBuffer.get();
                        if (byteBuffer.hasRemaining()) {
                            byteBuffer.get();
                            if (byteBuffer.hasRemaining() && byteBuffer.remaining() > (i = byteBuffer.get() & 255)) {
                                byte[] bArr3 = new byte[i];
                                int position = byteBuffer.position();
                                byteBuffer.position(0);
                                int i4 = 0;
                                for (int i5 = 0; byteBuffer.hasRemaining() && i5 < ChargeMessageCenter.INSTANCE.getHEAD().length + i + 4; i5++) {
                                    i4 = (i4 + (byteBuffer.get() & 255)) % 256;
                                }
                                byteBuffer.position(position);
                                byteBuffer.get(bArr3);
                                int i6 = byteBuffer.get() & 255;
                                if (i4 == i6) {
                                    Pdlog.m3273d(TAG, "crc success");
                                    this.receiveQueue.offer(bArr3);
                                } else {
                                    Pdlog.m3273d(TAG, "crc fail " + i4 + "  realCrc" + i6);
                                }
                                if (byteBuffer.hasRemaining()) {
                                    byte[] bArr4 = new byte[byteBuffer.remaining()];
                                    byteBuffer.get(bArr4);
                                    byteBuffer.position(0);
                                    byteBuffer.limit(byteBuffer.capacity());
                                    byteBuffer.put(new byte[byteBuffer.capacity()]);
                                    byteBuffer.position(0);
                                    byteBuffer.limit(byteBuffer.capacity());
                                    byteBuffer.put(bArr4);
                                    byteBuffer.flip();
                                } else {
                                    byteBuffer.position(0);
                                    byteBuffer.limit(byteBuffer.capacity());
                                    byteBuffer.put(new byte[byteBuffer.capacity()]);
                                    byteBuffer.position(0);
                                    byteBuffer.clear();
                                    return;
                                }
                            }
                        }
                    }
                }
                byteBuffer.position(0);
                break;
            }
        }
        byte[] bArr5 = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr5);
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(new byte[byteBuffer.capacity()]);
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(bArr5);
    }

    public final onChargePileUpdateListener getChargePileUpdateListener() {
        return this.chargePileUpdateListener;
    }

    public final void setChargePileUpdateListener(onChargePileUpdateListener onchargepileupdatelistener) {
        this.chargePileUpdateListener = onchargepileupdatelistener;
    }

    /* compiled from: ChargeUpdateTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/charge/ChargeUpdateTask$UpdateErrorCode;", "", "()V", "Check_Version_Fail_After_Update", "", "Device_Connect_Fail", "No_Reply", "Ota_File_Fail", "Send_Data_Fail", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class UpdateErrorCode {
        public static final int Check_Version_Fail_After_Update = 3;
        public static final int Device_Connect_Fail = 1;
        public static final UpdateErrorCode INSTANCE = new UpdateErrorCode();
        public static final int No_Reply = 5;
        public static final int Ota_File_Fail = 4;
        public static final int Send_Data_Fail = 2;

        private UpdateErrorCode() {
        }
    }
}
