package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        int i3;
        ChainHead[] chainHeadArr;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (r2.mHorizontalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x004c, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x004a, code lost:
    
        if (r2.mVerticalChainStyle == 2) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, int i2, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        ArrayList<ConstraintWidget> arrayList;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        int i3;
        ConstraintWidget constraintWidget2;
        int i4;
        ConstraintWidget constraintWidget3;
        ConstraintAnchor constraintAnchor4;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget4;
        ConstraintAnchor constraintAnchor5;
        ConstraintWidget constraintWidget5;
        SolverVariable solverVariable3;
        ConstraintWidget constraintWidget6;
        ConstraintWidget constraintWidget7;
        SolverVariable solverVariable4;
        float f;
        int size;
        int i5;
        float f2;
        float f3;
        ArrayList<ConstraintWidget> arrayList2;
        int i6;
        boolean z4;
        ConstraintWidget constraintWidget8;
        ConstraintWidget constraintWidget9;
        int i7;
        int i8 = i;
        ConstraintWidget constraintWidget10 = chainHead.mFirst;
        ConstraintWidget constraintWidget11 = chainHead.mLast;
        ConstraintWidget constraintWidget12 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget13 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget14 = chainHead.mHead;
        float f4 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget15 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget16 = chainHead.mLastMatchConstraintWidget;
        boolean z5 = constraintWidgetContainer.mListDimensionBehaviors[i8] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i8 == 0) {
            z = constraintWidget14.mHorizontalChainStyle == 0;
            z2 = constraintWidget14.mHorizontalChainStyle == 1;
        } else {
            z = constraintWidget14.mVerticalChainStyle == 0;
            z2 = constraintWidget14.mVerticalChainStyle == 1;
        }
        ConstraintWidget constraintWidget17 = constraintWidget10;
        boolean z6 = z2;
        boolean z7 = z;
        boolean z8 = z3;
        boolean z9 = false;
        while (true) {
            ConstraintWidget constraintWidget18 = null;
            if (z9) {
                break;
            }
            ConstraintAnchor constraintAnchor6 = constraintWidget17.mListAnchors[i2];
            int i9 = z8 ? 1 : 4;
            int margin = constraintAnchor6.getMargin();
            float f5 = f4;
            boolean z10 = z9;
            boolean z11 = constraintWidget17.mListDimensionBehaviors[i8] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget17.mResolvedMatchConstraintDefault[i8] == 0;
            if (constraintAnchor6.mTarget != null && constraintWidget17 != constraintWidget10) {
                margin += constraintAnchor6.mTarget.getMargin();
            }
            int i10 = margin;
            if (!z8 || constraintWidget17 == constraintWidget10 || constraintWidget17 == constraintWidget12) {
                z4 = z7;
            } else {
                z4 = z7;
                i9 = 8;
            }
            if (constraintAnchor6.mTarget != null) {
                if (constraintWidget17 == constraintWidget12) {
                    constraintWidget8 = constraintWidget14;
                    constraintWidget9 = constraintWidget10;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i10, 6);
                } else {
                    constraintWidget8 = constraintWidget14;
                    constraintWidget9 = constraintWidget10;
                    linearSystem.addGreaterThan(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i10, 8);
                }
                if (z11 && !z8) {
                    i9 = 5;
                }
                linearSystem.addEquality(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, i10, (constraintWidget17 == constraintWidget12 && z8 && constraintWidget17.isInBarrier(i8)) ? 5 : i9);
            } else {
                constraintWidget8 = constraintWidget14;
                constraintWidget9 = constraintWidget10;
            }
            if (z5) {
                if (constraintWidget17.getVisibility() == 8 || constraintWidget17.mListDimensionBehaviors[i8] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i7 = 0;
                } else {
                    i7 = 0;
                    linearSystem.addGreaterThan(constraintWidget17.mListAnchors[i2 + 1].mSolverVariable, constraintWidget17.mListAnchors[i2].mSolverVariable, 0, 5);
                }
                linearSystem.addGreaterThan(constraintWidget17.mListAnchors[i2].mSolverVariable, constraintWidgetContainer.mListAnchors[i2].mSolverVariable, i7, 8);
            }
            ConstraintAnchor constraintAnchor7 = constraintWidget17.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor7 != null) {
                ConstraintWidget constraintWidget19 = constraintAnchor7.mOwner;
                if (constraintWidget19.mListAnchors[i2].mTarget != null && constraintWidget19.mListAnchors[i2].mTarget.mOwner == constraintWidget17) {
                    constraintWidget18 = constraintWidget19;
                }
            }
            if (constraintWidget18 != null) {
                constraintWidget17 = constraintWidget18;
                z9 = z10;
            } else {
                z9 = true;
            }
            z7 = z4;
            f4 = f5;
            constraintWidget14 = constraintWidget8;
            constraintWidget10 = constraintWidget9;
        }
        ConstraintWidget constraintWidget20 = constraintWidget14;
        float f6 = f4;
        ConstraintWidget constraintWidget21 = constraintWidget10;
        boolean z12 = z7;
        if (constraintWidget13 != null) {
            int i11 = i2 + 1;
            if (constraintWidget11.mListAnchors[i11].mTarget != null) {
                ConstraintAnchor constraintAnchor8 = constraintWidget13.mListAnchors[i11];
                if ((constraintWidget13.mListDimensionBehaviors[i8] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget13.mResolvedMatchConstraintDefault[i8] == 0) && !z8 && constraintAnchor8.mTarget.mOwner == constraintWidgetContainer) {
                    linearSystem.addEquality(constraintAnchor8.mSolverVariable, constraintAnchor8.mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 5);
                } else if (z8 && constraintAnchor8.mTarget.mOwner == constraintWidgetContainer) {
                    linearSystem.addEquality(constraintAnchor8.mSolverVariable, constraintAnchor8.mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 4);
                }
                linearSystem.addLowerThan(constraintAnchor8.mSolverVariable, constraintWidget11.mListAnchors[i11].mTarget.mSolverVariable, -constraintAnchor8.getMargin(), 6);
                if (z5) {
                    int i12 = i2 + 1;
                    linearSystem.addGreaterThan(constraintWidgetContainer.mListAnchors[i12].mSolverVariable, constraintWidget11.mListAnchors[i12].mSolverVariable, constraintWidget11.mListAnchors[i12].getMargin(), 8);
                }
                arrayList = chainHead.mWeightedMatchConstraintsWidgets;
                if (arrayList != null && (size = arrayList.size()) > 1) {
                    float f7 = (chainHead.mHasUndefinedWeights || chainHead.mHasComplexMatchWeights) ? f6 : chainHead.mWidgetsMatchCount;
                    float f8 = 0.0f;
                    float f9 = 0.0f;
                    ConstraintWidget constraintWidget22 = null;
                    i5 = 0;
                    while (i5 < size) {
                        ConstraintWidget constraintWidget23 = arrayList.get(i5);
                        float f10 = constraintWidget23.mWeight[i8];
                        if (f10 >= f8) {
                            f2 = f8;
                            f3 = f10;
                        } else if (chainHead.mHasComplexMatchWeights) {
                            linearSystem.addEquality(constraintWidget23.mListAnchors[i2 + 1].mSolverVariable, constraintWidget23.mListAnchors[i2].mSolverVariable, 0, 4);
                            arrayList2 = arrayList;
                            i6 = size;
                            i5++;
                            size = i6;
                            arrayList = arrayList2;
                            f8 = 0.0f;
                        } else {
                            f3 = 1.0f;
                            f2 = 0.0f;
                        }
                        if (f3 == f2) {
                            linearSystem.addEquality(constraintWidget23.mListAnchors[i2 + 1].mSolverVariable, constraintWidget23.mListAnchors[i2].mSolverVariable, 0, 8);
                            arrayList2 = arrayList;
                            i6 = size;
                            i5++;
                            size = i6;
                            arrayList = arrayList2;
                            f8 = 0.0f;
                        } else {
                            if (constraintWidget22 != null) {
                                SolverVariable solverVariable5 = constraintWidget22.mListAnchors[i2].mSolverVariable;
                                int i13 = i2 + 1;
                                SolverVariable solverVariable6 = constraintWidget22.mListAnchors[i13].mSolverVariable;
                                SolverVariable solverVariable7 = constraintWidget23.mListAnchors[i2].mSolverVariable;
                                arrayList2 = arrayList;
                                SolverVariable solverVariable8 = constraintWidget23.mListAnchors[i13].mSolverVariable;
                                i6 = size;
                                ArrayRow createRow = linearSystem.createRow();
                                createRow.createRowEqualMatchDimensions(f9, f7, f3, solverVariable5, solverVariable6, solverVariable7, solverVariable8);
                                linearSystem.addConstraint(createRow);
                            } else {
                                arrayList2 = arrayList;
                                i6 = size;
                            }
                            f9 = f3;
                            constraintWidget22 = constraintWidget23;
                            i5++;
                            size = i6;
                            arrayList = arrayList2;
                            f8 = 0.0f;
                        }
                    }
                }
                if (constraintWidget12 == null && (constraintWidget12 == constraintWidget13 || z8)) {
                    ConstraintAnchor constraintAnchor9 = constraintWidget21.mListAnchors[i2];
                    int i14 = i2 + 1;
                    ConstraintAnchor constraintAnchor10 = constraintWidget11.mListAnchors[i14];
                    SolverVariable solverVariable9 = constraintAnchor9.mTarget != null ? constraintAnchor9.mTarget.mSolverVariable : null;
                    SolverVariable solverVariable10 = constraintAnchor10.mTarget != null ? constraintAnchor10.mTarget.mSolverVariable : null;
                    ConstraintAnchor constraintAnchor11 = constraintWidget12.mListAnchors[i2];
                    if (constraintWidget13 != null) {
                        constraintAnchor10 = constraintWidget13.mListAnchors[i14];
                    }
                    if (solverVariable9 != null && solverVariable10 != null) {
                        if (i8 == 0) {
                            f = constraintWidget20.mHorizontalBiasPercent;
                        } else {
                            f = constraintWidget20.mVerticalBiasPercent;
                        }
                        linearSystem.addCentering(constraintAnchor11.mSolverVariable, solverVariable9, constraintAnchor11.getMargin(), f, solverVariable10, constraintAnchor10.mSolverVariable, constraintAnchor10.getMargin(), 7);
                    }
                } else if (z12 || constraintWidget12 == null) {
                    int i15 = 8;
                    if (z6 && constraintWidget12 != null) {
                        boolean z13 = chainHead.mWidgetsMatchCount <= 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                        ConstraintWidget constraintWidget24 = constraintWidget12;
                        constraintWidget = constraintWidget24;
                        while (constraintWidget != null) {
                            ConstraintWidget constraintWidget25 = constraintWidget.mNextChainWidget[i8];
                            while (constraintWidget25 != null && constraintWidget25.getVisibility() == i15) {
                                constraintWidget25 = constraintWidget25.mNextChainWidget[i8];
                            }
                            if (constraintWidget == constraintWidget12 || constraintWidget == constraintWidget13 || constraintWidget25 == null) {
                                constraintWidget2 = constraintWidget24;
                                i4 = i15;
                            } else {
                                ConstraintWidget constraintWidget26 = constraintWidget25 == constraintWidget13 ? null : constraintWidget25;
                                ConstraintAnchor constraintAnchor12 = constraintWidget.mListAnchors[i2];
                                SolverVariable solverVariable11 = constraintAnchor12.mSolverVariable;
                                if (constraintAnchor12.mTarget != null) {
                                    SolverVariable solverVariable12 = constraintAnchor12.mTarget.mSolverVariable;
                                }
                                int i16 = i2 + 1;
                                SolverVariable solverVariable13 = constraintWidget24.mListAnchors[i16].mSolverVariable;
                                int margin2 = constraintAnchor12.getMargin();
                                int margin3 = constraintWidget.mListAnchors[i16].getMargin();
                                if (constraintWidget26 != null) {
                                    constraintAnchor4 = constraintWidget26.mListAnchors[i2];
                                    SolverVariable solverVariable14 = constraintAnchor4.mSolverVariable;
                                    constraintWidget3 = constraintWidget26;
                                    solverVariable2 = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                                    solverVariable = solverVariable14;
                                } else {
                                    constraintWidget3 = constraintWidget26;
                                    constraintAnchor4 = constraintWidget13.mListAnchors[i2];
                                    solverVariable = constraintAnchor4 != null ? constraintAnchor4.mSolverVariable : null;
                                    solverVariable2 = constraintWidget.mListAnchors[i16].mSolverVariable;
                                }
                                if (constraintAnchor4 != null) {
                                    margin3 += constraintAnchor4.getMargin();
                                }
                                int i17 = margin3;
                                int margin4 = constraintWidget24.mListAnchors[i16].getMargin() + margin2;
                                int i18 = z13 ? 8 : 4;
                                if (solverVariable11 == null || solverVariable13 == null || solverVariable == null || solverVariable2 == null) {
                                    constraintWidget4 = constraintWidget3;
                                    constraintWidget2 = constraintWidget24;
                                    i4 = 8;
                                } else {
                                    constraintWidget4 = constraintWidget3;
                                    constraintWidget2 = constraintWidget24;
                                    i4 = 8;
                                    linearSystem.addCentering(solverVariable11, solverVariable13, margin4, 0.5f, solverVariable, solverVariable2, i17, i18);
                                }
                                constraintWidget25 = constraintWidget4;
                            }
                            constraintWidget24 = constraintWidget.getVisibility() != i4 ? constraintWidget : constraintWidget2;
                            constraintWidget = constraintWidget25;
                            i15 = i4;
                            i8 = i;
                        }
                        ConstraintAnchor constraintAnchor13 = constraintWidget12.mListAnchors[i2];
                        constraintAnchor = constraintWidget21.mListAnchors[i2].mTarget;
                        int i19 = i2 + 1;
                        constraintAnchor2 = constraintWidget13.mListAnchors[i19];
                        constraintAnchor3 = constraintWidget11.mListAnchors[i19].mTarget;
                        if (constraintAnchor != null) {
                            i3 = 5;
                        } else if (constraintWidget12 != constraintWidget13) {
                            i3 = 5;
                            linearSystem.addEquality(constraintAnchor13.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor13.getMargin(), 5);
                        } else {
                            i3 = 5;
                            if (constraintAnchor3 != null) {
                                linearSystem.addCentering(constraintAnchor13.mSolverVariable, constraintAnchor.mSolverVariable, constraintAnchor13.getMargin(), 0.5f, constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, constraintAnchor2.getMargin(), 5);
                            }
                        }
                        if (constraintAnchor3 != null && constraintWidget12 != constraintWidget13) {
                            linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), i3);
                        }
                    }
                } else {
                    boolean z14 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                    ConstraintWidget constraintWidget27 = constraintWidget12;
                    ConstraintWidget constraintWidget28 = constraintWidget27;
                    while (constraintWidget28 != null) {
                        ConstraintWidget constraintWidget29 = constraintWidget28.mNextChainWidget[i8];
                        while (constraintWidget29 != null && constraintWidget29.getVisibility() == 8) {
                            constraintWidget29 = constraintWidget29.mNextChainWidget[i8];
                        }
                        if (constraintWidget29 != null || constraintWidget28 == constraintWidget13) {
                            ConstraintAnchor constraintAnchor14 = constraintWidget28.mListAnchors[i2];
                            SolverVariable solverVariable15 = constraintAnchor14.mSolverVariable;
                            SolverVariable solverVariable16 = constraintAnchor14.mTarget != null ? constraintAnchor14.mTarget.mSolverVariable : null;
                            if (constraintWidget27 != constraintWidget28) {
                                solverVariable16 = constraintWidget27.mListAnchors[i2 + 1].mSolverVariable;
                            } else if (constraintWidget28 == constraintWidget12) {
                                solverVariable16 = constraintWidget21.mListAnchors[i2].mTarget != null ? constraintWidget21.mListAnchors[i2].mTarget.mSolverVariable : null;
                            }
                            int margin5 = constraintAnchor14.getMargin();
                            int i20 = i2 + 1;
                            int margin6 = constraintWidget28.mListAnchors[i20].getMargin();
                            if (constraintWidget29 != null) {
                                constraintAnchor5 = constraintWidget29.mListAnchors[i2];
                                solverVariable3 = constraintAnchor5.mSolverVariable;
                            } else {
                                constraintAnchor5 = constraintWidget11.mListAnchors[i20].mTarget;
                                if (constraintAnchor5 != null) {
                                    solverVariable3 = constraintAnchor5.mSolverVariable;
                                } else {
                                    constraintWidget5 = constraintWidget29;
                                    solverVariable3 = null;
                                    SolverVariable solverVariable17 = constraintWidget28.mListAnchors[i20].mSolverVariable;
                                    if (constraintAnchor5 != null) {
                                        margin6 += constraintAnchor5.getMargin();
                                    }
                                    int margin7 = margin5 + constraintWidget27.mListAnchors[i20].getMargin();
                                    if (solverVariable15 != null || solverVariable16 == null || solverVariable3 == null || solverVariable17 == null) {
                                        constraintWidget6 = constraintWidget5;
                                    } else {
                                        if (constraintWidget28 == constraintWidget12) {
                                            margin7 = constraintWidget12.mListAnchors[i2].getMargin();
                                        }
                                        int i21 = margin7;
                                        constraintWidget6 = constraintWidget5;
                                        constraintWidget7 = constraintWidget27;
                                        linearSystem.addCentering(solverVariable15, solverVariable16, i21, 0.5f, solverVariable3, solverVariable17, constraintWidget28 == constraintWidget13 ? constraintWidget13.mListAnchors[i20].getMargin() : margin6, z14 ? 8 : 5);
                                        if (constraintWidget28.getVisibility() != 8) {
                                            constraintWidget28 = constraintWidget7;
                                        }
                                        constraintWidget27 = constraintWidget28;
                                        constraintWidget28 = constraintWidget6;
                                    }
                                }
                            }
                            constraintWidget5 = constraintWidget29;
                            SolverVariable solverVariable172 = constraintWidget28.mListAnchors[i20].mSolverVariable;
                            if (constraintAnchor5 != null) {
                            }
                            int margin72 = margin5 + constraintWidget27.mListAnchors[i20].getMargin();
                            if (solverVariable15 != null) {
                            }
                            constraintWidget6 = constraintWidget5;
                        } else {
                            constraintWidget6 = constraintWidget29;
                        }
                        constraintWidget7 = constraintWidget27;
                        if (constraintWidget28.getVisibility() != 8) {
                        }
                        constraintWidget27 = constraintWidget28;
                        constraintWidget28 = constraintWidget6;
                    }
                }
                if ((z12 && !z6) || constraintWidget12 == null || constraintWidget12 == constraintWidget13) {
                    return;
                }
                ConstraintAnchor constraintAnchor15 = constraintWidget12.mListAnchors[i2];
                if (constraintWidget13 == null) {
                    constraintWidget13 = constraintWidget12;
                }
                int i22 = i2 + 1;
                ConstraintAnchor constraintAnchor16 = constraintWidget13.mListAnchors[i22];
                solverVariable4 = constraintAnchor15.mTarget == null ? constraintAnchor15.mTarget.mSolverVariable : null;
                SolverVariable solverVariable18 = constraintAnchor16.mTarget == null ? constraintAnchor16.mTarget.mSolverVariable : null;
                if (constraintWidget11 != constraintWidget13) {
                    ConstraintAnchor constraintAnchor17 = constraintWidget11.mListAnchors[i22];
                    solverVariable18 = constraintAnchor17.mTarget != null ? constraintAnchor17.mTarget.mSolverVariable : null;
                }
                SolverVariable solverVariable19 = solverVariable18;
                if (constraintWidget12 == constraintWidget13) {
                    constraintAnchor15 = constraintWidget12.mListAnchors[i2];
                    constraintAnchor16 = constraintWidget12.mListAnchors[i22];
                }
                if (solverVariable4 != null || solverVariable19 == null) {
                }
                linearSystem.addCentering(constraintAnchor15.mSolverVariable, solverVariable4, constraintAnchor15.getMargin(), 0.5f, solverVariable19, constraintAnchor16.mSolverVariable, constraintWidget13.mListAnchors[i22].getMargin(), 5);
                return;
            }
        }
        if (z5) {
        }
        arrayList = chainHead.mWeightedMatchConstraintsWidgets;
        if (arrayList != null) {
            if (chainHead.mHasUndefinedWeights) {
            }
            float f82 = 0.0f;
            float f92 = 0.0f;
            ConstraintWidget constraintWidget222 = null;
            i5 = 0;
            while (i5 < size) {
            }
        }
        if (constraintWidget12 == null) {
        }
        if (z12) {
        }
        int i152 = 8;
        if (z6) {
            if (chainHead.mWidgetsMatchCount <= 0) {
            }
            ConstraintWidget constraintWidget242 = constraintWidget12;
            constraintWidget = constraintWidget242;
            while (constraintWidget != null) {
            }
            ConstraintAnchor constraintAnchor132 = constraintWidget12.mListAnchors[i2];
            constraintAnchor = constraintWidget21.mListAnchors[i2].mTarget;
            int i192 = i2 + 1;
            constraintAnchor2 = constraintWidget13.mListAnchors[i192];
            constraintAnchor3 = constraintWidget11.mListAnchors[i192].mTarget;
            if (constraintAnchor != null) {
            }
            if (constraintAnchor3 != null) {
                linearSystem.addEquality(constraintAnchor2.mSolverVariable, constraintAnchor3.mSolverVariable, -constraintAnchor2.getMargin(), i3);
            }
        }
        if (z12) {
        }
        ConstraintAnchor constraintAnchor152 = constraintWidget12.mListAnchors[i2];
        if (constraintWidget13 == null) {
        }
        int i222 = i2 + 1;
        ConstraintAnchor constraintAnchor162 = constraintWidget13.mListAnchors[i222];
        if (constraintAnchor152.mTarget == null) {
        }
        if (constraintAnchor162.mTarget == null) {
        }
        if (constraintWidget11 != constraintWidget13) {
        }
        SolverVariable solverVariable192 = solverVariable18;
        if (constraintWidget12 == constraintWidget13) {
        }
        if (solverVariable4 != null) {
        }
    }
}
