package com.pudutech.bumblebee.business.peripherals_task.lora_notice_task;

import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaNoticeTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\nR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/lora_notice_task/LoRaNoticeTask;", "", "()V", "robot", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "getRobot$module_bumblebee_business_robotRelease", "()Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "setRobot$module_bumblebee_business_robotRelease", "(Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;)V", "noticeVIP", "", NotificationCompat.CATEGORY_MESSAGE, "", "stopNoticeVIP", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaNoticeTask {
    private RobotInterface robot;

    /* renamed from: getRobot$module_bumblebee_business_robotRelease, reason: from getter */
    public final RobotInterface getRobot() {
        return this.robot;
    }

    public final void setRobot$module_bumblebee_business_robotRelease(RobotInterface robotInterface) {
        this.robot = robotInterface;
    }

    public final void noticeVIP(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        RobotInterface robotInterface = this.robot;
        if (robotInterface != null) {
            robotInterface.noticeToVIP(msg);
        }
    }

    public final void stopNoticeVIP() {
        RobotInterface robotInterface = this.robot;
        if (robotInterface != null) {
            robotInterface.stopNoticeVIP();
        }
    }
}
