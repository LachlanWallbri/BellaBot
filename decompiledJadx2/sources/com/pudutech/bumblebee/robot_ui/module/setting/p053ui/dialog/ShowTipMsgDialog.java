package com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
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
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShowTipMsgDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0001;B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\u001cH\u0002J\u000e\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\nJ\b\u0010'\u001a\u00020\u001cH\u0016J\b\u0010(\u001a\u0004\u0018\u00010\u0010J\b\u0010)\u001a\u0004\u0018\u00010\u0010J\b\u0010*\u001a\u0004\u0018\u00010 J\u0010\u0010+\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010,\u001a\u00020\u001cH\u0014J\u0010\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.H\u0016J\u000e\u0010/\u001a\u00020\u001c2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u000202J)\u00103\u001a\u00020\u001c2!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c0\u0017J\u0016\u00104\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001eJ\u000e\u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\nJ\b\u00107\u001a\u00020\u001cH\u0016J\u000e\u00108\u001a\u00020\u001c2\u0006\u00109\u001a\u00020\nJ\b\u0010:\u001a\u00020\u001cH\u0002R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "Landroid/app/Dialog;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "autoHideRunnable", "Ljava/lang/Runnable;", "backTip", "Landroid/widget/Button;", "callback", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog$Callback;", "cancel", "handler", "Landroid/os/Handler;", "onClick", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "onTitleClick", "Lkotlin/Function0;", "show_tip", "Landroid/widget/TextView;", "autoHide", "time", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "changeBtnTv", "str", "dismiss", "getTipBack", "getTipCancel", "getTipTitle", "init", "onStop", "owner", "Landroidx/lifecycle/LifecycleOwner;", "setCallback", "setCanCancel", "boolean", "", "setOnClickListener", "setOnTitleClick", "setTitle", "title", "show", "showTipMsg", "tip", "stopAutoHide", "Callback", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class ShowTipMsgDialog extends Dialog implements DefaultLifecycleObserver {
    private final String TAG;
    private Context _context;
    private final Runnable autoHideRunnable;
    private Button backTip;
    private Callback callback;
    private Button cancel;
    private final Handler handler;
    private Function1<? super View, Unit> onClick;
    private Function0<Unit> onTitleClick;
    private TextView show_tip;

    /* compiled from: ShowTipMsgDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog$Callback;", "", "onDissmiss", "", "dialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface Callback {
        void onDissmiss(ShowTipMsgDialog dialog);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowTipMsgDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (ShowTipMsgDialog.this.getContext() == null || !ShowTipMsgDialog.this.isShowing()) {
                    return;
                }
                ShowTipMsgDialog.this.dismiss();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowTipMsgDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (ShowTipMsgDialog.this.getContext() == null || !ShowTipMsgDialog.this.isShowing()) {
                    return;
                }
                ShowTipMsgDialog.this.dismiss();
            }
        };
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
        View inflate = getLayoutInflater().inflate(C4188R.layout.show_msg_tip_dialog, (ViewGroup) null);
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
            this.backTip = inflate != null ? (Button) inflate.findViewById(C4188R.id.tip_back) : null;
        }
        Button button = this.cancel;
        if (button != null) {
            button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View view) {
                    String str;
                    Function1 function1;
                    ShowTipMsgDialog.Callback callback;
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    str = ShowTipMsgDialog.this.TAG;
                    Pdlog.m3273d(str, "cancel OnClick ");
                    function1 = ShowTipMsgDialog.this.onClick;
                    if (function1 != null) {
                    }
                    ShowTipMsgDialog.this.dismiss();
                    callback = ShowTipMsgDialog.this.callback;
                    if (callback != null) {
                        callback.onDissmiss(ShowTipMsgDialog.this);
                    }
                }
            }, 3, null));
        }
        Button button2 = this.backTip;
        if (button2 != null) {
            button2.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View view) {
                    Function1 function1;
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    function1 = ShowTipMsgDialog.this.onClick;
                    if (function1 != null) {
                    }
                    ShowTipMsgDialog.this.dismiss();
                }
            }, 3, null));
        }
        TextView textView = (TextView) findViewById(C4188R.id.title_text);
        if (textView != null) {
            textView.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$4
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Function0 function0;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    function0 = ShowTipMsgDialog.this.onTitleClick;
                    if (function0 != null) {
                    }
                }
            }, 3, null));
        }
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$5
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                String str;
                str = ShowTipMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnCancelListener");
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$6
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                String str;
                str = ShowTipMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnDismissListener");
            }
        });
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog$build$7
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                String str;
                str = ShowTipMsgDialog.this.TAG;
                Pdlog.m3273d(str, "OnShowListener");
            }
        });
        setCancelable(false);
    }

    /* renamed from: getTipTitle, reason: from getter */
    public final TextView getShow_tip() {
        return this.show_tip;
    }

    /* renamed from: getTipBack, reason: from getter */
    public final Button getBackTip() {
        return this.backTip;
    }

    /* renamed from: getTipCancel, reason: from getter */
    public final Button getCancel() {
        return this.cancel;
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

    public final void setOnTitleClick(Function0<Unit> onTitleClick) {
        this.onTitleClick = onTitleClick;
    }

    public final void setOnClickListener(Function1<? super View, Unit> onClick) {
        Intrinsics.checkParameterIsNotNull(onClick, "onClick");
        this.onClick = onClick;
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

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        stopAutoHide();
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
