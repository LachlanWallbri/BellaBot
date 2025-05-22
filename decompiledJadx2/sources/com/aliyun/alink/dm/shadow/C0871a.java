package com.aliyun.alink.dm.shadow;

import android.text.TextUtils;
import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.IDeviceShadow;
import com.aliyun.alink.dm.api.IShadowRRPC;
import com.aliyun.alink.dm.p008c.C0847a;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DeviceShadowImpl.java */
/* renamed from: com.aliyun.alink.dm.shadow.a */
/* loaded from: classes.dex */
public class C0871a implements IDeviceShadow {

    /* renamed from: a */
    private BaseInfo f480a;

    public C0871a(BaseInfo baseInfo) {
        this.f480a = null;
        this.f480a = baseInfo;
    }

    @Override // com.aliyun.alink.dm.api.IDeviceShadow
    public void shadowUpload(String str, IConnectSendListener iConnectSendListener) {
        if (this.f480a == null) {
            C0859a.m134c("DeviceShadowImpl", "shadowUpload invalid state, deviceInfo is null, init sdk first.");
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_SHADOW_INVALID_STATE);
                aError.setMsg("shadowUpdate failed. SDK not inited or getDeviceShadow called before init.");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            C0859a.m134c("DeviceShadowImpl", "shadowUpload request param invalid.");
            if (iConnectSendListener != null) {
                AError aError2 = new AError();
                aError2.setCode(DMErrorCode.ERROR_SHADOW_PARAMS_INVALID);
                aError2.setMsg("shadowUpdate failed. param invalid.");
                iConnectSendListener.onFailure(null, aError2);
                return;
            }
            return;
        }
        String str2 = this.f480a.productKey;
        String str3 = this.f480a.deviceName;
        C0847a.m89a().m95a(C0852a.f379h.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, str2).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, str3), C0852a.f380i.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, str2).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, str3), str, iConnectSendListener);
    }

    @Override // com.aliyun.alink.dm.api.IDeviceShadow
    public void setShadowChangeListener(IShadowRRPC iShadowRRPC) {
        BaseInfo baseInfo = this.f480a;
        if (baseInfo == null) {
            C0859a.m134c("DeviceShadowImpl", "setShadowChangeListener invalid state, deviceInfo is null, init first.");
            if (iShadowRRPC != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_SHADOW_INVALID_STATE);
                aError.setMsg("setShadowChangeListener failed. SDK not inited or getDeviceShadow called before init.");
                iShadowRRPC.onSubscribeFailed(null, aError);
                return;
            }
            return;
        }
        String str = baseInfo.productKey;
        String str2 = this.f480a.deviceName;
        C0847a.m89a().m93a(C0852a.f380i.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, str).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, str2), C0852a.f379h.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, str).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, str2), iShadowRRPC);
    }
}
