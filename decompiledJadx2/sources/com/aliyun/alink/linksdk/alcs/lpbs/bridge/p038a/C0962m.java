package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAInitData;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAOptions;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAReqMessage;
import com.aliyun.alink.linksdk.alcs.data.ica.ICARspMessage;
import com.aliyun.alink.linksdk.alcs.data.ica.ICASubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICATranslate.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.m */
/* loaded from: classes.dex */
public class C0962m {
    /* renamed from: a */
    public static ICAInitData m386a(PalInitData palInitData) {
        if (palInitData == null) {
            return null;
        }
        if (palInitData.deviceInfo != null) {
            return new ICAInitData(palInitData.deviceInfo.productModel, palInitData.deviceInfo.deviceId, palInitData.role);
        }
        return new ICAInitData(null, null, palInitData.role);
    }

    /* renamed from: a */
    public static ICADeviceInfo m385a(PalDeviceInfo palDeviceInfo) {
        return new ICADeviceInfo(palDeviceInfo.productModel, palDeviceInfo.deviceId);
    }

    /* renamed from: a */
    public static ICAReqMessage m387a(PalReqMessage palReqMessage) {
        ICAReqMessage iCAReqMessage = new ICAReqMessage();
        iCAReqMessage.deviceInfo = new ICADeviceInfo(palReqMessage.deviceInfo.productModel, palReqMessage.deviceInfo.deviceId);
        if (palReqMessage.palOptions != null && (palReqMessage.palOptions instanceof ICAOptions)) {
            ICAOptions iCAOptions = (ICAOptions) palReqMessage.palOptions;
            iCAReqMessage.groupId = iCAOptions.groupId;
            iCAReqMessage.code = iCAOptions.code;
            iCAReqMessage.type = iCAOptions.type;
        }
        iCAReqMessage.topic = palReqMessage.topic;
        iCAReqMessage.payload = palReqMessage.payload;
        return iCAReqMessage;
    }

    /* renamed from: a */
    public static PalRspMessage m389a(ICARspMessage iCARspMessage) {
        if (iCARspMessage == null) {
            return null;
        }
        PalRspMessage palRspMessage = new PalRspMessage();
        palRspMessage.code = iCARspMessage.code;
        palRspMessage.cbContext = Integer.valueOf(iCARspMessage.cbContext);
        palRspMessage.payload = iCARspMessage.payload;
        return palRspMessage;
    }

    /* renamed from: a */
    public static ICASubMessage m388a(PalSubMessage palSubMessage) {
        if (palSubMessage == null) {
            return null;
        }
        ICASubMessage iCASubMessage = new ICASubMessage();
        iCASubMessage.deviceInfo = new ICADeviceInfo(palSubMessage.deviceInfo.productModel, palSubMessage.deviceInfo.deviceId);
        iCASubMessage.topic = palSubMessage.topic;
        iCASubMessage.payload = palSubMessage.payload;
        return iCASubMessage;
    }
}
