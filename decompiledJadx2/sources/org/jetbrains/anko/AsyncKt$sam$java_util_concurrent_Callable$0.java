package org.jetbrains.anko;

import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Async.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
final class AsyncKt$sam$java_util_concurrent_Callable$0 implements Callable {
    private final /* synthetic */ Function0 function;

    AsyncKt$sam$java_util_concurrent_Callable$0(Function0 function0) {
        this.function = function0;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.function.invoke();
    }
}
