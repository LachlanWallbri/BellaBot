package org.jetbrains.anko;

import androidx.exifinterface.media.ExifInterface;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Async.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"Lorg/jetbrains/anko/AnkoAsyncContext;", ExifInterface.GPS_DIRECTION_TRUE, "", "weakRef", "Ljava/lang/ref/WeakReference;", "(Ljava/lang/ref/WeakReference;)V", "getWeakRef", "()Ljava/lang/ref/WeakReference;", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AnkoAsyncContext<T> {
    private final WeakReference<T> weakRef;

    public AnkoAsyncContext(WeakReference<T> weakRef) {
        Intrinsics.checkParameterIsNotNull(weakRef, "weakRef");
        this.weakRef = weakRef;
    }

    public final WeakReference<T> getWeakRef() {
        return this.weakRef;
    }
}
