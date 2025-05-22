package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.os.SystemClock;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: SingleClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B3\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0012\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "Landroid/view/View$OnClickListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onSingleClick", "Lkotlin/Function0;", "", "(Ljava/util/Map;ILkotlin/jvm/functions/Function0;)V", "lastClickTimestamp_ms", "", "getOnSingleClick", "()Lkotlin/jvm/functions/Function0;", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "onClick", "v", "Landroid/view/View;", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SingleClickListener implements View.OnClickListener {
    private long lastClickTimestamp_ms;
    private final Function0<Unit> onSingleClick;
    private final Map<String, Object> param;
    private final int priority;
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    public SingleClickListener(Map<String, ? extends Object> param, int i, Function0<Unit> onSingleClick) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onSingleClick, "onSingleClick");
        this.param = param;
        this.priority = i;
        this.onSingleClick = onSingleClick;
        this.lastClickTimestamp_ms = -1L;
    }

    public /* synthetic */ SingleClickListener(Map map, int i, Function0 function0, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i, function0);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Function0<Unit> getOnSingleClick() {
        return this.onSingleClick;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Pdlog.m3276v(TAG, "onClick v=" + v);
        if (SystemClock.elapsedRealtime() - this.lastClickTimestamp_ms < 600) {
            Pdlog.m3277w(TAG, "click " + v + " too fast in " + (SystemClock.elapsedRealtime() - this.lastClickTimestamp_ms));
        } else {
            this.onSingleClick.invoke();
            if (v != null) {
                ClickReport.INSTANCE.onClickEvent(v, MapsKt.emptyMap(), 0);
            }
        }
        this.lastClickTimestamp_ms = SystemClock.elapsedRealtime();
    }
}
