package com.pudutech.robot.opensdk.bean.pub;

import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.aliyun.IotShadow;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowAuthConfig;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowAuthConfigSdkBean;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowStateValue;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseNotifyPub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/BaseNotifyPub;", "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "()V", "getNotifyTarget", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseNotifyPub implements IPubMsg {
    public final ArrayList<String> getNotifyTarget() {
        ShadowAuthConfig authConfig;
        ShadowStateValue shadowConfig = IotShadow.INSTANCE.getShadowConfig();
        if (shadowConfig == null || (authConfig = shadowConfig.getAuthConfig()) == null) {
            return new ArrayList<>();
        }
        ArrayList<ShadowAuthConfigSdkBean> sdk = authConfig.getSdk();
        ArrayList<ShadowAuthConfigSdkBean> arrayList = new ArrayList();
        Iterator<T> it = sdk.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ArrayList<String> listener = ((ShadowAuthConfigSdkBean) next).getListener();
            if (!(listener != null ? listener.contains(getMsgType()) : false) && !IotShadow.INSTANCE.getNormalNotifyType().contains(getMsgType())) {
                z = false;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (ShadowAuthConfigSdkBean shadowAuthConfigSdkBean : arrayList) {
            if (IotShadow.INSTANCE.sdkDeviceOnline(shadowAuthConfigSdkBean.getId())) {
                arrayList2.add(shadowAuthConfigSdkBean.getId());
            } else {
                Pdlog.m3273d("BaseNotifyPub", "getNotifyTarget offline " + shadowAuthConfigSdkBean.getId());
            }
        }
        Pdlog.m3273d("BaseNotifyPub", "msg type = " + getMsgType() + ", getNotifyTarget " + arrayList2);
        return arrayList2;
    }
}
