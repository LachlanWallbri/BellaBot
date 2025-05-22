package com.pudutech.mirsdk.mircore.module;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.ScheduleListener;
import com.pudutech.mirsdk.mircore.coreparcel.BusinessType;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.mirschedulemaster.ScheduleMaster;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ScheduleStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\tH\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/ScheduleStub;", "Lcom/pudutech/mirsdk/mircore/ScheduleInterface$Stub;", "listeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/mircore/ScheduleListener;", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "getListeners", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "addScheduleListener", "", "name", "", "listener", "findNextGateOnPath", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "noticeTaskStart", "noticeTaskStuck", "prepareDeliverTask", "", "goal", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "prepareGoHomeTask", "groupId", "businessType", "Lcom/pudutech/mirsdk/mircore/coreparcel/BusinessType;", "quitFillIn", "removeScheduleListener", "p0", "setGroupTaskParam", "json_param", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ScheduleStub extends ScheduleInterface.Stub {
    private final ThreadSafeListener<ScheduleListener> listeners;

    public ScheduleStub(ThreadSafeListener<ScheduleListener> listeners) {
        Intrinsics.checkParameterIsNotNull(listeners, "listeners");
        this.listeners = listeners;
    }

    public final ThreadSafeListener<ScheduleListener> getListeners() {
        return this.listeners;
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public void addScheduleListener(String name, ScheduleListener listener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public void removeScheduleListener(String p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        this.listeners.remove(p0);
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public void noticeTaskStart() {
        ScheduleMaster.INSTANCE.resetScheduleMode(SchedulingMode.Normal);
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public void noticeTaskStuck() {
        ScheduleMaster.INSTANCE.resetScheduleMode(SchedulingMode.Arrived);
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public void quitFillIn() {
        ScheduleMaster.INSTANCE.quitFillIn();
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public boolean prepareDeliverTask(Vector3d goal) {
        Intrinsics.checkParameterIsNotNull(goal, "goal");
        ScheduleMaster.INSTANCE.updateFinalGoal(goal.getX(), goal.getY(), goal.getZ());
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public boolean prepareGoHomeTask(String groupId, BusinessType businessType) {
        Intrinsics.checkParameterIsNotNull(groupId, "groupId");
        Intrinsics.checkParameterIsNotNull(businessType, "businessType");
        return ScheduleMaster.INSTANCE.setGroupIDAndGoHome(groupId, businessType);
    }

    public void setGroupTaskParam(String json_param) {
        Intrinsics.checkParameterIsNotNull(json_param, "json_param");
        ScheduleMaster.INSTANCE.setGroupTaskParam(json_param);
    }

    @Override // com.pudutech.mirsdk.mircore.ScheduleInterface
    public DestinationWithAccRange findNextGateOnPath() {
        return ScheduleMaster.INSTANCE.findGateOnCurrentPath();
    }
}
