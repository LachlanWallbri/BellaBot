package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class MaterialResources {
    private MaterialResources() {
    }

    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, int i) {
        int color;
        int resourceId;
        ColorStateList colorStateList;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0 && (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) != null) {
            return colorStateList;
        }
        if (Build.VERSION.SDK_INT <= 15 && (color = typedArray.getColor(i, -1)) != -1) {
            return ColorStateList.valueOf(color);
        }
        return typedArray.getColorStateList(i);
    }

    public static ColorStateList getColorStateList(Context context, TintTypedArray tintTypedArray, int i) {
        int color;
        int resourceId;
        ColorStateList colorStateList;
        if (tintTypedArray.hasValue(i) && (resourceId = tintTypedArray.getResourceId(i, 0)) != 0 && (colorStateList = AppCompatResources.getColorStateList(context, resourceId)) != null) {
            return colorStateList;
        }
        if (Build.VERSION.SDK_INT <= 15 && (color = tintTypedArray.getColor(i, -1)) != -1) {
            return ColorStateList.valueOf(color);
        }
        return tintTypedArray.getColorStateList(i);
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable drawable;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (drawable = AppCompatResources.getDrawable(context, resourceId)) == null) ? typedArray.getDrawable(i) : drawable;
    }

    public static TextAppearance getTextAppearance(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return new TextAppearance(context, resourceId);
    }

    public static int getDimensionPixelSize(Context context, TypedArray typedArray, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i, i2);
        }
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, i2);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getIndexWithValue(TypedArray typedArray, int i, int i2) {
        return typedArray.hasValue(i) ? i : i2;
    }
}
