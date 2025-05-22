package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.widget.RadioGroup;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceRadioGroupChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001as\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00022\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\t28\u0010\n\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\t¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, m3961d2 = {"onVoiceSwitchChanged", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/widget/RadioGroup;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onCheckedChanged", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", MapElement.Key.GROUP, "checkedId", "(Landroid/widget/RadioGroup;Ljava/util/Map;ILkotlin/jvm/functions/Function2;)V", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceRadioGroupChangeListenerKt {
    public static /* synthetic */ void onVoiceSwitchChanged$default(RadioGroup radioGroup, Map map, int i, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        onVoiceSwitchChanged(radioGroup, map, i, function2);
    }

    public static final <T extends RadioGroup> void onVoiceSwitchChanged(T onVoiceSwitchChanged, Map<String, ? extends Object> param, int i, Function2<? super RadioGroup, ? super Integer, Unit> onCheckedChanged) {
        Intrinsics.checkParameterIsNotNull(onVoiceSwitchChanged, "$this$onVoiceSwitchChanged");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onCheckedChanged, "onCheckedChanged");
        onVoiceSwitchChanged.setOnCheckedChangeListener(new VoiceRadioGroupChangeListener(param, i, onCheckedChanged));
    }
}
