package com.pudutech.bumblebee.robot;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: RobotService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/RobotService;", "Landroid/app/Service;", "()V", "TAG", "", "iBinder", "com/pudutech/bumblebee/robot/RobotService$iBinder$1", "Lcom/pudutech/bumblebee/robot/RobotService$iBinder$1;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RobotService extends Service {
    private final String TAG = "RobotService";
    private final RobotService$iBinder$1 iBinder = new RobotService$iBinder$1(this);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.iBinder.onCreate(this);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        return this.iBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unbindService(this.iBinder.getHardware());
    }
}
