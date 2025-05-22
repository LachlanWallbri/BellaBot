package com.pudutech.bumblebee.business.robotsdk.peripherals_listeners;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import kotlin.Metadata;

/* compiled from: TouchListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/TouchListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onTouch", "", "place", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;", "state", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface TouchListener extends BaseListener {
    void onTouch(TouchPlace place, TouchState state);
}
