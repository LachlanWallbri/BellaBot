package com.pudutech.sysstoragetempuse.lib;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SysStorageTempUserProvider.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016JO\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\u0015J;\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/sysstoragetempuse/lib/SysStorageTempUserProvider;", "Landroid/content/ContentProvider;", "()V", RequestParameters.SUBRESOURCE_DELETE, "", "p0", "Landroid/net/Uri;", "p1", "", "p2", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "p3", "p4", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "sys_storage_temp_use"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SysStorageTempUserProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public int delete(Uri p0, String p1, String[] p2) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri p0, ContentValues p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri p0, String[] p1, String p2, String[] p3, String p4) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri p0, ContentValues p1, String p2, String[] p3) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        try {
            SysStorageTempUseTool.INSTANCE.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("SysStorageTUProvider", "SysStorageTempUserProvider start");
        return true;
    }
}
