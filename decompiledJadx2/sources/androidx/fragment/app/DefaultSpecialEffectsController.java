package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    private final HashMap<SpecialEffectsController.Operation, HashSet<CancellationSignal>> mRunningOperations;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
        this.mRunningOperations = new HashMap<>();
    }

    private void addCancellationSignal(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
        if (this.mRunningOperations.get(operation) == null) {
            this.mRunningOperations.put(operation, new HashSet<>());
        }
        this.mRunningOperations.get(operation).add(cancellationSignal);
    }

    void removeCancellationSignal(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
        HashSet<CancellationSignal> hashSet = this.mRunningOperations.get(operation);
        if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
            this.mRunningOperations.remove(operation);
            operation.complete();
        }
    }

    void cancelAllSpecialEffects(SpecialEffectsController.Operation operation) {
        HashSet<CancellationSignal> remove = this.mRunningOperations.remove(operation);
        if (remove != null) {
            Iterator<CancellationSignal> it = remove.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation operation3 : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(operation3.getFragment().mView);
            int i = C035511.f101xe493b431[operation3.getFinalState().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = operation3;
                }
            } else if (i == 4 && from != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = operation3;
            }
        }
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList(list);
        Iterator<SpecialEffectsController.Operation> it = list.iterator();
        while (true) {
            boolean z2 = false;
            if (!it.hasNext()) {
                break;
            }
            final SpecialEffectsController.Operation next = it.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            addCancellationSignal(next, cancellationSignal);
            arrayList.add(new AnimationInfo(next, cancellationSignal));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            addCancellationSignal(next, cancellationSignal2);
            if (z) {
                if (next != operation) {
                    arrayList2.add(new TransitionInfo(next, cancellationSignal2, z, z2));
                    next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            View focusedView;
                            if (next.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE || (focusedView = next.getFragment().getFocusedView()) == null) {
                                return;
                            }
                            focusedView.requestFocus();
                            next.getFragment().setFocusedView(null);
                        }
                    });
                    next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(next)) {
                                arrayList3.remove(next);
                                DefaultSpecialEffectsController.this.applyContainerChanges(next);
                            }
                        }
                    });
                    next.getCancellationSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                        @Override // androidx.core.os.CancellationSignal.OnCancelListener
                        public void onCancel() {
                            DefaultSpecialEffectsController.this.cancelAllSpecialEffects(next);
                        }
                    });
                }
                z2 = true;
                arrayList2.add(new TransitionInfo(next, cancellationSignal2, z, z2));
                next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        View focusedView;
                        if (next.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE || (focusedView = next.getFragment().getFocusedView()) == null) {
                            return;
                        }
                        focusedView.requestFocus();
                        next.getFragment().setFocusedView(null);
                    }
                });
                next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList3.contains(next)) {
                            arrayList3.remove(next);
                            DefaultSpecialEffectsController.this.applyContainerChanges(next);
                        }
                    }
                });
                next.getCancellationSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        DefaultSpecialEffectsController.this.cancelAllSpecialEffects(next);
                    }
                });
            } else {
                if (next != operation2) {
                    arrayList2.add(new TransitionInfo(next, cancellationSignal2, z, z2));
                    next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                        @Override // java.lang.Runnable
                        public void run() {
                            View focusedView;
                            if (next.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE || (focusedView = next.getFragment().getFocusedView()) == null) {
                                return;
                            }
                            focusedView.requestFocus();
                            next.getFragment().setFocusedView(null);
                        }
                    });
                    next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (arrayList3.contains(next)) {
                                arrayList3.remove(next);
                                DefaultSpecialEffectsController.this.applyContainerChanges(next);
                            }
                        }
                    });
                    next.getCancellationSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                        @Override // androidx.core.os.CancellationSignal.OnCancelListener
                        public void onCancel() {
                            DefaultSpecialEffectsController.this.cancelAllSpecialEffects(next);
                        }
                    });
                }
                z2 = true;
                arrayList2.add(new TransitionInfo(next, cancellationSignal2, z, z2));
                next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        View focusedView;
                        if (next.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE || (focusedView = next.getFragment().getFocusedView()) == null) {
                            return;
                        }
                        focusedView.requestFocus();
                        next.getFragment().setFocusedView(null);
                    }
                });
                next.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList3.contains(next)) {
                            arrayList3.remove(next);
                            DefaultSpecialEffectsController.this.applyContainerChanges(next);
                        }
                    }
                });
                next.getCancellationSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        DefaultSpecialEffectsController.this.cancelAllSpecialEffects(next);
                    }
                });
            }
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, z, operation, operation2);
        boolean containsValue = startTransitions.containsValue(true);
        for (AnimationInfo animationInfo : arrayList) {
            SpecialEffectsController.Operation operation4 = animationInfo.getOperation();
            startAnimation(operation4, animationInfo.getSignal(), containsValue, startTransitions.containsKey(operation4) ? startTransitions.get(operation4).booleanValue() : false);
        }
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            applyContainerChanges((SpecialEffectsController.Operation) it2.next());
        }
        arrayList3.clear();
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$11 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C035511 {

        /* renamed from: $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State */
        static final /* synthetic */ int[] f101xe493b431;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            f101xe493b431 = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f101xe493b431[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f101xe493b431[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f101xe493b431[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void startAnimation(final SpecialEffectsController.Operation operation, final CancellationSignal cancellationSignal, boolean z, boolean z2) {
        Animation endViewTransitionAnimation;
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        Fragment fragment = operation.getFragment();
        final View view = fragment.mView;
        SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(view);
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        if (from == finalState || (from != SpecialEffectsController.Operation.State.VISIBLE && finalState != SpecialEffectsController.Operation.State.VISIBLE)) {
            removeCancellationSignal(operation, cancellationSignal);
            return;
        }
        FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, fragment, finalState == SpecialEffectsController.Operation.State.VISIBLE);
        if (loadAnimation == null) {
            removeCancellationSignal(operation, cancellationSignal);
            return;
        }
        if (z && loadAnimation.animation != null) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Ignoring Animation set on " + fragment + " as Animations cannot run alongside Transitions.");
            }
            removeCancellationSignal(operation, cancellationSignal);
            return;
        }
        if (z2) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
            }
            removeCancellationSignal(operation, cancellationSignal);
            return;
        }
        container.startViewTransition(view);
        if (loadAnimation.animation != null) {
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                endViewTransitionAnimation = new FragmentAnim.EnterViewTransitionAnimation(loadAnimation.animation);
            } else {
                endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(loadAnimation.animation, container, view);
            }
            endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            container.endViewTransition(view);
                            DefaultSpecialEffectsController.this.removeCancellationSignal(operation, cancellationSignal);
                        }
                    });
                }
            });
            view.startAnimation(endViewTransitionAnimation);
        } else {
            loadAnimation.animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    container.endViewTransition(view);
                    DefaultSpecialEffectsController.this.removeCancellationSignal(operation, cancellationSignal);
                }
            });
            loadAnimation.animator.setTarget(view);
            loadAnimation.animator.start();
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                view.clearAnimation();
            }
        });
    }

    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> list, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        SpecialEffectsController.Operation operation3;
        View view;
        Object obj;
        Object obj2;
        ArrayList<View> arrayList;
        HashMap hashMap;
        View view2;
        ArrayMap arrayMap;
        SpecialEffectsController.Operation operation4;
        View view3;
        boolean z2;
        FragmentTransitionImpl fragmentTransitionImpl;
        HashMap hashMap2;
        SpecialEffectsController.Operation operation5;
        ArrayList<View> arrayList2;
        Rect rect;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList3;
        final Rect rect2;
        boolean z3;
        String findKeyForValue;
        ArrayList<String> arrayList4;
        boolean z4 = z;
        SpecialEffectsController.Operation operation6 = operation;
        SpecialEffectsController.Operation operation7 = operation2;
        HashMap hashMap3 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = handlingImpl;
                } else if (handlingImpl != null && fragmentTransitionImpl2 != handlingImpl) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        boolean z5 = false;
        if (fragmentTransitionImpl2 == null) {
            for (TransitionInfo transitionInfo2 : list) {
                hashMap3.put(transitionInfo2.getOperation(), false);
                removeCancellationSignal(transitionInfo2.getOperation(), transitionInfo2.getSignal());
            }
            return hashMap3;
        }
        View view4 = new View(getContainer().getContext());
        Rect rect3 = new Rect();
        ArrayList<View> arrayList5 = new ArrayList<>();
        ArrayList<View> arrayList6 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        boolean z6 = false;
        Object obj3 = null;
        View view5 = null;
        for (TransitionInfo transitionInfo3 : list) {
            if (!transitionInfo3.hasSharedElementTransition() || operation6 == null || operation7 == null) {
                arrayMap = arrayMap2;
                operation4 = operation7;
                view3 = view4;
                z2 = z5;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                hashMap2 = hashMap3;
                operation5 = operation6;
                arrayList2 = arrayList6;
                rect = rect3;
                view5 = view5;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                View view6 = view5;
                int i = 0;
                while (i < sharedElementTargetNames.size()) {
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    ArrayList<String> arrayList7 = sharedElementTargetNames;
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                    sharedElementTargetNames = arrayList7;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (!z4) {
                    enterTransitionCallback = operation.getFragment().getExitTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getEnterTransitionCallback();
                } else {
                    enterTransitionCallback = operation.getFragment().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getExitTransitionCallback();
                }
                int i2 = 0;
                for (int size = sharedElementSourceNames.size(); i2 < size; size = size) {
                    arrayMap2.put(sharedElementSourceNames.get(i2), sharedElementTargetNames2.get(i2));
                    i2++;
                }
                ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                findNamedViews(arrayMap3, operation.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str = sharedElementSourceNames.get(size2);
                        View view7 = arrayMap3.get(str);
                        if (view7 == null) {
                            arrayMap2.remove(str);
                            arrayList4 = sharedElementSourceNames;
                        } else {
                            arrayList4 = sharedElementSourceNames;
                            if (!str.equals(ViewCompat.getTransitionName(view7))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view7), (String) arrayMap2.remove(str));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList4;
                    }
                    arrayList3 = sharedElementSourceNames;
                } else {
                    arrayList3 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                findNamedViews(arrayMap4, operation2.getFragment().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (exitTransitionCallback != null) {
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str2 = sharedElementTargetNames2.get(size3);
                        View view8 = arrayMap4.get(str2);
                        if (view8 == null) {
                            String findKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap2, str2);
                            if (findKeyForValue2 != null) {
                                arrayMap2.remove(findKeyForValue2);
                            }
                        } else if (!str2.equals(ViewCompat.getTransitionName(view8)) && (findKeyForValue = FragmentTransition.findKeyForValue(arrayMap2, str2)) != null) {
                            arrayMap2.put(findKeyForValue, ViewCompat.getTransitionName(view8));
                        }
                    }
                } else {
                    FragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList5.clear();
                    arrayList6.clear();
                    arrayMap = arrayMap2;
                    arrayList2 = arrayList6;
                    rect = rect3;
                    view3 = view4;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view5 = view6;
                    obj3 = null;
                    z2 = false;
                    operation4 = operation2;
                    hashMap2 = hashMap3;
                    operation5 = operation;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z4, arrayMap3, true);
                    HashMap hashMap4 = hashMap3;
                    arrayMap = arrayMap2;
                    View view9 = view4;
                    ArrayList<View> arrayList8 = arrayList6;
                    Rect rect4 = rect3;
                    ArrayList<View> arrayList9 = arrayList5;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap4, false);
                        }
                    });
                    Iterator<View> it = arrayMap3.values().iterator();
                    while (it.hasNext()) {
                        captureTransitioningViews(arrayList9, it.next());
                    }
                    if (arrayList3.isEmpty()) {
                        view5 = view6;
                    } else {
                        View view10 = arrayMap3.get(arrayList3.get(0));
                        fragmentTransitionImpl2.setEpicenter(wrapTransitionInSet, view10);
                        view5 = view10;
                    }
                    Iterator<View> it2 = arrayMap4.values().iterator();
                    while (it2.hasNext()) {
                        captureTransitioningViews(arrayList8, it2.next());
                    }
                    if (sharedElementTargetNames2.isEmpty()) {
                        rect2 = rect4;
                        z3 = false;
                    } else {
                        z3 = false;
                        final View view11 = arrayMap4.get(sharedElementTargetNames2.get(0));
                        if (view11 != null) {
                            rect2 = rect4;
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    fragmentTransitionImpl2.getBoundsOnScreen(view11, rect2);
                                }
                            });
                            view3 = view9;
                            z6 = true;
                            fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view3, arrayList9);
                            rect = rect2;
                            arrayList5 = arrayList9;
                            arrayList2 = arrayList8;
                            z2 = z3;
                            fragmentTransitionImpl = fragmentTransitionImpl2;
                            fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList2);
                            operation5 = operation;
                            hashMap2 = hashMap4;
                            hashMap2.put(operation5, true);
                            operation4 = operation2;
                            hashMap2.put(operation4, true);
                            obj3 = wrapTransitionInSet;
                        } else {
                            rect2 = rect4;
                        }
                    }
                    view3 = view9;
                    fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view3, arrayList9);
                    rect = rect2;
                    arrayList5 = arrayList9;
                    arrayList2 = arrayList8;
                    z2 = z3;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList2);
                    operation5 = operation;
                    hashMap2 = hashMap4;
                    hashMap2.put(operation5, true);
                    operation4 = operation2;
                    hashMap2.put(operation4, true);
                    obj3 = wrapTransitionInSet;
                }
            }
            z4 = z;
            view4 = view3;
            rect3 = rect;
            arrayList6 = arrayList2;
            z5 = z2;
            operation6 = operation5;
            hashMap3 = hashMap2;
            operation7 = operation4;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            arrayMap2 = arrayMap;
        }
        View view12 = view5;
        ArrayMap arrayMap5 = arrayMap2;
        SpecialEffectsController.Operation operation8 = operation7;
        View view13 = view4;
        boolean z7 = z5;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        HashMap hashMap5 = hashMap3;
        SpecialEffectsController.Operation operation9 = operation6;
        ArrayList<View> arrayList10 = arrayList6;
        Rect rect5 = rect3;
        ArrayList arrayList11 = new ArrayList();
        Iterator<TransitionInfo> it3 = list.iterator();
        Object obj4 = null;
        Object obj5 = null;
        while (it3.hasNext()) {
            TransitionInfo next = it3.next();
            if (next.isVisibilityUnchanged()) {
                hashMap5.put(next.getOperation(), Boolean.valueOf(z7));
                removeCancellationSignal(next.getOperation(), next.getSignal());
                it3 = it3;
            } else {
                Iterator<TransitionInfo> it4 = it3;
                Object cloneTransition = fragmentTransitionImpl3.cloneTransition(next.getTransition());
                SpecialEffectsController.Operation operation10 = next.getOperation();
                boolean z8 = (obj3 == null || !(operation10 == operation9 || operation10 == operation8)) ? z7 : true;
                if (cloneTransition == null) {
                    if (!z8) {
                        hashMap5.put(operation10, Boolean.valueOf(z7));
                        removeCancellationSignal(operation10, next.getSignal());
                    }
                    view = view13;
                    arrayList = arrayList5;
                    hashMap = hashMap5;
                    view2 = view12;
                } else {
                    final ArrayList<View> arrayList12 = new ArrayList<>();
                    captureTransitioningViews(arrayList12, operation10.getFragment().mView);
                    if (z8) {
                        if (operation10 == operation9) {
                            arrayList12.removeAll(arrayList5);
                        } else {
                            arrayList12.removeAll(arrayList10);
                        }
                    }
                    if (arrayList12.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(cloneTransition, view13);
                        view = view13;
                        arrayList = arrayList5;
                        operation3 = operation10;
                        obj = obj4;
                        obj2 = obj5;
                        hashMap = hashMap5;
                    } else {
                        fragmentTransitionImpl3.addTargets(cloneTransition, arrayList12);
                        operation3 = operation10;
                        view = view13;
                        obj = obj4;
                        obj2 = obj5;
                        arrayList = arrayList5;
                        hashMap = hashMap5;
                        fragmentTransitionImpl3.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList12, null, null, null, null);
                        if (operation3.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            fragmentTransitionImpl3.scheduleHideFragmentView(cloneTransition, operation3.getFragment().mView, arrayList12);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList12, 4);
                                }
                            });
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList11.addAll(arrayList12);
                        if (z6) {
                            fragmentTransitionImpl3.setEpicenter(cloneTransition, rect5);
                        }
                        view2 = view12;
                    } else {
                        view2 = view12;
                        fragmentTransitionImpl3.setEpicenter(cloneTransition, view2);
                    }
                    hashMap.put(operation3, true);
                    if (next.isOverlapAllowed()) {
                        obj4 = obj;
                        obj5 = fragmentTransitionImpl3.mergeTransitionsTogether(obj2, cloneTransition, null);
                    } else {
                        obj4 = fragmentTransitionImpl3.mergeTransitionsTogether(obj, cloneTransition, null);
                        obj5 = obj2;
                    }
                }
                it3 = it4;
                operation9 = operation;
                operation8 = operation2;
                hashMap5 = hashMap;
                view12 = view2;
                view13 = view;
                arrayList5 = arrayList;
                z7 = false;
            }
        }
        ArrayList<View> arrayList13 = arrayList5;
        HashMap hashMap6 = hashMap5;
        Object mergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj5, obj4, obj3);
        for (final TransitionInfo transitionInfo4 : list) {
            if (!transitionInfo4.isVisibilityUnchanged() && transitionInfo4.getTransition() != null) {
                fragmentTransitionImpl3.setListenerForTransitionEnd(transitionInfo4.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo4.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.10
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultSpecialEffectsController.this.removeCancellationSignal(transitionInfo4.getOperation(), transitionInfo4.getSignal());
                    }
                });
            }
        }
        FragmentTransition.setViewVisibility(arrayList11, 4);
        ArrayList<String> prepareSetNameOverridesReordered = fragmentTransitionImpl3.prepareSetNameOverridesReordered(arrayList10);
        fragmentTransitionImpl3.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl3.setNameOverridesReordered(getContainer(), arrayList13, arrayList10, prepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList11, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj3, arrayList13, arrayList10);
        return hashMap6;
    }

    void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
            return;
        }
        arrayList.add(view);
    }

    void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class AnimationInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }
    }

    /* loaded from: classes.dex */
    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class TransitionInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final CancellationSignal mSignal;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            Object exitTransition;
            Object enterTransition;
            boolean allowEnterTransitionOverlap;
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (z) {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = allowEnterTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.mOperation.getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.mOperation.getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
