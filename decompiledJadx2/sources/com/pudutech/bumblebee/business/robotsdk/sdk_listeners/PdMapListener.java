package com.pudutech.bumblebee.business.robotsdk.sdk_listeners;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import kotlin.Metadata;

/* compiled from: PdMapListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/PdMapListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onSwitchResult", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/SwitchMapResult;", "p1", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface PdMapListener extends BaseListener {
    void onSwitchResult(SwitchMapResult p0, String p1);
}
