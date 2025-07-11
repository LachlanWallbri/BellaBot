package org.jetbrains.anko.sdk27.coroutines;

import android.widget.SearchView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JG\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\n2-\u0010\u0014\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\bH\u0016JG\u0010\u0017\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\n2-\u0010\u0014\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\bH\u0016R<\u0010\u0005\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R<\u0010\u000f\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, m3961d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__SearchView_OnSuggestionListener;", "Landroid/widget/SearchView$OnSuggestionListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onSuggestionClick", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "_onSuggestionClick_returnValue", "_onSuggestionSelect", "_onSuggestionSelect_returnValue", "onSuggestionClick", "", "returnValue", "listener", "(ZLkotlin/jvm/functions/Function3;)V", RequestParameters.POSITION, "onSuggestionSelect", "anko-sdk27-coroutines_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __SearchView_OnSuggestionListener implements SearchView.OnSuggestionListener {
    private Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Boolean>, ? extends Object> _onSuggestionClick;
    private boolean _onSuggestionClick_returnValue;
    private Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Boolean>, ? extends Object> _onSuggestionSelect;
    private boolean _onSuggestionSelect_returnValue;
    private final CoroutineContext context;

    public __SearchView_OnSuggestionListener(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // android.widget.SearchView.OnSuggestionListener
    public boolean onSuggestionSelect(int position) {
        boolean z = this._onSuggestionSelect_returnValue;
        Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Boolean>, ? extends Object> function3 = this._onSuggestionSelect;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __SearchView_OnSuggestionListener$onSuggestionSelect$1(function3, position, null), 2, null);
        }
        return z;
    }

    public static /* synthetic */ void onSuggestionSelect$default(__SearchView_OnSuggestionListener __searchview_onsuggestionlistener, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        __searchview_onsuggestionlistener.onSuggestionSelect(z, function3);
    }

    public final void onSuggestionSelect(boolean returnValue, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Boolean>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onSuggestionSelect = listener;
        this._onSuggestionSelect_returnValue = returnValue;
    }

    @Override // android.widget.SearchView.OnSuggestionListener
    public boolean onSuggestionClick(int position) {
        boolean z = this._onSuggestionClick_returnValue;
        Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Boolean>, ? extends Object> function3 = this._onSuggestionClick;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __SearchView_OnSuggestionListener$onSuggestionClick$1(function3, position, null), 2, null);
        }
        return z;
    }

    public static /* synthetic */ void onSuggestionClick$default(__SearchView_OnSuggestionListener __searchview_onsuggestionlistener, boolean z, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        __searchview_onsuggestionlistener.onSuggestionClick(z, function3);
    }

    public final void onSuggestionClick(boolean returnValue, Function3<? super CoroutineScope, ? super Integer, ? super Continuation<? super Boolean>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onSuggestionClick = listener;
        this._onSuggestionClick_returnValue = returnValue;
    }
}
