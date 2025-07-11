package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.helpers.AlignHorizontallyReference;
import androidx.constraintlayout.core.state.helpers.AlignVerticallyReference;
import androidx.constraintlayout.core.state.helpers.BarrierReference;
import androidx.constraintlayout.core.state.helpers.GuidelineReference;
import androidx.constraintlayout.core.state.helpers.HorizontalChainReference;
import androidx.constraintlayout.core.state.helpers.VerticalChainReference;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class State {
    static final int CONSTRAINT_RATIO = 2;
    static final int CONSTRAINT_SPREAD = 0;
    static final int CONSTRAINT_WRAP = 1;
    public static final Integer PARENT = 0;
    static final int UNKNOWN = -1;
    protected HashMap<Object, Reference> mReferences = new HashMap<>();
    protected HashMap<Object, HelperReference> mHelperReferences = new HashMap<>();
    HashMap<String, ArrayList<String>> mTags = new HashMap<>();
    public final ConstraintReference mParent = new ConstraintReference(this);
    private int numHelpers = 0;

    /* loaded from: classes.dex */
    public enum Chain {
        SPREAD,
        SPREAD_INSIDE,
        PACKED
    }

    /* loaded from: classes.dex */
    public enum Constraint {
        LEFT_TO_LEFT,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        RIGHT_TO_RIGHT,
        START_TO_START,
        START_TO_END,
        END_TO_START,
        END_TO_END,
        TOP_TO_TOP,
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP,
        BOTTOM_TO_BOTTOM,
        BASELINE_TO_BASELINE,
        CENTER_HORIZONTALLY,
        CENTER_VERTICALLY,
        CIRCULAR_CONSTRAINT
    }

    /* loaded from: classes.dex */
    public enum Direction {
        LEFT,
        RIGHT,
        START,
        END,
        TOP,
        BOTTOM
    }

    /* loaded from: classes.dex */
    public enum Helper {
        HORIZONTAL_CHAIN,
        VERTICAL_CHAIN,
        ALIGN_HORIZONTALLY,
        ALIGN_VERTICALLY,
        BARRIER,
        LAYER,
        FLOW
    }

    public State() {
        this.mReferences.put(PARENT, this.mParent);
    }

    public void reset() {
        this.mHelperReferences.clear();
        this.mTags.clear();
    }

    public int convertDimension(Object obj) {
        if (obj instanceof Float) {
            return ((Float) obj).intValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public ConstraintReference createConstraintReference(Object obj) {
        return new ConstraintReference(this);
    }

    public boolean sameFixedWidth(int i) {
        return this.mParent.getWidth().equalsFixedValue(i);
    }

    public boolean sameFixedHeight(int i) {
        return this.mParent.getHeight().equalsFixedValue(i);
    }

    public State width(Dimension dimension) {
        return setWidth(dimension);
    }

    public State height(Dimension dimension) {
        return setHeight(dimension);
    }

    public State setWidth(Dimension dimension) {
        this.mParent.setWidth(dimension);
        return this;
    }

    public State setHeight(Dimension dimension) {
        this.mParent.setHeight(dimension);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Reference reference(Object obj) {
        return this.mReferences.get(obj);
    }

    public ConstraintReference constraints(Object obj) {
        Reference reference = this.mReferences.get(obj);
        if (reference == null) {
            reference = createConstraintReference(obj);
            this.mReferences.put(obj, reference);
            reference.setKey(obj);
        }
        if (reference instanceof ConstraintReference) {
            return (ConstraintReference) reference;
        }
        return null;
    }

    private String createHelperKey() {
        StringBuilder sb = new StringBuilder();
        sb.append("__HELPER_KEY_");
        int i = this.numHelpers;
        this.numHelpers = i + 1;
        sb.append(i);
        sb.append("__");
        return sb.toString();
    }

    public HelperReference helper(Object obj, Helper helper) {
        HelperReference horizontalChainReference;
        if (obj == null) {
            obj = createHelperKey();
        }
        HelperReference helperReference = this.mHelperReferences.get(obj);
        if (helperReference == null) {
            int i = C01581.$SwitchMap$androidx$constraintlayout$core$state$State$Helper[helper.ordinal()];
            if (i == 1) {
                horizontalChainReference = new HorizontalChainReference(this);
            } else if (i == 2) {
                horizontalChainReference = new VerticalChainReference(this);
            } else if (i == 3) {
                horizontalChainReference = new AlignHorizontallyReference(this);
            } else if (i == 4) {
                horizontalChainReference = new AlignVerticallyReference(this);
            } else if (i == 5) {
                horizontalChainReference = new BarrierReference(this);
            } else {
                helperReference = new HelperReference(this, helper);
                this.mHelperReferences.put(obj, helperReference);
            }
            helperReference = horizontalChainReference;
            this.mHelperReferences.put(obj, helperReference);
        }
        return helperReference;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.state.State$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C01581 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$state$State$Helper = new int[Helper.values().length];

        static {
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Helper[Helper.HORIZONTAL_CHAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Helper[Helper.VERTICAL_CHAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Helper[Helper.ALIGN_HORIZONTALLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Helper[Helper.ALIGN_VERTICALLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$state$State$Helper[Helper.BARRIER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public GuidelineReference horizontalGuideline(Object obj) {
        return guideline(obj, 0);
    }

    public GuidelineReference verticalGuideline(Object obj) {
        return guideline(obj, 1);
    }

    public GuidelineReference guideline(Object obj, int i) {
        ConstraintReference constraints = constraints(obj);
        if (constraints.getFacade() == null || !(constraints.getFacade() instanceof GuidelineReference)) {
            GuidelineReference guidelineReference = new GuidelineReference(this);
            guidelineReference.setOrientation(i);
            guidelineReference.setKey(obj);
            constraints.setFacade(guidelineReference);
        }
        return (GuidelineReference) constraints.getFacade();
    }

    public BarrierReference barrier(Object obj, Direction direction) {
        ConstraintReference constraints = constraints(obj);
        if (constraints.getFacade() == null || !(constraints.getFacade() instanceof BarrierReference)) {
            BarrierReference barrierReference = new BarrierReference(this);
            barrierReference.setBarrierDirection(direction);
            constraints.setFacade(barrierReference);
        }
        return (BarrierReference) constraints.getFacade();
    }

    public VerticalChainReference verticalChain() {
        return (VerticalChainReference) helper(null, Helper.VERTICAL_CHAIN);
    }

    public VerticalChainReference verticalChain(Object... objArr) {
        VerticalChainReference verticalChainReference = (VerticalChainReference) helper(null, Helper.VERTICAL_CHAIN);
        verticalChainReference.add(objArr);
        return verticalChainReference;
    }

    public HorizontalChainReference horizontalChain() {
        return (HorizontalChainReference) helper(null, Helper.HORIZONTAL_CHAIN);
    }

    public HorizontalChainReference horizontalChain(Object... objArr) {
        HorizontalChainReference horizontalChainReference = (HorizontalChainReference) helper(null, Helper.HORIZONTAL_CHAIN);
        horizontalChainReference.add(objArr);
        return horizontalChainReference;
    }

    public AlignHorizontallyReference centerHorizontally(Object... objArr) {
        AlignHorizontallyReference alignHorizontallyReference = (AlignHorizontallyReference) helper(null, Helper.ALIGN_HORIZONTALLY);
        alignHorizontallyReference.add(objArr);
        return alignHorizontallyReference;
    }

    public AlignVerticallyReference centerVertically(Object... objArr) {
        AlignVerticallyReference alignVerticallyReference = (AlignVerticallyReference) helper(null, Helper.ALIGN_VERTICALLY);
        alignVerticallyReference.add(objArr);
        return alignVerticallyReference;
    }

    public void directMapping() {
        for (Object obj : this.mReferences.keySet()) {
            ConstraintReference constraints = constraints(obj);
            if (constraints instanceof ConstraintReference) {
                constraints.setView(obj);
            }
        }
    }

    public void map(Object obj, Object obj2) {
        ConstraintReference constraints = constraints(obj);
        if (constraints instanceof ConstraintReference) {
            constraints.setView(obj2);
        }
    }

    public void setTag(String str, String str2) {
        ArrayList<String> arrayList;
        ConstraintReference constraints = constraints(str);
        if (constraints instanceof ConstraintReference) {
            constraints.setTag(str2);
            if (!this.mTags.containsKey(str2)) {
                arrayList = new ArrayList<>();
                this.mTags.put(str2, arrayList);
            } else {
                arrayList = this.mTags.get(str2);
            }
            arrayList.add(str);
        }
    }

    public ArrayList<String> getIdsForTag(String str) {
        if (this.mTags.containsKey(str)) {
            return this.mTags.get(str);
        }
        return null;
    }

    public void apply(ConstraintWidgetContainer constraintWidgetContainer) {
        HelperReference helperReference;
        HelperWidget helperWidget;
        HelperWidget helperWidget2;
        constraintWidgetContainer.removeAllChildren();
        this.mParent.getWidth().apply(this, constraintWidgetContainer, 0);
        this.mParent.getHeight().apply(this, constraintWidgetContainer, 1);
        for (Object obj : this.mHelperReferences.keySet()) {
            HelperWidget helperWidget3 = this.mHelperReferences.get(obj).getHelperWidget();
            if (helperWidget3 != null) {
                Reference reference = this.mReferences.get(obj);
                if (reference == null) {
                    reference = constraints(obj);
                }
                reference.setConstraintWidget(helperWidget3);
            }
        }
        for (Object obj2 : this.mReferences.keySet()) {
            Reference reference2 = this.mReferences.get(obj2);
            if (reference2 != this.mParent && (reference2.getFacade() instanceof HelperReference) && (helperWidget2 = ((HelperReference) reference2.getFacade()).getHelperWidget()) != null) {
                Reference reference3 = this.mReferences.get(obj2);
                if (reference3 == null) {
                    reference3 = constraints(obj2);
                }
                reference3.setConstraintWidget(helperWidget2);
            }
        }
        Iterator<Object> it = this.mReferences.keySet().iterator();
        while (it.hasNext()) {
            Reference reference4 = this.mReferences.get(it.next());
            if (reference4 != this.mParent) {
                ConstraintWidget constraintWidget = reference4.getConstraintWidget();
                constraintWidget.setDebugName(reference4.getKey().toString());
                constraintWidget.setParent(null);
                if (reference4.getFacade() instanceof GuidelineReference) {
                    reference4.apply();
                }
                constraintWidgetContainer.add(constraintWidget);
            } else {
                reference4.setConstraintWidget(constraintWidgetContainer);
            }
        }
        Iterator<Object> it2 = this.mHelperReferences.keySet().iterator();
        while (it2.hasNext()) {
            HelperReference helperReference2 = this.mHelperReferences.get(it2.next());
            if (helperReference2.getHelperWidget() != null) {
                Iterator<Object> it3 = helperReference2.mReferences.iterator();
                while (it3.hasNext()) {
                    helperReference2.getHelperWidget().add(this.mReferences.get(it3.next()).getConstraintWidget());
                }
                helperReference2.apply();
            } else {
                helperReference2.apply();
            }
        }
        Iterator<Object> it4 = this.mReferences.keySet().iterator();
        while (it4.hasNext()) {
            Reference reference5 = this.mReferences.get(it4.next());
            if (reference5 != this.mParent && (reference5.getFacade() instanceof HelperReference) && (helperWidget = (helperReference = (HelperReference) reference5.getFacade()).getHelperWidget()) != null) {
                Iterator<Object> it5 = helperReference.mReferences.iterator();
                while (it5.hasNext()) {
                    Object next = it5.next();
                    Reference reference6 = this.mReferences.get(next);
                    if (reference6 != null) {
                        helperWidget.add(reference6.getConstraintWidget());
                    } else if (next instanceof Reference) {
                        helperWidget.add(((Reference) next).getConstraintWidget());
                    } else {
                        System.out.println("couldn't find reference for " + next);
                    }
                }
                reference5.apply();
            }
        }
        for (Object obj3 : this.mReferences.keySet()) {
            Reference reference7 = this.mReferences.get(obj3);
            reference7.apply();
            ConstraintWidget constraintWidget2 = reference7.getConstraintWidget();
            if (constraintWidget2 != null && obj3 != null) {
                constraintWidget2.stringId = obj3.toString();
            }
        }
    }
}
