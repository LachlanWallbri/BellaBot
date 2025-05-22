package com.pudutech.resources.voice;

import android.text.TextUtils;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: VoiceUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u0007\u001a\u00020\u0004J\u001b\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\u0012J\u001b\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\fJ\u001b\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/resources/voice/VoiceUtils;", "", "()V", "TAG", "", "checkDigitAndLetterOnly", "", "chars", "checkDoubleDigits", "checkEuropeanPlayFormat", "checkLetter", "char", "", "splitDigitsLetter", "", "toDoubleDigitsItem", "", "Lcom/pudutech/resources/voice/VoiceItem;", "(Ljava/lang/String;)[Lcom/pudutech/resources/voice/VoiceItem;", "toEuropeanPlayFormat", "toItem", "toItems", "toTrayItem", "trayIndex", "", "resources_bellabot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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

    public final VoiceItem toItem(char r5) {
        if (Character.isDigit(r5)) {
            if (r5 == '0') {
                return VoiceItem.valueOf("voice30_10");
            }
            return VoiceItem.valueOf("voice30_" + VoiceUtilsKt.transformNumber(r5));
        }
        if (checkLetter(r5)) {
            return VoiceItem.valueOf("voice31_" + ((r5 < 'a' ? r5 - 'A' : r5 - 'a') + 1));
        }
        Pdlog.m3277w(TAG, r5 + " not a digit or a letter");
        return null;
    }

    public final VoiceItem[] toItems(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        int length = chars.length();
        VoiceItem[] voiceItemArr = new VoiceItem[length];
        for (int i = 0; i < length; i++) {
            voiceItemArr[i] = null;
        }
        int length2 = voiceItemArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            voiceItemArr[i2] = toItem(chars.charAt(i2));
        }
        return voiceItemArr;
    }

    public final VoiceItem toTrayItem(int trayIndex) {
        if (trayIndex <= 6 && trayIndex >= 1) {
            try {
                return VoiceItem.valueOf("voice40_" + trayIndex);
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

    public final VoiceItem[] toDoubleDigitsItem(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        if (checkDoubleDigits(chars)) {
            if (chars.charAt(0) == '0') {
                return toItems(chars);
            }
            if (chars.charAt(1) == '0') {
                VoiceItem[] voiceItemArr = new VoiceItem[1];
                for (int i = 0; i < 1; i++) {
                    voiceItemArr[i] = VoiceItem.valueOf("voice33_" + chars.charAt(0));
                }
                return voiceItemArr;
            }
            VoiceItem[] voiceItemArr2 = new VoiceItem[2];
            for (int i2 = 0; i2 < 2; i2++) {
                voiceItemArr2[i2] = null;
            }
            voiceItemArr2[0] = VoiceItem.valueOf("voice33_" + chars.charAt(0));
            voiceItemArr2[1] = VoiceItem.valueOf("voice30_" + chars.charAt(1));
            return voiceItemArr2;
        }
        return toItems(chars);
    }

    public final boolean checkEuropeanPlayFormat(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        Pdlog.m3273d(TAG, "checkEuropeanPlayFormat chars : " + chars);
        return Pattern.compile("([a-zA-Z]?([1-9]\\d?|100))|(([1-9]\\d?|100)[a-zA-Z]?)").matcher(chars).matches();
    }

    public final VoiceItem[] toEuropeanPlayFormat(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        if (!checkEuropeanPlayFormat(chars)) {
            return new VoiceItem[0];
        }
        Pdlog.m3273d(TAG, "toEuropeanPlayFormat chars : " + chars);
        ArrayList arrayList = new ArrayList();
        for (String str : splitDigitsLetter(chars)) {
            Pdlog.m3273d(TAG, "toEuropeanPlayFormat charList it : " + str);
            String str2 = str;
            if (TextUtils.isDigitsOnly(str2)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < str2.length(); i++) {
                    sb.append(VoiceUtilsKt.transformNumber(str2.charAt(i)));
                }
                arrayList.add(VoiceItem.valueOf("voice30_" + str));
            } else if (str != null) {
                char[] charArray = str.toCharArray();
                Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
                char c = charArray[0];
                arrayList.add(VoiceItem.valueOf("voice31_" + ((c < 'a' ? c - 'A' : c - 'a') + 1)));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        Object[] array = arrayList.toArray(new VoiceItem[0]);
        if (array != null) {
            return (VoiceItem[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final List<String> splitDigitsLetter(String chars) {
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        Matcher matcher = Pattern.compile("[a-zA-Z]+|\\d+").matcher(chars);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            String group = matcher.group();
            Pdlog.m3273d(TAG, "splitDigitsLetter group: " + group);
            Intrinsics.checkExpressionValueIsNotNull(group, "group");
            arrayList.add(group);
        }
        return arrayList;
    }
}
