package kotlin.coroutines;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: CoroutineContext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001:\u0002\u0011\u0012J5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00030\u0006H&¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH¦\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u00002\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH&J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0096\u0002¨\u0006\u0013"}, m3961d2 = {"Lkotlin/coroutines/CoroutineContext;", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", TmpConstant.PROPERTY_IDENTIFIER_GET, ExifInterface.LONGITUDE_EAST, TransferTable.COLUMN_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", SpeechConstant.MODE_PLUS, "context", "Element", "Key", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface CoroutineContext {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: CoroutineContext.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003¨\u0006\u0004"}, m3961d2 = {"Lkotlin/coroutines/CoroutineContext$Key;", ExifInterface.LONGITUDE_EAST, "Lkotlin/coroutines/CoroutineContext$Element;", "", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface Key<E extends Element> {
    }

    <R> R fold(R initial, Function2<? super R, ? super Element, ? extends R> operation);

    <E extends Element> E get(Key<E> key);

    CoroutineContext minusKey(Key<?> key);

    CoroutineContext plus(CoroutineContext context);

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: CoroutineContext.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static CoroutineContext plus(CoroutineContext coroutineContext, CoroutineContext context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return context == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) context.fold(coroutineContext, new Function2<CoroutineContext, Element, CoroutineContext>() { // from class: kotlin.coroutines.CoroutineContext$plus$1
                @Override // kotlin.jvm.functions.Function2
                public final CoroutineContext invoke(CoroutineContext acc, CoroutineContext.Element element) {
                    CombinedContext combinedContext;
                    Intrinsics.checkParameterIsNotNull(acc, "acc");
                    Intrinsics.checkParameterIsNotNull(element, "element");
                    CoroutineContext minusKey = acc.minusKey(element.getKey());
                    if (minusKey == EmptyCoroutineContext.INSTANCE) {
                        return element;
                    }
                    ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) minusKey.get(ContinuationInterceptor.Key);
                    if (continuationInterceptor == null) {
                        combinedContext = new CombinedContext(minusKey, element);
                    } else {
                        CoroutineContext minusKey2 = minusKey.minusKey(ContinuationInterceptor.Key);
                        combinedContext = minusKey2 == EmptyCoroutineContext.INSTANCE ? new CombinedContext(element, continuationInterceptor) : new CombinedContext(new CombinedContext(minusKey2, element), continuationInterceptor);
                    }
                    return combinedContext;
                }
            });
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    /* compiled from: CoroutineContext.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u0002H\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\r0\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, m3961d2 = {"Lkotlin/coroutines/CoroutineContext$Element;", "Lkotlin/coroutines/CoroutineContext;", TransferTable.COLUMN_KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", TmpConstant.PROPERTY_IDENTIFIER_GET, ExifInterface.LONGITUDE_EAST, "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusKey", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface Element extends CoroutineContext {
        @Override // kotlin.coroutines.CoroutineContext
        <R> R fold(R initial, Function2<? super R, ? super Element, ? extends R> operation);

        @Override // kotlin.coroutines.CoroutineContext
        <E extends Element> E get(Key<E> key);

        Key<?> getKey();

        @Override // kotlin.coroutines.CoroutineContext
        CoroutineContext minusKey(Key<?> key);

        /* JADX WARN: Classes with same name are omitted:
          classes.dex
          classes7.dex
          classes8.dex
         */
        /* compiled from: CoroutineContext.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class DefaultImpls {
            public static CoroutineContext plus(Element element, CoroutineContext context) {
                Intrinsics.checkParameterIsNotNull(context, "context");
                return DefaultImpls.plus(element, context);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends Element> E get(Element element, Key<E> key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                if (!Intrinsics.areEqual(element.getKey(), key)) {
                    return null;
                }
                if (element != 0) {
                    return element;
                }
                throw new TypeCastException("null cannot be cast to non-null type E");
            }

            public static <R> R fold(Element element, R r, Function2<? super R, ? super Element, ? extends R> operation) {
                Intrinsics.checkParameterIsNotNull(operation, "operation");
                return operation.invoke(r, element);
            }

            public static CoroutineContext minusKey(Element element, Key<?> key) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                boolean areEqual = Intrinsics.areEqual(element.getKey(), key);
                Object obj = element;
                if (areEqual) {
                    obj = EmptyCoroutineContext.INSTANCE;
                }
                return (CoroutineContext) obj;
            }
        }
    }
}
