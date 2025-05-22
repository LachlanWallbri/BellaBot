package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: numbers.kt */
/* loaded from: classes2.dex */
public final class NumbersKt {
    public static final NumberWithRadix extractRadix(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (StringsKt.startsWith$default(value, "0x", false, 2, (Object) null) || StringsKt.startsWith$default(value, "0X", false, 2, (Object) null)) {
            String substring = value.substring(2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            return new NumberWithRadix(substring, 16);
        }
        if (StringsKt.startsWith$default(value, "0b", false, 2, (Object) null) || StringsKt.startsWith$default(value, "0B", false, 2, (Object) null)) {
            String substring2 = value.substring(2);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            return new NumberWithRadix(substring2, 2);
        }
        return new NumberWithRadix(value, 10);
    }
}
