package org.jetbrains.anko.sdk27.coroutines;

import android.text.Editable;
import android.text.TextWatcher;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0016J?\u0010\u0014\u001a\u00020\n2/\u0010\u0016\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0017J*\u0010\u0018\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0016JQ\u0010\u0018\u001a\u00020\n2A\u0010\u0016\u001a=\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000f¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u001cJ*\u0010\u001d\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0016JQ\u0010\u001d\u001a\u00020\n2A\u0010\u0016\u001a=\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000f¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u001cR>\u0010\u0005\u001a-\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0006¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rRP\u0010\u000e\u001a?\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u000f¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0012RP\u0010\u0013\u001a?\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u000f¢\u0006\u0002\b\fX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, m3961d2 = {"Lorg/jetbrains/anko/sdk27/coroutines/__TextWatcher;", "Landroid/text/TextWatcher;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "_afterTextChanged", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/text/Editable;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "_beforeTextChanged", "Lkotlin/Function6;", "", "", "Lkotlin/jvm/functions/Function6;", "_onTextChanged", "afterTextChanged", "s", "listener", "(Lkotlin/jvm/functions/Function3;)V", "beforeTextChanged", "start", "count", "after", "(Lkotlin/jvm/functions/Function6;)V", "onTextChanged", "before", "anko-sdk27-coroutines_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __TextWatcher implements TextWatcher {
    private Function3<? super CoroutineScope, ? super Editable, ? super Continuation<? super Unit>, ? extends Object> _afterTextChanged;
    private Function6<? super CoroutineScope, ? super CharSequence, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> _beforeTextChanged;
    private Function6<? super CoroutineScope, ? super CharSequence, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> _onTextChanged;
    private final CoroutineContext context;

    public __TextWatcher(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Function6<? super CoroutineScope, ? super CharSequence, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function6 = this._beforeTextChanged;
        if (function6 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __TextWatcher$beforeTextChanged$1(function6, s, start, count, after, null), 2, null);
        }
    }

    public final void beforeTextChanged(Function6<? super CoroutineScope, ? super CharSequence, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._beforeTextChanged = listener;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Function6<? super CoroutineScope, ? super CharSequence, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> function6 = this._onTextChanged;
        if (function6 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __TextWatcher$onTextChanged$1(function6, s, start, before, count, null), 2, null);
        }
    }

    public final void onTextChanged(Function6<? super CoroutineScope, ? super CharSequence, ? super Integer, ? super Integer, ? super Integer, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onTextChanged = listener;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        Function3<? super CoroutineScope, ? super Editable, ? super Continuation<? super Unit>, ? extends Object> function3 = this._afterTextChanged;
        if (function3 != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.context, null, new __TextWatcher$afterTextChanged$1(function3, s, null), 2, null);
        }
    }

    public final void afterTextChanged(Function3<? super CoroutineScope, ? super Editable, ? super Continuation<? super Unit>, ? extends Object> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._afterTextChanged = listener;
    }
}
