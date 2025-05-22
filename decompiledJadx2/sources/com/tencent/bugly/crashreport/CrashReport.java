package com.tencent.bugly.crashreport;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.C5865b;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.biz.C5871b;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.C5887c;
import com.tencent.bugly.crashreport.crash.C5888d;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.p070h5.C5891b;
import com.tencent.bugly.crashreport.crash.p070h5.H5JavaScriptInterface;
import com.tencent.bugly.proguard.C5896a;
import com.tencent.bugly.proguard.C5933q;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class CrashReport {

    /* renamed from: a */
    private static Context f7647a;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* loaded from: classes7.dex */
    public static class CrashHandleCallback extends BuglyStrategy.C5863a {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* loaded from: classes7.dex */
    public interface WebViewInterface {
        void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str);

        CharSequence getContentDescription();

        String getUrl();

        void loadUrl(String str);

        void setJavaScriptEnabled(boolean z);
    }

    public static void enableBugly(boolean z) {
        C5865b.f7642a = z;
    }

    public static void initCrashReport(Context context) {
        f7647a = context;
        C5865b.m3334a(CrashModule.getInstance());
        C5865b.m3331a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        f7647a = context;
        C5865b.m3334a(CrashModule.getInstance());
        C5865b.m3332a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f7647a = context;
            C5865b.m3334a(CrashModule.getInstance());
            C5865b.m3333a(context, str, z, null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f7647a = context;
        C5865b.m3334a(CrashModule.getInstance());
        C5865b.m3333a(context, str, z, userStrategy);
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            C5940x.m3824d("Please call with context.", new Object[0]);
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        C5873a.m3389a(context);
        return C5873a.m3391c();
    }

    public static void testJavaCrash() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not test Java crash because bugly is disable.");
        } else {
            if (!CrashModule.getInstance().hasInitialized()) {
                Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                return;
            }
            C5873a m3390b = C5873a.m3390b();
            if (m3390b != null) {
                m3390b.m3416b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static void testNativeCrash() {
        testNativeCrash(false, false, false);
    }

    public static void testNativeCrash(boolean z, boolean z2, boolean z3) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C5940x.m3818a("start to create a native crash for test!", new Object[0]);
            C5887c.m3547a().m3555a(z, z2, z3);
        }
    }

    public static void testANRCrash() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C5940x.m3818a("start to create a anr crash for test!", new Object[0]);
            C5887c.m3547a().m3564j();
        }
    }

    public static void postException(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C5888d.m3572a(thread, i, str, str2, str3, map);
        }
    }

    public static void postException(int i, String str, String str2, String str3, Map<String, String> map) {
        postException(Thread.currentThread(), i, str, str2, str3, map);
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not post crash caught because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (th == null) {
            C5940x.m3824d("throwable is null, just return", new Object[0]);
            return;
        }
        if (thread == null) {
            thread = Thread.currentThread();
        }
        C5887c.m3547a().m3554a(thread, th, false, (String) null, (byte[]) null, z);
    }

    public static void closeNativeReport() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C5887c.m3547a().m3560f();
        }
    }

    public static void startCrashReport() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C5887c.m3547a().m3557c();
        }
    }

    public static void closeCrashReport() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C5887c.m3547a().m3558d();
        }
    }

    public static void closeBugly() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not close bugly because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (f7647a == null) {
            return;
        }
        BuglyBroadcastReceiver buglyBroadcastReceiver = BuglyBroadcastReceiver.getInstance();
        if (buglyBroadcastReceiver != null) {
            buglyBroadcastReceiver.unregister(f7647a);
        }
        closeCrashReport();
        C5871b.m3357a(f7647a);
        C5939w m3810a = C5939w.m3810a();
        if (m3810a != null) {
            m3810a.m3814b();
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set tag caught because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "setTag args context should not be null");
            return;
        }
        if (i <= 0) {
            C5940x.m3824d("setTag args tagId should > 0", new Object[0]);
        }
        C5873a.m3389a(context).m3411a(i);
        C5940x.m3821b("[param] set user scene tag: %d", Integer.valueOf(i));
    }

    public static int getUserSceneTagId(Context context) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get user scene tag because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "getUserSceneTagId args context should not be null");
            return -1;
        }
        return C5873a.m3389a(context).m3399H();
    }

    public static String getUserData(Context context, String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get user data because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "getUserDataValue args context should not be null");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (C5942z.m3868a(str)) {
            return null;
        }
        return C5873a.m3389a(context).m3429g(str);
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not put user data because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C5940x.f8272a, "putUserData args context should not be null");
            return;
        }
        if (str == null) {
            String str3 = str;
            C5940x.m3824d("putUserData args key should not be null or empty", new Object[0]);
            return;
        }
        if (str2 == null) {
            String str4 = str2;
            C5940x.m3824d("putUserData args value should not be null", new Object[0]);
            return;
        }
        if (!str.matches("[a-zA-Z[0-9]]+")) {
            C5940x.m3824d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
            return;
        }
        if (str2.length() > 200) {
            C5940x.m3824d("user data value length over limit %d, it will be cutted!", 200);
            str2 = str2.substring(0, 200);
        }
        C5873a m3389a = C5873a.m3389a(context);
        if (m3389a.m3396E().contains(str)) {
            NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
            if (nativeCrashHandler != null) {
                nativeCrashHandler.putKeyValueToNative(str, str2);
            }
            C5873a.m3389a(context).m3418b(str, str2);
            C5940x.m3823c("replace KV %s %s", str, str2);
            return;
        }
        if (m3389a.m3395D() >= 10) {
            C5940x.m3824d("user data size is over limit %d, it will be cutted!", 10);
            return;
        }
        if (str.length() > 50) {
            C5940x.m3824d("user data key length over limit %d , will drop this new key %s", 50, str);
            str = str.substring(0, 50);
        }
        NativeCrashHandler nativeCrashHandler2 = NativeCrashHandler.getInstance();
        if (nativeCrashHandler2 != null) {
            nativeCrashHandler2.putKeyValueToNative(str, str2);
        }
        C5873a.m3389a(context).m3418b(str, str2);
        C5940x.m3821b("[param] set user data: %s - %s", str, str2);
    }

    public static String removeUserData(Context context, String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not remove user data because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "removeUserData args context should not be null");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (C5942z.m3868a(str)) {
            return null;
        }
        C5940x.m3821b("[param] remove user data: %s", str);
        return C5873a.m3389a(context).m3427f(str);
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
        return C5873a.m3389a(context).m3396E();
    }

    public static int getUserDatasSize(Context context) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get size of user data because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "getUserDatasSize args context should not be null");
            return -1;
        }
        return C5873a.m3389a(context).m3395D();
    }

    public static String getAppID() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get App ID because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return C5873a.m3389a(f7647a).m3426f();
    }

    public static void setUserId(String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f7647a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set user ID because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(C5940x.f8272a, "Context should not be null when bugly has not been initialed!");
            return;
        }
        if (str == null) {
            C5940x.m3824d("userId should not be null", new Object[0]);
            return;
        }
        if (str.length() > 100) {
            String substring = str.substring(0, 100);
            C5940x.m3824d("userId %s length is over limit %d substring to %s", str, 100, substring);
            str = substring;
        }
        if (str.equals(C5873a.m3389a(context).m3428g())) {
            return;
        }
        C5873a.m3389a(context).m3417b(str);
        C5940x.m3821b("[user] set userId : %s", str);
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeUserId(str);
        }
        if (CrashModule.getInstance().hasInitialized()) {
            C5871b.m3355a();
        }
    }

    public static String getUserId() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get user ID because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return C5873a.m3389a(f7647a).m3428g();
    }

    public static String getAppVer() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get app version because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return C5873a.m3389a(f7647a).f7762j;
    }

    public static String getAppChannel() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get App channel because bugly is disable.");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return C5873a.m3389a(f7647a).f7764l;
    }

    public static void setContext(Context context) {
        f7647a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
        return C5887c.m3547a().m3556b();
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not put SDK extra data because bugly is disable.");
        } else {
            if (context == null || C5942z.m3868a(str) || C5942z.m3868a(str2)) {
                return;
            }
            C5873a.m3389a(context).m3413a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C5940x.f8272a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
        return C5873a.m3389a(f7647a).f7702A;
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (context == null) {
            C5940x.m3824d("Context should not be null.", new Object[0]);
            return null;
        }
        return C5873a.m3389a(context).f7702A;
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context == null || C5942z.m3868a(str) || C5942z.m3868a(str2)) {
            return;
        }
        String replace = str.replace("[a-zA-Z[0-9]]+", "");
        if (replace.length() > 100) {
            Log.w(C5940x.f8272a, String.format("putSdkData key length over limit %d, will be cutted.", 50));
            replace = replace.substring(0, 50);
        }
        if (str2.length() > 500) {
            Log.w(C5940x.f8272a, String.format("putSdkData value length over limit %d, will be cutted!", 200));
            str2 = str2.substring(0, 200);
        }
        C5873a.m3389a(context).m3421c(replace, str2);
        C5940x.m3821b(String.format("[param] putSdkData data: %s - %s", replace, str2), new Object[0]);
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set 'isAppForeground' because bugly is disable.");
            return;
        }
        if (context == null) {
            C5940x.m3824d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            C5940x.m3823c("App is in foreground.", new Object[0]);
        } else {
            C5940x.m3823c("App is in background.", new Object[0]);
        }
        C5873a.m3389a(context).m3414a(z);
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
            return;
        }
        if (context == null) {
            C5940x.m3824d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            C5940x.m3823c("This is a development device.", new Object[0]);
        } else {
            C5940x.m3823c("This is not a development device.", new Object[0]);
        }
        C5873a.m3389a(context).f7777y = z;
    }

    public static void setSessionIntervalMills(long j) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            C5871b.m3356a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C5940x.f8272a, "setAppVersion args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C5940x.f8272a, "App version is null, will not set");
            return;
        }
        C5873a.m3389a(context).f7762j = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppVersion(str);
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App channel because Bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C5940x.f8272a, "setAppChannel args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C5940x.f8272a, "App channel is null, will not set");
            return;
        }
        C5873a.m3389a(context).f7764l = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppChannel(str);
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App package because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C5940x.f8272a, "setAppPackage args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C5940x.f8272a, "App package is null, will not set");
            return;
        }
        C5873a.m3389a(context).f7755c = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppPackage(str);
        }
    }

    public static void setCrashFilter(String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(C5940x.f8272a, "Set crash stack filter: " + str);
        C5887c.f7930n = str;
    }

    public static void setCrashRegularFilter(String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(C5940x.f8272a, "Set crash stack filter: " + str);
        C5887c.f7931o = str;
    }

    public static void setHandleNativeCrashInJava(boolean z) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(C5940x.f8272a, "Should handle native crash in Java profile after handled in native profile: " + z);
        NativeCrashHandler.setShouldHandleInJava(z);
    }

    public static void setBuglyDbName(String str) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set DB name because bugly is disable.");
            return;
        }
        Log.i(C5940x.f8272a, "Set Bugly DB name: " + str);
        C5933q.f8206a = str;
    }

    public static void enableObtainId(Context context, boolean z) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set DB name because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C5940x.f8272a, "enableObtainId args context should not be null");
            return;
        }
        Log.i(C5940x.f8272a, "Enable identification obtaining? " + z);
        C5873a.m3389a(context).m3419b(z);
    }

    public static void setAuditEnable(Context context, boolean z) {
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set App package because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C5940x.f8272a, "setAppPackage args context should not be null");
            return;
        }
        Log.i(C5940x.f8272a, "Set audit enable: " + z);
        C5873a.m3389a(context).f7703B = z;
    }

    public static void setServerUrl(String str) {
        if (C5942z.m3868a(str) || !C5942z.m3891c(str)) {
            Log.i(C5940x.f8272a, "URL is invalid.");
            return;
        }
        C5876a.m3489a(str);
        StrategyBean.f7785b = str;
        StrategyBean.f7786c = str;
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(C5940x.f8272a, "WebView is null.");
            return false;
        }
        return setJavascriptMonitor(new WebViewInterface() { // from class: com.tencent.bugly.crashreport.CrashReport.1
            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final String getUrl() {
                return webView.getUrl();
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void setJavaScriptEnabled(boolean z3) {
                WebSettings settings = webView.getSettings();
                if (settings.getJavaScriptEnabled()) {
                    return;
                }
                settings.setJavaScriptEnabled(true);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void loadUrl(String str) {
                webView.loadUrl(str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                webView.addJavascriptInterface(h5JavaScriptInterface, str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final CharSequence getContentDescription() {
                return webView.getContentDescription();
            }
        }, z, z2);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z) {
        return setJavascriptMonitor(webViewInterface, z, false);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z, boolean z2) {
        if (webViewInterface == null) {
            Log.w(C5940x.f8272a, "WebViewInterface is null.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            C5940x.m3825e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
        C5940x.m3818a("Set Javascript exception monitor of webview.", new Object[0]);
        if (!C5865b.f7642a) {
            Log.w(C5940x.f8272a, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        }
        C5940x.m3823c("URL of webview is %s", webViewInterface.getUrl());
        if (!z2 && Build.VERSION.SDK_INT < 19) {
            C5940x.m3825e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
        C5940x.m3818a("Enable the javascript needed by webview monitor.", new Object[0]);
        webViewInterface.setJavaScriptEnabled(true);
        H5JavaScriptInterface h5JavaScriptInterface = H5JavaScriptInterface.getInstance(webViewInterface);
        if (h5JavaScriptInterface != null) {
            C5940x.m3818a("Add a secure javascript interface to the webview.", new Object[0]);
            webViewInterface.addJavascriptInterface(h5JavaScriptInterface, "exceptionUploader");
        }
        if (z) {
            C5940x.m3818a("Inject bugly.js(v%s) to the webview.", C5891b.m3584b());
            String m3583a = C5891b.m3583a();
            if (m3583a == null) {
                C5940x.m3825e("Failed to inject Bugly.js.", C5891b.m3584b());
                return false;
            }
            webViewInterface.loadUrl("javascript:" + m3583a);
        }
        return true;
    }

    public static void setHttpProxy(String str, int i) {
        C5896a.m3617a(str, i);
    }

    public static void setHttpProxy(InetAddress inetAddress, int i) {
        C5896a.m3618a(inetAddress, i);
    }

    public static Proxy getHttpProxy() {
        return C5896a.m3622b();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* loaded from: classes7.dex */
    public static class UserStrategy extends BuglyStrategy {

        /* renamed from: a */
        private CrashHandleCallback f7649a;

        public UserStrategy(Context context) {
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f7649a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f7649a = crashHandleCallback;
        }
    }
}
