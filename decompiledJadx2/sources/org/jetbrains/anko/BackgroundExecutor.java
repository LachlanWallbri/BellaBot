package org.jetbrains.anko;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Async.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, m3961d2 = {"Lorg/jetbrains/anko/BackgroundExecutor;", "", "()V", "executor", "Ljava/util/concurrent/ExecutorService;", "getExecutor", "()Ljava/util/concurrent/ExecutorService;", "setExecutor", "(Ljava/util/concurrent/ExecutorService;)V", "submit", "Ljava/util/concurrent/Future;", ExifInterface.GPS_DIRECTION_TRUE, "task", "Lkotlin/Function0;", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class BackgroundExecutor {
    public static final BackgroundExecutor INSTANCE = null;
    private static ExecutorService executor;

    static {
        new BackgroundExecutor();
    }

    private BackgroundExecutor() {
        INSTANCE = this;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        Intrinsics.checkExpressionValueIsNotNull(newScheduledThreadPool, "Executors.newScheduledTh…().availableProcessors())");
        executor = newScheduledThreadPool;
    }

    public final ExecutorService getExecutor() {
        return executor;
    }

    public final void setExecutor(ExecutorService executorService) {
        Intrinsics.checkParameterIsNotNull(executorService, "<set-?>");
        executor = executorService;
    }

    public final <T> Future<T> submit(final Function0<? extends T> task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Future<T> submit = executor.submit(task == null ? null : new Callable() { // from class: org.jetbrains.anko.AsyncKt$sam$Callable$892965c7
            /* JADX WARN: Type inference failed for: r0v1, types: [V, java.lang.Object] */
            @Override // java.util.concurrent.Callable
            public final /* synthetic */ V call() {
                return Function0.this.invoke();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(submit, "executor.submit(task)");
        return submit;
    }
}
