package androidx.core.util;

import android.util.Size;
import android.util.SizeF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: Size.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\n\u001a\r\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0087\n¨\u0006\u0006"}, m3961d2 = {"component1", "", "Landroid/util/Size;", "", "Landroid/util/SizeF;", "component2", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class SizeKt {
    public static final int component1(Size component1) {
        Intrinsics.checkParameterIsNotNull(component1, "$this$component1");
        return component1.getWidth();
    }

    public static final int component2(Size component2) {
        Intrinsics.checkParameterIsNotNull(component2, "$this$component2");
        return component2.getHeight();
    }

    public static final float component1(SizeF component1) {
        Intrinsics.checkParameterIsNotNull(component1, "$this$component1");
        return component1.getWidth();
    }

    public static final float component2(SizeF component2) {
        Intrinsics.checkParameterIsNotNull(component2, "$this$component2");
        return component2.getHeight();
    }
}
