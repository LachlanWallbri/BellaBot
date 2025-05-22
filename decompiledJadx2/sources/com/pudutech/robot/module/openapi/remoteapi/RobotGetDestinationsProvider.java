package com.pudutech.robot.module.openapi.remoteapi;

import com.pudutech.robot.opensdk.bean.PageBody;
import com.pudutech.robot.opensdk.bean.resp.RespDestinationsBody;
import kotlin.Metadata;

/* compiled from: RobotGetDestinationsProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/remoteapi/RobotGetDestinationsProvider;", "Lcom/pudutech/robot/module/openapi/remoteapi/BaseRemoteApiProvider;", "getDestinations", "Lcom/pudutech/robot/opensdk/bean/resp/RespDestinationsBody;", "data", "Lcom/pudutech/robot/opensdk/bean/PageBody;", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface RobotGetDestinationsProvider extends BaseRemoteApiProvider {
    RespDestinationsBody getDestinations(PageBody data);
}
