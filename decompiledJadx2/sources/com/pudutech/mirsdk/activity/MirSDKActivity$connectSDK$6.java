package com.pudutech.mirsdk.activity;

import android.widget.Button;
import android.widget.Toast;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", SpeechUtility.TAG_RESOURCE_RESULT, "", TmpConstant.SERVICE_DESC, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirSDKActivity$connectSDK$6 extends Lambda implements Function2<Boolean, String, Unit> {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirSDKActivity$connectSDK$6(MirSDKActivity mirSDKActivity) {
        super(2);
        this.this$0 = mirSDKActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
        invoke(bool.booleanValue(), str);
        return Unit.INSTANCE;
    }

    public final void invoke(final boolean z, final String str) {
        String str2;
        str2 = this.this$0.TAG;
        Pdlog.m3275i(str2, "showing add relocate point result " + z + ' ' + str);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$connectSDK$6.1
            @Override // java.lang.Runnable
            public final void run() {
                Button button_set_boot_pose = (Button) MirSDKActivity$connectSDK$6.this.this$0._$_findCachedViewById(C4946R.id.button_set_boot_pose);
                Intrinsics.checkExpressionValueIsNotNull(button_set_boot_pose, "button_set_boot_pose");
                button_set_boot_pose.setEnabled(true);
                if (z) {
                    Toast.makeText(MirSDKActivity$connectSDK$6.this.this$0, "add boot pose success", 0).show();
                    return;
                }
                Toast.makeText(MirSDKActivity$connectSDK$6.this.this$0, "add boot pose fail," + str, 0).show();
            }
        });
    }
}
