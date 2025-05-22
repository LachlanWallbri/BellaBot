package com.pudutech.schedulerlib.p065ui;

import android.widget.TextView;
import com.pudutech.schedulerlib.C5725R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
final class SchedulerTestActivity$recvDataShow$1 implements Runnable {
    final /* synthetic */ int $index;
    final /* synthetic */ SchedulerTestActivity this$0;

    SchedulerTestActivity$recvDataShow$1(SchedulerTestActivity schedulerTestActivity, int i) {
        this.this$0 = schedulerTestActivity;
        this.$index = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView rec_tv = (TextView) this.this$0._$_findCachedViewById(C5725R.id.rec_tv);
        Intrinsics.checkExpressionValueIsNotNull(rec_tv, "rec_tv");
        rec_tv.setText("接收：" + this.$index);
    }
}
