package com.pudutech.robot.module.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseApiNoNumReport;
import com.pudutech.robot.module.report.protocol.BaseApiReport;
import com.pudutech.robot.module.report.protocol.BaseV2Report;
import com.pudutech.robot.module.report.protocol.BaseV4Report;
import com.pudutech.robot.module.report.protocol.bean.RobotPositionInfo;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PuduReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002TUB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u00108\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u0004H\u0002J\u0018\u0010:\u001a\u00020;2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010#\u001a\u00020$J1\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020>2\b\b\u0002\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020\u00042\b\b\u0002\u0010A\u001a\u00020$H\u0000¢\u0006\u0002\bBJ\u0018\u0010<\u001a\u00020;2\b\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\u0004J'\u0010D\u001a\u00020;2\u0006\u0010=\u001a\u00020E2\b\b\u0002\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020\u0004H\u0000¢\u0006\u0002\bFJ'\u0010G\u001a\u00020;2\u0006\u0010=\u001a\u00020H2\b\b\u0002\u0010?\u001a\u00020$2\u0006\u0010@\u001a\u00020\u0004H\u0000¢\u0006\u0002\bIJ\u001a\u0010J\u001a\u00020;2\b\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\u0004H\u0002J\u001a\u0010K\u001a\u00020;2\b\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\u0004H\u0002J\u001a\u0010L\u001a\u00020;2\b\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\u0004H\u0002J\u001d\u0010M\u001a\u00020;2\u0006\u0010=\u001a\u00020N2\u0006\u0010@\u001a\u00020\u0004H\u0000¢\u0006\u0002\bOJ\u001a\u0010M\u001a\u00020;2\b\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\u0004H\u0002J\u001a\u0010P\u001a\u00020;2\b\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\u0004H\u0002J\u000e\u0010Q\u001a\u00020;2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010R\u001a\u00020;2\u0006\u0010S\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u00020(X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\u001a\u00105\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010/\"\u0004\b7\u00101¨\u0006V"}, m3961d2 = {"Lcom/pudutech/robot/module/report/PuduReportManager;", "", "()V", "API_V2", "", "API_V4", "FILE_NAME", "KEY_CNT", "PRODUCT_URL", "TAG", "TEST_URL", "V4_PRODUCT_URL", "V4_TEST_URL", "cnt", "", "context", "Landroid/content/Context;", "getContext$module_robot_report_release", "()Landroid/content/Context;", "setContext$module_robot_report_release", "(Landroid/content/Context;)V", "gson", "Lcom/google/gson/Gson;", "iReportDataProvide", "Lcom/pudutech/robot/module/report/PuduReportManager$IReportDataProvide;", "getIReportDataProvide$module_robot_report_release", "()Lcom/pudutech/robot/module/report/PuduReportManager$IReportDataProvide;", "setIReportDataProvide$module_robot_report_release", "(Lcom/pudutech/robot/module/report/PuduReportManager$IReportDataProvide;)V", "iRobotPositionProvider", "Lcom/pudutech/robot/module/report/PuduReportManager$IRobotPositionProvider;", "getIRobotPositionProvider$module_robot_report_release", "()Lcom/pudutech/robot/module/report/PuduReportManager$IRobotPositionProvider;", "setIRobotPositionProvider$module_robot_report_release", "(Lcom/pudutech/robot/module/report/PuduReportManager$IRobotPositionProvider;)V", "isTest", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences$module_robot_report_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$module_robot_report_release", "(Landroid/content/SharedPreferences;)V", "sysBuildId", "getSysBuildId$module_robot_report_release", "()Ljava/lang/String;", "setSysBuildId$module_robot_report_release", "(Ljava/lang/String;)V", "systemVersionCode", "getSystemVersionCode$module_robot_report_release", "setSystemVersionCode$module_robot_report_release", "versionName", "getVersionName$module_robot_report_release", "setVersionName$module_robot_report_release", "getProperty", TransferTable.COLUMN_KEY, "init", "", "report", "obj", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "save_to_history", "api", "b", "report$module_robot_report_release", "info", "reportApi", "Lcom/pudutech/robot/module/report/protocol/BaseApiReport;", "reportApi$module_robot_report_release", "reportNoNumApi", "Lcom/pudutech/robot/module/report/protocol/BaseApiNoNumReport;", "reportNoNumApi$module_robot_report_release", "reportOrThrown", "reportOrThrownV4", "reportOrThrownWrap", "reportV4", "Lcom/pudutech/robot/module/report/protocol/BaseV4Report;", "reportV4$module_robot_report_release", "reportWrap", "setReportDateProvide", "setRobotPositionProvider", "provider", "IReportDataProvide", "IRobotPositionProvider", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PuduReportManager {
    public static final String API_V2 = "/api/v2/report";
    public static final String API_V4 = "/api/v4/report";
    private static final String PRODUCT_URL = "https://rmp.pudutech.com/api/v2/report";
    private static final String TEST_URL = "http://rmp-test.pudutech.com/api/v2/report";
    private static final String V4_PRODUCT_URL = "https://rmp.pudutech.com/api/v4/report";
    private static final String V4_TEST_URL = "http://rmp-test.pudutech.com/api/v4/report";
    private static int cnt;
    public static Context context;
    private static IReportDataProvide iReportDataProvide;
    private static IRobotPositionProvider iRobotPositionProvider;
    private static boolean isTest;
    public static SharedPreferences sharedPreferences;
    public static final PuduReportManager INSTANCE = new PuduReportManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String FILE_NAME = FILE_NAME;
    private static final String FILE_NAME = FILE_NAME;
    private static final String KEY_CNT = KEY_CNT;
    private static final String KEY_CNT = KEY_CNT;
    private static final Gson gson = new Gson();
    private static String versionName = "";
    private static String systemVersionCode = "";
    private static String sysBuildId = "";
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: PuduReportManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\bH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/module/report/PuduReportManager$IReportDataProvide;", "", "getHardVersion", "", "getMac", "getPowerPercent", "", "isCharging", "", "isWorking", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface IReportDataProvide {
        String getHardVersion();

        String getMac();

        double getPowerPercent();

        boolean isCharging();

        boolean isWorking();
    }

    /* compiled from: PuduReportManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/robot/module/report/PuduReportManager$IRobotPositionProvider;", "", "getPositionInfo", "Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface IRobotPositionProvider {
        RobotPositionInfo getPositionInfo();
    }

    private PuduReportManager() {
    }

    public final Context getContext$module_robot_report_release() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext$module_robot_report_release(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final IReportDataProvide getIReportDataProvide$module_robot_report_release() {
        return iReportDataProvide;
    }

    public final void setIReportDataProvide$module_robot_report_release(IReportDataProvide iReportDataProvide2) {
        iReportDataProvide = iReportDataProvide2;
    }

    public final IRobotPositionProvider getIRobotPositionProvider$module_robot_report_release() {
        return iRobotPositionProvider;
    }

    public final void setIRobotPositionProvider$module_robot_report_release(IRobotPositionProvider iRobotPositionProvider2) {
        iRobotPositionProvider = iRobotPositionProvider2;
    }

    public final SharedPreferences getSharedPreferences$module_robot_report_release() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences2;
    }

    public final void setSharedPreferences$module_robot_report_release(SharedPreferences sharedPreferences2) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "<set-?>");
        sharedPreferences = sharedPreferences2;
    }

    public final String getVersionName$module_robot_report_release() {
        return versionName;
    }

    public final void setVersionName$module_robot_report_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        versionName = str;
    }

    public final String getSystemVersionCode$module_robot_report_release() {
        return systemVersionCode;
    }

    public final void setSystemVersionCode$module_robot_report_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        systemVersionCode = str;
    }

    public final String getSysBuildId$module_robot_report_release() {
        return sysBuildId;
    }

    public final void setSysBuildId$module_robot_report_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        sysBuildId = str;
    }

    public static /* synthetic */ void init$default(PuduReportManager puduReportManager, Context context2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        puduReportManager.init(context2, z);
    }

    public final void init(Context context2, boolean isTest2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
        isTest = isTest2;
        SharedPreferences sharedPreferences2 = context2.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences2, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        sharedPreferences = sharedPreferences2;
        SharedPreferences sharedPreferences3 = sharedPreferences;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        cnt = sharedPreferences3.getInt(KEY_CNT, 0);
        String str = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "context.packageManager.g…ckageName, 0).versionName");
        versionName = str;
        systemVersionCode = getProperty(context2, "ro.pudutech.version_code");
        sysBuildId = getProperty(context2, "ro.build.id");
        if (StringsKt.isBlank(sysBuildId)) {
            sysBuildId = "null";
        }
    }

    public final void setReportDateProvide(IReportDataProvide iReportDataProvide2) {
        Intrinsics.checkParameterIsNotNull(iReportDataProvide2, "iReportDataProvide");
        iReportDataProvide = iReportDataProvide2;
    }

    public final void setRobotPositionProvider(IRobotPositionProvider provider) {
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        iRobotPositionProvider = provider;
    }

    public final void report(String info, String api) {
        Intrinsics.checkParameterIsNotNull(api, "api");
        reportWrap(info, api);
    }

    private final void reportOrThrown(String info, String api) {
        if (info != null) {
            INSTANCE.reportOrThrownWrap(info, api);
        }
    }

    private final void reportWrap(String info, String api) {
        PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
        GatewayName.ROBOT_COLL robot_coll = GatewayName.ROBOT_COLL.INSTANCE;
        if (info == null) {
            Intrinsics.throwNpe();
        }
        pdNetworkManager.report(robot_coll, api, info);
    }

    private final void reportOrThrownWrap(String info, String api) {
        PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
        GatewayName.ROBOT_COLL robot_coll = GatewayName.ROBOT_COLL.INSTANCE;
        if (info == null) {
            Intrinsics.throwNpe();
        }
        pdNetworkManager.report(robot_coll, api, info);
    }

    private final void reportV4(String info, String api) {
        report(info, api);
    }

    private final void reportOrThrownV4(String info, String api) {
        reportOrThrownWrap(info, api);
    }

    public final void reportV4$module_robot_report_release(BaseV4Report obj, String api) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Intrinsics.checkParameterIsNotNull(api, "api");
        IReportDataProvide iReportDataProvide2 = iReportDataProvide;
        obj.setMac(iReportDataProvide2 != null ? iReportDataProvide2.getMac() : null);
        obj.setTimestamp(System.currentTimeMillis() / 1000);
        try {
            reportV4(gson.toJson(obj), api);
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3279e(TAG, "reportV4 : " + Log.getStackTraceString(e));
        }
    }

    public static /* synthetic */ void report$module_robot_report_release$default(PuduReportManager puduReportManager, BaseV2Report baseV2Report, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        puduReportManager.report$module_robot_report_release(baseV2Report, z, str, z2);
    }

    public final void report$module_robot_report_release(BaseV2Report obj, boolean save_to_history, String api, boolean b) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Intrinsics.checkParameterIsNotNull(api, "api");
        int i = cnt;
        if (i >= 2147483547) {
            cnt = 0;
        } else {
            cnt = i + 1;
        }
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        sharedPreferences2.edit().putInt(KEY_CNT, cnt).apply();
        IReportDataProvide iReportDataProvide2 = iReportDataProvide;
        obj.setMac(iReportDataProvide2 != null ? iReportDataProvide2.getMac() : null);
        obj.setReport_number(cnt);
        IReportDataProvide iReportDataProvide3 = iReportDataProvide;
        obj.setHardver(iReportDataProvide3 != null ? iReportDataProvide3.getHardVersion() : null);
        obj.setSoftver(versionName);
        obj.setTimestamp(System.currentTimeMillis() / 1000);
        try {
            String json = gson.toJson(obj);
            NetWorkLog.INSTANCE.mo3278d(TAG, "report to could. " + json);
            if (save_to_history) {
                report(json, api);
            } else {
                reportOrThrown(json, api);
            }
        } catch (Exception e) {
            e.printStackTrace();
            NetWorkLog.INSTANCE.mo3279e(TAG, "obj=" + obj.getType() + ' ' + e);
        }
    }

    public static /* synthetic */ void reportApi$module_robot_report_release$default(PuduReportManager puduReportManager, BaseApiReport baseApiReport, boolean z, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        puduReportManager.reportApi$module_robot_report_release(baseApiReport, z, str);
    }

    public final void reportApi$module_robot_report_release(BaseApiReport obj, boolean save_to_history, String api) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Intrinsics.checkParameterIsNotNull(api, "api");
        int i = cnt;
        if (i >= 2147483547) {
            cnt = 0;
        } else {
            cnt = i + 1;
        }
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        sharedPreferences2.edit().putInt(KEY_CNT, cnt).apply();
        obj.setReport_number(cnt);
        IReportDataProvide iReportDataProvide2 = iReportDataProvide;
        obj.setMac(iReportDataProvide2 != null ? iReportDataProvide2.getMac() : null);
        IReportDataProvide iReportDataProvide3 = iReportDataProvide;
        obj.setHardver(iReportDataProvide3 != null ? iReportDataProvide3.getHardVersion() : null);
        obj.setSoftver(versionName);
        obj.setTimestamp(System.currentTimeMillis() / 1000);
        try {
            String json = gson.toJson(obj);
            NetWorkLog.INSTANCE.mo3278d(TAG, "reportApi report to could" + json);
            if (save_to_history) {
                report(json, api);
            } else {
                reportOrThrown(json, api);
            }
        } catch (Exception e) {
            e.printStackTrace();
            NetWorkLog.INSTANCE.mo3279e(TAG, "obj=" + obj + ' ' + e);
        }
    }

    public static /* synthetic */ void reportNoNumApi$module_robot_report_release$default(PuduReportManager puduReportManager, BaseApiNoNumReport baseApiNoNumReport, boolean z, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        puduReportManager.reportNoNumApi$module_robot_report_release(baseApiNoNumReport, z, str);
    }

    public final void reportNoNumApi$module_robot_report_release(BaseApiNoNumReport obj, boolean save_to_history, String api) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Intrinsics.checkParameterIsNotNull(api, "api");
        IReportDataProvide iReportDataProvide2 = iReportDataProvide;
        obj.setMac(iReportDataProvide2 != null ? iReportDataProvide2.getMac() : null);
        IReportDataProvide iReportDataProvide3 = iReportDataProvide;
        obj.setHardver(iReportDataProvide3 != null ? iReportDataProvide3.getHardVersion() : null);
        obj.setSoftver(versionName);
        obj.setTimestamp(System.currentTimeMillis() / 1000);
        try {
            String json = gson.toJson(obj);
            NetWorkLog.INSTANCE.mo3278d(TAG, "reportApi report to could" + json);
            if (save_to_history) {
                report(json, api);
            } else {
                reportOrThrown(json, api);
            }
        } catch (Exception e) {
            e.printStackTrace();
            NetWorkLog.INSTANCE.mo3279e(TAG, "obj=" + obj + ' ' + e);
        }
    }

    private final String getProperty(Context context2, String key) {
        try {
            Class<?> loadClass = context2.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class);
            Intrinsics.checkExpressionValueIsNotNull(method, "systemProperties.getMeth…get\", String::class.java)");
            Object invoke = method.invoke(loadClass, key);
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3279e("SystemProperty get e:", " stack:" + e.getMessage());
            return "";
        }
    }
}
