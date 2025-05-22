package com.pudutech.lidar.base;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: CalibrationHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\tJ\r\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/lidar/base/CalibrationHelper;", "", "()V", "TAG", "", MapElement.Key.DIR, "Ljava/io/File;", "file", ES6Iterator.VALUE_PROPERTY, "", "isSecondLidar", "()Z", "setSecondLidar", "(Z)V", "checkHadCalibration", "getAngleCalibration", "", "()Ljava/lang/Double;", "save", "", "calibrationAngle", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CalibrationHelper {
    private static boolean isSecondLidar;
    public static final CalibrationHelper INSTANCE = new CalibrationHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final File dir = new File("sdcard/pudu/lidar");
    private static File file = new File(dir.getPath() + "/calibration");

    private CalibrationHelper() {
    }

    public final boolean isSecondLidar() {
        return isSecondLidar;
    }

    public final void setSecondLidar(boolean z) {
        isSecondLidar = z;
        if (z) {
            file = new File(dir.getPath() + "/calibration_second_lidar");
            return;
        }
        file = new File(dir.getPath() + "/calibration");
    }

    public final boolean checkHadCalibration() {
        return file.exists() && getAngleCalibration() != null;
    }

    public final void save(double calibrationAngle) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            JSONObject jSONObject = new JSONObject(FilesKt.readText$default(file, null, 1, null));
            jSONObject.put("angle", calibrationAngle);
            File file2 = file;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "json.toString()");
            FilesKt.writeText$default(file2, jSONObject2, null, 2, null);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, String.valueOf(e));
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("angle", calibrationAngle);
            File file3 = file;
            String jSONObject4 = jSONObject3.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject4, "json.toString()");
            FilesKt.writeText$default(file3, jSONObject4, null, 2, null);
        }
    }

    public final Double getAngleCalibration() {
        if (!file.exists()) {
            return null;
        }
        try {
            return Double.valueOf(new JSONObject(FilesKt.readText$default(file, null, 1, null)).getDouble("angle"));
        } catch (Exception e) {
            Pdlog.m3277w(TAG, String.valueOf(e));
            return null;
        }
    }
}
