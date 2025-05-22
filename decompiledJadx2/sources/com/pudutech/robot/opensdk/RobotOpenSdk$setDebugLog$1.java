package com.pudutech.robot.opensdk;

import com.aliyun.alink.linksdk.tools.log.ILogDispatcher;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;

/* compiled from: RobotOpenSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u00052\u000e\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "i", "", "s", "", "kotlin.jvm.PlatformType", "s2", "log"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class RobotOpenSdk$setDebugLog$1 implements ILogDispatcher {
    public static final RobotOpenSdk$setDebugLog$1 INSTANCE = new RobotOpenSdk$setDebugLog$1();

    RobotOpenSdk$setDebugLog$1() {
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogDispatcher
    public final void log(int i, String str, String str2) {
        Pdlog.m3273d(str, str2);
    }
}
