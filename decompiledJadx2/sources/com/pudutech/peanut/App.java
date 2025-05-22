package com.pudutech.peanut;

import android.app.ActivityManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.ScreenLedManager;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.mirsdk.update.ApiConstants;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.util.finishapp.AppStatusTracker;
import com.pudutech.peanut.robot_ui.util.finishapp.CrashHandler;
import com.pudutech.peanut.robot_ui.util.finishapp.CrashReportConfig;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000bH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0002J\u0006\u0010\u0017\u001a\u00020\u000eR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/peanut/App;", "Lcom/pudutech/disinfect/baselib/base/BaseApp;", "()V", TmpConstant.DATA_KEY_DEVICENAME, "", "getMAC", "()Ljava/lang/String;", "setMAC", "(Ljava/lang/String;)V", "TAG", "permission_assigned", "", "quested_perssion", "getMac", "", "initCrashHandler", "debug", "initLog", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "processName", "setQuestPerssionStatus", "Companion", "BellFree-9.1.2-1-2021-11-09_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class App extends BaseApp {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static App instance;
    private boolean permission_assigned;
    private boolean quested_perssion;
    private final String TAG = "App";
    private String MAC = ApiConstants.MAC_ADDRESS;

    public final String getMAC() {
        return this.MAC;
    }

    public final void setMAC(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.MAC = str;
    }

    /* compiled from: App.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/peanut/App$Companion;", "", "()V", "<set-?>", "Lcom/pudutech/peanut/App;", "instance", "getInstance", "()Lcom/pudutech/peanut/App;", "setInstance", "(Lcom/pudutech/peanut/App;)V", "BellFree-9.1.2-1-2021-11-09_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setInstance(App app) {
            App.instance = app;
        }

        public final App getInstance() {
            App app = App.instance;
            if (app == null) {
                Intrinsics.throwUninitializedPropertyAccessException("instance");
            }
            return app;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        System.setProperty(CoroutineContextKt.COROUTINES_SCHEDULER_PROPERTY_NAME, "off");
        super.onCreate();
        instance = this;
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                Log.d("App", "read permission not assign");
            } else {
                Log.d("App", "read permission assignend");
                this.permission_assigned = true;
            }
            if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                Log.d("App", "write permission not assign");
            } else {
                Log.d("App", "read permission assignend");
                this.permission_assigned = true;
            }
        }
        initCrashHandler(false);
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() { // from class: com.pudutech.peanut.App$onCreate$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Pdlog.m3273d("onRxJavaErrorHandler ---->: " + th, new Object[0]);
            }
        });
        ScreenLedManager.INSTANCE.init();
        AppStatusTracker.init(this);
    }

    public final void setQuestPerssionStatus() {
        this.quested_perssion = true;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkParameterIsNotNull(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Pdlog.m3274e(this.TAG, "onConfigurationChanged", newConfig);
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Locale locale = newConfig.getLocales().get(0);
        Intrinsics.checkExpressionValueIsNotNull(locale, "newConfig.locales[0]");
        ttsVoiceHelper.changeLanguageType(locale);
    }

    private final void initCrashHandler(boolean debug) {
        CrashHandler.getInstance().init(this, debug, false, 0L, Welcome.class);
        App app = this;
        CrashReportConfig.INSTANCE.initCrashReport(app, "b24e59192e", debug);
        initLog();
        RobotContext.init(app);
        AppContext.init(app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String processName() {
        Object systemService = getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    private final void initLog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new App$initLog$1(this, null), 2, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new App$initLog$2(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getMac() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Log.d(this.TAG, "interfaces " + networkInterfaces);
            Iterator it = Collections.list(networkInterfaces).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                String str = this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("nif name ");
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                sb.append(nif.getName());
                Log.d(str, sb.toString());
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb2.append(format);
                    }
                    if (sb2.length() > 0) {
                        sb2.deleteCharAt(sb2.length() - 1);
                    }
                    String sb3 = sb2.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb3, "mac_str.toString()");
                    this.MAC = sb3;
                }
            }
        } catch (Exception e) {
            String str2 = this.TAG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("cannot get MAC, exception ");
            sb4.append(e.getMessage());
            sb4.append(' ');
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "ex.stackTrace");
            sb4.append(ArraysKt.contentDeepToString(stackTrace));
            Log.e(str2, sb4.toString());
        }
    }
}
