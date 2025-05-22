package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.View;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceLongClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\u0010\fJ\u0010\u0010\b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0016R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VoiceLongClickListener;", "Landroid/view/View$OnLongClickListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onLongClick", "Lkotlin/Function1;", "Landroid/view/View;", "", "(Ljava/util/Map;ILkotlin/jvm/functions/Function1;)V", "getOnLongClick", "()Lkotlin/jvm/functions/Function1;", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "", "v", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class VoiceLongClickListener implements View.OnLongClickListener {
    private final Function1<View, Unit> onLongClick;
    private final Map<String, Object> param;
    private final int priority;

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceLongClickListener(Map<String, ? extends Object> param, int i, Function1<? super View, Unit> onLongClick) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onLongClick, "onLongClick");
        this.param = param;
        this.priority = i;
        this.onLongClick = onLongClick;
    }

    public /* synthetic */ VoiceLongClickListener(Map map, int i, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i, function1);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Function1<View, Unit> getOnLongClick() {
        return this.onLongClick;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        this.onLongClick.invoke(v);
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
        ClickReport.INSTANCE.onClickEvent(v, MapsKt.emptyMap(), 0);
        return true;
    }
}
