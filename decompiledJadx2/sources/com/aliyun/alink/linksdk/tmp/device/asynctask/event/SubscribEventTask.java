package com.aliyun.alink.linksdk.tmp.device.asynctask.event;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SubscribEventTask extends PropertySubEventTask {
    protected static final String TAG = "SubscribEventTask";
    protected IEventListener mEventListener;

    public SubscribEventTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceModel deviceModel, IEventListener iEventListener, Object obj) {
        super(deviceImpl, deviceBasicData, iEventListener);
        setDeviceModel(deviceModel);
        setTag(obj);
        this.mEventListener = iEventListener;
        setDeviceBasicData(deviceBasicData);
        setDeviceImpl(deviceImpl);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.event.PropertySubEventTask
    public SubscribEventTask setNotifyListener(INotifyHandler iNotifyHandler) {
        this.mNotifyHandler = iNotifyHandler;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.event.PropertySubEventTask
    public SubscribEventTask setEventNameList(String str) {
        this.mEventName = str;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void taskSuccess(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        addEventList();
        super.taskSuccess((SubscribEventTask) tmpCommonRequest, (TmpCommonRequest) tmpCommonResponse);
    }

    protected boolean addEventList() {
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl == null || TextUtils.isEmpty(this.mEventName)) {
            return false;
        }
        deviceImpl.addSubscribeEventAndListener(this.mEventName, deviceImpl.getDevId(), this.mEventListener);
        return true;
    }
}
