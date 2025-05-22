package com.pudutech.mirsdk.hardware;

import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.io.File;
import java.io.FileWriter;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: GpioCtrl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/GpioCtrl;", "", "()V", "TAG", "", "inited", "", "controlCameraIRDLED", "", "lightOn", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class GpioCtrl {
    private static boolean inited;
    public static final GpioCtrl INSTANCE = new GpioCtrl();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    static {
        try {
            Pdlog.m3273d(TAG, "init");
            if (!new File("/sys/class/gpio/gpio35").exists()) {
                Pair<Integer, String> execCommand = Tools.execCommand("chmod 777 /sys/class/gpio/export", true);
                Pdlog.m3273d(TAG, "chmod 777 " + ((Integer) execCommand.first) + ' ' + ((String) execCommand.second));
                FileWriter fileWriter = new FileWriter(new File("/sys/class/gpio/export"));
                fileWriter.write("35");
                fileWriter.close();
            }
            Tools.execCommand("chmod 777 /sys/class/gpio/gpio35/direction", true);
            FileWriter fileWriter2 = new FileWriter(new File("/sys/class/gpio/gpio35/direction"));
            fileWriter2.write("out");
            fileWriter2.close();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "init exception " + Log.getStackTraceString(e));
        }
    }

    private GpioCtrl() {
    }

    public final void controlCameraIRDLED(boolean lightOn) {
        try {
            Pdlog.m3273d(TAG, "controlCameraIRDLED");
            Tools.execCommand("chmod 777 /sys/class/gpio/gpio35/value", true);
            FileWriter fileWriter = new FileWriter(new File("/sys/class/gpio/gpio35/value"));
            fileWriter.write(lightOn ? "0" : "1");
            fileWriter.close();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "controlCameraIRDLED exception " + Log.getStackTraceString(e));
        }
    }
}
