package com.pudutech.mirsdk.mircore.module.speedlevel;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RunParamUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\nJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\rJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/speedlevel/RunParamUtils;", "", "()V", "TAG", "", "heavyLoadStr", "runParamFile", "runParamFilePath", "smoothStr", "getInitRunParam", "", "param_name", "getInitRunParamDouble", "", "getInitSmoothMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "writeRunParam", "", "flag", "number", "param_value", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RunParamUtils {
    public static final RunParamUtils INSTANCE = new RunParamUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String runParamFilePath = "/sdcard/pudu/config";
    private static final String runParamFile = runParamFile;
    private static final String runParamFile = runParamFile;
    private static final String smoothStr = smoothStr;
    private static final String smoothStr = smoothStr;
    private static final String heavyLoadStr = heavyLoadStr;
    private static final String heavyLoadStr = heavyLoadStr;

    private RunParamUtils() {
    }

    public final void writeRunParam(String param_name, boolean flag) {
        Intrinsics.checkParameterIsNotNull(param_name, "param_name");
        String str = runParamFilePath + File.separator;
        String str2 = str + runParamFile;
        if (new File(str2).exists()) {
            try {
                File file = new File(str2);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                JSONObject jSONObject = new JSONObject(new String(cArr));
                jSONObject.put(param_name, flag);
                fileReader.close();
                FileWriter fileWriter = new FileWriter(str2);
                Pdlog.m3275i(TAG, "write to file content " + jSONObject);
                fileWriter.write(jSONObject.toString());
                fileWriter.close();
                return;
            } catch (Exception unused) {
                Pdlog.m3274e(TAG, "exception: run param file error");
                return;
            }
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"" + param_name + "\":" + flag + '}');
        FileWriter fileWriter2 = new FileWriter(str2);
        fileWriter2.write(sb.toString());
        fileWriter2.close();
    }

    public final void writeRunParam(String param_name, String param_value) {
        Intrinsics.checkParameterIsNotNull(param_name, "param_name");
        Intrinsics.checkParameterIsNotNull(param_value, "param_value");
        String str = runParamFilePath + File.separator;
        String str2 = str + runParamFile;
        if (new File(str2).exists()) {
            try {
                File file = new File(str2);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                JSONObject jSONObject = new JSONObject(new String(cArr));
                if (jSONObject.has(heavyLoadStr)) {
                    jSONObject.remove(heavyLoadStr);
                }
                jSONObject.put(param_name, param_value);
                fileReader.close();
                FileWriter fileWriter = new FileWriter(str2);
                Pdlog.m3275i(TAG, "write to file content " + jSONObject);
                fileWriter.write(jSONObject.toString());
                fileWriter.close();
                return;
            } catch (Exception unused) {
                Pdlog.m3274e(TAG, "exception: run param file error");
                return;
            }
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"" + param_name + "\":" + param_value + '}');
        FileWriter fileWriter2 = new FileWriter(str2);
        fileWriter2.write(sb.toString());
        fileWriter2.close();
    }

    public final void writeRunParam(String param_name, double number) {
        Intrinsics.checkParameterIsNotNull(param_name, "param_name");
        String str = runParamFilePath + File.separator;
        String str2 = str + runParamFile;
        if (new File(str2).exists()) {
            try {
                File file = new File(str2);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                JSONObject jSONObject = new JSONObject(new String(cArr));
                if (jSONObject.has(heavyLoadStr)) {
                    jSONObject.remove(heavyLoadStr);
                }
                jSONObject.put(param_name, number);
                fileReader.close();
                FileWriter fileWriter = new FileWriter(str2);
                Pdlog.m3275i(TAG, "write to file content " + jSONObject);
                fileWriter.write(jSONObject.toString());
                fileWriter.close();
                return;
            } catch (Exception unused) {
                Pdlog.m3274e(TAG, "exception: run param file error");
                return;
            }
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"" + param_name + "\":" + number + '}');
        FileWriter fileWriter2 = new FileWriter(str2);
        fileWriter2.write(sb.toString());
        fileWriter2.close();
    }

    public final boolean getInitRunParam(String param_name) {
        boolean z;
        FileReader fileReader;
        Intrinsics.checkParameterIsNotNull(param_name, "param_name");
        String str = runParamFilePath + File.separator + runParamFile;
        Pdlog.m3273d(TAG, "loadRunParam " + str);
        if (new File(str).exists()) {
            Pdlog.m3273d(TAG, "run param file exists");
            try {
                File file = new File(str);
                fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str2 = new String(cArr);
                Pdlog.m3273d(TAG, "run param file is " + str2);
                String str3 = str2;
                int length = str3.length() - 1;
                int i = 0;
                boolean z2 = false;
                while (i <= length) {
                    boolean z3 = str3.charAt(!z2 ? i : length) <= ' ';
                    if (z2) {
                        if (!z3) {
                            break;
                        }
                        length--;
                    } else if (z3) {
                        i++;
                    } else {
                        z2 = true;
                    }
                }
                JSONObject jSONObject = new JSONObject(str3.subSequence(i, length + 1).toString());
                z = jSONObject.has(param_name) ? Boolean.parseBoolean(jSONObject.get(param_name).toString()) : false;
            } catch (Exception unused) {
                z = false;
            }
            try {
                fileReader.close();
                return z;
            } catch (Exception unused2) {
                Pdlog.m3274e(TAG, "exception: run param file error");
                return z;
            }
        }
        Pdlog.m3273d(TAG, "run param file no exist");
        return false;
    }

    public final double getInitRunParamDouble(String param_name) {
        Intrinsics.checkParameterIsNotNull(param_name, "param_name");
        String str = runParamFilePath + File.separator + runParamFile;
        Pdlog.m3273d(TAG, "loadRunParam " + str);
        if (new File(str).exists()) {
            Pdlog.m3273d(TAG, "run param file exists");
            try {
                File file = new File(str);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str2 = new String(cArr);
                Pdlog.m3273d(TAG, "run param file is " + str2);
                String str3 = str2;
                int length = str3.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = str3.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                JSONObject jSONObject = new JSONObject(str3.subSequence(i, length + 1).toString());
                r3 = jSONObject.has(param_name) ? Double.parseDouble(jSONObject.get(param_name).toString()) : 0.0d;
                fileReader.close();
            } catch (Exception unused) {
                Pdlog.m3274e(TAG, "exception: run param file error");
            }
        } else {
            Pdlog.m3273d(TAG, "run param file no exist");
        }
        return r3;
    }

    public final SmoothMode getInitSmoothMode(MachineModel machineModel) {
        SmoothMode smoothMode;
        FileReader fileReader;
        SmoothMode smoothMode2;
        boolean z;
        Intrinsics.checkParameterIsNotNull(machineModel, "machineModel");
        SmoothMode smoothMode3 = SmoothMode.LightLoad;
        String str = runParamFilePath + File.separator + runParamFile;
        if (new File(str).exists()) {
            Pdlog.m3273d(TAG, "run param file exists");
            try {
                File file = new File(str);
                fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str2 = new String(cArr);
                Pdlog.m3273d(TAG, "run param file is " + str2);
                String str3 = str2;
                int length = str3.length() - 1;
                int i = 0;
                boolean z2 = false;
                while (i <= length) {
                    boolean z3 = str3.charAt(!z2 ? i : length) <= ' ';
                    if (z2) {
                        if (!z3) {
                            break;
                        }
                        length--;
                    } else if (z3) {
                        i++;
                    } else {
                        z2 = true;
                    }
                }
                JSONObject jSONObject = new JSONObject(str3.subSequence(i, length + 1).toString());
                if (jSONObject.has(heavyLoadStr)) {
                    boolean parseBoolean = Boolean.parseBoolean(jSONObject.get(heavyLoadStr).toString());
                    if (parseBoolean) {
                        smoothMode3 = SmoothMode.HeavyLoad;
                    } else if (!parseBoolean) {
                        smoothMode3 = SmoothMode.LightLoad;
                    }
                    smoothMode = smoothMode3;
                    z = true;
                } else {
                    if (jSONObject.has(smoothStr)) {
                        String string = jSONObject.getString(smoothStr);
                        if (string == null) {
                            string = SmoothMode.LightLoad.name();
                        }
                        smoothMode2 = SmoothMode.valueOf(string);
                    } else {
                        smoothMode2 = SmoothMode.NoSmooth;
                    }
                    smoothMode = smoothMode2;
                    z = false;
                }
            } catch (Exception unused) {
                smoothMode = smoothMode3;
            }
            try {
                fileReader.close();
                if (!z) {
                    return smoothMode;
                }
                writeRunParam(smoothStr, smoothMode.toString());
                return smoothMode;
            } catch (Exception unused2) {
                Pdlog.m3274e(TAG, "exception: run param file error");
                return smoothMode;
            }
        }
        Pdlog.m3273d(TAG, "run param file no exist, just create");
        SmoothMode smoothMode4 = SmoothMode.NoSmooth;
        writeRunParam(smoothStr, smoothMode4.toString());
        return smoothMode4;
    }
}
