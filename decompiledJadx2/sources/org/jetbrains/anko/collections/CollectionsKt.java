package org.jetbrains.anko.collections;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Collections.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a-\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a3\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u001a3\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u001a1\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u000fH\u0086\b\u001a1\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u000f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\fH\u0086\b¨\u0006\u0011"}, m3961d2 = {"forEachByIndex", "", ExifInterface.GPS_DIRECTION_TRUE, "", C3898x.f4339h, "Lkotlin/Function1;", "forEachReversedByIndex", "forEachReversedWithIndex", "Lkotlin/Function2;", "", "forEachWithIndex", "toAndroidPair", "Landroid/util/Pair;", "F", ExifInterface.LATITUDE_SOUTH, "Lkotlin/Pair;", "toKotlinPair", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class CollectionsKt {
    public static final <T> void forEachByIndex(List<? extends T> receiver, Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int size = receiver.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            f.invoke(receiver.get(i));
            if (i == size) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final <T> void forEachWithIndex(List<? extends T> receiver, Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int size = receiver.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            f.invoke(Integer.valueOf(i), receiver.get(i));
            if (i == size) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final <T> void forEachReversedByIndex(List<? extends T> receiver, Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int size = receiver.size() - 1; size >= 0; size--) {
            f.invoke(receiver.get(size));
        }
    }

    public static final <T> void forEachReversedWithIndex(List<? extends T> receiver, Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int size = receiver.size() - 1; size >= 0; size--) {
            f.invoke(Integer.valueOf(size), receiver.get(size));
        }
    }

    public static final <F, S> Pair<F, S> toKotlinPair(android.util.Pair<F, S> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return TuplesKt.m3968to(receiver.first, receiver.second);
    }

    public static final <F, S> android.util.Pair<F, S> toAndroidPair(Pair<? extends F, ? extends S> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new android.util.Pair<>(receiver.getFirst(), receiver.getSecond());
    }
}
