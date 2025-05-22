package com.pudutech.mirsdk.activity;

import android.view.View;
import android.widget.TextView;
import com.pudutech.mirsdk.aidl.IPersonPassCountService;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$onCreate$25 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$onCreate$25(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        IPersonPassCountService personPassCountService;
        MirSDKActivity.access$setPersonPassCount$p(this.this$0, 0);
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (personPassCountService = sDKInterface.getPersonPassCountService()) != null) {
            personPassCountService.removeOnPersonPassedListener("SDKPersonPassCount");
        }
        TextView tx_person_pass_count = (TextView) this.this$0._$_findCachedViewById(C4946R.id.tx_person_pass_count);
        Intrinsics.checkExpressionValueIsNotNull(tx_person_pass_count, "tx_person_pass_count");
        tx_person_pass_count.setText(this.this$0.getString(C4946R.string.person_pass_count_not_start));
    }
}
