package com.pudutech.bumblebee.robot.activity;

import android.widget.Button;
import androidx.core.view.ViewCompat;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.FaultLevel;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.PowerOffEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RecycleRobotPeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0014\u0010\u0016\u001a\u00020\u0003*\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¨\u0006\u0019"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/RecycleRobotPeripheralsActivity$commonListener$1", "Lcom/pudutech/bumblebee/robot/aidl/IRobotListener$Stub;", "onDeviceStatusChange", "", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "status", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "string", "", "onFault", "level", "Lcom/pudutech/bumblebee/robot/aidl/serialize/FaultLevel;", "description", "onPowerOffEvent", "event", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PowerOffEvent;", "onTouch", "place", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;", "state", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "onSensorTouch", "Landroid/widget/Button;", "onStateChange", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RecycleRobotPeripheralsActivity$commonListener$1 extends IRobotListener.Stub {
    final /* synthetic */ RecycleRobotPeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecycleRobotPeripheralsActivity$commonListener$1(RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity) {
        this.this$0 = recycleRobotPeripheralsActivity;
    }

    public final void onSensorTouch(Button onSensorTouch, TouchState touchState) {
        Intrinsics.checkParameterIsNotNull(onSensorTouch, "$this$onSensorTouch");
        String replace$default = StringsKt.replace$default(StringsKt.replace$default(onSensorTouch.getText().toString(), "未触摸", "", false, 4, (Object) null), "有触摸", "", false, 4, (Object) null);
        if (touchState == TouchState.DOWN) {
            onSensorTouch.setText(replace$default + "有触摸");
            onSensorTouch.setTextColor(-1);
            return;
        }
        onSensorTouch.setText(replace$default + "未触摸");
        onSensorTouch.setTextColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public final void onStateChange(Button onStateChange, TouchState touchState) {
        Intrinsics.checkParameterIsNotNull(onStateChange, "$this$onStateChange");
        String replace$default = StringsKt.replace$default(StringsKt.replace$default(onStateChange.getText().toString(), "未触发", "", false, 4, (Object) null), "已触发", "", false, 4, (Object) null);
        if (touchState == TouchState.DOWN) {
            onStateChange.setText(replace$default + "已触发");
            onStateChange.setTextColor(-1);
            return;
        }
        onStateChange.setText(replace$default + "未触发");
        onStateChange.setTextColor(ViewCompat.MEASURED_STATE_MASK);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onPowerOffEvent(PowerOffEvent event) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPowerOffEvent event=" + event);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onTouch(final TouchPlace place, final TouchState state) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onTouch place=" + place + "  state=" + state);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$commonListener$1$onTouch$1
            @Override // java.lang.Runnable
            public final void run() {
                if (place == TouchPlace.TouchKey) {
                    RecycleRobotPeripheralsActivity$commonListener$1 recycleRobotPeripheralsActivity$commonListener$1 = RecycleRobotPeripheralsActivity$commonListener$1.this;
                    Button btnFunction = (Button) recycleRobotPeripheralsActivity$commonListener$1.this$0._$_findCachedViewById(C4144R.id.btnFunction);
                    Intrinsics.checkExpressionValueIsNotNull(btnFunction, "btnFunction");
                    TouchState touchState = state;
                    if (touchState == null) {
                        Intrinsics.throwNpe();
                    }
                    recycleRobotPeripheralsActivity$commonListener$1.onSensorTouch(btnFunction, touchState);
                }
                if (place == TouchPlace.NonTouchKey) {
                    RecycleRobotPeripheralsActivity$commonListener$1 recycleRobotPeripheralsActivity$commonListener$12 = RecycleRobotPeripheralsActivity$commonListener$1.this;
                    Button btnNoTouchKey = (Button) recycleRobotPeripheralsActivity$commonListener$12.this$0._$_findCachedViewById(C4144R.id.btnNoTouchKey);
                    Intrinsics.checkExpressionValueIsNotNull(btnNoTouchKey, "btnNoTouchKey");
                    recycleRobotPeripheralsActivity$commonListener$12.onStateChange(btnNoTouchKey, state);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onDeviceStatusChange(PeripheralDevice device, PeripheralDeviceStatus status, String string) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onDeviceStatusChange device=" + device + "  status=" + status);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onFault(FaultLevel level, String description) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onFault level=" + level + "  description=" + description);
    }
}
