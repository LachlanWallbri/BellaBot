package org.jetbrains.anko.support.p094v4;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Views.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b¨\u0006\u0015"}, m3961d2 = {"org/jetbrains/anko/support/v4/$$Anko$Factories$SupportV4ViewGroup", "", "()V", "DRAWER_LAYOUT", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/support/v4/_DrawerLayout;", "getDRAWER_LAYOUT", "()Lkotlin/jvm/functions/Function1;", "FRAGMENT_TAB_HOST", "Lorg/jetbrains/anko/support/v4/_FragmentTabHost;", "getFRAGMENT_TAB_HOST", "NESTED_SCROLL_VIEW", "Lorg/jetbrains/anko/support/v4/_NestedScrollView;", "getNESTED_SCROLL_VIEW", "SLIDING_PANE_LAYOUT", "Lorg/jetbrains/anko/support/v4/_SlidingPaneLayout;", "getSLIDING_PANE_LAYOUT", "VIEW_PAGER", "Lorg/jetbrains/anko/support/v4/_ViewPager;", "getVIEW_PAGER", "anko-support-v4_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup, reason: invalid class name */
/* loaded from: classes9.dex */
public final class C$$Anko$Factories$SupportV4ViewGroup {
    public static final C$$Anko$Factories$SupportV4ViewGroup INSTANCE = new C$$Anko$Factories$SupportV4ViewGroup();
    private static final Function1<Context, _FragmentTabHost> FRAGMENT_TAB_HOST = new Function1<Context, _FragmentTabHost>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup$FRAGMENT_TAB_HOST$1
        @Override // kotlin.jvm.functions.Function1
        public final _FragmentTabHost invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _FragmentTabHost(ctx);
        }
    };
    private static final Function1<Context, _ViewPager> VIEW_PAGER = new Function1<Context, _ViewPager>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup$VIEW_PAGER$1
        @Override // kotlin.jvm.functions.Function1
        public final _ViewPager invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ViewPager(ctx);
        }
    };
    private static final Function1<Context, _DrawerLayout> DRAWER_LAYOUT = new Function1<Context, _DrawerLayout>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup$DRAWER_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _DrawerLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _DrawerLayout(ctx);
        }
    };
    private static final Function1<Context, _NestedScrollView> NESTED_SCROLL_VIEW = new Function1<Context, _NestedScrollView>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup$NESTED_SCROLL_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _NestedScrollView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _NestedScrollView(ctx);
        }
    };
    private static final Function1<Context, _SlidingPaneLayout> SLIDING_PANE_LAYOUT = new Function1<Context, _SlidingPaneLayout>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4ViewGroup$SLIDING_PANE_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _SlidingPaneLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _SlidingPaneLayout(ctx);
        }
    };

    private C$$Anko$Factories$SupportV4ViewGroup() {
    }

    public final Function1<Context, _FragmentTabHost> getFRAGMENT_TAB_HOST() {
        return FRAGMENT_TAB_HOST;
    }

    public final Function1<Context, _ViewPager> getVIEW_PAGER() {
        return VIEW_PAGER;
    }

    public final Function1<Context, _DrawerLayout> getDRAWER_LAYOUT() {
        return DRAWER_LAYOUT;
    }

    public final Function1<Context, _NestedScrollView> getNESTED_SCROLL_VIEW() {
        return NESTED_SCROLL_VIEW;
    }

    public final Function1<Context, _SlidingPaneLayout> getSLIDING_PANE_LAYOUT() {
        return SLIDING_PANE_LAYOUT;
    }
}
