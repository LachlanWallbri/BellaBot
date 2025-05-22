package com.aliyun.alink.linksdk.tmp.device.asynctask.property;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.deviceshadow.TDeviceShadow;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GetAllPropertyTask extends GetPropertyTask {
    public GetAllPropertyTask(DeviceImpl deviceImpl, TDeviceShadow tDeviceShadow, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(tDeviceShadow, deviceImpl, deviceBasicData, iDevListener);
        setDeviceImpl(deviceImpl);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.property.GetPropertyTask, com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        DeviceImpl deviceImpl;
        ArrayList arrayList = new ArrayList();
        if (this.mDeviceModel == null && (deviceImpl = this.mDeviceImplRef.get()) != null) {
            this.mDeviceModel = deviceImpl.getDeviceModel();
        }
        if (this.mDeviceModel != null && this.mDeviceModel.getProperties() != null && !this.mDeviceModel.getProperties().isEmpty()) {
            for (Property property : this.mDeviceModel.getProperties()) {
                if (property != null) {
                    arrayList.add(property.getIdentifier());
                }
            }
        }
        setPropertyList(arrayList);
        return super.action();
    }
}
