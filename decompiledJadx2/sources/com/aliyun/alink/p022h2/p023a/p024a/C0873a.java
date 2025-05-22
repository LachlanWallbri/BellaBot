package com.aliyun.alink.p022h2.p023a.p024a;

import com.aliyun.alink.p022h2.api.AuthSignMethod;
import com.aliyun.alink.p022h2.api.Constraint;
import com.aliyun.alink.p022h2.api.IAuthSign;
import com.aliyun.alink.p022h2.p023a.InterfaceC0872a;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: AppKeyAuthHandler.java */
/* renamed from: com.aliyun.alink.h2.a.a.a */
/* loaded from: classes.dex */
public class C0873a implements InterfaceC0872a {

    /* renamed from: a */
    private static final String f481a = AuthSignMethod.SHA1.getMethod();

    /* renamed from: b */
    private Map<String, String> f482b;

    public C0873a(Map<String, String> map, IAuthSign iAuthSign) {
        String authSign;
        HashMap hashMap = new HashMap();
        this.f482b = hashMap;
        hashMap.putAll(map);
        String str = this.f482b.get(Constraint.PARAM_APP_SECRET);
        String valueOf = String.valueOf(new Random().nextLong());
        String str2 = "random=" + valueOf;
        if (iAuthSign == null) {
            authSign = m194a(str2, str);
            this.f482b.put(Constraint.PARAM_SIGN_METHOD, f481a);
        } else {
            authSign = iAuthSign.getAuthSign(str2);
        }
        this.f482b.put("name", Constraint.AUTH_TYPE_APP_KEY);
        this.f482b.put("param-sign", authSign);
        this.f482b.put("param-random", valueOf);
        this.f482b.remove(Constraint.PARAM_APP_SECRET);
    }

    @Override // com.aliyun.alink.p022h2.p023a.InterfaceC0872a
    /* renamed from: a */
    public Map<String, String> mo193a() {
        return this.f482b;
    }

    /* renamed from: a */
    private String m194a(String str, String str2) {
        return new String(Hex.encodeHex(new HmacUtils(HmacAlgorithms.HMAC_SHA_1, str2).hmac(str)));
    }
}
