package org.jetbrains.anko.appcompat.p092v7;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Views.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00160\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u001d\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\bR\u001d\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\b¨\u0006!"}, m3961d2 = {"org/jetbrains/anko/appcompat/v7/$$Anko$Factories$AppcompatV7ViewGroup", "", "()V", "ACTION_BAR_CONTAINER", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/appcompat/v7/_ActionBarContainer;", "getACTION_BAR_CONTAINER", "()Lkotlin/jvm/functions/Function1;", "ACTION_BAR_OVERLAY_LAYOUT", "Lorg/jetbrains/anko/appcompat/v7/_ActionBarOverlayLayout;", "getACTION_BAR_OVERLAY_LAYOUT", "ACTION_MENU_VIEW", "Lorg/jetbrains/anko/appcompat/v7/_ActionMenuView;", "getACTION_MENU_VIEW", "ALERT_DIALOG_LAYOUT", "Lorg/jetbrains/anko/appcompat/v7/_AlertDialogLayout;", "getALERT_DIALOG_LAYOUT", "BUTTON_BAR_LAYOUT", "Lorg/jetbrains/anko/appcompat/v7/_ButtonBarLayout;", "getBUTTON_BAR_LAYOUT", "LINEAR_LAYOUT_COMPAT", "Lorg/jetbrains/anko/appcompat/v7/_LinearLayoutCompat;", "getLINEAR_LAYOUT_COMPAT", "LIST_MENU_ITEM_VIEW", "Lorg/jetbrains/anko/appcompat/v7/_ListMenuItemView;", "getLIST_MENU_ITEM_VIEW", "SCROLLING_TAB_CONTAINER_VIEW", "Lorg/jetbrains/anko/appcompat/v7/_ScrollingTabContainerView;", "getSCROLLING_TAB_CONTAINER_VIEW", "TOOLBAR", "Lorg/jetbrains/anko/appcompat/v7/_Toolbar;", "getTOOLBAR", "anko-appcompat-v7_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup, reason: invalid class name */
/* loaded from: classes9.dex */
public final class C$$Anko$Factories$AppcompatV7ViewGroup {
    public static final C$$Anko$Factories$AppcompatV7ViewGroup INSTANCE = new C$$Anko$Factories$AppcompatV7ViewGroup();
    private static final Function1<Context, _ListMenuItemView> LIST_MENU_ITEM_VIEW = new Function1<Context, _ListMenuItemView>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$LIST_MENU_ITEM_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _ListMenuItemView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ListMenuItemView(ctx, null);
        }
    };
    private static final Function1<Context, _ActionBarContainer> ACTION_BAR_CONTAINER = new Function1<Context, _ActionBarContainer>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$ACTION_BAR_CONTAINER$1
        @Override // kotlin.jvm.functions.Function1
        public final _ActionBarContainer invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ActionBarContainer(ctx);
        }
    };
    private static final Function1<Context, _ActionBarOverlayLayout> ACTION_BAR_OVERLAY_LAYOUT = new Function1<Context, _ActionBarOverlayLayout>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$ACTION_BAR_OVERLAY_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _ActionBarOverlayLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ActionBarOverlayLayout(ctx);
        }
    };
    private static final Function1<Context, _ActionMenuView> ACTION_MENU_VIEW = new Function1<Context, _ActionMenuView>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$ACTION_MENU_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _ActionMenuView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ActionMenuView(ctx);
        }
    };
    private static final Function1<Context, _AlertDialogLayout> ALERT_DIALOG_LAYOUT = new Function1<Context, _AlertDialogLayout>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$ALERT_DIALOG_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _AlertDialogLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _AlertDialogLayout(ctx);
        }
    };
    private static final Function1<Context, _ButtonBarLayout> BUTTON_BAR_LAYOUT = new Function1<Context, _ButtonBarLayout>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$BUTTON_BAR_LAYOUT$1
        @Override // kotlin.jvm.functions.Function1
        public final _ButtonBarLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ButtonBarLayout(ctx, null);
        }
    };
    private static final Function1<Context, _LinearLayoutCompat> LINEAR_LAYOUT_COMPAT = new Function1<Context, _LinearLayoutCompat>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$LINEAR_LAYOUT_COMPAT$1
        @Override // kotlin.jvm.functions.Function1
        public final _LinearLayoutCompat invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _LinearLayoutCompat(ctx);
        }
    };
    private static final Function1<Context, _ScrollingTabContainerView> SCROLLING_TAB_CONTAINER_VIEW = new Function1<Context, _ScrollingTabContainerView>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$SCROLLING_TAB_CONTAINER_VIEW$1
        @Override // kotlin.jvm.functions.Function1
        public final _ScrollingTabContainerView invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _ScrollingTabContainerView(ctx);
        }
    };
    private static final Function1<Context, _Toolbar> TOOLBAR = new Function1<Context, _Toolbar>() { // from class: org.jetbrains.anko.appcompat.v7.$$Anko$Factories$AppcompatV7ViewGroup$TOOLBAR$1
        @Override // kotlin.jvm.functions.Function1
        public final _Toolbar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new _Toolbar(ctx);
        }
    };

    private C$$Anko$Factories$AppcompatV7ViewGroup() {
    }

    public final Function1<Context, _ListMenuItemView> getLIST_MENU_ITEM_VIEW() {
        return LIST_MENU_ITEM_VIEW;
    }

    public final Function1<Context, _ActionBarContainer> getACTION_BAR_CONTAINER() {
        return ACTION_BAR_CONTAINER;
    }

    public final Function1<Context, _ActionBarOverlayLayout> getACTION_BAR_OVERLAY_LAYOUT() {
        return ACTION_BAR_OVERLAY_LAYOUT;
    }

    public final Function1<Context, _ActionMenuView> getACTION_MENU_VIEW() {
        return ACTION_MENU_VIEW;
    }

    public final Function1<Context, _AlertDialogLayout> getALERT_DIALOG_LAYOUT() {
        return ALERT_DIALOG_LAYOUT;
    }

    public final Function1<Context, _ButtonBarLayout> getBUTTON_BAR_LAYOUT() {
        return BUTTON_BAR_LAYOUT;
    }

    public final Function1<Context, _LinearLayoutCompat> getLINEAR_LAYOUT_COMPAT() {
        return LINEAR_LAYOUT_COMPAT;
    }

    public final Function1<Context, _ScrollingTabContainerView> getSCROLLING_TAB_CONTAINER_VIEW() {
        return SCROLLING_TAB_CONTAINER_VIEW;
    }

    public final Function1<Context, _Toolbar> getTOOLBAR() {
        return TOOLBAR;
    }
}
