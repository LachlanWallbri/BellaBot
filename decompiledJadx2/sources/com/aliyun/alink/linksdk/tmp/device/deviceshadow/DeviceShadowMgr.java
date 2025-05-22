package com.aliyun.alink.linksdk.tmp.device.deviceshadow;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CmpNotifyManager;
import com.aliyun.alink.linksdk.tmp.data.deviceshadow.UpdateParam;
import com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.payload.discovery.GetTslResponsePayload;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IProcessListener;
import com.aliyun.alink.linksdk.tmp.utils.CloudUtils;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceShadowMgr implements IConnectNotifyListener, INotifyHandler {
    private static final String DEVICESHADOW_CACHE_FILE = "deviceShadow";
    private static final String DEVICESHADOW_DETAILINFO_PRE_KEY = "device_detailInfo_";
    private static final String DEVICESHADOW_PROPERTY_PRE_KEY = "device_property_";
    private static final String DEVICESHADOW_STATUS_PRE_KEY = "device_status_";
    private static final String DEVICESHADOW_TSL_PRE_KEY = "device_tsl_";
    private static final int DEVICESHADOW_VERSION = 1;
    public static final String TAG = "[Tmp]DeviceShadowMgr";
    private DiskLruHelper mDiskLruCacheHelper;
    private MemoryLruHelper mMemoryLruHelper;

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String str, ConnectState connectState) {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String str, String str2) {
        return true;
    }

    private DeviceShadowMgr() {
        this.mDiskLruCacheHelper = new DiskLruHelper(DEVICESHADOW_CACHE_FILE, 1);
        this.mMemoryLruHelper = new MemoryLruHelper();
        CmpNotifyManager.getInstance().addHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_PROPERTIES, this);
        CmpNotifyManager.getInstance().addHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_STATUS, this);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class InstanceHolder {
        private static DeviceShadowMgr mInstance = new DeviceShadowMgr();

        private InstanceHolder() {
        }
    }

    public static DeviceShadowMgr getInstance() {
        return InstanceHolder.mInstance;
    }

    public void getTsl(String str, IProcessListener iProcessListener) {
        if (iProcessListener == null) {
            ALog.m480e(TAG, "gettsl processListener empty");
            return;
        }
        String cacheKey = getCacheKey(DEVICESHADOW_TSL_PRE_KEY, str);
        String string = this.mDiskLruCacheHelper.getString(cacheKey);
        StringBuilder sb = new StringBuilder();
        sb.append("gettsl iotId: ");
        sb.append(str);
        sb.append(" processListener:");
        sb.append(iProcessListener);
        sb.append(" cacheKey:");
        sb.append(cacheKey);
        sb.append(" isCallbacked:");
        boolean z = false;
        int length = 0;
        sb.append(false);
        sb.append(" tsl:");
        sb.append(string);
        ALog.m479d(TAG, sb.toString());
        if (!TextUtils.isEmpty(string)) {
            CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
            commonResponsePayload.setCode(200);
            try {
                commonResponsePayload.setData(JSON.parseObject(string));
                String jSONString = JSON.toJSONString(commonResponsePayload);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("getTsl response payload:");
                if (!TextUtils.isEmpty(jSONString)) {
                    length = jSONString.length();
                }
                sb2.append(length);
                ALog.m479d(TAG, sb2.toString());
                iProcessListener.onSuccess(jSONString);
            } catch (Exception e) {
                ALog.m480e(TAG, "getTsl onSuccess error:" + e.toString());
            }
            z = true;
        }
        updateTslByCloud(str, z, iProcessListener);
    }

    public String getTsl(String str) {
        String string = this.mDiskLruCacheHelper.getString(getCacheKey(DEVICESHADOW_TSL_PRE_KEY, str));
        CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
        commonResponsePayload.setCode(200);
        try {
            commonResponsePayload.setData(JSON.parseObject(string));
            String jSONString = JSON.toJSONString(commonResponsePayload);
            StringBuilder sb = new StringBuilder();
            sb.append("getTsl response payload:");
            sb.append(TextUtils.isEmpty(jSONString) ? 0 : jSONString.length());
            ALog.m479d(TAG, sb.toString());
            return jSONString;
        } catch (Exception e) {
            ALog.m480e(TAG, "getTsl onSuccess error:" + e.toString());
            return null;
        }
    }

    public void getDetailInfo(String str, IProcessListener iProcessListener) {
        if (iProcessListener == null) {
            ALog.m480e(TAG, "getDetailInfo processListener empty");
            return;
        }
        boolean z = false;
        String cacheKey = getCacheKey(DEVICESHADOW_DETAILINFO_PRE_KEY, str);
        String string = this.mDiskLruCacheHelper.getString(cacheKey);
        ALog.m479d(TAG, "getDeviceDetailInfo iotId: " + str + " processListener:" + iProcessListener + " cacheKey:" + cacheKey + " isCallbacked:false deviceDetailInfo:" + string);
        if (!TextUtils.isEmpty(string)) {
            z = true;
            CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
            commonResponsePayload.setCode(200);
            commonResponsePayload.setData(JSON.parseObject(string));
            try {
                iProcessListener.onSuccess(JSON.toJSONString(commonResponsePayload));
            } catch (Exception e) {
                ALog.m480e(TAG, "getDetailInfo onSuccess error:" + e.toString());
            }
        }
        updateDetailInfoByCloud(str, z, iProcessListener);
    }

    public String getDetailInfo(String str) {
        String string = this.mDiskLruCacheHelper.getString(getCacheKey(DEVICESHADOW_DETAILINFO_PRE_KEY, str));
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
        commonResponsePayload.setCode(200);
        commonResponsePayload.setData(JSON.parseObject(string));
        try {
            return JSON.toJSONString(commonResponsePayload);
        } catch (Exception e) {
            ALog.m480e(TAG, "getDetailInfo onSuccess error:" + e.toString());
            return null;
        }
    }

    public boolean isPropertyCached(String str) {
        boolean z = !TextUtils.isEmpty(this.mMemoryLruHelper.getString(getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, str)));
        ALog.m479d(TAG, "isPropertyCached iotId:" + str + " ret:" + z);
        return z;
    }

    public void getProps(final String str, final DeviceShadowFetcher deviceShadowFetcher, final IProcessListener iProcessListener) {
        if (iProcessListener == null) {
            ALog.m480e(TAG, "getProps processListener empty");
            return;
        }
        boolean z = false;
        String cacheKey = getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, str);
        final String string = this.mMemoryLruHelper.getString(cacheKey);
        ALog.m479d(TAG, "getProps iotId: " + str + " processListener:" + iProcessListener + " cacheKey:" + cacheKey + " isCallbacked:false propertiesStr:" + string);
        if (!TextUtils.isEmpty(string)) {
            z = true;
            CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
            commonResponsePayload.setCode(200);
            commonResponsePayload.setData(JSON.parseObject(string));
            try {
                iProcessListener.onSuccess(JSON.toJSONString(commonResponsePayload));
            } catch (Exception e) {
                ALog.m480e(TAG, "getProps onSuccess error:" + e.toString());
            }
        }
        final boolean z2 = z;
        if (deviceShadowFetcher != null) {
            deviceShadowFetcher.getProperties(new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.deviceshadow.DeviceShadowMgr.1
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(boolean z3, Object obj) {
                    ALog.m479d(DeviceShadowMgr.TAG, "getProperties onComplete isSuccess: " + z3 + " data:" + obj);
                    if (obj == null || !z3) {
                        if (z2) {
                            return;
                        }
                        iProcessListener.onFail(new ErrorInfo(300, "getProperties error error"));
                        return;
                    }
                    try {
                        DeviceShadowMgr.this.updatePropertyCacheAndNotify(str, JSON.parseObject(String.valueOf(obj)).getJSONObject("data"), string, z2, new DeviceShadowNotifier(deviceShadowFetcher));
                    } catch (Exception e2) {
                        ALog.m480e(DeviceShadowMgr.TAG, "getProperties onComplete updatePropertyCacheAndNotify error:" + e2.toString());
                    }
                    if (z2) {
                        return;
                    }
                    iProcessListener.onSuccess(obj);
                }
            });
        } else {
            if (z2) {
                return;
            }
            iProcessListener.onFail(new ErrorInfo(300, "getProperties device empty error"));
        }
    }

    public String getProps(String str) {
        String cacheKey = getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, str);
        String string = this.mMemoryLruHelper.getString(cacheKey);
        ALog.m479d(TAG, "getProps iotId: " + str + " cacheKey:" + cacheKey + " propertiesStr:" + string);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
        commonResponsePayload.setCode(200);
        commonResponsePayload.setData(JSON.parseObject(string));
        try {
            return JSON.toJSONString(commonResponsePayload);
        } catch (Exception e) {
            ALog.m480e(TAG, "getProps onSuccess error:" + e.toString());
            return null;
        }
    }

    public void getStatus(final String str, final DeviceShadowFetcher deviceShadowFetcher, final IProcessListener iProcessListener) {
        if (iProcessListener == null) {
            ALog.m480e(TAG, "getStatus processListener empty");
            return;
        }
        boolean z = false;
        String cacheKey = getCacheKey(DEVICESHADOW_STATUS_PRE_KEY, str);
        final String string = this.mMemoryLruHelper.getString(cacheKey);
        ALog.m479d(TAG, "getStatus iotId: " + str + " processListener:" + iProcessListener + " cacheKey:" + cacheKey + " isCallbacked:false status:" + string);
        if (!TextUtils.isEmpty(string)) {
            z = true;
            CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
            commonResponsePayload.setCode(200);
            commonResponsePayload.setData(JSON.parseObject(string));
            try {
                iProcessListener.onSuccess(JSON.toJSONString(commonResponsePayload));
            } catch (Exception e) {
                ALog.m480e(TAG, "getStatus onSuccess error:" + e.toString());
            }
        }
        final boolean z2 = z;
        if (deviceShadowFetcher != null) {
            deviceShadowFetcher.getStatus(new IPanelCallback() { // from class: com.aliyun.alink.linksdk.tmp.device.deviceshadow.DeviceShadowMgr.2
                @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.IPanelCallback
                public void onComplete(boolean z3, Object obj) {
                    ALog.m479d(DeviceShadowMgr.TAG, "getStatus onComplete isSuccess: " + z3 + " data:" + obj);
                    if (obj == null || !z3) {
                        if (z2) {
                            return;
                        }
                        iProcessListener.onFail(new ErrorInfo(300, "getStatus error error"));
                        return;
                    }
                    try {
                        DeviceShadowMgr.this.updateStatusCacheAndNotify(str, JSON.parseObject(String.valueOf(obj)).getJSONObject("data"), string, z2, new DeviceShadowNotifier(deviceShadowFetcher));
                    } catch (Exception e2) {
                        ALog.m480e(DeviceShadowMgr.TAG, "getStatus notify error:" + e2.toString());
                    }
                    if (z2) {
                        return;
                    }
                    iProcessListener.onSuccess(obj);
                }
            });
        } else {
            if (z2) {
                return;
            }
            iProcessListener.onFail(new ErrorInfo(300, "getStatus device empty error"));
        }
    }

    public String getStatus(String str) {
        String cacheKey = getCacheKey(DEVICESHADOW_STATUS_PRE_KEY, str);
        String string = this.mMemoryLruHelper.getString(cacheKey);
        ALog.m479d(TAG, "getStatus iotId: " + str + " cacheKey:" + cacheKey + " isCallbacked:false status:" + string);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
        commonResponsePayload.setCode(200);
        commonResponsePayload.setData(JSON.parseObject(string));
        try {
            return JSON.toJSONString(commonResponsePayload);
        } catch (Exception e) {
            ALog.m480e(TAG, "getStatus onSuccess error:" + e.toString());
            return null;
        }
    }

    public void refreshDeviceShadow(String str, UpdateParam updateParam, IProcessListener iProcessListener) {
        boolean z;
        ALog.m479d(TAG, "refreshDeviceShadow iotId:" + str + " updateParam:" + updateParam + " processListener:" + iProcessListener);
        if (TextUtils.isEmpty(str) || updateParam == null || updateParam.updateType == null) {
            ALog.m480e(TAG, "updateCahce empty error");
            return;
        }
        DeviceShadowRefreshListener deviceShadowRefreshListener = new DeviceShadowRefreshListener(iProcessListener, updateParam.updateType.getValue());
        boolean z2 = true;
        if (updateParam.updateType == TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_ALL || (updateParam.updateType.getValue() & TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_PROPERTIES.getValue()) > 0) {
            updatePropertiesByCloud(str, deviceShadowRefreshListener);
            z = true;
        } else {
            z = false;
        }
        if (updateParam.updateType == TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_ALL || (updateParam.updateType.getValue() & TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_STATUS.getValue()) > 0) {
            updateStatusByCloud(str, deviceShadowRefreshListener);
            z = true;
        }
        if (updateParam.updateType == TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_ALL || (updateParam.updateType.getValue() & TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_DEVICE_DETAIL_INFO.getValue()) > 0) {
            updateDetailInfoByCloud(str, false, deviceShadowRefreshListener);
            z = true;
        }
        if (updateParam.updateType == TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_ALL || (updateParam.updateType.getValue() & TmpEnum.DeviceShadowUpdateType.UPDATE_OPTION_TSL.getValue()) > 0) {
            updateTslByCloud(str, false, deviceShadowRefreshListener);
        } else {
            z2 = z;
        }
        if (z2) {
            return;
        }
        ALog.m480e(TAG, "updateCahce updateType error:" + updateParam.updateType);
        if (iProcessListener != null) {
            iProcessListener.onFail(new ErrorInfo(300, "type error"));
        }
    }

    public void deleteDeviceShadow(String str, IProcessListener iProcessListener) {
        ALog.m479d(TAG, "deleteDeviceShadow iotId:" + str + " processListener:" + iProcessListener);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "deleteDeviceShadow empty error");
            return;
        }
        this.mDiskLruCacheHelper.deleteValue(getCacheKey(DEVICESHADOW_TSL_PRE_KEY, str));
        this.mDiskLruCacheHelper.deleteValue(getCacheKey(DEVICESHADOW_DETAILINFO_PRE_KEY, str));
        this.mMemoryLruHelper.deleteValue(getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, str));
        this.mMemoryLruHelper.deleteValue(getCacheKey(DEVICESHADOW_STATUS_PRE_KEY, str));
        if (iProcessListener != null) {
            iProcessListener.onSuccess(null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
    
        if (r9.equalsIgnoreCase(r1) == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void updateStatusCacheAndNotify(String str, JSONObject jSONObject, String str2, boolean z, DeviceShadowNotifier deviceShadowNotifier) {
        if (jSONObject == null || jSONObject.isEmpty()) {
            ALog.m480e(TAG, "updateStatusCacheAndNotify newData empty");
            return;
        }
        String str3 = null;
        boolean z2 = false;
        try {
            str3 = String.valueOf(jSONObject);
            if (!TextUtils.isEmpty(str2)) {
            }
            z2 = true;
        } catch (Exception e) {
            ALog.m480e(TAG, "updateStatusCacheAndNotify error:" + e.toString());
        }
        ALog.m479d(TAG, "updateStatusCacheAndNotify iotId:" + str + " newValue:" + jSONObject + " oldValue:" + str2 + " isCallbacked:" + z + " notifier:" + deviceShadowNotifier + " isDifference:" + z2);
        if (z2) {
            this.mMemoryLruHelper.saveValue(getCacheKey(DEVICESHADOW_STATUS_PRE_KEY, str), String.valueOf(str3));
            if (!z || deviceShadowNotifier == null) {
                return;
            }
            deviceShadowNotifier.notifyStatusChange(jSONObject);
        }
    }

    protected void updatePropertyCacheAndNotify(String str, JSONObject jSONObject, String str2, boolean z, DeviceShadowNotifier deviceShadowNotifier) {
        if (jSONObject == null || jSONObject.isEmpty()) {
            ALog.m480e(TAG, "updatePropertyCacheAndNotify newData empty");
            return;
        }
        try {
            JSONObject jSONObject2 = str2 == null ? new JSONObject() : JSON.parseObject(str2);
            boolean z2 = false;
            for (String str3 : jSONObject.keySet()) {
                JSONObject jSONObject3 = jSONObject.getJSONObject(str3);
                JSONObject jSONObject4 = jSONObject2.getJSONObject(str3);
                if (jSONObject4 == null || !jSONObject3.equals(jSONObject4)) {
                    z2 = true;
                    jSONObject2.put(str3, (Object) jSONObject3);
                }
            }
            ALog.m479d(TAG, "updatePropertyCacheAndNotify iotId:" + str + " newData:" + jSONObject + " oldValue:" + str2 + " isCallbacked:" + z + " notifier:" + deviceShadowNotifier + " isDifference:" + z2);
            if (z2) {
                this.mMemoryLruHelper.saveValue(getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, str), jSONObject2.toString());
                if (!z || deviceShadowNotifier == null) {
                    return;
                }
                deviceShadowNotifier.notifyPropertyChange(jSONObject);
            }
        } catch (Exception e) {
            ALog.m480e(TAG, "updatePropertyCacheAndNotify error:" + e.toString());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String str, String str2, AMessage aMessage) {
        String obj;
        ALog.m479d(TAG, "onNotify connectedId:" + str + " topic:" + str2 + " aMessage:" + aMessage);
        if (TextUtils.isEmpty(str2)) {
            ALog.m480e(TAG, "onNotify error topic:" + str2 + " connectedId:" + str);
            return;
        }
        try {
            if (aMessage.data instanceof byte[]) {
                obj = new String((byte[]) aMessage.data, "UTF-8");
            } else {
                obj = aMessage.data.toString();
            }
            JSONObject parseObject = JSONObject.parseObject(obj);
            JSONObject jSONObject = parseObject.getJSONObject("params");
            String string = jSONObject.getString(TmpConstant.DEVICE_IOTID);
            if (str2.contains(TmpConstant.MQTT_TOPIC_PROPERTIES)) {
                updatePropertyCacheAndNotify(string, jSONObject.getJSONObject("items"), this.mMemoryLruHelper.getString(getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, string)), false, null);
            } else if (str2.contains(TmpConstant.MQTT_TOPIC_STATUS)) {
                updateStatusCacheAndNotify(string, jSONObject.getJSONObject("status"), this.mMemoryLruHelper.getString(getCacheKey(DEVICESHADOW_STATUS_PRE_KEY, string)), false, null);
            }
            ALog.m479d(TAG, "notifyIotId:" + string + " data:" + parseObject);
        } catch (Exception e) {
            ALog.m480e(TAG, "onNotify e:" + e.toString());
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.event.INotifyHandler
    public void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        JSONObject jSONObject;
        ALog.m479d(TAG, "onMessage request:" + tmpCommonRequest + " response:" + tmpCommonResponse);
        try {
            JSONObject parseObject = JSONObject.parseObject(tmpCommonResponse.getResponseText());
            String string = parseObject.getString("method");
            if (TextUtils.isEmpty(string) || !string.contains(TmpConstant.METHOD_PROPERTY_POST) || (jSONObject = parseObject.getJSONObject("params")) == null) {
                return;
            }
            updatePropertyCacheAndNotify(tmpCommonResponse.getIotId(), jSONObject, this.mMemoryLruHelper.getString(getCacheKey(DEVICESHADOW_PROPERTY_PRE_KEY, tmpCommonResponse.getIotId())), false, null);
        } catch (Exception e) {
            ALog.m480e(TAG, "onMessage error:" + e.toString());
        }
    }

    protected void updatePropertiesByCloud(final String str, final IProcessListener iProcessListener) {
        ALog.m479d(TAG, "updatePropertiesByCloud iotId:" + str + " processListener:" + iProcessListener);
        CloudUtils.getProperties(str, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.deviceshadow.DeviceShadowMgr.3
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                ALog.m479d(DeviceShadowMgr.TAG, "updatePropertiesByCloud onResponse:" + aResponse);
                if (aResponse == null || aResponse.data == null) {
                    IProcessListener iProcessListener2 = iProcessListener;
                    if (iProcessListener2 != null) {
                        iProcessListener2.onFail(new ErrorInfo(300, "getProperties error error"));
                        return;
                    }
                    return;
                }
                try {
                    DeviceShadowMgr.this.updatePropertyCacheAndNotify(str, JSON.parseObject(String.valueOf(aResponse.data)).getJSONObject("data"), DeviceShadowMgr.this.mMemoryLruHelper.getString(DeviceShadowMgr.this.getCacheKey(DeviceShadowMgr.DEVICESHADOW_PROPERTY_PRE_KEY, str)), false, null);
                    if (iProcessListener != null) {
                        iProcessListener.onSuccess(aResponse.data);
                    }
                } catch (Exception e) {
                    ALog.m480e(DeviceShadowMgr.TAG, "getProperties onComplete updatePropertyCacheAndNotify error:" + e.toString());
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m479d(DeviceShadowMgr.TAG, "updatePropertiesByCloud onFailure:" + aError);
                IProcessListener iProcessListener2 = iProcessListener;
                if (iProcessListener2 != null) {
                    iProcessListener2.onFail(new ErrorInfo(aError));
                }
            }
        });
    }

    protected void updateStatusByCloud(final String str, final IProcessListener iProcessListener) {
        ALog.m479d(TAG, "updateStatusByCloud iotId:" + str + " processListener:" + iProcessListener);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "updateStatusByCloud empty error");
        } else {
            CloudUtils.getStatus(str, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.deviceshadow.DeviceShadowMgr.4
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    ALog.m479d(DeviceShadowMgr.TAG, "updateStatusByCloud onComplete aRequest: " + aRequest + " aResponse:" + aResponse);
                    if (aResponse == null || aResponse.data == null) {
                        IProcessListener iProcessListener2 = iProcessListener;
                        if (iProcessListener2 != null) {
                            iProcessListener2.onFail(new ErrorInfo(300, "getStatus response error"));
                            return;
                        }
                        return;
                    }
                    DeviceShadowMgr.this.updateStatusCacheAndNotify(str, JSON.parseObject(String.valueOf(aResponse.data)).getJSONObject("data"), DeviceShadowMgr.this.mMemoryLruHelper.getString(DeviceShadowMgr.this.getCacheKey(DeviceShadowMgr.DEVICESHADOW_STATUS_PRE_KEY, str)), false, null);
                    IProcessListener iProcessListener3 = iProcessListener;
                    if (iProcessListener3 != null) {
                        iProcessListener3.onSuccess(aResponse.data);
                    }
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    ALog.m479d(DeviceShadowMgr.TAG, "updateStatusByCloud onFailure aError: " + aError);
                    IProcessListener iProcessListener2 = iProcessListener;
                    if (iProcessListener2 != null) {
                        iProcessListener2.onFail(new ErrorInfo(300, "getStatus error error"));
                    }
                }
            });
        }
    }

    protected void updateDetailInfoByCloud(final String str, final boolean z, final IProcessListener iProcessListener) {
        ALog.m479d(TAG, "updateDetailInfoByCloud iotId:" + str + " isCallbacked:" + z + " processListener:" + iProcessListener);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "updateDetailInfoByCloud empty error");
        } else {
            CloudUtils.queryProductInfo(str, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.deviceshadow.DeviceShadowMgr.5
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    IProcessListener iProcessListener2;
                    ALog.m479d(DeviceShadowMgr.TAG, "updateDetailInfoByCloud onResponse iotId:" + str + " onResponse:" + aResponse);
                    if (aResponse == null || aResponse.data == null) {
                        if (z || (iProcessListener2 = iProcessListener) == null) {
                            return;
                        }
                        iProcessListener2.onFail(new ErrorInfo(300, "getDeviceDetailInfo aResponse error"));
                        return;
                    }
                    try {
                        CommonResponsePayload commonResponsePayload = (CommonResponsePayload) JSON.parseObject(String.valueOf(aResponse.data), CommonResponsePayload.class);
                        if (commonResponsePayload != null && commonResponsePayload.getData() != null) {
                            DeviceShadowMgr.this.mDiskLruCacheHelper.saveValue(DeviceShadowMgr.this.getCacheKey(DeviceShadowMgr.DEVICESHADOW_DETAILINFO_PRE_KEY, str), String.valueOf(commonResponsePayload.getData()));
                            if (z || iProcessListener == null) {
                                return;
                            }
                            iProcessListener.onSuccess(String.valueOf(aResponse.data));
                            return;
                        }
                        ALog.m480e(DeviceShadowMgr.TAG, "queryProductInfo payload error");
                        if (z || iProcessListener == null) {
                            return;
                        }
                        iProcessListener.onFail(new ErrorInfo(300, "getDeviceDetailInfo parseObject error"));
                    } catch (Exception e) {
                        ALog.m480e(DeviceShadowMgr.TAG, "parseObject error:" + e.toString());
                    }
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    IProcessListener iProcessListener2;
                    ALog.m479d(DeviceShadowMgr.TAG, "updateDetailInfoByCloud onFailure iotId:" + str + " aError:" + aError);
                    if (z || (iProcessListener2 = iProcessListener) == null) {
                        return;
                    }
                    iProcessListener2.onFail(new ErrorInfo(aError));
                }
            });
        }
    }

    protected void updateTslByCloud(final String str, final boolean z, final IProcessListener iProcessListener) {
        ALog.m479d(TAG, "updateTslByCloud iotId:" + str + " isCallbacked:" + z + " processListener:" + iProcessListener);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e(TAG, "updateTslByCloud empty error");
        } else {
            CloudUtils.getTsl(str, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.tmp.device.deviceshadow.DeviceShadowMgr.6
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    IProcessListener iProcessListener2;
                    IProcessListener iProcessListener3;
                    IProcessListener iProcessListener4;
                    ALog.m479d(DeviceShadowMgr.TAG, "updateTslByCloud iotId:" + str + " onResponse:" + aResponse);
                    if (aResponse == null || aResponse.data == null) {
                        if (z || (iProcessListener2 = iProcessListener) == null) {
                            return;
                        }
                        iProcessListener2.onFail(new ErrorInfo(300, "getTsl aResponse error"));
                        return;
                    }
                    GetTslResponsePayload getTslResponsePayload = null;
                    try {
                        getTslResponsePayload = (GetTslResponsePayload) JSON.parseObject(String.valueOf(aResponse.data), GetTslResponsePayload.class);
                    } catch (Exception e) {
                        ALog.m480e(DeviceShadowMgr.TAG, "parseObject error:" + e.toString());
                    }
                    if (getTslResponsePayload != null && getTslResponsePayload.data != null) {
                        DeviceShadowMgr.this.mDiskLruCacheHelper.saveValue(DeviceShadowMgr.this.getCacheKey(DeviceShadowMgr.DEVICESHADOW_TSL_PRE_KEY, str), String.valueOf(getTslResponsePayload.data));
                        if (z || (iProcessListener4 = iProcessListener) == null) {
                            return;
                        }
                        iProcessListener4.onSuccess(String.valueOf(aResponse.data));
                        return;
                    }
                    if (z || (iProcessListener3 = iProcessListener) == null) {
                        return;
                    }
                    iProcessListener3.onFail(new ErrorInfo(300, "payload data parse error"));
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    IProcessListener iProcessListener2;
                    ALog.m479d(DeviceShadowMgr.TAG, "updateTslByCloud onFailure iotId:" + str + " aError:" + aError);
                    if (z || (iProcessListener2 = iProcessListener) == null) {
                        return;
                    }
                    iProcessListener2.onFail(new ErrorInfo(aError));
                }
            });
        }
    }

    public String getCacheKey(String str, String str2) {
        return (str + str2).toLowerCase();
    }
}
