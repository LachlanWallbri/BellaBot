package com.pudutech.factory_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* compiled from: ShutDownHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/factory_test/ShutDownHelper;", "", "()V", "TAG", "", "addCanDataShutDownListener", "", "context", "Landroid/content/Context;", "doCaseByBroadcast", "", "doCaseByReflect", "doCaseBySimulateInputEvent", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ShutDownHelper {
    public static final ShutDownHelper INSTANCE = new ShutDownHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private ShutDownHelper() {
    }

    public final boolean doCaseByReflect() {
        Log.i(TAG, "doCaseByReflect version=" + Build.VERSION.SDK_INT);
        try {
            Object invoke = Class.forName("android.os.IPowerManager$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "power"));
            if (Build.VERSION.SDK_INT < 26) {
                invoke.getClass().getMethod("shutdown", Boolean.TYPE, Boolean.TYPE).invoke(invoke, false, true);
            } else {
                invoke.getClass().getMethod("shutdown", Boolean.TYPE, String.class, Boolean.TYPE).invoke(invoke, false, "userrequested", true);
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, String.valueOf(e));
            return false;
        }
    }

    public final boolean doCaseByBroadcast(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Log.i(TAG, "doCaseByBroadcast version=" + Build.VERSION.SDK_INT);
        try {
            Intent intent = Build.VERSION.SDK_INT < 26 ? new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN") : new Intent("com.android.internal.intent.action.REQUEST_SHUTDOWN");
            intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Log.e(TAG, String.valueOf(e));
            return false;
        }
    }

    public final void doCaseBySimulateInputEvent(Context context) {
        String str;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
        }
        Point point = new Point();
        ((WindowManager) systemService).getDefaultDisplay().getRealSize(point);
        Log.d("ShutDown", "real size=" + point.x + '*' + point.y);
        final boolean z = (point.x == 1920 && point.y == 1200) ? false : true;
        new Thread(new Runnable() { // from class: com.pudutech.factory_test.ShutDownHelper$doCaseBySimulateInputEvent$1
            @Override // java.lang.Runnable
            public final void run() {
                String str2 = z ? "input tap 960 200\n" : "input tap 1850 260\n";
                Process pro = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
                Intrinsics.checkExpressionValueIsNotNull(pro, "pro");
                OutputStream outputStream = pro.getOutputStream();
                byte[] bytes = str2.getBytes(Charsets.UTF_8);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                while (true) {
                    Thread.sleep(10L);
                    outputStream.write(bytes);
                    outputStream.flush();
                }
            }
        }).start();
        if (z) {
            str = ("sendevent /dev/input/event3 1 116 1 \nsendevent /dev/input/event3 0 0 0 \n") + "sleep 3 \n";
        } else {
            str = ("sendevent /dev/input/event2 1 116 1 \nsendevent /dev/input/event2 0 0 0 \n") + "sleep 3 \n";
        }
        Process pro = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
        Intrinsics.checkExpressionValueIsNotNull(pro, "pro");
        OutputStream outputStream = pro.getOutputStream();
        Charset charset = Charsets.UTF_8;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    public final void addCanDataShutDownListener(final Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "addCanDataShutDownListener context=" + context + " hdInterface=" + ServiceConnectionKt.getHdInterface());
        byte[] bArr = {(byte) 176};
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.addCANDataListener(TAG, bArr, new ICANData.Stub() { // from class: com.pudutech.factory_test.ShutDownHelper$addCanDataShutDownListener$1
            @Override // com.pudutech.mirsdk.hardware.ICANData
            public void onData(int p0, byte[] p1) {
                String str;
                ShutDownHelper shutDownHelper = ShutDownHelper.INSTANCE;
                str = ShutDownHelper.TAG;
                Pdlog.m3273d(str, "onData p0=" + p0 + "  p1=" + p1);
                HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
                if (hdInterface2 == null) {
                    Intrinsics.throwNpe();
                }
                byte[] copyOf = Arrays.copyOf(new byte[]{ClassDefinitionUtils.OPS_areturn, 2, 0, 0, 0, 0, 0}, 7);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                hdInterface2.sendCAN(copyOf);
                if (ShutDownHelper.INSTANCE.doCaseByBroadcast(context) || ShutDownHelper.INSTANCE.doCaseByReflect()) {
                    return;
                }
                ShutDownHelper.INSTANCE.doCaseBySimulateInputEvent(context);
            }
        });
    }
}
