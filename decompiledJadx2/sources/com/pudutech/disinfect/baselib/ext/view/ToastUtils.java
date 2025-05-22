package com.pudutech.disinfect.baselib.ext.view;

import android.content.Context;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.pudutech.disinfect.baselib.base.KtxKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ToastUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0018\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006J\u0018\u0010\u0018\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/ext/view/ToastUtils;", "", "()V", "mToast", "Landroid/widget/Toast;", "mToastGravity", "", "mToastView", "Lcom/pudutech/disinfect/baselib/ext/view/ToastView;", "cancelCurrentToast", "", "hide", "reToast", "msgId", NotificationCompat.CATEGORY_MESSAGE, "", "resetToast", "setToastGravity", "gravity", "setToastView", "context", "Landroid/content/Context;", "showLongToast", "showShortToast", "showToast", "time", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ToastUtils {
    private static Toast mToast;
    private static ToastView mToastView;
    public static final ToastUtils INSTANCE = new ToastUtils();
    private static int mToastGravity = -1;

    static {
        if (mToastView == null) {
            mToastView = new ToastView(KtxKt.getAppContext());
        }
        if (mToast == null) {
            mToast = new Toast(KtxKt.getAppContext());
        }
        Toast toast = mToast;
        if (toast == null) {
            Intrinsics.throwNpe();
        }
        toast.setGravity(17, 0, 0);
        Toast toast2 = mToast;
        if (toast2 == null) {
            Intrinsics.throwNpe();
        }
        toast2.setDuration(1);
        Toast toast3 = mToast;
        if (toast3 == null) {
            Intrinsics.throwNpe();
        }
        toast3.setView(mToastView);
    }

    private ToastUtils() {
    }

    public final void showToast(String msg, int time) {
        Toast toast = mToast;
        if (toast == null) {
            Intrinsics.throwNpe();
        }
        toast.setDuration(time);
        ToastView toastView = mToastView;
        if (toastView != null) {
            if (msg == null) {
                Intrinsics.throwNpe();
            }
            toastView.setText(msg);
        }
        Toast toast2 = mToast;
        if (toast2 == null) {
            Intrinsics.throwNpe();
        }
        toast2.show();
    }

    public final void showToast(int msgId, int time) {
        showToast(KtxKt.getAppContext().getString(msgId), time);
    }

    public final void showShortToast(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        showToast(msg, 0);
    }

    public final void showLongToast(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        showToast(msg, 1);
    }

    public final void cancelCurrentToast() {
        Toast toast = mToast;
        if (toast != null) {
            if (toast == null) {
                Intrinsics.throwNpe();
            }
            toast.cancel();
        }
    }

    public final void reToast(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Toast.makeText(KtxKt.getAppContext(), msg, 0).show();
    }

    public final void reToast(int msgId) {
        Toast.makeText(KtxKt.getAppContext(), msgId, 0).show();
    }

    public final void setToastView(Context context) {
        if (context == null) {
            Intrinsics.throwNpe();
        }
        mToastView = new ToastView(context);
    }

    public final void setToastGravity(int gravity) {
        mToastGravity = gravity;
    }

    public final void resetToast() {
        mToastView = (ToastView) null;
        mToastGravity = -1;
        mToast = (Toast) null;
    }

    private final void hide() {
        Toast toast = mToast;
        if (toast == null || toast == null) {
            return;
        }
        toast.cancel();
    }
}
