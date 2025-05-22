package com.pudutech.robot.opensdk.aliyun;

import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tools.AError;
import kotlin.Metadata;

/* compiled from: IotShadow.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, m3961d2 = {"com/pudutech/robot/opensdk/aliyun/IotShadow$initShadow$2", "Lcom/aliyun/alink/linksdk/cmp/core/listener/IConnectSendListener;", "onFailure", "", "p0", "Lcom/aliyun/alink/linksdk/cmp/core/base/ARequest;", "p1", "Lcom/aliyun/alink/linksdk/tools/AError;", "onResponse", "aResponse", "Lcom/aliyun/alink/linksdk/cmp/core/base/AResponse;", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class IotShadow$initShadow$2 implements IConnectSendListener {
    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onFailure(ARequest p0, AError p1) {
    }

    IotShadow$initShadow$2() {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onResponse(ARequest p0, AResponse aResponse) {
        if (aResponse == null) {
            return;
        }
        IotShadow.access$changeShadowConfig(IotShadow.INSTANCE, aResponse);
    }
}
