package com.pudutech.bumblebee.robot_ui.p054ui.view.floatview;

import android.R;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.badge.BadgeDrawable;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class FloatingView implements IFloatingView {
    private static volatile FloatingView mInstance;
    private WeakReference<FrameLayout> mContainer;
    private FloatingMagnetView mEnFloatingView;
    private int mLayoutId = 0;
    private int mIconRes = 0;
    private ViewGroup.LayoutParams mLayoutParams = getParams();

    private FloatingView() {
    }

    public static FloatingView get() {
        if (mInstance == null) {
            synchronized (FloatingView.class) {
                if (mInstance == null) {
                    mInstance = new FloatingView();
                }
            }
        }
        return mInstance;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.floatview.FloatingView.1
            @Override // java.lang.Runnable
            public void run() {
                if (FloatingView.this.mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(FloatingView.this.mEnFloatingView) && FloatingView.this.getContainer() != null) {
                    FloatingView.this.getContainer().removeView(FloatingView.this.mEnFloatingView);
                }
                FloatingView.this.mEnFloatingView = null;
            }
        });
        return this;
    }

    private void ensureFloatingView() {
        synchronized (this) {
            if (this.mEnFloatingView == null && this.mLayoutId != 0) {
                EnFloatingView enFloatingView = new EnFloatingView(RobotContext.context, this.mLayoutId);
                this.mEnFloatingView = enFloatingView;
                enFloatingView.setLayoutParams(this.mLayoutParams);
                if (this.mIconRes != 0) {
                    enFloatingView.setIconImage(this.mIconRes);
                }
                addViewToWindow(enFloatingView);
            }
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView add() {
        ensureFloatingView();
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView attach(FrameLayout frameLayout) {
        FloatingMagnetView floatingMagnetView;
        if (frameLayout == null || (floatingMagnetView = this.mEnFloatingView) == null) {
            this.mContainer = new WeakReference<>(frameLayout);
            return this;
        }
        if (floatingMagnetView.getParent() == frameLayout) {
            return this;
        }
        if (getContainer() != null && this.mEnFloatingView.getParent() == getContainer()) {
            getContainer().removeView(this.mEnFloatingView);
        }
        this.mContainer = new WeakReference<>(frameLayout);
        frameLayout.addView(this.mEnFloatingView);
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView detach(FrameLayout frameLayout) {
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null && frameLayout != null && ViewCompat.isAttachedToWindow(floatingMagnetView)) {
            frameLayout.removeView(this.mEnFloatingView);
        }
        if (getContainer() == frameLayout) {
            this.mContainer = null;
        }
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingMagnetView getView() {
        return this.mEnFloatingView;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView icon(int i) {
        this.mIconRes = i;
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView customView(FloatingMagnetView floatingMagnetView) {
        this.mEnFloatingView = floatingMagnetView;
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView customView(int i) {
        this.mLayoutId = i;
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView layoutParams(ViewGroup.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null) {
            floatingMagnetView.setLayoutParams(layoutParams);
        }
        return this;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.view.floatview.IFloatingView
    public FloatingView listener(MagnetViewListener magnetViewListener) {
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null) {
            floatingMagnetView.setMagnetViewListener(magnetViewListener);
        }
        return this;
    }

    private void addViewToWindow(View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FrameLayout getContainer() {
        WeakReference<FrameLayout> weakReference = this.mContainer;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = BadgeDrawable.BOTTOM_START;
        layoutParams.setMargins(13, layoutParams.topMargin, layoutParams.rightMargin, 500);
        return layoutParams;
    }

    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
