package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Charsets.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class Charsets {
    public static final Charsets INSTANCE = new Charsets();
    public static final Charset ISO_8859_1;
    public static final Charset US_ASCII;
    public static final Charset UTF_16;
    public static final Charset UTF_16BE;
    public static final Charset UTF_16LE;
    public static final Charset UTF_8;
    private static Charset utf_32;
    private static Charset utf_32be;
    private static Charset utf_32le;

    static {
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-8\")");
        UTF_8 = forName;
        Charset forName2 = Charset.forName("UTF-16");
        Intrinsics.checkExpressionValueIsNotNull(forName2, "Charset.forName(\"UTF-16\")");
        UTF_16 = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        Intrinsics.checkExpressionValueIsNotNull(forName3, "Charset.forName(\"UTF-16BE\")");
        UTF_16BE = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        Intrinsics.checkExpressionValueIsNotNull(forName4, "Charset.forName(\"UTF-16LE\")");
        UTF_16LE = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        Intrinsics.checkExpressionValueIsNotNull(forName5, "Charset.forName(\"US-ASCII\")");
        US_ASCII = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        Intrinsics.checkExpressionValueIsNotNull(forName6, "Charset.forName(\"ISO-8859-1\")");
        ISO_8859_1 = forName6;
    }

    private Charsets() {
    }

    public final Charset UTF32() {
        Charset charset = utf_32;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-32\")");
        utf_32 = forName;
        return forName;
    }

    public final Charset UTF32_LE() {
        Charset charset = utf_32le;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32LE");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-32LE\")");
        utf_32le = forName;
        return forName;
    }

    public final Charset UTF32_BE() {
        Charset charset = utf_32be;
        if (charset != null) {
            return charset;
        }
        Charset forName = Charset.forName("UTF-32BE");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-32BE\")");
        utf_32be = forName;
        return forName;
    }
}
