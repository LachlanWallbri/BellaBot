package androidx.core.util;

import android.util.Half;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: Half.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, m3961d2 = {"toHalf", "Landroid/util/Half;", "", "", "", "", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class HalfKt {
    public static final Half toHalf(short s) {
        Half valueOf = Half.valueOf(s);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "Half.valueOf(this)");
        return valueOf;
    }

    public static final Half toHalf(float f) {
        Half valueOf = Half.valueOf(f);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "Half.valueOf(this)");
        return valueOf;
    }

    public static final Half toHalf(String toHalf) {
        Intrinsics.checkParameterIsNotNull(toHalf, "$this$toHalf");
        Half valueOf = Half.valueOf(toHalf);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "Half.valueOf(this)");
        return valueOf;
    }

    public static final Half toHalf(double d) {
        Half valueOf = Half.valueOf((float) d);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "Half.valueOf(this)");
        return valueOf;
    }
}
