package com.pudutech.bumblebee.business.ims;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.config.RobotStatus;
import com.pudutech.bumblebee.business.ims.manager.RobotStatusManager;
import com.pudutech.bumblebee.business.ims.utils.ShortUUID;
import com.pudutech.bumblebee.business.ims.utils.SystemTool;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;

/* loaded from: classes4.dex */
public class RobotStatusReportTask extends Thread {
    private static final int DELAY_TIME_INTERVAL = 200;
    public static final int MAX_DELAY_TIME = 12000;
    public static final int MIN_DELAY_TIME = 3000;
    private static final String TAG = RobotStatusReportTask.class.getSimpleName();
    private RobotStatus lastRobotStatus;
    private String sender;
    private boolean isActive = false;
    private int currentDelayTime = 0;
    private volatile int delayTime = 12000;

    public RobotStatusReportTask() {
        Pdlog.m3273d(TAG, "RobotStatusReportTask init");
        this.sender = SystemTool.INSTANCE.getMac(IMSKit.INSTANCE.getInstance().getContext());
    }

    public void active() {
        Pdlog.m3273d(TAG, "active()");
        this.isActive = true;
        synchronized (this) {
            Pdlog.m3273d(TAG, "RobotStatusReportTask is notify.");
            notify();
        }
    }

    public void inactive() {
        Pdlog.m3273d(TAG, "inactive()");
        this.isActive = false;
        this.currentDelayTime = 0;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void updateDelayTime(int i) {
        if (i >= 12000) {
            i = 12000;
        } else if (i <= 3000) {
            i = 3000;
        }
        this.delayTime = i;
        Pdlog.m3273d(TAG, "updateDelayTime() delayTime = " + this.delayTime);
    }

    public void release() {
        Pdlog.m3273d(TAG, "release()");
        inactive();
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!isInterrupted()) {
            try {
                if (!this.isActive) {
                    synchronized (this) {
                        Pdlog.m3273d(TAG, "RobotStatusReportTask is wait.");
                        wait();
                    }
                }
                RobotStatus robotStatus = RobotStatusManager.INSTANCE.getINSTANCE().getRobotStatus();
                if (robotStatus != this.lastRobotStatus) {
                    Pdlog.m3273d(TAG, "状态不相同，上报");
                    this.currentDelayTime = 0;
                    this.lastRobotStatus = robotStatus;
                    reportRobotStatus(robotStatus);
                } else {
                    if (this.currentDelayTime >= this.delayTime) {
                        Pdlog.m3273d(TAG, "状态相同并满足延时时长，上报");
                        this.currentDelayTime = 0;
                        reportRobotStatus(robotStatus);
                    }
                    this.currentDelayTime += 200;
                }
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                Pdlog.m3274e(TAG, "RobotStatusReportTask is stop.");
            }
        }
    }

    private void reportRobotStatus(RobotStatus robotStatus) {
        IMSClientFactory.INSTANCE.getIMSClient().sendMsg(MessageProtobuf.Msg.newBuilder().setMsgId(ShortUUID.INSTANCE.randomUUID()).setMsgType(MsgType.SyncRobotState.getType()).setSender(this.sender).setReceiver(IMSKit.INSTANCE.getInstance().getCentralControlMac()).setRobotStatusReport(MessageProtobuf.RobotStatusReport.newBuilder().setRobotState(robotStatus.getState()).build()).build(), false, null);
    }
}
