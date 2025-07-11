package com.pudutech.bumblebee.robot_ui.widget.loopview;

import android.os.Handler;
import android.os.Message;
import com.pudutech.bumblebee.robot_ui.widget.loopview.LoopView;

/* loaded from: classes4.dex */
final class MessageHandler extends Handler {
    public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    public static final int WHAT_ITEM_SELECTED = 3000;
    public static final int WHAT_SMOOTH_SCROLL = 2000;
    public static final int WHAT_SMOOTH_SCROLL_INERTIA = 2001;
    final LoopView loopview;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageHandler(LoopView loopView) {
        this.loopview = loopView;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1000) {
            this.loopview.invalidate();
            return;
        }
        if (i == 2000) {
            removeMessages(WHAT_SMOOTH_SCROLL_INERTIA);
            this.loopview.smoothScroll(LoopView.ACTION.FLING);
        } else {
            if (i != 3000) {
                return;
            }
            this.loopview.onItemSelected();
        }
    }
}
