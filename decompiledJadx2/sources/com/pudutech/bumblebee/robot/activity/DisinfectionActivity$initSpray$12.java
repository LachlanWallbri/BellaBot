package com.pudutech.bumblebee.robot.activity;

import android.util.Log;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager;
import kotlin.Metadata;

/* compiled from: DisinfectionActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class DisinfectionActivity$initSpray$12 implements View.OnClickListener {
    final /* synthetic */ DisinfectionActivity this$0;

    DisinfectionActivity$initSpray$12(DisinfectionActivity disinfectionActivity) {
        this.this$0 = disinfectionActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        try {
            DisinfectionModuleManager.INSTANCE.configMagnetic-7apg3OU((byte) 2);
            DisinfectionActivity.access$setMagnity$p(this.this$0, "下通磁浮子");
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "current config magnetic occur error " + Log.getStackTraceString(e));
        }
    }
}
