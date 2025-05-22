package com.pudutech.mirsdk.mircore.localization;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Localization.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/mircore/localization/Localization$LocalizationScope$1", "Lkotlinx/coroutines/CoroutineScope;", "broadcastWorker", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getBroadcastWorker", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "coroutineContext", "getCoroutineContext", "MirLocalization_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Localization$LocalizationScope$1 implements CoroutineScope {
    private final ExecutorCoroutineDispatcher coroutineContext = ThreadPoolDispatcherKt.newSingleThreadContext("UpdateOdom");
    private final ExecutorCoroutineDispatcher broadcastWorker = ThreadPoolDispatcherKt.newSingleThreadContext("BroadPose");

    @Override // kotlinx.coroutines.CoroutineScope
    public ExecutorCoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }

    public final ExecutorCoroutineDispatcher getBroadcastWorker() {
        return this.broadcastWorker;
    }
}
