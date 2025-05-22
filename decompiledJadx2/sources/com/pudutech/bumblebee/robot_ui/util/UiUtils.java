package com.pudutech.bumblebee.robot_ui.util;

import android.text.TextPaint;
import android.widget.TextView;

/* loaded from: classes4.dex */
public class UiUtils {
    public static float adjustTvTextSize(TextView textView, int i, String str) {
        int paddingStart = ((i - textView.getPaddingStart()) - textView.getPaddingEnd()) - 10;
        if (paddingStart <= 0) {
            return paddingStart;
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        float textSize = textPaint.getTextSize();
        while (textPaint.measureText(str) > paddingStart) {
            textSize -= 1.0f;
            textPaint.setTextSize(textSize);
        }
        textView.setTextSize(0, textSize);
        return textSize;
    }

    public static float adjustTvTextSize(TextView textView, int i, String str, int i2) {
        int paddingStart = ((i - textView.getPaddingStart()) - textView.getPaddingEnd()) - 10;
        if (paddingStart <= 0) {
            return paddingStart;
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        float textSize = textPaint.getTextSize();
        while (textPaint.measureText(str) > paddingStart) {
            textSize -= 1.0f;
            textPaint.setTextSize(textSize);
            if (i2 != 0 && textSize <= i2) {
                break;
            }
        }
        textView.setTextSize(0, textSize);
        return textSize;
    }
}
