package androidx.room;

import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* compiled from: CoroutinesRoom.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, m3961d2 = {"queryDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Landroidx/room/RoomDatabase;", "getQueryDispatcher", "(Landroidx/room/RoomDatabase;)Lkotlinx/coroutines/CoroutineDispatcher;", "transactionDispatcher", "getTransactionDispatcher", "room-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class CoroutinesRoomKt {
    public static final CoroutineDispatcher getQueryDispatcher(RoomDatabase queryDispatcher) {
        Intrinsics.checkParameterIsNotNull(queryDispatcher, "$this$queryDispatcher");
        Map<String, Object> backingFieldMap = queryDispatcher.getBackingFieldMap();
        Intrinsics.checkExpressionValueIsNotNull(backingFieldMap, "backingFieldMap");
        Object obj = backingFieldMap.get("QueryDispatcher");
        if (obj == null) {
            Executor queryExecutor = queryDispatcher.getQueryExecutor();
            Intrinsics.checkExpressionValueIsNotNull(queryExecutor, "queryExecutor");
            obj = ExecutorsKt.from(queryExecutor);
            backingFieldMap.put("QueryDispatcher", obj);
        }
        if (obj != null) {
            return (CoroutineDispatcher) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
    }

    public static final CoroutineDispatcher getTransactionDispatcher(RoomDatabase transactionDispatcher) {
        Intrinsics.checkParameterIsNotNull(transactionDispatcher, "$this$transactionDispatcher");
        Map<String, Object> backingFieldMap = transactionDispatcher.getBackingFieldMap();
        Intrinsics.checkExpressionValueIsNotNull(backingFieldMap, "backingFieldMap");
        Object obj = backingFieldMap.get("TransactionDispatcher");
        if (obj == null) {
            Executor transactionExecutor = transactionDispatcher.getTransactionExecutor();
            Intrinsics.checkExpressionValueIsNotNull(transactionExecutor, "transactionExecutor");
            obj = ExecutorsKt.from(transactionExecutor);
            backingFieldMap.put("TransactionDispatcher", obj);
        }
        if (obj != null) {
            return (CoroutineDispatcher) obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
    }
}
