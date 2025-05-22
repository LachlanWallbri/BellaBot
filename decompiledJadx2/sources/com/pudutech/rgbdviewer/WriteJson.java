package com.pudutech.rgbdviewer;

import android.util.Log;
import com.google.gson.Gson;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WriteJson {
    private static final String TAG = "RGBDUtils";
    private static final String fileName = "rgbd.json";
    private static final String[] filePath = {"/sdcard/PuduRobotMap", ConfigUtil.CONFIG_DIR};
    private static Gson gson = new Gson();
    private static boolean exist_old_params = false;

    public static Boolean writeConfigForPlus(String str, String str2, String str3, String str4) {
        Boolean bool;
        StringBuilder sb = new StringBuilder();
        String str5 = str;
        sb.append(str5);
        sb.append(" ");
        String str6 = str2;
        sb.append(str6);
        sb.append(" pose0: ");
        sb.append(str3);
        sb.append(" pose1: ");
        sb.append(str4);
        Log.d("WriteJson", sb.toString());
        if (str.length() != 12 || str2.length() != 12) {
            return false;
        }
        if (!str3.equals("left")) {
            str6 = str5;
            str5 = str6;
        }
        try {
            bool = false;
            try {
                if (HardwareManager.INSTANCE.getRgbdVersion() != 4) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("x", 0.18105d);
                    jSONObject.put("y", 0.08143d);
                    jSONObject.put(CompressorStreamFactory.f8930Z, 0.23012d);
                    jSONObject.put("yaw", 0.61d);
                    jSONObject.put("pitch", -0.61d);
                    jSONObject.put("roll", 0);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("serial", str5);
                    jSONObject2.put("extrinsics", jSONObject);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("x", 0.18105d);
                    jSONObject3.put("y", -0.08143d);
                    jSONObject3.put(CompressorStreamFactory.f8930Z, 0.23012d);
                    jSONObject3.put("yaw", -0.61d);
                    jSONObject3.put("pitch", -0.61d);
                    jSONObject3.put("roll", 3.14159d);
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("serial", str6);
                    jSONObject4.put("extrinsics", jSONObject3);
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("path", "/sdcard/pudu/costmap_data");
                    jSONObject5.put("record", 0);
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("left_rgbd", jSONObject2);
                    jSONObject6.put("right_rgbd", jSONObject4);
                    jSONObject6.put("data", jSONObject5);
                    return writePathFile(filePath, "rgbd.json", jSONObject6.toString());
                }
                JSONObject jSONObject7 = new JSONObject();
                jSONObject7.put("x", 0.1779d);
                jSONObject7.put("y", 0.083d);
                jSONObject7.put(CompressorStreamFactory.f8930Z, 0.2215d);
                jSONObject7.put("yaw", 0.52d);
                jSONObject7.put("pitch", -0.7d);
                jSONObject7.put("roll", 0);
                JSONObject jSONObject8 = new JSONObject();
                jSONObject8.put("serial", str5);
                jSONObject8.put("extrinsics", jSONObject7);
                JSONObject jSONObject9 = new JSONObject();
                jSONObject9.put("x", 0.1779d);
                jSONObject9.put("y", -0.083d);
                jSONObject9.put(CompressorStreamFactory.f8930Z, 0.2215d);
                jSONObject9.put("yaw", -0.52d);
                jSONObject9.put("pitch", -0.7d);
                jSONObject9.put("roll", 3.14159d);
                JSONObject jSONObject10 = new JSONObject();
                jSONObject10.put("serial", str6);
                jSONObject10.put("extrinsics", jSONObject9);
                JSONObject jSONObject11 = new JSONObject();
                jSONObject11.put("path", "/sdcard/pudu/costmap_data");
                jSONObject11.put("record", 0);
                JSONObject jSONObject12 = new JSONObject();
                jSONObject12.put("left_rgbd", jSONObject8);
                jSONObject12.put("right_rgbd", jSONObject10);
                jSONObject12.put("data", jSONObject11);
                return writePathFile(filePath, "rgbd.json", jSONObject12.toString());
            } catch (Exception e) {
                e = e;
                Log.e(TAG, e.getLocalizedMessage());
                return bool;
            }
        } catch (Exception e2) {
            e = e2;
            bool = false;
        }
    }

    public static Boolean writeConfigForBella(String str, String str2, String str3, String str4) {
        Boolean bool;
        JSONObject jSONObject;
        StringBuilder sb = new StringBuilder();
        String str5 = str;
        sb.append(str5);
        sb.append(" ");
        String str6 = str2;
        sb.append(str6);
        sb.append(" pose0: ");
        sb.append(str3);
        sb.append(" pose1: ");
        sb.append(str4);
        Log.d("WriteJson", sb.toString());
        if (str.length() != 12 || str2.length() != 12) {
            return false;
        }
        if (!str3.equals("left")) {
            str6 = str5;
            str5 = str6;
        }
        try {
            jSONObject = new JSONObject();
            bool = false;
        } catch (Exception e) {
            e = e;
            bool = false;
        }
        try {
            jSONObject.put("x", 0.20661d);
            jSONObject.put("y", 0.0797d);
            jSONObject.put(CompressorStreamFactory.f8930Z, 0.12835d);
            jSONObject.put("yaw", 0.61d);
            jSONObject.put("pitch", -0.61d);
            jSONObject.put("roll", 0);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("serial", str5);
            jSONObject2.put("extrinsics", jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("x", 0.20661d);
            jSONObject3.put("y", -0.0797d);
            jSONObject3.put(CompressorStreamFactory.f8930Z, 0.12835d);
            jSONObject3.put("yaw", -0.61d);
            jSONObject3.put("pitch", -0.61d);
            jSONObject3.put("roll", 3.14159d);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("serial", str6);
            jSONObject4.put("extrinsics", jSONObject3);
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("path", "/sdcard/pudu/costmap_data");
            jSONObject5.put("record", 0);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("left_rgbd", jSONObject2);
            jSONObject6.put("right_rgbd", jSONObject4);
            jSONObject6.put("data", jSONObject5);
            return writePathFile(filePath, "rgbd.json", jSONObject6.toString());
        } catch (Exception e2) {
            e = e2;
            Log.e(TAG, e.getLocalizedMessage());
            return bool;
        }
    }

    private static void saveParamToSDCard(String str, String str2) {
        writePathFile(filePath, str, str2);
    }

    public static Boolean writePathFile(String[] strArr, String str, String str2) {
        for (int i = 0; i < strArr.length; i++) {
            try {
                String[] split = strArr[i].split(File.separator);
                StringBuffer stringBuffer = new StringBuffer();
                for (String str3 : split) {
                    stringBuffer.append(str3);
                    stringBuffer.append(File.separator);
                    File file = new File(stringBuffer.toString());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
                File file2 = new File(strArr[i] + File.separator + str);
                StringBuilder sb = new StringBuilder();
                sb.append("file name: ");
                sb.append(file2);
                Log.d("WriteJson", sb.toString());
                FileWriter fileWriter = new FileWriter(file2);
                fileWriter.write(str2);
                fileWriter.close();
            } catch (IOException e) {
                Log.e(TAG, "exception " + e.getLocalizedMessage() + " :" + Log.getStackTraceString(e));
                return false;
            }
        }
        return true;
    }
}
