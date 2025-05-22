package org.jetbrains.anko;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Views.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ª\u0001\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00160\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u001d\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\bR\u001d\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u001d\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\bR\u001d\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020%0\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u001d\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(0\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\bR\u001d\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020+0\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\bR\u001d\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020.0\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\bR\u001d\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002010\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\bR\u001d\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002040\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\bR\u001d\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002070\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\bR\u001d\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020:0\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\bR\u001d\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020=0\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\b¨\u0006?"}, m3961d2 = {"org/jetbrains/anko/$$Anko$Factories$Sdk27ViewGroup", "", "()V", "ABSOLUTE_LAYOUT", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/_AbsoluteLayout;", "getABSOLUTE_LAYOUT", "()Lkotlin/jvm/functions/Function1;", "ACTION_MENU_VIEW", "Lorg/jetbrains/anko/_ActionMenuView;", "getACTION_MENU_VIEW", "APP_WIDGET_HOST_VIEW", "Lorg/jetbrains/anko/_AppWidgetHostView;", "getAPP_WIDGET_HOST_VIEW", "FRAME_LAYOUT", "Lorg/jetbrains/anko/_FrameLayout;", "getFRAME_LAYOUT", "GALLERY", "Lorg/jetbrains/anko/_Gallery;", "getGALLERY", "GRID_LAYOUT", "Lorg/jetbrains/anko/_GridLayout;", "getGRID_LAYOUT", "GRID_VIEW", "Lorg/jetbrains/anko/_GridView;", "getGRID_VIEW", "HORIZONTAL_SCROLL_VIEW", "Lorg/jetbrains/anko/_HorizontalScrollView;", "getHORIZONTAL_SCROLL_VIEW", "IMAGE_SWITCHER", "Lorg/jetbrains/anko/_ImageSwitcher;", "getIMAGE_SWITCHER", "LINEAR_LAYOUT", "Lorg/jetbrains/anko/_LinearLayout;", "getLINEAR_LAYOUT", "RADIO_GROUP", "Lorg/jetbrains/anko/_RadioGroup;", "getRADIO_GROUP", "RELATIVE_LAYOUT", "Lorg/jetbrains/anko/_RelativeLayout;", "getRELATIVE_LAYOUT", "SCROLL_VIEW", "Lorg/jetbrains/anko/_ScrollView;", "getSCROLL_VIEW", "TABLE_LAYOUT", "Lorg/jetbrains/anko/_TableLayout;", "getTABLE_LAYOUT", "TABLE_ROW", "Lorg/jetbrains/anko/_TableRow;", "getTABLE_ROW", "TEXT_SWITCHER", "Lorg/jetbrains/anko/_TextSwitcher;", "getTEXT_SWITCHER", "TOOLBAR", "Lorg/jetbrains/anko/_Toolbar;", "getTOOLBAR", "VIEW_ANIMATOR", "Lorg/jetbrains/anko/_ViewAnimator;", "getVIEW_ANIMATOR", "VIEW_SWITCHER", "Lorg/jetbrains/anko/_ViewSwitcher;", "getVIEW_SWITCHER", "anko-sdk27_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup, reason: invalid class name */
/* loaded from: classes9.dex */
public final class C$$Anko$Factories$Sdk27ViewGroup {
    public static final C$$Anko$Factories$Sdk27ViewGroup INSTANCE = new C$$Anko$Factories$Sdk27ViewGroup();
    private static final Function1<Context, _AppWidgetHostView> APP_WIDGET_HOST_VIEW = new Function1<Context, _AppWidgetHostView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$APP_WIDGET_HOST_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _AppWidgetHostView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _AppWidgetHostView(ctx);
        }
    };
    private static final Function1<Context, _AbsoluteLayout> ABSOLUTE_LAYOUT = new Function1<Context, _AbsoluteLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$ABSOLUTE_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _AbsoluteLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _AbsoluteLayout(ctx);
        }
    };
    private static final Function1<Context, _ActionMenuView> ACTION_MENU_VIEW = new Function1<Context, _ActionMenuView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$ACTION_MENU_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _ActionMenuView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ActionMenuView(ctx);
        }
    };
    private static final Function1<Context, _FrameLayout> FRAME_LAYOUT = new Function1<Context, _FrameLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$FRAME_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _FrameLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _FrameLayout(ctx);
        }
    };
    private static final Function1<Context, _Gallery> GALLERY = new Function1<Context, _Gallery>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$GALLERY$1
        @Override // kotlin.jvm.functions.Function1
        public final _Gallery invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _Gallery(ctx);
        }
    };
    private static final Function1<Context, _GridLayout> GRID_LAYOUT = new Function1<Context, _GridLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$GRID_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _GridLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _GridLayout(ctx);
        }
    };
    private static final Function1<Context, _GridView> GRID_VIEW = new Function1<Context, _GridView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$GRID_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _GridView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _GridView(ctx);
        }
    };
    private static final Function1<Context, _HorizontalScrollView> HORIZONTAL_SCROLL_VIEW = new Function1<Context, _HorizontalScrollView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$HORIZONTAL_SCROLL_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _HorizontalScrollView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _HorizontalScrollView(ctx);
        }
    };
    private static final Function1<Context, _ImageSwitcher> IMAGE_SWITCHER = new Function1<Context, _ImageSwitcher>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$IMAGE_SWITCHER$1
        @Override // kotlin.jvm.functions.Function1
        public final _ImageSwitcher invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ImageSwitcher(ctx);
        }
    };
    private static final Function1<Context, _LinearLayout> LINEAR_LAYOUT = new Function1<Context, _LinearLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$LINEAR_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _LinearLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _LinearLayout(ctx);
        }
    };
    private static final Function1<Context, _RadioGroup> RADIO_GROUP = new Function1<Context, _RadioGroup>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$RADIO_GROUP$1
        @Override // kotlin.jvm.functions.Function1
        public final _RadioGroup invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _RadioGroup(ctx);
        }
    };
    private static final Function1<Context, _RelativeLayout> RELATIVE_LAYOUT = new Function1<Context, _RelativeLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$RELATIVE_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _RelativeLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _RelativeLayout(ctx);
        }
    };
    private static final Function1<Context, _ScrollView> SCROLL_VIEW = new Function1<Context, _ScrollView>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$SCROLL_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _ScrollView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ScrollView(ctx);
        }
    };
    private static final Function1<Context, _TableLayout> TABLE_LAYOUT = new Function1<Context, _TableLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$TABLE_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _TableLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _TableLayout(ctx);
        }
    };
    private static final Function1<Context, _TableRow> TABLE_ROW = new Function1<Context, _TableRow>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$TABLE_ROW$1
        @Override // kotlin.jvm.functions.Function1
        public final _TableRow invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _TableRow(ctx);
        }
    };
    private static final Function1<Context, _TextSwitcher> TEXT_SWITCHER = new Function1<Context, _TextSwitcher>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$TEXT_SWITCHER$1
        @Override // kotlin.jvm.functions.Function1
        public final _TextSwitcher invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _TextSwitcher(ctx);
        }
    };
    private static final Function1<Context, _Toolbar> TOOLBAR = new Function1<Context, _Toolbar>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$TOOLBAR$1
        @Override // kotlin.jvm.functions.Function1
        public final _Toolbar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _Toolbar(ctx);
        }
    };
    private static final Function1<Context, _ViewAnimator> VIEW_ANIMATOR = new Function1<Context, _ViewAnimator>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$VIEW_ANIMATOR$1
        @Override // kotlin.jvm.functions.Function1
        public final _ViewAnimator invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ViewAnimator(ctx);
        }
    };
    private static final Function1<Context, _ViewSwitcher> VIEW_SWITCHER = new Function1<Context, _ViewSwitcher>() { // from class: org.jetbrains.anko.$$Anko$Factories$Sdk27ViewGroup$VIEW_SWITCHER$1
        @Override // kotlin.jvm.functions.Function1
        public final _ViewSwitcher invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ViewSwitcher(ctx);
        }
    };

    private C$$Anko$Factories$Sdk27ViewGroup() {
    }

    public final Function1<Context, _AppWidgetHostView> getAPP_WIDGET_HOST_VIEW() {
        return APP_WIDGET_HOST_VIEW;
    }

    public final Function1<Context, _AbsoluteLayout> getABSOLUTE_LAYOUT() {
        return ABSOLUTE_LAYOUT;
    }

    public final Function1<Context, _ActionMenuView> getACTION_MENU_VIEW() {
        return ACTION_MENU_VIEW;
    }

    public final Function1<Context, _FrameLayout> getFRAME_LAYOUT() {
        return FRAME_LAYOUT;
    }

    public final Function1<Context, _Gallery> getGALLERY() {
        return GALLERY;
    }

    public final Function1<Context, _GridLayout> getGRID_LAYOUT() {
        return GRID_LAYOUT;
    }

    public final Function1<Context, _GridView> getGRID_VIEW() {
        return GRID_VIEW;
    }

    public final Function1<Context, _HorizontalScrollView> getHORIZONTAL_SCROLL_VIEW() {
        return HORIZONTAL_SCROLL_VIEW;
    }

    public final Function1<Context, _ImageSwitcher> getIMAGE_SWITCHER() {
        return IMAGE_SWITCHER;
    }

    public final Function1<Context, _LinearLayout> getLINEAR_LAYOUT() {
        return LINEAR_LAYOUT;
    }

    public final Function1<Context, _RadioGroup> getRADIO_GROUP() {
        return RADIO_GROUP;
    }

    public final Function1<Context, _RelativeLayout> getRELATIVE_LAYOUT() {
        return RELATIVE_LAYOUT;
    }

    public final Function1<Context, _ScrollView> getSCROLL_VIEW() {
        return SCROLL_VIEW;
    }

    public final Function1<Context, _TableLayout> getTABLE_LAYOUT() {
        return TABLE_LAYOUT;
    }

    public final Function1<Context, _TableRow> getTABLE_ROW() {
        return TABLE_ROW;
    }

    public final Function1<Context, _TextSwitcher> getTEXT_SWITCHER() {
        return TEXT_SWITCHER;
    }

    public final Function1<Context, _Toolbar> getTOOLBAR() {
        return TOOLBAR;
    }

    public final Function1<Context, _ViewAnimator> getVIEW_ANIMATOR() {
        return VIEW_ANIMATOR;
    }

    public final Function1<Context, _ViewSwitcher> getVIEW_SWITCHER() {
        return VIEW_SWITCHER;
    }
}
