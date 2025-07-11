package com.google.android.material.badge;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.android.material.internal.ToolbarUtils;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class BadgeUtils {
    public static final boolean USE_COMPAT_PARENT;

    static {
        USE_COMPAT_PARENT = Build.VERSION.SDK_INT < 18;
    }

    private BadgeUtils() {
    }

    public static void updateBadgeBounds(Rect rect, float f, float f2, float f3, float f4) {
        rect.set((int) (f - f3), (int) (f2 - f4), (int) (f + f3), (int) (f2 + f4));
    }

    public static void attachBadgeDrawable(BadgeDrawable badgeDrawable, View view, FrameLayout frameLayout) {
        setBadgeDrawableBounds(badgeDrawable, view, frameLayout);
        if (!USE_COMPAT_PARENT) {
            view.getOverlay().add(badgeDrawable);
        } else {
            if (frameLayout == null) {
                throw new IllegalArgumentException("Trying to reference null compatBadgeParent");
            }
            frameLayout.setForeground(badgeDrawable);
        }
    }

    public static void detachBadgeDrawable(BadgeDrawable badgeDrawable, View view, FrameLayout frameLayout) {
        if (badgeDrawable == null) {
            return;
        }
        if (USE_COMPAT_PARENT) {
            frameLayout.setForeground(null);
        } else {
            view.getOverlay().remove(badgeDrawable);
        }
    }

    public static void setBadgeDrawableBounds(BadgeDrawable badgeDrawable, View view, FrameLayout frameLayout) {
        Rect rect = new Rect();
        View view2 = USE_COMPAT_PARENT ? frameLayout : view;
        if (view2 == null) {
            throw new IllegalArgumentException("Trying to reference null badgeParent");
        }
        view2.getDrawingRect(rect);
        badgeDrawable.setBounds(rect);
        badgeDrawable.updateBadgeCoordinates(view, frameLayout);
    }

    /* renamed from: com.google.android.material.badge.BadgeUtils$1 */
    /* loaded from: classes2.dex */
    static class RunnableC16641 implements Runnable {
        final /* synthetic */ BadgeDrawable val$badgeDrawable;
        final /* synthetic */ FrameLayout val$customBadgeParent;
        final /* synthetic */ int val$menuItemId;
        final /* synthetic */ Toolbar val$toolbar;

        RunnableC16641(Toolbar toolbar, int i, BadgeDrawable badgeDrawable, FrameLayout frameLayout) {
            this.val$toolbar = toolbar;
            this.val$menuItemId = i;
            this.val$badgeDrawable = badgeDrawable;
            this.val$customBadgeParent = frameLayout;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionMenuItemView actionMenuItemView = ToolbarUtils.getActionMenuItemView(this.val$toolbar, this.val$menuItemId);
            if (actionMenuItemView != null) {
                BadgeUtils.setToolbarOffset(this.val$badgeDrawable, this.val$toolbar.getResources());
                BadgeUtils.attachBadgeDrawable(this.val$badgeDrawable, actionMenuItemView, this.val$customBadgeParent);
            }
        }
    }

    public static ParcelableSparseArray createParcelableBadgeStates(SparseArray<BadgeDrawable> sparseArray) {
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            BadgeDrawable valueAt = sparseArray.valueAt(i);
            if (valueAt == null) {
                throw new IllegalArgumentException("badgeDrawable cannot be null");
            }
            parcelableSparseArray.put(keyAt, valueAt.getSavedState());
        }
        return parcelableSparseArray;
    }

    public static SparseArray<BadgeDrawable> createBadgeDrawablesFromSavedStates(Context context, ParcelableSparseArray parcelableSparseArray) {
        SparseArray<BadgeDrawable> sparseArray = new SparseArray<>(parcelableSparseArray.size());
        for (int i = 0; i < parcelableSparseArray.size(); i++) {
            int keyAt = parcelableSparseArray.keyAt(i);
            BadgeDrawable.SavedState savedState = (BadgeDrawable.SavedState) parcelableSparseArray.valueAt(i);
            if (savedState == null) {
                throw new IllegalArgumentException("BadgeDrawable's savedState cannot be null");
            }
            sparseArray.put(keyAt, BadgeDrawable.createFromSavedState(context, savedState));
        }
        return sparseArray;
    }
}
