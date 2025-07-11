package kotlin.text;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: StringBuilderJVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a-\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a/\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b\u001a\u001d\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\t\u001a\u00060\nj\u0002`\u000b*\u00060\nj\u0002`\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u0012\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\rH\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000eH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0012H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0013H\u0087\b\u001a\u001d\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0014H\u0087\b\u001a\u001f\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0015H\u0087\b\u001a%\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001a\u0014\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001a\u001d\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u0006H\u0087\b\u001a%\u0010\u0019\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a5\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a7\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u001a!\u0010\u001b\u001a\u00020\u001c*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\fH\u0087\n\u001a-\u0010\u001d\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\b\u001a7\u0010\u001e\u001a\u00020\u001c*\u00060\u0001j\u0002`\u00022\u0006\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\b¨\u0006!"}, m3961d2 = {"appendRange", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", ES6Iterator.VALUE_PROPERTY, "", "startIndex", "", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "clear", "deleteAt", "index", "deleteRange", "insertRange", TmpConstant.PROPERTY_IDENTIFIER_SET, "", "setRange", "toCharArray", "destination", "destinationOffset", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/text/StringsKt")
/* loaded from: classes2.dex */
public class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    public static final StringBuilder clear(StringBuilder clear) {
        Intrinsics.checkParameterIsNotNull(clear, "$this$clear");
        clear.setLength(0);
        return clear;
    }

    private static final void set(StringBuilder set, int i, char c) {
        Intrinsics.checkParameterIsNotNull(set, "$this$set");
        set.setCharAt(i, c);
    }

    private static final StringBuilder setRange(StringBuilder sb, int i, int i2, String str) {
        StringBuilder replace = sb.replace(i, i2, str);
        Intrinsics.checkExpressionValueIsNotNull(replace, "this.replace(startIndex, endIndex, value)");
        return replace;
    }

    private static final StringBuilder deleteAt(StringBuilder sb, int i) {
        StringBuilder deleteCharAt = sb.deleteCharAt(i);
        Intrinsics.checkExpressionValueIsNotNull(deleteCharAt, "this.deleteCharAt(index)");
        return deleteCharAt;
    }

    private static final StringBuilder deleteRange(StringBuilder sb, int i, int i2) {
        StringBuilder delete = sb.delete(i, i2);
        Intrinsics.checkExpressionValueIsNotNull(delete, "this.delete(startIndex, endIndex)");
        return delete;
    }

    static /* synthetic */ void toCharArray$default(StringBuilder sb, char[] cArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sb.length();
        }
        sb.getChars(i2, i3, cArr, i);
    }

    private static final void toCharArray(StringBuilder sb, char[] cArr, int i, int i2, int i3) {
        sb.getChars(i2, i3, cArr, i);
    }

    private static final StringBuilder appendRange(StringBuilder sb, char[] cArr, int i, int i2) {
        sb.append(cArr, i, i2 - i);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, start…x, endIndex - startIndex)");
        return sb;
    }

    private static final StringBuilder appendRange(StringBuilder sb, CharSequence charSequence, int i, int i2) {
        sb.append(charSequence, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
        return sb;
    }

    private static final StringBuilder insertRange(StringBuilder sb, int i, char[] cArr, int i2, int i3) {
        StringBuilder insert = sb.insert(i, cArr, i2, i3 - i2);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value…x, endIndex - startIndex)");
        return insert;
    }

    private static final StringBuilder insertRange(StringBuilder sb, int i, CharSequence charSequence, int i2, int i3) {
        StringBuilder insert = sb.insert(i, charSequence, i2, i3);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value, startIndex, endIndex)");
        return insert;
    }

    public static final Appendable appendln(Appendable appendln) {
        Intrinsics.checkParameterIsNotNull(appendln, "$this$appendln");
        Appendable append = appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    private static final Appendable appendln(Appendable appendable, CharSequence charSequence) {
        Appendable append = appendable.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return StringsKt.appendln(append);
    }

    private static final Appendable appendln(Appendable appendable, char c) {
        Appendable append = appendable.append(c);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return StringsKt.appendln(append);
    }

    public static final StringBuilder appendln(StringBuilder appendln) {
        Intrinsics.checkParameterIsNotNull(appendln, "$this$appendln");
        appendln.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(appendln, "append(SystemProperties.LINE_SEPARATOR)");
        return appendln;
    }

    private static final StringBuilder appendln(StringBuilder sb, StringBuffer stringBuffer) {
        sb.append(stringBuffer);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, CharSequence charSequence) {
        sb.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, String str) {
        sb.append(str);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, Object obj) {
        sb.append(obj);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, StringBuilder sb2) {
        sb.append((CharSequence) sb2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, char[] cArr) {
        sb.append(cArr);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, char c) {
        sb.append(c);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, boolean z) {
        sb.append(z);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, int i) {
        sb.append(i);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, short s) {
        sb.append((int) s);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value.toInt())");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, byte b) {
        sb.append((int) b);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value.toInt())");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, long j) {
        sb.append(j);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, float f) {
        sb.append(f);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }

    private static final StringBuilder appendln(StringBuilder sb, double d) {
        sb.append(d);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return StringsKt.appendln(sb);
    }
}
