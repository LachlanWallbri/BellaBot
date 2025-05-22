package com.pudutech.antichannelconflict.upgrade.listener;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import kotlin.Metadata;

/* compiled from: PeriodStatusListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J$\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/upgrade/listener/PeriodStatusListener;", "", "onCheckPeriodCallback", "", "status", "Lcom/pudutech/antichannelconflict/upgrade/util/UpgradeStatus;", "onObtainBTS", "bts", "", "onUpdateProgressCB", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "progress", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface PeriodStatusListener {
    void onCheckPeriodCallback(UpgradeStatus status);

    void onObtainBTS(String bts);

    void onUpdateProgressCB(String res, String progress, UpgradeStatus status);
}
