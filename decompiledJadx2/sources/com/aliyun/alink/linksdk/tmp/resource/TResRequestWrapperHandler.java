package com.aliyun.alink.linksdk.tmp.resource;

import com.aliyun.alink.linksdk.tmp.api.InputParams;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TResRequestWrapperHandler implements ITResRequestInnerHandler {
    private static final String TAG = "[Tmp]TResRequestWrapperHandler";
    protected ITResRequestHandler mHandler;

    public TResRequestWrapperHandler(ITResRequestHandler iTResRequestHandler) {
        this.mHandler = iTResRequestHandler;
    }

    @Override // com.aliyun.alink.linksdk.tmp.resource.ITResRequestInnerHandler
    public void onProcess(String str, String str2, Object obj, ITResResponseCallback iTResResponseCallback) {
        ALog.m479d(TAG, "onProcess identifier:" + str2 + " topic:" + str2 + " payload:" + obj + " mHandler:" + this.mHandler);
        if (this.mHandler != null) {
            CommonRequestPayload commonRequestPayload = (CommonRequestPayload) GsonUtils.fromJson(String.valueOf(obj), new TypeToken<CommonRequestPayload>() { // from class: com.aliyun.alink.linksdk.tmp.resource.TResRequestWrapperHandler.1
            }.getType());
            InputParams inputParams = null;
            if (obj != null && commonRequestPayload != null) {
                inputParams = new InputParams(commonRequestPayload.getParams());
            }
            this.mHandler.onProcess(str, inputParams, iTResResponseCallback);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        ALog.m479d(TAG, "onSuccess identifier:" + outputParams + " returnValue: mHandler:" + this.mHandler);
        ITResRequestHandler iTResRequestHandler = this.mHandler;
        if (iTResRequestHandler != null) {
            iTResRequestHandler.onSuccess(obj, outputParams);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "onFail errorInfo:" + errorInfo + " returnValue: mHandler:" + this.mHandler);
        ITResRequestHandler iTResRequestHandler = this.mHandler;
        if (iTResRequestHandler != null) {
            iTResRequestHandler.onFail(obj, errorInfo);
        }
    }
}
