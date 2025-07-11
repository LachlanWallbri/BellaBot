package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConstraintWidgetGroup {
    public List<ConstraintWidget> mConstrainedGroup;
    public final int[] mGroupDimensions;
    int mGroupHeight;
    int mGroupWidth;
    public boolean mSkipSolver;
    List<ConstraintWidget> mStartHorizontalWidgets;
    List<ConstraintWidget> mStartVerticalWidgets;
    List<ConstraintWidget> mUnresolvedWidgets;
    HashSet<ConstraintWidget> mWidgetsToSetHorizontal;
    HashSet<ConstraintWidget> mWidgetsToSetVertical;
    List<ConstraintWidget> mWidgetsToSolve;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstraintWidgetGroup(List<ConstraintWidget> list) {
        this.mGroupWidth = -1;
        this.mGroupHeight = -1;
        this.mSkipSolver = false;
        this.mGroupDimensions = new int[]{-1, -1};
        this.mStartHorizontalWidgets = new ArrayList();
        this.mStartVerticalWidgets = new ArrayList();
        this.mWidgetsToSetHorizontal = new HashSet<>();
        this.mWidgetsToSetVertical = new HashSet<>();
        this.mWidgetsToSolve = new ArrayList();
        this.mUnresolvedWidgets = new ArrayList();
        this.mConstrainedGroup = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstraintWidgetGroup(List<ConstraintWidget> list, boolean z) {
        this.mGroupWidth = -1;
        this.mGroupHeight = -1;
        this.mSkipSolver = false;
        this.mGroupDimensions = new int[]{-1, -1};
        this.mStartHorizontalWidgets = new ArrayList();
        this.mStartVerticalWidgets = new ArrayList();
        this.mWidgetsToSetHorizontal = new HashSet<>();
        this.mWidgetsToSetVertical = new HashSet<>();
        this.mWidgetsToSolve = new ArrayList();
        this.mUnresolvedWidgets = new ArrayList();
        this.mConstrainedGroup = list;
        this.mSkipSolver = z;
    }

    public List<ConstraintWidget> getStartWidgets(int i) {
        if (i == 0) {
            return this.mStartHorizontalWidgets;
        }
        if (i == 1) {
            return this.mStartVerticalWidgets;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<ConstraintWidget> getWidgetsToSet(int i) {
        if (i == 0) {
            return this.mWidgetsToSetHorizontal;
        }
        if (i == 1) {
            return this.mWidgetsToSetVertical;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addWidgetsToSet(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            this.mWidgetsToSetHorizontal.add(constraintWidget);
        } else if (i == 1) {
            this.mWidgetsToSetVertical.add(constraintWidget);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ConstraintWidget> getWidgetsToSolve() {
        if (!this.mWidgetsToSolve.isEmpty()) {
            return this.mWidgetsToSolve;
        }
        int size = this.mConstrainedGroup.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mConstrainedGroup.get(i);
            if (!constraintWidget.mOptimizerMeasurable) {
                getWidgetsToSolveTraversal((ArrayList) this.mWidgetsToSolve, constraintWidget);
            }
        }
        this.mUnresolvedWidgets.clear();
        this.mUnresolvedWidgets.addAll(this.mConstrainedGroup);
        this.mUnresolvedWidgets.removeAll(this.mWidgetsToSolve);
        return this.mWidgetsToSolve;
    }

    private void getWidgetsToSolveTraversal(ArrayList<ConstraintWidget> arrayList, ConstraintWidget constraintWidget) {
        if (constraintWidget.mGroupsToSolver) {
            return;
        }
        arrayList.add(constraintWidget);
        constraintWidget.mGroupsToSolver = true;
        if (constraintWidget.isFullyResolved()) {
            return;
        }
        if (constraintWidget instanceof Helper) {
            Helper helper = (Helper) constraintWidget;
            int i = helper.mWidgetsCount;
            for (int i2 = 0; i2 < i; i2++) {
                getWidgetsToSolveTraversal(arrayList, helper.mWidgets[i2]);
            }
        }
        int length = constraintWidget.mListAnchors.length;
        for (int i3 = 0; i3 < length; i3++) {
            ConstraintAnchor constraintAnchor = constraintWidget.mListAnchors[i3].mTarget;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget2 = constraintAnchor.mOwner;
                if (constraintAnchor != null && constraintWidget2 != constraintWidget.getParent()) {
                    getWidgetsToSolveTraversal(arrayList, constraintWidget2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateUnresolvedWidgets() {
        int size = this.mUnresolvedWidgets.size();
        for (int i = 0; i < size; i++) {
            updateResolvedDimension(this.mUnresolvedWidgets.get(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void updateResolvedDimension(ConstraintWidget constraintWidget) {
        ConstraintAnchor constraintAnchor;
        int i;
        int margin;
        ConstraintAnchor constraintAnchor2;
        int margin2;
        if (!constraintWidget.mOptimizerMeasurable || constraintWidget.isFullyResolved()) {
            return;
        }
        boolean z = constraintWidget.mRight.mTarget != null;
        if (z) {
            constraintAnchor = constraintWidget.mRight.mTarget;
        } else {
            constraintAnchor = constraintWidget.mLeft.mTarget;
        }
        if (constraintAnchor != null) {
            if (!constraintAnchor.mOwner.mOptimizerMeasured) {
                updateResolvedDimension(constraintAnchor.mOwner);
            }
            if (constraintAnchor.mType == ConstraintAnchor.Type.RIGHT) {
                i = constraintAnchor.mOwner.f72mX + constraintAnchor.mOwner.getWidth();
            } else if (constraintAnchor.mType == ConstraintAnchor.Type.LEFT) {
                i = constraintAnchor.mOwner.f72mX;
            }
            if (!z) {
                margin = i - constraintWidget.mRight.getMargin();
            } else {
                margin = i + constraintWidget.mLeft.getMargin() + constraintWidget.getWidth();
            }
            constraintWidget.setHorizontalDimension(margin - constraintWidget.getWidth(), margin);
            if (constraintWidget.mBaseline.mTarget == null) {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mBaseline.mTarget;
                if (!constraintAnchor3.mOwner.mOptimizerMeasured) {
                    updateResolvedDimension(constraintAnchor3.mOwner);
                }
                int i2 = (constraintAnchor3.mOwner.f73mY + constraintAnchor3.mOwner.mBaselineDistance) - constraintWidget.mBaselineDistance;
                constraintWidget.setVerticalDimension(i2, constraintWidget.mHeight + i2);
                constraintWidget.mOptimizerMeasured = true;
                return;
            }
            boolean z2 = constraintWidget.mBottom.mTarget != null;
            if (z2) {
                constraintAnchor2 = constraintWidget.mBottom.mTarget;
            } else {
                constraintAnchor2 = constraintWidget.mTop.mTarget;
            }
            if (constraintAnchor2 != null) {
                if (!constraintAnchor2.mOwner.mOptimizerMeasured) {
                    updateResolvedDimension(constraintAnchor2.mOwner);
                }
                if (constraintAnchor2.mType == ConstraintAnchor.Type.BOTTOM) {
                    margin = constraintAnchor2.mOwner.f73mY + constraintAnchor2.mOwner.getHeight();
                } else if (constraintAnchor2.mType == ConstraintAnchor.Type.TOP) {
                    margin = constraintAnchor2.mOwner.f73mY;
                }
            }
            if (z2) {
                margin2 = margin - constraintWidget.mBottom.getMargin();
            } else {
                margin2 = margin + constraintWidget.mTop.getMargin() + constraintWidget.getHeight();
            }
            constraintWidget.setVerticalDimension(margin2 - constraintWidget.getHeight(), margin2);
            constraintWidget.mOptimizerMeasured = true;
            return;
        }
        i = 0;
        if (!z) {
        }
        constraintWidget.setHorizontalDimension(margin - constraintWidget.getWidth(), margin);
        if (constraintWidget.mBaseline.mTarget == null) {
        }
    }
}
