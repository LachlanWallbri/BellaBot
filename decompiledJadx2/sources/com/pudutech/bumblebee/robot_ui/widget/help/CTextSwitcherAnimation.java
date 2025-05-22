package com.pudutech.bumblebee.robot_ui.widget.help;

import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CTextSwitcherAnimation.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\u001eB!\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\tH\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\tJ\b\u0010\u001c\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0018\u00010\u0012R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/help/CTextSwitcherAnimation;", "", "textSwitcher", "Landroid/widget/TextSwitcher;", "texts", "", "", "(Landroid/widget/TextSwitcher;[Ljava/lang/String;)V", "delayTime", "", TypedValues.Transition.S_DURATION, "handler", "Landroid/os/Handler;", "inAnimationSet", "Landroid/view/animation/AnimationSet;", RequestParameters.MARKER, "outAnimationSet", "task", "Lcom/pudutech/bumblebee/robot_ui/widget/help/CTextSwitcherAnimation$Task;", "[Ljava/lang/String;", "create", "", "createAnimation", "getMarker", ES6Iterator.NEXT_METHOD, "release", "setDelayTime", "setDuration", "start", "stop", "Task", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CTextSwitcherAnimation {
    private int delayTime;
    private int duration;
    private AnimationSet inAnimationSet;
    private int marker;
    private AnimationSet outAnimationSet;
    private TextSwitcher textSwitcher;
    private String[] texts;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.widget.help.CTextSwitcherAnimation$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return false;
        }
    });
    private Task task = new Task();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CTextSwitcherAnimation.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/help/CTextSwitcherAnimation$Task;", "Ljava/lang/Runnable;", "(Lcom/pudutech/bumblebee/robot_ui/widget/help/CTextSwitcherAnimation;)V", "run", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class Task implements Runnable {
        public Task() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CTextSwitcherAnimation.this.next();
            Task task = CTextSwitcherAnimation.this.task;
            if (task != null) {
                CTextSwitcherAnimation.this.handler.postDelayed(task, CTextSwitcherAnimation.this.delayTime * 2);
            }
        }
    }

    public CTextSwitcherAnimation(TextSwitcher textSwitcher, String[] strArr) {
        this.textSwitcher = textSwitcher;
        this.texts = strArr;
    }

    private final void start() {
        stop();
        Task task = this.task;
        if (task != null) {
            this.handler.postDelayed(task, this.delayTime + 0);
        }
    }

    private final void stop() {
        Task task = this.task;
        if (task != null) {
            this.handler.removeCallbacks(task);
        }
    }

    private final int getMarker() {
        return this.marker;
    }

    public final void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public final void setDuration(int duration) {
        this.duration = duration;
    }

    public final void create() {
        TextSwitcher textSwitcher;
        this.marker = 0;
        String[] strArr = this.texts;
        if (strArr != null) {
            if ((strArr == null || strArr.length != 0) && (textSwitcher = this.textSwitcher) != null) {
                if (textSwitcher == null) {
                    Intrinsics.throwNpe();
                }
                String[] strArr2 = this.texts;
                if (strArr2 == null) {
                    Intrinsics.throwNpe();
                }
                textSwitcher.setText(strArr2[0]);
                createAnimation();
                TextSwitcher textSwitcher2 = this.textSwitcher;
                if (textSwitcher2 == null) {
                    Intrinsics.throwNpe();
                }
                textSwitcher2.setInAnimation(this.inAnimationSet);
                TextSwitcher textSwitcher3 = this.textSwitcher;
                if (textSwitcher3 == null) {
                    Intrinsics.throwNpe();
                }
                textSwitcher3.setOutAnimation(this.outAnimationSet);
                start();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [T, android.view.animation.Animation] */
    /* JADX WARN: Type inference failed for: r3v13, types: [T, android.view.animation.Animation] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, android.view.animation.Animation] */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, android.view.animation.Animation] */
    private final void createAnimation() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        TextSwitcher textSwitcher = this.textSwitcher;
        if (textSwitcher != null) {
            int height = textSwitcher.getHeight();
            if (height <= 0) {
                textSwitcher.measure(0, 0);
                height = textSwitcher.getMeasuredHeight();
            }
            this.inAnimationSet = new AnimationSet(true);
            this.outAnimationSet = new AnimationSet(true);
            objectRef.element = new AlphaAnimation(0.0f, 1.0f);
            objectRef2.element = new TranslateAnimation(0, 0.0f, 0, 0.0f, 0, height + 0.0f, 0, 0.0f);
            AnimationSet animationSet = this.inAnimationSet;
            if (animationSet == null) {
                Intrinsics.throwNpe();
            }
            animationSet.addAnimation((Animation) objectRef.element);
            AnimationSet animationSet2 = this.inAnimationSet;
            if (animationSet2 == null) {
                Intrinsics.throwNpe();
            }
            animationSet2.addAnimation((Animation) objectRef2.element);
            AnimationSet animationSet3 = this.inAnimationSet;
            if (animationSet3 == null) {
                Intrinsics.throwNpe();
            }
            animationSet3.setDuration(this.duration + 0);
            objectRef.element = new AlphaAnimation(1.0f, 0.0f);
            objectRef2.element = new TranslateAnimation(0, 0.0f, 0, 0.0f, 0, 0.0f, 0, (-height) + 0.0f);
            AnimationSet animationSet4 = this.outAnimationSet;
            if (animationSet4 == null) {
                Intrinsics.throwNpe();
            }
            animationSet4.addAnimation((Animation) objectRef.element);
            AnimationSet animationSet5 = this.outAnimationSet;
            if (animationSet5 == null) {
                Intrinsics.throwNpe();
            }
            animationSet5.addAnimation((Animation) objectRef2.element);
            AnimationSet animationSet6 = this.outAnimationSet;
            if (animationSet6 == null) {
                Intrinsics.throwNpe();
            }
            animationSet6.setDuration(this.duration + 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void next() {
        this.marker++;
        int i = this.marker;
        String[] strArr = this.texts;
        if (strArr == null) {
            Intrinsics.throwNpe();
        }
        this.marker = i % strArr.length;
        TextSwitcher textSwitcher = this.textSwitcher;
        if (textSwitcher == null) {
            Intrinsics.throwNpe();
        }
        String[] strArr2 = this.texts;
        if (strArr2 == null) {
            Intrinsics.throwNpe();
        }
        textSwitcher.setText(strArr2[this.marker]);
    }

    public final void release() {
        this.handler.removeCallbacksAndMessages(null);
        this.task = (Task) null;
    }
}
