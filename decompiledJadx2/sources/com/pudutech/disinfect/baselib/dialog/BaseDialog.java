package com.pudutech.disinfect.baselib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.disinfect.baselib.C4429R;
import com.pudutech.disinfect.baselib.ext.ExtKt;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010\u00122\u0006\u0010'\u001a\u00020\u0000H&J\b\u0010(\u001a\u00020\tH\u0016J\b\u0010)\u001a\u00020\u0004H&J\b\u0010*\u001a\u00020\tH\u0002J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u0012\u0010+\u001a\u00020\t2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J&\u0010.\u001a\u0004\u0018\u00010\u00122\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u00103\u001a\u00020\tH\u0016J\u001a\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\u00122\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u000e\u00106\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u00107\u001a\u00020\u00002\u0006\u00108\u001a\u00020\u000fJ\u000e\u00109\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u0014\u0010:\u001a\u00020\u00002\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010<\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010>\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0006J\u000e\u0010?\u001a\u00020\t2\u0006\u0010;\u001a\u00020@J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u000fJ\u000e\u0010B\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0004J\u0018\u0010C\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\f2\u0006\u0010D\u001a\u00020ER\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\t\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/dialog/BaseDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "animStyle", "", "dimAmount", "", "dismissListener", "Lkotlin/Function0;", "", "fm", "Ljava/lang/ref/WeakReference;", "Landroidx/fragment/app/FragmentManager;", "height", "isBlurBg", "", "isFullScreen", "mDialogView", "Landroid/view/View;", "getMDialogView", "()Landroid/view/View;", "setMDialogView", "(Landroid/view/View;)V", "mLayoutId", "margin", "onClickListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "v", "getOnClickListener", "()Lkotlin/jvm/functions/Function1;", "setOnClickListener", "(Lkotlin/jvm/functions/Function1;)V", "outCancel", "showBottom", "width", "bindView", "rootView", "dialog", "dismissDialog", "getDialogLayoutId", "initParams", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "onViewCreated", "view", "setAnimStyle", "setCanOutCancel", "canOutCancel", "setDimAmount", "setDismissListener", "listener", "setHeight", "setIsBlurBg", "setMargin", "setOnCancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "setShowBottom", "setWidth", "showDialog", AIUIConstant.KEY_TAG, "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class BaseDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    private int animStyle;
    private Function0<Unit> dismissListener;
    private WeakReference<FragmentManager> fm;
    private int height;
    private View mDialogView;
    private int mLayoutId;
    private float margin;
    private Function1<? super View, Unit> onClickListener;
    private boolean outCancel;
    private boolean showBottom;
    private int width;
    private float dimAmount = 0.5f;
    private boolean isBlurBg = true;
    private boolean isFullScreen = true;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public abstract void bindView(View rootView, BaseDialog dialog);

    public abstract int getDialogLayoutId();

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final View getMDialogView() {
        return this.mDialogView;
    }

    public final void setMDialogView(View view) {
        this.mDialogView = view;
    }

    public final Function1<View, Unit> getOnClickListener() {
        return this.onClickListener;
    }

    public final void setOnClickListener(Function1<? super View, Unit> function1) {
        this.onClickListener = function1;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, C4429R.style.BaseDialogStyle);
        this.mLayoutId = getDialogLayoutId();
        if (savedInstanceState != null) {
            this.margin = savedInstanceState.getFloat("margin");
            this.width = savedInstanceState.getInt("width");
            this.height = savedInstanceState.getInt("height");
            this.dimAmount = savedInstanceState.getFloat("dim_amount");
            this.showBottom = savedInstanceState.getBoolean("bottom");
            this.outCancel = savedInstanceState.getBoolean("cancel");
            this.animStyle = savedInstanceState.getInt("anim_style");
            this.mLayoutId = savedInstanceState.getInt("layout_id");
            this.isBlurBg = savedInstanceState.getBoolean("blur_bg");
            this.isFullScreen = savedInstanceState.getBoolean("full_screen");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        int i = this.mLayoutId;
        if (i == 0) {
            return null;
        }
        this.mDialogView = inflater.inflate(i, container, false);
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            if (this.isBlurBg) {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    Window window2 = activity.getWindow();
                    Intrinsics.checkExpressionValueIsNotNull(window2, "window");
                    View decorView = window2.getDecorView();
                    Intrinsics.checkExpressionValueIsNotNull(decorView, "window.decorView");
                    Bitmap bitmapFromView = CommonExtKt.getBitmapFromView(decorView);
                    if (bitmapFromView != null) {
                        Context context = window.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                        window.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), ExtKt.blur(bitmapFromView, context, 25.0f)));
                        bitmapFromView.recycle();
                    }
                }
            } else {
                window.setBackgroundDrawable(new ColorDrawable(0));
                window.getDecorView().setBackgroundColor(0);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.height = -1;
                window.setAttributes(attributes);
            }
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.pudutech.disinfect.baselib.dialog.BaseDialog$onCreateView$2
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    return i2 == 4;
                }
            });
        }
        return this.mDialogView;
    }

    public final BaseDialog isFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        return this;
    }

    public final void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnCancelListener(listener);
        }
    }

    public final BaseDialog setDismissListener(Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.dismissListener = listener;
        return this;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        bindView(this.mDialogView, this);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        initParams();
    }

    private final void initParams() {
        Window window;
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = this.dimAmount;
            if (this.showBottom) {
                attributes.gravity = 80;
            }
            int i = this.width;
            if (i == -1) {
                i = -1;
            } else if (i == 0) {
                Context context = window.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                int screenWidth = ExtKt.getScreenWidth(context);
                Context context2 = window.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                i = screenWidth - (ExtKt.dp2px(context2, this.margin) << 1);
            }
            attributes.width = i;
            int i2 = this.height;
            if (i2 == 0) {
                i2 = -1;
            }
            attributes.height = i2;
            Integer valueOf = Integer.valueOf(this.animStyle);
            if (!(valueOf.intValue() >= 0)) {
                valueOf = null;
            }
            if (valueOf != null) {
                if (valueOf.intValue() == 0) {
                    window.setWindowAnimations(C4429R.style.DialogDefaultAnimation);
                } else {
                    window.setWindowAnimations(this.animStyle);
                }
            }
        }
        setCancelable(this.outCancel);
        if (this.isFullScreen) {
            Dialog dialog2 = getDialog();
            NavigationBarUtil.fullScreen(dialog2 != null ? dialog2.getWindow() : null);
        }
    }

    public final BaseDialog showDialog(FragmentManager fm, String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        if (fm != null) {
            if (fm.isDestroyed()) {
                throw new IllegalStateException("fm is destroyed");
            }
            this.fm = new WeakReference<>(fm);
            fm.beginTransaction().remove(this).commit();
            super.show(fm, tag);
        }
        return this;
    }

    public void dismissDialog() {
        super.dismiss();
        Function0<Unit> function0 = this.dismissListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final BaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public final BaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public final BaseDialog setMargin(float margin) {
        this.margin = margin;
        return this;
    }

    public final BaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public final BaseDialog setIsBlurBg(boolean isBlurBg) {
        this.isBlurBg = isBlurBg;
        return this;
    }

    public final BaseDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public final BaseDialog setCanOutCancel(boolean canOutCancel) {
        this.outCancel = canOutCancel;
        return this;
    }

    public final BaseDialog setAnimStyle(int animStyle) {
        this.animStyle = animStyle;
        return this;
    }
}
