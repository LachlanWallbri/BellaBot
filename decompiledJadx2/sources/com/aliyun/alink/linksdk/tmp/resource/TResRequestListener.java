package com.aliyun.alink.linksdk.tmp.resource;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.api.ResourceRequest;
import com.aliyun.alink.linksdk.cmp.connect.alcs.CoAPResource;
import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceRequestListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TResRequestListener implements IResourceRequestListener {
    private static final String TAG = "[Tmp]TResRequestListener";
    protected ITResRequestInnerHandler mHandler;
    protected String mIdentifer;

    public TResRequestListener(String str, ITResRequestInnerHandler iTResRequestInnerHandler) {
        this.mHandler = iTResRequestInnerHandler;
        this.mIdentifer = str;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
    public void onSuccess() {
        ALog.m479d(TAG, "onSuccess mIdentifer:" + this.mIdentifer);
        ITResRequestInnerHandler iTResRequestInnerHandler = this.mHandler;
        if (iTResRequestInnerHandler != null) {
            iTResRequestInnerHandler.onSuccess(null, null);
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
    public void onFailure(AError aError) {
        ALog.m479d(TAG, "onFailure mIdentifer:" + this.mIdentifer);
        ITResRequestInnerHandler iTResRequestInnerHandler = this.mHandler;
        if (iTResRequestInnerHandler != null) {
            iTResRequestInnerHandler.onFail(null, new ErrorInfo(aError));
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IResourceRequestListener
    public void onHandleRequest(AResource aResource, ResourceRequest resourceRequest, IResourceResponseListener iResourceResponseListener) {
        ALog.m479d(TAG, "onHandleRequest identifier:" + this.mIdentifer + " aResource:" + aResource + " request:" + resourceRequest + " mHandler:" + this.mHandler);
        String str = null;
        String str2 = (aResource == null || !(aResource instanceof CoAPResource)) ? null : ((CoAPResource) aResource).topic;
        if (resourceRequest != null && !TextUtils.isEmpty(this.mIdentifer) && this.mIdentifer.contains(TmpConstant.IDENTIFIER_RAW_DATA_DOWN)) {
            this.mHandler.onProcess(this.mIdentifer, str2, resourceRequest, new TRawResRespnseCallback(resourceRequest, aResource, iResourceResponseListener));
            return;
        }
        if (resourceRequest != null && (resourceRequest instanceof ResourceRequest)) {
            if (resourceRequest.payloadObj instanceof byte[]) {
                try {
                    str = new String((byte[]) resourceRequest.payloadObj, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                str = String.valueOf(resourceRequest.payloadObj);
            }
            ALog.m479d(TAG, "onHandleRequest payload:" + str);
            String str3 = "0";
            try {
                JSONObject parseObject = JSONObject.parseObject(str);
                if (parseObject != null) {
                    str3 = parseObject.getString("id");
                }
            } catch (Exception e2) {
                ALog.m484w(TAG, "onHandleRequest:" + e2.toString());
            }
            this.mHandler.onProcess(this.mIdentifer, resourceRequest.topic, str, new TResResponseCallback(resourceRequest, aResource, str3, iResourceResponseListener));
            return;
        }
        ALog.m484w(TAG, "onHandleRequest request error" + resourceRequest);
    }
}
