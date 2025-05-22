package com.pudutech.lidar.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.base.EthernetLidarAdapter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: EthernetLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001.B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020'H\u0016J\b\u0010)\u001a\u00020'H\u0016J\b\u0010*\u001a\u00020'H\u0016J\b\u0010+\u001a\u00020'H\u0016J\u0010\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020\u0014H\u0002R\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u0000X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/lidar/base/EthernetLidarAdapter;", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "context", "Landroid/content/Context;", "version", "Lcom/pudutech/lidar/LidarVersion;", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "(Landroid/content/Context;Lcom/pudutech/lidar/LidarVersion;Lcom/pudutech/lidar/LidarAdapterCallback;)V", "MESSAGE_FROM_ETHERNET", "", "getMESSAGE_FROM_ETHERNET", "()I", "ethernetThread", "Lcom/pudutech/lidar/base/EthernetLidarAdapter$EthernetThread;", "getEthernetThread", "()Lcom/pudutech/lidar/base/EthernetLidarAdapter$EthernetThread;", "setEthernetThread", "(Lcom/pudutech/lidar/base/EthernetLidarAdapter$EthernetThread;)V", "lastStartTimestamp", "", "lidar", "Lcom/pudutech/lidar/base/EthernetLidar;", "getLidar", "()Lcom/pudutech/lidar/base/EthernetLidar;", "setLidar", "(Lcom/pudutech/lidar/base/EthernetLidar;)V", "parseHandler", "Landroid/os/Handler;", "getParseHandler", "()Landroid/os/Handler;", "startDelayRunnable", "Ljava/lang/Runnable;", "checkLidarServiceOK", "", "getProperty", "", TransferTable.COLUMN_KEY, "removeRunnable", "", "startLidarService", "startScan", "stopLidarService", "stopScan", "threadSleep", "ms", "EthernetThread", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EthernetLidarAdapter extends BaseLidarAdapter {
    private final int MESSAGE_FROM_ETHERNET;
    private EthernetThread ethernetThread;
    private long lastStartTimestamp;
    public EthernetLidar lidar;
    private final Handler parseHandler;
    private final Runnable startDelayRunnable;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LidarVersion.values().length];

        static {
            $EnumSwitchMapping$0[LidarVersion.ECHOX.ordinal()] = 1;
            $EnumSwitchMapping$0[LidarVersion.PandarXT.ordinal()] = 2;
            $EnumSwitchMapping$0[LidarVersion.RSLIDAR_16.ordinal()] = 3;
            $EnumSwitchMapping$0[LidarVersion.MID_70.ordinal()] = 4;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EthernetLidarAdapter(Context context, LidarVersion version, LidarAdapterCallback lidarAdapterCallback) {
        super(context, version, lidarAdapterCallback);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(version, "version");
        this.startDelayRunnable = new Runnable() { // from class: com.pudutech.lidar.base.EthernetLidarAdapter$startDelayRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                EthernetLidarAdapter.this.getLidar().startScan();
                EthernetLidarAdapter.this.removeRunnable();
            }
        };
        this.MESSAGE_FROM_ETHERNET = 256;
        this.parseHandler = new Handler(getParseDataThread().getLooper(), new Handler.Callback() { // from class: com.pudutech.lidar.base.EthernetLidarAdapter$parseHandler$1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg == null) {
                    return false;
                }
                if (msg.what == EthernetLidarAdapter.this.getMESSAGE_FROM_ETHERNET()) {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                    }
                    byte[] bArr = (byte[]) obj;
                    EthernetLidarAdapter.this.getLidar().parser(bArr);
                    Pdlog.m3276v(EthernetLidarAdapter.this.getTAG(), "receive size=" + bArr.length + " from ethernet");
                } else {
                    Pdlog.m3276v(EthernetLidarAdapter.this.getTAG(), "no need parse data. msg.what=" + msg.what);
                }
                return true;
            }
        });
    }

    public final EthernetLidar getLidar() {
        EthernetLidar ethernetLidar = this.lidar;
        if (ethernetLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        return ethernetLidar;
    }

    public final void setLidar(EthernetLidar ethernetLidar) {
        Intrinsics.checkParameterIsNotNull(ethernetLidar, "<set-?>");
        this.lidar = ethernetLidar;
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startLidarService() {
        Pdlog.m3275i(getTAG(), "startLidarService");
        if (this.ethernetThread != null) {
            Pdlog.m3277w(getTAG(), "lidar service has been started");
        }
        EthernetThread ethernetThread = new EthernetThread();
        this.ethernetThread = ethernetThread;
        if (ethernetThread != null) {
            ethernetThread.setName("ethernetThread");
        }
        EthernetThread ethernetThread2 = this.ethernetThread;
        if (ethernetThread2 == null) {
            Intrinsics.throwNpe();
        }
        ethernetThread2.start();
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopLidarService() {
        Pdlog.m3275i(getTAG(), "stopLidarService");
        try {
            stopScan();
            EthernetThread ethernetThread = this.ethernetThread;
            if (ethernetThread != null) {
                ethernetThread.setRunning(false);
            }
            EthernetThread ethernetThread2 = this.ethernetThread;
            if (ethernetThread2 != null) {
                ethernetThread2.interrupt();
            }
        } catch (Exception e) {
            Pdlog.m3274e(getTAG(), String.valueOf(e));
        }
        this.ethernetThread = (EthernetThread) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeRunnable() {
        getControlHandler().removeCallbacks(this.startDelayRunnable);
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startScan() {
        Pdlog.m3275i(getTAG(), "startScan. " + getLidarAdapterCallback());
        EthernetThread ethernetThread = this.ethernetThread;
        if (ethernetThread == null || !ethernetThread.getIsDeviceConnected()) {
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("lidar device not connect yet. try it later. ");
            EthernetThread ethernetThread2 = this.ethernetThread;
            sb.append(ethernetThread2 != null ? Boolean.valueOf(ethernetThread2.getIsDeviceConnected()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3277w(tag, objArr);
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.lastStartTimestamp;
        EthernetLidar ethernetLidar = this.lidar;
        if (ethernetLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (elapsedRealtime < ethernetLidar.getScanCMDValidInterval_ms()) {
            this.lastStartTimestamp = SystemClock.elapsedRealtime();
            String tag2 = getTAG();
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("start scan cmd interval is too short < ");
            EthernetLidar ethernetLidar2 = this.lidar;
            if (ethernetLidar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            sb2.append(ethernetLidar2.getScanCMDValidInterval_ms());
            sb2.append(". not allow it. try it later");
            objArr2[0] = sb2.toString();
            Pdlog.m3277w(tag2, objArr2);
            return;
        }
        this.lastStartTimestamp = SystemClock.elapsedRealtime();
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.EthernetLidarAdapter$startScan$1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable;
                EthernetLidarAdapter.EthernetThread ethernetThread3 = EthernetLidarAdapter.this.getEthernetThread();
                if (ethernetThread3 != null) {
                    ethernetThread3.setKeepScan(true);
                }
                EthernetLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(true);
                Handler controlHandler = EthernetLidarAdapter.this.getControlHandler();
                runnable = EthernetLidarAdapter.this.startDelayRunnable;
                controlHandler.postDelayed(runnable, EthernetLidarAdapter.this.getLidar().getPowerOnDelay_ms());
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopScan() {
        Pdlog.m3275i(getTAG(), "stopScan. " + getLidarAdapterCallback());
        this.lastStartTimestamp = 0L;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.EthernetLidarAdapter$stopScan$1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable;
                EthernetLidarAdapter.EthernetThread ethernetThread = EthernetLidarAdapter.this.getEthernetThread();
                if (ethernetThread != null) {
                    ethernetThread.setKeepScan(false);
                }
                Handler controlHandler = EthernetLidarAdapter.this.getControlHandler();
                runnable = EthernetLidarAdapter.this.startDelayRunnable;
                controlHandler.removeCallbacks(runnable);
                EthernetLidarAdapter.this.getLidar().stopScan();
                EthernetLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(false);
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public boolean checkLidarServiceOK() {
        EthernetThread ethernetThread = this.ethernetThread;
        if (ethernetThread != null) {
            return ethernetThread.getIsDeviceConnected();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final EthernetThread getEthernetThread() {
        return this.ethernetThread;
    }

    protected final void setEthernetThread(EthernetThread ethernetThread) {
        this.ethernetThread = ethernetThread;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: EthernetLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R$\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/lidar/base/EthernetLidarAdapter$EthernetThread;", "Ljava/lang/Thread;", "(Lcom/pudutech/lidar/base/EthernetLidarAdapter;)V", "isDeviceConnected", "", "()Z", "setDeviceConnected", "(Z)V", ES6Iterator.VALUE_PROPERTY, "isRunning", "setRunning", "keepScan", "getKeepScan", "setKeepScan", "run", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final class EthernetThread extends Thread {
        private boolean isDeviceConnected;
        private boolean keepScan = true;
        private boolean isRunning = true;

        public EthernetThread() {
        }

        /* renamed from: isDeviceConnected, reason: from getter */
        public final boolean getIsDeviceConnected() {
            return this.isDeviceConnected;
        }

        public final void setDeviceConnected(boolean z) {
            this.isDeviceConnected = z;
        }

        public final boolean getKeepScan() {
            return this.keepScan;
        }

        public final void setKeepScan(boolean z) {
            this.keepScan = z;
            Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), "set keepScan " + z);
        }

        /* renamed from: isRunning, reason: from getter */
        public final boolean getIsRunning() {
            return this.isRunning;
        }

        public final void setRunning(boolean z) {
            this.isRunning = z;
            Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), "set EthernetThread " + z);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            int parseInt = Integer.parseInt(EthernetLidarAdapter.this.getProperty("ro.pudutech.version_code"));
            boolean z = parseInt < 204;
            Pdlog.m3273d(EthernetLidarAdapter.this.getTAG(), "versionCode=" + parseInt + ' ');
            Socket socket = (Socket) null;
            while (true) {
                if (this.isDeviceConnected || !this.isRunning) {
                    break;
                }
                Pair<Integer, String> execCommand = Tools.execCommand("ifconfig eth0 " + EthernetLidarAdapter.this.getLidar().getEth0IP() + " netmask 255.255.255.0", true);
                Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), "ifconfig " + EthernetLidarAdapter.this.getLidar().getEth0IP() + ". result " + execCommand);
                if (!z) {
                    List split$default = StringsKt.split$default((CharSequence) EthernetLidarAdapter.this.getLidar().getEth0IP(), new String[]{"."}, false, 0, 6, (Object) null);
                    String str = "ip rule add to " + ((String) split$default.get(0)) + FilenameUtils.EXTENSION_SEPARATOR + ((String) split$default.get(1)) + FilenameUtils.EXTENSION_SEPARATOR + ((String) split$default.get(2)) + ".0/24 table eth0 prio 10";
                    kotlin.Pair<Integer, String> execCommand2 = EthernetLidarRootCmd.INSTANCE.execCommand(str, true);
                    Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), str + ". reslut=" + execCommand2);
                }
                EthernetLidarAdapter.this.threadSleep(500L);
                try {
                    Socket socket2 = new Socket(EthernetLidarAdapter.this.getLidar().getSocketIP(), EthernetLidarAdapter.this.getLidar().getSocketPort());
                    try {
                        Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), "connect success. " + socket2);
                        EthernetLidarAdapter.this.getLidar().setLidarListener(EthernetLidarAdapter.this.getBaseLidarListener());
                        this.isDeviceConnected = true;
                        socket = socket2;
                        break;
                    } catch (Exception unused) {
                        socket = socket2;
                    }
                } catch (Exception unused2) {
                }
                Pdlog.m3277w(EthernetLidarAdapter.this.getTAG(), "fail try again");
            }
            if (z) {
                Tools.execCommand("ifconfig eth0 down", true);
                EthernetLidarAdapter.this.threadSleep(100L);
                Tools.execCommand("ifconfig eth0 up", true);
            }
            DatagramSocket datagramSocket = new DatagramSocket(EthernetLidarAdapter.this.getLidar().getUdpPort());
            Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), "udp " + datagramSocket);
            while (this.isRunning) {
                if (!this.keepScan) {
                    EthernetLidarAdapter.this.threadSleep(50L);
                } else {
                    int udpPackageSize = EthernetLidarAdapter.this.getLidar().getUdpPackageSize();
                    byte[] bArr = new byte[udpPackageSize];
                    DatagramPacket datagramPacket = new DatagramPacket(bArr, udpPackageSize);
                    datagramSocket.receive(datagramPacket);
                    Pdlog.m3275i(EthernetLidarAdapter.this.getTAG(), "receive " + datagramPacket.getLength());
                    EthernetLidarAdapter.this.getParseHandler().obtainMessage(EthernetLidarAdapter.this.getMESSAGE_FROM_ETHERNET(), bArr).sendToTarget();
                }
            }
            try {
                datagramSocket.close();
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception unused3) {
            }
        }
    }

    /* compiled from: EthernetLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/lidar/base/EthernetLidarAdapter$ParserThread;", "Ljava/lang/Thread;", "(Lcom/pudutech/lidar/base/EthernetLidarAdapter;)V", "run", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    protected final class ParserThread extends Thread {
        public ParserThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (EthernetLidarAdapter.this.getEthernetThread() != null) {
                EthernetThread ethernetThread = EthernetLidarAdapter.this.getEthernetThread();
                if (ethernetThread == null) {
                    Intrinsics.throwNpe();
                }
                if (!ethernetThread.getIsRunning()) {
                    return;
                }
                EthernetThread ethernetThread2 = EthernetLidarAdapter.this.getEthernetThread();
                if (ethernetThread2 == null) {
                    Intrinsics.throwNpe();
                }
                if (ethernetThread2.getKeepScan()) {
                    EthernetLidar lidar = EthernetLidarAdapter.this.getLidar();
                    Object take = EthernetLidarAdapter.this.getDataQueue().take();
                    Intrinsics.checkExpressionValueIsNotNull(take, "dataQueue.take()");
                    lidar.parser((byte[]) take);
                }
            }
        }
    }

    public final int getMESSAGE_FROM_ETHERNET() {
        return this.MESSAGE_FROM_ETHERNET;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: EthernetLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/lidar/base/EthernetLidarAdapter$StatueInfoThread;", "Ljava/lang/Thread;", "(Lcom/pudutech/lidar/base/EthernetLidarAdapter;)V", "run", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    protected final class StatueInfoThread extends Thread {
        public StatueInfoThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            EthernetLidarAdapter.access$receiveStatusInfo(EthernetLidarAdapter.this);
        }
    }

    public final Handler getParseHandler() {
        return this.parseHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void threadSleep(long ms) {
        Pdlog.m3273d(getTAG(), "threadSleep " + ms);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException unused) {
            Pdlog.m3273d(getTAG(), "interrupt threadSleep");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getProperty(String key) {
        try {
            Class<?> loadClass = getMContext().getClassLoader().loadClass("android.os.SystemProperties");
            Object invoke = loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class).invoke(loadClass, key);
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception e) {
            Pdlog.m3274e("SystemProperty get e:", " stack:" + e.getMessage());
            return "";
        }
    }
}
