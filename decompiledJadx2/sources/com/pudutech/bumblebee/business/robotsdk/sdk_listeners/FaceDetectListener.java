package com.pudutech.bumblebee.business.robotsdk.sdk_listeners;

import com.pudutech.bumblebee.business.base.BaseListener;
import kotlin.Metadata;

/* compiled from: FaceDetectListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/FaceDetectListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onFaceDetectResult", "", "p0", "", "p1", "", "p2", "p3", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface FaceDetectListener extends BaseListener {
    void onFaceDetectResult(int p0, double p1, double p2, double p3);
}
