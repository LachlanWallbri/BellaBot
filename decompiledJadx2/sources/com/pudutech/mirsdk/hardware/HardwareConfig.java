package com.pudutech.mirsdk.hardware;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.mirsdk.update.ApiConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/HardwareConfig;", "", "()V", "CameraOpenTimeOut", "", "IMUEncoderInterval", "", "LidarOpenTimeOut", TmpConstant.DATA_KEY_DEVICENAME, "", "getMAC", "()Ljava/lang/String;", "setMAC", "(Ljava/lang/String;)V", "RGBDFwUpdateTimeOut", "RGBDOpenIimeOut", "SlamLidarOpenTimeOut", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class HardwareConfig {
    public static final long CameraOpenTimeOut = 30000;
    public static final double IMUEncoderInterval = 0.025d;
    public static final long LidarOpenTimeOut = 30000;
    public static final long RGBDFwUpdateTimeOut = 180000;
    public static final long RGBDOpenIimeOut = 30000;
    public static final long SlamLidarOpenTimeOut = 80000;
    public static final HardwareConfig INSTANCE = new HardwareConfig();
    private static String MAC = ApiConstants.MAC_ADDRESS;

    private HardwareConfig() {
    }

    public final String getMAC() {
        return MAC;
    }

    public final void setMAC(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        MAC = str;
    }
}
