package kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize;

import java.util.Iterator;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: capitalizeDecapitalize.kt */
/* loaded from: classes2.dex */
public final class CapitalizeDecapitalizeKt {
    public static final String decapitalizeSmartForCompiler(String decapitalizeSmartForCompiler, boolean z) {
        Integer num;
        Intrinsics.checkParameterIsNotNull(decapitalizeSmartForCompiler, "$this$decapitalizeSmartForCompiler");
        String str = decapitalizeSmartForCompiler;
        if ((str.length() == 0) || !isUpperCaseCharAt(decapitalizeSmartForCompiler, 0, z)) {
            return decapitalizeSmartForCompiler;
        }
        if (decapitalizeSmartForCompiler.length() == 1 || !isUpperCaseCharAt(decapitalizeSmartForCompiler, 1, z)) {
            return z ? decapitalizeAsciiOnly(decapitalizeSmartForCompiler) : StringsKt.decapitalize(decapitalizeSmartForCompiler);
        }
        Iterator<Integer> it = StringsKt.getIndices(str).iterator();
        while (true) {
            if (!it.hasNext()) {
                num = null;
                break;
            }
            num = it.next();
            if (!isUpperCaseCharAt(decapitalizeSmartForCompiler, num.intValue(), z)) {
                break;
            }
        }
        Integer num2 = num;
        if (num2 == null) {
            return toLowerCase(decapitalizeSmartForCompiler, z);
        }
        int intValue = num2.intValue() - 1;
        StringBuilder sb = new StringBuilder();
        String substring = decapitalizeSmartForCompiler.substring(0, intValue);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        sb.append(toLowerCase(substring, z));
        String substring2 = decapitalizeSmartForCompiler.substring(intValue);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        return sb.toString();
    }

    private static final boolean isUpperCaseCharAt(String str, int i, boolean z) {
        char charAt = str.charAt(i);
        if (z) {
            return 'A' <= charAt && 'Z' >= charAt;
        }
        return Character.isUpperCase(charAt);
    }

    private static final String toLowerCase(String str, boolean z) {
        if (z) {
            return toLowerCaseAsciiOnly(str);
        }
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = str.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    public static final String capitalizeAsciiOnly(String capitalizeAsciiOnly) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(capitalizeAsciiOnly, "$this$capitalizeAsciiOnly");
        if ((capitalizeAsciiOnly.length() == 0) || 'a' > (charAt = capitalizeAsciiOnly.charAt(0)) || 'z' < charAt) {
            return capitalizeAsciiOnly;
        }
        char upperCase = Character.toUpperCase(charAt);
        String substring = capitalizeAsciiOnly.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(upperCase) + substring;
    }

    public static final String decapitalizeAsciiOnly(String decapitalizeAsciiOnly) {
        char charAt;
        Intrinsics.checkParameterIsNotNull(decapitalizeAsciiOnly, "$this$decapitalizeAsciiOnly");
        if ((decapitalizeAsciiOnly.length() == 0) || 'A' > (charAt = decapitalizeAsciiOnly.charAt(0)) || 'Z' < charAt) {
            return decapitalizeAsciiOnly;
        }
        char lowerCase = Character.toLowerCase(charAt);
        String substring = decapitalizeAsciiOnly.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(lowerCase) + substring;
    }

    public static final String toLowerCaseAsciiOnly(String toLowerCaseAsciiOnly) {
        Intrinsics.checkParameterIsNotNull(toLowerCaseAsciiOnly, "$this$toLowerCaseAsciiOnly");
        StringBuilder sb = new StringBuilder(toLowerCaseAsciiOnly.length());
        int length = toLowerCaseAsciiOnly.length();
        for (int i = 0; i < length; i++) {
            char charAt = toLowerCaseAsciiOnly.charAt(i);
            if ('A' <= charAt && 'Z' >= charAt) {
                charAt = Character.toLowerCase(charAt);
            }
            sb.append(charAt);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "builder.toString()");
        return sb2;
    }
}
