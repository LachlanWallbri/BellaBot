package com.pudutech.antichannelconflict.escape.listener;

import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import kotlin.Metadata;

/* compiled from: EscapeDetectListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/listener/EscapeDetectListener;", "", "onEscapeDetect", "", "status", "Lcom/pudutech/antichannelconflict/escape/util/EscapeStatus;", "onPeriodUpgrade", "Lcom/pudutech/antichannelconflict/upgrade/util/UpgradeStatus;", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface EscapeDetectListener {
    void onEscapeDetect(EscapeStatus status);

    void onPeriodUpgrade(UpgradeStatus status);
}
