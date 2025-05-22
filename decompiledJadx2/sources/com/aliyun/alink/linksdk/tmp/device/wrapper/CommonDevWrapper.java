package com.aliyun.alink.linksdk.tmp.device.wrapper;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.IDevice;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Event;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.devicemodel.Service;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class CommonDevWrapper implements IDevice {
    public static final String TAG = "[Tmp]CommonDevWrapper";
    protected DeviceConfig mConfig;
    protected DeviceImpl mDeviceImpl;

    public CommonDevWrapper(DeviceConfig deviceConfig) {
        this.mConfig = deviceConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CommonDevWrapper() {
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public void init(Object obj, IDevListener iDevListener) {
        DeviceManager deviceManager = DeviceManager.getInstance();
        DeviceConfig deviceConfig = this.mConfig;
        DeviceBasicData deviceBasicData = deviceManager.getDeviceBasicData(deviceConfig == null ? "" : deviceConfig.getBasicData().getDevId());
        if (deviceBasicData == null) {
            DeviceConfig deviceConfig2 = this.mConfig;
            deviceBasicData = new DeviceBasicData(deviceConfig2 == null ? null : deviceConfig2.getBasicData());
        }
        DeviceImpl deviceImpl = new DeviceImpl(this.mConfig, deviceBasicData);
        this.mDeviceImpl = deviceImpl;
        deviceImpl.init(obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public void unInit() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            deviceImpl.unInit();
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public String getDevName() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        return deviceImpl != null ? deviceImpl.getDevId() : "";
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public String getDevId() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        return deviceImpl != null ? deviceImpl.getDevId() : "";
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public TmpEnum.DeviceState getDeviceState() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.getDeviceState();
        }
        return TmpEnum.DeviceState.DISCONNECTED;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean addDeviceStateChangeListener(IDevStateChangeListener iDevStateChangeListener) {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.addDeviceStateChangeListener(iDevStateChangeListener);
        }
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean removeDeviceStateChangeListener(IDevStateChangeListener iDevStateChangeListener) {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.removeDeviceStateChangeListener(iDevStateChangeListener);
        }
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public List<Property> getProperties() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.getProperties();
        }
        return null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public List<Service> getServices() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.getServices();
        }
        return null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public List<Event> getEvents() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.getEvent();
        }
        return null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public ValueWrapper getPropertyValue(String str) {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.getPropertyValue(str);
        }
        return null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public Map<String, ValueWrapper> getAllPropertyValue() {
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            return deviceImpl.getAllPropertyValues();
        }
        return null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean getPropertyValue(List<String> list, Object obj, IDevListener iDevListener) {
        ALog.m480e(TAG, "getPropertyValue could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        ALog.m480e(TAG, "setPropertyValue could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(String str, ValueWrapper valueWrapper, Object obj, IDevListener iDevListener) {
        ALog.m480e(TAG, "setPropertyValue could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(ExtraData extraData, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        ALog.m480e(TAG, "setPropertyValue could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z) {
        return setPropertyValue(map, z, (IPublishResourceListener) null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z, IPublishResourceListener iPublishResourceListener) {
        ALog.m480e(TAG, "setPropertyValue could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean invokeService(String str, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        ALog.m480e(TAG, "invokeService could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean subscribeEvent(String str, Object obj, IEventListener iEventListener) {
        ALog.m480e(TAG, "subscribeEvent could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean subAllEvents(Object obj, IEventListener iEventListener) {
        ALog.m480e(TAG, "subAllEvents could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean unsubscribeEvent(String str, Object obj, IDevListener iDevListener) {
        ALog.m480e(TAG, "unsubscribeEvent could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setup(Object obj, Object obj2, IDevListener iDevListener) {
        ALog.m480e(TAG, "setup could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public String regRes(String str, boolean z, ITResRequestHandler iTResRequestHandler) {
        ALog.m480e(TAG, "regRes could not exce");
        return null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean unRegRes(String str, ITResRequestHandler iTResRequestHandler) {
        ALog.m480e(TAG, "unRegRes could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean triggerRes(String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        ALog.m480e(TAG, "triggerRes could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean triggerRes(String str, OutputParams outputParams) {
        return triggerRes(str, outputParams, null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean sendRawData(byte[] bArr, IDevRawDataListener iDevRawDataListener) {
        ALog.m480e(TAG, "sendRawData could not exce");
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean regRawRes(boolean z, ITRawDataRequestHandler iTRawDataRequestHandler) {
        ALog.m480e(TAG, "regRawRes could not exce");
        return false;
    }
}
