package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    private static final boolean USE_SNAPSHOT = true;
    int mDebugSolverPassCount;
    public boolean mGroupsWrapOptimized;
    private boolean mHeightMeasuredTooSmall;
    ChainHead[] mHorizontalChainsArray;
    int mHorizontalChainsSize;
    public boolean mHorizontalWrapOptimized;
    private boolean mIsRtl;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    ChainHead[] mVerticalChainsArray;
    int mVerticalChainsSize;
    public boolean mVerticalWrapOptimized;
    public List<ConstraintWidgetGroup> mWidgetGroups;
    private boolean mWidthMeasuredTooSmall;
    public int mWrapFixedHeight;
    public int mWrapFixedWidth;

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String getType() {
        return "ConstraintLayout";
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mWidgetGroups = new ArrayList();
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 7;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mWidgetGroups = new ArrayList();
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 7;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ChainHead[4];
        this.mHorizontalChainsArray = new ChainHead[4];
        this.mWidgetGroups = new ArrayList();
        this.mGroupsWrapOptimized = false;
        this.mHorizontalWrapOptimized = false;
        this.mVerticalWrapOptimized = false;
        this.mWrapFixedWidth = 0;
        this.mWrapFixedHeight = 0;
        this.mOptimizationLevel = 7;
        this.mSkipSolver = false;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    @Override // androidx.constraintlayout.solver.widgets.WidgetContainer, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mWidgetGroups.clear();
        this.mSkipSolver = false;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem);
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                constraintWidget.addToSolver(linearSystem);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 1);
        }
        return true;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            constraintWidget.updateFromSolver(linearSystem);
            if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void analyze(int i) {
        super.analyze(i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mChildren.get(i2).analyze(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0295  */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v27 */
    @Override // androidx.constraintlayout.solver.widgets.WidgetContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void layout() {
        int i;
        int i2;
        char c;
        int i3;
        boolean z;
        int max;
        int max2;
        ?? r8;
        boolean z2;
        int i4 = this.f72mX;
        int i5 = this.f73mY;
        int i6 = 0;
        int max3 = Math.max(0, getWidth());
        int max4 = Math.max(0, getHeight());
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            setX(this.mPaddingLeft);
            setY(this.mPaddingTop);
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.f72mX = 0;
            this.f73mY = 0;
        }
        int i7 = 32;
        if (this.mOptimizationLevel != 0) {
            if (!optimizeFor(8)) {
                optimizeReset();
            }
            if (!optimizeFor(32)) {
                optimize();
            }
            this.mSystem.graphOptimizer = true;
        } else {
            this.mSystem.graphOptimizer = false;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.mListDimensionBehaviors[1];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.mListDimensionBehaviors[0];
        resetChains();
        if (this.mWidgetGroups.size() == 0) {
            this.mWidgetGroups.clear();
            this.mWidgetGroups.add(0, new ConstraintWidgetGroup(this.mChildren));
        }
        int size = this.mWidgetGroups.size();
        ArrayList<ConstraintWidget> arrayList = this.mChildren;
        boolean z3 = getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z4 = false;
        int i8 = 0;
        while (i8 < size && !this.mSkipSolver) {
            if (this.mWidgetGroups.get(i8).mSkipSolver) {
                i = size;
            } else {
                if (optimizeFor(i7)) {
                    if (getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED && getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
                        this.mChildren = (ArrayList) this.mWidgetGroups.get(i8).getWidgetsToSolve();
                    } else {
                        this.mChildren = (ArrayList) this.mWidgetGroups.get(i8).mConstrainedGroup;
                    }
                }
                resetChains();
                int size2 = this.mChildren.size();
                for (int i9 = i6; i9 < size2; i9++) {
                    ConstraintWidget constraintWidget = this.mChildren.get(i9);
                    if (constraintWidget instanceof WidgetContainer) {
                        ((WidgetContainer) constraintWidget).layout();
                    }
                }
                boolean z5 = z4;
                int i10 = 0;
                boolean z6 = true;
                while (z6) {
                    boolean z7 = z5;
                    int i11 = i10 + 1;
                    try {
                        this.mSystem.reset();
                        resetChains();
                        createObjectVariables(this.mSystem);
                        int i12 = 0;
                        while (i12 < size2) {
                            boolean z8 = z6;
                            try {
                                this.mChildren.get(i12).createObjectVariables(this.mSystem);
                                i12++;
                                z6 = z8;
                            } catch (Exception e) {
                                e = e;
                                z6 = z8;
                                e.printStackTrace();
                                PrintStream printStream = System.out;
                                boolean z9 = z6;
                                StringBuilder sb = new StringBuilder();
                                i2 = size;
                                sb.append("EXCEPTION : ");
                                sb.append(e);
                                printStream.println(sb.toString());
                                z6 = z9;
                                if (z6) {
                                }
                                c = 2;
                                if (z3) {
                                }
                                i3 = i11;
                                z = false;
                                max = Math.max(this.mMinWidth, getWidth());
                                if (max > getWidth()) {
                                }
                                max2 = Math.max(this.mMinHeight, getHeight());
                                if (max2 > getHeight()) {
                                }
                                if (!z2) {
                                }
                                z6 = z;
                                z5 = z2;
                                i10 = i3;
                                size = i2;
                            }
                        }
                        z6 = addChildrenToSolver(this.mSystem);
                        if (z6) {
                            try {
                                this.mSystem.minimize();
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                PrintStream printStream2 = System.out;
                                boolean z92 = z6;
                                StringBuilder sb2 = new StringBuilder();
                                i2 = size;
                                sb2.append("EXCEPTION : ");
                                sb2.append(e);
                                printStream2.println(sb2.toString());
                                z6 = z92;
                                if (z6) {
                                }
                                c = 2;
                                if (z3) {
                                }
                                i3 = i11;
                                z = false;
                                max = Math.max(this.mMinWidth, getWidth());
                                if (max > getWidth()) {
                                }
                                max2 = Math.max(this.mMinHeight, getHeight());
                                if (max2 > getHeight()) {
                                }
                                if (!z2) {
                                }
                                z6 = z;
                                z5 = z2;
                                i10 = i3;
                                size = i2;
                            }
                        }
                        i2 = size;
                    } catch (Exception e3) {
                        e = e3;
                    }
                    if (z6) {
                        updateChildrenFromSolver(this.mSystem, Optimizer.flags);
                    } else {
                        updateFromSolver(this.mSystem);
                        for (int i13 = 0; i13 < size2; i13++) {
                            ConstraintWidget constraintWidget2 = this.mChildren.get(i13);
                            if (constraintWidget2.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget2.getWidth() < constraintWidget2.getWrapWidth()) {
                                Optimizer.flags[2] = true;
                                c = 2;
                                break;
                            } else {
                                if (constraintWidget2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget2.getHeight() < constraintWidget2.getWrapHeight()) {
                                    c = 2;
                                    Optimizer.flags[2] = true;
                                    break;
                                }
                            }
                        }
                    }
                    c = 2;
                    if (z3 || i11 >= 8 || !Optimizer.flags[c]) {
                        i3 = i11;
                        z = false;
                    } else {
                        int i14 = 0;
                        int i15 = 0;
                        int i16 = 0;
                        while (i14 < size2) {
                            ConstraintWidget constraintWidget3 = this.mChildren.get(i14);
                            i15 = Math.max(i15, constraintWidget3.f72mX + constraintWidget3.getWidth());
                            i16 = Math.max(i16, constraintWidget3.f73mY + constraintWidget3.getHeight());
                            i14++;
                            i11 = i11;
                        }
                        i3 = i11;
                        int max5 = Math.max(this.mMinWidth, i15);
                        int max6 = Math.max(this.mMinHeight, i16);
                        if (dimensionBehaviour2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getWidth() >= max5) {
                            z = false;
                        } else {
                            setWidth(max5);
                            this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            z = true;
                            z7 = true;
                        }
                        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getHeight() < max6) {
                            setHeight(max6);
                            this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            z = true;
                            z7 = true;
                        }
                    }
                    max = Math.max(this.mMinWidth, getWidth());
                    if (max > getWidth()) {
                        setWidth(max);
                        this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                        z = true;
                        z7 = true;
                    }
                    max2 = Math.max(this.mMinHeight, getHeight());
                    if (max2 > getHeight()) {
                        setHeight(max2);
                        r8 = 1;
                        this.mListDimensionBehaviors[1] = ConstraintWidget.DimensionBehaviour.FIXED;
                        z = true;
                        z2 = true;
                    } else {
                        r8 = 1;
                        z2 = z7;
                    }
                    if (!z2) {
                        if (this.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && max3 > 0 && getWidth() > max3) {
                            this.mWidthMeasuredTooSmall = r8;
                            this.mListDimensionBehaviors[0] = ConstraintWidget.DimensionBehaviour.FIXED;
                            setWidth(max3);
                            z = r8;
                            z2 = z;
                        }
                        if (this.mListDimensionBehaviors[r8] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && max4 > 0 && getHeight() > max4) {
                            this.mHeightMeasuredTooSmall = r8;
                            this.mListDimensionBehaviors[r8] = ConstraintWidget.DimensionBehaviour.FIXED;
                            setHeight(max4);
                            z5 = true;
                            z6 = true;
                            i10 = i3;
                            size = i2;
                        }
                    }
                    z6 = z;
                    z5 = z2;
                    i10 = i3;
                    size = i2;
                }
                i = size;
                this.mWidgetGroups.get(i8).updateUnresolvedWidgets();
                z4 = z5;
            }
            i8++;
            size = i;
            i6 = 0;
            i7 = 32;
        }
        this.mChildren = arrayList;
        if (this.mParent != null) {
            int max7 = Math.max(this.mMinWidth, getWidth());
            int max8 = Math.max(this.mMinHeight, getHeight());
            this.mSnapshot.applyTo(this);
            setWidth(max7 + this.mPaddingLeft + this.mPaddingRight);
            setHeight(max8 + this.mPaddingTop + this.mPaddingBottom);
        } else {
            this.f72mX = i4;
            this.f73mY = i5;
        }
        if (z4) {
            this.mListDimensionBehaviors[0] = dimensionBehaviour2;
            this.mListDimensionBehaviors[1] = dimensionBehaviour;
        }
        resetSolverVariables(this.mSystem.getCache());
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public void preOptimize() {
        optimizeReset();
        analyze(this.mOptimizationLevel);
    }

    public void solveGraph() {
        ResolutionAnchor resolutionNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        resolutionNode.resolve(null, 0.0f);
        resolutionNode2.resolve(null, 0.0f);
    }

    public void resetGraph() {
        ResolutionAnchor resolutionNode = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
        resolutionNode.invalidateAnchors();
        resolutionNode2.invalidateAnchors();
        resolutionNode.resolve(null, 0.0f);
        resolutionNode2.resolve(null, 0.0f);
    }

    public void optimizeForDimensions(int i, int i2) {
        if (this.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && this.mResolutionWidth != null) {
            this.mResolutionWidth.resolve(i);
        }
        if (this.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.mResolutionHeight == null) {
            return;
        }
        this.mResolutionHeight.resolve(i2);
    }

    public void optimizeReset() {
        int size = this.mChildren.size();
        resetResolutionNodes();
        for (int i = 0; i < size; i++) {
            this.mChildren.get(i).resetResolutionNodes();
        }
    }

    public void optimize() {
        if (!optimizeFor(8)) {
            analyze(this.mOptimizationLevel);
        }
        solveGraph();
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mVerticalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public List<ConstraintWidgetGroup> getWidgetGroups() {
        return this.mWidgetGroups;
    }
}
