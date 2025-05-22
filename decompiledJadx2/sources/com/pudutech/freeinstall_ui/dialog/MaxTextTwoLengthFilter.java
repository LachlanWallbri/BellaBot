package com.pudutech.freeinstall_ui.dialog;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;

/* loaded from: classes3.dex */
public class MaxTextTwoLengthFilter implements InputFilter {
    private int mMaxLength;

    public MaxTextTwoLengthFilter(Context context, int i) {
        this.mMaxLength = i;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        while (i5 <= this.mMaxLength && i6 < spanned.length()) {
            int i7 = i6 + 1;
            i5 = spanned.charAt(i6) < 128 ? i5 + 1 : i5 + 2;
            i6 = i7;
        }
        if (i5 > this.mMaxLength) {
            return spanned.subSequence(0, i6 - 1);
        }
        int i8 = 0;
        while (i5 <= this.mMaxLength && i8 < charSequence.length()) {
            int i9 = i8 + 1;
            i5 = charSequence.charAt(i8) < 128 ? i5 + 1 : i5 + 2;
            i8 = i9;
        }
        if (i5 > this.mMaxLength) {
            i8--;
        }
        return charSequence.subSequence(0, i8);
    }
}
