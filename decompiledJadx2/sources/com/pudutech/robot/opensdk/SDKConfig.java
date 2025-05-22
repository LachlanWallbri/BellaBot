package com.pudutech.robot.opensdk;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: SDKConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/SDKConfig;", "", "()V", "bindCodeLength", "", "bindCodeLength$annotations", "getBindCodeLength", "()I", "setBindCodeLength", "(I)V", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SDKConfig {
    public static final SDKConfig INSTANCE = new SDKConfig();
    private static int bindCodeLength = 8;

    @JvmStatic
    public static /* synthetic */ void bindCodeLength$annotations() {
    }

    private SDKConfig() {
    }

    public static final int getBindCodeLength() {
        return bindCodeLength;
    }

    public static final void setBindCodeLength(int i) {
        bindCodeLength = i;
    }
}
