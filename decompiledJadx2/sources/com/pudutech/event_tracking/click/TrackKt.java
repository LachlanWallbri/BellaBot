package com.pudutech.event_tracking.click;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: track.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\b\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\t¨\u0006\n"}, m3961d2 = {"onPdClick", "", "Landroid/view/View;", "debounce", "", "args", "Lcom/pudutech/event_tracking/click/ClickArgs;", "block", "Lkotlin/Function1;", "Landroid/view/View$OnClickListener;", "event_tracking_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TrackKt {
    public static /* synthetic */ void onPdClick$default(View view, int i, ClickArgs clickArgs, View.OnClickListener onClickListener, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 300;
        }
        if ((i2 & 2) != 0) {
            clickArgs = new ClickArgs(null, 0, 3, null);
        }
        onPdClick(view, i, clickArgs, onClickListener);
    }

    public static final void onPdClick(View onPdClick, int i, ClickArgs args, View.OnClickListener block) {
        Intrinsics.checkParameterIsNotNull(onPdClick, "$this$onPdClick");
        Intrinsics.checkParameterIsNotNull(args, "args");
        Intrinsics.checkParameterIsNotNull(block, "block");
        onPdClick.setOnClickListener(new PdClickWrap(i, args, block));
    }

    public static /* synthetic */ void onPdClick$default(View view, int i, ClickArgs clickArgs, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 300;
        }
        if ((i2 & 2) != 0) {
            clickArgs = new ClickArgs(null, 0, 3, null);
        }
        onPdClick(view, i, clickArgs, (Function1<? super View, Unit>) function1);
    }

    public static final void onPdClick(View onPdClick, int i, ClickArgs args, final Function1<? super View, Unit> block) {
        Intrinsics.checkParameterIsNotNull(onPdClick, "$this$onPdClick");
        Intrinsics.checkParameterIsNotNull(args, "args");
        Intrinsics.checkParameterIsNotNull(block, "block");
        onPdClick(onPdClick, i, args, new View.OnClickListener() { // from class: com.pudutech.event_tracking.click.TrackKt$onPdClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                function1.invoke(it);
            }
        });
    }
}
