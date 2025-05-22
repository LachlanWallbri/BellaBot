package com.aliyun.alink.linksdk.tmp.resource;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.devicemodel.Profile;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.WifiManagerUtil;
import com.aliyun.alink.linksdk.tools.ALog;
import java.net.InetAddress;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiscoveryResHander implements ITResRequestHandler {
    private static final String TAG = "[Tmp]DiscoveryResHander";
    protected DeviceModel mDeviceModel;
    protected int mDeviceModelLength;
    protected String mDeviceName;
    protected String mProductKey;

    public DiscoveryResHander(String str, String str2, DeviceModel deviceModel) {
        setData(str, str2, deviceModel);
    }

    public void setData(String str, String str2, DeviceModel deviceModel) {
        this.mProductKey = str;
        this.mDeviceName = str2;
        this.mDeviceModel = deviceModel;
        if (deviceModel != null) {
            String json = GsonUtils.toJson(deviceModel);
            if (TextUtils.isEmpty(json)) {
                return;
            }
            this.mDeviceModelLength = json.length();
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler
    public void onProcess(String str, Object obj, ITResResponseCallback iTResResponseCallback) {
        Profile profile = new Profile();
        profile.setProdKey(this.mProductKey);
        profile.setName(this.mDeviceName);
        profile.port = 5683;
        InetAddress ipAddress = WifiManagerUtil.getIpAddress(WifiManagerUtil.NetworkType.WLAN);
        profile.addr = ipAddress == null ? "" : ipAddress.getHostAddress();
        ValueWrapper valueWrapper = new ValueWrapper();
        ALog.m479d(TAG, "onProcess identifier mDeviceModelLength:" + this.mDeviceModelLength + " mDeviceModel:" + this.mDeviceModel);
        DeviceModel deviceModel = this.mDeviceModel;
        if (deviceModel != null && this.mDeviceModelLength <= 3072) {
            deviceModel.setProfile(profile);
            valueWrapper.setValue(this.mDeviceModel);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("profile", profile);
            valueWrapper.setValue(hashMap);
        }
        iTResResponseCallback.onComplete("dev", null, new OutputParams("deviceModel", valueWrapper));
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        ALog.m479d(TAG, "onSuccess returnValue:" + outputParams);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "onFail errorInfo:" + errorInfo);
    }
}
