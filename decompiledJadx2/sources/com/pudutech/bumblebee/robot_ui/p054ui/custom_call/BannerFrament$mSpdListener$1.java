package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import kotlin.Metadata;

/* compiled from: BannerFrament.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/custom_call/BannerFrament$mSpdListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SpeedListener;", "onSpeed", "", "p0", "", "p1", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BannerFrament$mSpdListener$1 implements SpeedListener {
    final /* synthetic */ BannerFrament this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BannerFrament$mSpdListener$1(BannerFrament bannerFrament) {
        this.this$0 = bannerFrament;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
    public void onSpeed(double p0, double p1) {
        boolean z;
        z = this.this$0.isStart;
        if (z) {
            if (p0 != 0.0d && p1 != 0.0d) {
                this.this$0.getMDownHandler().post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.BannerFrament$mSpdListener$1$onSpeed$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BannerFrament$mSpdListener$1.this.this$0.recoverScreenLight();
                    }
                });
                Pdlog.m3273d(this.this$0.getTAG(), "SpdListener:机器晃动");
            }
            Pdlog.m3273d(this.this$0.getTAG(), "SpdListener:p0--" + p0 + "----p1:" + p1 + '}');
        }
    }
}
