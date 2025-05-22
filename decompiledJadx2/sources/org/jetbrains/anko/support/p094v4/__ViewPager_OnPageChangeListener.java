package org.jetbrains.anko.support.p094v4;

import androidx.viewpager.widget.ViewPager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Listeners.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0005H\u0016J&\u0010\u000e\u001a\u00020\u00062\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\bJ \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u001a\u0010\u0012\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0005H\u0016R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lorg/jetbrains/anko/support/v4/__ViewPager_OnPageChangeListener;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "()V", "_onPageScrollStateChanged", "Lkotlin/Function1;", "", "", "_onPageScrolled", "Lkotlin/Function3;", "", "_onPageSelected", "onPageScrollStateChanged", "listener", "state", "onPageScrolled", RequestParameters.POSITION, "positionOffset", "positionOffsetPixels", "onPageSelected", "anko-support-v4_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class __ViewPager_OnPageChangeListener implements ViewPager.OnPageChangeListener {
    private Function1<? super Integer, Unit> _onPageScrollStateChanged;
    private Function3<? super Integer, ? super Float, ? super Integer, Unit> _onPageScrolled;
    private Function1<? super Integer, Unit> _onPageSelected;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Function3<? super Integer, ? super Float, ? super Integer, Unit> function3 = this._onPageScrolled;
        if (function3 != null) {
            function3.invoke(Integer.valueOf(position), Float.valueOf(positionOffset), Integer.valueOf(positionOffsetPixels));
        }
    }

    public final void onPageScrolled(Function3<? super Integer, ? super Float, ? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onPageScrolled = listener;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        Function1<? super Integer, Unit> function1 = this._onPageSelected;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(position));
        }
    }

    public final void onPageSelected(Function1<? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onPageSelected = listener;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        Function1<? super Integer, Unit> function1 = this._onPageScrollStateChanged;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(state));
        }
    }

    public final void onPageScrollStateChanged(Function1<? super Integer, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this._onPageScrollStateChanged = listener;
    }
}
