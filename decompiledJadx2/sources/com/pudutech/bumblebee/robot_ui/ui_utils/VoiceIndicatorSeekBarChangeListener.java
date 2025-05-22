package com.pudutech.bumblebee.robot_ui.ui_utils;

import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceIndicatorSeekBarChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B \u0001\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012'\b\u0002\u0010\b\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t\u0012'\b\u0002\u0010\u000f\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t\u0012'\b\u0002\u0010\u0012\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\u0002\u0010\u0013J\u0012\u0010\b\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016R0\u0010\b\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R0\u0010\u000f\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R0\u0010\u0012\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VoiceIndicatorSeekBarChangeListener;", "Lcom/warkiz/widget/OnSeekChangeListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onSeeking", "Lkotlin/Function1;", "Lcom/warkiz/widget/SeekParams;", "Lkotlin/ParameterName;", "name", "seekParams", "", "onStartTrackingTouch", "Lcom/warkiz/widget/IndicatorSeekBar;", "seekBar", "onStopTrackingTouch", "(Ljava/util/Map;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getOnSeeking", "()Lkotlin/jvm/functions/Function1;", "getOnStartTrackingTouch", "getOnStopTrackingTouch", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceIndicatorSeekBarChangeListener implements OnSeekChangeListener {
    private final Function1<SeekParams, Unit> onSeeking;
    private final Function1<IndicatorSeekBar, Unit> onStartTrackingTouch;
    private final Function1<IndicatorSeekBar, Unit> onStopTrackingTouch;
    private final Map<String, Object> param;
    private final int priority;

    public VoiceIndicatorSeekBarChangeListener() {
        this(null, 0, null, null, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceIndicatorSeekBarChangeListener(Map<String, ? extends Object> param, int i, Function1<? super SeekParams, Unit> function1, Function1<? super IndicatorSeekBar, Unit> function12, Function1<? super IndicatorSeekBar, Unit> function13) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.param = param;
        this.priority = i;
        this.onSeeking = function1;
        this.onStartTrackingTouch = function12;
        this.onStopTrackingTouch = function13;
    }

    public /* synthetic */ VoiceIndicatorSeekBarChangeListener(Map map, int i, Function1 function1, Function1 function12, Function1 function13, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? (Function1) null : function1, (i2 & 8) != 0 ? (Function1) null : function12, (i2 & 16) != 0 ? (Function1) null : function13);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Function1<SeekParams, Unit> getOnSeeking() {
        return this.onSeeking;
    }

    public final Function1<IndicatorSeekBar, Unit> getOnStartTrackingTouch() {
        return this.onStartTrackingTouch;
    }

    public final Function1<IndicatorSeekBar, Unit> getOnStopTrackingTouch() {
        return this.onStopTrackingTouch;
    }

    @Override // com.warkiz.widget.OnSeekChangeListener
    public void onSeeking(SeekParams seekParams) {
        Function1<SeekParams, Unit> function1 = this.onSeeking;
        if (function1 != null) {
            function1.invoke(seekParams);
        }
    }

    @Override // com.warkiz.widget.OnSeekChangeListener
    public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
        Function1<IndicatorSeekBar, Unit> function1 = this.onStartTrackingTouch;
        if (function1 != null) {
            function1.invoke(seekBar);
        }
    }

    @Override // com.warkiz.widget.OnSeekChangeListener
    public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
        Function1<IndicatorSeekBar, Unit> function1 = this.onStopTrackingTouch;
        if (function1 != null) {
            function1.invoke(seekBar);
        }
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.param);
        if (seekBar != null) {
            linkedHashMap.put(TrackKey.SEEKBAR_PROGRESS, Float.valueOf(seekBar.getProgressFloat()));
            ClickReport.INSTANCE.onClickEvent(seekBar, linkedHashMap, this.priority);
        }
    }
}
