package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.widget.PasswordInput;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PasswordDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\u0012\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0016H\u0016R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/PasswordDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/AutoCloseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "TIME_OUT", "", "close", "Landroid/view/View;", "confirm", "input", "Lcom/pudutech/bumblebee/robot_ui/widget/PasswordInput;", "onDoneListener", "Lkotlin/Function0;", "", "getOnDoneListener", "()Lkotlin/jvm/functions/Function0;", "setOnDoneListener", "(Lkotlin/jvm/functions/Function0;)V", "dismiss", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "reset", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PasswordDialog extends AutoCloseDialog {
    private final String TAG;
    private final long TIME_OUT;
    private final View close;
    private final View confirm;
    private final PasswordInput input;
    private Function0<Unit> onDoneListener;

    public final String getTAG() {
        return this.TAG;
    }

    public final Function0<Unit> getOnDoneListener() {
        return this.onDoneListener;
    }

    public final void setOnDoneListener(Function0<Unit> function0) {
        this.onDoneListener = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.TIME_OUT = 10000L;
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_password, (ViewGroup) null);
        View findViewById = inflate.findViewById(C4188R.id.close);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "layout.findViewById(R.id.close)");
        this.close = findViewById;
        View findViewById2 = inflate.findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "layout.findViewById(R.id.confirm)");
        this.confirm = findViewById2;
        View findViewById3 = inflate.findViewById(C4188R.id.input);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "layout.findViewById(R.id.input)");
        this.input = (PasswordInput) findViewById3;
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(attributes);
            this.input.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$$special$$inlined$let$lambda$1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    PasswordDialog.this.resetAutoClose();
                    Pdlog.m3273d(PasswordDialog.this.getTAG(), "twiceInput doOnTextChanged");
                }
            });
            ViewExtKt.disableCopyAndPaste(this.input.getEditText());
            this.close.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$$special$$inlined$let$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PasswordDialog.this.dismiss();
                }
            });
            this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$$special$$inlined$let$lambda$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PasswordInput passwordInput;
                    passwordInput = PasswordDialog.this.input;
                    if (Intrinsics.areEqual(passwordInput.getEditText().getText().toString(), Constans.INSTANCE.getSettingPassword())) {
                        Function0<Unit> onDoneListener = PasswordDialog.this.getOnDoneListener();
                        if (onDoneListener != null) {
                            onDoneListener.invoke();
                        }
                        PasswordDialog.this.dismiss();
                        return;
                    }
                    ToastUtils.show(PasswordDialog.this.getContext(), C4188R.string.pdStr7_42);
                }
            });
            setContentView(inflate);
        }
        setCancelable(false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PasswordDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.TIME_OUT = 10000L;
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_password, (ViewGroup) null);
        View findViewById = inflate.findViewById(C4188R.id.close);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "layout.findViewById(R.id.close)");
        this.close = findViewById;
        View findViewById2 = inflate.findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "layout.findViewById(R.id.confirm)");
        this.confirm = findViewById2;
        View findViewById3 = inflate.findViewById(C4188R.id.input);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "layout.findViewById(R.id.input)");
        this.input = (PasswordInput) findViewById3;
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(attributes);
            this.input.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$$special$$inlined$let$lambda$4
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                    PasswordDialog.this.resetAutoClose();
                    Pdlog.m3273d(PasswordDialog.this.getTAG(), "twiceInput doOnTextChanged");
                }
            });
            ViewExtKt.disableCopyAndPaste(this.input.getEditText());
            this.close.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$$special$$inlined$let$lambda$5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PasswordDialog.this.dismiss();
                }
            });
            this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$$special$$inlined$let$lambda$6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PasswordInput passwordInput;
                    passwordInput = PasswordDialog.this.input;
                    if (Intrinsics.areEqual(passwordInput.getEditText().getText().toString(), Constans.INSTANCE.getSettingPassword())) {
                        Function0<Unit> onDoneListener = PasswordDialog.this.getOnDoneListener();
                        if (onDoneListener != null) {
                            onDoneListener.invoke();
                        }
                        PasswordDialog.this.dismiss();
                        return;
                    }
                    ToastUtils.show(PasswordDialog.this.getContext(), C4188R.string.pdStr7_42);
                }
            });
            setContentView(inflate);
        }
        setCancelable(false);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            window.setSoftInputMode(16);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        KeyboardUtils.hideSoftInput(this.input);
        super.dismiss();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.AutoCloseDialog, android.app.Dialog
    public void show() {
        super.show();
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-2, -2);
        }
        this.input.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.PasswordDialog$show$1
            @Override // java.lang.Runnable
            public final void run() {
                PasswordInput passwordInput;
                passwordInput = PasswordDialog.this.input;
                KeyboardUtils.showSoftInput(passwordInput.getEditText());
            }
        });
        reset();
    }

    private final void reset() {
        this.input.getEditText().setText("");
    }
}
