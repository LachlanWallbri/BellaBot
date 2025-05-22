package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.Uri;
import android.provider.Contacts;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;

/* renamed from: com.iflytek.cloud.thirdparty.bd */
/* loaded from: classes3.dex */
public class C3719bd extends AbstractC3721bf {

    /* renamed from: d */
    private static final String[] f3169d = {TransferTable.COLUMN_ID, "name"};

    /* renamed from: e */
    private static final String[] f3170e = {"name", "number", TransferTable.COLUMN_ID};

    /* renamed from: f */
    private static final String[] f3171f = {"person"};

    /* renamed from: g */
    private static final String[] f3172g = {"display_name"};

    /* renamed from: h */
    private static final String[] f3173h = {"number", "type", "name"};

    /* renamed from: i */
    private static final String[] f3174i = {TransferTable.COLUMN_ID, "name", "number", "type"};

    /* renamed from: j */
    private static final String[] f3175j = {"number"};

    @Override // com.iflytek.cloud.thirdparty.AbstractC3721bf
    /* renamed from: c */
    protected String mo2038c() {
        return "name";
    }

    public C3719bd(Context context) {
        super(context);
        m2039a(context);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3721bf
    /* renamed from: a */
    public Uri mo2036a() {
        return Contacts.People.CONTENT_URI;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3721bf
    /* renamed from: b */
    protected String[] mo2037b() {
        return f3169d;
    }
}
