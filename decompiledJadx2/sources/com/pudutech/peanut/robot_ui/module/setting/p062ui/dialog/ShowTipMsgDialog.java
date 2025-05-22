package com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShowTipMsgDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0001+B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\nJ\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#J\u0016\u0010$\u001a\u00020\u00132\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012J\u000e\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\nJ\b\u0010'\u001a\u00020\u0013H\u0016J\u000e\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\nJ\b\u0010*\u001a\u00020\u0013H\u0002R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "Landroid/app/Dialog;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "callback", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog$Callback;", "cancel", "Landroid/widget/Button;", "onTitleClick", "Lkotlin/Function0;", "", "show_tip", "Landroid/widget/TextView;", "autoHide", "time", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "changeBtnTv", "str", "init", "onStop", "owner", "Landroidx/lifecycle/LifecycleOwner;", "setCallback", "setCanCancel", "boolean", "", "setOnTitleClick", "setTitle", "title", "show", "showTipMsg", "tip", "stopAutoHide", "Callback", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class ShowTipMsgDialog extends Dialog implements DefaultLifecycleObserver {
    private final String TAG;
    private Context _context;
    private Callback callback;
    private Button cancel;
    private Function0<Unit> onTitleClick;
    private TextView show_tip;

    /* compiled from: ShowTipMsgDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog$Callback;", "", "onDissmiss", "", "dialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface Callback {
        void onDissmiss(ShowTipMsgDialog dialog);
    }

    private final void stopAutoHide() {
    }

    public final void autoHide(long time) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowTipMsgDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowTipMsgDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Pdlog.m3273d(this.TAG, "onStop ShowTipMsgDialog");
        dismiss();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.show_msg_tip_dialog, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(inflate);
            this.show_tip = inflate != null ? (TextView) inflate.findViewById(C5508R.id.show_tip) : null;
            this.cancel = inflate != null ? (Button) inflate.findViewById(C5508R.id.cancel) : null;
        }
        Button button = this.cancel;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    ShowTipMsgDialog.Callback callback;
                    str = ShowTipMsgDialog.this.TAG;
                    Pdlog.m3273d(str, "cancel OnClick ");
                    ShowTipMsgDialog.this.dismiss();
                    callback = ShowTipMsgDialog.this.callback;
                    if (callback != null) {
                        callback.onDissmiss(ShowTipMsgDialog.this);
                    }
                }
            });
        }
        TextView textView = (TextView) findViewById(C5508R.id.title_text);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0 function0;
                    function0 = ShowTipMsgDialog.this.onTitleClick;
                    if (function0 != null) {
                    }
                }
            });
        }
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$4
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                String str;
                str = ShowTipMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnCancelListener");
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$5
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                String str;
                str = ShowTipMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnDismissListener");
            }
        });
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$6
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                String str;
                str = ShowTipMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnShowListener");
            }
        });
        setCancelable(false);
    }

    public final void showTipMsg(String tip) {
        Intrinsics.checkParameterIsNotNull(tip, "tip");
        TextView textView = this.show_tip;
        if (textView != null) {
            textView.setText(tip);
        }
    }

    public final void setTitle(String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        TextView title_text = (TextView) findViewById(C5508R.id.title_text);
        Intrinsics.checkExpressionValueIsNotNull(title_text, "title_text");
        title_text.setText(title);
    }

    public final void setCallback(Callback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    public final void setCanCancel(boolean r2) {
        if (r2) {
            Button button = this.cancel;
            if (button != null) {
                button.setVisibility(0);
                return;
            }
            return;
        }
        Button button2 = this.cancel;
        if (button2 != null) {
            button2.setVisibility(4);
        }
    }

    public final void changeBtnTv(String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        Button button = this.cancel;
        if (button != null) {
            button.setText(str);
        }
    }

    public final void setOnTitleClick(Function0<Unit> onTitleClick) {
        this.onTitleClick = onTitleClick;
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        stopAutoHide();
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
        try {
            Context context = this._context;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            }
            ((AppCompatActivity) context).getLifecycle().addObserver(this);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "show : " + Log.getStackTraceString(e));
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        try {
            Context context = this._context;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            }
            ((AppCompatActivity) context).getLifecycle().removeObserver(this);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "show : " + Log.getStackTraceString(e));
        }
    }
}
