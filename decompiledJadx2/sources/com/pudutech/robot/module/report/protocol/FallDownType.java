package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FallDownType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/FallDownType;", "", "()V", "fall_down_type", "", "getFall_down_type", "()I", "setFall_down_type", "(I)V", "level", "", "getLevel", "()Ljava/lang/String;", "setLevel", "(Ljava/lang/String;)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FallDownType {
    private String level = "error";
    private int fall_down_type = 1;

    public final String getLevel() {
        return this.level;
    }

    public final void setLevel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.level = str;
    }

    public final int getFall_down_type() {
        return this.fall_down_type;
    }

    public final void setFall_down_type(int i) {
        this.fall_down_type = i;
    }
}
