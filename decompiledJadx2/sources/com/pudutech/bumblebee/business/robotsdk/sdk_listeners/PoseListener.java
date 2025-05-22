package com.pudutech.bumblebee.business.robotsdk.sdk_listeners;

import com.pudutech.bumblebee.business.base.BaseListener;
import kotlin.Metadata;

/* compiled from: PoseListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/PoseListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onPose", "", "p0", "", "p1", "p2", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface PoseListener extends BaseListener {
    void onPose(double p0, double p1, double p2);
}
