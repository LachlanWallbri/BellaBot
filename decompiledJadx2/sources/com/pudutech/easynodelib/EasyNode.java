package com.pudutech.easynodelib;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.DataOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.DebugKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes5.dex
 */
/* compiled from: EasyNode.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u001d\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0086 J\u0011\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\tH\u0086 J\u0011\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\tH\u0086 J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\rJ\t\u0010\u0019\u001a\u00020\rH\u0082 J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\t\u0010\u001b\u001a\u00020\rH\u0086 J\t\u0010\u001c\u001a\u00020\rH\u0086 J\t\u0010\u001d\u001a\u00020\rH\u0086 J\u0011\u0010\u001e\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0086 J\u000e\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0007J\u000e\u0010!\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0007J\t\u0010\"\u001a\u00020\rH\u0082 J\t\u0010#\u001a\u00020\rH\u0082 J\t\u0010$\u001a\u00020\rH\u0082 J\u000e\u0010%\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0007J\t\u0010&\u001a\u00020\rH\u0086 J\u0011\u0010'\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086 J\u0011\u0010(\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086 J\u0011\u0010)\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086 J\u0011\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\rH\u0086 J\u0011\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\rH\u0086 J\u0011\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\rH\u0086 J\u0006\u00100\u001a\u00020\u0013J\u0010\u00101\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/easynodelib/EasyNode;", "", "()V", "TAG", "", "esp32Listener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/easynodelib/EasyNodeDataCatcher;", "initStatus", "", "loraListener", "usbCtrlListener", "closeUSB", "", "index", "control4G", DebugKt.DEBUG_PROPERTY_VALUE_ON, "controlESP", "esp32Callback", "", "data", "", "executeCommand", "command", "initEasyNode", "initTheEasyNode", "loraCallback", "loraDIS", "loraEN", "loraRST", "openUSB", "registerESP32", "callback", "registerLora", "registerTheESP32", "registerTheLora", "registerTheUSBCtrl", "registerUSBCtrl", "resetESP", "sendTheESP32Data", "sendTheLoraData", "sendTheUsbCtrlData", "setBacklight", "luminance", "setESPChannel", "channel", "setLoraMod", "mod", "unInitEspNode", "usbCtrlCallback", "easynodelib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class EasyNode {
    public static final EasyNode INSTANCE = new EasyNode();
    public static final String TAG = "EasyNode";
    private static final ThreadSafeListener<EasyNodeDataCatcher> esp32Listener;
    private static boolean initStatus;
    private static final ThreadSafeListener<EasyNodeDataCatcher> loraListener;
    private static final ThreadSafeListener<EasyNodeDataCatcher> usbCtrlListener;

    /* JADX INFO: Access modifiers changed from: private */
    public final native int initTheEasyNode();

    private final native int registerTheESP32();

    private final native int registerTheLora();

    private final native int registerTheUSBCtrl();

    public final native int closeUSB(int index);

    public final native int control4G(boolean on);

    public final native int controlESP(boolean on);

    public final native int loraDIS();

    public final native int loraEN();

    public final native int loraRST();

    public final native int openUSB(int index);

    public final native int resetESP();

    public final native int sendTheESP32Data(byte[] data);

    public final native int sendTheLoraData(byte[] data);

    public final native int sendTheUsbCtrlData(byte[] data);

    public final native int setBacklight(int luminance);

    public final native int setESPChannel(int channel);

    public final native int setLoraMod(int mod);

    static {
        System.loadLibrary("easynode_lib");
        usbCtrlListener = new ThreadSafeListener<>();
        esp32Listener = new ThreadSafeListener<>();
        loraListener = new ThreadSafeListener<>();
    }

    private EasyNode() {
    }

    private final void usbCtrlCallback(final byte[] data) {
        usbCtrlListener.notify(new Function2<EasyNodeDataCatcher, String, Unit>() { // from class: com.pudutech.easynodelib.EasyNode$usbCtrlCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(EasyNodeDataCatcher easyNodeDataCatcher, String str) {
                invoke2(easyNodeDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EasyNodeDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.callback(data);
            }
        });
    }

    private final void esp32Callback(final byte[] data) {
        esp32Listener.notify(new Function2<EasyNodeDataCatcher, String, Unit>() { // from class: com.pudutech.easynodelib.EasyNode$esp32Callback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(EasyNodeDataCatcher easyNodeDataCatcher, String str) {
                invoke2(easyNodeDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EasyNodeDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.callback(data);
            }
        });
    }

    private final void loraCallback(final byte[] data) {
        loraListener.notify(new Function2<EasyNodeDataCatcher, String, Unit>() { // from class: com.pudutech.easynodelib.EasyNode$loraCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(EasyNodeDataCatcher easyNodeDataCatcher, String str) {
                invoke2(easyNodeDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(EasyNodeDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.callback(data);
            }
        });
    }

    public final int initEasyNode() {
        if (initStatus) {
            return -1;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        BuildersKt__BuildersKt.runBlocking$default(null, new EasyNode$initEasyNode$1(intRef, null), 1, null);
        return intRef.element;
    }

    public final void unInitEspNode() {
        initStatus = false;
    }

    public final int registerUSBCtrl(EasyNodeDataCatcher callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        usbCtrlListener.add("easyNodeUsbCtrlListener", callback);
        return registerTheUSBCtrl();
    }

    public final int registerESP32(EasyNodeDataCatcher callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        esp32Listener.add("easyNodeEsp32Listener", callback);
        return registerTheESP32();
    }

    public final int registerLora(EasyNodeDataCatcher callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        loraListener.add("loraListener", callback);
        return registerTheLora();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0090 A[Catch: Exception -> 0x0096, TryCatch #4 {Exception -> 0x0096, blocks: (B:35:0x008b, B:29:0x0090, B:30:0x0093), top: B:34:0x008b }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean executeCommand(String command) {
        DataOutputStream dataOutputStream;
        Exception e;
        Intrinsics.checkParameterIsNotNull(command, "command");
        Process process = (Process) null;
        DataOutputStream dataOutputStream2 = (DataOutputStream) null;
        try {
            process = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
            Intrinsics.checkExpressionValueIsNotNull(process, "process");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
        } catch (Exception e2) {
            dataOutputStream = dataOutputStream2;
            e = e2;
        } catch (Throwable th) {
            th = th;
            if (dataOutputStream2 != null) {
            }
            if (process == null) {
            }
            process.destroy();
            throw th;
        }
        try {
            try {
                dataOutputStream.writeBytes(command + '\n');
                dataOutputStream.writeBytes(ShellUtils.COMMAND_EXIT);
                dataOutputStream.flush();
                process.waitFor();
                try {
                    dataOutputStream.close();
                    process.destroy();
                } catch (Exception unused) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream2 = dataOutputStream;
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (Exception unused2) {
                        throw th;
                    }
                }
                if (process == null) {
                    Intrinsics.throwNpe();
                }
                process.destroy();
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            Pdlog.m3273d(TAG, "RootCommand failure command=" + command + " error:" + e.getMessage());
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (Exception unused3) {
                    return false;
                }
            }
            if (process == null) {
                Intrinsics.throwNpe();
            }
            process.destroy();
            return false;
        }
    }
}
