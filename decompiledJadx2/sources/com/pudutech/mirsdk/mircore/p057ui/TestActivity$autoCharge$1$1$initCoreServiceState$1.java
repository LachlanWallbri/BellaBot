package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.p057ui.TestActivity$autoCharge$1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class TestActivity$autoCharge$1$1$initCoreServiceState$1 implements Runnable {
    final /* synthetic */ TestActivity$autoCharge$1.C52611 this$0;

    TestActivity$autoCharge$1$1$initCoreServiceState$1(TestActivity$autoCharge$1.C52611 c52611) {
        this.this$0 = c52611;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView status_text = (TextView) TestActivity$autoCharge$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
        Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
        status_text.setText("Success");
    }
}
