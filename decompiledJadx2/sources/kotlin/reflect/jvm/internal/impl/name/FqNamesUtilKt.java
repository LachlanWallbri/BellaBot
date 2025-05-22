package kotlin.reflect.jvm.internal.impl.name;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: FqNamesUtil.kt */
/* loaded from: classes2.dex */
public final class FqNamesUtilKt {

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[State.BEGINNING.ordinal()] = 1;
            $EnumSwitchMapping$0[State.AFTER_DOT.ordinal()] = 2;
            $EnumSwitchMapping$0[State.MIDDLE.ordinal()] = 3;
        }
    }

    public static final boolean isSubpackageOf(FqName isSubpackageOf, FqName packageName) {
        Intrinsics.checkParameterIsNotNull(isSubpackageOf, "$this$isSubpackageOf");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        if (Intrinsics.areEqual(isSubpackageOf, packageName) || packageName.isRoot()) {
            return true;
        }
        String asString = isSubpackageOf.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "this.asString()");
        String asString2 = packageName.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "packageName.asString()");
        return isSubpackageOf(asString, asString2);
    }

    private static final boolean isSubpackageOf(String str, String str2) {
        return StringsKt.startsWith$default(str, str2, false, 2, (Object) null) && str.charAt(str2.length()) == '.';
    }

    public static final FqName tail(FqName tail, FqName prefix) {
        Intrinsics.checkParameterIsNotNull(tail, "$this$tail");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!isSubpackageOf(tail, prefix) || prefix.isRoot()) {
            return tail;
        }
        if (Intrinsics.areEqual(tail, prefix)) {
            FqName fqName = FqName.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(fqName, "FqName.ROOT");
            return fqName;
        }
        String asString = tail.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "asString()");
        int length = prefix.asString().length() + 1;
        if (asString == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = asString.substring(length);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return new FqName(substring);
    }

    public static final boolean isValidJavaFqName(String str) {
        if (str == null) {
            return false;
        }
        State state = State.BEGINNING;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i2 == 1 || i2 == 2) {
                if (!Character.isJavaIdentifierPart(charAt)) {
                    return false;
                }
                state = State.MIDDLE;
            } else if (i2 != 3) {
                continue;
            } else if (charAt == '.') {
                state = State.AFTER_DOT;
            } else if (!Character.isJavaIdentifierPart(charAt)) {
                return false;
            }
        }
        return state != State.AFTER_DOT;
    }
}
