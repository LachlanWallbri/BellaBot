package com.pudutech.peanut.robot_ui.p063ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback;
import kotlin.Metadata;

/* compiled from: SimpleMusicPlayerCallbck.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\b\u0010\f\u001a\u00020\u0007H\u0016J\b\u0010\r\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/SimpleMusicPlayerCallbck;", "Lcom/pudutech/mpcomponent/interf/IMusicPlayerStateCallback;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onCompletion", "", "onError", "onInitialized", "onPause", "onPrepared", "onRelease", "onReset", "onStop", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class SimpleMusicPlayerCallbck extends IMusicPlayerStateCallback {
    private final String TAG = getClass().getSimpleName();

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onInitialized() {
        Pdlog.m3273d(this.TAG, "onInitialized");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onReset() {
        Pdlog.m3273d(this.TAG, "onReset");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onPrepared() {
        Pdlog.m3273d(this.TAG, "onPrepared");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onRelease() {
        Pdlog.m3273d(this.TAG, "onRelease");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onPause() {
        Pdlog.m3273d(this.TAG, "onPause");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onCompletion() {
        Pdlog.m3273d(this.TAG, "onCompletion");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onError() {
        Pdlog.m3274e(this.TAG, "onError");
    }

    @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
    public void onStop() {
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
