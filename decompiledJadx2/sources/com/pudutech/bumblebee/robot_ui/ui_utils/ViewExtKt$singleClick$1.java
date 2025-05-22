package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.View;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui_utils/ViewExtKt$singleClick$1", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onSingleClick", "", "v", "Landroid/view/View;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ViewExtKt$singleClick$1 extends OnLazyVoiceClickListener {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Map $param;
    final /* synthetic */ int $priority;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewExtKt$singleClick$1(Function1 function1, Map map, int i, Map map2, int i2) {
        super(map2, i2);
        this.$block = function1;
        this.$param = map;
        this.$priority = i;
    }

    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
    public void onSingleClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        super.onSingleClick(v);
        this.$block.invoke(v);
    }
}
