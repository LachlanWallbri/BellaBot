package org.hash.mock.debug.view;

import android.view.View;
import org.hash.mock.debug.manager.DebugTools;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class DebugToolsCoordinator {
    private static DebugToolsCoordinator INSTANCE;
    private DebugToolsTrashLayout trashView;

    static /* synthetic */ DebugToolsCoordinator access$000() {
        return getInstance();
    }

    private static DebugToolsCoordinator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DebugToolsCoordinator();
        }
        return INSTANCE;
    }

    private DebugToolsCoordinator() {
    }

    public void notifyPositionChanged(MagnetView magnetView) {
        DebugToolsTrashLayout debugToolsTrashLayout = this.trashView;
        if (debugToolsTrashLayout == null) {
            return;
        }
        debugToolsTrashLayout.setVisibility(0);
        if (isOverTrash(magnetView)) {
            this.trashView.applyMagnetism();
            this.trashView.vibrate();
            applyTrashMagnetism(magnetView);
            return;
        }
        this.trashView.releaseMagnetism();
    }

    private void applyTrashMagnetism(MagnetView magnetView) {
        View trashContent = getTrashContent();
        int left = trashContent.getLeft() + (trashContent.getMeasuredWidth() / 2);
        int top = trashContent.getTop() + (trashContent.getMeasuredHeight() / 2);
        int measuredWidth = left - (magnetView.getMeasuredWidth() / 2);
        int measuredHeight = top - (magnetView.getMeasuredHeight() / 2);
        magnetView.setX(measuredWidth);
        magnetView.setY(measuredHeight);
    }

    private boolean isOverTrash(MagnetView magnetView) {
        if (this.trashView.getVisibility() != 0) {
            return false;
        }
        View trashContent = getTrashContent();
        int measuredWidth = trashContent.getMeasuredWidth();
        int measuredHeight = trashContent.getMeasuredHeight();
        int i = measuredWidth / 2;
        int left = trashContent.getLeft() - i;
        int left2 = trashContent.getLeft() + measuredWidth + i;
        int i2 = measuredHeight / 2;
        int top = trashContent.getTop() - i2;
        int top2 = trashContent.getTop() + measuredHeight + i2;
        int measuredWidth2 = magnetView.getMeasuredWidth();
        int measuredHeight2 = magnetView.getMeasuredHeight();
        int x = (int) magnetView.getX();
        int i3 = measuredWidth2 + x;
        int y = (int) magnetView.getY();
        return x >= left && i3 <= left2 && y >= top && measuredHeight2 + y <= top2;
    }

    public void notifyBubbleRelease(MagnetView magnetView) {
        if (this.trashView == null) {
            return;
        }
        if (isOverTrash(magnetView)) {
            DebugTools.get().remove();
        }
        this.trashView.setVisibility(8);
    }

    private View getTrashContent() {
        return this.trashView.getChildAt(0);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class Builder {
        private DebugToolsCoordinator layoutCoordinator = DebugToolsCoordinator.access$000();

        public Builder setTrashView(DebugToolsTrashLayout debugToolsTrashLayout) {
            this.layoutCoordinator.trashView = debugToolsTrashLayout;
            return this;
        }

        public DebugToolsCoordinator build() {
            return this.layoutCoordinator;
        }
    }
}
