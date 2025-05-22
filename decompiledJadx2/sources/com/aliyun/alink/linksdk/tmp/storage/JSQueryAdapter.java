package com.aliyun.alink.linksdk.tmp.storage;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsQeuryCallback;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.C1024R;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.utils.ResHelper;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JSQueryAdapter implements IJsProvider {
    private static final String TAG = "[Tmp]JSQueryAdapter";

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IJsProvider
    public void queryJsCode(String str, String str2, IJsQeuryCallback iJsQeuryCallback) {
        String script = TmpStorage.getInstance().getScript(str);
        if (!TextUtils.isEmpty(script)) {
            iJsQeuryCallback.onLoad(str, script);
        } else {
            iJsQeuryCallback.onLoad(str, ResHelper.getRawDeviceModel(TmpSdk.getContext(), C1024R.raw.a11pmnp5zxp));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.tmp.storage.JSQueryAdapter$1 */
    /* loaded from: classes.dex */
    class C11351 implements IConnectSendListener {
        final /* synthetic */ IJsQeuryCallback val$callback;
        final /* synthetic */ String val$productKey;

        C11351(IJsQeuryCallback iJsQeuryCallback, String str) {
            this.val$callback = iJsQeuryCallback;
            this.val$productKey = str;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
        public void onResponse(ARequest aRequest, AResponse aResponse) {
            if (aResponse == null || aResponse.data == null) {
                ALog.m480e(JSQueryAdapter.TAG, "queryJsCode onResponse data null");
                this.val$callback.onLoad(this.val$productKey, null);
            }
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
        public void onFailure(ARequest aRequest, AError aError) {
            StringBuilder sb = new StringBuilder();
            sb.append("queryJsCode onFailure error:");
            sb.append(aError);
            ALog.m480e(JSQueryAdapter.TAG, sb.toString() == null ? "null" : aError.toString());
            this.val$callback.onLoad(this.val$productKey, null);
        }
    }
}
