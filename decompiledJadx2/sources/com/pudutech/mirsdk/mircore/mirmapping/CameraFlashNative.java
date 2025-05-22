package com.pudutech.mirsdk.mircore.mirmapping;

import kotlin.Metadata;

/* compiled from: CameraFlashNative.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0086 J\t\u0010\u0005\u001a\u00020\u0006H\u0086 J\t\u0010\u0007\u001a\u00020\bH\u0086 J\t\u0010\t\u001a\u00020\u0004H\u0086 J\t\u0010\n\u001a\u00020\u0006H\u0086 J\t\u0010\u000b\u001a\u00020\bH\u0086 J\t\u0010\f\u001a\u00020\rH\u0086 J\t\u0010\u000e\u001a\u00020\u000fH\u0086 J\t\u0010\u0010\u001a\u00020\rH\u0086 J\u0011\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0086 J\t\u0010\u0014\u001a\u00020\u0004H\u0086 ¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/CameraFlashNative;", "", "()V", "onGetCalibrateValidFrameNative", "", "onGetCameraCalibrateTimeNative", "", "onGetCameraClarityNative", "", "onGetCameraFovNative", "onGetCameraIDNative", "onGetCameraParamNative", "onGetCameraReprojectErrorNative", "", "onGetCameraResolveNative", "", "onGetFocusLengthNative", "onInitCameraNative", "", "jd", "onOpenCameraNative", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CameraFlashNative {
    public static final CameraFlashNative INSTANCE = new CameraFlashNative();

    public final native int onGetCalibrateValidFrameNative();

    public final native byte[] onGetCameraCalibrateTimeNative();

    public final native float[] onGetCameraClarityNative();

    public final native int onGetCameraFovNative();

    public final native byte[] onGetCameraIDNative();

    public final native float[] onGetCameraParamNative();

    public final native float onGetCameraReprojectErrorNative();

    public final native int[] onGetCameraResolveNative();

    public final native float onGetFocusLengthNative();

    public final native boolean onInitCameraNative(int jd);

    public final native int onOpenCameraNative();

    static {
        System.loadLibrary("camera_flash_native");
    }

    private CameraFlashNative() {
    }
}
