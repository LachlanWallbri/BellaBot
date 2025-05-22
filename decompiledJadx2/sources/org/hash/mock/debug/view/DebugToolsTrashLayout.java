package org.hash.mock.debug.view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.pudutech.disinfect.baselib.C4429R;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DebugToolsTrashLayout extends FrameLayout {
    public static final int VIBRATION_DURATION_IN_MS = 70;
    private boolean attachedToWindow;
    private boolean isVibrateInThisSession;
    private DebugToolsCoordinator layoutCoordinator;
    private boolean magnetismApplied;

    public DebugToolsTrashLayout(Context context) {
        super(context);
        this.magnetismApplied = false;
        this.attachedToWindow = false;
        this.isVibrateInThisSession = false;
    }

    public DebugToolsTrashLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.magnetismApplied = false;
        this.attachedToWindow = false;
        this.isVibrateInThisSession = false;
    }

    public DebugToolsTrashLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.magnetismApplied = false;
        this.attachedToWindow = false;
        this.isVibrateInThisSession = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (this.attachedToWindow && i != getVisibility()) {
            if (i == 0) {
                playAnimation(C4429R.animator.debug_tools_trash_shown_animator);
            } else {
                playAnimation(C4429R.animator.debug_tools_trash_hide_animator);
            }
        }
        super.setVisibility(i);
    }

    public void applyMagnetism() {
        if (this.magnetismApplied) {
            return;
        }
        this.magnetismApplied = true;
        playAnimation(C4429R.animator.debug_tools_trash_shown_magnetism_animator);
    }

    public void vibrate() {
        if (this.isVibrateInThisSession) {
            return;
        }
        ((Vibrator) getContext().getSystemService("vibrator")).vibrate(70L);
        this.isVibrateInThisSession = true;
    }

    public void releaseMagnetism() {
        if (this.magnetismApplied) {
            this.magnetismApplied = false;
            playAnimation(C4429R.animator.debug_tools_trash_hide_magnetism_animator);
        }
        this.isVibrateInThisSession = false;
    }

    private void playAnimation(int i) {
        if (isInEditMode()) {
            return;
        }
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), i);
        animatorSet.setTarget(getChildAt(0));
        animatorSet.start();
    }

    public void setLayoutCoordinator(DebugToolsCoordinator debugToolsCoordinator) {
        this.layoutCoordinator = debugToolsCoordinator;
    }

    DebugToolsCoordinator getLayoutCoordinator() {
        return this.layoutCoordinator;
    }
}
