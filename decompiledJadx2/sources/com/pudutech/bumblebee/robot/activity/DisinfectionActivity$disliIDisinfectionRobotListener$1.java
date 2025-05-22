package com.pudutech.bumblebee.robot.activity;

import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import com.pudutech.bumblebee.robot.aidl.serialize.SprayDeviceError;
import com.pudutech.bumblebee.robot.aidl.serialize.UvLampDeviceError;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DisinfectionActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001f\u0010\u0007\u001a\u00020\u00032\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\n\u0018\u00010\tH\u0016¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001f\u0010\u0012\u001a\u00020\u00032\u0010\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0013\u0018\u00010\tH\u0016¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016¨\u0006\u0019"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/DisinfectionActivity$disliIDisinfectionRobotListener$1", "Lcom/pudutech/bumblebee/robot/aidl/IDisinfectionRobotListener$Stub;", "onLiquidLevelChange", "", "l", "", "onSprayChamberLevelChange", "onSprayDiveceError", "error", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError;", "([Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError;)V", "onSprayDiveceOpen", "open", "", "onSprayLiquidStatus", "arrive", "onSpringOpenStatus", "onUvLampDeviceError", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UvLampDeviceError;", "([Lcom/pudutech/bumblebee/robot/aidl/serialize/UvLampDeviceError;)V", "onUvLampDeviceOpen", "onUvLampPlateOpenState", "openState", "Lcom/pudutech/bumblebee/robot/aidl/serialize/OpenState;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DisinfectionActivity$disliIDisinfectionRobotListener$1 extends IDisinfectionRobotListener.Stub {
    final /* synthetic */ DisinfectionActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisinfectionActivity$disliIDisinfectionRobotListener$1(DisinfectionActivity disinfectionActivity) {
        this.this$0 = disinfectionActivity;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onLiquidLevelChange(double l) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onLiquidLevelChange : l = " + l + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C4154x7a36ff3a(this, l, null), 2, null);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onSprayDiveceError(final SprayDeviceError[] error) {
        String str;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onSprayDiveceError : error = ");
        sb.append(error);
        sb.append("; and error size is ");
        sb.append(error != null ? Integer.valueOf(error.length) : null);
        sb.append(' ');
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$disliIDisinfectionRobotListener$1$onSprayDiveceError$1
            @Override // java.lang.Runnable
            public final void run() {
                SprayDeviceError[] sprayDeviceErrorArr = error;
                if (sprayDeviceErrorArr != null) {
                    for (SprayDeviceError sprayDeviceError : sprayDeviceErrorArr) {
                        String str2 = "" + sprayDeviceError.name();
                    }
                }
                DisinfectionActivity$disliIDisinfectionRobotListener$1.this.this$0.updateSprayError("");
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onSprayDiveceOpen(final boolean open) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onSprayDiveceOpen : open = " + open + "; ");
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$disliIDisinfectionRobotListener$1$onSprayDiveceOpen$1
            @Override // java.lang.Runnable
            public final void run() {
                if (!open) {
                    Toast.makeText(DisinfectionActivity$disliIDisinfectionRobotListener$1.this.this$0, "超干雾设备操作失败", 0).show();
                }
                if (open) {
                    DisinfectionActivity$disliIDisinfectionRobotListener$1.this.this$0.updateSprayError("");
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onSprayChamberLevelChange(double l) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onSprayChamberLevelChange : l = " + l + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C4155x687c0ca5(this, l, null), 2, null);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onSprayLiquidStatus(boolean arrive) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onSprayLiquidStatus : l = " + arrive);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C4158x908eea9d(this, arrive, null), 2, null);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onUvLampPlateOpenState(OpenState openState) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onUvLampPlateOpenState : openState = " + openState + "; ");
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onUvLampDeviceError(final UvLampDeviceError[] error) {
        String str;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onUvLampDeviceError : error = ");
        sb.append(error);
        sb.append("; and error size is ");
        sb.append(error != null ? Integer.valueOf(error.length) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$disliIDisinfectionRobotListener$1$onUvLampDeviceError$1
            @Override // java.lang.Runnable
            public final void run() {
                UvLampDeviceError[] uvLampDeviceErrorArr = error;
                if (uvLampDeviceErrorArr != null) {
                    for (UvLampDeviceError uvLampDeviceError : uvLampDeviceErrorArr) {
                        String str2 = "" + uvLampDeviceError.name();
                    }
                }
                DisinfectionActivity$disliIDisinfectionRobotListener$1.this.this$0.updateUvError("");
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onUvLampDeviceOpen(final boolean open) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onUvLampDeviceOpen : open = " + open + "; ");
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$disliIDisinfectionRobotListener$1$onUvLampDeviceOpen$1
            @Override // java.lang.Runnable
            public final void run() {
                if (!open) {
                    Toast.makeText(DisinfectionActivity$disliIDisinfectionRobotListener$1.this.this$0, "紫外设备操作失败", 0).show();
                }
                if (open) {
                    DisinfectionActivity$disliIDisinfectionRobotListener$1.this.this$0.updateUvError("");
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener
    public void onSpringOpenStatus(boolean open) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "spring open status " + open);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C4159x8042c141(this, open, null), 3, null);
    }
}
