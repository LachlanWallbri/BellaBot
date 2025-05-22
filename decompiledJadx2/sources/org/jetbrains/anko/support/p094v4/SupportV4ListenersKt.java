package org.jetbrains.anko.support.p094v4;

import android.widget.TabHost;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTabHost;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Listeners.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006\u001ab\u0010\u0007\u001a\u00020\u0001*\u00020\b2S\b\b\u0010\t\u001aM\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00010\nH\u0086\b\u001a#\u0010\u0011\u001a\u00020\u0001*\u00020\b2\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006\u001a\u001d\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0086\b\u001a\u0088\u0001\u0010\u0016\u001a\u00020\u0001*\u00020\u00172y\b\b\u0010\t\u001as\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00010\u0018H\u0086\b\u001a4\u0010\u001f\u001a\u00020\u0001*\u00020 2%\b\b\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010!¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¨\u0006#"}, m3961d2 = {"drawerListener", "", "Landroidx/drawerlayout/widget/DrawerLayout;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/anko/support/v4/__DrawerLayout_DrawerListener;", "Lkotlin/ExtensionFunctionType;", "onAdapterChange", "Landroidx/viewpager/widget/ViewPager;", "l", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "viewPager", "Landroidx/viewpager/widget/PagerAdapter;", "oldAdapter", "newAdapter", "onPageChangeListener", "Lorg/jetbrains/anko/support/v4/__ViewPager_OnPageChangeListener;", "onRefresh", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "Lkotlin/Function0;", "onScrollChange", "Landroidx/core/widget/NestedScrollView;", "Lkotlin/Function5;", "v", "", "scrollX", "scrollY", "oldScrollX", "oldScrollY", "onTabChanged", "Landroidx/fragment/app/FragmentTabHost;", "", "tabId", "anko-support-v4_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportV4ListenersKt {
    public static final void drawerListener(DrawerLayout receiver$0, Function1<? super __DrawerLayout_DrawerListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __DrawerLayout_DrawerListener __drawerlayout_drawerlistener = new __DrawerLayout_DrawerListener();
        init.invoke(__drawerlayout_drawerlistener);
        receiver$0.addDrawerListener(__drawerlayout_drawerlistener);
    }

    public static final void onAdapterChange(ViewPager receiver$0, final Function3<? super ViewPager, ? super PagerAdapter, ? super PagerAdapter, Unit> l) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(l, "l");
        receiver$0.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() { // from class: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_support_v4_view_ViewPager_OnAdapterChangeListener$0
            @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
            public final /* synthetic */ void onAdapterChanged(ViewPager p0, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
                Intrinsics.checkParameterIsNotNull(p0, "p0");
                Intrinsics.checkExpressionValueIsNotNull(Function3.this.invoke(p0, pagerAdapter, pagerAdapter2), "invoke(...)");
            }
        });
    }

    public static final void onPageChangeListener(ViewPager receiver$0, Function1<? super __ViewPager_OnPageChangeListener, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        __ViewPager_OnPageChangeListener __viewpager_onpagechangelistener = new __ViewPager_OnPageChangeListener();
        init.invoke(__viewpager_onpagechangelistener);
        receiver$0.addOnPageChangeListener(__viewpager_onpagechangelistener);
    }

    public static final void onTabChanged(FragmentTabHost receiver$0, final Function1<? super String, Unit> l) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(l, "l");
        receiver$0.setOnTabChangedListener(new TabHost.OnTabChangeListener() { // from class: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_widget_TabHost_OnTabChangeListener$0
            @Override // android.widget.TabHost.OnTabChangeListener
            public final /* synthetic */ void onTabChanged(String str) {
                Intrinsics.checkExpressionValueIsNotNull(Function1.this.invoke(str), "invoke(...)");
            }
        });
    }

    public static final void onScrollChange(NestedScrollView receiver$0, final Function5<? super NestedScrollView, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> l) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(l, "l");
        receiver$0.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_support_v4_widget_NestedScrollView_OnScrollChangeListener$0
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public final /* synthetic */ void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                Intrinsics.checkExpressionValueIsNotNull(Function5.this.invoke(nestedScrollView, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)), "invoke(...)");
            }
        });
    }

    public static final void onRefresh(SwipeRefreshLayout receiver$0, final Function0<Unit> l) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(l, "l");
        receiver$0.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: org.jetbrains.anko.support.v4.SupportV4ListenersKt$sam$i$android_support_v4_widget_SwipeRefreshLayout_OnRefreshListener$0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final /* synthetic */ void onRefresh() {
                Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
            }
        });
    }
}
