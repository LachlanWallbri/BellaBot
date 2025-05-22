package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.widget.SeekBar;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceSeekBarChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001BÔ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012Q\b\u0002\u0010\n\u001aK\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b\u0012'\b\u0002\u0010\u0013\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014\u0012'\b\u0002\u0010\u0015\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014¢\u0006\u0002\u0010\u0016J\"\u0010\n\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018RZ\u0010\n\u001aK\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR0\u0010\u0013\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR0\u0010\u0015\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VoiceSeekBarChangeListener;", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "hadVoice", "", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onProgressChanged", "Lkotlin/Function3;", "Landroid/widget/SeekBar;", "Lkotlin/ParameterName;", "name", "seekBar", "progress", "fromUser", "", "onStartTrackingTouch", "Lkotlin/Function1;", "onStopTrackingTouch", "(ZLjava/util/Map;ILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getHadVoice", "()Z", "getOnProgressChanged", "()Lkotlin/jvm/functions/Function3;", "getOnStartTrackingTouch", "()Lkotlin/jvm/functions/Function1;", "getOnStopTrackingTouch", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
    private final boolean hadVoice;
    private final Function3<SeekBar, Integer, Boolean, Unit> onProgressChanged;
    private final Function1<SeekBar, Unit> onStartTrackingTouch;
    private final Function1<SeekBar, Unit> onStopTrackingTouch;
    private final Map<String, Object> param;
    private final int priority;

    public VoiceSeekBarChangeListener() {
        this(false, null, 0, null, null, null, 63, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceSeekBarChangeListener(boolean z, Map<String, ? extends Object> param, int i, Function3<? super SeekBar, ? super Integer, ? super Boolean, Unit> function3, Function1<? super SeekBar, Unit> function1, Function1<? super SeekBar, Unit> function12) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.hadVoice = z;
        this.param = param;
        this.priority = i;
        this.onProgressChanged = function3;
        this.onStartTrackingTouch = function1;
        this.onStopTrackingTouch = function12;
    }

    public final boolean getHadVoice() {
        return this.hadVoice;
    }

    public /* synthetic */ VoiceSeekBarChangeListener(boolean z, Map map, int i, Function3 function3, Function1 function1, Function1 function12, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? true : z, (i2 & 2) != 0 ? MapsKt.emptyMap() : map, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? (Function3) null : function3, (i2 & 16) != 0 ? (Function1) null : function1, (i2 & 32) != 0 ? (Function1) null : function12);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Function3<SeekBar, Integer, Boolean, Unit> getOnProgressChanged() {
        return this.onProgressChanged;
    }

    public final Function1<SeekBar, Unit> getOnStartTrackingTouch() {
        return this.onStartTrackingTouch;
    }

    public final Function1<SeekBar, Unit> getOnStopTrackingTouch() {
        return this.onStopTrackingTouch;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Function3<SeekBar, Integer, Boolean, Unit> function3 = this.onProgressChanged;
        if (function3 != null) {
            function3.invoke(seekBar, Integer.valueOf(progress), Boolean.valueOf(fromUser));
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        Function1<SeekBar, Unit> function1 = this.onStartTrackingTouch;
        if (function1 != null) {
            function1.invoke(seekBar);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        Function1<SeekBar, Unit> function1 = this.onStopTrackingTouch;
        if (function1 != null) {
            function1.invoke(seekBar);
        }
        if (this.hadVoice) {
            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.param);
        if (seekBar != null) {
            linkedHashMap.put(TrackKey.SEEKBAR_PROGRESS, Integer.valueOf(seekBar.getProgress()));
            ClickReport.INSTANCE.onClickEvent(seekBar, linkedHashMap, this.priority);
        }
    }
}
