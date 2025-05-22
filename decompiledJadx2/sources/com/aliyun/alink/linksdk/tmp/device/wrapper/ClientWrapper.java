package com.aliyun.alink.linksdk.tmp.device.wrapper;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.notify.DiscoveryDeviceStateMgr;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IDiscoveryDeviceStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ClientWrapper extends CommonDevWrapper implements IDiscoveryDeviceStateChangeListener {
    private static final String TAG = "[Tmp]ClientWrapper";

    public ClientWrapper(DeviceConfig deviceConfig) {
        super(deviceConfig);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public TmpEnum.DeviceState getDeviceState() {
        if (this.mDeviceImpl != null) {
            return this.mDeviceImpl.getDeviceState();
        }
        return TmpEnum.DeviceState.DISCONNECTED;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public void init(Object obj, IDevListener iDevListener) {
        super.init(obj, iDevListener);
        DiscoveryDeviceStateMgr.getInstance().addDiscoveryDeviceStateChangeListener(this);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public void unInit() {
        DiscoveryDeviceStateMgr.getInstance().removeDiscoveryDeviceStateChangeListener(this);
        super.unInit();
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean getPropertyValue(List<String> list, Object obj, IDevListener iDevListener) {
        return this.mDeviceImpl.getPropertyValue(list, obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        return this.mDeviceImpl.setPropertyValue(list, obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(ExtraData extraData, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        return this.mDeviceImpl.setPropertyValue(extraData, list, obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(String str, ValueWrapper valueWrapper, Object obj, IDevListener iDevListener) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair(str, valueWrapper));
        return setPropertyValue(arrayList, obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean invokeService(String str, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        return this.mDeviceImpl.invokeService(str, list, obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean subscribeEvent(String str, Object obj, IEventListener iEventListener) {
        return this.mDeviceImpl.subscribeEvent(str, obj, iEventListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean subAllEvents(Object obj, IEventListener iEventListener) {
        return this.mDeviceImpl.subAllEvents(obj, iEventListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean unsubscribeEvent(String str, Object obj, IDevListener iDevListener) {
        return this.mDeviceImpl.unsubscribeEvent(str, obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDiscoveryDeviceStateChangeListener
    public void onDiscoveryDeviceStateChange(DeviceBasicData deviceBasicData, TmpEnum.DiscoveryDeviceState discoveryDeviceState) {
        ALog.m479d(TAG, "onDiscoveryDeviceStateChange mDeviceImpl:" + this.mDeviceImpl + " state:" + discoveryDeviceState);
        if (deviceBasicData == null || this.mDeviceImpl == null || TmpEnum.DiscoveryDeviceState.DISCOVERY_STATE_OFFLINE != discoveryDeviceState || TextUtils.isEmpty(this.mDeviceImpl.getDevId()) || !this.mDeviceImpl.getDevId().equalsIgnoreCase(deviceBasicData.getDevId())) {
            return;
        }
        this.mDeviceImpl.stopConnect();
    }
}
