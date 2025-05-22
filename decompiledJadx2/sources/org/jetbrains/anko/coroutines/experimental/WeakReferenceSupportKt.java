package org.jetbrains.anko.coroutines.experimental;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: weakReferenceSupport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"asReference", "Lorg/jetbrains/anko/coroutines/experimental/Ref;", ExifInterface.GPS_DIRECTION_TRUE, "", "(Ljava/lang/Object;)Lorg/jetbrains/anko/coroutines/experimental/Ref;", "anko-coroutines_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class WeakReferenceSupportKt {
    public static final <T> Ref<T> asReference(T receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new Ref<>(receiver$0);
    }
}
