package com.pudutech.bumblebee.robot_ui.track.report;

import android.view.View;
import java.util.Map;
import kotlin.Metadata;
import org.apache.commons.logging.LogFactory;

/* compiled from: IClickReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/report/IClickReport;", "", "onClickEvent", "", "v", "Landroid/view/View;", "param", "", "", LogFactory.PRIORITY_KEY, "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface IClickReport {
    void onClickEvent(View v, Map<String, ? extends Object> param, int priority);
}
