package com.pudutech.bumblebee.robot.remote_device;

import android.content.Context;
import android.util.Pair;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.serialport.library.ISerialPortDataReceiveCallback;
import com.pudutech.serialport.library.SerialPortHelper;
import java.io.File;
import java.io.FileWriter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RecycleRobotLora.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 !2\u00020\u0001:\u0003!\"#B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J8\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\n0\u0013H\u0016J\u0011\u0010\u0016\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0015H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/RecycleRobotLora;", "Lcom/pudutech/bumblebee/robot/remote_device/RemoteDeviceInterface;", "()V", "TAG", "", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/serialport/library/SerialPortHelper;", "status", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "exportAndSetupDirectionOut", "", "gpioNum", "", "flush", "getDeviceStatus", "open", "context", "Landroid/content/Context;", "statueListener", "Lkotlin/Function1;", "dataListener", "", "resetDevice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MqttServiceConstants.SEND_ACTION, "byteArray", "setupLevel", "", "level", "Lcom/pudutech/bumblebee/robot/remote_device/RecycleRobotLora$Level;", "switchLevelMode", "mode", "Lcom/pudutech/bumblebee/robot/remote_device/RecycleRobotLora$LevelMode;", "Companion", "Level", "LevelMode", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RecycleRobotLora implements RemoteDeviceInterface {
    public static final int BAUD_RATE = 9600;
    public static final int FLAGS = 0;
    private static final int GPIO_NUM_132 = 132;
    public static final String ID_PRODUCT = "6011";
    public static final String ID_VENDOR = "0403";
    public static final String INTERFACE_NUN = "00";
    private static final int M0_GPIO_NUM = 134;
    private static final int M1_GPIO_NUM = 131;
    public static final String PRODUCT = "Quad RS232-HS";
    private final String TAG = "RecycleRobotLora";
    private final SerialPortHelper device = new SerialPortHelper();
    private PeripheralDeviceStatus status = PeripheralDeviceStatus.DEVICE_DISCONNECT;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: RecycleRobotLora.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/RecycleRobotLora$LevelMode;", "", "(Ljava/lang/String;I)V", "DeepSleep", "Normal", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum LevelMode {
        DeepSleep,
        Normal
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LevelMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LevelMode.DeepSleep.ordinal()] = 1;
            $EnumSwitchMapping$0[LevelMode.Normal.ordinal()] = 2;
        }
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.RemoteDeviceInterface
    public void open(Context context, Function1<? super PeripheralDeviceStatus, Unit> statueListener, final Function1<? super byte[], Unit> dataListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(statueListener, "statueListener");
        Intrinsics.checkParameterIsNotNull(dataListener, "dataListener");
        PeripheralDeviceStatus peripheralDeviceStatus = PeripheralDeviceStatus.DEVICE_CONNECTING;
        this.status = peripheralDeviceStatus;
        statueListener.invoke(peripheralDeviceStatus);
        RecycleRobotLora$open$statusCallback$1 recycleRobotLora$open$statusCallback$1 = new RecycleRobotLora$open$statusCallback$1(this, statueListener);
        ISerialPortDataReceiveCallback iSerialPortDataReceiveCallback = new ISerialPortDataReceiveCallback() { // from class: com.pudutech.bumblebee.robot.remote_device.RecycleRobotLora$open$dataReceiveCallback$1
            @Override // com.pudutech.serialport.library.ISerialPortDataReceiveCallback
            public void onReceive(byte[] data, int length) {
                String str;
                Intrinsics.checkParameterIsNotNull(data, "data");
                str = RecycleRobotLora.this.TAG;
                Pdlog.m3276v(str, "onReceive length=" + length);
                if (length < 0) {
                    return;
                }
                dataListener.invoke(ArraysKt.copyOfRange(data, 0, length));
            }
        };
        Pdlog.m3273d(this.TAG, "init ID_VENDOR=0403 ID_PRODUCT=6011 PRODUCT=Quad RS232-HS BAUD_RATE=9600 FLAGS=0");
        this.device.init(context, "0403", "6011", "Quad RS232-HS", "00", 9600, 0, recycleRobotLora$open$statusCallback$1, iSerialPortDataReceiveCallback);
        this.device.openSerialPort();
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.RemoteDeviceInterface
    public void send(byte[] byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        Pdlog.m3276v(this.TAG, "send size=" + byteArray.length);
        this.device.writeData(byteArray);
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.RemoteDeviceInterface
    /* renamed from: getDeviceStatus, reason: from getter */
    public PeripheralDeviceStatus getStatus() {
        return this.status;
    }

    @Override // com.pudutech.bumblebee.robot.remote_device.RemoteDeviceInterface
    public void flush() {
        this.device.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00d5, code lost:
    
        if (r1 == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00d7, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00f0, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00eb, code lost:
    
        if (r1 != null) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object resetDevice(Continuation<? super Unit> continuation) {
        RecycleRobotLora$resetDevice$1 recycleRobotLora$resetDevice$1;
        int i;
        FileWriter fileWriter;
        Throwable th;
        Exception e;
        if (continuation instanceof RecycleRobotLora$resetDevice$1) {
            recycleRobotLora$resetDevice$1 = (RecycleRobotLora$resetDevice$1) continuation;
            if ((recycleRobotLora$resetDevice$1.label & Integer.MIN_VALUE) != 0) {
                recycleRobotLora$resetDevice$1.label -= Integer.MIN_VALUE;
                Object obj = recycleRobotLora$resetDevice$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = recycleRobotLora$resetDevice$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(this.TAG, "resetDevice ");
                    FileWriter fileWriter2 = (FileWriter) null;
                    try {
                        Pdlog.m3273d(this.TAG, "resetLoRa() resetCmd = chmod 777 /sys/class/gpio/gpio132/value");
                        Pair<Integer, String> execCommand = Tools.execCommand("chmod 777 /sys/class/gpio/gpio132/value", true);
                        Pdlog.m3273d(this.TAG, "resetLoRa() result first = " + ((Integer) execCommand.first) + "\tsecond = " + ((String) execCommand.second));
                        FileWriter fileWriter3 = new FileWriter(new File("/sys/class/gpio/gpio132/value"));
                        try {
                            fileWriter3.write("1");
                            fileWriter3.flush();
                            recycleRobotLora$resetDevice$1.L$0 = this;
                            recycleRobotLora$resetDevice$1.L$1 = fileWriter3;
                            recycleRobotLora$resetDevice$1.L$2 = "chmod 777 /sys/class/gpio/gpio132/value";
                            recycleRobotLora$resetDevice$1.L$3 = execCommand;
                            recycleRobotLora$resetDevice$1.label = 1;
                            if (DelayKt.delay(100L, recycleRobotLora$resetDevice$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            fileWriter = fileWriter3;
                        } catch (Exception e2) {
                            e = e2;
                            fileWriter = fileWriter3;
                            e.printStackTrace();
                        } catch (Throwable th2) {
                            th = th2;
                            fileWriter = fileWriter3;
                            if (fileWriter != null) {
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        fileWriter = fileWriter2;
                        e = e3;
                    } catch (Throwable th3) {
                        fileWriter = fileWriter2;
                        th = th3;
                    }
                } else if (i == 1) {
                    fileWriter = (FileWriter) recycleRobotLora$resetDevice$1.L$1;
                    try {
                        try {
                            ResultKt.throwOnFailure(obj);
                        } catch (Throwable th4) {
                            th = th4;
                            if (fileWriter != null) {
                                fileWriter.close();
                            }
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                fileWriter.write("0");
                fileWriter.flush();
            }
        }
        recycleRobotLora$resetDevice$1 = new RecycleRobotLora$resetDevice$1(this, continuation);
        Object obj2 = recycleRobotLora$resetDevice$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = recycleRobotLora$resetDevice$1.label;
        if (i != 0) {
        }
        fileWriter.write("0");
        fileWriter.flush();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: RecycleRobotLora.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/remote_device/RecycleRobotLora$Level;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "High", "Low", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum Level {
        High("0"),
        Low("1");

        private final String value;

        Level(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0175  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void exportAndSetupDirectionOut(int gpioNum) {
        FileWriter fileWriter;
        String str;
        FileWriter fileWriter2 = (FileWriter) null;
        try {
            str = "/sys/class/gpio/gpio" + gpioNum;
            Pdlog.m3273d(this.TAG, "exportAndSetupDirectionOut() filePath = " + str);
        } catch (Exception e) {
            e = e;
            fileWriter = fileWriter2;
        } catch (Throwable th) {
            th = th;
            fileWriter = fileWriter2;
        }
        if (new File(str).exists()) {
            return;
        }
        String str2 = "chmod 777 /sys/class/gpio/export";
        Pdlog.m3273d(this.TAG, "exportAndSetupDirectionOut() exportCmd = " + str2);
        Pair<Integer, String> execCommand = Tools.execCommand(str2, true);
        Pdlog.m3273d(this.TAG, "exportAndSetupDirectionOut() exportResult first = " + ((Integer) execCommand.first) + "\tsecond = " + ((String) execCommand.second));
        StringBuilder sb = new StringBuilder();
        sb.append("/sys/class/gpio/");
        sb.append("export");
        FileWriter fileWriter3 = new FileWriter(new File(sb.toString()));
        try {
            fileWriter3.write(String.valueOf(gpioNum));
            fileWriter3.flush();
            String str3 = "chmod 777 /sys/class/gpio/gpio" + gpioNum + "/direction";
            Pdlog.m3273d(this.TAG, "exportAndSetupDirectionOut() directionCmd = " + str3);
            Pair<Integer, String> execCommand2 = Tools.execCommand(str3, true);
            Pdlog.m3273d(this.TAG, "exportAndSetupDirectionOut() directionResult first = " + ((Integer) execCommand2.first) + "\tsecond = " + ((String) execCommand2.second));
            fileWriter = new FileWriter(new File("/sys/class/gpio/gpio" + gpioNum + "/direction"));
        } catch (Exception e2) {
            e = e2;
            fileWriter = fileWriter2;
        } catch (Throwable th2) {
            th = th2;
            fileWriter = fileWriter2;
        }
        try {
            fileWriter.write("out");
            fileWriter.flush();
            fileWriter3.close();
        } catch (Exception e3) {
            e = e3;
            fileWriter2 = fileWriter3;
            try {
                e.printStackTrace();
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
                if (fileWriter == null) {
                    return;
                }
                fileWriter.close();
            } catch (Throwable th3) {
                th = th3;
                if (fileWriter2 != null) {
                    fileWriter2.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileWriter2 = fileWriter3;
            if (fileWriter2 != null) {
            }
            if (fileWriter != null) {
            }
            throw th;
        }
        fileWriter.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchLevelMode(LevelMode mode) {
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = setupLevel(131, Level.Low);
            boolean z2 = setupLevel(134, Level.Low);
            long currentTimeMillis2 = System.currentTimeMillis();
            Pdlog.m3273d(this.TAG, "setup level to low : m1[" + z + "], m0[" + z2 + "], execTime = " + (currentTimeMillis2 - currentTimeMillis) + "ms");
            return;
        }
        if (i != 2) {
            return;
        }
        long currentTimeMillis3 = System.currentTimeMillis();
        boolean z3 = setupLevel(131, Level.High);
        boolean z4 = setupLevel(134, Level.High);
        long currentTimeMillis4 = System.currentTimeMillis();
        Pdlog.m3273d(this.TAG, "setup level to high : m1[" + z3 + "], m0[" + z4 + "], execTime = " + (currentTimeMillis4 - currentTimeMillis3) + "ms");
    }

    private final boolean setupLevel(int gpioNum, Level level) {
        FileWriter fileWriter = (FileWriter) null;
        boolean z = false;
        try {
            try {
                String str = "chmod 777 /sys/class/gpio/gpio" + gpioNum + "/value";
                Pdlog.m3273d(this.TAG, "setupLevel() valueCmd = " + str);
                Pair<Integer, String> execCommand = Tools.execCommand(str, true);
                Pdlog.m3273d(this.TAG, "setupLevel() result first = " + ((Integer) execCommand.first) + "\tsecond = " + ((String) execCommand.second));
                StringBuilder sb = new StringBuilder();
                sb.append("/sys/class/gpio/gpio");
                sb.append(gpioNum);
                sb.append("/value");
                FileWriter fileWriter2 = new FileWriter(new File(sb.toString()));
                try {
                    fileWriter2.write(level.getValue());
                    fileWriter2.flush();
                    fileWriter2.close();
                    z = true;
                } catch (Exception e) {
                    e = e;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
