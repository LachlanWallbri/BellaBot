package retrofit2;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KotlinExtensions.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m3961d2 = {"create", ExifInterface.GPS_DIRECTION_TRUE, "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "retrofit"}, m3962k = 2, m3963mv = {1, 1, 11})
/* renamed from: retrofit2.-KotlinExtensions, reason: invalid class name */
/* loaded from: classes2.dex */
public final class KotlinExtensions {
    private static final <T> T create(Retrofit retrofit) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) retrofit.create(Object.class);
    }
}
