package com.pudutech.robot.module.openapi.remoteapi;

import androidx.core.app.NotificationCompat;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.bean.CallBody;
import com.pudutech.robot.opensdk.bean.resp.RespResultBody;
import kotlin.Metadata;

/* compiled from: RobotCallProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/remoteapi/RobotCallProvider;", "Lcom/pudutech/robot/module/openapi/remoteapi/BaseRemoteApiProvider;", "addCallTask", "Lcom/pudutech/robot/opensdk/bean/resp/RespResultBody;", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/robot/opensdk/MsgContext;", "Lcom/pudutech/robot/opensdk/bean/CallBody;", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface RobotCallProvider extends BaseRemoteApiProvider {
    RespResultBody addCallTask(MsgContext<CallBody> msg);
}
