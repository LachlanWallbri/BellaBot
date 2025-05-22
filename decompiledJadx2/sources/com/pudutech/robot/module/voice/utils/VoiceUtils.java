package com.pudutech.robot.module.voice.utils;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u001b\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\r2\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u001b\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\r2\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/utils/VoiceUtils;", "", "()V", "TAG", "", "checkDigitAndLetterOnly", "", "chars", "checkDoubleDigits", "checkLetter", "char", "", "toDoubleDigitsItem", "", "(Ljava/lang/String;)[Ljava/lang/String;", "toItem", "toItems", "toTrayItem", "trayIndex", "", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoiceUtils {
    public static final VoiceUtils INSTANCE = new VoiceUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    public final boolean checkLetter(char r3) {
        if ('A' > r3 || 'Z' < r3) {
            return 'a' <= r3 && 'z' >= r3;
        }
        return true;
    }

    private VoiceUtils() {
    }

    public final String toItem(char r5) {
        if (Character.isDigit(r5)) {
            if (r5 == '0') {
                return "voice30_10";
            }
            return "voice30_" + String.valueOf(r5);
        }
        if (checkLetter(r5)) {
            return "voice31_" + ((r5 < 'a' ? r5 - 'A' : r5 - 'a') + 1);
        }
        Pdlog.m3277w(TAG, r5 + " not a digit or a letter");
        return null;
    }

    public final String[] toItems(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        int length = chars.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = null;
        }
        int length2 = strArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            strArr[i2] = toItem(chars.charAt(i2));
        }
        return strArr;
    }

    public final String toTrayItem(int trayIndex) {
        if (trayIndex <= 6 && trayIndex >= 1) {
            try {
                return "voice40_" + trayIndex;
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "toTrayItem index=" + trayIndex + ' ' + e);
            }
        }
        return null;
    }

    public final boolean checkDigitAndLetterOnly(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        int length = chars.length();
        for (int i = 0; i < length; i++) {
            char charAt = chars.charAt(i);
            if (!Character.isDigit(charAt) && !checkLetter(charAt)) {
                return false;
            }
        }
        return true;
    }

    public final boolean checkDoubleDigits(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        return chars.length() == 2 && Character.isDigit(chars.charAt(0)) && Character.isDigit(chars.charAt(1));
    }

    public final String[] toDoubleDigitsItem(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        if (checkDoubleDigits(chars)) {
            if (chars.charAt(0) == '0') {
                return toItems(chars);
            }
            if (chars.charAt(1) == '0') {
                String[] strArr = new String[1];
                for (int i = 0; i < 1; i++) {
                    strArr[i] = "voice33_" + chars.charAt(0);
                }
                return strArr;
            }
            String[] strArr2 = new String[2];
            for (int i2 = 0; i2 < 2; i2++) {
                strArr2[i2] = null;
            }
            strArr2[0] = "voice33_" + chars.charAt(0);
            strArr2[1] = "voice30_" + chars.charAt(1);
            return strArr2;
        }
        return toItems(chars);
    }
}
