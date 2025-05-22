package com.pudutech.bumblebee.robot_ui.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.CrossProcessTask;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.presenter.robot_open_task.provider.PositionDataManager;
import com.pudutech.bumblebee.robot_ui.config.UrlManager;
import com.pudutech.bumblebee.robot_ui.manager.LedLightManager;
import com.pudutech.bumblebee.robot_ui.manager.LoRaManager;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.bean.RobotPositionInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* compiled from: AppUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002)*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0013J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0013J\u0010\u0010\u001b\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0013J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0010\u0010\u001d\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0018\u0010\"\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010$\u001a\u0004\u0018\u00010\u0004J\u0010\u0010%\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010\u0004J\u000e\u0010'\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020(R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\b¨\u0006+"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/AppUtil;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "diskPercent", "getDiskPercent", "()Ljava/lang/String;", "fileItemList", "", "sdcardPercent", "getSdcardPercent", "singleThreadEventExecutor", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "systemVersion", "getSystemVersion", "getAppMetaData", "context", "Landroid/content/Context;", TransferTable.COLUMN_KEY, "getAppVersionCode", "getApplicationName", "getAvailMemory", "ctx", "getMac", "getMemoryPercent", "getTotalMemory", "getVersionCode", "getVersionName", "initMain", "", InternalConstant.KEY_APP, "Landroid/app/Application;", "installApp", "", "path", "isLegalApk", "apkPath", "startDebugFunction", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "DefIReportDataProvide", "DefRobotPositionProvider", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AppUtil {
    public static final AppUtil INSTANCE = new AppUtil();
    private static final String TAG = AppUtil.class.getSimpleName();
    private static final ExecutorCoroutineDispatcher singleThreadEventExecutor = ThreadPoolDispatcherKt.newSingleThreadContext("procState");
    private static final Set<String> fileItemList = new HashSet();

    static {
        fileItemList.add("META-INF/CERT.RSA");
        fileItemList.add("META-INF/CERT.SF");
        fileItemList.add("META-INF/MANIFEST.MF");
        fileItemList.add("AndroidManifest.xml");
        fileItemList.add("resources.arsc");
        fileItemList.add("classes.dex");
    }

    private AppUtil() {
    }

    public static final /* synthetic */ String access$getTAG$p(AppUtil appUtil) {
        return TAG;
    }

    public final String getApplicationName(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        CharSequence loadLabel = context.getApplicationInfo().loadLabel(context.getPackageManager());
        if (loadLabel == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        String str = (String) loadLabel;
        if (str == null || !(!Intrinsics.areEqual("", str))) {
            return null;
        }
        return str;
    }

    public final String getSystemVersion() {
        return Build.MODEL + "," + Build.VERSION.SDK_INT + "," + Build.VERSION.RELEASE;
    }

    public final String getMac(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("wifi");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        WifiInfo connectionInfo = ((WifiManager) systemService).getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getMacAddress() == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        Intrinsics.checkExpressionValueIsNotNull(macAddress, "info.macAddress");
        String replace = new Regex(":").replace(macAddress, "");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (replace == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = replace.toLowerCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    public final String getVersionName(Context context) {
        String str = "";
        if (context != null) {
            try {
                synchronized (context) {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 65536);
                    if (packageInfo != null) {
                        String str2 = packageInfo.versionName;
                        Intrinsics.checkExpressionValueIsNotNull(str2, "pi.versionName");
                        str = str2;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Log.i(TAG, "getVersionName1()", e);
            }
        }
        return str;
    }

    public final String getVersionCode(Context context) {
        String str = (String) null;
        if (context == null) {
            return str;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo == null) {
                return str;
            }
            return String.valueOf(packageInfo.versionCode) + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "getAppVersionCode()", e);
            return str;
        }
    }

    public final String getAppVersionCode(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        StringBuilder sb = new StringBuilder();
        String versionName = getVersionName(context);
        if (versionName == null) {
            Intrinsics.throwNpe();
        }
        sb.append(new Regex("\\.").replace(versionName, ""));
        sb.append(getVersionCode(context));
        return sb.toString();
    }

    public final String getTotalMemory(Context ctx) {
        BufferedReader bufferedReader;
        Object[] array;
        long j = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            Intrinsics.checkExpressionValueIsNotNull(readLine, "bufferedReader.readLine()");
            array = StringsKt.split$default((CharSequence) readLine, new String[]{"\\s+"}, false, 0, 6, (Object) null).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (array != null) {
            j = Integer.valueOf(((String[]) array)[1]).intValue() * 1024;
            bufferedReader.close();
            String formatFileSize = Formatter.formatFileSize(ctx, j);
            Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize(ctx, mTotalMem)");
            return formatFileSize;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final String getAvailMemory(Context ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Object systemService = ctx.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        String formatFileSize = Formatter.formatFileSize(ctx, memoryInfo.availMem);
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize(ctx, mAvailMem)");
        return formatFileSize;
    }

    public final String getMemoryPercent(Context ctx) {
        BufferedReader bufferedReader;
        Object[] array;
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Object systemService = ctx.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        long j = 0;
        long j2 = memoryInfo.availMem;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            Intrinsics.checkExpressionValueIsNotNull(readLine, "bufferedReader.readLine()");
            array = StringsKt.split$default((CharSequence) readLine, new String[]{"\\s+"}, false, 0, 6, (Object) null).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (array != null) {
            j = Integer.valueOf(((String[]) array)[1]).intValue() * 1024;
            bufferedReader.close();
            return String.valueOf(1.0f - ((((float) j2) * 1.0f) / ((float) j)));
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final String getSdcardPercent() {
        if (!Intrinsics.areEqual(Environment.getExternalStorageState(), "mounted")) {
            return "UNKNOWN";
        }
        File path = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        new StatFs(path.getPath()).getBlockSize();
        return String.valueOf(1.0f - ((r1.getAvailableBlocks() * 1.0f) / r1.getBlockCount()));
    }

    public final String getDiskPercent() {
        File path = Environment.getRootDirectory();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        StatFs statFs = new StatFs(path.getPath());
        return String.valueOf(1.0f - ((statFs.getAvailableBlocks() * 1.0f) / statFs.getBlockCount()));
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isLegalApk(String apkPath) {
        ZipFile zipFile;
        ZipInputStream zipInputStream;
        String str;
        StringBuilder sb;
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        ZipFile zipFile2 = (ZipFile) null;
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        boolean z2 = false;
        try {
            File file = new File(apkPath);
            zipFile = new ZipFile(file);
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file));
            } catch (Throwable th) {
                th = th;
                zipInputStream = zipInputStream2;
                try {
                    th.printStackTrace();
                    if (zipInputStream != null) {
                    }
                    if (zipFile != null) {
                    }
                    str = TAG;
                    sb = new StringBuilder();
                    sb.append("check used time:");
                    sb.append(System.currentTimeMillis() - currentTimeMillis);
                    sb.append("ms.");
                    Log.d(str, sb.toString());
                    return z2;
                } catch (Throwable th2) {
                    if (zipInputStream != null) {
                        try {
                            zipInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    Log.d(TAG, "check used time:" + (System.currentTimeMillis() - currentTimeMillis) + "ms.");
                    throw th2;
                }
            }
            try {
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        Set<String> set = fileItemList;
                        if (nextEntry == null) {
                            Intrinsics.throwNpe();
                        }
                        if (set.contains(nextEntry.getName())) {
                            InputStream inputStream = zipFile.getInputStream(nextEntry);
                            inputStream.skip(10240000);
                            inputStream.close();
                        }
                    } else {
                        try {
                            break;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            z = false;
                        }
                    }
                }
                zipInputStream.close();
                z = true;
                try {
                    zipFile.close();
                    z2 = z;
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                str = TAG;
                sb = new StringBuilder();
            } catch (Throwable th3) {
                th = th3;
                th.printStackTrace();
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                str = TAG;
                sb = new StringBuilder();
                sb.append("check used time:");
                sb.append(System.currentTimeMillis() - currentTimeMillis);
                sb.append("ms.");
                Log.d(str, sb.toString());
                return z2;
            }
        } catch (Throwable th4) {
            th = th4;
            zipFile = zipFile2;
        }
        sb.append("check used time:");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append("ms.");
        Log.d(str, sb.toString());
        return z2;
    }

    public final boolean installApp(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        try {
            Uri fromFile = Uri.fromFile(new File(path));
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final String getAppMetaData(Context context, String key) {
        ApplicationInfo applicationInfo;
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String str = (String) null;
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || applicationInfo.metaData == null) ? str : applicationInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    public final void startDebugFunction(MyBaseActivity context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            CrossProcessTask.INSTANCE.jumpActivity(context, "com.pudutech.mirsdk", "com.pudutech.mirsdk.activity.MirSDKActivity");
            LedLightManager.INSTANCE.release();
        } catch (Exception unused) {
            Pdlog.m3274e(TAG, "startDebugFunction# ${e.localizedMessage}: ${e.stackTrace.contentDeepToString()}");
            context.showTipDialog("startDebugFunction# ${e.stackTrace.contentDeepToString()}", null, null, null);
        }
    }

    public final void initMain(Application app) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Application application = app;
        if (PackageUtil.INSTANCE.isMainProcess(application)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, singleThreadEventExecutor, null, new AppUtil$initMain$1(null), 2, null);
            LoRaManager.INSTANCE.init(application);
            NetWorkApiManager.INSTANCE.setTestServer(UrlManager.INSTANCE.isTest());
            PuduReportManager.INSTANCE.init(BaseApp.INSTANCE.getINSTANCE(), UrlManager.INSTANCE.isTest());
            PuduReportManager.INSTANCE.setReportDateProvide(new DefIReportDataProvide());
            PuduReportManager.INSTANCE.setRobotPositionProvider(new DefRobotPositionProvider());
            Log.d("isMainProcess", "mainProcess");
            return;
        }
        Log.d("isMainProcess", "otherProcess");
    }

    /* compiled from: AppUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/AppUtil$DefIReportDataProvide;", "Lcom/pudutech/robot/module/report/PuduReportManager$IReportDataProvide;", "()V", "getHardVersion", "", "getMac", "getPowerPercent", "", "isCharging", "", "isWorking", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private static final class DefIReportDataProvide implements PuduReportManager.IReportDataProvide {
        @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
        public String getHardVersion() {
            Pdlog.m3273d(AppUtil.access$getTAG$p(AppUtil.INSTANCE), "DefIReportDataProvide getHardVersion()=" + RobotConfig.INSTANCE.getHardwareVersion());
            return RobotConfig.INSTANCE.getHardwareVersion();
        }

        @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
        public String getMac() {
            String mac = WifiUtil.INSTANCE.getMac();
            if (mac == null) {
                mac = "";
            }
            Pdlog.m3273d(AppUtil.access$getTAG$p(AppUtil.INSTANCE), "DefIReportDataProvide getMac()=" + mac);
            return mac;
        }

        @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
        public double getPowerPercent() {
            double intValue = BatteryInfoManager.INSTANCE.getPower() != null ? r0.intValue() : 0.0d;
            Pdlog.m3273d(AppUtil.access$getTAG$p(AppUtil.INSTANCE), "DefIReportDataProvide getPowerPercent()=" + intValue);
            if (BatteryInfoManager.INSTANCE.getPower() != null) {
                return r0.intValue();
            }
            return 0.0d;
        }

        @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
        public boolean isCharging() {
            Pdlog.m3273d(AppUtil.access$getTAG$p(AppUtil.INSTANCE), "DefIReportDataProvide isCharging()=" + BatteryInfoManager.INSTANCE.isCharging());
            return BatteryInfoManager.INSTANCE.isCharging();
        }

        @Override // com.pudutech.robot.module.report.PuduReportManager.IReportDataProvide
        public boolean isWorking() {
            boolean z = (((Behavior.INSTANCE.getMovementTask() instanceof IdleTask) || (Behavior.INSTANCE.getMovementTask() instanceof GoHomeTask)) && CoreDevices.INSTANCE.getBattery().getChargerState() == ChargeState.Idle && !RobotMoveManager.INSTANCE.isWorking()) ? false : true;
            Pdlog.m3273d(AppUtil.access$getTAG$p(AppUtil.INSTANCE), "DefIReportDataProvide isWorking()=" + z);
            return z;
        }
    }

    /* compiled from: AppUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/AppUtil$DefRobotPositionProvider;", "Lcom/pudutech/robot/module/report/PuduReportManager$IRobotPositionProvider;", "()V", "getPositionInfo", "Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private static final class DefRobotPositionProvider implements PuduReportManager.IRobotPositionProvider {
        @Override // com.pudutech.robot.module.report.PuduReportManager.IRobotPositionProvider
        public RobotPositionInfo getPositionInfo() {
            return PositionDataManager.INSTANCE.getPositionInfo();
        }
    }
}
