package com.pudutech.schedulerlib.connection;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: MulticastConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, m3961d2 = {"com/pudutech/schedulerlib/connection/MulticastConnection$MulticastScope$1", "Lkotlinx/coroutines/CoroutineScope;", "KeepContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getKeepContext", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "RecvContext", "getRecvContext", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MulticastConnection$MulticastScope$1 implements CoroutineScope {
    private final CoroutineContext coroutineContext = Dispatchers.getDefault();
    private final ExecutorCoroutineDispatcher RecvContext = ThreadPoolDispatcherKt.newSingleThreadContext("MulticastRecv");
    private final ExecutorCoroutineDispatcher KeepContext = ThreadPoolDispatcherKt.newSingleThreadContext("MulticastKeep");

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final ExecutorCoroutineDispatcher getRecvContext() {
        return this.RecvContext;
    }

    public final ExecutorCoroutineDispatcher getKeepContext() {
        return this.KeepContext;
    }
}
