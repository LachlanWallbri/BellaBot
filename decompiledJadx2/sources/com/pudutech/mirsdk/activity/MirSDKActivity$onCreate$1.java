package com.pudutech.mirsdk.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class MirSDKActivity$onCreate$1 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onCreate$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        EditText et_battery_level = (EditText) this.this$0._$_findCachedViewById(C4946R.id.et_battery_level);
        Intrinsics.checkExpressionValueIsNotNull(et_battery_level, "et_battery_level");
        if (!TextUtils.isEmpty(et_battery_level.getText())) {
            try {
                EditText et_battery_level2 = (EditText) this.this$0._$_findCachedViewById(C4946R.id.et_battery_level);
                Intrinsics.checkExpressionValueIsNotNull(et_battery_level2, "et_battery_level");
                int parseInt = Integer.parseInt(et_battery_level2.getText().toString());
                if (parseInt < 5) {
                    parseInt = 5;
                }
                if (parseInt > 20) {
                    parseInt = 20;
                }
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface != null) {
                    sDKInterface.controlBatteryLevel(parseInt);
                    return;
                }
                return;
            } catch (Exception unused) {
                Toast.makeText(this.this$0, "edit input is wrong type", 0).show();
                return;
            }
        }
        Toast.makeText(this.this$0, "edit input is null", 0).show();
    }
}
