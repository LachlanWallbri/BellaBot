package com.pudutech.robot.module.openapi.remoteapi;

import com.pudutech.robot.opensdk.bean.resp.RespRobotStateBody;
import kotlin.Metadata;

/* compiled from: RobotStatusProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/remoteapi/RobotStatusProvider;", "Lcom/pudutech/robot/module/openapi/remoteapi/BaseRemoteApiProvider;", "getRobotState", "Lcom/pudutech/robot/opensdk/bean/resp/RespRobotStateBody;", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface RobotStatusProvider extends BaseRemoteApiProvider {
    RespRobotStateBody getRobotState();
}
