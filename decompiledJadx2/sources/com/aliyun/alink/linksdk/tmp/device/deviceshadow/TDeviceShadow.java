package com.aliyun.alink.linksdk.tmp.device.deviceshadow;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTaskFlow;
import com.aliyun.alink.linksdk.tmp.device.asynctask.event.CancelSubEventTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.event.PropertySubEventTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.property.GetPropertyTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.property.SetPropertyTask;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.event.EventManager;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.resource.TResManager;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TDeviceShadow {
    protected static final String TAG = "[Tmp]TShadow";
    protected DeviceConfig mConfig;
    protected ConnectWrapper mConnect;
    protected DeviceBasicData mDeviceBasicData;
    protected DeviceModel mDeviceModel;
    protected WeakReference<DeviceImpl> mImplRef;
    protected Map<String, ValueWrapper> mPropertyValueList = new ConcurrentHashMap();
    protected TPropEventHandler mTPropEventHandler = new TPropEventHandler(this);
    protected TPropServiceHandler mTPropServiceHandler = new TPropServiceHandler(this);

    public TDeviceShadow(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig) {
        this.mConfig = deviceConfig;
        this.mImplRef = new WeakReference<>(deviceImpl);
        this.mDeviceBasicData = deviceBasicData;
    }

    public void setConnect(ConnectWrapper connectWrapper) {
        this.mConnect = connectWrapper;
    }

    public void setConfig(DeviceConfig deviceConfig) {
        this.mConfig = deviceConfig;
    }

    public void updateProDev(String str, String str2) {
        if (this.mDeviceBasicData != null) {
            if (!TextUtils.isEmpty(str)) {
                this.mDeviceBasicData.setProductKey(str);
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.mDeviceBasicData.setDeviceName(str2);
        }
    }

    public String getIotId() {
        DeviceBasicData deviceBasicData = this.mDeviceBasicData;
        if (deviceBasicData != null) {
            return deviceBasicData.getIotId();
        }
        return null;
    }

    public boolean init(IDevListener iDevListener) {
        ALog.m479d(TAG, "init mConfig:" + this.mConfig + " handler:" + iDevListener);
        if (this.mConfig.getDeviceType() == DeviceConfig.DeviceType.CLIENT) {
            this.mTPropEventHandler.subEvent(EventManager.getInstance());
            if (iDevListener == null) {
                return true;
            }
            iDevListener.onSuccess(null, null);
            return true;
        }
        if (this.mConfig.getDeviceType() != DeviceConfig.DeviceType.SERVER) {
            return true;
        }
        DefaultServerConfig defaultServerConfig = (DefaultServerConfig) this.mConfig;
        if (defaultServerConfig.getPropertValues() != null && !defaultServerConfig.getPropertValues().isEmpty()) {
            for (Map.Entry<String, ValueWrapper> entry : defaultServerConfig.getPropertValues().entrySet()) {
                setPropertyValue(entry.getKey(), entry.getValue(), false, (IPublishResourceListener) null);
            }
        }
        TResManager.getinstance().regRes(this.mConnect, this.mDeviceModel, TmpConstant.PROPERTY_IDENTIFIER_GET, true, this.mTPropServiceHandler);
        TResManager.getinstance().regRes(this.mConnect, this.mDeviceModel, TmpConstant.PROPERTY_IDENTIFIER_SET, true, this.mTPropServiceHandler);
        if (DefaultServerConfig.ConnectType.isConnectContainCoap(defaultServerConfig.getConnectType())) {
            TResManager.getinstance().regRes(this.mConnect, ConnectSDK.getInstance().getAlcsServerConnectId(), this.mDeviceModel, "post", true, this.mTPropServiceHandler);
        }
        if (DefaultServerConfig.ConnectType.isConnectContainMqtt(defaultServerConfig.getConnectType())) {
            TResManager.getinstance().regTopic(this.mConnect, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.EVENT_PROPERTY_URI_POST_REPLY, TextHelper.formatPostReplyTopic(defaultServerConfig.mIotProductKey, defaultServerConfig.mIotDeviceName), true, this.mTPropServiceHandler);
        }
        if (iDevListener == null) {
            return true;
        }
        iDevListener.onSuccess(null, null);
        return true;
    }

    public void unInit() {
        ALog.m479d(TAG, "unInit ");
        if (this.mConfig.getDeviceType() == DeviceConfig.DeviceType.CLIENT) {
            this.mTPropEventHandler.unsubEvent(EventManager.getInstance());
            new AsyncTaskFlow().appendTask(new CancelSubEventTask(this.mImplRef.get(), this.mDeviceBasicData, null).setDeviceModel(this.mDeviceModel).setEventNameList("post")).action();
        } else if (this.mConfig.getDeviceType() == DeviceConfig.DeviceType.SERVER) {
            TResManager.getinstance().unRegRes(this.mConnect, this.mDeviceModel, TmpConstant.PROPERTY_IDENTIFIER_GET);
            TResManager.getinstance().unRegRes(this.mConnect, this.mDeviceModel, TmpConstant.PROPERTY_IDENTIFIER_SET);
            TResManager.getinstance().unRegRes(this.mConnect, this.mDeviceModel, "post");
        }
    }

    public void subPropertyPostEvent(IDevListener iDevListener) {
        DeviceModel deviceModel = this.mDeviceModel;
        PropertySubEventTask notifyListener = (deviceModel == null || deviceModel.getEvents() == null || this.mDeviceModel.getEvents().size() < 1) ? null : new PropertySubEventTask(this.mImplRef.get(), this.mDeviceBasicData, iDevListener).setDeviceModel(this.mDeviceModel).setEventNameList("post").setNotifyListener(this.mTPropEventHandler);
        AsyncTaskFlow asyncTaskFlow = new AsyncTaskFlow();
        if (notifyListener != null) {
            asyncTaskFlow.appendTask(notifyListener);
        }
        if (asyncTaskFlow.action() || iDevListener == null) {
            return;
        }
        iDevListener.onFail(null, null);
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.mDeviceModel = deviceModel;
    }

    public ValueWrapper getPropertyValue(String str) {
        return this.mPropertyValueList.get(str);
    }

    public Map<String, ValueWrapper> getAllPropertyValues() {
        return this.mPropertyValueList;
    }

    public boolean getPropertyValue(List<String> list, Object obj, IDevListener iDevListener) {
        return new AsyncTaskFlow().appendTask(new GetPropertyTask(this, this.mImplRef.get(), this.mDeviceBasicData, iDevListener).setTag(obj).setDeviceModel(this.mDeviceModel).setPropertyList(list)).action();
    }

    public boolean setPropertyValue(String str, ValueWrapper valueWrapper, boolean z, IPublishResourceListener iPublishResourceListener) {
        ALog.m479d(TAG, "setPropertyValue propId :" + str + " value:" + valueWrapper + " needPublish:" + z);
        if (TextUtils.isEmpty(str) || valueWrapper == null) {
            ALog.m480e(TAG, "setPropertyValue null");
            return false;
        }
        boolean z2 = this.mPropertyValueList.put(str, valueWrapper) != null;
        if (z) {
            triggerPostEvent(str, valueWrapper, iPublishResourceListener);
        }
        return z2;
    }

    public boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z, IPublishResourceListener iPublishResourceListener) {
        for (Map.Entry<String, ValueWrapper> entry : map.entrySet()) {
            setPropertyValue(entry.getKey(), entry.getValue(), false, (IPublishResourceListener) null);
        }
        if (z) {
            triggerPostEvent(new OutputParams(map), iPublishResourceListener);
        }
        return false;
    }

    public boolean setPropertyValue(ExtraData extraData, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        return new AsyncTaskFlow().appendTask(new SetPropertyTask(this, this.mImplRef.get(), this.mDeviceBasicData, iDevListener).setTag(obj).setDeviceModel(this.mDeviceModel).setExtraData(extraData).setProperties(list)).action();
    }

    public boolean setPropertyValue(List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        return setPropertyValue((ExtraData) null, list, obj, iDevListener);
    }

    public boolean triggerPostEvent(String str, ValueWrapper valueWrapper, IPublishResourceListener iPublishResourceListener) {
        TResManager.getinstance().triggerResource(this.mConnect, this.mDeviceModel, "post", new OutputParams(str, valueWrapper), iPublishResourceListener);
        return true;
    }

    public boolean triggerPostEvent(OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        ALog.m479d(TAG, "triggerPostEvent outputParams:" + outputParams);
        TResManager.getinstance().triggerResource(this.mConnect, this.mDeviceModel, "post", outputParams, iPublishResourceListener);
        return true;
    }

    public boolean setPropGetServiceHandler(ITResRequestHandler iTResRequestHandler) {
        return this.mTPropServiceHandler.setPropGetServiceHandler(iTResRequestHandler);
    }

    public boolean setPropSetServiceHandler(ITResRequestHandler iTResRequestHandler) {
        return this.mTPropServiceHandler.setPropSetServiceHandler(iTResRequestHandler);
    }
}
