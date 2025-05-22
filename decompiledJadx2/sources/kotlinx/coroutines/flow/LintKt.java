package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.CoroutineContext;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Lint.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a&\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, m3961d2 = {"conflate", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/StateFlow;", "distinctUntilChanged", "flowOn", "context", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-core"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LintKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying flowOn operator to StateFlow has no effect. See StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final <T> Flow<T> flowOn(StateFlow<? extends T> stateFlow, CoroutineContext coroutineContext) {
        FlowKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying conflate operator to StateFlow has no effect. See StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final <T> Flow<T> conflate(StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Applying distinctUntilChanged operator to StateFlow has no effect. See StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}))
    public static final <T> Flow<T> distinctUntilChanged(StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw null;
    }
}
