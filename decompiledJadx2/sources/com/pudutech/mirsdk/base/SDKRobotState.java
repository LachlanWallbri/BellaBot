package com.pudutech.mirsdk.base;

import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKRobotState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mirsdk/base/SDKRobotState;", "", "(Ljava/lang/String;I)V", "Idle", "Moving", "Stuck", "Approaching", "Arrive", "Pause", "Resume", "Avoid", MoveError.LEVEL_ERROR, "Warn", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public enum SDKRobotState {
    Idle,
    Moving,
    Stuck,
    Approaching,
    Arrive,
    Pause,
    Resume,
    Avoid,
    Error,
    Warn
}
