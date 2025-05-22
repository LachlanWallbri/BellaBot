package com.pudutech.bumblebee.robot_ui.widget.loopview;

import android.util.Log;

/* loaded from: classes4.dex */
final class InertiaTimerTask implements Runnable {

    /* renamed from: a */
    float f4995a = 2.14748365E9f;
    final LoopView loopView;
    final float velocityY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InertiaTimerTask(LoopView loopView, float f) {
        this.loopView = loopView;
        this.velocityY = f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f4995a == 2.14748365E9f) {
            if (Math.abs(this.velocityY) <= 2000.0f) {
                this.f4995a = this.velocityY;
            } else if (this.velocityY > 0.0f) {
                this.f4995a = 2000.0f;
            } else {
                this.f4995a = -2000.0f;
            }
        }
        if (Math.abs(this.f4995a) >= 0.0f && Math.abs(this.f4995a) <= 20.0f) {
            Log.i("gy", "WHAT_SMOOTH_SCROLL_INERTIA");
            this.loopView.handler.sendEmptyMessageDelayed(MessageHandler.WHAT_SMOOTH_SCROLL_INERTIA, 60L);
            this.loopView.cancelFuture();
            this.loopView.handler.sendEmptyMessage(2000);
            return;
        }
        this.loopView.totalScrollY -= (int) ((this.f4995a * 10.0f) / 1000.0f);
        if (!this.loopView.isLoop) {
            float f = this.loopView.lineSpacingMultiplier * this.loopView.itemTextHeight;
            if (this.loopView.totalScrollY <= ((int) ((-this.loopView.initPosition) * f))) {
                this.f4995a = 40.0f;
                this.loopView.totalScrollY = (int) ((-r3.initPosition) * f);
            } else if (this.loopView.totalScrollY >= ((int) (((this.loopView.items.size() - 1) - this.loopView.initPosition) * f))) {
                this.loopView.totalScrollY = (int) (((r3.items.size() - 1) - this.loopView.initPosition) * f);
                this.f4995a = -40.0f;
            }
        }
        float f2 = this.f4995a;
        if (f2 < 0.0f) {
            this.f4995a = f2 + 20.0f;
        } else {
            this.f4995a = f2 - 20.0f;
        }
        this.loopView.handler.sendEmptyMessage(1000);
    }
}
