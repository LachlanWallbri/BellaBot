package com.pudutech.mirsdk.hardware.cameralib;

import com.pudutech.mirsdk.hardware.serialize.SensorImageContainer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* loaded from: classes2.dex */
public class CameraNative {
    public static native void closeMarker();

    public static native void closeMonocular();

    public static native int getExposure();

    public static native String getLastError(int i);

    public static native String getLastRunningError();

    public static native SensorImageContainer getMarkerFrame();

    public static native SensorImageContainer getMonocularFrame();

    public static native boolean isOpenedMarker();

    public static native boolean isOpenedMonocular();

    public static native int openMarker(String str);

    public static native int openMonocular(String str, String str2);

    public static native boolean setExposure(int i);
}
