package com.pudutech.base.architecture;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: CrossProcessTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/base/architecture/CrossProcessTask;", "", "()V", "TAG", "", "jumpActivity", "", "context", "Landroid/content/Context;", "outPackageName", "activity", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CrossProcessTask {
    public static final CrossProcessTask INSTANCE = new CrossProcessTask();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private CrossProcessTask() {
    }

    public final void jumpActivity(Context context, String outPackageName, String activity) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(outPackageName, "outPackageName");
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intent intent = new Intent();
        ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
        Intrinsics.checkExpressionValueIsNotNull(activityInfoArr, "packageInfo.activities");
        int length = activityInfoArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            String str = activityInfoArr[i].name;
            Intrinsics.checkExpressionValueIsNotNull(str, "it.name");
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) activity, false, 2, (Object) null)) {
                z = true;
                break;
            }
            i++;
        }
        Pdlog.m3273d(TAG, "package " + context.getPackageName() + " contains activity " + activity + " ? " + z);
        if (!z) {
            intent.setComponent(new ComponentName(outPackageName, activity));
        } else {
            intent.setComponent(new ComponentName(context.getPackageName(), activity));
        }
        context.startActivity(intent);
    }
}
