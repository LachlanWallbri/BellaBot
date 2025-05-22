package com.pudutech.pd_network;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import kotlin.Metadata;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/OnAutoVerifyAction;", "", "onError", "", AUserTrack.UTKEY_ERROR_CODE, "", "step", "Lcom/pudutech/pd_network/AutoVerifyStep;", "onTokenChange", "token", "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface OnAutoVerifyAction {
    void onError(long errorCode, AutoVerifyStep step);

    void onTokenChange(String token);
}
