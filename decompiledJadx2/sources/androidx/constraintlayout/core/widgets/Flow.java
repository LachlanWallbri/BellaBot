package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mDisplayedWidgets;
    private int mHorizontalStyle = -1;
    private int mVerticalStyle = -1;
    private int mFirstHorizontalStyle = -1;
    private int mFirstVerticalStyle = -1;
    private int mLastHorizontalStyle = -1;
    private int mLastVerticalStyle = -1;
    private float mHorizontalBias = 0.5f;
    private float mVerticalBias = 0.5f;
    private float mFirstHorizontalBias = 0.5f;
    private float mFirstVerticalBias = 0.5f;
    private float mLastHorizontalBias = 0.5f;
    private float mLastVerticalBias = 0.5f;
    private int mHorizontalGap = 0;
    private int mVerticalGap = 0;
    private int mHorizontalAlign = 2;
    private int mVerticalAlign = 2;
    private int mWrapMode = 0;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private int[] mAlignedDimensions = null;
    private int mDisplayedWidgetsCount = 0;

    @Override // androidx.constraintlayout.core.widgets.HelperWidget, androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        Flow flow = (Flow) constraintWidget;
        this.mHorizontalStyle = flow.mHorizontalStyle;
        this.mVerticalStyle = flow.mVerticalStyle;
        this.mFirstHorizontalStyle = flow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = flow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = flow.mLastHorizontalStyle;
        this.mLastVerticalStyle = flow.mLastVerticalStyle;
        this.mHorizontalBias = flow.mHorizontalBias;
        this.mVerticalBias = flow.mVerticalBias;
        this.mFirstHorizontalBias = flow.mFirstHorizontalBias;
        this.mFirstVerticalBias = flow.mFirstVerticalBias;
        this.mLastHorizontalBias = flow.mLastHorizontalBias;
        this.mLastVerticalBias = flow.mLastVerticalBias;
        this.mHorizontalGap = flow.mHorizontalGap;
        this.mVerticalGap = flow.mVerticalGap;
        this.mHorizontalAlign = flow.mHorizontalAlign;
        this.mVerticalAlign = flow.mVerticalAlign;
        this.mWrapMode = flow.mWrapMode;
        this.mMaxElementsWrap = flow.mMaxElementsWrap;
        this.mOrientation = flow.mOrientation;
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public void setFirstHorizontalStyle(int i) {
        this.mFirstHorizontalStyle = i;
    }

    public void setFirstVerticalStyle(int i) {
        this.mFirstVerticalStyle = i;
    }

    public void setLastHorizontalStyle(int i) {
        this.mLastHorizontalStyle = i;
    }

    public void setLastVerticalStyle(int i) {
        this.mLastVerticalStyle = i;
    }

    public void setHorizontalStyle(int i) {
        this.mHorizontalStyle = i;
    }

    public void setVerticalStyle(int i) {
        this.mVerticalStyle = i;
    }

    public void setHorizontalBias(float f) {
        this.mHorizontalBias = f;
    }

    public void setVerticalBias(float f) {
        this.mVerticalBias = f;
    }

    public void setFirstHorizontalBias(float f) {
        this.mFirstHorizontalBias = f;
    }

    public void setFirstVerticalBias(float f) {
        this.mFirstVerticalBias = f;
    }

    public void setLastHorizontalBias(float f) {
        this.mLastHorizontalBias = f;
    }

    public void setLastVerticalBias(float f) {
        this.mLastVerticalBias = f;
    }

    public void setHorizontalAlign(int i) {
        this.mHorizontalAlign = i;
    }

    public void setVerticalAlign(int i) {
        this.mVerticalAlign = i;
    }

    public void setWrapMode(int i) {
        this.mWrapMode = i;
    }

    public void setHorizontalGap(int i) {
        this.mHorizontalGap = i;
    }

    public void setVerticalGap(int i) {
        this.mVerticalGap = i;
    }

    public void setMaxElementsWrap(int i) {
        this.mMaxElementsWrap = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                return 0;
            }
            if (constraintWidget.mMatchConstraintDefaultWidth == 2) {
                int i2 = (int) (constraintWidget.mMatchConstraintPercentWidth * i);
                if (i2 != constraintWidget.getWidth()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i2, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                }
                return i2;
            }
            if (constraintWidget.mMatchConstraintDefaultWidth == 1) {
                return constraintWidget.getWidth();
            }
            if (constraintWidget.mMatchConstraintDefaultWidth == 3) {
                return (int) ((constraintWidget.getHeight() * constraintWidget.mDimensionRatio) + 0.5f);
            }
        }
        return constraintWidget.getWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget constraintWidget, int i) {
        if (constraintWidget == null) {
            return 0;
        }
        if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                return 0;
            }
            if (constraintWidget.mMatchConstraintDefaultHeight == 2) {
                int i2 = (int) (constraintWidget.mMatchConstraintPercentHeight * i);
                if (i2 != constraintWidget.getHeight()) {
                    constraintWidget.setMeasureRequested(true);
                    measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i2);
                }
                return i2;
            }
            if (constraintWidget.mMatchConstraintDefaultHeight == 1) {
                return constraintWidget.getHeight();
            }
            if (constraintWidget.mMatchConstraintDefaultHeight == 3) {
                return (int) ((constraintWidget.getWidth() * constraintWidget.mDimensionRatio) + 0.5f);
            }
        }
        return constraintWidget.getHeight();
    }

    @Override // androidx.constraintlayout.core.widgets.VirtualLayout
    public void measure(int i, int i2, int i3, int i4) {
        int[] iArr;
        boolean z;
        if (this.mWidgetsCount > 0 && !measureChildren()) {
            setMeasure(0, 0);
            needsCallbackFromSolver(false);
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int[] iArr2 = new int[2];
        int i5 = (i2 - paddingLeft) - paddingRight;
        if (this.mOrientation == 1) {
            i5 = (i4 - paddingTop) - paddingBottom;
        }
        int i6 = i5;
        if (this.mOrientation == 0) {
            if (this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
            if (this.mVerticalStyle == -1) {
                this.mVerticalStyle = 0;
            }
        } else {
            if (this.mHorizontalStyle == -1) {
                this.mHorizontalStyle = 0;
            }
            if (this.mVerticalStyle == -1) {
                this.mVerticalStyle = 0;
            }
        }
        ConstraintWidget[] constraintWidgetArr = this.mWidgets;
        int i7 = 0;
        for (int i8 = 0; i8 < this.mWidgetsCount; i8++) {
            if (this.mWidgets[i8].getVisibility() == 8) {
                i7++;
            }
        }
        int i9 = this.mWidgetsCount;
        if (i7 > 0) {
            constraintWidgetArr = new ConstraintWidget[this.mWidgetsCount - i7];
            int i10 = 0;
            for (int i11 = 0; i11 < this.mWidgetsCount; i11++) {
                ConstraintWidget constraintWidget = this.mWidgets[i11];
                if (constraintWidget.getVisibility() != 8) {
                    constraintWidgetArr[i10] = constraintWidget;
                    i10++;
                }
            }
            i9 = i10;
        }
        this.mDisplayedWidgets = constraintWidgetArr;
        this.mDisplayedWidgetsCount = i9;
        int i12 = this.mWrapMode;
        if (i12 == 0) {
            iArr = iArr2;
            z = true;
            measureNoWrap(constraintWidgetArr, i9, this.mOrientation, i6, iArr2);
        } else if (i12 == 1) {
            z = true;
            iArr = iArr2;
            measureChainWrap(constraintWidgetArr, i9, this.mOrientation, i6, iArr2);
        } else if (i12 != 2) {
            z = true;
            iArr = iArr2;
        } else {
            z = true;
            iArr = iArr2;
            measureAligned(constraintWidgetArr, i9, this.mOrientation, i6, iArr2);
        }
        int i13 = iArr[0] + paddingLeft + paddingRight;
        int i14 = iArr[z ? 1 : 0] + paddingTop + paddingBottom;
        if (i == 1073741824) {
            i13 = i2;
        } else if (i == Integer.MIN_VALUE) {
            i13 = Math.min(i13, i2);
        } else if (i != 0) {
            i13 = 0;
        }
        if (i3 == 1073741824) {
            i14 = i4;
        } else if (i3 == Integer.MIN_VALUE) {
            i14 = Math.min(i14, i4);
        } else if (i3 != 0) {
            i14 = 0;
        }
        setMeasure(i13, i14);
        setWidth(i13);
        setHeight(i14);
        if (this.mWidgetsCount <= 0) {
            z = false;
        }
        needsCallbackFromSolver(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class WidgetsList {
        private ConstraintAnchor mBottom;
        private ConstraintAnchor mLeft;
        private int mMax;
        private int mOrientation;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private ConstraintAnchor mRight;
        private ConstraintAnchor mTop;
        private ConstraintWidget biggest = null;
        int biggestDimension = 0;
        private int mWidth = 0;
        private int mHeight = 0;
        private int mStartIndex = 0;
        private int mCount = 0;
        private int mNbMatchConstraintsWidgets = 0;

        public WidgetsList(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2) {
            this.mOrientation = 0;
            this.mPaddingLeft = 0;
            this.mPaddingTop = 0;
            this.mPaddingRight = 0;
            this.mPaddingBottom = 0;
            this.mMax = 0;
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = i2;
        }

        public void setup(int i, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, ConstraintAnchor constraintAnchor3, ConstraintAnchor constraintAnchor4, int i2, int i3, int i4, int i5, int i6) {
            this.mOrientation = i;
            this.mLeft = constraintAnchor;
            this.mTop = constraintAnchor2;
            this.mRight = constraintAnchor3;
            this.mBottom = constraintAnchor4;
            this.mPaddingLeft = i2;
            this.mPaddingTop = i3;
            this.mPaddingRight = i4;
            this.mPaddingBottom = i5;
            this.mMax = i6;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void setStartIndex(int i) {
            this.mStartIndex = i;
        }

        public int getWidth() {
            if (this.mOrientation == 0) {
                return this.mWidth - Flow.this.mHorizontalGap;
            }
            return this.mWidth;
        }

        public int getHeight() {
            if (this.mOrientation == 1) {
                return this.mHeight - Flow.this.mVerticalGap;
            }
            return this.mHeight;
        }

        public void add(ConstraintWidget constraintWidget) {
            if (this.mOrientation == 0) {
                int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetWidth = 0;
                }
                this.mWidth += widgetWidth + (constraintWidget.getVisibility() != 8 ? Flow.this.mHorizontalGap : 0);
                int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (this.biggest == null || this.biggestDimension < widgetHeight) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = widgetHeight;
                    this.mHeight = widgetHeight;
                }
            } else {
                int widgetWidth2 = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                if (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    widgetHeight2 = 0;
                }
                this.mHeight += widgetHeight2 + (constraintWidget.getVisibility() != 8 ? Flow.this.mVerticalGap : 0);
                if (this.biggest == null || this.biggestDimension < widgetWidth2) {
                    this.biggest = constraintWidget;
                    this.biggestDimension = widgetWidth2;
                    this.mWidth = widgetWidth2;
                }
            }
            this.mCount++;
        }

        public void createConstraints(boolean z, int i, boolean z2) {
            ConstraintWidget constraintWidget;
            char c;
            float f;
            float f2;
            int i2 = this.mCount;
            for (int i3 = 0; i3 < i2 && this.mStartIndex + i3 < Flow.this.mDisplayedWidgetsCount; i3++) {
                ConstraintWidget constraintWidget2 = Flow.this.mDisplayedWidgets[this.mStartIndex + i3];
                if (constraintWidget2 != null) {
                    constraintWidget2.resetAnchors();
                }
            }
            if (i2 == 0 || this.biggest == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i4 = -1;
            int i5 = -1;
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = z ? (i2 - 1) - i6 : i6;
                if (this.mStartIndex + i7 >= Flow.this.mDisplayedWidgetsCount) {
                    break;
                }
                ConstraintWidget constraintWidget3 = Flow.this.mDisplayedWidgets[this.mStartIndex + i7];
                if (constraintWidget3 != null && constraintWidget3.getVisibility() == 0) {
                    if (i4 == -1) {
                        i4 = i6;
                    }
                    i5 = i6;
                }
            }
            if (this.mOrientation == 0) {
                ConstraintWidget constraintWidget4 = this.biggest;
                constraintWidget4.setVerticalChainStyle(Flow.this.mVerticalStyle);
                int i8 = this.mPaddingTop;
                if (i > 0) {
                    i8 += Flow.this.mVerticalGap;
                }
                constraintWidget4.mTop.connect(this.mTop, i8);
                if (z2) {
                    constraintWidget4.mBottom.connect(this.mBottom, this.mPaddingBottom);
                }
                if (i > 0) {
                    this.mTop.mOwner.mBottom.connect(constraintWidget4.mTop, 0);
                }
                char c2 = 3;
                if (Flow.this.mVerticalAlign == 3 && !constraintWidget4.hasBaseline()) {
                    for (int i9 = 0; i9 < i2; i9++) {
                        int i10 = z ? (i2 - 1) - i9 : i9;
                        if (this.mStartIndex + i10 >= Flow.this.mDisplayedWidgetsCount) {
                            break;
                        }
                        constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i10];
                        if (constraintWidget.hasBaseline()) {
                            break;
                        }
                    }
                }
                constraintWidget = constraintWidget4;
                ConstraintWidget constraintWidget5 = null;
                int i11 = 0;
                while (i11 < i2) {
                    int i12 = z ? (i2 - 1) - i11 : i11;
                    if (this.mStartIndex + i12 >= Flow.this.mDisplayedWidgetsCount) {
                        return;
                    }
                    ConstraintWidget constraintWidget6 = Flow.this.mDisplayedWidgets[this.mStartIndex + i12];
                    if (constraintWidget6 == null) {
                        constraintWidget6 = constraintWidget5;
                        c = c2;
                    } else {
                        if (i11 == 0) {
                            constraintWidget6.connect(constraintWidget6.mLeft, this.mLeft, this.mPaddingLeft);
                        }
                        if (i12 == 0) {
                            int i13 = Flow.this.mHorizontalStyle;
                            float f3 = Flow.this.mHorizontalBias;
                            if (z) {
                                f3 = 1.0f - f3;
                            }
                            if (this.mStartIndex != 0 || Flow.this.mFirstHorizontalStyle == -1) {
                                if (z2 && Flow.this.mLastHorizontalStyle != -1) {
                                    i13 = Flow.this.mLastHorizontalStyle;
                                    if (z) {
                                        f2 = Flow.this.mLastHorizontalBias;
                                        f = 1.0f - f2;
                                        f3 = f;
                                    } else {
                                        f = Flow.this.mLastHorizontalBias;
                                        f3 = f;
                                    }
                                }
                            } else {
                                i13 = Flow.this.mFirstHorizontalStyle;
                                if (z) {
                                    f2 = Flow.this.mFirstHorizontalBias;
                                    f = 1.0f - f2;
                                    f3 = f;
                                } else {
                                    f = Flow.this.mFirstHorizontalBias;
                                    f3 = f;
                                }
                            }
                            constraintWidget6.setHorizontalChainStyle(i13);
                            constraintWidget6.setHorizontalBiasPercent(f3);
                        }
                        if (i11 == i2 - 1) {
                            constraintWidget6.connect(constraintWidget6.mRight, this.mRight, this.mPaddingRight);
                        }
                        if (constraintWidget5 != null) {
                            constraintWidget6.mLeft.connect(constraintWidget5.mRight, Flow.this.mHorizontalGap);
                            if (i11 == i4) {
                                constraintWidget6.mLeft.setGoneMargin(this.mPaddingLeft);
                            }
                            constraintWidget5.mRight.connect(constraintWidget6.mLeft, 0);
                            if (i11 == i5 + 1) {
                                constraintWidget5.mRight.setGoneMargin(this.mPaddingRight);
                            }
                        }
                        if (constraintWidget6 != constraintWidget4) {
                            c = 3;
                            if (Flow.this.mVerticalAlign != 3 || !constraintWidget.hasBaseline() || constraintWidget6 == constraintWidget || !constraintWidget6.hasBaseline()) {
                                int i14 = Flow.this.mVerticalAlign;
                                if (i14 == 0) {
                                    constraintWidget6.mTop.connect(constraintWidget4.mTop, 0);
                                } else if (i14 == 1) {
                                    constraintWidget6.mBottom.connect(constraintWidget4.mBottom, 0);
                                } else if (z3) {
                                    constraintWidget6.mTop.connect(this.mTop, this.mPaddingTop);
                                    constraintWidget6.mBottom.connect(this.mBottom, this.mPaddingBottom);
                                } else {
                                    constraintWidget6.mTop.connect(constraintWidget4.mTop, 0);
                                    constraintWidget6.mBottom.connect(constraintWidget4.mBottom, 0);
                                }
                            } else {
                                constraintWidget6.mBaseline.connect(constraintWidget.mBaseline, 0);
                            }
                        } else {
                            c = 3;
                        }
                    }
                    i11++;
                    c2 = c;
                    constraintWidget5 = constraintWidget6;
                }
                return;
            }
            ConstraintWidget constraintWidget7 = this.biggest;
            constraintWidget7.setHorizontalChainStyle(Flow.this.mHorizontalStyle);
            int i15 = this.mPaddingLeft;
            if (i > 0) {
                i15 += Flow.this.mHorizontalGap;
            }
            if (z) {
                constraintWidget7.mRight.connect(this.mRight, i15);
                if (z2) {
                    constraintWidget7.mLeft.connect(this.mLeft, this.mPaddingRight);
                }
                if (i > 0) {
                    this.mRight.mOwner.mLeft.connect(constraintWidget7.mRight, 0);
                }
            } else {
                constraintWidget7.mLeft.connect(this.mLeft, i15);
                if (z2) {
                    constraintWidget7.mRight.connect(this.mRight, this.mPaddingRight);
                }
                if (i > 0) {
                    this.mLeft.mOwner.mRight.connect(constraintWidget7.mLeft, 0);
                }
            }
            ConstraintWidget constraintWidget8 = null;
            for (int i16 = 0; i16 < i2 && this.mStartIndex + i16 < Flow.this.mDisplayedWidgetsCount; i16++) {
                ConstraintWidget constraintWidget9 = Flow.this.mDisplayedWidgets[this.mStartIndex + i16];
                if (constraintWidget9 != null) {
                    if (i16 == 0) {
                        constraintWidget9.connect(constraintWidget9.mTop, this.mTop, this.mPaddingTop);
                        int i17 = Flow.this.mVerticalStyle;
                        float f4 = Flow.this.mVerticalBias;
                        if (this.mStartIndex != 0 || Flow.this.mFirstVerticalStyle == -1) {
                            if (z2 && Flow.this.mLastVerticalStyle != -1) {
                                i17 = Flow.this.mLastVerticalStyle;
                                f4 = Flow.this.mLastVerticalBias;
                            }
                        } else {
                            i17 = Flow.this.mFirstVerticalStyle;
                            f4 = Flow.this.mFirstVerticalBias;
                        }
                        constraintWidget9.setVerticalChainStyle(i17);
                        constraintWidget9.setVerticalBiasPercent(f4);
                    }
                    if (i16 == i2 - 1) {
                        constraintWidget9.connect(constraintWidget9.mBottom, this.mBottom, this.mPaddingBottom);
                    }
                    if (constraintWidget8 != null) {
                        constraintWidget9.mTop.connect(constraintWidget8.mBottom, Flow.this.mVerticalGap);
                        if (i16 == i4) {
                            constraintWidget9.mTop.setGoneMargin(this.mPaddingTop);
                        }
                        constraintWidget8.mBottom.connect(constraintWidget9.mTop, 0);
                        if (i16 == i5 + 1) {
                            constraintWidget8.mBottom.setGoneMargin(this.mPaddingBottom);
                        }
                    }
                    if (constraintWidget9 != constraintWidget7) {
                        if (z) {
                            int i18 = Flow.this.mHorizontalAlign;
                            if (i18 == 0) {
                                constraintWidget9.mRight.connect(constraintWidget7.mRight, 0);
                            } else if (i18 == 1) {
                                constraintWidget9.mLeft.connect(constraintWidget7.mLeft, 0);
                            } else if (i18 == 2) {
                                constraintWidget9.mLeft.connect(constraintWidget7.mLeft, 0);
                                constraintWidget9.mRight.connect(constraintWidget7.mRight, 0);
                            }
                        } else {
                            int i19 = Flow.this.mHorizontalAlign;
                            if (i19 == 0) {
                                constraintWidget9.mLeft.connect(constraintWidget7.mLeft, 0);
                            } else if (i19 == 1) {
                                constraintWidget9.mRight.connect(constraintWidget7.mRight, 0);
                            } else if (i19 == 2) {
                                if (z3) {
                                    constraintWidget9.mLeft.connect(this.mLeft, this.mPaddingLeft);
                                    constraintWidget9.mRight.connect(this.mRight, this.mPaddingRight);
                                } else {
                                    constraintWidget9.mLeft.connect(constraintWidget7.mLeft, 0);
                                    constraintWidget9.mRight.connect(constraintWidget7.mRight, 0);
                                }
                            }
                            constraintWidget8 = constraintWidget9;
                        }
                    }
                    constraintWidget8 = constraintWidget9;
                }
            }
        }

        public void measureMatchConstraints(int i) {
            int i2 = this.mNbMatchConstraintsWidgets;
            if (i2 == 0) {
                return;
            }
            int i3 = this.mCount;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3 && this.mStartIndex + i5 < Flow.this.mDisplayedWidgetsCount; i5++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i5];
                if (this.mOrientation == 0) {
                    if (constraintWidget != null && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                        Flow.this.measure(constraintWidget, ConstraintWidget.DimensionBehaviour.FIXED, i4, constraintWidget.getVerticalDimensionBehaviour(), constraintWidget.getHeight());
                    }
                } else if (constraintWidget != null && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                    Flow.this.measure(constraintWidget, constraintWidget.getHorizontalDimensionBehaviour(), constraintWidget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, i4);
                }
            }
            recomputeDimensions();
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.biggestDimension = 0;
            int i = this.mCount;
            for (int i2 = 0; i2 < i && this.mStartIndex + i2 < Flow.this.mDisplayedWidgetsCount; i2++) {
                ConstraintWidget constraintWidget = Flow.this.mDisplayedWidgets[this.mStartIndex + i2];
                if (this.mOrientation != 0) {
                    int widgetWidth = Flow.this.getWidgetWidth(constraintWidget, this.mMax);
                    int widgetHeight = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    int i3 = Flow.this.mVerticalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i3 = 0;
                    }
                    this.mHeight += widgetHeight + i3;
                    if (this.biggest == null || this.biggestDimension < widgetWidth) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = widgetWidth;
                        this.mWidth = widgetWidth;
                    }
                } else {
                    int width = constraintWidget.getWidth();
                    int i4 = Flow.this.mHorizontalGap;
                    if (constraintWidget.getVisibility() == 8) {
                        i4 = 0;
                    }
                    this.mWidth += width + i4;
                    int widgetHeight2 = Flow.this.getWidgetHeight(constraintWidget, this.mMax);
                    if (this.biggest == null || this.biggestDimension < widgetHeight2) {
                        this.biggest = constraintWidget;
                        this.biggestDimension = widgetHeight2;
                        this.mHeight = widgetHeight2;
                    }
                }
            }
        }
    }

    private void measureChainWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        if (i == 0) {
            return;
        }
        this.mChainList.clear();
        WidgetsList widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
        this.mChainList.add(widgetsList);
        if (i2 == 0) {
            WidgetsList widgetsList2 = widgetsList;
            i4 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i8 < i) {
                ConstraintWidget constraintWidget = constraintWidgetArr[i8];
                int widgetWidth = getWidgetWidth(constraintWidget, i3);
                if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i9 = i4;
                boolean z = (i7 == i3 || (this.mHorizontalGap + i7) + widgetWidth > i3) && widgetsList2.biggest != null;
                if (!z && i8 > 0 && (i6 = this.mMaxElementsWrap) > 0 && i8 % i6 == 0) {
                    z = true;
                }
                if (z) {
                    WidgetsList widgetsList3 = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    widgetsList3.setStartIndex(i8);
                    this.mChainList.add(widgetsList3);
                    i7 = widgetWidth;
                    widgetsList2 = widgetsList3;
                } else {
                    i7 = i8 > 0 ? i7 + this.mHorizontalGap + widgetWidth : widgetWidth;
                }
                widgetsList2.add(constraintWidget);
                i8++;
                i4 = i9;
            }
        } else {
            WidgetsList widgetsList4 = widgetsList;
            i4 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i11 < i) {
                ConstraintWidget constraintWidget2 = constraintWidgetArr[i11];
                int widgetHeight = getWidgetHeight(constraintWidget2, i3);
                if (constraintWidget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i4++;
                }
                int i12 = i4;
                boolean z2 = (i10 == i3 || (this.mVerticalGap + i10) + widgetHeight > i3) && widgetsList4.biggest != null;
                if (!z2 && i11 > 0 && (i5 = this.mMaxElementsWrap) > 0 && i11 % i5 == 0) {
                    z2 = true;
                }
                if (z2) {
                    WidgetsList widgetsList5 = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
                    widgetsList5.setStartIndex(i11);
                    this.mChainList.add(widgetsList5);
                    i10 = widgetHeight;
                    widgetsList4 = widgetsList5;
                } else {
                    i10 = i11 > 0 ? i10 + this.mVerticalGap + widgetHeight : widgetHeight;
                }
                widgetsList4.add(constraintWidget2);
                i11++;
                i4 = i12;
            }
        }
        int size = this.mChainList.size();
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = this.mTop;
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = this.mBottom;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        boolean z3 = getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i4 > 0 && z3) {
            for (int i13 = 0; i13 < size; i13++) {
                WidgetsList widgetsList6 = this.mChainList.get(i13);
                if (i2 == 0) {
                    widgetsList6.measureMatchConstraints(i3 - widgetsList6.getWidth());
                } else {
                    widgetsList6.measureMatchConstraints(i3 - widgetsList6.getHeight());
                }
            }
        }
        ConstraintAnchor constraintAnchor5 = constraintAnchor3;
        int i14 = paddingTop;
        int i15 = paddingRight;
        int i16 = paddingBottom;
        ConstraintAnchor constraintAnchor6 = constraintAnchor;
        int i17 = paddingLeft;
        int i18 = 0;
        ConstraintAnchor constraintAnchor7 = constraintAnchor4;
        ConstraintAnchor constraintAnchor8 = constraintAnchor2;
        int i19 = 0;
        for (int i20 = 0; i20 < size; i20++) {
            WidgetsList widgetsList7 = this.mChainList.get(i20);
            if (i2 == 0) {
                if (i20 < size - 1) {
                    constraintAnchor7 = this.mChainList.get(i20 + 1).biggest.mTop;
                    i16 = 0;
                } else {
                    constraintAnchor7 = this.mBottom;
                    i16 = getPaddingBottom();
                }
                constraintAnchor8 = widgetsList7.biggest.mBottom;
                widgetsList7.setup(i2, constraintAnchor6, constraintAnchor8, constraintAnchor5, constraintAnchor7, i17, i14, i15, i16, i3);
                i18 = Math.max(i18, widgetsList7.getWidth());
                i19 += widgetsList7.getHeight();
                if (i20 > 0) {
                    i19 += this.mVerticalGap;
                }
                i14 = 0;
            } else {
                if (i20 < size - 1) {
                    constraintAnchor5 = this.mChainList.get(i20 + 1).biggest.mLeft;
                    i15 = 0;
                } else {
                    constraintAnchor5 = this.mRight;
                    i15 = getPaddingRight();
                }
                constraintAnchor6 = widgetsList7.biggest.mRight;
                widgetsList7.setup(i2, constraintAnchor6, constraintAnchor8, constraintAnchor5, constraintAnchor7, i17, i14, i15, i16, i3);
                i18 += widgetsList7.getWidth();
                i19 = Math.max(i19, widgetsList7.getHeight());
                if (i20 > 0) {
                    i18 += this.mHorizontalGap;
                }
                i17 = 0;
            }
        }
        iArr[0] = i18;
        iArr[1] = i19;
    }

    private void measureNoWrap(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        WidgetsList widgetsList;
        if (i == 0) {
            return;
        }
        if (this.mChainList.size() == 0) {
            widgetsList = new WidgetsList(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, i3);
            this.mChainList.add(widgetsList);
        } else {
            WidgetsList widgetsList2 = this.mChainList.get(0);
            widgetsList2.clear();
            widgetsList = widgetsList2;
            widgetsList.setup(i2, this.mLeft, this.mTop, this.mRight, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), i3);
        }
        for (int i4 = 0; i4 < i; i4++) {
            widgetsList.add(constraintWidgetArr[i4]);
        }
        iArr[0] = widgetsList.getWidth();
        iArr[1] = widgetsList.getHeight();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x011e -> B:23:0x0065). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:79:0x0120 -> B:23:0x0065). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0126 -> B:23:0x0065). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x0128 -> B:23:0x0065). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void measureAligned(ConstraintWidget[] constraintWidgetArr, int i, int i2, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6;
        boolean z;
        ConstraintWidget constraintWidget;
        if (i2 == 0) {
            int i7 = this.mMaxElementsWrap;
            if (i7 <= 0) {
                int i8 = 0;
                i5 = 0;
                for (int i9 = 0; i9 < i; i9++) {
                    if (i9 > 0) {
                        i8 += this.mHorizontalGap;
                    }
                    ConstraintWidget constraintWidget2 = constraintWidgetArr[i9];
                    if (constraintWidget2 != null) {
                        i8 += getWidgetWidth(constraintWidget2, i3);
                        if (i8 > i3) {
                            break;
                        } else {
                            i5++;
                        }
                    }
                }
            } else {
                i5 = i7;
            }
            i4 = 0;
        } else {
            i4 = this.mMaxElementsWrap;
            if (i4 <= 0) {
                int i10 = 0;
                int i11 = 0;
                for (int i12 = 0; i12 < i; i12++) {
                    if (i12 > 0) {
                        i10 += this.mVerticalGap;
                    }
                    ConstraintWidget constraintWidget3 = constraintWidgetArr[i12];
                    if (constraintWidget3 != null) {
                        i10 += getWidgetHeight(constraintWidget3, i3);
                        if (i10 > i3) {
                            break;
                        } else {
                            i11++;
                        }
                    }
                }
                i4 = i11;
            }
            i5 = 0;
        }
        if (this.mAlignedDimensions == null) {
            this.mAlignedDimensions = new int[2];
        }
        if ((i4 != 0 || i2 != 1) && (i5 != 0 || i2 != 0)) {
            i6 = i4;
            z = false;
            while (!z) {
                if (i2 == 0) {
                    i6 = (int) Math.ceil(i / i5);
                } else {
                    i5 = (int) Math.ceil(i / i6);
                }
                ConstraintWidget[] constraintWidgetArr2 = this.mAlignedBiggestElementsInCols;
                if (constraintWidgetArr2 == null || constraintWidgetArr2.length < i5) {
                    this.mAlignedBiggestElementsInCols = new ConstraintWidget[i5];
                } else {
                    Arrays.fill(constraintWidgetArr2, (Object) null);
                }
                ConstraintWidget[] constraintWidgetArr3 = this.mAlignedBiggestElementsInRows;
                if (constraintWidgetArr3 == null || constraintWidgetArr3.length < i6) {
                    this.mAlignedBiggestElementsInRows = new ConstraintWidget[i6];
                } else {
                    Arrays.fill(constraintWidgetArr3, (Object) null);
                }
                for (int i13 = 0; i13 < i5; i13++) {
                    for (int i14 = 0; i14 < i6; i14++) {
                        int i15 = (i14 * i5) + i13;
                        if (i2 == 1) {
                            i15 = (i13 * i6) + i14;
                        }
                        if (i15 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i15]) != null) {
                            int widgetWidth = getWidgetWidth(constraintWidget, i3);
                            ConstraintWidget[] constraintWidgetArr4 = this.mAlignedBiggestElementsInCols;
                            if (constraintWidgetArr4[i13] == null || constraintWidgetArr4[i13].getWidth() < widgetWidth) {
                                this.mAlignedBiggestElementsInCols[i13] = constraintWidget;
                            }
                            int widgetHeight = getWidgetHeight(constraintWidget, i3);
                            ConstraintWidget[] constraintWidgetArr5 = this.mAlignedBiggestElementsInRows;
                            if (constraintWidgetArr5[i14] == null || constraintWidgetArr5[i14].getHeight() < widgetHeight) {
                                this.mAlignedBiggestElementsInRows[i14] = constraintWidget;
                            }
                        }
                    }
                }
                int i16 = 0;
                for (int i17 = 0; i17 < i5; i17++) {
                    ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInCols[i17];
                    if (constraintWidget4 != null) {
                        if (i17 > 0) {
                            i16 += this.mHorizontalGap;
                        }
                        i16 += getWidgetWidth(constraintWidget4, i3);
                    }
                }
                int i18 = 0;
                for (int i19 = 0; i19 < i6; i19++) {
                    ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInRows[i19];
                    if (constraintWidget5 != null) {
                        if (i19 > 0) {
                            i18 += this.mVerticalGap;
                        }
                        i18 += getWidgetHeight(constraintWidget5, i3);
                    }
                }
                iArr[0] = i16;
                iArr[1] = i18;
                if (i2 != 0) {
                    if (i18 > i3 && i6 > 1) {
                        i6--;
                    }
                    z = true;
                } else {
                    if (i16 > i3 && i5 > 1) {
                        i5--;
                    }
                    z = true;
                }
                while (!z) {
                }
            }
            int[] iArr2 = this.mAlignedDimensions;
            iArr2[0] = i5;
            iArr2[1] = i6;
        }
        i6 = i4;
        z = true;
        while (!z) {
        }
        int[] iArr22 = this.mAlignedDimensions;
        iArr22[0] = i5;
        iArr22[1] = i6;
    }

    private void createAlignedConstraints(boolean z) {
        ConstraintWidget constraintWidget;
        float f;
        int i;
        if (this.mAlignedDimensions == null || this.mAlignedBiggestElementsInCols == null || this.mAlignedBiggestElementsInRows == null) {
            return;
        }
        for (int i2 = 0; i2 < this.mDisplayedWidgetsCount; i2++) {
            this.mDisplayedWidgets[i2].resetAnchors();
        }
        int[] iArr = this.mAlignedDimensions;
        int i3 = iArr[0];
        int i4 = iArr[1];
        float f2 = this.mHorizontalBias;
        ConstraintWidget constraintWidget2 = null;
        int i5 = 0;
        while (i5 < i3) {
            if (z) {
                i = (i3 - i5) - 1;
                f = 1.0f - this.mHorizontalBias;
            } else {
                f = f2;
                i = i5;
            }
            ConstraintWidget constraintWidget3 = this.mAlignedBiggestElementsInCols[i];
            if (constraintWidget3 != null && constraintWidget3.getVisibility() != 8) {
                if (i5 == 0) {
                    constraintWidget3.connect(constraintWidget3.mLeft, this.mLeft, getPaddingLeft());
                    constraintWidget3.setHorizontalChainStyle(this.mHorizontalStyle);
                    constraintWidget3.setHorizontalBiasPercent(f);
                }
                if (i5 == i3 - 1) {
                    constraintWidget3.connect(constraintWidget3.mRight, this.mRight, getPaddingRight());
                }
                if (i5 > 0 && constraintWidget2 != null) {
                    constraintWidget3.connect(constraintWidget3.mLeft, constraintWidget2.mRight, this.mHorizontalGap);
                    constraintWidget2.connect(constraintWidget2.mRight, constraintWidget3.mLeft, 0);
                }
                constraintWidget2 = constraintWidget3;
            }
            i5++;
            f2 = f;
        }
        for (int i6 = 0; i6 < i4; i6++) {
            ConstraintWidget constraintWidget4 = this.mAlignedBiggestElementsInRows[i6];
            if (constraintWidget4 != null && constraintWidget4.getVisibility() != 8) {
                if (i6 == 0) {
                    constraintWidget4.connect(constraintWidget4.mTop, this.mTop, getPaddingTop());
                    constraintWidget4.setVerticalChainStyle(this.mVerticalStyle);
                    constraintWidget4.setVerticalBiasPercent(this.mVerticalBias);
                }
                if (i6 == i4 - 1) {
                    constraintWidget4.connect(constraintWidget4.mBottom, this.mBottom, getPaddingBottom());
                }
                if (i6 > 0 && constraintWidget2 != null) {
                    constraintWidget4.connect(constraintWidget4.mTop, constraintWidget2.mBottom, this.mVerticalGap);
                    constraintWidget2.connect(constraintWidget2.mBottom, constraintWidget4.mTop, 0);
                }
                constraintWidget2 = constraintWidget4;
            }
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i4; i8++) {
                int i9 = (i8 * i3) + i7;
                if (this.mOrientation == 1) {
                    i9 = (i7 * i4) + i8;
                }
                ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                if (i9 < constraintWidgetArr.length && (constraintWidget = constraintWidgetArr[i9]) != null && constraintWidget.getVisibility() != 8) {
                    ConstraintWidget constraintWidget5 = this.mAlignedBiggestElementsInCols[i7];
                    ConstraintWidget constraintWidget6 = this.mAlignedBiggestElementsInRows[i8];
                    if (constraintWidget != constraintWidget5) {
                        constraintWidget.connect(constraintWidget.mLeft, constraintWidget5.mLeft, 0);
                        constraintWidget.connect(constraintWidget.mRight, constraintWidget5.mRight, 0);
                    }
                    if (constraintWidget != constraintWidget6) {
                        constraintWidget.connect(constraintWidget.mTop, constraintWidget6.mTop, 0);
                        constraintWidget.connect(constraintWidget.mBottom, constraintWidget6.mBottom, 0);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        boolean z2 = getParent() != null && ((ConstraintWidgetContainer) getParent()).isRtl();
        int i = this.mWrapMode;
        if (i != 0) {
            if (i == 1) {
                int size = this.mChainList.size();
                int i2 = 0;
                while (i2 < size) {
                    this.mChainList.get(i2).createConstraints(z2, i2, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2) {
                createAlignedConstraints(z2);
            }
        } else if (this.mChainList.size() > 0) {
            this.mChainList.get(0).createConstraints(z2, 0, true);
        }
        needsCallbackFromSolver(false);
    }
}
