package com.pudutech.mirsdk.activity;

import android.view.View;
import com.pudutech.mirsdk.aidl.SDKInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$coreWorkSpace$3 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$coreWorkSpace$3(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.checkAuth("reloc", new Function0<Unit>() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$coreWorkSpace$3.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                if (sDKInterface != null) {
                    sDKInterface.reloadLocalization();
                }
            }
        });
    }
}
