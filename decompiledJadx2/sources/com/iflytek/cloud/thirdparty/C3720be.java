package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;

/* renamed from: com.iflytek.cloud.thirdparty.be */
/* loaded from: classes3.dex */
public class C3720be extends AbstractC3721bf {

    /* renamed from: d */
    private static final String[] f3176d = {"display_name", TransferTable.COLUMN_ID};

    /* renamed from: e */
    private static final String[] f3177e = {"display_name", "data1", "contact_id"};

    /* renamed from: f */
    private static final String[] f3178f = {TransferTable.COLUMN_ID, "has_phone_number"};

    /* renamed from: g */
    private static final String[] f3179g = {"contact_id"};

    /* renamed from: h */
    private static final String[] f3180h = {"display_name"};

    /* renamed from: i */
    private static final String[] f3181i = {"data1", "data2", "display_name"};

    /* renamed from: j */
    private static final String[] f3182j = {"has_phone_number"};

    public C3720be(Context context) {
        super(context);
        m2039a(context);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3721bf
    /* renamed from: a */
    public Uri mo2036a() {
        return ContactsContract.Contacts.CONTENT_URI;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3721bf
    /* renamed from: b */
    protected String[] mo2037b() {
        return f3176d;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3721bf
    /* renamed from: c */
    protected String mo2038c() {
        return Integer.parseInt(Build.VERSION.SDK) >= 8 ? "sort_key" : "display_name";
    }
}
