package com.pudutech.disinfect.baselib.util;

import android.text.TextPaint;
import android.widget.TextView;
import com.pudutech.disinfect.baselib.base.BaseApp;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class UiUtils {
    public static float adjustTvTextSize(TextView textView, int i, String str) {
        int paddingLeft = ((i - textView.getPaddingLeft()) - textView.getPaddingRight()) - 10;
        if (paddingLeft <= 0) {
            return paddingLeft;
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        float textSize = textPaint.getTextSize();
        while (textPaint.measureText(str) > paddingLeft) {
            textSize -= 1.0f;
            textPaint.setTextSize(textSize);
        }
        textView.setTextSize(0, textSize);
        return textSize;
    }

    public static float adjustTvTextSize(TextView textView, int i, String str, int i2) {
        int paddingLeft = ((i - textView.getPaddingLeft()) - textView.getPaddingRight()) - 10;
        if (paddingLeft <= 0) {
            return paddingLeft;
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        float textSize = textPaint.getTextSize();
        while (textPaint.measureText(str) > paddingLeft) {
            textSize -= 1.0f;
            textPaint.setTextSize(textSize);
            if (i2 != 0 && textSize <= i2) {
                break;
            }
        }
        textView.setTextSize(0, textSize);
        return textSize;
    }

    public static int px2dip(float f) {
        return (int) ((f / BaseApp.INSTANCE.getINSTANCE().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dip2px(float f) {
        return (int) ((f * BaseApp.INSTANCE.getINSTANCE().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(float f) {
        return (int) ((f / BaseApp.INSTANCE.getINSTANCE().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((f * BaseApp.INSTANCE.getINSTANCE().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
