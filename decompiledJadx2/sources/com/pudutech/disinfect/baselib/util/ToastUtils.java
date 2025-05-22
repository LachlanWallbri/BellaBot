package com.pudutech.disinfect.baselib.util;

import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import com.pudutech.disinfect.baselib.ext.view.ToastView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ToastUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/ToastUtils;", "", "()V", "getCustomToast", "Landroid/widget/Toast;", NotificationCompat.CATEGORY_MESSAGE, "", "gravity", "", "time", "showCustomToast", "", "msgId", "showCustomToastLong", "showToastSys", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ToastUtils {
    public static final ToastUtils INSTANCE = new ToastUtils();

    private ToastUtils() {
    }

    public final void showCustomToast(String msg, int time) {
        getCustomToast(msg, 17, time).show();
    }

    private final Toast getCustomToast(String msg, int gravity, int time) {
        Toast toast = new Toast(KtxKt.getAppContext());
        ToastView toastView = new ToastView(KtxKt.getAppContext());
        if (msg != null) {
            toastView.setText(msg);
        }
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(time);
        toast.setView(toastView);
        LogExtKt.logD("getCustomToast()##msg:" + msg + "##gravity:" + gravity + "##time：" + time, "ToastUtils");
        return toast;
    }

    public final void showCustomToast(int msgId, int time) {
        showCustomToast(KtxKt.getAppContext().getString(msgId), time);
    }

    public final void showCustomToast(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        showCustomToast(msg, 0);
    }

    public final void showCustomToastLong(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        showCustomToast(msg, 1);
    }

    public final void showToastSys(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Toast.makeText(KtxKt.getAppContext(), msg, 0).show();
    }

    public final void showToastSys(int msgId) {
        Toast.makeText(KtxKt.getAppContext(), msgId, 0).show();
    }
}
