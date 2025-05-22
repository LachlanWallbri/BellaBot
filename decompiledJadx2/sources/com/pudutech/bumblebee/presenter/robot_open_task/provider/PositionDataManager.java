package com.pudutech.bumblebee.presenter.robot_open_task.provider;

import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.module.report.protocol.bean.RobotPositionInfo;
import com.pudutech.robot.module.report.protocol.bean.RobotPositionVector;
import kotlin.Metadata;

/* compiled from: PositionDataManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/provider/PositionDataManager;", "", "()V", "getPositionInfo", "Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionInfo;", "getPositionVector", "Lcom/pudutech/robot/module/report/protocol/bean/RobotPositionVector;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PositionDataManager {
    public static final PositionDataManager INSTANCE = new PositionDataManager();

    private PositionDataManager() {
    }

    public final RobotPositionInfo getPositionInfo() {
        return new RobotPositionInfo(RobotNewOpenManager.INSTANCE.getShopId(), RobotMapManager.INSTANCE.getDefaultPdmap(), System.currentTimeMillis() / 1000, getPositionVector());
    }

    private final RobotPositionVector getPositionVector() {
        return new RobotPositionVector(SDK.INSTANCE.getRobotPose2D().getX(), SDK.INSTANCE.getRobotPose2D().getY(), 0.0d, "0");
    }
}
