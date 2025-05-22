package com.pudutech.bumblebee.robot.activity;

import android.widget.Button;
import android.widget.Toast;
import com.pudutech.bumblebee.robot.C4144R;
import com.pudutech.bumblebee.robot.activity.PeripheralsActivity;
import com.pudutech.bumblebee.robot.aidl.IUpdateListener;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/PeripheralsActivity$initScreen$listener$1", "Lcom/pudutech/bumblebee/robot/aidl/IUpdateListener$Stub;", "onUpdateEvent", "", "obj", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateObject;", "event", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateEvent;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PeripheralsActivity$initScreen$listener$1 extends IUpdateListener.Stub {
    final /* synthetic */ PeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeripheralsActivity$initScreen$listener$1(PeripheralsActivity peripheralsActivity) {
        this.this$0 = peripheralsActivity;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IUpdateListener
    public void onUpdateEvent(UpdateObject obj, final UpdateEvent event) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot.activity.PeripheralsActivity$initScreen$listener$1$onUpdateEvent$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                UpdateEvent updateEvent = event;
                if (updateEvent == null) {
                    Intrinsics.throwNpe();
                }
                int i = PeripheralsActivity.WhenMappings.$EnumSwitchMapping$0[updateEvent.ordinal()];
                if (i == 1) {
                    str = "字库不需要更新";
                } else if (i == 2) {
                    str = "更新中，请稍等";
                } else if (i == 3) {
                    str = "更新字库失败";
                } else {
                    if (i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    str = "更新字库成功";
                }
                Toast.makeText(PeripheralsActivity$initScreen$listener$1.this.this$0, str, 1).show();
                Button btnTryUpdateFontLib = (Button) PeripheralsActivity$initScreen$listener$1.this.this$0._$_findCachedViewById(C4144R.id.btnTryUpdateFontLib);
                Intrinsics.checkExpressionValueIsNotNull(btnTryUpdateFontLib, "btnTryUpdateFontLib");
                btnTryUpdateFontLib.setEnabled(event != UpdateEvent.UPDATING);
                Button btnForceUpdateFontLib = (Button) PeripheralsActivity$initScreen$listener$1.this.this$0._$_findCachedViewById(C4144R.id.btnForceUpdateFontLib);
                Intrinsics.checkExpressionValueIsNotNull(btnForceUpdateFontLib, "btnForceUpdateFontLib");
                btnForceUpdateFontLib.setEnabled(event != UpdateEvent.UPDATING);
            }
        });
    }
}
