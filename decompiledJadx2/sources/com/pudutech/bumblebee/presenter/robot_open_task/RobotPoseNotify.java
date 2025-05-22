package com.pudutech.bumblebee.presenter.robot_open_task;

import android.os.SystemClock;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.pub.PubRobotPoseDate;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotPoseNotify.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0002\u0012\u0015\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\b\u0010 \u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\u001a\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\n¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify;", "", "()V", "TAG", "", "angle", "", "getAngle", "()D", "setAngle", "(D)V", "intervalTime", "", "isInit", "", "lastNotify", "", "poseListener", "com/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify$poseListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify$poseListener$1;", "speedListener", "com/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify$speedListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify$speedListener$1;", "x", "getX", "setX", "y", "getY", "setY", "destroy", "", "init", "notifyPose", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotPoseNotify {
    private double angle;
    private boolean isInit;
    private long lastNotify;
    private double x;
    private double y;
    private final String TAG = "RobotPoseNotify";
    private int intervalTime = 300000;
    private final RobotPoseNotify$poseListener$1 poseListener = new PoseListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotPoseNotify$poseListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener
        public void onPose(double p0, double p1, double p2) {
            long j;
            int i;
            long j2;
            RobotPoseNotify.this.setX(p0);
            RobotPoseNotify.this.setY(p1);
            RobotPoseNotify.this.setAngle(p2);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            j = RobotPoseNotify.this.lastNotify;
            long j3 = elapsedRealtime - j;
            i = RobotPoseNotify.this.intervalTime;
            if (j3 <= i) {
                j2 = RobotPoseNotify.this.lastNotify;
                if (j2 != 0) {
                    return;
                }
            }
            RobotPoseNotify.this.lastNotify = elapsedRealtime;
            RobotPoseNotify.this.notifyPose();
        }
    };
    private final RobotPoseNotify$speedListener$1 speedListener = new SpeedListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotPoseNotify$speedListener$1
        private final double MOVING_MPS = 0.02d;
        private final double ROTATE_RPS = 0.1d;

        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
        public void onSpeed(double p0, double p1) {
            if (Math.abs(p0) > this.MOVING_MPS || Math.abs(p1) > this.ROTATE_RPS) {
                RobotPoseNotify.this.intervalTime = 2000;
            } else {
                RobotPoseNotify.this.intervalTime = 300000;
            }
        }
    };

    public final double getX() {
        return this.x;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final double getY() {
        return this.y;
    }

    public final void setY(double d) {
        this.y = d;
    }

    public final double getAngle() {
        return this.angle;
    }

    public final void setAngle(double d) {
        this.angle = d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyPose() {
        RobotOpenSdk.INSTANCE.publishMsg(new PubRobotPoseDate(this.x, this.y, this.angle), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotPoseNotify$notifyPose$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
            }
        });
    }

    public final void init() {
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        SDK.INSTANCE.getPoseListeners().addListener(this.poseListener);
        SDK.INSTANCE.getSpeedListeners().addListener(this.speedListener);
    }

    public final void destroy() {
        SDK.INSTANCE.getPoseListeners().removeListener(this.poseListener);
        SDK.INSTANCE.getSpeedListeners().removeListener(this.speedListener);
        this.isInit = false;
    }
}
