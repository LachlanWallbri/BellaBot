package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener;
import kotlin.Metadata;

/* compiled from: BannerFrament.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$mLcdScreenTouchListener$1", "Lcom/pudutech/bumblebee/business/peripherals_task/lcd_screed_task/LCDScreenListener;", "onTouchEvent", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BannerFrament$mLcdScreenTouchListener$1 implements LCDScreenListener {
    final /* synthetic */ BannerFrament this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BannerFrament$mLcdScreenTouchListener$1(BannerFrament bannerFrament) {
        this.this$0 = bannerFrament;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener
    public void onTouchEvent() {
        boolean z;
        z = this.this$0.isStart;
        if (z) {
            Pdlog.m3273d(this.this$0.getTAG(), "mLcdScreenTouch 点击屏幕");
            this.this$0.getMDownHandler().post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.BannerFrament$mLcdScreenTouchListener$1$onTouchEvent$1
                @Override // java.lang.Runnable
                public final void run() {
                    BannerFrament$mLcdScreenTouchListener$1.this.this$0.recoverScreenLight();
                }
            });
        }
    }
}
