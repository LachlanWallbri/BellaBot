package com.pudutech.bumblebee.robot_ui.p054ui.view.videoface;

import android.R;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceVideoViewManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u0000H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0012\u0010\u000f\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\tH\u0002J\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0012\u0010\u0012\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoViewManager;", "", "()V", "TAG", "", "animationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "container", "Ljava/lang/ref/WeakReference;", "Landroid/widget/FrameLayout;", TmpConstant.GROUP_OP_ADD, "addViewToWindow", "", "view", "Landroid/view/View;", "attach", "activity", "Landroid/app/Activity;", "detach", "ensureFaceVideoView", "getActivityRoot", "getContainer", "getView", "play", "anim", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "playAnimation", "ac", "remove", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FaceVideoViewManager {
    private static FaceVideoView animationView;
    private static WeakReference<FrameLayout> container;
    public static final FaceVideoViewManager INSTANCE = new FaceVideoViewManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private FaceVideoViewManager() {
    }

    public final void remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.videoface.FaceVideoViewManager$remove$1
            @Override // java.lang.Runnable
            public final void run() {
                FaceVideoView faceVideoView;
                FaceVideoView faceVideoView2;
                FrameLayout container2;
                FrameLayout container3;
                FaceVideoView faceVideoView3;
                FaceVideoViewManager faceVideoViewManager = FaceVideoViewManager.INSTANCE;
                faceVideoView = FaceVideoViewManager.animationView;
                if (faceVideoView == null) {
                    return;
                }
                FaceVideoViewManager faceVideoViewManager2 = FaceVideoViewManager.INSTANCE;
                faceVideoView2 = FaceVideoViewManager.animationView;
                if (faceVideoView2 == null) {
                    Intrinsics.throwNpe();
                }
                if (ViewCompat.isAttachedToWindow(faceVideoView2)) {
                    container2 = FaceVideoViewManager.INSTANCE.getContainer();
                    if (container2 != null) {
                        container3 = FaceVideoViewManager.INSTANCE.getContainer();
                        if (container3 == null) {
                            Intrinsics.throwNpe();
                        }
                        FaceVideoViewManager faceVideoViewManager3 = FaceVideoViewManager.INSTANCE;
                        faceVideoView3 = FaceVideoViewManager.animationView;
                        container3.removeView(faceVideoView3);
                    }
                }
                FaceVideoViewManager faceVideoViewManager4 = FaceVideoViewManager.INSTANCE;
                FaceVideoViewManager.animationView = (FaceVideoView) null;
            }
        });
    }

    private final void ensureFaceVideoView() {
        synchronized (this) {
            if (animationView != null) {
                return;
            }
            animationView = new FaceVideoView(RobotContext.INSTANCE.getContext());
            FaceVideoViewManager faceVideoViewManager = INSTANCE;
            FaceVideoView faceVideoView = animationView;
            if (faceVideoView == null) {
                Intrinsics.throwNpe();
            }
            faceVideoViewManager.addViewToWindow(faceVideoView);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final FaceVideoViewManager add() {
        ensureFaceVideoView();
        return this;
    }

    public final void playAnimation(Activity ac, FaceVideoAnimation anim) {
        Intrinsics.checkParameterIsNotNull(ac, "ac");
        Intrinsics.checkParameterIsNotNull(anim, "anim");
        attach(ac).add().play(anim);
    }

    private final FaceVideoViewManager play(FaceVideoAnimation anim) {
        FaceVideoView faceVideoView = animationView;
        if (faceVideoView != null) {
            faceVideoView.playAnimation(anim);
        }
        return this;
    }

    private final FaceVideoViewManager attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    private final FaceVideoViewManager attach(FrameLayout container2) {
        FaceVideoView faceVideoView;
        if (container2 == null || (faceVideoView = animationView) == null) {
            container = new WeakReference<>(container2);
            return this;
        }
        if (faceVideoView == null) {
            Intrinsics.throwNpe();
        }
        if (faceVideoView.getParent() == container2) {
            return this;
        }
        FaceVideoView faceVideoView2 = animationView;
        if ((faceVideoView2 != null ? faceVideoView2.getParent() : null) == getContainer()) {
            FrameLayout container3 = getContainer();
            if (container3 == null) {
                Intrinsics.throwNpe();
            }
            container3.removeView(animationView);
        }
        container = new WeakReference<>(container2);
        container2.addView(animationView);
        return this;
    }

    public final FaceVideoViewManager detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    private final FaceVideoViewManager detach(FrameLayout container2) {
        FaceVideoView faceVideoView = animationView;
        if (faceVideoView != null && container2 != null) {
            if (faceVideoView == null) {
                Intrinsics.throwNpe();
            }
            if (ViewCompat.isAttachedToWindow(faceVideoView)) {
                container2.removeView(animationView);
            }
        }
        if (getContainer() == container2) {
            container = (WeakReference) null;
        }
        return this;
    }

    public final FaceVideoView getView() {
        return animationView;
    }

    private final void addViewToWindow(View view) {
        if (getContainer() == null) {
            return;
        }
        FrameLayout container2 = getContainer();
        if (container2 == null) {
            Intrinsics.throwNpe();
        }
        container2.addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FrameLayout getContainer() {
        WeakReference<FrameLayout> weakReference = container;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private final FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            Window window = activity.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
            View findViewById = window.getDecorView().findViewById(R.id.content);
            if (findViewById != null) {
                return (FrameLayout) findViewById;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout");
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "getActivityRoot : " + Log.getStackTraceString(e));
            return null;
        }
    }
}
