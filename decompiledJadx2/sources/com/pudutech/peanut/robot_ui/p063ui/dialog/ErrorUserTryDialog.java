package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ErrorUserTryDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0015\u001a\u00020\u000bH\u0002J\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u000bH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/ErrorUserTryDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "_context", "onBtnCancelClick", "Lkotlin/Function0;", "", "getOnBtnCancelClick", "()Lkotlin/jvm/functions/Function0;", "setOnBtnCancelClick", "(Lkotlin/jvm/functions/Function0;)V", "onBtnTryClick", "getOnBtnTryClick", "setOnBtnTryClick", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "init", "initView", "setContent", "m", "", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class ErrorUserTryDialog extends Dialog {
    private Context _context;
    private Function0<Unit> onBtnCancelClick;
    private Function0<Unit> onBtnTryClick;

    public final Function0<Unit> getOnBtnTryClick() {
        return this.onBtnTryClick;
    }

    public final void setOnBtnTryClick(Function0<Unit> function0) {
        this.onBtnTryClick = function0;
    }

    public final Function0<Unit> getOnBtnCancelClick() {
        return this.onBtnCancelClick;
    }

    public final void setOnBtnCancelClick(Function0<Unit> function0) {
        this.onBtnCancelClick = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorUserTryDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorUserTryDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.dialog_error_user_try, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(inflate);
        }
        setCancelable(false);
        initView();
    }

    private final void initView() {
        Button button = (Button) findViewById(C5508R.id.btn_try);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.ErrorUserTryDialog$initView$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0<Unit> onBtnTryClick = ErrorUserTryDialog.this.getOnBtnTryClick();
                    if (onBtnTryClick != null) {
                        onBtnTryClick.invoke();
                    }
                }
            });
        }
        Button button2 = (Button) findViewById(C5508R.id.btn_cancel);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.ErrorUserTryDialog$initView$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0<Unit> onBtnCancelClick = ErrorUserTryDialog.this.getOnBtnCancelClick();
                    if (onBtnCancelClick != null) {
                        onBtnCancelClick.invoke();
                    }
                }
            });
        }
    }

    public final void setContent(String m) {
        Intrinsics.checkParameterIsNotNull(m, "m");
        TextView msg_tv = (TextView) findViewById(C5508R.id.msg_tv);
        Intrinsics.checkExpressionValueIsNotNull(msg_tv, "msg_tv");
        msg_tv.setText(m);
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-1, -1);
        }
    }
}
