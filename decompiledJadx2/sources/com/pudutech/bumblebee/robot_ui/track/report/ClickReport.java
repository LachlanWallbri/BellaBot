package com.pudutech.bumblebee.robot_ui.track.report;

import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.click.ClickArgs;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: ClickReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/report/ClickReport;", "Lcom/pudutech/bumblebee/robot_ui/track/report/IClickReport;", "()V", "TAG", "", "onClickEvent", "", "v", "Landroid/view/View;", "param", "", "", LogFactory.PRIORITY_KEY, "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ClickReport implements IClickReport {
    public static final ClickReport INSTANCE = new ClickReport();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private ClickReport() {
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.report.IClickReport
    public void onClickEvent(View v, Map<String, ? extends Object> param, int priority) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Pdlog.m3273d(TAG, "onClickEvent: view:" + v + ",param:" + param + ",priority:" + priority);
        PuduEventTrackingManager.INSTANCE.onClick(v, new ClickArgs(param, priority));
    }
}
