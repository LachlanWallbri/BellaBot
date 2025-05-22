package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.cloud.msc.util.Encrypter;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.iflytek.cloud.thirdparty.bf */
/* loaded from: classes3.dex */
public abstract class AbstractC3721bf {

    /* renamed from: a */
    protected static final String[] f3183a = {"number", "name", TmpConstant.TYPE_VALUE_DATE};

    /* renamed from: c */
    protected static String[] f3184c;

    /* renamed from: b */
    protected Context f3185b;

    /* renamed from: a */
    public abstract Uri mo2036a();

    /* renamed from: b */
    protected abstract String[] mo2037b();

    /* renamed from: c */
    protected abstract String mo2038c();

    public AbstractC3721bf(Context context) {
        this.f3185b = null;
        this.f3185b = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x006e, code lost:
    
        if (r8 == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0065, code lost:
    
        if (r8 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0073, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0070, code lost:
    
        r8.close();
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HashMap<String, String> m2040d() {
        String[] mo2037b = mo2037b();
        HashMap<String, String> hashMap = new HashMap<>();
        Cursor cursor = null;
        try {
            try {
                cursor = this.f3185b.getContentResolver().query(mo2036a(), mo2037b, null, null, mo2038c());
                if (cursor != null && cursor.getCount() != 0) {
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndex(mo2037b[0]));
                        String string2 = cursor.getString(cursor.getColumnIndex(mo2037b[1]));
                        if (string != null) {
                            hashMap.put(string2, string);
                        }
                    }
                    DebugLog.LogD("iFly_ContactManager", "queryContacts_20------count = " + cursor.getCount());
                }
            } catch (Exception e) {
                DebugLog.LogE(e);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0082, code lost:
    
        if (r1 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0090, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
    
        if (0 == 0) goto L23;
     */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<C3718bc> m2041e() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = this.f3185b.getContentResolver().query(Uri.parse("content://icc/adn"), null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndex("name"));
                        String string2 = cursor.getString(cursor.getColumnIndex(TransferTable.COLUMN_ID));
                        String MD5 = Encrypter.MD5(C3717bb.m2022a(cursor.getString(cursor.getColumnIndex("number"))));
                        if (string != null) {
                            arrayList.add(new C3718bc(string2, string, null, MD5, null, f3184c[9]));
                        }
                    }
                    DebugLog.LogD("iFly_ContactManager", "querySIM-------count = " + cursor.getCount());
                } else {
                    DebugLog.LogD("iFly_ContactManager", "querySIM-------cursor getCount = 0 or cursor is null");
                }
            } catch (Exception e) {
                DebugLog.LogE(e);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m2039a(Context context) {
        f3184c = new String[100];
        String[] strArr = f3184c;
        strArr[0] = "其他";
        strArr[1] = "住宅";
        strArr[2] = "手机";
        strArr[3] = "工作";
        strArr[4] = "工作传真";
        strArr[5] = "住宅传真";
        strArr[6] = "寻呼机";
        strArr[7] = "其他";
        strArr[9] = "SIM卡";
        int i = 10;
        while (true) {
            String[] strArr2 = f3184c;
            if (i >= strArr2.length) {
                return;
            }
            strArr2[i] = "自定义电话";
            i++;
        }
    }
}
