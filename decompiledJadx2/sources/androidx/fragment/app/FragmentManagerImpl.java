package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.activity.OnBackPressedCallback;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class FragmentManagerImpl extends FragmentManager {

    /* loaded from: classes.dex */
    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* loaded from: classes.dex */
    private static final class FragmentLifecycleCallbacksHolder {
        final FragmentManager.FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;

        FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            this.mCallback = fragmentLifecycleCallbacks;
            this.mRecursive = z;
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManagerImpl$1 */
    /* loaded from: classes.dex */
    class C03971 extends OnBackPressedCallback {
        C03971(boolean z) {
            super(z);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FragmentManagerImpl.this.handleOnBackPressed();
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManagerImpl$2 */
    /* loaded from: classes.dex */
    class RunnableC03982 implements Runnable {
        RunnableC03982() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManagerImpl$3 */
    /* loaded from: classes.dex */
    class AnimationAnimationListenerC03993 implements Animation.AnimationListener {
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ Fragment val$fragment;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnimationAnimationListenerC03993(ViewGroup viewGroup, Fragment fragment) {
            this.val$container = viewGroup;
            this.val$fragment = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.val$container.post(new Runnable() { // from class: androidx.fragment.app.FragmentManagerImpl.3.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AnimationAnimationListenerC03993.this.val$fragment.getAnimatingAway() != null) {
                        AnimationAnimationListenerC03993.this.val$fragment.setAnimatingAway(null);
                        FragmentManagerImpl.this.moveToState(AnimationAnimationListenerC03993.this.val$fragment, AnimationAnimationListenerC03993.this.val$fragment.getStateAfterAnimating(), 0, 0, false);
                    }
                }
            });
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManagerImpl$4 */
    /* loaded from: classes.dex */
    class C04004 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ Fragment val$fragment;
        final /* synthetic */ View val$viewToAnimate;

        C04004(ViewGroup viewGroup, View view, Fragment fragment) {
            this.val$container = viewGroup;
            this.val$viewToAnimate = view;
            this.val$fragment = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.val$container.endViewTransition(this.val$viewToAnimate);
            Animator animator2 = this.val$fragment.getAnimator();
            this.val$fragment.setAnimator(null);
            if (animator2 == null || this.val$container.indexOfChild(this.val$viewToAnimate) >= 0) {
                return;
            }
            FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
            Fragment fragment = this.val$fragment;
            fragmentManagerImpl.moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManagerImpl$5 */
    /* loaded from: classes.dex */
    class C04015 extends AnimatorListenerAdapter {
        final /* synthetic */ View val$animatingView;
        final /* synthetic */ ViewGroup val$container;
        final /* synthetic */ Fragment val$fragment;

        C04015(ViewGroup viewGroup, View view, Fragment fragment) {
            this.val$container = viewGroup;
            this.val$animatingView = view;
            this.val$fragment = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.val$container.endViewTransition(this.val$animatingView);
            animator.removeListener(this);
            if (this.val$fragment.mView == null || !this.val$fragment.mHidden) {
                return;
            }
            this.val$fragment.mView.setVisibility(8);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManagerImpl$6 */
    /* loaded from: classes.dex */
    class C04026 extends FragmentFactory {
        C04026() {
        }

        @Override // androidx.fragment.app.FragmentFactory
        public Fragment instantiate(ClassLoader classLoader, String str) {
            return FragmentManagerImpl.this.mHost.instantiate(FragmentManagerImpl.this.mHost.getContext(), str, null);
        }
    }

    /* loaded from: classes.dex */
    static class FragmentTag {
        public static final int[] Fragment = {R.attr.name, R.attr.id, R.attr.tag};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        private FragmentTag() {
        }
    }

    /* loaded from: classes.dex */
    private class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String str, int i, int i2) {
            this.mName = str;
            this.mId = i;
            this.mFlags = i2;
        }

        @Override // androidx.fragment.app.FragmentManagerImpl.OpGenerator
        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            if (FragmentManagerImpl.this.mPrimaryNav == null || this.mId >= 0 || this.mName != null || !FragmentManagerImpl.this.mPrimaryNav.getChildFragmentManager().popBackStackImmediate()) {
                return FragmentManagerImpl.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        final boolean mIsBack;
        private int mNumPostponed;
        final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord backStackRecord, boolean z) {
            this.mIsBack = z;
            this.mRecord = backStackRecord;
        }

        @Override // androidx.fragment.app.Fragment.OnStartEnterTransitionListener
        public void onStartEnterTransition() {
            int i = this.mNumPostponed - 1;
            this.mNumPostponed = i;
            if (i != 0) {
                return;
            }
            this.mRecord.mManager.scheduleCommit();
        }

        @Override // androidx.fragment.app.Fragment.OnStartEnterTransitionListener
        public void startListening() {
            this.mNumPostponed++;
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void completeTransaction() {
            boolean z = this.mNumPostponed > 0;
            FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
            int size = fragmentManagerImpl.mAdded.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) fragmentManagerImpl.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, !z, true);
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }
    }

    /* loaded from: classes.dex */
    private static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animation animation) {
            this.animation = animation;
            this.animator = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        AnimationOrAnimator(Animator animator) {
            this.animation = null;
            this.animator = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* loaded from: classes.dex */
    private static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimation(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.mAnimating = true;
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
            this.mParent.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mEnded && this.mAnimating) {
                this.mAnimating = false;
                this.mParent.post(this);
            } else {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
            }
        }
    }
}
