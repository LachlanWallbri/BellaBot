package com.aliyun.alink.linksdk.tmp.device.panel.linkselection;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CmpNotifyManager;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.AsyncConnectListenerWrapper;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.ConnectListenerWrapper;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.DoubleDataListenerWrapper;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelEventCallback;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.SubsListenerWrapper;
import com.aliyun.alink.linksdk.tmp.service.UTPoint;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CloudChannelDevice {
    private static final String TAG = "[Tmp]CloudChannelDevice";
    private TmpEnum.ChannelStrategy mChannelType;
    private String mIotId;

    public boolean isInit() {
        return true;
    }

    public boolean isIniting() {
        return false;
    }

    public CloudChannelDevice(String str, TmpEnum.ChannelStrategy channelStrategy) {
        this.mIotId = str;
        this.mChannelType = channelStrategy;
    }

    public void init(IPanelCallback iPanelCallback) {
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(true, null);
        }
    }

    public void uninit() {
        CmpNotifyManager.getInstance().removeHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_EVENTS);
        CmpNotifyManager.getInstance().removeHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_PROPERTIES);
        CmpNotifyManager.getInstance().removeHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_STATUS);
    }

    public void getProperties(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "getProperties callback:" + iPanelCallback);
        CloudUtils.getProperties(this.mIotId, new ConnectListenerWrapper(iPanelCallback));
    }

    public void setProperties(String str, IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "setProperties params:" + str + " callback:" + iPanelCallback);
        JSONObject parseObject = JSON.parseObject(str);
        CloudUtils.setProperties(parseObject, new ConnectListenerWrapper(UTPoint.createUTPoint(parseObject, "/thing/properties/set"), iPanelCallback));
    }

    public void getLastEvent(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "getEvents callback:" + iPanelCallback);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getEvents callback null");
        } else {
            CloudUtils.getEvents(this.mIotId, new ConnectListenerWrapper(iPanelCallback));
        }
    }

    public void invokeService(String str, IPanelCallback iPanelCallback) {
        Object remove;
        ALog.m479d(TAG, "invokeServiceCloud params:" + str + " callback:" + iPanelCallback);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "invokeService callback null");
            return;
        }
        JSONObject jSONObject = null;
        IConnectSendListener doubleDataListenerWrapper = new DoubleDataListenerWrapper(iPanelCallback);
        try {
            jSONObject = JSON.parseObject(str);
            if (jSONObject != null && (remove = jSONObject.remove(TmpConstant.SERVICE_CALLTYPE)) != null) {
                String valueOf = String.valueOf(remove);
                if (!TextUtils.isEmpty(valueOf) && InvokeServiceData.CALL_TYPE_ASYNC.equalsIgnoreCase(valueOf)) {
                    doubleDataListenerWrapper = new AsyncConnectListenerWrapper(iPanelCallback);
                }
            }
        } catch (Exception e) {
            ALog.m480e(TAG, "invokeService error:" + e.toString());
        }
        CloudUtils.invokeService(jSONObject, doubleDataListenerWrapper);
    }

    public void getStatus(IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "getStatus callback:" + iPanelCallback);
        if (iPanelCallback == null) {
            ALog.m480e(TAG, "getStatus callback null");
        } else {
            CloudUtils.getStatus(this.mIotId, new ConnectListenerWrapper(iPanelCallback));
        }
    }

    public void subAllEvents(IPanelEventCallback iPanelEventCallback, IPanelCallback iPanelCallback) {
        ALog.m479d(TAG, "subAllEvents callback:" + iPanelCallback + " listener:" + iPanelEventCallback);
        if (iPanelEventCallback == null) {
            ALog.m480e(TAG, "subAllEvents callback null");
            return;
        }
        SubsListenerWrapper subsListenerWrapper = new SubsListenerWrapper(this.mIotId, iPanelEventCallback);
        CmpNotifyManager.getInstance().addHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_PROPERTIES, subsListenerWrapper);
        CmpNotifyManager.getInstance().addHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_EVENTS, subsListenerWrapper);
        CmpNotifyManager.getInstance().addHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_STATUS, subsListenerWrapper);
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(true, null);
        }
    }
}
