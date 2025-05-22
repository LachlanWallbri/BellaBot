package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BumbleBaseDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0006H&J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006H\u0016J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001cJ\b\u0010\u001f\u001a\u00020\u000eH\u0014J\b\u0010 \u001a\u00020\u000eH\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "onCloseListener", "Lkotlin/Function0;", "", "getOnCloseListener", "()Lkotlin/jvm/functions/Function0;", "setOnCloseListener", "(Lkotlin/jvm/functions/Function0;)V", "getLayoutId", "getString", "", "resId", "init", "initView", "view", "Landroid/view/View;", "isOpenReset", "", "setAllCancelable", "isCancel", "setData", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public abstract class BumbleBaseDialog extends Dialog {
    private Context mContext;
    private Function0<Unit> onCloseListener;

    public abstract int getLayoutId();

    /* JADX INFO: Access modifiers changed from: protected */
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    protected boolean isOpenReset() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setData() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getMContext() {
        return this.mContext;
    }

    protected final void setMContext(Context context) {
        this.mContext = context;
    }

    public final Function0<Unit> getOnCloseListener() {
        return this.onCloseListener;
    }

    public final void setOnCloseListener(Function0<Unit> function0) {
        this.onCloseListener = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BumbleBaseDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BumbleBaseDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        View viewLayout = getLayoutInflater().inflate(getLayoutId(), (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = -1;
            attributes.width = -1;
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(viewLayout);
        }
        Intrinsics.checkExpressionValueIsNotNull(viewLayout, "viewLayout");
        initView(viewLayout);
        setAllCancelable(false);
        setData();
    }

    public final void setAllCancelable(boolean isCancel) {
        setCancelable(isCancel);
        setCanceledOnTouchOutside(isCancel);
    }

    @Override // android.app.Dialog
    public void show() {
        Window window;
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
        if (!isOpenReset() || (window = getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    public String getString(int resId) {
        String string;
        Context context = getContext();
        return (context == null || (string = context.getString(resId)) == null) ? "" : string;
    }
}
