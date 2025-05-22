package com.pudutech.mirsdk.hardware.cameralib;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CameraLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\tJ\u0006\u0010\u0016\u001a\u00020\tJ\u0096\u0001\u0010\u0017\u001a\u00020\u00182\u008b\u0001\u0010\u0019\u001a\u0086\u0001\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110#¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00110\u001aH\u0007J\u009e\u0001\u0010%\u001a\u00020\u00182\u008b\u0001\u0010\u0019\u001a\u0086\u0001\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110#¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00110\u001a2\u0006\u0010&\u001a\u00020\tH\u0007J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006+"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cameralib/CameraLib;", "", "()V", "MarkerContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "MonocularContext", "MonocularLoop", "Lkotlinx/coroutines/Job;", "TAG", "", "jobLoop", "lastError", "getLastError", "()Ljava/lang/String;", "setLastError", "(Ljava/lang/String;)V", "closeMarkerCamera", "", "closeMonocularCamera", "getExposure", "", "getGitHash", "getLastRunningError", "openMarkerCamera", "Lcom/pudutech/mirsdk/hardware/cameralib/CameraLib$Result;", "dataCallback", "Lkotlin/Function6;", "Landroid/os/ParcelFileDescriptor;", "Lkotlin/ParameterName;", "name", "parcelFileDescriptor", "rows", "cols", "memorySize", "elementSize", "", "stamp", "openMonocularCamera", "devVid", "setExposure", "", "exposure", "Result", "MarkerCamera_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CameraLib {
    public static final CameraLib INSTANCE = new CameraLib();
    private static final ExecutorCoroutineDispatcher MarkerContext;
    private static final ExecutorCoroutineDispatcher MonocularContext;
    private static Job MonocularLoop = null;
    private static final String TAG;
    private static Job jobLoop;
    private static String lastError;

    public final String getGitHash() {
        return "{\"MarkerCamera\":\"commit: aedf28a, auth: “songleiquan”<“songleiquan@pudutech.com”>, time: “Thu Mar 18 14:17:37 2021 +0800”\"}";
    }

    static {
        System.loadLibrary("markernative");
        TAG = TAG;
        lastError = "";
        MarkerContext = ThreadPoolDispatcherKt.newSingleThreadContext("MarkCameraThd");
        MonocularContext = ThreadPoolDispatcherKt.newSingleThreadContext("MonoCameraThd");
    }

    private CameraLib() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: CameraLib.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cameralib/CameraLib$Result;", "", "isSuccess", "", "description", "", "(ZLjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "MarkerCamera_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final /* data */ class Result {
        private final String description;
        private final boolean isSuccess;

        public static /* synthetic */ Result copy$default(Result result, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.isSuccess;
            }
            if ((i & 2) != 0) {
                str = result.description;
            }
            return result.copy(z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final Result copy(boolean isSuccess, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            return new Result(isSuccess, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.isSuccess == result.isSuccess && Intrinsics.areEqual(this.description, result.description);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.description;
            return i + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(isSuccess=" + this.isSuccess + ", description=" + this.description + ")";
        }

        public Result(boolean z, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.isSuccess = z;
            this.description = description;
        }

        public final String getDescription() {
            return this.description;
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }
    }

    public final String getLastError() {
        return lastError;
    }

    public final void setLastError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        lastError = str;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: CameraLib.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cameralib/CameraLib$CameraParam;", "", "width", "", "height", "fps", "exposure", "(IIII)V", "getExposure", "()I", "setExposure", "(I)V", "getFps", "setFps", "getHeight", "setHeight", "getWidth", "setWidth", "toString", "", "MarkerCamera_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class CameraParam {
        private int exposure;
        private int fps;
        private int height;
        private int width;

        public CameraParam() {
            this(0, 0, 0, 0, 15, null);
        }

        public CameraParam(int i, int i2, int i3, int i4) {
            this.width = i;
            this.height = i2;
            this.fps = i3;
            this.exposure = i4;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setWidth(int i) {
            this.width = i;
        }

        public final int getHeight() {
            return this.height;
        }

        public final void setHeight(int i) {
            this.height = i;
        }

        public final int getFps() {
            return this.fps;
        }

        public final void setFps(int i) {
            this.fps = i;
        }

        public /* synthetic */ CameraParam(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this((i5 & 1) != 0 ? 1280 : i, (i5 & 2) != 0 ? 720 : i2, (i5 & 4) != 0 ? 10 : i3, (i5 & 8) != 0 ? 0 : i4);
        }

        public final int getExposure() {
            return this.exposure;
        }

        public final void setExposure(int i) {
            this.exposure = i;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.width);
            jSONObject.put("height", this.height);
            jSONObject.put("fps", this.fps);
            jSONObject.put("exposure", this.exposure);
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "json.toString()");
            return jSONObject2;
        }
    }

    public final synchronized Result openMarkerCamera(Function6<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Long, Unit> dataCallback) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(dataCallback, "dataCallback");
        lastError = "";
        BuildersKt__BuildersKt.runBlocking$default(null, new CameraLib$openMarkerCamera$1(null), 1, null);
        if (!new File(MapFilePathConfig.CAMERA_CONFIG_PATH).exists()) {
            File file = new File(ConfigUtil.CONFIG_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!new File(MapFilePathConfig.OLD_CAMERA_CONFIG_PATH).exists()) {
                String str = "missing file " + MapFilePathConfig.CAMERA_CONFIG_PATH;
                lastError = str;
                Pdlog.m3274e(TAG, str);
                return new Result(false, lastError);
            }
            OutputStream outputStream = (OutputStream) null;
            InputStream inputStream = (InputStream) null;
            try {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(MapFilePathConfig.CAMERA_CONFIG_PATH);
                    try {
                        FileInputStream fileInputStream = new FileInputStream(MapFilePathConfig.OLD_CAMERA_CONFIG_PATH);
                        try {
                            byte[] bArr = new byte[256];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.close();
                            fileInputStream.close();
                        } catch (Exception e) {
                            e = e;
                            inputStream = fileInputStream;
                            outputStream = fileOutputStream;
                            Pdlog.m3274e(TAG, "copy camera config file err: " + e);
                            Result result = new Result(false, "copy camera config file err: " + e);
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return result;
                        } catch (Throwable th) {
                            th = th;
                            inputStream = fileInputStream;
                            outputStream = fileOutputStream;
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        int openMarker = CameraNative.openMarker(MapFilePathConfig.CAMERA_CONFIG_PATH);
        if (openMarker == 0) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, MarkerContext, null, new CameraLib$openMarkerCamera$2(dataCallback, null), 2, null);
            jobLoop = launch$default;
            return new Result(true, "camera open success");
        }
        Pdlog.m3274e(TAG, "camera open fail");
        String lastError2 = CameraNative.getLastError(openMarker);
        Intrinsics.checkExpressionValueIsNotNull(lastError2, "CameraNative.getLastError(ret)");
        return new Result(false, lastError2);
    }

    public final void closeMarkerCamera() {
        BuildersKt__BuildersKt.runBlocking$default(null, new CameraLib$closeMarkerCamera$1(null), 1, null);
    }

    public final synchronized Result openMonocularCamera(Function6<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Long, Unit> dataCallback, String devVid) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(dataCallback, "dataCallback");
        Intrinsics.checkParameterIsNotNull(devVid, "devVid");
        lastError = "";
        BuildersKt__BuildersKt.runBlocking$default(null, new CameraLib$openMonocularCamera$1(null), 1, null);
        int openMonocular = CameraNative.openMonocular("/sdcard/pudu/config/Monocular_camera.config", devVid);
        if (openMonocular == 0) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, MonocularContext, null, new CameraLib$openMonocularCamera$2(dataCallback, null), 2, null);
            MonocularLoop = launch$default;
            return new Result(true, "camera open success");
        }
        Pdlog.m3274e(TAG, "camera open fail");
        String lastError2 = CameraNative.getLastError(openMonocular);
        Intrinsics.checkExpressionValueIsNotNull(lastError2, "CameraNative.getLastError(ret)");
        return new Result(false, lastError2);
    }

    public final void closeMonocularCamera() {
        BuildersKt__BuildersKt.runBlocking$default(null, new CameraLib$closeMonocularCamera$1(null), 1, null);
    }

    public final boolean setExposure(int exposure) {
        Pdlog.m3273d(TAG, "setExposure");
        return CameraNative.setExposure(exposure);
    }

    public final int getExposure() {
        return CameraNative.getExposure();
    }

    public final String getLastRunningError() {
        String lastRunningError = CameraNative.getLastRunningError();
        Intrinsics.checkExpressionValueIsNotNull(lastRunningError, "CameraNative.getLastRunningError()");
        return lastRunningError;
    }
}
