package com.pudutech.antichannelconflict.escape.util;

import kotlin.Metadata;

/* compiled from: EscapeStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/util/EscapeStatus;", "", "code", "", "(Ljava/lang/String;II)V", "LOCKED", "UNLOCKED", "EXCEPTION_OF_LOCK", "EXCEPTION_OF_ESCAPE", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum EscapeStatus {
    LOCKED(1),
    UNLOCKED(-1),
    EXCEPTION_OF_LOCK(2),
    EXCEPTION_OF_ESCAPE(3);

    EscapeStatus(int i) {
    }
}
