package com.pudutech.disinfect.baselib.ext.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ViewExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a7\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00070\u000b\u001a\n\u0010\u000f\u001a\u00020\u0007*\u00020\b\u001a\n\u0010\u0010\u001a\u00020\u0007*\u00020\b\u001a=\u0010\u0011\u001a\u00020\u0007*\u0004\u0018\u00010\u00122!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00070\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016\u001a\n\u0010\u0017\u001a\u00020\u0007*\u00020\b\u001a\u0012\u0010\u0018\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a\u001a\u0012\u0010\u001b\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u001c"}, m3961d2 = {"lastClickTime", "", "getLastClickTime", "()J", "setLastClickTime", "(J)V", "clickNotRepeat", "", "Landroid/view/View;", "interval", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "gone", "invisible", "notNull", "", "notNullAction", ES6Iterator.VALUE_PROPERTY, "nullAction", "Lkotlin/Function0;", "visible", "visibleOrGone", "flag", "", "visibleOrInVisible", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ViewExtKt {
    private static long lastClickTime;

    public static final void visible(View visible) {
        Intrinsics.checkParameterIsNotNull(visible, "$this$visible");
        visible.setVisibility(0);
    }

    public static final void invisible(View invisible) {
        Intrinsics.checkParameterIsNotNull(invisible, "$this$invisible");
        invisible.setVisibility(4);
    }

    public static final void visibleOrGone(View visibleOrGone, boolean z) {
        Intrinsics.checkParameterIsNotNull(visibleOrGone, "$this$visibleOrGone");
        visibleOrGone.setVisibility(z ? 0 : 8);
    }

    public static final void visibleOrInVisible(View visibleOrInVisible, boolean z) {
        Intrinsics.checkParameterIsNotNull(visibleOrInVisible, "$this$visibleOrInVisible");
        visibleOrInVisible.setVisibility(z ? 0 : 4);
    }

    public static final void gone(View gone) {
        Intrinsics.checkParameterIsNotNull(gone, "$this$gone");
        gone.setVisibility(8);
    }

    public static final long getLastClickTime() {
        return lastClickTime;
    }

    public static final void setLastClickTime(long j) {
        lastClickTime = j;
    }

    public static /* synthetic */ void clickNotRepeat$default(View view, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 500;
        }
        clickNotRepeat(view, j, function1);
    }

    public static final void clickNotRepeat(View clickNotRepeat, final long j, final Function1<? super View, Unit> action) {
        Intrinsics.checkParameterIsNotNull(clickNotRepeat, "$this$clickNotRepeat");
        Intrinsics.checkParameterIsNotNull(action, "action");
        clickNotRepeat.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.disinfect.baselib.ext.view.ViewExtKt$clickNotRepeat$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                long currentTimeMillis = System.currentTimeMillis();
                if (ViewExtKt.getLastClickTime() == 0 || currentTimeMillis - ViewExtKt.getLastClickTime() >= j) {
                    ViewExtKt.setLastClickTime(currentTimeMillis);
                    Function1 function1 = action;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    function1.invoke(it);
                }
            }
        });
    }

    public static final void notNull(Object obj, Function1<Object, Unit> notNullAction, Function0<Unit> nullAction) {
        Intrinsics.checkParameterIsNotNull(notNullAction, "notNullAction");
        Intrinsics.checkParameterIsNotNull(nullAction, "nullAction");
        if (obj != null) {
            notNullAction.invoke(obj);
        } else {
            nullAction.invoke();
        }
    }
}
