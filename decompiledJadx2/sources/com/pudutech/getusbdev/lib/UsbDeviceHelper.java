package com.pudutech.getusbdev.lib;

import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UsbDeviceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0082 J\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/getusbdev/lib/UsbDeviceHelper;", "", "()V", "findUsbDevice", "", "pid", SpeechConstant.ISV_VID, "deviceType", "", "getUsbDevice", "Lcom/pudutech/getusbdev/lib/UsbDeviceType;", "library_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UsbDeviceHelper {
    public static final UsbDeviceHelper INSTANCE = new UsbDeviceHelper();

    private final native String findUsbDevice(String pid, String vid, int deviceType);

    static {
        System.loadLibrary("getUsbDev");
    }

    private UsbDeviceHelper() {
    }

    public final String getUsbDevice(String pid, String vid, UsbDeviceType deviceType) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(vid, "vid");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        return findUsbDevice(pid, vid, deviceType.ordinal());
    }
}
