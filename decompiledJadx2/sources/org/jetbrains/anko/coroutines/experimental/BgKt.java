package org.jetbrains.anko.coroutines.experimental;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: bg.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\u0004\b\u0000\u0010\n2\u000e\b\u0004\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH\u0087\b\"$\u0010\u0000\u001a\u00020\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\r"}, m3961d2 = {"POOL", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "POOL$annotations", "()V", "getPOOL", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "setPOOL", "(Lkotlinx/coroutines/ExecutorCoroutineDispatcher;)V", "bg", "Lkotlinx/coroutines/Deferred;", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "anko-coroutines_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class BgKt {
    private static ExecutorCoroutineDispatcher POOL = ThreadPoolDispatcherKt.newFixedThreadPoolContext(Runtime.getRuntime().availableProcessors() * 2, "bg");

    @Deprecated(message = "Use the default pool")
    public static /* synthetic */ void POOL$annotations() {
    }

    public static final ExecutorCoroutineDispatcher getPOOL() {
        return POOL;
    }

    public static final void setPOOL(ExecutorCoroutineDispatcher executorCoroutineDispatcher) {
        Intrinsics.checkParameterIsNotNull(executorCoroutineDispatcher, "<set-?>");
        POOL = executorCoroutineDispatcher;
    }

    @Deprecated(message = "Use the default pool", replaceWith = @ReplaceWith(expression = "async(block)", imports = {"kotlinx.coroutines.async"}))
    /* renamed from: bg */
    public static final <T> Deferred<T> m4175bg(Function0<? extends T> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        return BuildersKt.async(GlobalScope.INSTANCE, getPOOL(), CoroutineStart.DEFAULT, new BgKt$bg$1(block, null));
    }
}
