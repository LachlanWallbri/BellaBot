package com.pudu.library.loracall.utils;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.databinding.BindingAdapter;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.loracall.library.C3965R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DetailBindingAdapters.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007\u001am\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001a\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¨\u0006\u0015"}, m3961d2 = {"setDevAdder", "", "view", "Landroid/widget/TextView;", "s", "", "viewRadius", "Landroid/view/View;", "radius", "", "viewShape", "", "topLeftRadius", "topRightRadius", "bottomLeftRadius", "bottomRightRadius", TypedValues.Custom.S_COLOR, "width", "strokeColor", "(Landroid/view/View;Ljava/lang/Float;FFFFLjava/lang/String;Ljava/lang/Float;Ljava/lang/String;)V", "visibilityEmpty", "library_loracall_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DetailBindingAdaptersKt {
    @BindingAdapter(requireAll = false, value = {"shape_radius", "shape_topLeftRadius", "shape_topRightRadius", "shape_bottomLeftRadius", "shape_bottomRightRadius", "shape_color", "shape_stroke_width", "shape_stroke_color"})
    public static final void viewShape(View view, Float f, float f2, float f3, float f4, float f5, String str, Float f6, String str2) {
        float[] fArr;
        Intrinsics.checkParameterIsNotNull(view, "view");
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (f != null) {
            f.floatValue();
            float dp = KotlinUtilsKt.getDp(f.floatValue());
            fArr = new float[]{dp, dp, dp, dp, dp, dp, dp, dp};
        } else {
            fArr = new float[]{KotlinUtilsKt.getDp(f2), KotlinUtilsKt.getDp(f2), KotlinUtilsKt.getDp(f3), KotlinUtilsKt.getDp(f3), KotlinUtilsKt.getDp(f5), KotlinUtilsKt.getDp(f5), KotlinUtilsKt.getDp(f4), KotlinUtilsKt.getDp(f4)};
        }
        gradientDrawable.setCornerRadii(fArr);
        if (str != null) {
            gradientDrawable.setColor(KotlinUtilsKt.parseColor(str));
        }
        if (f6 != null && str2 != null) {
            gradientDrawable.setStroke((int) KotlinUtilsKt.getDp(f6.floatValue()), KotlinUtilsKt.parseColor(str2));
        }
        view.setBackground(gradientDrawable);
    }

    @BindingAdapter({"view_radius"})
    public static final void viewRadius(View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        view.setOutlineProvider(new CircleViewOutlineProvider(KotlinUtilsKt.getDp(i)));
        view.setClipToOutline(true);
    }

    @BindingAdapter({"visibility_empty"})
    public static final void visibilityEmpty(View view, String str) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
    }

    @BindingAdapter({"set_devAdder"})
    public static final void setDevAdder(TextView view, String s) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(s, "s");
        view.setText("（" + view.getContext().getString(C3965R.string.lora_bind_id) + s + "）");
    }
}
