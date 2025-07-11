package org.jetbrains.anko.sdk27.coroutines;

import android.widget.SlidingDrawer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\tH\u0016J7\u0010\u000e\u001a\u00020\t2'\u0010\u000f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006¢\u0006\u0002\b\u000bø\u0001\u0000¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\tH\u0016J7\u0010\u0011\u001a\u00020\t2'\u0010\u000f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006¢\u0006\u0002\b\u000bø\u0001\u0000¢\u0006\u0002\u0010\u0010R6\u0010\u0005\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0006¢\u0006\u0002\b\u000bX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\fR6\u0010\r\u001a%\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0006¢\u0006\u0002\b\u000bX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m3961d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__SlidingDrawer_OnDrawerScrollListener;", "Landroid/widget/SlidingDrawer$OnDrawerScrollListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onScrollEnded", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function2;", "_onScrollStarted", "onScrollEnded", "listener", "(Lkotlin/jvm/functions/Function2;)V", "onScrollStarted", "anko-sdk27-coroutines_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __SlidingDrawer_OnDrawerScrollListener implements SlidingDrawer.OnDrawerScrollListener {
    private Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> _onScrollEnded;
    private Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> _onScrollStarted;
    private final CoroutineContext context;

    public __SlidingDrawer_OnDrawerScrollListener(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // android.widget.SlidingDrawer.OnDrawerScrollListener
    public void onScrollStarted() {
        Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2 = this._onScrollStarted;
        if (function2 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, function2, 2, null);
        }
    }

    public final void onScrollStarted(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onScrollStarted = listener;
    }

    @Override // android.widget.SlidingDrawer.OnDrawerScrollListener
    public void onScrollEnded() {
        Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2 = this._onScrollEnded;
        if (function2 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, function2, 2, null);
        }
    }

    public final void onScrollEnded(Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onScrollEnded = listener;
    }
}
