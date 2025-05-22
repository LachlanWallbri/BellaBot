package com.aliyun.alink.p022h2.p023a;

import com.aliyun.alink.p022h2.api.Constraint;
import com.aliyun.alink.p022h2.api.H2ClientException;
import com.aliyun.alink.p022h2.api.Profile;
import com.aliyun.alink.p022h2.p023a.p024a.C0873a;
import com.aliyun.alink.p022h2.p023a.p024a.C0874b;
import com.aliyun.alink.p022h2.p023a.p024a.C0875c;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: AuthenticationFactory.java */
/* renamed from: com.aliyun.alink.h2.a.b */
/* loaded from: classes.dex */
public class C0876b {

    /* renamed from: a */
    private static Map<String, InterfaceC0872a> f486a = new HashMap();

    /* renamed from: a */
    public static InterfaceC0872a m196a(Profile profile) {
        Map<String, String> authParams = profile.getAuthParams();
        if (authParams == null || authParams.isEmpty()) {
            throw new H2ClientException("authorization parameter is empty");
        }
        String str = profile.getAuthParams().get("name");
        if (Constraint.AUTH_TYPE_DEVICE_NAME.equals(str)) {
            return new C0875c(authParams);
        }
        if (Constraint.AUTH_TYPE_APP_KEY.equals(str)) {
            return new C0873a(authParams, profile.getAuthSign());
        }
        if (Constraint.AUTH_TYPE_ID2.equals(str)) {
            return new C0874b(authParams, profile.getAuthCallback());
        }
        if (f486a.containsKey(str)) {
            return f486a.get(str);
        }
        throw new H2ClientException("unsupported auth type: " + str);
    }
}
