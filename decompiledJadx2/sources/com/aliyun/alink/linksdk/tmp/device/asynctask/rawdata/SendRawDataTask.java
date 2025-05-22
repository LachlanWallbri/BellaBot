package com.aliyun.alink.linksdk.tmp.device.asynctask.rawdata;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpSendRawDataRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.service.InvokeServiceTask;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SendRawDataTask extends DeviceAsyncTask<InvokeServiceTask> implements IRequestHandler {
    private static final String TAG = "[Tmp]SendRawDataTask";
    private byte[] mData;

    public SendRawDataTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevRawDataListener iDevRawDataListener) {
        super(deviceImpl, null);
        setRawDataListener(iDevRawDataListener);
        setDeviceBasicData(deviceBasicData);
    }

    public SendRawDataTask setData(byte[] bArr) {
        this.mData = bArr;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess()) {
            taskSuccess(tmpCommonRequest, tmpCommonResponse);
        } else {
            onError(tmpCommonRequest, null);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void onFlowComplete(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse, ErrorInfo errorInfo) {
        if (this.mDevRawDataListener == null) {
            LogCat.m471e(TAG, "onFlowComplete handler empty error");
            return;
        }
        IDevRawDataListener iDevRawDataListener = this.mDevRawDataListener;
        this.mDevRawDataListener = null;
        iDevRawDataListener.onSuccess(this.mTag, tmpCommonResponse.getResponseByte());
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        if (this.mDeviceBasicData == null || this.mConnect == null) {
            ALog.m480e(TAG, "mDeviceBasicData or mDeviceModel or mConnect null");
            taskError(null, new ErrorInfo(300, "param is invalid"));
            return false;
        }
        boolean send = this.mConnect.send(TmpSendRawDataRequestBuilder.createBuilder(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName()).setData(this.mData).createRequest(), this);
        ALog.m479d(TAG, "action bRet:" + send);
        return send;
    }
}
