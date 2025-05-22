package org.jetbrains.anko;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AnkoContext.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lorg/jetbrains/anko/AnkoComponent;", ExifInterface.GPS_DIRECTION_TRUE, "", "createView", "Landroid/view/View;", "ui", "Lorg/jetbrains/anko/AnkoContext;", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public interface AnkoComponent<T> {
    View createView(AnkoContext<? extends T> ui);
}
