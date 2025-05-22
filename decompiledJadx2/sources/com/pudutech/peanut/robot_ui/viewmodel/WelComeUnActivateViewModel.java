package com.pudutech.peanut.robot_ui.viewmodel;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WelComeUnActivateViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeUnActivateViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "animationView", "Landroid/view/View;", "mActivateChangeState", "Landroidx/lifecycle/MutableLiveData;", "", "getMActivateChangeState", "()Landroidx/lifecycle/MutableLiveData;", "mCloseAction", "Landroid/view/animation/TranslateAnimation;", "mShowAction", "closeView", "", "initAnimation", "showView", "mView", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeUnActivateViewModel extends BaseViewModel {
    private View animationView;
    private TranslateAnimation mCloseAction;
    private TranslateAnimation mShowAction;
    private final String TAG = "WelComeUnActivateViewModel";
    private final MutableLiveData<Integer> mActivateChangeState = new MutableLiveData<>();

    public WelComeUnActivateViewModel() {
        initAnimation();
    }

    public final MutableLiveData<Integer> getMActivateChangeState() {
        return this.mActivateChangeState;
    }

    private final void initAnimation() {
        this.mShowAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        TranslateAnimation translateAnimation = this.mShowAction;
        if (translateAnimation != null) {
            translateAnimation.setDuration(500L);
        }
        this.mCloseAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        TranslateAnimation translateAnimation2 = this.mCloseAction;
        if (translateAnimation2 != null) {
            translateAnimation2.setDuration(500L);
        }
        TranslateAnimation translateAnimation3 = this.mCloseAction;
        if (translateAnimation3 != null) {
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.WelComeUnActivateViewModel$initAnimation$1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation p0) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation p0) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation p0) {
                    View view;
                    view = WelComeUnActivateViewModel.this.animationView;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                }
            });
        }
    }

    public final void showView(View mView) {
        Intrinsics.checkParameterIsNotNull(mView, "mView");
        this.animationView = mView;
        View view = this.animationView;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = this.animationView;
        if (view2 != null) {
            view2.startAnimation(this.mShowAction);
        }
    }

    public final void closeView() {
        View view = this.animationView;
        if (view != null) {
            if (!(view.getVisibility() == 0)) {
                return;
            }
        }
        View view2 = this.animationView;
        if (view2 != null) {
            view2.startAnimation(this.mCloseAction);
        }
    }
}
