package com.pudutech.factory_test;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;
import android.util.Log;
import com.pudutech.factory_test.test_pack.JumpToRobot;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/factory_test/App;", "Landroid/app/Application;", "()V", "initLog", "", "onCreate", "processName", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class App extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = new CrashHandler();
        crashHandler.setOnHandle(new Function0<Unit>() { // from class: com.pudutech.factory_test.App$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Log.e("APP", "on crash");
                JumpToRobot.INSTANCE.killMirsdk(App.this);
                JumpToRobot.INSTANCE.killFactoryTest(App.this);
                Process.killProcess(Process.myPid());
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        initLog();
    }

    private final void initLog() {
        Log.d("APP", "initLog ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new App$initLog$1(this, null), 2, null);
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
}
