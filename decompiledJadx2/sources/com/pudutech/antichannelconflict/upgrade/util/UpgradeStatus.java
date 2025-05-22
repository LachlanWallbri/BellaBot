package com.pudutech.antichannelconflict.upgrade.util;

import kotlin.Metadata;

/* compiled from: UpgradeStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/upgrade/util/UpgradeStatus;", "", "code", "", "(Ljava/lang/String;II)V", "SIM_COM_GET_REVISION_FAIL", "SIM_COM_NO_FOUND", "UPDATE_FAIL", "UPDATE_FAIL_TOOL_NO_FOUND", "NEED_UPDATE", "D0_NOT_NEED_UPDATE", "UPDATING", "UPDATE_SUCCESS", "DOWNLOADING", "DOWNLOADING_FAIL", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum UpgradeStatus {
    SIM_COM_GET_REVISION_FAIL(-3),
    SIM_COM_NO_FOUND(-2),
    UPDATE_FAIL(-1),
    UPDATE_FAIL_TOOL_NO_FOUND(-4),
    NEED_UPDATE(1),
    D0_NOT_NEED_UPDATE(0),
    UPDATING(2),
    UPDATE_SUCCESS(3),
    DOWNLOADING(4),
    DOWNLOADING_FAIL(5);

    UpgradeStatus(int i) {
    }
}
