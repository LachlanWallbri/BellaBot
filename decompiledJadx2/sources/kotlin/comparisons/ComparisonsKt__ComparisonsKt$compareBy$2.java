package kotlin.comparisons;

import androidx.exifinterface.media.ExifInterface;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Comparisons.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ComparisonsKt__ComparisonsKt$compareBy$2<T> implements Comparator<T> {
    final /* synthetic */ Function1 $selector;

    public ComparisonsKt__ComparisonsKt$compareBy$2(Function1 function1) {
        this.$selector = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        return ComparisonsKt.compareValues((Comparable) this.$selector.invoke(t), (Comparable) this.$selector.invoke(t2));
    }
}
