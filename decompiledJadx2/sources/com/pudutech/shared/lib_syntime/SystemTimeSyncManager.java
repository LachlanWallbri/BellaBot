package com.pudutech.shared.lib_syntime;

import kotlin.Metadata;

/* compiled from: SystemTimeSyncManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/shared/lib_syntime/SystemTimeSyncManager;", "", "clear", "", "startSync", "delayMills", "", "lib_syntime_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface SystemTimeSyncManager {
    void clear();

    void startSync(long delayMills);
}
