package com.pudutech.freeinstall_ui.utils;

import android.view.View;
import android.widget.Checkable;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: Extands.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "it", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ExtandsKt$singleClick$1 implements View.OnClickListener {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ View $this_singleClick;
    final /* synthetic */ long $time;

    public ExtandsKt$singleClick$1(View view, long j, Function1 function1) {
        this.$this_singleClick = view;
        this.$time = j;
        this.$block = function1;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - ExtandsKt.getLastClickTime(this.$this_singleClick) > this.$time || (this.$this_singleClick instanceof Checkable)) {
            ExtandsKt.setLastClickTime(this.$this_singleClick, currentTimeMillis);
            this.$block.invoke(this.$this_singleClick);
        }
    }
}
