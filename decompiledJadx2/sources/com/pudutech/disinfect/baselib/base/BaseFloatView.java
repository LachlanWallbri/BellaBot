package com.pudutech.disinfect.baselib.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.disinfect.baselib.base.PermissionFragment;
import com.pudutech.disinfect.baselib.callback.OnPermissionResult;
import com.pudutech.disinfect.baselib.ext.lifecycle.KtxActivityManager;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseFloatView.kt */
@Deprecated(message = "不建议用")
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0018\b'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020\u0006J\u0006\u0010!\u001a\u00020\"J!\u0010#\u001a\u0004\u0018\u0001H$\"\b\b\u0000\u0010$*\u00020\u00132\b\b\u0001\u0010%\u001a\u00020&¢\u0006\u0002\u0010'J\n\u0010(\u001a\u0004\u0018\u00010\u0013H&J\u000f\u0010)\u001a\u0004\u0018\u00010&H&¢\u0006\u0002\u0010*J&\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u00020&J\b\u00100\u001a\u00020\"H&J\u001e\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020&2\u0006\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020&J\u0018\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020&2\u0006\u00107\u001a\u00020&H\u0007J\u0010\u00108\u001a\u00020\"2\b\b\u0002\u00109\u001a\u00020\u0006J\u000e\u0010:\u001a\u00020\"2\u0006\u00109\u001a\u00020\u0006J\u0010\u0010;\u001a\u00020\"2\u0006\u00109\u001a\u00020\u0006H\u0002J\u0010\u0010<\u001a\u00020\"2\u0006\u00104\u001a\u00020&H\u0016J\u001e\u0010=\u001a\u00020\"2\u0006\u00102\u001a\u00020&2\u0006\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020&R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tR\u001a\u0010\f\u001a\u00020\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0004R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006>"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/BaseFloatView;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isPermissionRequesting", "", "()Z", "setPermissionRequesting", "(Z)V", "isShowing", "setShowing", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "mLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "mView", "Landroid/view/View;", "getMView", "()Landroid/view/View;", "setMView", "(Landroid/view/View;)V", "mWindowManager", "Landroid/view/WindowManager;", "onPermissionResult", "Lcom/pudutech/disinfect/baselib/callback/OnPermissionResult;", "getOnPermissionResult", "()Lcom/pudutech/disinfect/baselib/callback/OnPermissionResult;", "setOnPermissionResult", "(Lcom/pudutech/disinfect/baselib/callback/OnPermissionResult;)V", "checkIsShow", "dismiss", "", "findViewById", ExifInterface.GPS_DIRECTION_TRUE, "id", "", "(I)Landroid/view/View;", "getContentView", "getLayoutId", "()Ljava/lang/Integer;", "layout", "l", "t", "r", "b", "onCreate", "setGravity", "gravity", "xOffset", "yOffset", "setSize", "width", "height", "show", "visible", "showView", "showWindow", "updateAlpha", "updateView", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class BaseFloatView {
    private boolean isPermissionRequesting;
    private boolean isShowing;
    private Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private WindowManager mWindowManager;
    private OnPermissionResult onPermissionResult;

    public abstract View getContentView();

    public abstract Integer getLayoutId();

    public abstract void onCreate();

    public void updateAlpha(int yOffset) {
    }

    public BaseFloatView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mContext = context;
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.mWindowManager = (WindowManager) systemService;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = 1;
        layoutParams.windowAnimations = 0;
        layoutParams.flags = 552;
        layoutParams.type = 2038;
        this.mLayoutParams = layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getMContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setMContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.mContext = context;
    }

    public final View getMView() {
        return this.mView;
    }

    public final void setMView(View view) {
        this.mView = view;
    }

    /* renamed from: isShowing, reason: from getter */
    public final boolean getIsShowing() {
        return this.isShowing;
    }

    public final void setShowing(boolean z) {
        this.isShowing = z;
    }

    /* renamed from: isPermissionRequesting, reason: from getter */
    public final boolean getIsPermissionRequesting() {
        return this.isPermissionRequesting;
    }

    public final void setPermissionRequesting(boolean z) {
        this.isPermissionRequesting = z;
    }

    public final OnPermissionResult getOnPermissionResult() {
        return this.onPermissionResult;
    }

    public final void setOnPermissionResult(OnPermissionResult onPermissionResult) {
        this.onPermissionResult = onPermissionResult;
    }

    public final void setGravity(int gravity, int xOffset, int yOffset) {
        updateAlpha(yOffset);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.gravity = gravity;
        layoutParams.x = xOffset;
        layoutParams.y = yOffset;
    }

    public final void updateView(int gravity, int xOffset, int yOffset) {
        updateAlpha(yOffset);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.gravity = gravity;
        layoutParams.x = xOffset;
        layoutParams.y = yOffset;
        View view = this.mView;
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        this.mWindowManager.updateViewLayout(view, this.mLayoutParams);
    }

    public final void layout(int l, int t, int r, int b) {
        View view = this.mView;
        if (view != null) {
            view.layout(l, t, r, b);
        }
    }

    public final void setSize(int width, int height) {
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.width = width;
        layoutParams.height = height;
    }

    public final <T extends View> T findViewById(int id) {
        View view = this.mView;
        if (view != null) {
            return (T) view.findViewById(id);
        }
        return null;
    }

    public static /* synthetic */ void show$default(BaseFloatView baseFloatView, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: show");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        baseFloatView.show(z);
    }

    public final void show(boolean visible) {
        if (!checkIsShow() && !this.isPermissionRequesting && KtxActivityManager.INSTANCE.getCurrentActivity() != null) {
            Activity currentActivity = KtxActivityManager.INSTANCE.getCurrentActivity();
            Boolean valueOf = currentActivity != null ? Boolean.valueOf(CommonExtKt.checkCanDrawOverlaysPermission(currentActivity)) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (!valueOf.booleanValue()) {
                this.isPermissionRequesting = true;
                PermissionFragment.Companion companion = PermissionFragment.INSTANCE;
                Activity currentActivity2 = KtxActivityManager.INSTANCE.getCurrentActivity();
                if (currentActivity2 != null) {
                    companion.requestPermission((FragmentActivity) currentActivity2, new OnPermissionResult() { // from class: com.pudutech.disinfect.baselib.base.BaseFloatView$show$1
                        @Override // com.pudutech.disinfect.baselib.callback.OnPermissionResult
                        public void permissionResult(boolean isOpen) {
                            OnPermissionResult onPermissionResult;
                            BaseFloatView.this.setPermissionRequesting(false);
                            if (!isOpen || (onPermissionResult = BaseFloatView.this.getOnPermissionResult()) == null) {
                                return;
                            }
                            onPermissionResult.permissionResult(isOpen);
                        }
                    });
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            showWindow(visible);
            return;
        }
        showWindow(visible);
    }

    private final void showWindow(boolean visible) {
        View inflate;
        if (this.mView == null) {
            if (getLayoutId() == null) {
                inflate = getContentView();
            } else {
                LayoutInflater from = LayoutInflater.from(this.mContext);
                Integer layoutId = getLayoutId();
                if (layoutId == null) {
                    Intrinsics.throwNpe();
                }
                inflate = from.inflate(layoutId.intValue(), (ViewGroup) null);
            }
            this.mView = inflate;
            Unit unit = Unit.INSTANCE;
        }
        if (checkIsShow()) {
            return;
        }
        onCreate();
        showView(visible);
        this.mWindowManager.addView(this.mView, this.mLayoutParams);
    }

    public final void showView(boolean visible) {
        View view = this.mView;
        if (view != null) {
            view.setVisibility(visible ? 0 : 8);
        }
    }

    public final boolean checkIsShow() {
        Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
        Intrinsics.checkExpressionValueIsNotNull(cls, "Class.forName(\"android.view.WindowManagerGlobal\")");
        Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
        Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "mWindowManagerGlobal.get…aredMethod(\"getInstance\")");
        Field declaredField = cls.getDeclaredField("mViews");
        Intrinsics.checkExpressionValueIsNotNull(declaredField, "mWindowManagerGlobal.getDeclaredField(\"mViews\")");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(declaredMethod.invoke(cls, new Object[0]));
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.ArrayList<*>");
        }
        return CollectionsKt.contains((ArrayList) obj, this.mView);
    }

    public final void dismiss() {
        View view = this.mView;
        if (view == null || !checkIsShow()) {
            return;
        }
        view.setVisibility(8);
        this.mWindowManager.removeView(view);
    }
}
