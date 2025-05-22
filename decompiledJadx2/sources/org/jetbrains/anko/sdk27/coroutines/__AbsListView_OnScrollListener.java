package org.jetbrains.anko.sdk27.coroutines;

import android.widget.AbsListView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0016JQ\u0010\u0012\u001a\u00020\u000b2A\u0010\u0017\u001a=\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\tH\u0016JE\u0010\u0019\u001a\u00020\u000b25\u0010\u0017\u001a1\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0010¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u001bRP\u0010\u0005\u001a?\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0006¢\u0006\u0002\b\rX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u000eRD\u0010\u000f\u001a3\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0010¢\u0006\u0002\b\rX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, m3961d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__AbsListView_OnScrollListener;", "Landroid/widget/AbsListView$OnScrollListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onScroll", "Lkotlin/Function6;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/widget/AbsListView;", "", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function6;", "_onScrollStateChanged", "Lkotlin/Function4;", "Lkotlin/jvm/functions/Function4;", "onScroll", "view", "firstVisibleItem", "visibleItemCount", "totalItemCount", "listener", "(Lkotlin/jvm/functions/Function6;)V", "onScrollStateChanged", "scrollState", "(Lkotlin/jvm/functions/Function4;)V", "anko-sdk27-coroutines_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __AbsListView_OnScrollListener implements AbsListView.OnScrollListener {
    private Function6<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> _onScroll;
    private Function4<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> _onScrollStateChanged;
    private final CoroutineContext context;

    public __AbsListView_OnScrollListener(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Function4<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function4 = this._onScrollStateChanged;
        if (function4 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __AbsListView_OnScrollListener$onScrollStateChanged$1(function4, view, scrollState, null), 2, null);
        }
    }

    public final void onScrollStateChanged(Function4<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onScrollStateChanged = listener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Function6<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function6 = this._onScroll;
        if (function6 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __AbsListView_OnScrollListener$onScroll$1(function6, view, firstVisibleItem, visibleItemCount, totalItemCount, null), 2, null);
        }
    }

    public final void onScroll(Function6<? super CoroutineScope, ? super AbsListView, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onScroll = listener;
    }
}
