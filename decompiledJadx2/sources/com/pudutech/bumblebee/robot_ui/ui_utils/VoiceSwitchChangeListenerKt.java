package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.widget.CompoundButton;
import androidx.exifinterface.media.ExifInterface;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceSwitchChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a{\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00022\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b26\u0010\f\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"onVoiceSwitchChanged", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/widget/CompoundButton;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "filterUser", "", "onCheckedChanged", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buttonView", "isChecked", "(Landroid/widget/CompoundButton;Ljava/util/Map;IZLkotlin/jvm/functions/Function2;)V", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceSwitchChangeListenerKt {
    public static /* synthetic */ void onVoiceSwitchChanged$default(CompoundButton compoundButton, Map map, int i, boolean z, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        onVoiceSwitchChanged(compoundButton, map, i, z, function2);
    }

    public static final <T extends CompoundButton> void onVoiceSwitchChanged(T onVoiceSwitchChanged, Map<String, ? extends Object> param, int i, boolean z, Function2<? super CompoundButton, ? super Boolean, Unit> onCheckedChanged) {
        Intrinsics.checkParameterIsNotNull(onVoiceSwitchChanged, "$this$onVoiceSwitchChanged");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onCheckedChanged, "onCheckedChanged");
        onVoiceSwitchChanged.setOnCheckedChangeListener(new VoiceSwitchChangeListener(param, i, z, onCheckedChanged));
    }
}
