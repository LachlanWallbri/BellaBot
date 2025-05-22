package com.aliyun.alink.p022h2.p023a.p024a;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.api.Constraint;
import com.aliyun.alink.p022h2.p023a.InterfaceC0872a;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DeviceAuthHandler.java */
/* renamed from: com.aliyun.alink.h2.a.a.c */
/* loaded from: classes.dex */
public class C0875c implements InterfaceC0872a {

    /* renamed from: a */
    private Map<String, String> f485a;

    public C0875c(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        this.f485a = hashMap;
        hashMap.putAll(map);
        String valueOf = String.valueOf(System.currentTimeMillis());
        String str = map.get(Constraint.PARAM_PRODUCT_KEY);
        String str2 = map.get(Constraint.PARAM_DEVICE_NAME);
        String str3 = map.get(Constraint.PARAM_CLIENT_ID);
        String str4 = map.get(Constraint.PARAM_DEVICE_SECRET);
        String str5 = TmpConstant.KEY_CLIENT_ID + str3 + "deviceName" + str2 + "productKey" + str + "timestamp" + valueOf;
        this.f485a.put("param-timestamp", valueOf);
        this.f485a.put("param-signmethod", "hmacmd5");
        this.f485a.put("param-sign", m195a(str5, str4));
        this.f485a.remove(Constraint.PARAM_DEVICE_SECRET);
    }

    @Override // com.aliyun.alink.p022h2.p023a.InterfaceC0872a
    /* renamed from: a */
    public Map<String, String> mo193a() {
        return this.f485a;
    }

    /* renamed from: a */
    private String m195a(String str, String str2) {
        return new String(Hex.encodeHex(new HmacUtils(HmacAlgorithms.HMAC_MD5, str2).hmac(str)));
    }
}
