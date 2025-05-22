package com.pudutech.bumblebee.business.robotsdk.peripherals_listeners;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import java.util.List;
import kotlin.Metadata;

/* compiled from: PalletStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/PalletStateListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onPalletsResponse", "", "p0", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface PalletStateListener extends BaseListener {
    void onPalletsResponse(List<Pallet> p0);
}
