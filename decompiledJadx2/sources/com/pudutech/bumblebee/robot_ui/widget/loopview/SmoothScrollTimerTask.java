package com.pudutech.bumblebee.robot_ui.widget.loopview;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class SmoothScrollTimerTask implements Runnable {
    final LoopView loopView;
    int offset;
    int realTotalOffset = Integer.MAX_VALUE;
    int realOffset = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SmoothScrollTimerTask(LoopView loopView, int i) {
        this.loopView = loopView;
        this.offset = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.realTotalOffset == Integer.MAX_VALUE) {
            this.realTotalOffset = this.offset;
        }
        int i = this.realTotalOffset;
        this.realOffset = (int) (i * 0.1f);
        if (this.realOffset == 0) {
            if (i < 0) {
                this.realOffset = -1;
            } else {
                this.realOffset = 1;
            }
        }
        if (Math.abs(this.realTotalOffset) <= 0) {
            this.loopView.cancelFuture();
            this.loopView.handler.sendEmptyMessage(3000);
        } else {
            this.loopView.totalScrollY += this.realOffset;
            this.loopView.handler.sendEmptyMessage(1000);
            this.realTotalOffset -= this.realOffset;
        }
    }
}
