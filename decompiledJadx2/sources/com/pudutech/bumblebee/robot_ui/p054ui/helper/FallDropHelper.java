package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import kotlin.Metadata;

/* compiled from: FallDropHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/FallDropHelper;", "", "()V", "clearDropEvent", "", "getDropDetStatus", "", "setEnableDropDet", "flag", "setLockMotor", "lock", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class FallDropHelper {
    public static final FallDropHelper INSTANCE = new FallDropHelper();

    private FallDropHelper() {
    }

    public final void setLockMotor(boolean lock) {
        SDK.INSTANCE.lockMotor(lock);
    }

    public final void clearDropEvent() {
        SDK.INSTANCE.clearDropEvent();
    }

    public final boolean getDropDetStatus() {
        return MirSdkManager.INSTANCE.getDropDetStatus();
    }

    public final void setEnableDropDet(boolean flag) {
        MirSdkManager.INSTANCE.setEnableDropDet(flag);
    }
}
