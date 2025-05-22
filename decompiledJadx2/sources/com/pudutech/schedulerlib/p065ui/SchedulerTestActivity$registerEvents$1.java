package com.pudutech.schedulerlib.p065ui;

import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.p065ui.SchedulerTestActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JH\u0010\u0002\u001a\u00020\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00070\u00052\u001e\u0010\t\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00070\u0005H\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/schedulerlib/ui/SchedulerTestActivity$registerEvents$1", "Lcom/pudutech/schedulerlib/ScheduleController$FPSCallback;", "updateFPS", "", "fps", "Ljava/util/LinkedHashMap;", "", "", "", "eps", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SchedulerTestActivity$registerEvents$1 implements ScheduleController.FPSCallback {
    final /* synthetic */ SchedulerTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchedulerTestActivity$registerEvents$1(SchedulerTestActivity schedulerTestActivity) {
        this.this$0 = schedulerTestActivity;
    }

    @Override // com.pudutech.schedulerlib.ScheduleController.FPSCallback
    public void updateFPS(final LinkedHashMap<String, Map<String, Integer>> fps, final LinkedHashMap<String, Map<String, String>> eps) {
        Intrinsics.checkParameterIsNotNull(fps, "fps");
        Intrinsics.checkParameterIsNotNull(eps, "eps");
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$registerEvents$1$updateFPS$1
            @Override // java.lang.Runnable
            public final void run() {
                SchedulerTestActivity.OnReceivedMsgListener onReceivedMsgListener;
                SchedulerTestActivity.OnErrorFrameListener onErrorFrameListener;
                Pdlog.m3273d(SchedulerTestActivity$registerEvents$1.this.this$0.getTAG(), "FPS: " + fps + ", EPS: " + eps);
                SchedulerTestActivity$registerEvents$1.this.this$0.resetStatus(fps);
                onReceivedMsgListener = SchedulerTestActivity$registerEvents$1.this.this$0.onReceivedMsgListener;
                if (onReceivedMsgListener != null) {
                    onReceivedMsgListener.onReceivedMsg(fps);
                }
                onErrorFrameListener = SchedulerTestActivity$registerEvents$1.this.this$0.onErrorMsgListener;
                if (onErrorFrameListener != null) {
                    onErrorFrameListener.onReceivedMsg(eps);
                }
            }
        });
    }
}
