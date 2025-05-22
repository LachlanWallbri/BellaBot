package com.livinglifetechway.quickpermissions_kotlin.util;

import android.content.Context;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionsUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ/\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\rJ#\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionsUtil;", "", "()V", "getDeniedPermissions", "", "", "permissions", "grantResults", "", "([Ljava/lang/String;[I)[Ljava/lang/String;", "getPermanentlyDeniedPermissions", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroid/support/v4/app/Fragment;[Ljava/lang/String;[I)[Ljava/lang/String;", "hasSelfPermission", "", "activity", "Landroid/content/Context;", "(Landroid/content/Context;[Ljava/lang/String;)Z", "quickpermissions-kotlin_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes.dex */
public final class PermissionsUtil {
    public static final PermissionsUtil INSTANCE = new PermissionsUtil();

    private PermissionsUtil() {
    }

    public final boolean hasSelfPermission(Context activity, String[] permissions2) {
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        if (activity == null) {
            return true;
        }
        for (String str : permissions2) {
            if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public final String[] getDeniedPermissions(String[] permissions2, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        ArrayList arrayList = new ArrayList();
        int length = permissions2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = permissions2[i];
            int i3 = i2 + 1;
            if (grantResults[i2] == -1) {
                arrayList.add(str);
            }
            i++;
            i2 = i3;
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final String[] getPermanentlyDeniedPermissions(Fragment fragment, String[] permissions2, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(fragment, "fragment");
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        ArrayList arrayList = new ArrayList();
        int length = permissions2.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = permissions2[i];
            int i3 = i2 + 1;
            if (grantResults[i2] == -1 && !fragment.shouldShowRequestPermissionRationale(str)) {
                arrayList.add(str);
            }
            i++;
            i2 = i3;
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
