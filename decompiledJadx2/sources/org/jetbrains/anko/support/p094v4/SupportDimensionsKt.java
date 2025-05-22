package org.jetbrains.anko.support.p094v4;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SupportDimensions.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\u0007\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001H\u0086\b¨\u0006\u000b"}, m3961d2 = {"dimen", "", "Landroidx/fragment/app/Fragment;", "resource", "dip", ES6Iterator.VALUE_PROPERTY, "", "px2dip", "px", "px2sp", "sp", "supportV4-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportDimensionsKt {
    public static final int dip(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.dip((Context) requireActivity, i);
    }

    public static final int dip(Fragment receiver$0, float f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.dip(requireActivity, f);
    }

    /* renamed from: sp */
    public static final int m4177sp(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.m4170sp((Context) requireActivity, i);
    }

    /* renamed from: sp */
    public static final int m4176sp(Fragment receiver$0, float f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.m4169sp(requireActivity, f);
    }

    public static final float px2dip(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.px2dip(requireActivity, i);
    }

    public static final float px2sp(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.px2sp(requireActivity, i);
    }

    public static final int dimen(Fragment receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return DimensionsKt.dimen(requireActivity, i);
    }
}
