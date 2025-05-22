package com.aliyun.alink.linksdk.tmp.device.panel.listener;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.device.panel.linkselection.LocalChannelDevice;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsMulChannelEventListenerWrapper extends AlcsEventListenerWrapper {
    protected WeakReference<LocalChannelDevice> mLocalChannellDeviceRef;

    @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.AlcsEventListenerWrapper
    public void stopLocalConnect() {
    }

    public AlcsMulChannelEventListenerWrapper(LocalChannelDevice localChannelDevice, DeviceBasicData deviceBasicData, IPanelCallback iPanelCallback, IPanelEventCallback iPanelEventCallback) {
        super(null, deviceBasicData, iPanelCallback, iPanelEventCallback);
        this.mLocalChannellDeviceRef = new WeakReference<>(localChannelDevice);
    }
}
