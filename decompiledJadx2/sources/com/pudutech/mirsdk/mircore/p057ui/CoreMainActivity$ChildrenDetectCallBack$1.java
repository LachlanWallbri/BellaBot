package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.Toast;
import kotlin.Metadata;

/* compiled from: CoreMainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class CoreMainActivity$ChildrenDetectCallBack$1 implements Runnable {
    final /* synthetic */ int $flag;
    final /* synthetic */ CoreMainActivity this$0;

    CoreMainActivity$ChildrenDetectCallBack$1(CoreMainActivity coreMainActivity, int i) {
        this.this$0 = coreMainActivity;
        this.$flag = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$flag;
        Toast toast = new Toast(this.this$0);
        toast.setText(i);
        toast.show();
    }
}
