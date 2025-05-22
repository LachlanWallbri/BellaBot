package org.jetbrains.anko.support.p094v4;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Listeners.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\r\u001a\u00020\u00062\u0014\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0004J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u001c\u0010\u0010\u001a\u00020\u00062\u0014\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0004J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\"\u0010\u0011\u001a\u00020\u00062\u001a\u0010\u000e\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\tJ\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u001a\u0010\u0013\u001a\u00020\u00062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u0004J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\fH\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lorg/jetbrains/anko/support/v4/__DrawerLayout_DrawerListener;", "Landroidx/drawerlayout/widget/DrawerLayout$DrawerListener;", "()V", "_onDrawerClosed", "Lkotlin/Function1;", "Landroid/view/View;", "", "_onDrawerOpened", "_onDrawerSlide", "Lkotlin/Function2;", "", "_onDrawerStateChanged", "", "onDrawerClosed", "listener", "drawerView", "onDrawerOpened", "onDrawerSlide", "slideOffset", "onDrawerStateChanged", "newState", "anko-support-v4_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __DrawerLayout_DrawerListener implements DrawerLayout.DrawerListener {
    private Function1<? super View, Unit> _onDrawerClosed;
    private Function1<? super View, Unit> _onDrawerOpened;
    private Function2<? super View, ? super Float, Unit> _onDrawerSlide;
    private Function1<? super Integer, Unit> _onDrawerStateChanged;

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Intrinsics.checkParameterIsNotNull(drawerView, "drawerView");
        Function2<? super View, ? super Float, Unit> function2 = this._onDrawerSlide;
        if (function2 != null) {
            function2.invoke(drawerView, Float.valueOf(slideOffset));
        }
    }

    public final void onDrawerSlide(Function2<? super View, ? super Float, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onDrawerSlide = listener;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View drawerView) {
        Intrinsics.checkParameterIsNotNull(drawerView, "drawerView");
        Function1<? super View, Unit> function1 = this._onDrawerOpened;
        if (function1 != null) {
            function1.invoke(drawerView);
        }
    }

    public final void onDrawerOpened(Function1<? super View, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onDrawerOpened = listener;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View drawerView) {
        Intrinsics.checkParameterIsNotNull(drawerView, "drawerView");
        Function1<? super View, Unit> function1 = this._onDrawerClosed;
        if (function1 != null) {
            function1.invoke(drawerView);
        }
    }

    public final void onDrawerClosed(Function1<? super View, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onDrawerClosed = listener;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int newState) {
        Function1<? super Integer, Unit> function1 = this._onDrawerStateChanged;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(newState));
        }
    }

    public final void onDrawerStateChanged(Function1<? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onDrawerStateChanged = listener;
    }
}
