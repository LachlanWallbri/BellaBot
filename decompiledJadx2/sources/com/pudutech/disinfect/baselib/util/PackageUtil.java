package com.pudutech.disinfect.baselib.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.config.ConstantKt;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: PackageUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004J\u0012\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0007J\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010!\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\"\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010#\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\u0004J\n\u0010(\u001a\u00020)*\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006*"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/PackageUtil;", "", "()V", "PACKAGE_FACTORY_TEST", "", "PACKAGE_MAPIFY", "PACKAGE_ROBOT_BELLFREE", "PACKAGE_ROBOT_PEANUT", "TAG", "getTAG", "()Ljava/lang/String;", "currentProcessName", "<set-?>", "softVersion", "getSoftVersion", "appIsRunning", "", "packageName", "getClazzForBuildConfig", "Ljava/lang/Class;", "context", "Landroid/content/Context;", "getCurrentProcessName", "getCurrentProcessNameByActivityManager", "getCurrentProcessNameByActivityThread", "getCurrentProcessNameByApplication", "getPackageName", "getProductNameByBuildConfig", "getVersionCode", "", "getVersionCodeByBuildConfig", "(Landroid/content/Context;)Ljava/lang/Integer;", "getVersionName", "getVersionNameByBuildConfig", "isAppInstalled", "isMainProcess", "killMapOrFactory", "", "silentInstallApp", "appPath", "getPackageInfo", "Landroid/content/pm/PackageInfo;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PackageUtil {
    public static final String PACKAGE_FACTORY_TEST = "com.pudutech.factory_test";
    public static final String PACKAGE_MAPIFY = "com.pudutech.mapify";
    public static final String PACKAGE_ROBOT_BELLFREE = "com.pudutech.robot.bellfree";
    public static final String PACKAGE_ROBOT_PEANUT = "com.pudutech.robot.peanut";
    private static String currentProcessName;
    public static final PackageUtil INSTANCE = new PackageUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String softVersion = "";

    private PackageUtil() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final boolean isAppInstalled(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        try {
            context.getPackageManager().getPackageInfo(packageName, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final void silentInstallApp(String appPath) {
        Intrinsics.checkParameterIsNotNull(appPath, "appPath");
        try {
            Pair<Integer, String> execCommand = Tools.execCommand("pm install -r " + appPath, true);
            if (execCommand != null) {
                Pdlog.m3273d(TAG, "first[" + ((Integer) execCommand.first) + "], second[" + ((String) execCommand.second) + ']');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final int getVersionCode(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = packageName;
        if (str == null || str.length() == 0) {
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo == null) {
                return 0;
            }
            if (Build.VERSION.SDK_INT >= 28) {
                return (int) packageInfo.getLongVersionCode();
            }
            return packageInfo.versionCode;
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "getVersionCode: " + Log.getStackTraceString(e));
            return 0;
        }
    }

    public final PackageInfo getPackageInfo(Context getPackageInfo) {
        Intrinsics.checkParameterIsNotNull(getPackageInfo, "$this$getPackageInfo");
        PackageInfo packageInfo = getPackageInfo.getPackageManager().getPackageInfo(getPackageInfo.getPackageName(), 0);
        Intrinsics.checkExpressionValueIsNotNull(packageInfo, "packageManager.getPackageInfo(packageName, 0)");
        return packageInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #0 {Exception -> 0x0072, blocks: (B:3:0x0006, B:5:0x000f, B:10:0x0045, B:12:0x004b, B:14:0x0051, B:19:0x005d), top: B:2:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean appIsRunning(String packageName) {
        Pair<Integer, String> execCommand;
        boolean z;
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        try {
            execCommand = Tools.execCommand("busybox ps aux", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (execCommand == null) {
            return false;
        }
        Pdlog.m3273d(TAG, "first[" + ((Integer) execCommand.first) + "], second[" + ((String) execCommand.second) + ']');
        Integer num = (Integer) execCommand.first;
        if (num != null && num.intValue() == 0) {
            CharSequence charSequence = (CharSequence) execCommand.second;
            if (charSequence != null && charSequence.length() != 0) {
                z = false;
                if (!z) {
                    Object obj = execCommand.second;
                    Intrinsics.checkExpressionValueIsNotNull(obj, "pair.second");
                    if (StringsKt.contains$default((CharSequence) obj, (CharSequence) packageName, false, 2, (Object) null)) {
                        return true;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        return false;
    }

    public final String getCurrentProcessName(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            if (!TextUtils.isEmpty(currentProcessName)) {
                return currentProcessName;
            }
            currentProcessName = getCurrentProcessNameByApplication();
            if (!TextUtils.isEmpty(currentProcessName)) {
                return currentProcessName;
            }
            currentProcessName = getCurrentProcessNameByActivityThread();
            if (!TextUtils.isEmpty(currentProcessName)) {
                return currentProcessName;
            }
            currentProcessName = getCurrentProcessNameByActivityManager(context);
            return currentProcessName;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("getCurrentProcessName", e.toString());
            return null;
        }
    }

    public final String getCurrentProcessNameByApplication() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        return null;
    }

    public final String getCurrentProcessNameByActivityThread() {
        String str = (String) null;
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "Class.forName(\n         …rayOfNulls<Class<*>?>(0))");
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Intrinsics.checkExpressionValueIsNotNull(invoke, "declaredMethod.invoke(null, arrayOfNulls<Any>(0))");
            if (invoke instanceof String) {
                return (String) invoke;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    public final String getCurrentProcessNameByActivityManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        int myPid = Process.myPid();
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        Intrinsics.checkExpressionValueIsNotNull(runningAppProcesses, "am.runningAppProcesses");
        List<ActivityManager.RunningAppProcessInfo> list = runningAppProcesses;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public final boolean isMainProcess(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            return StringsKt.equals$default(getCurrentProcessName(context), getPackageName(context), false, 2, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final String getPackageName(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String packageName = context.getPackageName();
        Intrinsics.checkExpressionValueIsNotNull(packageName, "context.packageName");
        return packageName;
    }

    public final String getVersionName(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = getPackageInfo(context).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "context.getPackageInfo().versionName");
        return str;
    }

    public final Class<?> getClazzForBuildConfig(Context context) throws ClassNotFoundException {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Class<?> cls = Class.forName(context.getPackageName() + ".BuildConfig");
        Intrinsics.checkExpressionValueIsNotNull(cls, "Class.forName(\"${context…ackageName}.BuildConfig\")");
        return cls;
    }

    public final String getVersionNameByBuildConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Class<?> cls = (Class) null;
        try {
            cls = getClazzForBuildConfig(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Pdlog.m3274e("getVersionNameByBuildConfig", Unit.INSTANCE);
        }
        if (cls == null) {
            return null;
        }
        Object obj = cls.getField(ConstantKt.VERSION_NAME).get(null);
        if (obj != null) {
            return (String) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    public final Integer getVersionCodeByBuildConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Class<?> cls = (Class) null;
        try {
            cls = getClazzForBuildConfig(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Pdlog.m3274e("getVersionNameByBuildConfig", Unit.INSTANCE);
        }
        if (cls == null) {
            return null;
        }
        Object obj = cls.getField(ConstantKt.VERSION_CODE).get(null);
        if (obj != null) {
            return (Integer) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
    }

    public final String getProductNameByBuildConfig(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Class<?> cls = (Class) null;
        try {
            cls = getClazzForBuildConfig(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Pdlog.m3274e("getVersionNameByBuildConfig", Unit.INSTANCE);
        }
        if (cls == null) {
            return null;
        }
        Object obj = cls.getField(ConstantKt.PRODUCT_NAME).get(null);
        if (obj != null) {
            return (String) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    public final String getSoftVersion() {
        try {
            String v = BaseApp.INSTANCE.getINSTANCE().getPackageManager().getPackageInfo(BaseApp.INSTANCE.getINSTANCE().getPackageName(), 0).versionName;
            Pdlog.m3273d(TAG, "softVersion = " + v);
            Intrinsics.checkExpressionValueIsNotNull(v, "v");
            softVersion = v;
            return v;
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "softVersion Exception = " + e.getMessage());
            return softVersion;
        }
    }

    public final void killMapOrFactory() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new PackageUtil$killMapOrFactory$1(null), 2, null);
    }
}
