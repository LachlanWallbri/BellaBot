package com.aliyun.alink.linksdk.tmp.device.panel.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.tmp.service.UTPoint;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DoubleDataListenerWrapper extends ConnectListenerWrapper {
    public DoubleDataListenerWrapper(IPanelCallback iPanelCallback) {
        super(iPanelCallback);
    }

    public DoubleDataListenerWrapper(UTPoint uTPoint, IPanelCallback iPanelCallback) {
        super(uTPoint, iPanelCallback);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.ConnectListenerWrapper, com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onResponse(ARequest aRequest, AResponse aResponse) {
        try {
            JSONObject parseObject = JSON.parseObject(aResponse.data.toString());
            parseObject.put("data", (Object) parseObject.getJSONObject("data").getJSONObject("data"));
            aResponse.data = parseObject.toString();
        } catch (Throwable th) {
            ALog.m480e("[Tmp]ConnectListenerWrapper", "DoubleDataListenerWrapper onResponse error : " + th.toString());
        }
        super.onResponse(aRequest, aResponse);
    }
}
