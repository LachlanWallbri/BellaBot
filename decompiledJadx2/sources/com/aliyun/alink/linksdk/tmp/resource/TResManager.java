package com.aliyun.alink.linksdk.tmp.resource;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.resource.ResDescpt;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TResManager {
    protected static final String TAG = "[Tmp]TResManager";
    protected Map<String, ITResRequestInnerHandler> mResHandlerList;

    private TResManager() {
        this.mResHandlerList = new HashMap();
    }

    public static TResManager getinstance() {
        return SingleInstanceHolder.mInstance;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    protected static class SingleInstanceHolder {
        protected static TResManager mInstance = new TResManager();

        protected SingleInstanceHolder() {
        }
    }

    public String regRes(ConnectWrapper connectWrapper, String str, DeviceModel deviceModel, String str2, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler) {
        String topicStr = TextHelper.getTopicStr(deviceModel, str2);
        if (regTopic(connectWrapper, str, str2, topicStr, z, iTResRequestInnerHandler)) {
            return topicStr;
        }
        return null;
    }

    public String regRes(ConnectWrapper connectWrapper, DeviceModel deviceModel, String str, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler) {
        return regRes(connectWrapper, null, deviceModel, str, z, iTResRequestInnerHandler);
    }

    public boolean unRegRes(ConnectWrapper connectWrapper, DeviceModel deviceModel, String str) {
        return unRegTopic(connectWrapper, str, TextHelper.getTopicStr(deviceModel, str));
    }

    public boolean triggerResource(ConnectWrapper connectWrapper, DeviceModel deviceModel, String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        return triggerTopic(connectWrapper, str, deviceModel.getEventMethod(str), TextHelper.getTopicStr(deviceModel, str), outputParams, iPublishResourceListener);
    }

    public boolean regTopic(ConnectWrapper connectWrapper, String str, String str2, String str3, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler) {
        return connectWrapper.regTopic(str, str2, str3, z, iTResRequestInnerHandler);
    }

    public boolean regTopic(ConnectWrapper connectWrapper, String str, String str2, boolean z, ITResRequestInnerHandler iTResRequestInnerHandler) {
        return regTopic(connectWrapper, null, str, str2, z, iTResRequestInnerHandler);
    }

    public boolean unRegTopic(ConnectWrapper connectWrapper, String str, String str2) {
        return connectWrapper.unRegTopic(str, str2);
    }

    protected boolean triggerTopic(ConnectWrapper connectWrapper, String str, String str2, String str3, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        return connectWrapper.resourcePublish(str, str2, str3, outputParams, iPublishResourceListener);
    }

    public boolean triggerRawTopic(ConnectWrapper connectWrapper, String str, String str2, byte[] bArr, IPublishResourceListener iPublishResourceListener) {
        return connectWrapper.rawResourcePublish(str, str2, bArr, iPublishResourceListener);
    }

    public static ResDescpt.ResElementType getIdType(DeviceModel deviceModel, String str) {
        if (TextUtils.isEmpty(str)) {
            return ResDescpt.ResElementType.SERVICE;
        }
        if (str.equalsIgnoreCase("dev")) {
            return ResDescpt.ResElementType.DISCOVERY;
        }
        if (str.equalsIgnoreCase(TmpConstant.PROPERTY_IDENTIFIER_GET) || str.equalsIgnoreCase(TmpConstant.PROPERTY_IDENTIFIER_SET)) {
            return ResDescpt.ResElementType.PROPERTY;
        }
        if (deviceModel != null && !TextUtils.isEmpty(deviceModel.getEventMethod(str))) {
            return ResDescpt.ResElementType.EVENT;
        }
        if (deviceModel != null && !TextUtils.isEmpty(deviceModel.getServiceMethod(str))) {
            return ResDescpt.ResElementType.SERVICE;
        }
        return ResDescpt.ResElementType.ALCS;
    }
}
