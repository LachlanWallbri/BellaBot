package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthParams;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DefaultICAStorage.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.a.a */
/* loaded from: classes.dex */
public class C0945a implements InterfaceC0950f {

    /* renamed from: a */
    public static final String f800a = "[AlcsLPBS]DefaultICAStorage";

    /* renamed from: b */
    protected static final String f801b = "asKey_pre_";

    /* renamed from: c */
    protected static final String f802c = "asToken_pre_";

    /* renamed from: f */
    private static final String f803f = "DefaultICAStoragePerf";

    /* renamed from: d */
    protected SharedPreferences f804d;

    /* renamed from: e */
    protected SharedPreferences.Editor f805e;

    /* renamed from: g */
    private Context f806g;

    public C0945a(Context context) {
        this.f806g = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(f803f, 0);
        this.f804d = sharedPreferences;
        this.f805e = sharedPreferences.edit();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0950f
    /* renamed from: a */
    public void mo373a(String str, String str2, String str3) {
        this.f805e.putString(f801b + str, str2);
        this.f805e.putString(f802c + str, str3);
        this.f805e.apply();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0950f
    /* renamed from: a */
    public ICAAuthParams mo372a(String str) {
        String string = this.f804d.getString(f801b + str, null);
        String string2 = this.f804d.getString(f802c + str, null);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            ALog.m480e(f800a, "getAccessInfo empty id:" + str);
            return null;
        }
        ICAAuthParams iCAAuthParams = new ICAAuthParams();
        iCAAuthParams.accessKey = string;
        iCAAuthParams.accessToken = string2;
        return iCAAuthParams;
    }
}
