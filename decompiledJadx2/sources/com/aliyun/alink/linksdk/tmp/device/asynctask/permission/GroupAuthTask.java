package com.aliyun.alink.linksdk.tmp.device.asynctask.permission;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpGroupAuthRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.payload.cloud.EncryptGroupAuthInfo;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GroupAuthTask extends DeviceAsyncTask<GroupAuthTask> implements IRequestHandler {
    protected static final String TAG = "[Tmp]GroupAuthTask";
    protected String mDataType;
    protected String mGroupId;
    protected String mOp;
    protected String mParam1;
    protected String mParam2;

    public GroupAuthTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceImpl(deviceImpl);
        setDeviceBasicData(deviceBasicData);
    }

    public GroupAuthTask setOp(String str) {
        this.mOp = str;
        return this;
    }

    public GroupAuthTask setAuthData(String str, EncryptGroupAuthInfo encryptGroupAuthInfo) {
        this.mDataType = str;
        if (encryptGroupAuthInfo != null) {
            if (TmpConstant.GROUP_ROLE_DEVICE.equalsIgnoreCase(str)) {
                this.mParam1 = encryptGroupAuthInfo.encryptGroupKeyPrefix;
                this.mParam2 = encryptGroupAuthInfo.encryptGroupSecret;
            } else if (TmpConstant.GROUP_ROLE_CONTROLLER.equalsIgnoreCase(this.mDataType)) {
                this.mParam1 = encryptGroupAuthInfo.encryptAccessKey;
                this.mParam2 = encryptGroupAuthInfo.encryptAccessToken;
            }
        }
        return this;
    }

    public GroupAuthTask setGroupId(String str) {
        this.mGroupId = str;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        CommonResponsePayload commonResponsePayload;
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess() && (commonResponsePayload = (CommonResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<CommonResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.permission.GroupAuthTask.1
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

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        ALog.m479d(TAG, "dealGroupAUthInfo groupId:" + this.mGroupId + " groupAuthType:" + this.mDataType + " mOp:" + this.mOp + " mParam1:" + this.mParam1 + " mParam2" + this.mParam2);
        this.mConnect.send(TmpGroupAuthRequestBuilder.createBuilder(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName()).setAddr(this.mDeviceBasicData.getAddr()).setPort(this.mDeviceBasicData.getPort()).setGroupId(this.mGroupId).setOp(this.mOp).setDataType(this.mDataType).setParam1(this.mParam1).setParam2(this.mParam2).setIsSecure(true).createRequest(), this);
        return true;
    }
}
