package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: MutableCollectionsJVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001a!\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0087\b\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000bH\u0007\u001a&\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a \u0010\f\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a3\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00100\u000fH\u0087\b\u001a5\u0010\f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0011\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0012j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0013H\u0087\b\u001a2\u0010\u0014\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0011\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0012j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0013¨\u0006\u0015"}, m3961d2 = {"fill", "", ExifInterface.GPS_DIRECTION_TRUE, "", ES6Iterator.VALUE_PROPERTY, "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "random", "Ljava/util/Random;", "shuffled", "", "", "sort", "", "comparison", "Lkotlin/Function2;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, m3962k = 5, m3963mv = {1, 1, 16}, m3965xi = 1, m3966xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes2.dex */
public class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(comparator) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(comparator)", imports = {}))
    private static final <T> void sort(List<T> list, Comparator<? super T> comparator) {
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(Comparator(comparison)) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(Comparator(comparison))", imports = {}))
    private static final <T> void sort(List<T> list, Function2<? super T, ? super T, Integer> function2) {
        throw new NotImplementedError(null, 1, null);
    }

    public static final <T extends Comparable<? super T>> void sort(List<T> sort) {
        Intrinsics.checkParameterIsNotNull(sort, "$this$sort");
        if (sort.size() > 1) {
            Collections.sort(sort);
        }
    }

    public static final <T> void sortWith(List<T> sortWith, Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(sortWith, "$this$sortWith");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        if (sortWith.size() > 1) {
            Collections.sort(sortWith, comparator);
        }
    }

    private static final <T> void fill(List<T> list, T t) {
        Collections.fill(list, t);
    }

    private static final <T> void shuffle(List<T> list) {
        Collections.shuffle(list);
    }

    private static final <T> void shuffle(List<T> list, Random random) {
        Collections.shuffle(list, random);
    }

    public static final <T> List<T> shuffled(Iterable<? extends T> shuffled) {
        Intrinsics.checkParameterIsNotNull(shuffled, "$this$shuffled");
        List<T> mutableList = CollectionsKt.toMutableList(shuffled);
        Collections.shuffle(mutableList);
        return mutableList;
    }

    public static final <T> List<T> shuffled(Iterable<? extends T> shuffled, Random random) {
        Intrinsics.checkParameterIsNotNull(shuffled, "$this$shuffled");
        Intrinsics.checkParameterIsNotNull(random, "random");
        List<T> mutableList = CollectionsKt.toMutableList(shuffled);
        Collections.shuffle(mutableList, random);
        return mutableList;
    }
}
