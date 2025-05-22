package com.pudutech.mirsdk;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: FunctionScope.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/FunctionScope;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "ListenerWorker", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getListenerWorker", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "MoveActionWorker", "getMoveActionWorker", "SDKStateWorker", "getSDKStateWorker", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FunctionScope implements CoroutineScope {
    public static final FunctionScope INSTANCE = new FunctionScope();
    private static final CoroutineContext coroutineContext = Dispatchers.getDefault();
    private static final ExecutorCoroutineDispatcher MoveActionWorker = ThreadPoolDispatcherKt.newSingleThreadContext("MoveActionWorker");
    private static final ExecutorCoroutineDispatcher SDKStateWorker = ThreadPoolDispatcherKt.newSingleThreadContext("SDKStateWorker");
    private static final ExecutorCoroutineDispatcher ListenerWorker = ThreadPoolDispatcherKt.newSingleThreadContext("ListenerWorker");

    private FunctionScope() {
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return coroutineContext;
    }

    public final ExecutorCoroutineDispatcher getMoveActionWorker() {
        return MoveActionWorker;
    }

    public final ExecutorCoroutineDispatcher getSDKStateWorker() {
        return SDKStateWorker;
    }

    public final ExecutorCoroutineDispatcher getListenerWorker() {
        return ListenerWorker;
    }
}
