package com.aliyun.alink.linksdk.tmp.device;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.IConnect;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.UpdateBlackListHandler;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.UpdatePrefixHandler;
import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTaskFlow;
import com.aliyun.alink.linksdk.tmp.device.asynctask.event.CancelSubEventTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.event.SubscribEventTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateClientConnectTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateMqttConnectTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateProvisionConnectTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateProvisionReceiverConnectTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateServerConnectTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.ModelSerializeTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.RegDefaultResTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.ShadowInitTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.init.UpdateSvrInfoTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.permission.DeleteAuthUserTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.permission.GroupAuthTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.permission.PutAuthUserTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.rawdata.SendRawDataTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.service.InvokeServiceTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.setup.ChgPrvsRcerAuthTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.setup.SetupTask;
import com.aliyun.alink.linksdk.tmp.device.deviceshadow.TDeviceShadow;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.device.payload.cloud.EncryptGroupAuthInfo;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.devicemodel.Event;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.devicemodel.Service;
import com.aliyun.alink.linksdk.tmp.event.EventManager;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.resource.TRawResRequestWrapperHandler;
import com.aliyun.alink.linksdk.tmp.resource.TResManager;
import com.aliyun.alink.linksdk.tmp.resource.TResRequestWrapperHandler;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceImpl {
    protected static final String TAG = "[Tmp]DeviceImpl";
    protected ConnectWrapper mConnect;
    protected DeviceBasicData mDeviceBasicData;
    protected DeviceConfig mDeviceConfig;
    protected DeviceModel mDeviceModel;
    protected TDeviceShadow mDeviceShadow;
    protected boolean mIsDefaultAuthInfo = false;
    protected boolean mIsSecure = true;
    protected UpdateBlackListHandler mUpdateBlackListHandler;
    protected UpdatePrefixHandler mUpdatePrefixHandler;

    public DeviceImpl(DeviceConfig deviceConfig, DeviceBasicData deviceBasicData) {
        this.mDeviceConfig = deviceConfig;
        this.mDeviceBasicData = deviceBasicData;
        this.mDeviceShadow = new TDeviceShadow(this, this.mDeviceBasicData, this.mDeviceConfig);
    }

    public void setConnect(ConnectWrapper connectWrapper) {
        this.mConnect = connectWrapper;
        TDeviceShadow tDeviceShadow = this.mDeviceShadow;
        if (tDeviceShadow != null) {
            tDeviceShadow.setConnect(connectWrapper);
        }
    }

    public ConnectWrapper getConnect() {
        return this.mConnect;
    }

    public boolean isDefaultAuthInfo() {
        return this.mIsDefaultAuthInfo;
    }

    public void setIsDefaultAuthInfo(boolean z) {
        this.mIsDefaultAuthInfo = z;
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.mDeviceModel = deviceModel;
        this.mDeviceShadow.setDeviceModel(deviceModel);
    }

    public DeviceModel getDeviceModel() {
        return this.mDeviceModel;
    }

    public boolean isSecure() {
        return this.mIsSecure;
    }

    public void setIsSecure(boolean z) {
        this.mIsSecure = z;
    }

    public void updateProDev(String str, String str2) {
        if (this.mDeviceBasicData != null) {
            if (!TextUtils.isEmpty(str)) {
                this.mDeviceBasicData.setProductKey(str);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.mDeviceBasicData.setDeviceName(str2);
            }
        }
        this.mDeviceShadow.updateProDev(str, str2);
    }

    public boolean init(Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "init tag:" + obj + " handler:" + iDevListener);
        if (iDevListener == null || this.mDeviceBasicData == null) {
            LogCat.m469d(TAG, "init error handler:" + iDevListener);
            return false;
        }
        this.mDeviceShadow.setDeviceModel(this.mDeviceModel);
        AsyncTaskFlow asyncTaskFlow = new AsyncTaskFlow();
        if (DeviceConfig.DeviceType.CLIENT == this.mDeviceConfig.getDeviceType()) {
            return asyncTaskFlow.appendTask(new CreateClientConnectTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new ModelSerializeTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new ShadowInitTask(this.mDeviceShadow, iDevListener).setTag(obj)).action();
        }
        if (DeviceConfig.DeviceType.SERVER == this.mDeviceConfig.getDeviceType()) {
            this.mUpdateBlackListHandler = new UpdateBlackListHandler(this);
            this.mUpdatePrefixHandler = new UpdatePrefixHandler(this);
            return asyncTaskFlow.appendTask(new CreateMqttConnectTask(this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new CreateServerConnectTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new UpdateSvrInfoTask(this.mDeviceBasicData, this.mDeviceConfig, iDevListener, this.mUpdateBlackListHandler, this.mUpdatePrefixHandler).setTag(obj)).appendTask(new ModelSerializeTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new RegDefaultResTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener)).appendTask(new ShadowInitTask(this.mDeviceShadow, iDevListener).setTag(obj)).action();
        }
        if (DeviceConfig.DeviceType.PROVISION == this.mDeviceConfig.getDeviceType()) {
            return asyncTaskFlow.appendTask(new CreateProvisionConnectTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new ChgPrvsRcerAuthTask(this, obj, this.mDeviceBasicData, this.mDeviceConfig, iDevListener)).action();
        }
        if (DeviceConfig.DeviceType.PROVISION_RECEIVER == this.mDeviceConfig.getDeviceType()) {
            return asyncTaskFlow.appendTask(new CreateMqttConnectTask(this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new CreateProvisionReceiverConnectTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener).setTag(obj)).appendTask(new RegDefaultResTask(this, this.mDeviceBasicData, this.mDeviceConfig, iDevListener)).action();
        }
        return false;
    }

    public void unInit() {
        this.mDeviceShadow.unInit();
        Set<String> devSubedEventList = EventManager.getInstance().getDevSubedEventList(hashCode(), getDevId());
        if (devSubedEventList == null || devSubedEventList.isEmpty()) {
            ALog.m479d(TAG, "subedEventlist empty");
        } else {
            Iterator<String> it = devSubedEventList.iterator();
            while (it.hasNext()) {
                unsubscribeEvent(it.next(), null, null);
            }
        }
        UpdatePrefixHandler updatePrefixHandler = this.mUpdatePrefixHandler;
        if (updatePrefixHandler != null) {
            updatePrefixHandler.unSubTopic();
        }
        UpdateBlackListHandler updateBlackListHandler = this.mUpdateBlackListHandler;
        if (updateBlackListHandler != null) {
            updateBlackListHandler.unSubTopic();
        }
        ConnectWrapper connectWrapper = this.mConnect;
        if (connectWrapper != null) {
            connectWrapper.unInit();
        }
        ALog.m479d(TAG, "unInit mDeviceShadow:" + this.mDeviceShadow + " subedEventlist:" + devSubedEventList + " mUpdatePrefixHandler:" + this.mUpdatePrefixHandler + " mUpdateBlackListHandler:" + this.mUpdateBlackListHandler + " mConnect:" + this.mConnect);
    }

    public void stopConnect() {
        ALog.m479d(TAG, "stopConnect mConnect:" + this.mConnect);
        ConnectWrapper connectWrapper = this.mConnect;
        if (connectWrapper != null) {
            connectWrapper.stopConnect();
        }
    }

    public String getDevModelType() {
        DeviceBasicData deviceBasicData = this.mDeviceBasicData;
        if (deviceBasicData != null) {
            return deviceBasicData.getModelType();
        }
        return null;
    }

    public String getDevId() {
        DeviceBasicData deviceBasicData = this.mDeviceBasicData;
        if (deviceBasicData != null) {
            return deviceBasicData.getDevId();
        }
        return null;
    }

    public TmpEnum.DeviceState getDeviceState() {
        ConnectWrapper connectWrapper = this.mConnect;
        if (connectWrapper == null) {
            ALog.m479d(TAG, "getDeviceState mConnect null return DISCONNECTED");
            return TmpEnum.DeviceState.DISCONNECTED;
        }
        return connectWrapper.getConnectState();
    }

    public boolean addDeviceStateChangeListener(IDevStateChangeListener iDevStateChangeListener) {
        ConnectWrapper connectWrapper = this.mConnect;
        if (connectWrapper != null) {
            return connectWrapper.addConnectChangeListener(iDevStateChangeListener);
        }
        return false;
    }

    public boolean removeDeviceStateChangeListener(IDevStateChangeListener iDevStateChangeListener) {
        ConnectWrapper connectWrapper = this.mConnect;
        if (connectWrapper != null) {
            return connectWrapper.removeConnectChangeListener(iDevStateChangeListener);
        }
        return false;
    }

    public List<Property> getProperties() {
        DeviceModel deviceModel = this.mDeviceModel;
        if (deviceModel != null) {
            return deviceModel.getProperties();
        }
        return null;
    }

    public List<Service> getServices() {
        DeviceModel deviceModel = this.mDeviceModel;
        if (deviceModel != null) {
            return deviceModel.getServices();
        }
        return null;
    }

    public List<Event> getEvent() {
        DeviceModel deviceModel = this.mDeviceModel;
        if (deviceModel != null) {
            return deviceModel.getEvents();
        }
        return null;
    }

    public boolean getPropertyValue(List<String> list, Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "getPropertyValue idList:" + list + " tag:" + obj + " handler:" + iDevListener);
        return this.mDeviceShadow.getPropertyValue(list, obj, iDevListener);
    }

    public ValueWrapper getPropertyValue(String str) {
        ALog.m479d(TAG, "getPropertyValue propId:" + str);
        return this.mDeviceShadow.getPropertyValue(str);
    }

    public Map<String, ValueWrapper> getAllPropertyValues() {
        ALog.m479d(TAG, "getAllPropertyValues");
        return this.mDeviceShadow.getAllPropertyValues();
    }

    public boolean setPropertyValue(ExtraData extraData, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "setPropertyValue extraData:" + extraData + " propertyPair:" + list + " tag:" + obj + " handler:" + iDevListener);
        return this.mDeviceShadow.setPropertyValue(extraData, list, obj, iDevListener);
    }

    public boolean setPropertyValue(List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "setPropertyValue propertyPair:" + list + " tag:" + obj + " handler:" + iDevListener);
        return this.mDeviceShadow.setPropertyValue(list, obj, iDevListener);
    }

    public boolean setPropertyValue(String str, ValueWrapper valueWrapper, boolean z, IPublishResourceListener iPublishResourceListener) {
        return this.mDeviceShadow.setPropertyValue(str, valueWrapper, z, iPublishResourceListener);
    }

    public boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z, IPublishResourceListener iPublishResourceListener) {
        return this.mDeviceShadow.setPropertyValue(map, z, iPublishResourceListener);
    }

    public boolean invokeService(String str, List<KeyValuePair> list, Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "invokeService serviceId:" + str + " args:" + list + " tag:" + obj + " handler:" + iDevListener);
        return new AsyncTaskFlow().appendTask(new InvokeServiceTask(this, this.mDeviceBasicData, iDevListener).setDeviceModel(this.mDeviceModel).setServiceIdentifier(str).setTag(obj).setIsSecure(isSecure()).setServiceArgs(list)).action();
    }

    public boolean subscribeEvent(final String str, Object obj, final IEventListener iEventListener) {
        ALog.m479d(TAG, "subscribeEvent eventId:" + str + " tag:" + obj + " handler:" + iEventListener);
        if ("post".equalsIgnoreCase(str)) {
            this.mDeviceShadow.subPropertyPostEvent(new IDevListener() { // from class: com.aliyun.alink.linksdk.tmp.device.DeviceImpl.1
                @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                public void onSuccess(Object obj2, OutputParams outputParams) {
                    DeviceImpl deviceImpl = DeviceImpl.this;
                    deviceImpl.addSubscribeEventAndListener(str, deviceImpl.getDevId(), iEventListener);
                    IEventListener iEventListener2 = iEventListener;
                    if (iEventListener2 != null) {
                        iEventListener2.onSuccess(obj2, null);
                    }
                }

                @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
                public void onFail(Object obj2, ErrorInfo errorInfo) {
                    IEventListener iEventListener2 = iEventListener;
                    if (iEventListener2 != null) {
                        iEventListener2.onFail(obj2, null);
                    }
                }
            });
            return true;
        }
        return new AsyncTaskFlow().appendTask(new SubscribEventTask(this, this.mDeviceBasicData, this.mDeviceModel, iEventListener, obj).setNotifyListener((INotifyHandler) EventManager.getInstance()).setEventNameList(str)).action();
    }

    public boolean subAllEvents(Object obj, IEventListener iEventListener) {
        ALog.m479d(TAG, "subAllEvent  tag:" + obj + " handler:" + iEventListener + " mDeviceModel:" + this.mDeviceModel);
        DeviceModel deviceModel = this.mDeviceModel;
        if (deviceModel != null && deviceModel.getEvents() != null && this.mDeviceModel.getEvents().size() <= 1) {
            return subscribeEvent("post", obj, iEventListener);
        }
        AsyncTaskFlow asyncTaskFlow = new AsyncTaskFlow();
        DeviceModel deviceModel2 = this.mDeviceModel;
        if (deviceModel2 != null) {
            for (Event event : deviceModel2.getEvents()) {
                if ("post".equalsIgnoreCase(event.getIdentifier())) {
                    subscribeEvent("post", obj, iEventListener);
                } else {
                    asyncTaskFlow.appendTask(new SubscribEventTask(this, this.mDeviceBasicData, this.mDeviceModel, iEventListener, obj).setNotifyListener((INotifyHandler) EventManager.getInstance()).setEventNameList(event.getIdentifier()));
                }
            }
        }
        return asyncTaskFlow.action();
    }

    public boolean unsubscribeEvent(String str, Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "unsubscribeEvent eventId:" + str + " tag:" + obj + " handler:" + iDevListener);
        removeSubscribeEventAndListener(str);
        if (!"post".equalsIgnoreCase(str)) {
            return new AsyncTaskFlow().appendTask(new CancelSubEventTask(this, this.mDeviceBasicData, iDevListener).setTag(obj).setIsSecure(isSecure()).setDeviceModel(this.mDeviceModel).setEventNameList(str)).action();
        }
        if (iDevListener == null) {
            return true;
        }
        iDevListener.onSuccess(obj, null);
        return true;
    }

    public boolean addGroupInfo(String str, TmpEnum.GroupRoleType groupRoleType, Object obj, Object obj2, IDevListener iDevListener) {
        ALog.m479d(TAG, "addGroupAuthInfo groupId:" + str + " roleType:" + groupRoleType + " authInfo:" + obj + " tag:" + obj2 + " handler:" + iDevListener);
        if (groupRoleType == null || obj == null) {
            return false;
        }
        return new AsyncTaskFlow().appendTask(new GroupAuthTask(this, this.mDeviceBasicData, iDevListener).setTag(obj2).setGroupId(str).setAuthData(groupRoleType.getValue(), (EncryptGroupAuthInfo) GsonUtils.fromJson(obj.toString(), new TypeToken<EncryptGroupAuthInfo>() { // from class: com.aliyun.alink.linksdk.tmp.device.DeviceImpl.2
        }.getType())).setOp(TmpConstant.GROUP_OP_ADD)).action();
    }

    public boolean delGroupInfo(String str, TmpEnum.GroupRoleType groupRoleType, Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "delGroupAUthInfo groupId:" + str + " roleType:" + groupRoleType + " tag:" + obj + " handler:" + iDevListener);
        if (groupRoleType == null) {
            groupRoleType = TmpEnum.GroupRoleType.UNKNOWN;
        }
        return new AsyncTaskFlow().appendTask(new GroupAuthTask(this, this.mDeviceBasicData, iDevListener).setTag(obj).setGroupId(str).setAuthData(groupRoleType.getValue(), null).setOp(TmpConstant.GROUP_OP_DEL)).action();
    }

    public boolean putAuthUser(String str, String str2, Object obj, IDevListener iDevListener) {
        return new AsyncTaskFlow().appendTask(new PutAuthUserTask(this, iDevListener, this.mDeviceBasicData).setUid(str).setToken(str2)).action();
    }

    public boolean delAuthUser(List<String> list, Object obj, IDevListener iDevListener) {
        return new AsyncTaskFlow().appendTask(new DeleteAuthUserTask(this, iDevListener, this.mDeviceBasicData).setUids(list)).action();
    }

    public boolean setup(Object obj, Object obj2, IDevListener iDevListener) {
        SetupTask setupTask = new SetupTask(obj2, this.mDeviceBasicData, this, iDevListener);
        setupTask.setParams(obj);
        return new AsyncTaskFlow().appendTask(setupTask).action();
    }

    public boolean isEventSubscribed(String str) {
        return EventManager.getInstance().isEventSubscribed(hashCode(), getDevId(), str);
    }

    public void addSubscribeEventAndListener(String str, String str2, IEventListener iEventListener) {
        EventManager.getInstance().addSubscribedEvent(hashCode(), getDevId(), str);
        EventManager.getInstance().addEventListener(hashCode(), str2, str, iEventListener);
    }

    public void removeSubscribeEventAndListener(String str) {
        EventManager.getInstance().removeSubscribedEvent(hashCode(), getDevId(), str);
        EventManager.getInstance().removeEventListener(hashCode(), getDevId(), str);
    }

    public String regRes(String str, boolean z, ITResRequestHandler iTResRequestHandler) {
        return regRes(null, str, z, iTResRequestHandler);
    }

    public String regRes(String str, String str2, boolean z, ITResRequestHandler iTResRequestHandler) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (TmpConstant.PROPERTY_IDENTIFIER_SET.compareToIgnoreCase(str2) == 0) {
            this.mDeviceShadow.setPropSetServiceHandler(iTResRequestHandler);
            return TextHelper.getTopicStr(this.mDeviceModel, str2);
        }
        if (TmpConstant.PROPERTY_IDENTIFIER_GET.compareToIgnoreCase(str2) == 0) {
            this.mDeviceShadow.setPropGetServiceHandler(iTResRequestHandler);
            return TextHelper.getTopicStr(this.mDeviceModel, str2);
        }
        return TResManager.getinstance().regRes(this.mConnect, str, this.mDeviceModel, str2, z, new TResRequestWrapperHandler(iTResRequestHandler));
    }

    public boolean unRegRes(String str, ITResRequestHandler iTResRequestHandler) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TmpConstant.PROPERTY_IDENTIFIER_SET.compareToIgnoreCase(str) == 0) {
            this.mDeviceShadow.setPropSetServiceHandler(null);
            return true;
        }
        if (TmpConstant.PROPERTY_IDENTIFIER_GET.compareToIgnoreCase(str) == 0) {
            this.mDeviceShadow.setPropGetServiceHandler(null);
            return true;
        }
        return TResManager.getinstance().unRegRes(this.mConnect, this.mDeviceModel, str);
    }

    public boolean triggerRes(String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if ("post".compareToIgnoreCase(str) == 0) {
            Map.Entry<String, ValueWrapper> next = outputParams.entrySet().iterator().next();
            this.mDeviceShadow.triggerPostEvent(next.getKey(), next.getValue(), iPublishResourceListener);
            return true;
        }
        return TResManager.getinstance().triggerResource(this.mConnect, this.mDeviceModel, str, outputParams, iPublishResourceListener);
    }

    public boolean setConnectOption(int i, Object obj) {
        ALog.m479d(TAG, "setConnectOption mConnect:" + this.mConnect + " optionType:" + i + " value:" + obj);
        ConnectWrapper connectWrapper = this.mConnect;
        if (connectWrapper != null) {
            return connectWrapper.setOption(connectWrapper.getConnectId(IConnect.ConnectType.CONNECT_TYPE_COAP), i, obj);
        }
        return false;
    }

    public void updatePrefix(String str) {
        ALog.m479d(TAG, "updatePrefix prefix:" + str);
        setConnectOption(1, str);
        DeviceConfig deviceConfig = this.mDeviceConfig;
        if (deviceConfig == null || !(deviceConfig instanceof DefaultServerConfig)) {
            return;
        }
        DefaultServerConfig defaultServerConfig = (DefaultServerConfig) deviceConfig;
        defaultServerConfig.setPrefix(str);
        TmpStorage.getInstance().saveServerEnptInfo(getDevId(), str, defaultServerConfig.getSecret());
    }

    public void updateBlackList(String str) {
        ALog.m479d(TAG, "updateBlackList blackList:" + str);
        setConnectOption(2, str);
        TmpStorage.getInstance().saveBlackList(getDevId(), str);
    }

    public boolean sendRawData(byte[] bArr, IDevRawDataListener iDevRawDataListener) {
        return new AsyncTaskFlow().appendTask(new SendRawDataTask(this, this.mDeviceBasicData, iDevRawDataListener).setData(bArr)).action();
    }

    public boolean regRawRes(boolean z, ITRawDataRequestHandler iTRawDataRequestHandler) {
        return TResManager.getinstance().regTopic(this.mConnect, TextHelper.formatDownRawId(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName()), TextHelper.formatDownRawTopic(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName()), z, new TRawResRequestWrapperHandler(iTRawDataRequestHandler));
    }

    public boolean publishRawData(byte[] bArr, final IDevRawDataListener iDevRawDataListener) {
        ALog.m479d(TAG, "publishRawData handler:" + iDevRawDataListener + " data:" + iDevRawDataListener);
        return TResManager.getinstance().triggerRawTopic(this.mConnect, "post", "/sys/" + this.mDeviceBasicData.getProductKey() + "/" + this.mDeviceBasicData.getDeviceName() + TmpConstant.URI_THING + TmpConstant.URI_MODEL + "/up_raw", bArr, new IPublishResourceListener() { // from class: com.aliyun.alink.linksdk.tmp.device.DeviceImpl.3
            @Override // com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener
            public void onSuccess(String str, Object obj) {
                IDevRawDataListener iDevRawDataListener2 = iDevRawDataListener;
                if (iDevRawDataListener2 != null) {
                    iDevRawDataListener2.onSuccess(null, obj);
                }
            }

            @Override // com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener
            public void onError(String str, AError aError) {
                IDevRawDataListener iDevRawDataListener2 = iDevRawDataListener;
                if (iDevRawDataListener2 != null) {
                    iDevRawDataListener2.onFail(null, aError == null ? null : new ErrorInfo(aError.getCode(), aError.getMsg()));
                }
            }
        });
    }
}
