package com.pudutech.peanut.robot_ui.viewmodel;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.peanut.robot_ui.bean.WeComeBean;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.widget.MarqueeTextView;
import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WelComeActivateViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007*\u0001\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u001cH\u0002J\u0006\u0010\u001f\u001a\u00020\u001cJ\u0006\u0010 \u001a\u00020\u001cJ\u0018\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011¨\u0006#"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "animationView", "Landroid/view/View;", "count", "", "iVoiceReponseTextListener", "com/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel$iVoiceReponseTextListener$1", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeActivateViewModel$iVoiceReponseTextListener$1;", "mCloseAction", "Landroid/view/animation/TranslateAnimation;", "mInstructionState", "Landroidx/lifecycle/MutableLiveData;", "getMInstructionState", "()Landroidx/lifecycle/MutableLiveData;", "mShowAction", "mState", "mTextInput", "Lcom/pudutech/peanut/robot_ui/widget/MarqueeTextView;", "mVoiceChangeState", "getMVoiceChangeState", "msgModel", "Lcom/pudutech/peanut/robot_ui/bean/WeComeBean;", "getMsgModel", "addVoiceInteraction", "", "closeView", "initAnimation", "removeTextListener", "resetVmState", "showView", "mView", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeActivateViewModel extends BaseViewModel {
    private View animationView;
    private TranslateAnimation mCloseAction;
    private TranslateAnimation mShowAction;
    private volatile int mState;
    private MarqueeTextView mTextInput;
    private final String TAG = "WecomeViewModel";
    private final MutableLiveData<WeComeBean> msgModel = new MutableLiveData<>();
    private final MutableLiveData<Integer> mVoiceChangeState = new MutableLiveData<>();
    private final MutableLiveData<Integer> mInstructionState = new MutableLiveData<>();
    private volatile int count = 3;
    private final WelComeActivateViewModel$iVoiceReponseTextListener$1 iVoiceReponseTextListener = new IVoiceReponseTextListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.WelComeActivateViewModel$iVoiceReponseTextListener$1
        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void dismissCountdown(int countdown) {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "dismissCountdown " + countdown);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void finishVoice() {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "finishVoice");
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void loading(boolean isShow) {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "loading " + isShow);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void palyCompelete(int state) {
            String str;
            WelComeActivateViewModel.this.getMVoiceChangeState().postValue(Integer.valueOf(state));
            WelComeActivateViewModel.this.mState = 0;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "palyCompelete " + state);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void reWakeup(boolean isShow) {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "reWakeup " + isShow);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void requestText(String data, boolean isFinsh) {
            String str;
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, ' ' + data + "  isFish: " + isFinsh);
            if (data != null) {
                if (isFinsh) {
                    i5 = WelComeActivateViewModel.this.mState;
                    if (i5 == 0) {
                        WelComeActivateViewModel.this.mState = 1;
                        MutableLiveData<WeComeBean> msgModel = WelComeActivateViewModel.this.getMsgModel();
                        i6 = WelComeActivateViewModel.this.count;
                        msgModel.postValue(new WeComeBean(i6, 1, data, "", Boolean.valueOf(isFinsh)));
                    }
                } else {
                    MutableLiveData<WeComeBean> msgModel2 = WelComeActivateViewModel.this.getMsgModel();
                    i4 = WelComeActivateViewModel.this.count;
                    msgModel2.postValue(new WeComeBean(i4, 1, data, "", Boolean.valueOf(isFinsh)));
                }
            }
            if (isFinsh) {
                WelComeActivateViewModel.this.getMVoiceChangeState().postValue(3);
                if (data != null) {
                    String str2 = data;
                    if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "特色菜", false, 2, (Object) null)) {
                        WelComeActivateViewModel.this.mState = 0;
                        WelComeActivateViewModel.this.getMInstructionState().postValue(1);
                        WelComeActivateViewModel welComeActivateViewModel = WelComeActivateViewModel.this;
                        i3 = welComeActivateViewModel.count;
                        welComeActivateViewModel.count = i3 + 1;
                        return;
                    }
                    if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "优惠活动", false, 2, (Object) null)) {
                        WelComeActivateViewModel.this.mState = 0;
                        WelComeActivateViewModel.this.getMInstructionState().postValue(2);
                        WelComeActivateViewModel welComeActivateViewModel2 = WelComeActivateViewModel.this;
                        i2 = welComeActivateViewModel2.count;
                        welComeActivateViewModel2.count = i2 + 1;
                        return;
                    }
                    if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "带我进店", false, 2, (Object) null)) {
                        WelComeActivateViewModel.this.mState = 0;
                        WelComeActivateViewModel.this.getMInstructionState().postValue(3);
                        WelComeActivateViewModel welComeActivateViewModel3 = WelComeActivateViewModel.this;
                        i = welComeActivateViewModel3.count;
                        welComeActivateViewModel3.count = i + 1;
                    }
                }
            }
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void requestTimeout() {
            String str;
            WelComeActivateViewModel.this.mState = 0;
            WelComeActivateViewModel.this.getMVoiceChangeState().postValue(2);
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "requestTimeout");
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void responseText(String data) {
            int i;
            int i2;
            String str;
            WelComeActivateViewModel welComeActivateViewModel = WelComeActivateViewModel.this;
            i = welComeActivateViewModel.count;
            welComeActivateViewModel.count = i + 1;
            MutableLiveData<WeComeBean> msgModel = WelComeActivateViewModel.this.getMsgModel();
            i2 = WelComeActivateViewModel.this.count;
            msgModel.postValue(new WeComeBean(i2, 2, data != null ? data : "", "", null, 16, null));
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "responseText " + data);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void showDialog(int random) {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "showDialog " + random);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void volumeChange(boolean isShow) {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "volumeChange " + isShow);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void volumeValue(int value) {
            String str;
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "volumeValue " + value);
        }

        @Override // com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener
        public void wakeup(String data, int random) {
            String str;
            WelComeActivateViewModel.this.getMVoiceChangeState().postValue(6);
            str = WelComeActivateViewModel.this.TAG;
            Pdlog.m3273d(str, "wakeup " + data + " random " + random);
        }
    };

    /* JADX WARN: Type inference failed for: r0v5, types: [com.pudutech.peanut.robot_ui.viewmodel.WelComeActivateViewModel$iVoiceReponseTextListener$1] */
    public WelComeActivateViewModel() {
        addVoiceInteraction();
        initAnimation();
    }

    public final MutableLiveData<WeComeBean> getMsgModel() {
        return this.msgModel;
    }

    public final MutableLiveData<Integer> getMVoiceChangeState() {
        return this.mVoiceChangeState;
    }

    public final MutableLiveData<Integer> getMInstructionState() {
        return this.mInstructionState;
    }

    private final void addVoiceInteraction() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.attachNullActivity(this.iVoiceReponseTextListener);
    }

    private final void initAnimation() {
        this.mShowAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        TranslateAnimation translateAnimation = this.mShowAction;
        if (translateAnimation != null) {
            translateAnimation.setDuration(300L);
        }
        this.mCloseAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        TranslateAnimation translateAnimation2 = this.mCloseAction;
        if (translateAnimation2 != null) {
            translateAnimation2.setDuration(300L);
        }
        TranslateAnimation translateAnimation3 = this.mCloseAction;
        if (translateAnimation3 != null) {
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.WelComeActivateViewModel$initAnimation$1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation p0) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation p0) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation p0) {
                    View view;
                    MarqueeTextView marqueeTextView;
                    view = WelComeActivateViewModel.this.animationView;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                    marqueeTextView = WelComeActivateViewModel.this.mTextInput;
                    if (marqueeTextView != null) {
                        marqueeTextView.setText("");
                    }
                }
            });
        }
    }

    public final void showView(View mView, MarqueeTextView mTextInput) {
        Intrinsics.checkParameterIsNotNull(mView, "mView");
        this.animationView = mView;
        this.mTextInput = mTextInput;
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

    public final void resetVmState() {
        this.mInstructionState.setValue(0);
        this.mVoiceChangeState.setValue(0);
        this.msgModel.setValue(new WeComeBean(this.count, -1, "", "", null, 16, null));
    }

    public final void removeTextListener() {
        AiVoiceManager.INSTANCE.detachActivity();
    }
}
