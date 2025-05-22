package com.pudutech.peanut.robot_ui.util;

import android.text.TextPaint;
import android.widget.TextView;

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
}
