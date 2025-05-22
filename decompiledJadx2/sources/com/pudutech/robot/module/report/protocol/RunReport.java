package com.pudutech.robot.module.report.protocol;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.robot.module.report.protocol.bean.RobotPositionInfo;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RunReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/RunReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "battery", "", "getBattery", "()D", "setBattery", "(D)V", RequestParameters.POSITION, "Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;", "getPosition", "()Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;", "setPosition", "(Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;)V", "status", "", "getStatus", "()I", "setStatus", "(I)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public class RunReport extends BaseV2Report {
    private double battery;
    private RobotPositionInfo position;
    private int status;

    public RunReport() {
        setType("run");
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    public final double getBattery() {
        return this.battery;
    }

    public final void setBattery(double d) {
        this.battery = d;
    }

    public final RobotPositionInfo getPosition() {
        return this.position;
    }

    public final void setPosition(RobotPositionInfo robotPositionInfo) {
        this.position = robotPositionInfo;
    }
}
