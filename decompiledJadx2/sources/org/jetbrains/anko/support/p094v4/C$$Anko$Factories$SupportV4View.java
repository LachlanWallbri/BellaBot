package org.jetbrains.anko.support.p094v4;

import android.content.Context;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.legacy.widget.Space;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.PagerTitleStrip;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Views.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b¨\u0006\u0015"}, m3961d2 = {"org/jetbrains/anko/support/v4/$$Anko$Factories$SupportV4View", "", "()V", "CONTENT_LOADING_PROGRESS_BAR", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroidx/core/widget/ContentLoadingProgressBar;", "getCONTENT_LOADING_PROGRESS_BAR", "()Lkotlin/jvm/functions/Function1;", "PAGER_TAB_STRIP", "Landroidx/viewpager/widget/PagerTabStrip;", "getPAGER_TAB_STRIP", "PAGER_TITLE_STRIP", "Landroidx/viewpager/widget/PagerTitleStrip;", "getPAGER_TITLE_STRIP", "SPACE", "Landroidx/legacy/widget/Space;", "getSPACE", "SWIPE_REFRESH_LAYOUT", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "getSWIPE_REFRESH_LAYOUT", "anko-support-v4_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View, reason: invalid class name */
/* loaded from: classes9.dex */
public final class C$$Anko$Factories$SupportV4View {
    public static final C$$Anko$Factories$SupportV4View INSTANCE = new C$$Anko$Factories$SupportV4View();
    private static final Function1<Context, PagerTabStrip> PAGER_TAB_STRIP = new Function1<Context, PagerTabStrip>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View$PAGER_TAB_STRIP$1
        @Override // kotlin.jvm.functions.Function1
        public final PagerTabStrip invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new PagerTabStrip(ctx);
        }
    };
    private static final Function1<Context, PagerTitleStrip> PAGER_TITLE_STRIP = new Function1<Context, PagerTitleStrip>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View$PAGER_TITLE_STRIP$1
        @Override // kotlin.jvm.functions.Function1
        public final PagerTitleStrip invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new PagerTitleStrip(ctx);
        }
    };
    private static final Function1<Context, ContentLoadingProgressBar> CONTENT_LOADING_PROGRESS_BAR = new Function1<Context, ContentLoadingProgressBar>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View$CONTENT_LOADING_PROGRESS_BAR$1
        @Override // kotlin.jvm.functions.Function1
        public final ContentLoadingProgressBar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ContentLoadingProgressBar(ctx);
        }
    };
    private static final Function1<Context, Space> SPACE = new Function1<Context, Space>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View$SPACE$1
        @Override // kotlin.jvm.functions.Function1
        public final Space invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new Space(ctx);
        }
    };
    private static final Function1<Context, SwipeRefreshLayout> SWIPE_REFRESH_LAYOUT = new Function1<Context, SwipeRefreshLayout>() { // from class: org.jetbrains.anko.support.v4.$$Anko$Factories$SupportV4View$SWIPE_REFRESH_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final SwipeRefreshLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new SwipeRefreshLayout(ctx);
        }
    };

    private C$$Anko$Factories$SupportV4View() {
    }

    public final Function1<Context, PagerTabStrip> getPAGER_TAB_STRIP() {
        return PAGER_TAB_STRIP;
    }

    public final Function1<Context, PagerTitleStrip> getPAGER_TITLE_STRIP() {
        return PAGER_TITLE_STRIP;
    }

    public final Function1<Context, ContentLoadingProgressBar> getCONTENT_LOADING_PROGRESS_BAR() {
        return CONTENT_LOADING_PROGRESS_BAR;
    }

    public final Function1<Context, Space> getSPACE() {
        return SPACE;
    }

    public final Function1<Context, SwipeRefreshLayout> getSWIPE_REFRESH_LAYOUT() {
        return SWIPE_REFRESH_LAYOUT;
    }
}
