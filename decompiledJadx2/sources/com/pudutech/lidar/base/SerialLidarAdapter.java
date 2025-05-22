package com.pudutech.lidar.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.serialport.SerialPort;
import com.felhr.utils.HexData;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.base.SerialLidarAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u00013B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020,H\u0016J\b\u0010.\u001a\u00020,H\u0016J\b\u0010/\u001a\u00020,H\u0016J\b\u00100\u001a\u00020,H\u0016J\u0010\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020\u0010H\u0002R\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010#\u001a\b\u0018\u00010$R\u00020\u0000X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00064"}, m3961d2 = {"Lcom/pudutech/lidar/base/SerialLidarAdapter;", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "context", "Landroid/content/Context;", "version", "Lcom/pudutech/lidar/LidarVersion;", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "(Landroid/content/Context;Lcom/pudutech/lidar/LidarVersion;Lcom/pudutech/lidar/LidarAdapterCallback;)V", "MESSAGE_FROM_SERIAL_PORT", "", "getMESSAGE_FROM_SERIAL_PORT", "()I", "devName", "", "lastStartTimestamp", "", "lidar", "Lcom/pudutech/lidar/base/SerialLidar;", "getLidar", "()Lcom/pudutech/lidar/base/SerialLidar;", "setLidar", "(Lcom/pudutech/lidar/base/SerialLidar;)V", "lidarInterface", "Lcom/pudutech/lidar/base/SerialLidarInterface;", "getLidarInterface", "()Lcom/pudutech/lidar/base/SerialLidarInterface;", "parseHandler", "Landroid/os/Handler;", "getParseHandler", "()Landroid/os/Handler;", "serialPort", "Landroid/serialport/SerialPort;", "startDelayRunnable", "Ljava/lang/Runnable;", "uartThread", "Lcom/pudutech/lidar/base/SerialLidarAdapter$UARTThread;", "getUartThread", "()Lcom/pudutech/lidar/base/SerialLidarAdapter$UARTThread;", "setUartThread", "(Lcom/pudutech/lidar/base/SerialLidarAdapter$UARTThread;)V", "checkLidarServiceOK", "", "removeRunnable", "", "startLidarService", "startScan", "stopLidarService", "stopScan", "threadSleep", "ms", "UARTThread", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SerialLidarAdapter extends BaseLidarAdapter {
    private final int MESSAGE_FROM_SERIAL_PORT;
    private final String devName;
    private long lastStartTimestamp;
    public SerialLidar lidar;
    private final SerialLidarInterface lidarInterface;
    private final Handler parseHandler;
    private SerialPort serialPort;
    private final Runnable startDelayRunnable;
    private UARTThread uartThread;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialLidarAdapter(Context context, LidarVersion version, LidarAdapterCallback lidarAdapterCallback) {
        super(context, version, lidarAdapterCallback);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(version, "version");
        this.startDelayRunnable = new Runnable() { // from class: com.pudutech.lidar.base.SerialLidarAdapter$startDelayRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialLidarAdapter.this.getLidar().startScan();
                SerialLidarAdapter.this.removeRunnable();
            }
        };
        this.devName = "/dev/ttyS4";
        this.MESSAGE_FROM_SERIAL_PORT = 256;
        this.lidarInterface = new SerialLidarInterface() { // from class: com.pudutech.lidar.base.SerialLidarAdapter$lidarInterface$1
            @Override // com.pudutech.lidar.base.SerialLidarInterface
            public void send(byte[] data) {
                OutputStream outputStream;
                Intrinsics.checkParameterIsNotNull(data, "data");
                Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "lidar send " + HexData.hexToString(data));
                try {
                    SerialPort serialPort = SerialLidarAdapter.this.serialPort;
                    if (serialPort == null || (outputStream = serialPort.getOutputStream()) == null) {
                        return;
                    }
                    outputStream.write(data);
                } catch (IOException e) {
                    Pdlog.m3274e(SerialLidarAdapter.this.getTAG(), "lidar sending fail." + e);
                    SerialLidarAdapter.this.getLidarAdapterCallback().onError(new LidarError(LidarErrorType.LIDAR_INNER_ERROR, "lidar sending fail." + e));
                }
            }

            @Override // com.pudutech.lidar.base.SerialLidarInterface
            public void setDTR(boolean onOrOff) {
                Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "setDTR " + onOrOff + ". uart lidar. this setting useless");
            }
        };
        this.parseHandler = new Handler(getParseDataThread().getLooper(), new Handler.Callback() { // from class: com.pudutech.lidar.base.SerialLidarAdapter$parseHandler$1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg == null) {
                    return false;
                }
                if (msg.what == SerialLidarAdapter.this.getMESSAGE_FROM_SERIAL_PORT()) {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                    }
                    byte[] bArr = (byte[]) obj;
                    SerialLidarAdapter.this.getLidar().parser(bArr);
                    Pdlog.m3276v(SerialLidarAdapter.this.getTAG(), "receive size=" + bArr.length + " from serial ");
                } else {
                    Pdlog.m3276v(SerialLidarAdapter.this.getTAG(), "no need parse data. msg.what=" + msg.what);
                }
                return true;
            }
        });
    }

    public final SerialLidar getLidar() {
        SerialLidar serialLidar = this.lidar;
        if (serialLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        return serialLidar;
    }

    public final void setLidar(SerialLidar serialLidar) {
        Intrinsics.checkParameterIsNotNull(serialLidar, "<set-?>");
        this.lidar = serialLidar;
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startLidarService() {
        Pdlog.m3273d(getTAG(), "startLidarService");
        if (this.uartThread != null) {
            Pdlog.m3277w(getTAG(), "lidar service has been started");
        }
        UARTThread uARTThread = new UARTThread();
        this.uartThread = uARTThread;
        if (uARTThread != null) {
            uARTThread.setName("UARTThread");
        }
        UARTThread uARTThread2 = this.uartThread;
        if (uARTThread2 == null) {
            Intrinsics.throwNpe();
        }
        uARTThread2.start();
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopLidarService() {
        Pdlog.m3275i(getTAG(), "stopLidarService");
        try {
            stopScan();
            UARTThread uARTThread = this.uartThread;
            if (uARTThread != null) {
                uARTThread.setRunning(false);
            }
            UARTThread uARTThread2 = this.uartThread;
            if (uARTThread2 != null) {
                uARTThread2.interrupt();
            }
        } catch (Exception e) {
            Pdlog.m3274e(getTAG(), String.valueOf(e));
        }
        this.uartThread = (UARTThread) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeRunnable() {
        getControlHandler().removeCallbacks(this.startDelayRunnable);
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startScan() {
        Pdlog.m3275i(getTAG(), "startScan. " + getLidarAdapterCallback());
        UARTThread uARTThread = this.uartThread;
        if (uARTThread == null || !uARTThread.getIsDeviceConnected()) {
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("lidar device not connect yet. try it later. ");
            UARTThread uARTThread2 = this.uartThread;
            sb.append(uARTThread2 != null ? Boolean.valueOf(uARTThread2.getIsDeviceConnected()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3277w(tag, objArr);
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.lastStartTimestamp;
        SerialLidar serialLidar = this.lidar;
        if (serialLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (elapsedRealtime < serialLidar.getScanCMDValidInterval_ms()) {
            this.lastStartTimestamp = SystemClock.elapsedRealtime();
            String tag2 = getTAG();
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("start scan cmd interval is too short < ");
            SerialLidar serialLidar2 = this.lidar;
            if (serialLidar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            sb2.append(serialLidar2.getScanCMDValidInterval_ms());
            sb2.append(". not allow it. try it later");
            objArr2[0] = sb2.toString();
            Pdlog.m3277w(tag2, objArr2);
            return;
        }
        this.lastStartTimestamp = SystemClock.elapsedRealtime();
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.SerialLidarAdapter$startScan$1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable;
                SerialLidarAdapter.UARTThread uartThread = SerialLidarAdapter.this.getUartThread();
                if (uartThread != null) {
                    uartThread.setScanning(true);
                }
                SerialLidarAdapter.this.removeRunnable();
                SerialLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(true);
                Handler controlHandler = SerialLidarAdapter.this.getControlHandler();
                runnable = SerialLidarAdapter.this.startDelayRunnable;
                controlHandler.postDelayed(runnable, SerialLidarAdapter.this.getLidar().getPowerOnDelay_ms());
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopScan() {
        Pdlog.m3275i(getTAG(), "stopScan. " + getLidarAdapterCallback());
        this.lastStartTimestamp = 0L;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.SerialLidarAdapter$stopScan$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialLidarAdapter.UARTThread uartThread = SerialLidarAdapter.this.getUartThread();
                if (uartThread != null) {
                    uartThread.setScanning(false);
                }
                SerialLidarAdapter.this.removeRunnable();
                SerialLidarAdapter.this.getLidar().stopScan();
                SerialLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(false);
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public boolean checkLidarServiceOK() {
        UARTThread uARTThread = this.uartThread;
        if (uARTThread != null) {
            return uARTThread.getIsDeviceConnected();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final UARTThread getUartThread() {
        return this.uartThread;
    }

    protected final void setUartThread(UARTThread uARTThread) {
        this.uartThread = uARTThread;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: SerialLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R$\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000f\"\u0004\b\u0016\u0010\u0011R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/lidar/base/SerialLidarAdapter$UARTThread;", "Ljava/lang/Thread;", "(Lcom/pudutech/lidar/base/SerialLidarAdapter;)V", "BUFFER_SIZE", "", SpeechEvent.KEY_EVENT_TTS_BUFFER, "", "input", "Ljava/io/InputStream;", "getInput", "()Ljava/io/InputStream;", "setInput", "(Ljava/io/InputStream;)V", "isDeviceConnected", "", "()Z", "setDeviceConnected", "(Z)V", ES6Iterator.VALUE_PROPERTY, "isRunning", "setRunning", "isScanning", "setScanning", "lastReadTimeStamp", "", "openDevice", "read", "run", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final class UARTThread extends Thread {
        private InputStream input;
        private boolean isDeviceConnected;
        private boolean isScanning = true;
        private boolean isRunning = true;
        private final int BUFFER_SIZE = 1024;
        private final byte[] buffer = new byte[1024];
        private long lastReadTimeStamp = SystemClock.elapsedRealtime();

        public UARTThread() {
        }

        /* renamed from: isDeviceConnected, reason: from getter */
        public final boolean getIsDeviceConnected() {
            return this.isDeviceConnected;
        }

        public final void setDeviceConnected(boolean z) {
            this.isDeviceConnected = z;
        }

        /* renamed from: isScanning, reason: from getter */
        public final boolean getIsScanning() {
            return this.isScanning;
        }

        public final void setScanning(boolean z) {
            this.isScanning = z;
            Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "set keepScan " + z);
        }

        /* renamed from: isRunning, reason: from getter */
        public final boolean getIsRunning() {
            return this.isRunning;
        }

        public final void setRunning(boolean z) {
            this.isRunning = z;
            Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "set UART thread " + z);
        }

        public final InputStream getInput() {
            return this.input;
        }

        public final void setInput(InputStream inputStream) {
            this.input = inputStream;
        }

        private final boolean openDevice() {
            try {
                Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "try opening serial port." + SerialLidarAdapter.this.devName + ' ' + SerialLidarAdapter.this.getLidar().getBaudRate());
                SerialLidarAdapter.this.serialPort = new SerialPort(SerialLidarAdapter.this.devName, SerialLidarAdapter.this.getLidar().getBaudRate());
                SerialLidarAdapter.this.getLidar().setLidarListener(SerialLidarAdapter.this.getBaseLidarListener());
                this.isDeviceConnected = true;
                Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "open uart success.");
                return true;
            } catch (IOException e) {
                Pdlog.m3274e(SerialLidarAdapter.this.getTAG(), "open uart fail. " + e);
                SerialLidarAdapter.this.getLidarAdapterCallback().onError(new LidarError(LidarErrorType.OPEN_FAIL, "open UART " + SerialLidarAdapter.this.devName + " fail"));
                return false;
            }
        }

        private final boolean read() {
            InputStream inputStream = this.input;
            if (inputStream != null) {
                int read = inputStream.read(this.buffer);
                Pdlog.m3276v(SerialLidarAdapter.this.getTAG(), "read size=" + read);
                if (read > 0) {
                    byte[] bArr = new byte[read];
                    System.arraycopy(this.buffer, 0, bArr, 0, read);
                    SerialLidarAdapter.this.getParseHandler().obtainMessage(SerialLidarAdapter.this.getMESSAGE_FROM_SERIAL_PORT(), bArr).sendToTarget();
                    this.lastReadTimeStamp = SystemClock.elapsedRealtime();
                    return true;
                }
                Pdlog.m3277w(SerialLidarAdapter.this.getTAG(), "read nothing");
                if (SystemClock.elapsedRealtime() - this.lastReadTimeStamp > 5000) {
                    SerialLidarAdapter.this.getLidarAdapterCallback().onError(new LidarError(LidarErrorType.READ_NOTHING, "serial read nothing in " + (SystemClock.elapsedRealtime() - this.lastReadTimeStamp) + " ms"));
                }
            }
            return false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            while (true) {
                if (this.isDeviceConnected || !this.isRunning) {
                    break;
                }
                if (!openDevice()) {
                    SerialLidarAdapter.this.threadSleep(500L);
                    Pdlog.m3275i(SerialLidarAdapter.this.getTAG(), "try open uart again");
                } else {
                    SerialLidarAdapter.this.getLidar().setLidarInterface(SerialLidarAdapter.this.getLidarInterface());
                    SerialPort serialPort = SerialLidarAdapter.this.serialPort;
                    if (serialPort == null) {
                        Intrinsics.throwNpe();
                    }
                    this.input = serialPort.getInputStream();
                    Pdlog.m3273d(SerialLidarAdapter.this.getTAG(), "open device success. input=" + this.input);
                    SerialLidarAdapter.this.startScan();
                }
            }
            while (this.isRunning) {
                if (!this.isScanning) {
                    SerialLidarAdapter.this.threadSleep(50L);
                } else if (!read()) {
                    SerialLidarAdapter.this.threadSleep(1L);
                }
            }
            SerialPort serialPort2 = SerialLidarAdapter.this.serialPort;
            if (serialPort2 != null) {
                serialPort2.close();
            }
        }
    }

    public final int getMESSAGE_FROM_SERIAL_PORT() {
        return this.MESSAGE_FROM_SERIAL_PORT;
    }

    public final SerialLidarInterface getLidarInterface() {
        return this.lidarInterface;
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

    /* compiled from: SerialLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/lidar/base/SerialLidarAdapter$Companion;", "", "()V", "BUFFER_SIZE", "", "MESSAGE_FROM_SERIAL_PORT", "MESSAGE_RETRY", "READ_DATA_WAIT_TIME", "", "READ_TIME_OUT", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
