package org.jetbrains.anko.sdk27.coroutines;

import android.view.View;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ListenersWithCoroutines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016JW\u0010\u0014\u001a\u00020\r2G\u0010\u0019\u001aC\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006¢\u0006\u0002\b\u000fø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bH\u0016JC\u0010\u001b\u001a\u00020\r23\u0010\u0019\u001a/\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0012¢\u0006\u0002\b\u000fø\u0001\u0000¢\u0006\u0002\u0010\u001cRV\u0010\u0005\u001aE\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\u0006¢\u0006\u0002\b\u000fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010RB\u0010\u0011\u001a1\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\u0012¢\u0006\u0002\b\u000fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, m3961d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__AdapterView_OnItemSelectedListener;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_onItemSelected", "Lkotlin/Function6;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/widget/AdapterView;", "Landroid/view/View;", "", "", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function6;", "_onNothingSelected", "Lkotlin/Function3;", "Lkotlin/jvm/functions/Function3;", "onItemSelected", "p0", "p1", "p2", "p3", "listener", "(Lkotlin/jvm/functions/Function6;)V", "onNothingSelected", "(Lkotlin/jvm/functions/Function3;)V", "anko-sdk27-coroutines_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __AdapterView_OnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private Function6<? super CoroutineScope, ? super AdapterView<?>, ? super View, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> _onItemSelected;
    private Function3<? super CoroutineScope, ? super AdapterView<?>, ? super Continuation<? super Unit>, ? extends Object> _onNothingSelected;
    private final CoroutineContext context;

    public __AdapterView_OnItemSelectedListener(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> p0, View p1, int p2, long p3) {
        Function6<? super CoroutineScope, ? super AdapterView<?>, ? super View, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> function6 = this._onItemSelected;
        if (function6 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __AdapterView_OnItemSelectedListener$onItemSelected$1(function6, p0, p1, p2, p3, null), 2, null);
        }
    }

    public final void onItemSelected(Function6<? super CoroutineScope, ? super AdapterView<?>, ? super View, ? super Integer, ? super Long, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onItemSelected = listener;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> p0) {
        Function3<? super CoroutineScope, ? super AdapterView<?>, ? super Continuation<? super Unit>, ? extends Object> function3 = this._onNothingSelected;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __AdapterView_OnItemSelectedListener$onNothingSelected$1(function3, p0, null), 2, null);
        }
    }

    public final void onNothingSelected(Function3<? super CoroutineScope, ? super AdapterView<?>, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onNothingSelected = listener;
    }
}
