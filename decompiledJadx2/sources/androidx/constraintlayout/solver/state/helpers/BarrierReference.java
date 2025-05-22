package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.HelperWidget;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BarrierReference extends HelperReference {
    private Barrier mBarrierWidget;
    private State.Direction mDirection;
    private int mMargin;

    public BarrierReference(State state) {
        super(state, State.Helper.BARRIER);
    }

    public void setBarrierDirection(State.Direction direction) {
        this.mDirection = direction;
    }

    public void margin(Object obj) {
        margin(this.mState.convertDimension(obj));
    }

    public void margin(int i) {
        this.mMargin = i;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.constraintlayout.solver.widgets.HelperWidget, androidx.constraintlayout.solver.widgets.Barrier] */
    @Override // androidx.constraintlayout.solver.state.HelperReference
    public HelperWidget getHelperWidget() {
        if (this.mBarrierWidget == null) {
            this.mBarrierWidget = new Barrier();
        }
        return this.mBarrierWidget;
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        getHelperWidget();
        int i = 0;
        switch (C01851.f70xf452c4aa[this.mDirection.ordinal()]) {
            case 3:
            case 4:
                i = 1;
                break;
            case 5:
                i = 2;
                break;
            case 6:
                i = 3;
                break;
        }
        this.mBarrierWidget.setBarrierType(i);
        this.mBarrierWidget.setMargin(this.mMargin);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: androidx.constraintlayout.solver.state.helpers.BarrierReference$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C01851 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$state$State$Direction */
        static final /* synthetic */ int[] f70xf452c4aa;

        static {
            int[] iArr = new int[State.Direction.values().length];
            f70xf452c4aa = iArr;
            try {
                iArr[State.Direction.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f70xf452c4aa[State.Direction.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f70xf452c4aa[State.Direction.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f70xf452c4aa[State.Direction.END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f70xf452c4aa[State.Direction.TOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f70xf452c4aa[State.Direction.BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
