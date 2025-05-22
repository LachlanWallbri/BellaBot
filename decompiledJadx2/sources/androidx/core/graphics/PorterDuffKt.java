package androidx.core.graphics;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PorterDuff.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\r\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0086\b¨\u0006\u0007"}, m3961d2 = {"toColorFilter", "Landroid/graphics/PorterDuffColorFilter;", "Landroid/graphics/PorterDuff$Mode;", TypedValues.Custom.S_COLOR, "", "toXfermode", "Landroid/graphics/PorterDuffXfermode;", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class PorterDuffKt {
    public static final PorterDuffXfermode toXfermode(PorterDuff.Mode toXfermode) {
        Intrinsics.checkParameterIsNotNull(toXfermode, "$this$toXfermode");
        return new PorterDuffXfermode(toXfermode);
    }

    public static final PorterDuffColorFilter toColorFilter(PorterDuff.Mode toColorFilter, int i) {
        Intrinsics.checkParameterIsNotNull(toColorFilter, "$this$toColorFilter");
        return new PorterDuffColorFilter(i, toColorFilter);
    }
}
