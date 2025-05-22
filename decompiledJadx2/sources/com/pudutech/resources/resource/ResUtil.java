package com.pudutech.resources.resource;

import android.content.Context;
import android.content.res.Resources;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: ResUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/resources/resource/ResUtil;", "", "()V", "TAG", "", "appContext", "Landroid/content/Context;", "getString", "name", "init", "", "resources_bellabot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ResUtil {
    public static final ResUtil INSTANCE = new ResUtil();
    private static final String TAG = "ResUtil";
    private static Context appContext;

    private ResUtil() {
    }

    public final void init(Context appContext2) {
        Intrinsics.checkParameterIsNotNull(appContext2, "appContext");
        Pdlog.m3275i(TAG, "init. " + appContext2.getPackageName());
        appContext = appContext2;
    }

    public final String getString(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Context context = appContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appContext");
        }
        Resources resources = context.getResources();
        Context context2 = appContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appContext");
        }
        int identifier = resources.getIdentifier(name, "string", context2.getPackageName());
        if (identifier == 0) {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("not contain ");
            sb.append(name);
            sb.append(" in ");
            Context context3 = appContext;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appContext");
            }
            sb.append(context3.getPackageName());
            sb.append(" resource");
            objArr[0] = sb.toString();
            Pdlog.m3277w(TAG, objArr);
            return "";
        }
        try {
            Context context4 = appContext;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appContext");
            }
            String string = context4.getString(identifier);
            Intrinsics.checkExpressionValueIsNotNull(string, "appContext.getString(id)");
            return string;
        } catch (Exception e) {
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("not contain ");
            sb2.append(name);
            sb2.append(" in ");
            Context context5 = appContext;
            if (context5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appContext");
            }
            sb2.append(context5.getPackageName());
            sb2.append(" resource. ");
            sb2.append(e);
            objArr2[0] = sb2.toString();
            Pdlog.m3277w(TAG, objArr2);
            return "";
        }
    }
}
