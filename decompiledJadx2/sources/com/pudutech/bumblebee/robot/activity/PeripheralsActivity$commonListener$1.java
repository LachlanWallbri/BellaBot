package com.pudutech.bumblebee.robot.activity;

import android.widget.Button;
import androidx.core.view.ViewCompat;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.activity.PeripheralsActivity;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.FaultLevel;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.aidl.serialize.PowerOffEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000G\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0014\u0010\u0015\u001a\u00020\u0003*\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¨\u0006\u0017"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/PeripheralsActivity$commonListener$1", "Lcom/pudutech/bumblebee/robot/aidl/IRobotListener$Stub;", "onDeviceStatusChange", "", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "status", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", "description", "", "onFault", "level", "Lcom/pudutech/bumblebee/robot/aidl/serialize/FaultLevel;", "onPowerOffEvent", "event", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PowerOffEvent;", "onTouch", "place", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;", "state", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "onSensorTouch", "Landroid/widget/Button;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PeripheralsActivity$commonListener$1 extends IRobotListener.Stub {
    final /* synthetic */ PeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeripheralsActivity$commonListener$1(PeripheralsActivity peripheralsActivity) {
        this.this$0 = peripheralsActivity;
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

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onPowerOffEvent(PowerOffEvent event) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPowerOffEvent event=" + event);
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onDeviceStatusChange(PeripheralDevice device, PeripheralDeviceStatus status, String description) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onTouch(TouchPlace place, final TouchState state) {
        String str;
        final Button button;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onTouch place=" + place + "  state=" + state);
        if (place != null) {
            int i = PeripheralsActivity.WhenMappings.$EnumSwitchMapping$1[place.ordinal()];
            if (i == 1) {
                button = (Button) this.this$0._$_findCachedViewById(C4144R.id.btnHead);
            } else if (i == 2) {
                button = (Button) this.this$0._$_findCachedViewById(C4144R.id.btnLeftEar);
            } else if (i == 3) {
                button = (Button) this.this$0._$_findCachedViewById(C4144R.id.btnRightEar);
            } else if (i == 4) {
                button = (Button) this.this$0._$_findCachedViewById(C4144R.id.btnFunction);
            }
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$commonListener$1$onTouch$1
                @Override // java.lang.Runnable
                public final void run() {
                    Button button2 = button;
                    if (button2 != null) {
                        PeripheralsActivity$commonListener$1.this.onSensorTouch(button2, state);
                    }
                }
            });
        }
        button = null;
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$commonListener$1$onTouch$1
            @Override // java.lang.Runnable
            public final void run() {
                Button button2 = button;
                if (button2 != null) {
                    PeripheralsActivity$commonListener$1.this.onSensorTouch(button2, state);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IRobotListener
    public void onFault(FaultLevel level, String description) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onFault level=" + level + "  description=" + description);
    }
}
