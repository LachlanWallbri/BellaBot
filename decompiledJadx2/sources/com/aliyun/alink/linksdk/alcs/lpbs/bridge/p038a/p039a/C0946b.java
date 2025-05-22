package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a;

import android.util.Base64;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthPairs;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthParams;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0940a;
import com.aliyun.alink.linksdk.alcs.lpbs.p037b.C0941b;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAAuthInfoCreater.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.a.b */
/* loaded from: classes.dex */
public class C0946b {

    /* renamed from: a */
    public static final String f807a = "2";

    /* renamed from: b */
    public static final String f808b = "0";

    /* renamed from: c */
    public static final String f809c = "1";

    /* renamed from: d */
    public static final String f810d = "Xtau@iot";

    /* renamed from: e */
    public static final String f811e = "Yx3DdsyetbSezlvc";

    /* renamed from: f */
    public static final String f812f = "1";

    /* renamed from: g */
    public static final String f813g = "001";

    /* renamed from: h */
    private static final String f814h = "[AlcsLPBS]ICAAuthInfoCreater";

    /* renamed from: i */
    private static final int f815i = 8;

    /* renamed from: j */
    private static final int f816j = 16;

    /* renamed from: a */
    public static ICAAuthPairs m374a(String str) {
        String m364a = C0940a.m364a(8);
        String m364a2 = C0940a.m364a(16);
        ICAAuthParams m375a = m375a(m364a, m364a2, str);
        ICAAuthPairs iCAAuthPairs = new ICAAuthPairs(m375a.accessKey, m375a.accessToken, m364a, m364a2);
        ALog.m479d(f814h, "authCode:" + m364a + " secret:" + m364a2 + " ak:" + m375a.accessKey + " at:" + m375a.accessToken);
        return iCAAuthPairs;
    }

    /* renamed from: a */
    public static ICAAuthParams m375a(String str, String str2, String str3) {
        ICAAuthParams iCAAuthParams = new ICAAuthParams();
        iCAAuthParams.accessKey = str + "1" + str3 + "001";
        iCAAuthParams.accessToken = Base64.encodeToString(C0941b.m365a(iCAAuthParams.accessKey, str2), 2);
        return iCAAuthParams;
    }
}
