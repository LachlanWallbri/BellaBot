package com.pudutech.mirsdk.activity;

import android.view.View;
import com.pudutech.mirsdk.activity.StartUpMirSDKActivity;
import kotlin.Metadata;

/* compiled from: StartUpMirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class StartUpMirSDKActivity$initListener$1 implements View.OnClickListener {
    final /* synthetic */ StartUpMirSDKActivity this$0;

    StartUpMirSDKActivity$initListener$1(StartUpMirSDKActivity startUpMirSDKActivity) {
        this.this$0 = startUpMirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.this$0.getCurrentState() == StartUpMirSDKActivity.StartUpState.NeedStartUp) {
            this.this$0.getStepStateList().clear();
            StartUpMirSDKActivity.access$initCheckHardware(this.this$0);
        } else if (this.this$0.getCurrentState() == StartUpMirSDKActivity.StartUpState.Finish) {
            this.this$0.finish();
        }
    }
}
