package com.pudutech.mirsdk.mircore.module.speedlevel;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.base.FileUtil;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.mirnavigation.Navigation;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PlannerParamUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0004J\u0006\u0010#\u001a\u00020\u0004J\u0006\u0010$\u001a\u00020\u0004J\u0006\u0010%\u001a\u00020\u0004J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0002J\u000e\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\u0016\u0010*\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010'\u001a\u00020,H\u0002J\b\u0010/\u001a\u00020!H\u0002J\b\u00100\u001a\u00020!H\u0002J\u0006\u00101\u001a\u00020!J\u0012\u00102\u001a\u0004\u0018\u00010\u00042\u0006\u00103\u001a\u00020\u0004H\u0002J\u001a\u00104\u001a\u0004\u0018\u00010\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0018\u00106\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004H\u0002J\u0006\u00108\u001a\u00020!J\u0006\u00109\u001a\u00020!J\u0006\u0010:\u001a\u00020!J\u000e\u0010;\u001a\u00020!2\u0006\u0010'\u001a\u00020,J\u000e\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0004J\u0006\u0010?\u001a\u00020!J\u0006\u0010@\u001a\u00020!J\u000e\u0010A\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0004J\u0006\u0010B\u001a\u00020!J\u000e\u0010C\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0004J\u0016\u0010D\u001a\u00020=2\u0006\u0010'\u001a\u00020,2\u0006\u0010>\u001a\u00020\u0004J\u0018\u0010E\u001a\u00020!2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020GH\u0002J\b\u0010I\u001a\u00020!H\u0002J\u000e\u0010J\u001a\u00020!2\u0006\u0010K\u001a\u00020\u0014J \u0010L\u001a\u00020!2\u0006\u0010M\u001a\u00020G2\u0006\u0010N\u001a\u00020G2\u0006\u0010O\u001a\u00020GH\u0002J\u0016\u0010P\u001a\u00020!2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u0004J \u0010R\u001a\u00020!2\u0006\u00105\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010S\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u000e\u0010\r\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0011\u0010\nR*\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR*\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0013j\b\u0012\u0004\u0012\u00020\u0004`\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019¨\u0006T"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/speedlevel/PlannerParamUtils;", "", "()V", "TAG", "", "<set-?>", "cruise_Mode", "getCruise_Mode", "()Ljava/lang/String;", "setCruise_Mode", "(Ljava/lang/String;)V", "defaultSettingInCode", "getDefaultSettingInCode", "fileName", "filePath", "gohome_Mode", "getGohome_Mode", "setGohome_Mode", "maxSpeedArray", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getMaxSpeedArray", "()Ljava/util/ArrayList;", "setMaxSpeedArray", "(Ljava/util/ArrayList;)V", "p2P_Mode", "getP2P_Mode", "setP2P_Mode", "speedLevels", "getSpeedLevels", "setSpeedLevels", "factoryReset", "", "name", "getCruiseLevelMaxSpeed", "getDeliverLevelMaxSpeed", "getGoHomeLevelMaxSpeed", "getLocalModeParams", "mode", "getParamBySpeedMode", "speedMode", "getParamBySpeedModeLessThanCurrentMode", "moveMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/MoveMode;", "getSpecifiedMode", "getSteadyParams", "initParamFirst", "initParams", "initPlannerParams", "readParamFromSDCard", "str", "readPathFile", "pathName", "saveParamToSDCard", "param", "sendCruiseParams", "sendGoHomeParams", "sendP2PParams", "sendSteadyParams", "setCruisePlannerMode", "", "level", "setCruiseToNormal", "setDeliverToNormal", "setGoHomePlannerMode", "setGoHomeToNormal", "setP2PPlannerMode", "switchPlannerLevel", "updateLocalToRemote", "local", "Lorg/json/JSONObject;", "output", "updateMaxSpeedArrays", "updateObsDis", "dis", "updateOldParams", "oldJson", "newJson", "saveJson", "updateSpecifiedMode", "params", "writePathFile", "data", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PlannerParamUtils {
    public static final PlannerParamUtils INSTANCE = new PlannerParamUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String filePath = "/sdcard/pudu/config";
    private static final String fileName = fileName;
    private static final String fileName = fileName;
    private static String cruise_Mode = "Normal";
    private static String p2P_Mode = "Normal";
    private static String gohome_Mode = "Normal";
    private static ArrayList<String> speedLevels = new ArrayList<>();
    private static ArrayList<Double> maxSpeedArray = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MoveMode.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[MoveMode.Direct.ordinal()] = 1;
            $EnumSwitchMapping$0[MoveMode.Cruise.ordinal()] = 2;
            $EnumSwitchMapping$0[MoveMode.GoHome.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[MoveMode.values().length];
            $EnumSwitchMapping$1[MoveMode.Direct.ordinal()] = 1;
            $EnumSwitchMapping$1[MoveMode.Cruise.ordinal()] = 2;
            $EnumSwitchMapping$1[MoveMode.GoHome.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[MoveMode.values().length];
            $EnumSwitchMapping$2[MoveMode.Direct.ordinal()] = 1;
            $EnumSwitchMapping$2[MoveMode.Cruise.ordinal()] = 2;
            $EnumSwitchMapping$2[MoveMode.GoHome.ordinal()] = 3;
        }
    }

    private PlannerParamUtils() {
    }

    public final String getCruise_Mode() {
        return cruise_Mode;
    }

    public final void setCruise_Mode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        cruise_Mode = str;
    }

    public final String getP2P_Mode() {
        return p2P_Mode;
    }

    public final void setP2P_Mode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        p2P_Mode = str;
    }

    public final String getGohome_Mode() {
        return gohome_Mode;
    }

    public final void setGohome_Mode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        gohome_Mode = str;
    }

    private final String getDefaultSettingInCode() {
        return Navigation.INSTANCE.getPlannerParams();
    }

    public final ArrayList<String> getSpeedLevels() {
        return speedLevels;
    }

    public final void setSpeedLevels(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        speedLevels = arrayList;
    }

    public final ArrayList<Double> getMaxSpeedArray() {
        return maxSpeedArray;
    }

    public final void setMaxSpeedArray(ArrayList<Double> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        maxSpeedArray = arrayList;
    }

    public final void initPlannerParams() {
        FileOutputStream fileOutputStream;
        Throwable th;
        JSONObject jSONObject = new JSONObject(getDefaultSettingInCode());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            String string = jSONObject2.getString("name");
            if (next.equals(string)) {
                linkedHashMap.put(next, Double.valueOf(jSONObject2.getDouble("max_speed")));
            } else {
                Pdlog.m3274e(TAG, "Planner Params may be wrong, key " + next + ", name " + string);
            }
        }
        speedLevels = new ArrayList<>();
        maxSpeedArray = new ArrayList<>();
        while (!linkedHashMap.isEmpty()) {
            double d = 10.0d;
            String str = "";
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                String str2 = (String) entry.getKey();
                double doubleValue = ((Number) entry.getValue()).doubleValue();
                if (d > doubleValue) {
                    str = str2;
                    d = doubleValue;
                }
            }
            Pdlog.m3273d(TAG, "speedLevels size " + speedLevels.size() + ", maxSpeedArray size " + maxSpeedArray.size());
            speedLevels.add(str);
            maxSpeedArray.add(Double.valueOf(d));
            linkedHashMap.remove(str);
        }
        if (!new File(ConfigUtil.CONFIG_DIR + fileName).exists()) {
            File file = new File(ConfigUtil.CONFIG_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!new File("/sdcard/RobotConfig/" + fileName).exists()) {
                Pdlog.m3273d(TAG, "first create planner config file");
                initParamFirst();
                updateMaxSpeedArrays();
                return;
            }
            OutputStream outputStream = (OutputStream) null;
            InputStream inputStream = (InputStream) null;
            try {
                fileOutputStream = new FileOutputStream(ConfigUtil.CONFIG_DIR + fileName);
                try {
                    FileInputStream fileInputStream = new FileInputStream("/sdcard/RobotConfig/" + fileName);
                    try {
                        byte[] bArr = new byte[256];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                        fileOutputStream.close();
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = fileInputStream;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                fileOutputStream = outputStream;
                th = th4;
            }
        }
        Pdlog.m3273d(TAG, "find plannerparams.cfg file");
        if (FileUtil.getFileSize(filePath + '/' + fileName) == 0) {
            Pdlog.m3274e(TAG, "Planner Param file has been broken, need to reload file from local planner module");
            initParamFirst();
        } else {
            try {
                initParams();
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "init old params to new ex ", e, " will load default params");
                initParamFirst();
            }
        }
        updateMaxSpeedArrays();
    }

    public final String getCruiseLevelMaxSpeed() {
        return String.valueOf(maxSpeedArray.get(speedLevels.indexOf(cruise_Mode)).doubleValue());
    }

    public final String getDeliverLevelMaxSpeed() {
        return String.valueOf(maxSpeedArray.get(speedLevels.indexOf(p2P_Mode)).doubleValue());
    }

    public final String getGoHomeLevelMaxSpeed() {
        return String.valueOf(maxSpeedArray.get(speedLevels.indexOf(gohome_Mode)).doubleValue());
    }

    public final void setCruiseToNormal() {
        cruise_Mode = "Normal";
    }

    public final void setDeliverToNormal() {
        p2P_Mode = "Normal";
    }

    public final void setGoHomeToNormal() {
        gohome_Mode = "Normal";
    }

    public final boolean switchPlannerLevel(MoveMode mode, String level) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(level, "level");
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            return setP2PPlannerMode(level);
        }
        if (i == 2) {
            return setCruisePlannerMode(level);
        }
        if (i != 3) {
            return false;
        }
        return setGoHomePlannerMode(level);
    }

    public final boolean setCruisePlannerMode(String level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        if (!maxSpeedArray.contains(Double.valueOf(Double.parseDouble(level)))) {
            return false;
        }
        String str = speedLevels.get(maxSpeedArray.indexOf(Double.valueOf(Double.parseDouble(level))));
        Intrinsics.checkExpressionValueIsNotNull(str, "speedLevels.get(maxSpeed…ndexOf(level.toDouble()))");
        cruise_Mode = str;
        try {
            JSONObject jSONObject = new JSONObject(readParamFromSDCard(fileName));
            jSONObject.put("cruise_mode", cruise_Mode);
            String str2 = fileName;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "localPlannerParams.toString()");
            saveParamToSDCard(str2, jSONObject2);
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "reset cruise planner mode err", Log.getStackTraceString(e));
            return true;
        }
    }

    public final boolean setP2PPlannerMode(String level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        if (!maxSpeedArray.contains(Double.valueOf(Double.parseDouble(level)))) {
            return false;
        }
        String str = speedLevels.get(maxSpeedArray.indexOf(Double.valueOf(Double.parseDouble(level))));
        Intrinsics.checkExpressionValueIsNotNull(str, "speedLevels.get(maxSpeed…ndexOf(level.toDouble()))");
        p2P_Mode = str;
        try {
            JSONObject jSONObject = new JSONObject(readParamFromSDCard(fileName));
            jSONObject.put("p2p_mode", p2P_Mode);
            String str2 = fileName;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "localPlannerParams.toString()");
            saveParamToSDCard(str2, jSONObject2);
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "reset cruise planner mode err", Log.getStackTraceString(e));
            return true;
        }
    }

    public final boolean setGoHomePlannerMode(String level) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        if (!maxSpeedArray.contains(Double.valueOf(Double.parseDouble(level)))) {
            return false;
        }
        String str = speedLevels.get(maxSpeedArray.indexOf(Double.valueOf(Double.parseDouble(level))));
        Intrinsics.checkExpressionValueIsNotNull(str, "speedLevels.get(maxSpeed…ndexOf(level.toDouble()))");
        gohome_Mode = str;
        try {
            JSONObject jSONObject = new JSONObject(readParamFromSDCard(fileName));
            jSONObject.put("gohome_mode", gohome_Mode);
            String str2 = fileName;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "localPlannerParams.toString()");
            saveParamToSDCard(str2, jSONObject2);
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "reset gohome planner mode err", Log.getStackTraceString(e));
            return true;
        }
    }

    public final String getSpecifiedMode(String mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        if (!speedLevels.contains(mode)) {
            mode = "Normal";
        }
        return getLocalModeParams(mode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x012c, code lost:
    
        if (r7 <= r9.doubleValue()) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateSpecifiedMode(String mode, String params) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(params, "params");
        try {
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "updateSpecifiedMode ", e.getLocalizedMessage());
        }
        if (speedLevels.contains(mode) && speedLevels.size() > 0) {
            JSONObject jSONObject = new JSONObject(getLocalModeParams(mode));
            String readParamFromSDCard = readParamFromSDCard(fileName);
            Pdlog.m3273d(TAG, "get Local Speed Mode", mode, " params:", readParamFromSDCard);
            JSONObject jSONObject2 = new JSONObject(readParamFromSDCard);
            JSONObject jSONObject3 = new JSONObject(params);
            int indexOf = speedLevels.indexOf(mode);
            Pdlog.m3273d(TAG, "speed level size " + speedLevels.size() + " speed array size " + maxSpeedArray.size() + " get mode " + mode + " level " + indexOf);
            double d = jSONObject3.getDouble("max_speed");
            if (indexOf == 0) {
                Double d2 = maxSpeedArray.get(1);
                Intrinsics.checkExpressionValueIsNotNull(d2, "maxSpeedArray[1]");
                if (d >= d2.doubleValue()) {
                    Double d3 = maxSpeedArray.get(indexOf);
                    Intrinsics.checkExpressionValueIsNotNull(d3, "maxSpeedArray[level]");
                    jSONObject3.put("max_speed", d3.doubleValue());
                    updateOldParams(jSONObject3, jSONObject, jSONObject2);
                    String str = fileName;
                    String jSONObject4 = jSONObject2.toString();
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject4, "curJson.toString()");
                    saveParamToSDCard(str, jSONObject4);
                    updateMaxSpeedArrays();
                    return;
                }
            }
            if (indexOf == speedLevels.size() - 1) {
                Double d4 = maxSpeedArray.get(indexOf - 1);
                Intrinsics.checkExpressionValueIsNotNull(d4, "maxSpeedArray[level - 1]");
                if (d <= d4.doubleValue()) {
                    Double d5 = maxSpeedArray.get(indexOf);
                    Intrinsics.checkExpressionValueIsNotNull(d5, "maxSpeedArray[level]");
                    jSONObject3.put("max_speed", d5.doubleValue());
                    updateOldParams(jSONObject3, jSONObject, jSONObject2);
                    String str2 = fileName;
                    String jSONObject42 = jSONObject2.toString();
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject42, "curJson.toString()");
                    saveParamToSDCard(str2, jSONObject42);
                    updateMaxSpeedArrays();
                    return;
                }
            }
            if (indexOf > 0 && indexOf < speedLevels.size() - 1) {
                Double d6 = maxSpeedArray.get(indexOf + 1);
                Intrinsics.checkExpressionValueIsNotNull(d6, "maxSpeedArray[level + 1]");
                if (d < d6.doubleValue()) {
                    Double d7 = maxSpeedArray.get(indexOf - 1);
                    Intrinsics.checkExpressionValueIsNotNull(d7, "maxSpeedArray[level - 1]");
                }
                Double d8 = maxSpeedArray.get(indexOf);
                Intrinsics.checkExpressionValueIsNotNull(d8, "maxSpeedArray[level]");
                jSONObject3.put("max_speed", d8.doubleValue());
            }
            updateOldParams(jSONObject3, jSONObject, jSONObject2);
            String str22 = fileName;
            String jSONObject422 = jSONObject2.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject422, "curJson.toString()");
            saveParamToSDCard(str22, jSONObject422);
            updateMaxSpeedArrays();
            return;
        }
        Pdlog.m3277w(TAG, "speed levels error " + mode);
    }

    public final void factoryReset(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        try {
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "factoryReset", e.getLocalizedMessage());
        }
        if (speedLevels.contains(name)) {
            JSONObject jSONObject = new JSONObject(readParamFromSDCard(fileName));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (Intrinsics.areEqual(name, keys.next())) {
                    keys.remove();
                }
            }
            String str = fileName;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "localJson.toString()");
            saveParamToSDCard(str, jSONObject2);
            updateMaxSpeedArrays();
        }
    }

    public final void sendCruiseParams() {
        Navigation.INSTANCE.updateConfig(getLocalModeParams(cruise_Mode));
    }

    public final void sendP2PParams() {
        Navigation.INSTANCE.updateConfig(getLocalModeParams(p2P_Mode));
    }

    public final void sendGoHomeParams() {
        Navigation.INSTANCE.updateConfig(getLocalModeParams(gohome_Mode));
    }

    public final String getParamBySpeedMode(String speedMode) {
        Intrinsics.checkParameterIsNotNull(speedMode, "speedMode");
        return getLocalModeParams(speedMode);
    }

    public final String getParamBySpeedModeLessThanCurrentMode(String speedMode, MoveMode moveMode) {
        String localModeParams;
        double parseDouble;
        Intrinsics.checkParameterIsNotNull(speedMode, "speedMode");
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        String localModeParams2 = getLocalModeParams(speedMode);
        double parseDouble2 = Double.parseDouble(new JSONObject(localModeParams2).get("max_speed").toString());
        int i = WhenMappings.$EnumSwitchMapping$1[moveMode.ordinal()];
        if (i == 1) {
            localModeParams = getLocalModeParams(p2P_Mode);
            parseDouble = Double.parseDouble(new JSONObject(getLocalModeParams(p2P_Mode)).get("max_speed").toString());
        } else if (i == 2) {
            localModeParams = getLocalModeParams(cruise_Mode);
            parseDouble = Double.parseDouble(new JSONObject(getLocalModeParams(cruise_Mode)).get("max_speed").toString());
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            localModeParams = getLocalModeParams(gohome_Mode);
            parseDouble = Double.parseDouble(new JSONObject(getLocalModeParams(gohome_Mode)).get("max_speed").toString());
        }
        boolean z = parseDouble < parseDouble2;
        if (z) {
            localModeParams2 = localModeParams;
        } else if (z) {
            throw new NoWhenBranchMatchedException();
        }
        Pdlog.m3273d(TAG, "currentModeSpeed " + parseDouble + " certainModeSpeed " + parseDouble2 + " finalParam " + localModeParams2);
        return localModeParams2;
    }

    public final void sendSteadyParams(MoveMode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Navigation.INSTANCE.updateConfig(getSteadyParams(mode));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.lang.String] */
    private final void initParamFirst() {
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                jSONObject.put("cruise_mode", "Normal");
                jSONObject.put("p2p_mode", "Normal");
                jSONObject.put("gohome_mode", "Normal");
            } catch (JSONException e) {
                Pdlog.m3274e(TAG, "initParamFirst ", e.getLocalizedMessage());
            }
        } finally {
            String str = fileName;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "json.toString()");
            saveParamToSDCard(str, jSONObject2);
        }
    }

    private final void initParams() {
        String str;
        String jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            try {
                String defaultSettingInCode = getDefaultSettingInCode();
                Pdlog.m3273d(TAG, "remote speed mode:", defaultSettingInCode);
                JSONObject jSONObject3 = new JSONObject(defaultSettingInCode);
                String readParamFromSDCard = readParamFromSDCard(fileName);
                Pdlog.m3273d(TAG, "local speed mode:", readParamFromSDCard);
                JSONObject jSONObject4 = new JSONObject(readParamFromSDCard);
                if (jSONObject4.has("cruise_mode")) {
                    String string = jSONObject4.getString("cruise_mode");
                    Intrinsics.checkExpressionValueIsNotNull(string, "localJson.getString(\"cruise_mode\")");
                    cruise_Mode = string;
                } else {
                    cruise_Mode = "Normal";
                }
                if (jSONObject4.has("p2p_mode")) {
                    String string2 = jSONObject4.getString("p2p_mode");
                    Intrinsics.checkExpressionValueIsNotNull(string2, "localJson.getString(\"p2p_mode\")");
                    p2P_Mode = string2;
                } else {
                    p2P_Mode = "Normal";
                }
                if (jSONObject4.has("gohome_mode")) {
                    String string3 = jSONObject4.getString("gohome_mode");
                    Intrinsics.checkExpressionValueIsNotNull(string3, "localJson.getString(\"gohome_mode\")");
                    gohome_Mode = string3;
                } else {
                    gohome_Mode = "Normal";
                }
                jSONObject2.put("cruise_mode", cruise_Mode);
                jSONObject2.put("p2p_mode", p2P_Mode);
                jSONObject2.put("gohome_mode", gohome_Mode);
                Iterator<String> keys = jSONObject4.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if ((!Intrinsics.areEqual(next, "cruise_mode")) && (!Intrinsics.areEqual(next, "p2p_mode")) && (!Intrinsics.areEqual(next, "gohome_mode"))) {
                        Iterator<String> keys2 = jSONObject4.getJSONObject(next).keys();
                        while (keys2.hasNext()) {
                            if (!jSONObject3.getJSONObject(next).has(keys2.next())) {
                                keys2.remove();
                            }
                        }
                    }
                }
                str = fileName;
                jSONObject = jSONObject4.toString();
            } catch (JSONException e) {
                Pdlog.m3274e(TAG, "initParams", e.getLocalizedMessage());
                str = fileName;
                jSONObject = jSONObject2.toString();
            }
            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "saveJson.toString()");
            saveParamToSDCard(str, jSONObject);
        } catch (Throwable th) {
            String str2 = fileName;
            String jSONObject5 = jSONObject2.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject5, "saveJson.toString()");
            saveParamToSDCard(str2, jSONObject5);
            throw th;
        }
    }

    private final void updateOldParams(JSONObject oldJson, JSONObject newJson, JSONObject saveJson) {
        Pdlog.m3273d(TAG, "local ", oldJson.toString(), " remote ", newJson.toString(), " save ", saveJson.toString());
        try {
            String string = newJson.getString("name");
            Iterator<String> keys = newJson.keys();
            JSONObject jSONObject = new JSONObject();
            if (saveJson.has(string)) {
                jSONObject = saveJson.getJSONObject(string);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "saveJson.getJSONObject(paramName)");
            }
            while (keys.hasNext()) {
                String next = keys.next();
                if ((!Intrinsics.areEqual(next, "name")) && oldJson.has(next) && (!Intrinsics.areEqual(oldJson.get(next), newJson.get(next)))) {
                    jSONObject.put(next, oldJson.get(next));
                }
            }
            if (jSONObject.length() != 0) {
                saveJson.put(string, jSONObject);
            }
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "updateOldParams ", e.getLocalizedMessage());
        }
    }

    private final String getLocalModeParams(String mode) {
        JSONObject jSONObject = (JSONObject) null;
        try {
            String defaultSettingInCode = getDefaultSettingInCode();
            Pdlog.m3273d(TAG, "remote speed mode:", defaultSettingInCode);
            JSONObject jSONObject2 = new JSONObject(defaultSettingInCode);
            String readParamFromSDCard = readParamFromSDCard(fileName);
            Pdlog.m3273d(TAG, "get Local Speed Mode", mode, " params:", readParamFromSDCard);
            jSONObject = jSONObject2.getJSONObject(mode);
            JSONObject jSONObject3 = new JSONObject(readParamFromSDCard);
            if (jSONObject == null) {
                Intrinsics.throwNpe();
            }
            updateLocalToRemote(jSONObject3, jSONObject);
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "getLocalModeParams ", e.getLocalizedMessage());
        }
        return String.valueOf(jSONObject);
    }

    private final String getSteadyParams(MoveMode mode) {
        String str;
        JSONObject jSONObject = (JSONObject) null;
        try {
            jSONObject = new JSONObject(Navigation.INSTANCE.getSteadyParams()).getJSONObject("Steady");
            JSONObject jSONObject2 = new JSONObject(readParamFromSDCard(fileName));
            int i = WhenMappings.$EnumSwitchMapping$2[mode.ordinal()];
            if (i == 1) {
                str = p2P_Mode;
            } else if (i == 2) {
                str = cruise_Mode;
            } else if (i == 3) {
                str = gohome_Mode;
            } else {
                str = p2P_Mode;
            }
            if (jSONObject.has("planner_type") && jSONObject2.has(str) && new JSONObject(jSONObject2.getString(str)).has("planner_type")) {
                jSONObject.put("planner_type", new JSONObject(jSONObject2.getString(str)).get("planner_type"));
            }
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "getSteadyParams ", e.getLocalizedMessage());
        }
        Pdlog.m3273d(TAG, "current steady param is " + String.valueOf(jSONObject));
        return String.valueOf(jSONObject);
    }

    private final void updateLocalToRemote(JSONObject local, JSONObject output) {
        try {
            String string = output.getString("name");
            if (local.has(string)) {
                Iterator<String> keys = local.getJSONObject(string).keys();
                boolean z = false;
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (Intrinsics.areEqual(local.getJSONObject(string).get(next), output.get(next))) {
                        keys.remove();
                        z = true;
                    } else {
                        output.put(next, local.getJSONObject(string).get(next));
                    }
                }
                if (z) {
                    String str = fileName;
                    String jSONObject = local.toString();
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject, "local.toString()");
                    saveParamToSDCard(str, jSONObject);
                }
            }
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "updateLocalToRemote ", e.getLocalizedMessage());
        }
    }

    private final String readParamFromSDCard(String str) {
        String readPathFile = readPathFile(filePath, str);
        if (readPathFile == null) {
            Pdlog.m3273d(TAG, "readParamFromSDCard get null");
            return readPathFile;
        }
        String str2 = readPathFile;
        boolean z = false;
        int length = str2.length() - 1;
        int i = 0;
        while (i <= length) {
            boolean z2 = str2.charAt(!z ? i : length) <= ' ';
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
        String obj = str2.subSequence(i, length + 1).toString();
        Pdlog.m3273d(TAG, "readParamFromSDCard get " + obj);
        return obj;
    }

    private final void saveParamToSDCard(String name, String param) {
        writePathFile(filePath, name, param);
    }

    private final String readPathFile(String pathName, String fileName2) {
        try {
            String str = pathName + File.separator + fileName2;
            File file = new File(str);
            if (!file.exists()) {
                Pdlog.m3274e(TAG, "readPathFile " + str + " do not exists");
                return null;
            }
            FileReader fileReader = new FileReader(file);
            char[] cArr = new char[(int) file.length()];
            fileReader.read(cArr);
            String str2 = new String(cArr);
            fileReader.close();
            return str2;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "readPathFile exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            return null;
        }
    }

    private final void writePathFile(String pathName, String fileName2, String data) {
        List emptyList;
        Object[] array;
        try {
            try {
                String str = File.separator;
                Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
                List<String> split = new Regex(str).split(pathName, 0);
                if (!split.isEmpty()) {
                    ListIterator<String> listIterator = split.listIterator(split.size());
                    while (listIterator.hasPrevious()) {
                        if (!(listIterator.previous().length() == 0)) {
                            emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList = CollectionsKt.emptyList();
                array = emptyList.toArray(new String[0]);
            } catch (IOException e) {
                Pdlog.m3274e(TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            }
            if (array != null) {
                StringBuffer stringBuffer = new StringBuffer();
                for (String str2 : (String[]) array) {
                    stringBuffer.append(str2);
                    stringBuffer.append(File.separator);
                    File file = new File(stringBuffer.toString());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
                FileWriter fileWriter = new FileWriter(new File(pathName + File.separator + fileName2));
                fileWriter.write(data);
                fileWriter.close();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } finally {
            Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, false);
        }
    }

    private final void updateMaxSpeedArrays() {
        JSONObject jSONObject = new JSONObject(readParamFromSDCard(fileName));
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (speedLevels.contains(next)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                if (jSONObject2.has("max_speed")) {
                    maxSpeedArray.set(speedLevels.indexOf(next), Double.valueOf(jSONObject2.getDouble("max_speed")));
                }
            }
        }
        Pdlog.m3273d(TAG, "speed levels " + Arrays.toString(speedLevels.toArray()));
        Pdlog.m3273d(TAG, "max speed list " + Arrays.toString(maxSpeedArray.toArray()));
    }

    public final void updateObsDis(double dis) {
        JSONObject jSONObject = new JSONObject(readParamFromSDCard(fileName));
        new JSONObject();
        try {
            try {
                Set<String> mutableSetOf = SetsKt.mutableSetOf(jSONObject.getString("cruise_mode"));
                mutableSetOf.add(jSONObject.getString("p2p_mode"));
                mutableSetOf.add(jSONObject.getString("gohome_mode"));
                for (String str : mutableSetOf) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("obs_dis", dis);
                    jSONObject.put(str, jSONObject2);
                }
            } catch (JSONException e) {
                Pdlog.m3274e(TAG, e.getLocalizedMessage());
            }
        } finally {
            String str2 = fileName;
            String jSONObject3 = jSONObject.toString();
            Intrinsics.checkExpressionValueIsNotNull(jSONObject3, "curJson.toString()");
            saveParamToSDCard(str2, jSONObject3);
        }
    }
}
