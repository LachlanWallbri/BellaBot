package com.pudutech.freeinstall_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.DensityUtil;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: BaseDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001a\u001a\u00020\u0000H&J\u0006\u0010\u001b\u001a\u00020\u0018J\b\u0010\u001c\u001a\u00020\u0004H&J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\u0012\u0010\u001e\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J&\u0010!\u001a\u0004\u0018\u00010\f2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010&\u001a\u00020\u0018H\u0016J\b\u0010'\u001a\u00020\u0018H\u0016J\u001a\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0006\u0010*\u001a\u00020\u0018J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0014J\u000e\u0010.\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010/\u001a\u00020\u0018J\u000e\u00100\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u00101\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\u00020\u00182\u0006\u00103\u001a\u000206J\u000e\u00107\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u00108\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0004J\u0018\u00109\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\t2\u0006\u0010:\u001a\u00020;R\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/BaseDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "animStyle", "", "dimAmount", "", "fm", "Ljava/lang/ref/WeakReference;", "Landroidx/fragment/app/FragmentManager;", "height", "mDialogView", "Landroid/view/View;", "getMDialogView", "()Landroid/view/View;", "setMDialogView", "(Landroid/view/View;)V", "mLayoutId", "margin", "outCancel", "", "showBottom", "width", "bindView", "", "rootView", "dialog", "dismissDialog", "getDialogLayoutId", "initParams", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "onStart", "onViewCreated", "view", "release", "setAnimStyle", "setCanOutCancel", "canOutCancel", "setDimAmount", "setFullScreen", "setHeight", "setMargin", "setOnCancelListener", "listener", "Landroid/content/DialogInterface$OnCancelListener;", "setOnDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "setShowBottom", "setWidth", "showDialog", AIUIConstant.KEY_TAG, "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class BaseDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    private int animStyle;
    private float dimAmount = 0.5f;
    private WeakReference<FragmentManager> fm;
    private int height;
    private View mDialogView;
    private int mLayoutId;
    private float margin;
    private boolean outCancel;
    private boolean showBottom;
    private int width;

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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, C5362R.style.BaseDialogStyle);
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
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Window window2;
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        this.mDialogView = inflater.inflate(this.mLayoutId, container, false);
        Dialog dialog = getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
            View decorView = window2.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
            Sdk27PropertiesKt.setBackgroundColor(decorView, 0);
            WindowManager.LayoutParams attributes = window2.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window2.setAttributes(attributes);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.pudutech.freeinstall_ui.dialog.BaseDialog$onCreateView$2
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == 4;
                }
            });
        }
        Dialog dialog3 = getDialog();
        if (dialog3 != null && (window = dialog3.getWindow()) != null) {
            NavigationBarUtil.focusNotAle(window);
            NavigationBarUtil.hideNavigationBar(window);
            NavigationBarUtil.clearFocusNotAle(window);
        }
        return this.mDialogView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public final void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnCancelListener(listener);
        }
    }

    public final void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnDismissListener(listener);
        }
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

    public final void setFullScreen() {
        Window window;
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
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
                i = -2;
            } else if (i == 0) {
                DensityUtil densityUtil = DensityUtil.INSTANCE;
                Context context = window.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                int screenWidth = densityUtil.getScreenWidth(context);
                DensityUtil densityUtil2 = DensityUtil.INSTANCE;
                Context context2 = window.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                i = screenWidth - (densityUtil2.dip2px(context2, this.margin) << 1);
            }
            attributes.width = i;
            int i2 = this.height;
            if (i2 == 0) {
                i2 = -2;
            }
            attributes.height = i2;
            Integer valueOf = Integer.valueOf(this.animStyle);
            if (!(valueOf.intValue() >= 0)) {
                valueOf = null;
            }
            if (valueOf != null) {
                if (valueOf.intValue() == 0) {
                    int i3 = C5362R.style.DialogDefaultAnimation;
                    window.setWindowAnimations(this.animStyle);
                } else {
                    window.setWindowAnimations(this.animStyle);
                }
            }
        }
        setCancelable(this.outCancel);
    }

    public final BaseDialog showDialog(FragmentManager fm, String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        if (fm != null) {
            this.fm = new WeakReference<>(fm);
        }
        if (getDialog() == null || fm == null) {
            if (fm != null) {
                if (!isAdded()) {
                    fm.beginTransaction().remove(this).commit();
                    show(fm, tag);
                } else {
                    fm.beginTransaction().show(this).commit();
                }
            }
        } else if (isAdded()) {
            fm.beginTransaction().show(this).commit();
        } else {
            fm.beginTransaction().remove(this).commit();
            show(fm, tag);
        }
        return this;
    }

    public final void dismissDialog() {
        FragmentManager fragmentManager;
        FragmentTransaction beginTransaction;
        FragmentTransaction remove;
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Boolean valueOf = dialog != null ? Boolean.valueOf(dialog.isShowing()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.booleanValue()) {
                Pdlog.m3273d("BaseDialog", "dismissDialog");
                Dialog dialog2 = getDialog();
                if (dialog2 != null) {
                    dialog2.dismiss();
                    return;
                }
                return;
            }
        }
        Pdlog.m3273d("BaseDialog", "dismissDialog2");
        WeakReference<FragmentManager> weakReference = this.fm;
        if (weakReference == null || (fragmentManager = weakReference.get()) == null || (beginTransaction = fragmentManager.beginTransaction()) == null || (remove = beginTransaction.remove(this)) == null) {
            return;
        }
        remove.commitAllowingStateLoss();
    }

    public final void release() {
        Pdlog.m3273d("BaseDialog", "release" + this.fm);
        WeakReference<FragmentManager> weakReference = this.fm;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.fm = (WeakReference) null;
        this.mDialogView = (View) null;
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
