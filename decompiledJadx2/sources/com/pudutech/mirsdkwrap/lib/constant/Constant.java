package com.pudutech.mirsdkwrap.lib.constant;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Constant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/constant/Constant;", "", "()V", Constant.KEY_MIR_SDK_LISTENER, "", "mirsdkMockServer", "mirsdkServer", "getMirsdkServer", "isMock", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Constant {
    public static final Constant INSTANCE = new Constant();
    public static final String KEY_MIR_SDK_LISTENER = "KEY_MIR_SDK_LISTENER";
    private static final String mirsdkMockServer = "com.pudutech.bumblebee.robot.mock.SDKService";
    private static final String mirsdkServer = "com.pudutech.mirsdk.SDKService";

    public final String getMirsdkServer(boolean isMock) {
        return isMock ? "com.pudutech.bumblebee.robot.mock.SDKService" : "com.pudutech.mirsdk.SDKService";
    }

    private Constant() {
    }
}
