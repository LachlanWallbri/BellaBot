package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.View;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceLongClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\n¨\u0006\u000b"}, m3961d2 = {"onVoiceLongClickListener", "", "Landroid/view/View;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onLongClick", "Lkotlin/Function1;", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceLongClickListenerKt {
    public static /* synthetic */ void onVoiceLongClickListener$default(View view, Map map, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        onVoiceLongClickListener(view, map, i, function1);
    }

    public static final void onVoiceLongClickListener(View onVoiceLongClickListener, Map<String, ? extends Object> param, int i, Function1<? super View, Unit> onLongClick) {
        Intrinsics.checkParameterIsNotNull(onVoiceLongClickListener, "$this$onVoiceLongClickListener");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onLongClick, "onLongClick");
        onVoiceLongClickListener.setOnLongClickListener(new VoiceLongClickListener(param, i, onLongClick));
    }
}
