package com.pudutech.mirsdk.activity;

import android.view.View;
import android.widget.TextView;
import com.pudutech.mirsdk.aidl.IPersonPassCountService;
import com.pudutech.mirsdk.aidl.OnPersonPassedListener;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$onCreate$24 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$onCreate$24(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        IPersonPassCountService personPassCountService;
        TextView tx_person_pass_count = (TextView) this.this$0._$_findCachedViewById(C4946R.id.tx_person_pass_count);
        Intrinsics.checkExpressionValueIsNotNull(tx_person_pass_count, "tx_person_pass_count");
        tx_person_pass_count.setText(this.this$0.getString(C4946R.string.person_pass_count) + ' ' + MirSDKActivity.access$getPersonPassCount$p(this.this$0));
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface == null || (personPassCountService = sDKInterface.getPersonPassCountService()) == null) {
            return;
        }
        personPassCountService.addOnPersonPassedListener("SDKPersonPassCount", new BinderC48661());
    }

    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onCreate$24$1", "Lcom/pudutech/mirsdk/aidl/OnPersonPassedListener$Stub;", "onPersonPassed", "", "count", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$24$1 */
    /* loaded from: classes4.dex */
    public static final class BinderC48661 extends OnPersonPassedListener.Stub {
        BinderC48661() {
        }

        @Override // com.pudutech.mirsdk.aidl.OnPersonPassedListener
        public void onPersonPassed(int count) {
            MirSDKActivity mirSDKActivity = MirSDKActivity$onCreate$24.this.this$0;
            MirSDKActivity.access$setPersonPassCount$p(mirSDKActivity, MirSDKActivity.access$getPersonPassCount$p(mirSDKActivity) + count);
            ((TextView) MirSDKActivity$onCreate$24.this.this$0._$_findCachedViewById(C4946R.id.tx_person_pass_count)).post(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$24$1$onPersonPassed$1
                @Override // java.lang.Runnable
                public final void run() {
                    TextView tx_person_pass_count = (TextView) MirSDKActivity$onCreate$24.this.this$0._$_findCachedViewById(C4946R.id.tx_person_pass_count);
                    Intrinsics.checkExpressionValueIsNotNull(tx_person_pass_count, "tx_person_pass_count");
                    tx_person_pass_count.setText(MirSDKActivity$onCreate$24.this.this$0.getString(C4946R.string.person_pass_count) + ' ' + MirSDKActivity.access$getPersonPassCount$p(MirSDKActivity$onCreate$24.this.this$0));
                }
            });
        }
    }
}
