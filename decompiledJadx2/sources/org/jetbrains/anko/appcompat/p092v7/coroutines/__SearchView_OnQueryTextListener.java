package org.jetbrains.anko.appcompat.p092v7.coroutines;

import androidx.appcompat.widget.SearchView;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JI\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\n2/\u0010\u0014\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0011\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u0016JI\u0010\u0017\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\n2/\u0010\u0014\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0016R>\u0010\u0005\u001a-\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R>\u0010\u000f\u001a-\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m3961d2 = {"Lorg/jetbrains/anko/appcompat/v7/coroutines/__SearchView_OnQueryTextListener;", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onQueryTextChange", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "_onQueryTextChange_returnValue", "_onQueryTextSubmit", "_onQueryTextSubmit_returnValue", "onQueryTextChange", "", "returnValue", "listener", "(ZLkotlin/jvm/functions/Function3;)V", "newText", "onQueryTextSubmit", "query", "anko-appcompat-v7-coroutines_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __SearchView_OnQueryTextListener implements SearchView.OnQueryTextListener {
    private Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Boolean>, ? extends Object> _onQueryTextChange;
    private boolean _onQueryTextChange_returnValue;
    private Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Boolean>, ? extends Object> _onQueryTextSubmit;
    private boolean _onQueryTextSubmit_returnValue;
    private final CoroutineContext context;

    public __SearchView_OnQueryTextListener(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String query) {
        boolean z = this._onQueryTextSubmit_returnValue;
        Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Boolean>, ? extends Object> function3 = this._onQueryTextSubmit;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __SearchView_OnQueryTextListener$onQueryTextSubmit$1(function3, query, null), 2, null);
        }
        return z;
    }

    public static /* synthetic */ void onQueryTextSubmit$default(__SearchView_OnQueryTextListener __searchview_onquerytextlistener, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        __searchview_onquerytextlistener.onQueryTextSubmit(z, function3);
    }

    public final void onQueryTextSubmit(boolean returnValue, Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Boolean>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onQueryTextSubmit = listener;
        this._onQueryTextSubmit_returnValue = returnValue;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String newText) {
        boolean z = this._onQueryTextChange_returnValue;
        Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Boolean>, ? extends Object> function3 = this._onQueryTextChange;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __SearchView_OnQueryTextListener$onQueryTextChange$1(function3, newText, null), 2, null);
        }
        return z;
    }

    public static /* synthetic */ void onQueryTextChange$default(__SearchView_OnQueryTextListener __searchview_onquerytextlistener, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        __searchview_onquerytextlistener.onQueryTextChange(z, function3);
    }

    public final void onQueryTextChange(boolean returnValue, Function3<? super CoroutineScope, ? super String, ? super Continuation<? super Boolean>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onQueryTextChange = listener;
        this._onQueryTextChange_returnValue = returnValue;
    }
}
