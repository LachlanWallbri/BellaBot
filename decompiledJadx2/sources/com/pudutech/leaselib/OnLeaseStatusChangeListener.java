package com.pudutech.leaselib;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LeaseSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/leaselib/OnLeaseStatusChangeListener;", "", "onStatusChange", "", "useType", "Lcom/pudutech/leaselib/UseType;", AUserTrack.UTKEY_END_TIME, "", "leftTime", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface OnLeaseStatusChangeListener {
    void onStatusChange(UseType useType, long endTime, long leftTime);
}
