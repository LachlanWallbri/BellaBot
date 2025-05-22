package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceItemLongClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B~\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012W\u0010\b\u001aS\u0012\u001d\u0012\u001b\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\t¢\u0006\u0002\u0010\u0012J,\u0010\u001a\u001a\u00020\u00112\u0010\u0010\r\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000Rb\u0010\b\u001aS\u0012\u001d\u0012\u001b\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VoiceItemLongClickListener;", "Lcom/chad/library/adapter/base/BaseQuickAdapter$OnItemLongClickListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onItemChildLongClick", "Lkotlin/Function3;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lkotlin/ParameterName;", "name", "adapter", "Landroid/view/View;", "view", RequestParameters.POSITION, "", "(Ljava/util/Map;ILkotlin/jvm/functions/Function3;)V", "TAG", "getOnItemChildLongClick", "()Lkotlin/jvm/functions/Function3;", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "onItemLongClick", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceItemLongClickListener implements BaseQuickAdapter.OnItemLongClickListener {
    private final String TAG;
    private final Function3<BaseQuickAdapter<?, ?>, View, Integer, Boolean> onItemChildLongClick;
    private final Map<String, Object> param;
    private final int priority;

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceItemLongClickListener(Map<String, ? extends Object> param, int i, Function3<? super BaseQuickAdapter<?, ?>, ? super View, ? super Integer, Boolean> onItemChildLongClick) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onItemChildLongClick, "onItemChildLongClick");
        this.param = param;
        this.priority = i;
        this.onItemChildLongClick = onItemChildLongClick;
        this.TAG = "VoiceItemLongClickListener";
    }

    public /* synthetic */ VoiceItemLongClickListener(Map map, int i, Function3 function3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i, function3);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Function3<BaseQuickAdapter<?, ?>, View, Integer, Boolean> getOnItemChildLongClick() {
        return this.onItemChildLongClick;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemLongClickListener
    public boolean onItemLongClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
        boolean booleanValue = this.onItemChildLongClick.invoke(adapter, view, Integer.valueOf(position)).booleanValue();
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
        if (view != null) {
            try {
                ClickReport clickReport = ClickReport.INSTANCE;
                ViewParent parent = view.getParent();
                if (parent == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                }
                clickReport.onClickEvent((ViewGroup) parent, MapsKt.mapOf(TuplesKt.m3968to(TrackKey.RECYCLERVIEW_POSITION, Integer.valueOf(position)), TuplesKt.m3968to(TrackKey.LONG_CLICK, true)), 0);
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "onItemLongClick:" + e.getMessage() + ' ');
                if (view != null) {
                    ClickReport.INSTANCE.onClickEvent(view, MapsKt.mapOf(TuplesKt.m3968to(TrackKey.RECYCLERVIEW_POSITION, Integer.valueOf(position)), TuplesKt.m3968to(TrackKey.LONG_CLICK, true)), 0);
                }
            }
        }
        return booleanValue;
    }
}
