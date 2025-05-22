package com.pudutech.mirsdk.activity;

import android.view.View;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.dance.IDanceService;
import kotlin.Metadata;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$onCreate$23 implements View.OnClickListener {
    public static final MirSDKActivity$onCreate$23 INSTANCE = new MirSDKActivity$onCreate$23();

    MirSDKActivity$onCreate$23() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        IDanceService danceService;
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface == null || (danceService = sDKInterface.getDanceService()) == null) {
            return;
        }
        danceService.stopDancing();
    }
}
