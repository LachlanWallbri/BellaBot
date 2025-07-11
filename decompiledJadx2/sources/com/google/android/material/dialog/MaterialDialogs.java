package com.google.android.material.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import com.google.android.material.C1653R;
import com.google.android.material.internal.ThemeEnforcement;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class MaterialDialogs {
    private MaterialDialogs() {
    }

    public static InsetDrawable insetDrawable(Drawable drawable, Rect rect) {
        return new InsetDrawable(drawable, rect.left, rect.top, rect.right, rect.bottom);
    }

    public static Rect getDialogBackgroundInsets(Context context, int i, int i2) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, null, C1653R.styleable.MaterialAlertDialog, i, i2, new int[0]);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C1653R.styleable.MaterialAlertDialog_backgroundInsetStart, context.getResources().getDimensionPixelSize(C1653R.dimen.mtrl_alert_dialog_background_inset_start));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(C1653R.styleable.MaterialAlertDialog_backgroundInsetTop, context.getResources().getDimensionPixelSize(C1653R.dimen.mtrl_alert_dialog_background_inset_top));
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(C1653R.styleable.MaterialAlertDialog_backgroundInsetEnd, context.getResources().getDimensionPixelSize(C1653R.dimen.mtrl_alert_dialog_background_inset_end));
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(C1653R.styleable.MaterialAlertDialog_backgroundInsetBottom, context.getResources().getDimensionPixelSize(C1653R.dimen.mtrl_alert_dialog_background_inset_bottom));
        obtainStyledAttributes.recycle();
        if (Build.VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == 1) {
            dimensionPixelSize3 = dimensionPixelSize;
            dimensionPixelSize = dimensionPixelSize3;
        }
        return new Rect(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize4);
    }
}
