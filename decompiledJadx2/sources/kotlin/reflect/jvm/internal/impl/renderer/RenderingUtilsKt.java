package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: RenderingUtils.kt */
/* loaded from: classes2.dex */
public final class RenderingUtilsKt {
    public static final String render(Name render) {
        Intrinsics.checkParameterIsNotNull(render, "$this$render");
        if (!shouldBeEscaped(render)) {
            String asString = render.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "asString()");
            return asString;
        }
        StringBuilder sb = new StringBuilder();
        String asString2 = render.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "asString()");
        sb.append(String.valueOf('`') + asString2);
        sb.append('`');
        return sb.toString();
    }

    private static final boolean shouldBeEscaped(Name name) {
        boolean z;
        if (name.isSpecial()) {
            return false;
        }
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "asString()");
        if (!KeywordStringsGenerated.KEYWORDS.contains(asString)) {
            String str = asString;
            int i = 0;
            while (true) {
                if (i >= str.length()) {
                    z = false;
                    break;
                }
                char charAt = str.charAt(i);
                if ((Character.isLetterOrDigit(charAt) || charAt == '_') ? false : true) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static final String render(FqNameUnsafe render) {
        Intrinsics.checkParameterIsNotNull(render, "$this$render");
        List<Name> pathSegments = render.pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "pathSegments()");
        return renderFqName(pathSegments);
    }

    public static final String renderFqName(List<Name> pathSegments) {
        Intrinsics.checkParameterIsNotNull(pathSegments, "pathSegments");
        StringBuilder sb = new StringBuilder();
        for (Name name : pathSegments) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(render(name));
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
