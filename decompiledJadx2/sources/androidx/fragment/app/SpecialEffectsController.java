package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.fragment.C0351R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class SpecialEffectsController {
    private final ViewGroup mContainer;
    final ArrayList<Operation> mPendingOperations = new ArrayList<>();
    final HashMap<Fragment, Operation> mAwaitingCompletionOperations = new HashMap<>();
    boolean mOperationDirectionIsPop = false;
    boolean mIsContainerPostponed = false;

    abstract void executeOperations(List<Operation> list, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpecialEffectsController getOrCreateController(ViewGroup viewGroup, FragmentManager fragmentManager) {
        return getOrCreateController(viewGroup, fragmentManager.getSpecialEffectsControllerFactory());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SpecialEffectsController getOrCreateController(ViewGroup viewGroup, SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        Object tag = viewGroup.getTag(C0351R.id.special_effects_controller_view_tag);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController createController = specialEffectsControllerFactory.createController(viewGroup);
        viewGroup.setTag(C0351R.id.special_effects_controller_view_tag, createController);
        return createController;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpecialEffectsController(ViewGroup viewGroup) {
        this.mContainer = viewGroup;
    }

    public ViewGroup getContainer() {
        return this.mContainer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Operation.LifecycleImpact getAwaitingCompletionLifecycleImpact(FragmentStateManager fragmentStateManager) {
        Operation operation = this.mAwaitingCompletionOperations.get(fragmentStateManager.getFragment());
        if (operation == null || operation.getCancellationSignal().isCanceled()) {
            return null;
        }
        return operation.getLifecycleImpact();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueAdd(Operation.State state, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(state, Operation.LifecycleImpact.ADDING, fragmentStateManager, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueShow(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, fragmentStateManager, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueHide(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.State.GONE, Operation.LifecycleImpact.NONE, fragmentStateManager, cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueRemove(FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        enqueue(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, fragmentStateManager, cancellationSignal);
    }

    private void enqueue(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
        if (cancellationSignal.isCanceled()) {
            return;
        }
        synchronized (this.mPendingOperations) {
            final CancellationSignal cancellationSignal2 = new CancellationSignal();
            Operation operation = this.mAwaitingCompletionOperations.get(fragmentStateManager.getFragment());
            if (operation != null) {
                operation.mergeWith(state, lifecycleImpact, cancellationSignal);
                return;
            }
            final FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(state, lifecycleImpact, fragmentStateManager, cancellationSignal2);
            this.mPendingOperations.add(fragmentStateManagerOperation);
            this.mAwaitingCompletionOperations.put(fragmentStateManagerOperation.getFragment(), fragmentStateManagerOperation);
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    synchronized (SpecialEffectsController.this.mPendingOperations) {
                        SpecialEffectsController.this.mPendingOperations.remove(fragmentStateManagerOperation);
                        SpecialEffectsController.this.mAwaitingCompletionOperations.remove(fragmentStateManagerOperation.getFragment());
                        cancellationSignal2.cancel();
                    }
                }
            });
            fragmentStateManagerOperation.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.2
                @Override // java.lang.Runnable
                public void run() {
                    if (fragmentStateManagerOperation.getCancellationSignal().isCanceled()) {
                        return;
                    }
                    SpecialEffectsController.this.mAwaitingCompletionOperations.remove(fragmentStateManagerOperation.getFragment());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateOperationDirection(boolean z) {
        this.mOperationDirectionIsPop = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markPostponedState() {
        synchronized (this.mPendingOperations) {
            this.mIsContainerPostponed = false;
            int size = this.mPendingOperations.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Operation operation = this.mPendingOperations.get(size);
                Operation.State from = Operation.State.from(operation.getFragment().mView);
                if (operation.getFinalState() == Operation.State.VISIBLE && from != Operation.State.VISIBLE) {
                    this.mIsContainerPostponed = operation.getFragment().isPostponed();
                    break;
                }
                size--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void forcePostponedExecutePendingOperations() {
        if (this.mIsContainerPostponed) {
            this.mIsContainerPostponed = false;
            executePendingOperations();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void executePendingOperations() {
        if (this.mIsContainerPostponed) {
            return;
        }
        synchronized (this.mPendingOperations) {
            if (!this.mPendingOperations.isEmpty()) {
                executeOperations(new ArrayList(this.mPendingOperations), this.mOperationDirectionIsPop);
                this.mPendingOperations.clear();
                this.mOperationDirectionIsPop = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void forceCompleteAllOperations() {
        synchronized (this.mPendingOperations) {
            for (Operation operation : this.mAwaitingCompletionOperations.values()) {
                operation.getCancellationSignal().cancel();
                operation.getFinalState().applyState(operation.getFragment().mView);
                operation.complete();
            }
            this.mAwaitingCompletionOperations.clear();
            this.mPendingOperations.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Operation {
        final CancellationSignal mCancellationSignal = new CancellationSignal();
        private final List<Runnable> mCompletionListeners = new ArrayList();
        private State mFinalState;
        private final Fragment mFragment;
        private LifecycleImpact mLifecycleImpact;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes.dex */
        public enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes.dex */
        public enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            /* JADX INFO: Access modifiers changed from: package-private */
            public static State from(View view) {
                return from(view.getVisibility());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static State from(int i) {
                if (i == 0) {
                    return VISIBLE;
                }
                if (i == 4) {
                    return INVISIBLE;
                }
                if (i == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException("Unknown visibility " + i);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public void applyState(View view) {
                int i = C04283.f103xe493b431[ordinal()];
                if (i == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    view.setVisibility(0);
                } else if (i == 3) {
                    view.setVisibility(8);
                } else {
                    if (i != 4) {
                        return;
                    }
                    view.setVisibility(4);
                }
            }
        }

        Operation(State state, LifecycleImpact lifecycleImpact, Fragment fragment, CancellationSignal cancellationSignal) {
            this.mFinalState = state;
            this.mLifecycleImpact = lifecycleImpact;
            this.mFragment = fragment;
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.Operation.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    Operation.this.mCancellationSignal.cancel();
                }
            });
        }

        public State getFinalState() {
            return this.mFinalState;
        }

        LifecycleImpact getLifecycleImpact() {
            return this.mLifecycleImpact;
        }

        public final Fragment getFragment() {
            return this.mFragment;
        }

        public final CancellationSignal getCancellationSignal() {
            return this.mCancellationSignal;
        }

        final void mergeWith(State state, LifecycleImpact lifecycleImpact, CancellationSignal cancellationSignal) {
            int i = C04283.f102xb9e640f0[lifecycleImpact.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    this.mFinalState = State.REMOVED;
                    this.mLifecycleImpact = LifecycleImpact.REMOVING;
                } else if (i == 3 && this.mFinalState != State.REMOVED) {
                    this.mFinalState = state;
                }
            } else if (this.mFinalState == State.REMOVED) {
                this.mFinalState = State.VISIBLE;
                this.mLifecycleImpact = LifecycleImpact.ADDING;
            }
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.Operation.2
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    Operation.this.mCancellationSignal.cancel();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void addCompletionListener(Runnable runnable) {
            this.mCompletionListeners.add(runnable);
        }

        public void complete() {
            Iterator<Runnable> it = this.mCompletionListeners.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: androidx.fragment.app.SpecialEffectsController$3 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C04283 {

        /* renamed from: $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$LifecycleImpact */
        static final /* synthetic */ int[] f102xb9e640f0;

        /* renamed from: $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State */
        static final /* synthetic */ int[] f103xe493b431;

        static {
            int[] iArr = new int[Operation.LifecycleImpact.values().length];
            f102xb9e640f0 = iArr;
            try {
                iArr[Operation.LifecycleImpact.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f102xb9e640f0[Operation.LifecycleImpact.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f102xb9e640f0[Operation.LifecycleImpact.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Operation.State.values().length];
            f103xe493b431 = iArr2;
            try {
                iArr2[Operation.State.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f103xe493b431[Operation.State.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f103xe493b431[Operation.State.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f103xe493b431[Operation.State.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class FragmentStateManagerOperation extends Operation {
        private final FragmentStateManager mFragmentStateManager;

        FragmentStateManagerOperation(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
            super(state, lifecycleImpact, fragmentStateManager.getFragment(), cancellationSignal);
            this.mFragmentStateManager = fragmentStateManager;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.moveToExpectedState();
        }
    }
}
