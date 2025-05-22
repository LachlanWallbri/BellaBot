package com.aliyun.alink.dm.p014h;

import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DMErrorConverter.java */
/* renamed from: com.aliyun.alink.dm.h.a */
/* loaded from: classes.dex */
public class C0856a {
    /* renamed from: a */
    public static AError m127a(AError aError) {
        if (aError == null) {
            return aError;
        }
        AError m128b = m128b(aError);
        if (m128b.getCode() == CmpError.MQTT_CONNECT_FAIL().getCode()) {
            m128b.setCode(-33);
            return m128b;
        }
        if (m128b.getCode() == CmpError.MQTT_CONNECT_FAIL().getCode()) {
            m128b.setCode(-33);
            return m128b;
        }
        if (m128b.getCode() == CmpError.MQTT_CONNECT_FAIL().getCode()) {
            m128b.setCode(-33);
        }
        return m128b;
    }

    /* renamed from: b */
    public static AError m128b(AError aError) {
        if (aError == null) {
            return aError;
        }
        AError aError2 = new AError();
        aError2.setCode(aError.getCode());
        aError2.setSubCode(aError.getSubCode());
        aError2.setDomain(aError.getDomain());
        aError2.setSubDomain(aError.getSubDomain());
        aError2.setMsg(aError.getMsg());
        aError2.setSubMsg(aError.getSubMsg());
        aError2.setOriginResponseObject(aError.getOriginResponseObject());
        return aError2;
    }
}
