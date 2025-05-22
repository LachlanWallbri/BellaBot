package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.StutterStopMsgDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StutterStopMsgDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001:\u0001(B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\tJ\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\tJ\b\u0010$\u001a\u00020\u0017H\u0016J\u000e\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\tJ\b\u0010'\u001a\u00020\u0017H\u0002R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/StutterStopMsgDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "autoHideRunnable", "Ljava/lang/Runnable;", "callback", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/StutterStopMsgDialog$Callback;", "cancel", "Landroid/widget/Button;", "handler", "Landroid/os/Handler;", "show_tip", "Landroid/widget/TextView;", "autoHide", "", "time", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "changeBtnTv", "str", "init", "setCallback", "setCanCancel", "boolean", "", "setTitle", "title", "show", "showTipMsg", "tip", "stopAutoHide", "Callback", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class StutterStopMsgDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private final Runnable autoHideRunnable;
    private Callback callback;
    private Button cancel;
    private final Handler handler;
    private TextView show_tip;

    /* compiled from: StutterStopMsgDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/StutterStopMsgDialog$Callback;", "", "onDissmiss", "", "dialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/StutterStopMsgDialog;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface Callback {
        void onDissmiss(StutterStopMsgDialog dialog);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StutterStopMsgDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.StutterStopMsgDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (StutterStopMsgDialog.this.getContext() == null || !StutterStopMsgDialog.this.isShowing()) {
                    return;
                }
                StutterStopMsgDialog.this.dismiss();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StutterStopMsgDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.StutterStopMsgDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (StutterStopMsgDialog.this.getContext() == null || !StutterStopMsgDialog.this.isShowing()) {
                    return;
                }
                StutterStopMsgDialog.this.dismiss();
            }
        };
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_stutter_stop, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(inflate);
            this.show_tip = inflate != null ? (TextView) inflate.findViewById(C4188R.id.show_tip) : null;
            this.cancel = inflate != null ? (Button) inflate.findViewById(C4188R.id.cancel) : null;
        }
        Button button = this.cancel;
        if (button != null) {
            button.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.StutterStopMsgDialog$build$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, 0 == true ? 1 : 0);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick() {
                    String str;
                    StutterStopMsgDialog.Callback callback;
                    str = StutterStopMsgDialog.this.TAG;
                    Pdlog.m3273d(str, "cancel OnClick ");
                    StutterStopMsgDialog.this.dismiss();
                    callback = StutterStopMsgDialog.this.callback;
                    if (callback != null) {
                        callback.onDissmiss(StutterStopMsgDialog.this);
                    }
                }
            });
        }
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.StutterStopMsgDialog$build$3
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                String str;
                str = StutterStopMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnCancelListener");
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.StutterStopMsgDialog$build$4
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                String str;
                str = StutterStopMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnDismissListener");
            }
        });
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.StutterStopMsgDialog$build$5
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                String str;
                str = StutterStopMsgDialog.this.TAG;
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
        TextView title_text = (TextView) findViewById(C4188R.id.title_text);
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

    public final void autoHide(long time) {
        this.handler.postDelayed(this.autoHideRunnable, time);
    }

    private final void stopAutoHide() {
        this.handler.removeCallbacks(this.autoHideRunnable);
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
    }
}
