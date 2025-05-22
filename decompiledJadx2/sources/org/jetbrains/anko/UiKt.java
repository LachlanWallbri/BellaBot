package org.jetbrains.anko;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Ui.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0014\b\b\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0086\b¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"applyRecursively", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", C3898x.f4339h, "Lkotlin/Function1;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class UiKt {
    public static final <T extends View> T applyRecursively(T receiver, Function1<? super View, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        AnkoInternals.INSTANCE.applyRecursively(receiver, f);
        return receiver;
    }
}
