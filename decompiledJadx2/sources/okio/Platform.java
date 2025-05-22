package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: -Platform.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a\f\u0010\t\u001a\u00020\u0003*\u00020\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\u0003H\u0000*\n\u0010\f\"\u00020\r2\u00020\r*\n\u0010\u000e\"\u00020\u000f2\u00020\u000f*\n\u0010\u0010\"\u00020\u00112\u00020\u0011*\n\u0010\u0012\"\u00020\u00132\u00020\u0013*\n\u0010\u0014\"\u00020\u00152\u00020\u0015¨\u0006\u0016"}, m3961d2 = {"arraycopy", "", "src", "", "srcPos", "", "dest", "destPos", Name.LENGTH, "asUtf8ToByteArray", "", "toUtf8String", "ArrayIndexOutOfBoundsException", "Ljava/lang/ArrayIndexOutOfBoundsException;", "JvmField", "Lkotlin/jvm/JvmField;", "JvmName", "Lkotlin/jvm/JvmName;", "JvmOverloads", "Lkotlin/jvm/JvmOverloads;", "JvmStatic", "Lkotlin/jvm/JvmStatic;", "jvm"}, m3962k = 2, m3963mv = {1, 1, 11})
/* renamed from: okio.-Platform, reason: invalid class name */
/* loaded from: classes2.dex */
public final class Platform {
    public static final void arraycopy(byte[] src, int i, byte[] dest, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        System.arraycopy(src, i, dest, i2, i3);
    }

    public static final String toUtf8String(byte[] receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new String(receiver, Charsets.UTF_8);
    }

    public static final byte[] asUtf8ToByteArray(String receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        byte[] bytes = receiver.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }
}
