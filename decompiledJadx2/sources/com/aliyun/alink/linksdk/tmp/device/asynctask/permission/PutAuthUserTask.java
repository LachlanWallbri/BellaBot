package com.aliyun.alink.linksdk.tmp.device.asynctask.permission;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpPutAuthUserReqeustBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PutAuthUserTask extends DeviceAsyncTask<PutAuthUserTask> implements IRequestHandler {
    protected static final String TAG = "[Tmp]PutAuthUserTask";
    protected String mToken;
    protected String mUid;

    public PutAuthUserTask(DeviceImpl deviceImpl, IDevListener iDevListener, DeviceBasicData deviceBasicData) {
        super(deviceImpl, iDevListener);
        setDeviceBasicData(deviceBasicData);
    }

    public PutAuthUserTask setUid(String str) {
        this.mUid = str;
        return this;
    }

    public PutAuthUserTask setToken(String str) {
        this.mToken = str;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        this.mConnect.send(TmpPutAuthUserReqeustBuilder.createBuilder(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName()).setUid(this.mUid).setToken(this.mToken).setIsSecure(true).createRequest(), this);
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        CommonResponsePayload commonResponsePayload;
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess() && (commonResponsePayload = (CommonResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<CommonResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.permission.PutAuthUserTask.1
        }.getType())) != null && commonResponsePayload.payloadSuccess()) {
            taskSuccess(tmpCommonRequest, tmpCommonResponse);
        } else {
            onError(tmpCommonRequest, new ErrorInfo(300, "response error"));
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }
}
