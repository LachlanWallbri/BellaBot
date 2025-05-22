package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.widgets = new ArrayList<>();
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            sb.append("<");
            sb.append(next);
            sb.append("> ");
        }
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            j = j + r4.start.margin + this.widgets.get(i).getWrapDimension() + r4.end.margin;
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
            }
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:284:0x03f1, code lost:
    
        r2 = r2 - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00cd, code lost:
    
        if (r3.dimension.resolved != false) goto L57;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void update(Dependency dependency) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        float f2;
        int i11;
        int i12;
        int i13;
        if (this.start.resolved && this.end.resolved) {
            ConstraintWidget parent = this.widget.getParent();
            boolean isRtl = parent instanceof ConstraintWidgetContainer ? ((ConstraintWidgetContainer) parent).isRtl() : false;
            int i14 = this.end.value - this.start.value;
            int size = this.widgets.size();
            int i15 = 0;
            while (true) {
                i = -1;
                i2 = 8;
                if (i15 >= size) {
                    i15 = -1;
                    break;
                } else if (this.widgets.get(i15).widget.getVisibility() != 8) {
                    break;
                } else {
                    i15++;
                }
            }
            int i16 = size - 1;
            int i17 = i16;
            while (true) {
                if (i17 < 0) {
                    break;
                }
                if (this.widgets.get(i17).widget.getVisibility() != 8) {
                    i = i17;
                    break;
                }
                i17--;
            }
            int i18 = 0;
            while (i18 < 2) {
                int i19 = 0;
                i4 = 0;
                i5 = 0;
                int i20 = 0;
                f = 0.0f;
                while (i19 < size) {
                    WidgetRun widgetRun = this.widgets.get(i19);
                    if (widgetRun.widget.getVisibility() != i2) {
                        i20++;
                        if (i19 > 0 && i19 >= i15) {
                            i4 += widgetRun.start.margin;
                        }
                        int i21 = widgetRun.dimension.value;
                        boolean z = widgetRun.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (z) {
                            if (this.orientation == 0 && !widgetRun.widget.horizontalRun.dimension.resolved) {
                                return;
                            }
                            if (this.orientation == 1 && !widgetRun.widget.verticalRun.dimension.resolved) {
                                return;
                            }
                        } else {
                            if (widgetRun.matchConstraintsType == 1 && i18 == 0) {
                                i21 = widgetRun.dimension.wrapValue;
                                i5++;
                            }
                            z = true;
                        }
                        if (z) {
                            i4 += i21;
                        } else {
                            i5++;
                            float f3 = widgetRun.widget.mWeight[this.orientation];
                            if (f3 >= 0.0f) {
                                f += f3;
                            }
                        }
                        if (i19 < i16 && i19 < i) {
                            i4 += -widgetRun.end.margin;
                        }
                    }
                    i19++;
                    i2 = 8;
                }
                if (i4 < i14 || i5 == 0) {
                    i3 = i20;
                    break;
                } else {
                    i18++;
                    i2 = 8;
                }
            }
            i3 = 0;
            i4 = 0;
            i5 = 0;
            f = 0.0f;
            int i22 = this.start.value;
            if (isRtl) {
                i22 = this.end.value;
            }
            if (i4 > i14) {
                i22 = isRtl ? i22 + ((int) (((i4 - i14) / 2.0f) + 0.5f)) : i22 - ((int) (((i4 - i14) / 2.0f) + 0.5f));
            }
            if (i5 > 0) {
                float f4 = i14 - i4;
                int i23 = (int) ((f4 / i5) + 0.5f);
                int i24 = 0;
                int i25 = 0;
                while (i24 < size) {
                    WidgetRun widgetRun2 = this.widgets.get(i24);
                    int i26 = i23;
                    if (widgetRun2.widget.getVisibility() == 8 || widgetRun2.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widgetRun2.dimension.resolved) {
                        i10 = i22;
                        f2 = f4;
                        i11 = i4;
                    } else {
                        int i27 = f > 0.0f ? (int) (((widgetRun2.widget.mWeight[this.orientation] * f4) / f) + 0.5f) : i26;
                        if (this.orientation == 0) {
                            i12 = widgetRun2.widget.mMatchConstraintMaxWidth;
                            i13 = widgetRun2.widget.mMatchConstraintMinWidth;
                        } else {
                            i12 = widgetRun2.widget.mMatchConstraintMaxHeight;
                            i13 = widgetRun2.widget.mMatchConstraintMinHeight;
                        }
                        i11 = i4;
                        float f5 = f4;
                        int i28 = i13;
                        int i29 = i12;
                        f2 = f5;
                        i10 = i22;
                        int max = Math.max(i28, widgetRun2.matchConstraintsType == 1 ? Math.min(i27, widgetRun2.dimension.wrapValue) : i27);
                        if (i29 > 0) {
                            max = Math.min(i29, max);
                        }
                        if (max != i27) {
                            i25++;
                        } else {
                            max = i27;
                        }
                        widgetRun2.dimension.resolve(max);
                    }
                    i24++;
                    i23 = i26;
                    f4 = f2;
                    i4 = i11;
                    i22 = i10;
                }
                i6 = i22;
                int i30 = i4;
                if (i25 > 0) {
                    i5 -= i25;
                    int i31 = 0;
                    for (int i32 = 0; i32 < size; i32++) {
                        WidgetRun widgetRun3 = this.widgets.get(i32);
                        if (widgetRun3.widget.getVisibility() != 8) {
                            if (i32 > 0 && i32 >= i15) {
                                i31 += widgetRun3.start.margin;
                            }
                            i31 += widgetRun3.dimension.value;
                            if (i32 < i16 && i32 < i) {
                                i31 += -widgetRun3.end.margin;
                            }
                        }
                    }
                    i4 = i31;
                } else {
                    i4 = i30;
                }
                i8 = 2;
                if (this.chainStyle == 2 && i25 == 0) {
                    i7 = 0;
                    this.chainStyle = 0;
                } else {
                    i7 = 0;
                }
            } else {
                i6 = i22;
                i7 = 0;
                i8 = 2;
            }
            if (i4 > i14) {
                this.chainStyle = i8;
            }
            if (i3 > 0 && i5 == 0 && i15 == i) {
                this.chainStyle = i8;
            }
            int i33 = this.chainStyle;
            if (i33 == 1) {
                if (i3 > 1) {
                    i9 = (i14 - i4) / (i3 - 1);
                } else {
                    i9 = i3 == 1 ? (i14 - i4) / 2 : i7;
                }
                if (i5 > 0) {
                    i9 = i7;
                }
                int i34 = i6;
                while (i7 < size) {
                    WidgetRun widgetRun4 = this.widgets.get(isRtl ? size - (i7 + 1) : i7);
                    if (widgetRun4.widget.getVisibility() == 8) {
                        widgetRun4.start.resolve(i34);
                        widgetRun4.end.resolve(i34);
                    } else {
                        if (i7 > 0) {
                            i34 = isRtl ? i34 - i9 : i34 + i9;
                        }
                        if (i7 > 0 && i7 >= i15) {
                            if (isRtl) {
                                i34 -= widgetRun4.start.margin;
                            } else {
                                i34 += widgetRun4.start.margin;
                            }
                        }
                        if (isRtl) {
                            widgetRun4.end.resolve(i34);
                        } else {
                            widgetRun4.start.resolve(i34);
                        }
                        int i35 = widgetRun4.dimension.value;
                        if (widgetRun4.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun4.matchConstraintsType == 1) {
                            i35 = widgetRun4.dimension.wrapValue;
                        }
                        i34 = isRtl ? i34 - i35 : i34 + i35;
                        if (isRtl) {
                            widgetRun4.start.resolve(i34);
                        } else {
                            widgetRun4.end.resolve(i34);
                        }
                        widgetRun4.resolved = true;
                        if (i7 < i16 && i7 < i) {
                            if (isRtl) {
                                i34 -= -widgetRun4.end.margin;
                            } else {
                                i34 += -widgetRun4.end.margin;
                            }
                        }
                    }
                    i7++;
                }
                return;
            }
            if (i33 == 0) {
                int i36 = (i14 - i4) / (i3 + 1);
                if (i5 > 0) {
                    i36 = i7;
                }
                int i37 = i6;
                while (i7 < size) {
                    WidgetRun widgetRun5 = this.widgets.get(isRtl ? size - (i7 + 1) : i7);
                    if (widgetRun5.widget.getVisibility() == 8) {
                        widgetRun5.start.resolve(i37);
                        widgetRun5.end.resolve(i37);
                    } else {
                        int i38 = isRtl ? i37 - i36 : i37 + i36;
                        if (i7 > 0 && i7 >= i15) {
                            if (isRtl) {
                                i38 -= widgetRun5.start.margin;
                            } else {
                                i38 += widgetRun5.start.margin;
                            }
                        }
                        if (isRtl) {
                            widgetRun5.end.resolve(i38);
                        } else {
                            widgetRun5.start.resolve(i38);
                        }
                        int i39 = widgetRun5.dimension.value;
                        if (widgetRun5.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun5.matchConstraintsType == 1) {
                            i39 = Math.min(i39, widgetRun5.dimension.wrapValue);
                        }
                        i37 = isRtl ? i38 - i39 : i38 + i39;
                        if (isRtl) {
                            widgetRun5.start.resolve(i37);
                        } else {
                            widgetRun5.end.resolve(i37);
                        }
                        if (i7 < i16 && i7 < i) {
                            if (isRtl) {
                                i37 -= -widgetRun5.end.margin;
                            } else {
                                i37 += -widgetRun5.end.margin;
                            }
                        }
                    }
                    i7++;
                }
                return;
            }
            if (i33 == 2) {
                float horizontalBiasPercent = this.orientation == 0 ? this.widget.getHorizontalBiasPercent() : this.widget.getVerticalBiasPercent();
                if (isRtl) {
                    horizontalBiasPercent = 1.0f - horizontalBiasPercent;
                }
                int i40 = (int) (((i14 - i4) * horizontalBiasPercent) + 0.5f);
                if (i40 < 0 || i5 > 0) {
                    i40 = i7;
                }
                int i41 = isRtl ? i6 - i40 : i6 + i40;
                while (i7 < size) {
                    WidgetRun widgetRun6 = this.widgets.get(isRtl ? size - (i7 + 1) : i7);
                    if (widgetRun6.widget.getVisibility() == 8) {
                        widgetRun6.start.resolve(i41);
                        widgetRun6.end.resolve(i41);
                    } else {
                        if (i7 > 0 && i7 >= i15) {
                            if (isRtl) {
                                i41 -= widgetRun6.start.margin;
                            } else {
                                i41 += widgetRun6.start.margin;
                            }
                        }
                        if (isRtl) {
                            widgetRun6.end.resolve(i41);
                        } else {
                            widgetRun6.start.resolve(i41);
                        }
                        int i42 = widgetRun6.dimension.value;
                        if (widgetRun6.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun6.matchConstraintsType == 1) {
                            i42 = widgetRun6.dimension.wrapValue;
                        }
                        i41 += i42;
                        if (isRtl) {
                            widgetRun6.start.resolve(i41);
                        } else {
                            widgetRun6.end.resolve(i41);
                        }
                        if (i7 < i16 && i7 < i) {
                            if (isRtl) {
                                i41 -= -widgetRun6.end.margin;
                            } else {
                                i41 += -widgetRun6.end.margin;
                            }
                        }
                    }
                    i7++;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.widgets.get(0).widget;
        ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target = getTarget(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin = firstVisibleWidget.mLeft.getMargin();
            }
            if (target != null) {
                addTarget(this.start, target, margin);
            }
            DependencyNode target2 = getTarget(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin2 = lastVisibleWidget.mRight.getMargin();
            }
            if (target2 != null) {
                addTarget(this.end, target2, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target3 = getTarget(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (target3 != null) {
                addTarget(this.start, target3, margin3);
            }
            DependencyNode target4 = getTarget(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin4 = lastVisibleWidget2.mBottom.getMargin();
            }
            if (target4 != null) {
                addTarget(this.end, target4, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }
}
