package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ConfirmTipDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u00108\u001a\u00020+2\u0006\u00109\u001a\u00020:J\b\u0010;\u001a\u00020+H\u0002J\b\u0010<\u001a\u00020+H\u0016J\u0010\u0010=\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020@H\u0002J\u000e\u0010A\u001a\u00020+2\u0006\u0010B\u001a\u00020\u0006J\u000e\u0010C\u001a\u00020+2\u0006\u0010B\u001a\u00020\u0006J\b\u0010D\u001a\u00020+H\u0016J\b\u0010E\u001a\u00020+H\u0002R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010$\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0019\"\u0004\b&\u0010\u001bR\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00100\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R$\u00104\u001a\u00020\f2\u0006\u00103\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0019\"\u0004\b6\u0010\u001bR\u0010\u00107\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ConfirmTipDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isReverseButton", "", "(Landroid/content/Context;Z)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "autoHideRunnable", "Ljava/lang/Runnable;", ES6Iterator.VALUE_PROPERTY, "btn1BgId", "getBtn1BgId", "()I", "setBtn1BgId", "(I)V", "btn1Text", "getBtn1Text", "()Ljava/lang/String;", "setBtn1Text", "(Ljava/lang/String;)V", "btn2Text", "getBtn2Text", "setBtn2Text", "btn_1", "Landroid/widget/Button;", "btn_2", "handler", "Landroid/os/Handler;", NotificationCompat.CATEGORY_MESSAGE, "getMsg", "setMsg", "msg_tv", "Landroid/widget/TextView;", "onBtn1Click", "Lkotlin/Function0;", "", "getOnBtn1Click", "()Lkotlin/jvm/functions/Function0;", "setOnBtn1Click", "(Lkotlin/jvm/functions/Function0;)V", "onBtn2Click", "getOnBtn2Click", "setOnBtn2Click", "v", "title", "getTitle", "setTitle", "title_tv", "autoHide", "time", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "init", "initView", "layout", "Landroid/view/View;", "setBtn1Visibility", "gone", "setBtn2Visibility", "show", "stopAutoHide", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public class ConfirmTipDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private final Runnable autoHideRunnable;
    private int btn1BgId;
    private String btn1Text;
    private String btn2Text;
    private Button btn_1;
    private Button btn_2;
    private final Handler handler;
    private boolean isReverseButton;
    private String msg;
    private TextView msg_tv;
    private Function0<Unit> onBtn1Click;
    private Function0<Unit> onBtn2Click;
    private String title;
    private TextView title_tv;

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        this.title = v;
        TextView textView = this.title_tv;
        if (textView != null) {
            textView.setText(v);
        }
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setMsg(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.msg = value;
        TextView textView = this.msg_tv;
        if (textView != null) {
            textView.setText(value);
        }
    }

    public final String getBtn1Text() {
        return this.btn1Text;
    }

    public final void setBtn1Text(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.btn1Text = value;
        Button button = this.btn_1;
        if (button != null) {
            button.setText(value);
        }
    }

    public final String getBtn2Text() {
        return this.btn2Text;
    }

    public final void setBtn2Text(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.btn2Text = value;
        Button button = this.btn_2;
        if (button != null) {
            button.setText(value);
        }
    }

    public final int getBtn1BgId() {
        return this.btn1BgId;
    }

    public final void setBtn1BgId(int i) {
        this.btn1BgId = i;
        Button button = this.btn_1;
        if (button != null) {
            button.setBackground(ContextCompat.getDrawable(getContext(), i));
        }
    }

    public final Function0<Unit> getOnBtn1Click() {
        return this.onBtn1Click;
    }

    public final void setOnBtn1Click(Function0<Unit> function0) {
        this.onBtn1Click = function0;
    }

    public final Function0<Unit> getOnBtn2Click() {
        return this.onBtn2Click;
    }

    public final void setOnBtn2Click(Function0<Unit> function0) {
        this.onBtn2Click = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfirmTipDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.msg = "";
        this.btn1Text = "";
        this.btn2Text = "";
        this.btn1BgId = C4188R.drawable.shape_dialog_next_reboot_btn;
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmTipDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (ConfirmTipDialog.this.getContext() == null || !ConfirmTipDialog.this.isShowing()) {
                    return;
                }
                ConfirmTipDialog.this.dismiss();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfirmTipDialog(Context context, boolean z) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.msg = "";
        this.btn1Text = "";
        this.btn2Text = "";
        this.btn1BgId = C4188R.drawable.shape_dialog_next_reboot_btn;
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmTipDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (ConfirmTipDialog.this.getContext() == null || !ConfirmTipDialog.this.isShowing()) {
                    return;
                }
                ConfirmTipDialog.this.dismiss();
            }
        };
        this.isReverseButton = z;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfirmTipDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.msg = "";
        this.btn1Text = "";
        this.btn2Text = "";
        this.btn1BgId = C4188R.drawable.shape_dialog_next_reboot_btn;
        this.handler = new Handler(Looper.getMainLooper());
        this.autoHideRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmTipDialog$autoHideRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                if (ConfirmTipDialog.this.getContext() == null || !ConfirmTipDialog.this.isShowing()) {
                    return;
                }
                ConfirmTipDialog.this.dismiss();
            }
        };
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View viewLayout = getLayoutInflater().inflate(this.isReverseButton ? C4188R.layout.dialog_confirm_tip_reverse_btn : C4188R.layout.dialog_confirm_tip, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(viewLayout);
        }
        setCancelable(false);
        Intrinsics.checkExpressionValueIsNotNull(viewLayout, "viewLayout");
        initView(viewLayout);
    }

    private final void initView(View layout) {
        this.title_tv = (TextView) layout.findViewById(C4188R.id.title_tv);
        this.msg_tv = (TextView) layout.findViewById(C4188R.id.msg_tv);
        this.btn_1 = (Button) layout.findViewById(C4188R.id.btn_1);
        this.btn_2 = (Button) layout.findViewById(C4188R.id.btn_2);
        TextView textView = this.title_tv;
        if (textView != null) {
            textView.setText(this.title);
        }
        TextView textView2 = this.msg_tv;
        if (textView2 != null) {
            textView2.setText(this.msg);
        }
        Button button = this.btn_1;
        if (button != null) {
            button.setText(this.btn1Text);
        }
        Button button2 = this.btn_2;
        if (button2 != null) {
            button2.setText(this.btn2Text);
        }
        Button button3 = this.btn_1;
        if (button3 != null) {
            button3.setBackground(ContextCompat.getDrawable(getContext(), this.btn1BgId));
        }
        Button button4 = this.btn_2;
        if (button4 != null) {
            button4.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmTipDialog$initView$1
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
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function0<Unit> onBtn2Click = ConfirmTipDialog.this.getOnBtn2Click();
                    if (onBtn2Click != null) {
                        onBtn2Click.invoke();
                    }
                }
            }, 3, null));
        }
        Button button5 = this.btn_1;
        if (button5 != null) {
            button5.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ConfirmTipDialog$initView$2
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
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Function0<Unit> onBtn1Click = ConfirmTipDialog.this.getOnBtn1Click();
                    if (onBtn1Click != null) {
                        onBtn1Click.invoke();
                    }
                }
            }, 3, null));
        }
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

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        stopAutoHide();
    }

    private final void stopAutoHide() {
        this.handler.removeCallbacks(this.autoHideRunnable);
    }

    public final void autoHide(long time) {
        this.handler.postDelayed(this.autoHideRunnable, time);
    }

    public final void setBtn1Visibility(boolean gone) {
        if (gone) {
            Button button = this.btn_1;
            if (button != null) {
                ViewExtKt.gone(button);
                return;
            }
            return;
        }
        Button button2 = this.btn_1;
        if (button2 != null) {
            ViewExtKt.show(button2);
        }
    }

    public final void setBtn2Visibility(boolean gone) {
        if (gone) {
            Button button = this.btn_2;
            if (button != null) {
                ViewExtKt.gone(button);
                return;
            }
            return;
        }
        Button button2 = this.btn_2;
        if (button2 != null) {
            ViewExtKt.show(button2);
        }
    }
}
