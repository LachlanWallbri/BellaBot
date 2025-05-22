package com.aliyun.alink.linksdk.tmp.device.deviceshadow;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;
import java.util.Set;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TPropEventHandler implements INotifyHandler {
    protected static final String TAG = "[Tmp]TPropEventHandler";
    protected INotifyHandler mEventCallback;
    protected WeakReference<TDeviceShadow> mShadowRef;

    public TPropEventHandler(TDeviceShadow tDeviceShadow) {
        this.mShadowRef = new WeakReference<>(tDeviceShadow);
    }

    @Override // com.aliyun.alink.linksdk.tmp.event.INotifyHandler
    public void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        JSONObject jSONObject;
        TDeviceShadow tDeviceShadow = this.mShadowRef.get();
        ALog.m479d(TAG, "onMessage shadow:" + tDeviceShadow + " response:" + tmpCommonResponse + " mEventCallback:" + this.mEventCallback);
        if (tDeviceShadow == null || tmpCommonResponse == null) {
            LogCat.m471e(TAG, "onMessage null");
            return;
        }
        tmpCommonResponse.setIotId(tDeviceShadow.getIotId());
        try {
            JSONObject parseObject = JSONObject.parseObject(tmpCommonResponse.getResponseText());
            String string = parseObject.getString("method");
            long currentTimeMillis = System.currentTimeMillis();
            if (!TextUtils.isEmpty(string) && string.contains(TmpConstant.METHOD_PROPERTY_POST) && (jSONObject = parseObject.getJSONObject("params")) != null) {
                Set<String> keySet = jSONObject.keySet();
                if (keySet != null) {
                    for (String str : keySet) {
                        Object obj = jSONObject.get(str);
                        if (obj != null) {
                            if (obj instanceof JSONObject) {
                                JSONObject jSONObject2 = (JSONObject) obj;
                                if (!jSONObject2.containsKey("time")) {
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("time", (Object) Long.valueOf(currentTimeMillis));
                                    jSONObject3.put(ES6Iterator.VALUE_PROPERTY, (Object) jSONObject2);
                                    jSONObject.put(str, (Object) jSONObject3);
                                }
                            } else {
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("time", (Object) Long.valueOf(currentTimeMillis));
                                jSONObject4.put(ES6Iterator.VALUE_PROPERTY, obj);
                                jSONObject.put(str, (Object) jSONObject4);
                            }
                        }
                    }
                }
                parseObject.put("params", (Object) jSONObject);
                tmpCommonResponse.setResponseText(parseObject.toString());
            }
        } catch (Exception e) {
            ALog.m484w(TAG, e.toString());
        }
        INotifyHandler iNotifyHandler = this.mEventCallback;
        if (iNotifyHandler != null) {
            iNotifyHandler.onMessage(tmpCommonRequest, tmpCommonResponse);
        }
        DeviceShadowMgr.getInstance().onMessage(tmpCommonRequest, tmpCommonResponse);
    }

    public boolean subEvent(INotifyHandler iNotifyHandler) {
        this.mEventCallback = iNotifyHandler;
        return true;
    }

    public boolean unsubEvent(INotifyHandler iNotifyHandler) {
        this.mEventCallback = null;
        return true;
    }
}
