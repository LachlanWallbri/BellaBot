package kotlin.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: CoroutineContextImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, m3961d2 = {"getPolymorphicElement", ExifInterface.LONGITUDE_EAST, "Lkotlin/coroutines/CoroutineContext$Element;", TransferTable.COLUMN_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Element;Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusPolymorphicKey", "Lkotlin/coroutines/CoroutineContext;", "kotlin-stdlib"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CoroutineContextImplKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <E extends CoroutineContext.Element> E getPolymorphicElement(CoroutineContext.Element getPolymorphicElement, CoroutineContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(getPolymorphicElement, "$this$getPolymorphicElement");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (!abstractCoroutineContextKey.isSubKey$kotlin_stdlib(getPolymorphicElement.getKey())) {
                return null;
            }
            E e = (E) abstractCoroutineContextKey.tryCast$kotlin_stdlib(getPolymorphicElement);
            if (e instanceof CoroutineContext.Element) {
                return e;
            }
            return null;
        }
        if (getPolymorphicElement.getKey() == key) {
            return getPolymorphicElement;
        }
        return null;
    }

    public static final CoroutineContext minusPolymorphicKey(CoroutineContext.Element minusPolymorphicKey, CoroutineContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(minusPolymorphicKey, "$this$minusPolymorphicKey");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            boolean isSubKey$kotlin_stdlib = abstractCoroutineContextKey.isSubKey$kotlin_stdlib(minusPolymorphicKey.getKey());
            Object obj = minusPolymorphicKey;
            if (isSubKey$kotlin_stdlib) {
                CoroutineContext.Element tryCast$kotlin_stdlib = abstractCoroutineContextKey.tryCast$kotlin_stdlib(minusPolymorphicKey);
                obj = minusPolymorphicKey;
                if (tryCast$kotlin_stdlib != null) {
                    obj = EmptyCoroutineContext.INSTANCE;
                }
            }
            return (CoroutineContext) obj;
        }
        CoroutineContext.Key<?> key2 = minusPolymorphicKey.getKey();
        Object obj2 = minusPolymorphicKey;
        if (key2 == key) {
            obj2 = EmptyCoroutineContext.INSTANCE;
        }
        return (CoroutineContext) obj2;
    }
}
