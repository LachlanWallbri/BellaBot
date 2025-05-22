package com.pudutech.bumblebee.robot.utils;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LimitQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0019\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"main", "", "args", "", "", "([Ljava/lang/String;)V", "Robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LimitQueueKt {
    public static final void main(String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        LimitQueue limitQueue = new LimitQueue(3);
        limitQueue.offer("1");
        limitQueue.offer("2");
        limitQueue.offer("3");
        limitQueue.offer(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
        System.out.println(limitQueue);
    }
}
