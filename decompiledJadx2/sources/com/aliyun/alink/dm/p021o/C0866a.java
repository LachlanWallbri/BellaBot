package com.aliyun.alink.dm.p021o;

import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ClassConvertor.java */
/* renamed from: com.aliyun.alink.dm.o.a */
/* loaded from: classes.dex */
public class C0866a {
    /* renamed from: a */
    public static List<SubDeviceInfo> m181a(List<? extends BaseInfo> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BaseInfo baseInfo = list.get(i);
            if (baseInfo != null) {
                SubDeviceInfo subDeviceInfo = new SubDeviceInfo();
                subDeviceInfo.productKey = baseInfo.productKey;
                subDeviceInfo.deviceName = baseInfo.deviceName;
                arrayList.add(subDeviceInfo);
            }
        }
        return arrayList;
    }
}
