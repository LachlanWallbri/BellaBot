package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.widget.CompoundButton;
import com.pudutech.base.Pdlog;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceSwitchChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001Bg\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00126\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u000b¢\u0006\u0002\u0010\u0012J\u0018\u0010\n\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\tH\u0016R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015RA\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VoiceSwitchChangeListener;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "filterUser", "", "onCheckedChanged", "Lkotlin/Function2;", "Landroid/widget/CompoundButton;", "Lkotlin/ParameterName;", "name", "buttonView", "isChecked", "", "(Ljava/util/Map;IZLkotlin/jvm/functions/Function2;)V", "TAG", "getFilterUser", "()Z", "getOnCheckedChanged", "()Lkotlin/jvm/functions/Function2;", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceSwitchChangeListener implements CompoundButton.OnCheckedChangeListener {
    private final String TAG;
    private final boolean filterUser;
    private final Function2<CompoundButton, Boolean, Unit> onCheckedChanged;
    private final Map<String, Object> param;
    private final int priority;

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceSwitchChangeListener(Map<String, ? extends Object> param, int i, boolean z, Function2<? super CompoundButton, ? super Boolean, Unit> onCheckedChanged) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onCheckedChanged, "onCheckedChanged");
        this.param = param;
        this.priority = i;
        this.filterUser = z;
        this.onCheckedChanged = onCheckedChanged;
        this.TAG = "VoiceSwitchChangeListener";
    }

    public /* synthetic */ VoiceSwitchChangeListener(Map map, int i, boolean z, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? true : z, function2);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final boolean getFilterUser() {
        return this.filterUser;
    }

    public final Function2<CompoundButton, Boolean, Unit> getOnCheckedChanged() {
        return this.onCheckedChanged;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
        Pdlog.m3273d(this.TAG, "onCheckedChanged: buttonView:" + buttonView + ",isChecked:" + isChecked + ",buttonView?.isPressed:" + buttonView.isPressed());
        if (!this.filterUser || buttonView.isPressed()) {
            this.onCheckedChanged.invoke(buttonView, Boolean.valueOf(isChecked));
            PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(this.param);
            linkedHashMap.put(TrackKey.SWITCH_CHECK, Boolean.valueOf(isChecked));
            ClickReport.INSTANCE.onClickEvent(buttonView, linkedHashMap, this.priority);
        }
    }
}
