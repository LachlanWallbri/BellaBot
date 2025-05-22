package com.pudutech.pd_network.sn;

import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.ExtKt;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SNUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u001e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/pd_network/sn/SNUtils;", "", "()V", "AES_KEY", "", "TAG", "kotlin.jvm.PlatformType", "gson", "Lcom/google/gson/Gson;", "path1", "path2", "availableSN", "", "checkKey", TransferTable.COLUMN_KEY, "configKey", "deleteSN", "", "getSN", "Lcom/pudutech/pd_network/sn/SNBean;", "getSNFromFile", "path", "saveFile", AIUIConstant.KEY_CONTENT, "saveSN", "sn", "packageName", "time", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SNUtils {
    private static final String AES_KEY;
    public static final SNUtils INSTANCE;
    private static final String TAG;
    private static final Gson gson;
    private static final String path1 = "/sdcard/pudu/config/common_test.xml";
    private static final String path2 = "/sdcard/pudu/data/simple.xml";

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0027, code lost:
    
        if (r0 != null) goto L12;
     */
    static {
        String str;
        SNUtils sNUtils = new SNUtils();
        INSTANCE = sNUtils;
        TAG = sNUtils.getClass().getSimpleName();
        String md5 = ExtKt.md5("pudu-tech");
        if (md5 != null) {
            if (md5 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str = md5.substring(0, 16);
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        str = "";
        AES_KEY = str;
        gson = new Gson();
    }

    private SNUtils() {
    }

    public final void saveSN(String sn, String packageName, String time) {
        String aesEncrypt;
        Intrinsics.checkParameterIsNotNull(sn, "sn");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(time, "time");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "saveSN > sn:" + sn + " packageName:" + packageName + " time:" + time + ' ');
        SNBean sNBean = new SNBean(sn, packageName, time, null, 8, null);
        String json = gson.toJson(sNBean);
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(snBean)");
        String md5 = ExtKt.md5(json);
        if (md5 == null) {
            md5 = "";
        }
        sNBean.setRandom(md5);
        String preSnJson = gson.toJson(sNBean);
        Intrinsics.checkExpressionValueIsNotNull(preSnJson, "preSnJson");
        String md52 = ExtKt.md5(preSnJson);
        aesEncrypt = SNUtilsKt.aesEncrypt(preSnJson, AES_KEY);
        new File(ConfigUtil.CONFIG_DIR).mkdirs();
        new File("/sdcard/pudu/data/").mkdirs();
        String saveSN1 = gson.toJson(MapsKt.mapOf(TuplesKt.m3968to(configKey(path1), aesEncrypt), TuplesKt.m3968to(checkKey(path1), md52)));
        String saveSN2 = gson.toJson(MapsKt.mapOf(TuplesKt.m3968to(configKey(path2), aesEncrypt), TuplesKt.m3968to(checkKey(path2), md52)));
        Intrinsics.checkExpressionValueIsNotNull(saveSN1, "saveSN1");
        saveFile(saveSN1, path1);
        Intrinsics.checkExpressionValueIsNotNull(saveSN2, "saveSN2");
        saveFile(saveSN2, path2);
    }

    private final void saveFile(String content, String path) {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "saveFile > content:" + content + " path:" + path + ' ');
        File file = new File(path);
        file.deleteOnExit();
        file.createNewFile();
        FilesKt.writeText$default(file, content, null, 2, null);
    }

    public final void deleteSN() {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "deleteSN");
        try {
            new File(path1).delete();
            new File(path2).delete();
        } catch (Exception e) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
            netWorkLog2.mo3279e(TAG3, "deleteSN.error " + Log.getStackTraceString(e));
        }
    }

    public final SNBean getSN() {
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "getSN");
        try {
            SNBean sNFromFile = getSNFromFile(path1);
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
            netWorkLog2.mo3280i(TAG3, "getSN sn1 = " + sNFromFile);
            SNBean sNFromFile2 = getSNFromFile(path2);
            NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
            String TAG4 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG4, "TAG");
            netWorkLog3.mo3280i(TAG4, "getSN sn2 = " + sNFromFile2);
            if (!(!Intrinsics.areEqual(sNFromFile, sNFromFile2))) {
                return sNFromFile;
            }
            throw new Exception("getSN.error sn1 != sn2 \n sn1 = " + sNFromFile + " \n sn2 = " + sNFromFile2);
        } catch (Exception e) {
            NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
            String TAG5 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG5, "TAG");
            netWorkLog4.mo3281w(TAG5, "getSN.error " + Log.getStackTraceString(e));
            deleteSN();
            return (SNBean) null;
        }
    }

    private final SNBean getSNFromFile(String path) {
        String aesDecrypt;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "getSNFromFile > path:" + path + ' ');
        String readText$default = FilesKt.readText$default(new File(path), null, 1, null);
        NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
        netWorkLog2.mo3280i(TAG3, "getSNFromFile.text " + readText$default);
        JSONObject jSONObject = new JSONObject(readText$default);
        String sn = jSONObject.getString(INSTANCE.configKey(path));
        String string = jSONObject.getString(INSTANCE.checkKey(path));
        Intrinsics.checkExpressionValueIsNotNull(sn, "sn");
        aesDecrypt = SNUtilsKt.aesDecrypt(sn, AES_KEY);
        if (!Intrinsics.areEqual(ExtKt.md5(aesDecrypt), string)) {
            throw new Exception("getSNFromFile.error md5 check error");
        }
        SNBean sNBean = (SNBean) gson.fromJson(aesDecrypt, SNBean.class);
        String random = sNBean.getRandom();
        sNBean.setRandom("");
        Intrinsics.checkExpressionValueIsNotNull(gson.toJson(sNBean), "gson.toJson(snBean)");
        if (!Intrinsics.areEqual(ExtKt.md5(r1), random)) {
            throw new Exception("getSNFromFile.error random check error");
        }
        return sNBean;
    }

    public final boolean availableSN() {
        return getSN() != null;
    }

    private final String configKey(String key) {
        return ExtKt.md5("config" + key);
    }

    private final String checkKey(String key) {
        return ExtKt.md5("check" + key);
    }
}
