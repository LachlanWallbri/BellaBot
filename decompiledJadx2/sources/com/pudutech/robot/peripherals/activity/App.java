package com.pudutech.robot.peripherals.activity;

import android.app.Application;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/activity/App;", "Landroid/app/Application;", "()V", "initLog", "", "onCreate", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class App extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private final void initLog() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new App$initLog$1(this, null), 2, null);
    }
}
