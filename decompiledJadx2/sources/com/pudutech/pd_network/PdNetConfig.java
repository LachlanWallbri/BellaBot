package com.pudutech.pd_network;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.bean.DeviceType;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.utils.ProcessUtil;
import java.io.File;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PdNetConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0006\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0004J\u0016\u0010C\u001a\u00020A2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010D\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010E\u001a\u00020A2\u0006\u0010F\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010*\u001a\u00020)2\u0006\u0010\u0006\u001a\u00020)8F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010+\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00101\"\u0004\b6\u00103R\u001a\u00107\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010+\"\u0004\b9\u0010.R\u001a\u0010:\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00101\"\u0004\b<\u00103R\u001a\u0010=\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u00101\"\u0004\b?\u00103¨\u0006G"}, m3961d2 = {"Lcom/pudutech/pd_network/PdNetConfig;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "<set-?>", "Lcom/pudutech/pd_network/bean/GatewayBean;", "baseGatewayBean", "getBaseGatewayBean", "()Lcom/pudutech/pd_network/bean/GatewayBean;", "builder", "Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "getBuilder", "()Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "setBuilder", "(Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "deviceType", "Lcom/pudutech/pd_network/bean/DeviceType;", "getDeviceType", "()Lcom/pudutech/pd_network/bean/DeviceType;", "setDeviceType", "(Lcom/pudutech/pd_network/bean/DeviceType;)V", "downloadDir", "Ljava/io/File;", "getDownloadDir", "()Ljava/io/File;", "setDownloadDir", "(Ljava/io/File;)V", "environment", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "getEnvironment", "()Lcom/pudutech/pd_network/bean/NetEnvironment;", "setEnvironment", "(Lcom/pudutech/pd_network/bean/NetEnvironment;)V", "", "isDebug", "()Z", "isMainProcess", "setMainProcess", "(Z)V", "language", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "processName", "getProcessName", "setProcessName", "reportAbility", "getReportAbility", "setReportAbility", AIUIConstant.KEY_TAG, "getTag", "setTag", "versionName", "getVersionName", "setVersionName", "baseUrl", "", "url", "init", "isMainProcessFun", "throwOrLog", "log", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdNetConfig {
    public static final PdNetConfig INSTANCE;
    private static final String TAG;
    private static GatewayBean baseGatewayBean;
    public static PdNetworkManagerBuilder builder;
    public static Context context;
    private static DeviceType deviceType;
    public static File downloadDir;
    private static NetEnvironment environment;
    private static boolean isDebug;
    private static boolean isMainProcess;
    private static String language;
    private static String processName;
    private static boolean reportAbility;
    private static String tag;
    private static String versionName;

    static {
        PdNetConfig pdNetConfig = new PdNetConfig();
        INSTANCE = pdNetConfig;
        TAG = pdNetConfig.getClass().getSimpleName();
        environment = NetEnvironment.Product.INSTANCE;
        tag = "product";
        language = "en-us";
        isMainProcess = true;
        processName = "";
        deviceType = DeviceType.Robot;
        reportAbility = true;
        versionName = "";
        isDebug = true;
    }

    private PdNetConfig() {
    }

    public final NetEnvironment getEnvironment() {
        return environment;
    }

    public final void setEnvironment(NetEnvironment netEnvironment) {
        Intrinsics.checkParameterIsNotNull(netEnvironment, "<set-?>");
        environment = netEnvironment;
    }

    public final String getTag() {
        return tag;
    }

    public final void setTag(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        tag = str;
    }

    public final String getLanguage() {
        return language;
    }

    public final void setLanguage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        language = str;
    }

    public final boolean isMainProcess() {
        return isMainProcess;
    }

    public final void setMainProcess(boolean z) {
        isMainProcess = z;
    }

    public final String getProcessName() {
        return processName;
    }

    public final void setProcessName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        processName = str;
    }

    public final DeviceType getDeviceType() {
        return deviceType;
    }

    public final void setDeviceType(DeviceType deviceType2) {
        Intrinsics.checkParameterIsNotNull(deviceType2, "<set-?>");
        deviceType = deviceType2;
    }

    public final boolean getReportAbility() {
        return reportAbility;
    }

    public final void setReportAbility(boolean z) {
        reportAbility = z;
    }

    public final Context getContext() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final PdNetworkManagerBuilder getBuilder() {
        PdNetworkManagerBuilder pdNetworkManagerBuilder = builder;
        if (pdNetworkManagerBuilder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        return pdNetworkManagerBuilder;
    }

    public final void setBuilder(PdNetworkManagerBuilder pdNetworkManagerBuilder) {
        Intrinsics.checkParameterIsNotNull(pdNetworkManagerBuilder, "<set-?>");
        builder = pdNetworkManagerBuilder;
    }

    public final GatewayBean getBaseGatewayBean() {
        GatewayBean gatewayBean = baseGatewayBean;
        if (gatewayBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseGatewayBean");
        }
        return gatewayBean;
    }

    public final String getVersionName() {
        return versionName;
    }

    public final void setVersionName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        versionName = str;
    }

    public final boolean isDebug() {
        return !Intrinsics.areEqual(environment, NetEnvironment.Product.INSTANCE);
    }

    public final File getDownloadDir() {
        File file = downloadDir;
        if (file == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadDir");
        }
        return file;
    }

    public final void setDownloadDir(File file) {
        Intrinsics.checkParameterIsNotNull(file, "<set-?>");
        downloadDir = file;
    }

    public final void init(Context context2, PdNetworkManagerBuilder builder2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(builder2, "builder");
        environment = builder2.getEnvironment();
        builder = builder2;
        context = context2;
        deviceType = builder2.getDeviceType();
        String currentProcessName = ProcessUtil.getCurrentProcessName(context2);
        Intrinsics.checkExpressionValueIsNotNull(currentProcessName, "ProcessUtil.getCurrentProcessName(context)");
        processName = currentProcessName;
        reportAbility = builder2.getReportAbility();
        NetEnvironment netEnvironment = environment;
        if (Intrinsics.areEqual(netEnvironment, NetEnvironment.Dev.INSTANCE)) {
            tag = "dev";
            baseGatewayBean = GatewayBean.INSTANCE.fromHost("dev", "https://robot-biz-dev.pudutech.com");
        } else if (Intrinsics.areEqual(netEnvironment, NetEnvironment.PreTest.INSTANCE)) {
            tag = "preTest";
            baseGatewayBean = GatewayBean.INSTANCE.fromHost("preTest", "https://robot-biz-pre-test.pudutech.com");
        } else if (Intrinsics.areEqual(netEnvironment, NetEnvironment.Test.INSTANCE)) {
            tag = "test";
            baseGatewayBean = GatewayBean.INSTANCE.fromHost("test", "https://robot-biz-hongkong-test.pudutech.com");
        } else if (Intrinsics.areEqual(netEnvironment, NetEnvironment.Product.INSTANCE)) {
            tag = "product";
            baseGatewayBean = GatewayBean.INSTANCE.fromHost("product", "https://robot-biz-hongkong.pudutech.com");
        } else {
            tag = "product";
            baseGatewayBean = GatewayBean.INSTANCE.fromHost("product", "https://robot-biz-hongkong.pudutech.com");
        }
        if (builder2.getForceBaseUrl().length() > 0) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog.mo3280i(TAG2, "forceBaseUrl > " + builder2.getForceBaseUrl());
            GatewayBean gatewayBean = baseGatewayBean;
            if (gatewayBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("baseGatewayBean");
            }
            gatewayBean.setHost(builder2.getForceBaseUrl());
            GatewayBean gatewayBean2 = baseGatewayBean;
            if (gatewayBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("baseGatewayBean");
            }
            gatewayBean2.setAuth_host(builder2.getForceBaseUrl());
        }
        downloadDir = new File(context2.getFilesDir(), "download");
        File file = downloadDir;
        if (file == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadDir");
        }
        if (!file.exists()) {
            File file2 = downloadDir;
            if (file2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadDir");
            }
            file2.mkdirs();
        }
        Resources resources = context2.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        Locale locale = resources.getConfiguration().locale;
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
        sb.append(locale.getLanguage());
        sb.append("-");
        sb.append(locale.getCountry());
        language = sb.toString();
        isMainProcess = isMainProcessFun(context2);
        String str = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "context.packageManager.g…  0\n        ).versionName");
        versionName = str;
        NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n            PdNetworkManagerBaseConfig > \n            context:");
        sb2.append(context2);
        sb2.append(" \n            environment:");
        sb2.append(environment);
        sb2.append(" \n            language:");
        sb2.append(language);
        sb2.append(" \n            isMainProcess:");
        sb2.append(isMainProcess);
        sb2.append(" \n            downloadDir:");
        File file3 = downloadDir;
        if (file3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadDir");
        }
        sb2.append(file3);
        sb2.append(" \n            baseUrl:");
        GatewayBean gatewayBean3 = baseGatewayBean;
        if (gatewayBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseGatewayBean");
        }
        sb2.append(gatewayBean3.getHost());
        sb2.append(" \n            tag:");
        sb2.append(tag);
        sb2.append(" \n            versionName:");
        sb2.append(versionName);
        sb2.append(" \n            deviceType:");
        sb2.append(deviceType);
        sb2.append(" \n            reportAbility:");
        sb2.append(reportAbility);
        sb2.append(" \n        ");
        netWorkLog2.mo3280i(TAG3, StringsKt.trimIndent(sb2.toString()));
    }

    public final void baseUrl(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        GatewayBean gatewayBean = baseGatewayBean;
        if (gatewayBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseGatewayBean");
        }
        gatewayBean.setHost(url);
        GatewayBean gatewayBean2 = baseGatewayBean;
        if (gatewayBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseGatewayBean");
        }
        gatewayBean2.setAuth_host(url);
    }

    private final boolean isMainProcessFun(Context context2) {
        Object systemService = context2.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        Intrinsics.checkExpressionValueIsNotNull(runningAppProcesses, "am.getRunningAppProcesses()");
        String packageName = context2.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && Intrinsics.areEqual(packageName, runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public final void throwOrLog(String log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        if (isDebug()) {
            throw new Exception(log);
        }
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3279e(TAG2, log);
    }
}
