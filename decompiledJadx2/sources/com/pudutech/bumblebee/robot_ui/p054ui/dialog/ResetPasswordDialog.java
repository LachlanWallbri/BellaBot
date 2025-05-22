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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: ResetPasswordDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\u001a\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u001e\u001a\u00020\u0015H\u0002R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ResetPasswordDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/AutoCloseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "TIME_OUT", "", "close", "Landroid/view/View;", "confirm", "input", "Lcom/pudutech/bumblebee/robot_ui/widget/PasswordInput;", "checkLen", "", "dismiss", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "reset", "show", "validate", "showError", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ResetPasswordDialog extends AutoCloseDialog {
    private final String TAG;
    private final long TIME_OUT;
    private final View close;
    private final View confirm;
    private final PasswordInput input;

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResetPasswordDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.TIME_OUT = 10000L;
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_reset_password, (ViewGroup) null);
        View findViewById = inflate.findViewById(C4188R.id.close);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "layout.findViewById(R.id.close)");
        this.close = findViewById;
        View findViewById2 = inflate.findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "layout.findViewById(R.id.confirm)");
        this.confirm = findViewById2;
        View findViewById3 = inflate.findViewById(C4188R.id.input_new);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "layout.findViewById(R.id.input_new)");
        this.input = (PasswordInput) findViewById3;
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(attributes);
            this.input.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$$special$$inlined$let$lambda$1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                /* JADX WARN: Code restructure failed: missing block: B:11:0x0042, code lost:
                
                    if (r0 != false) goto L14;
                 */
                @Override // android.text.TextWatcher
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void afterTextChanged(Editable s) {
                    View view;
                    PasswordInput passwordInput;
                    boolean validate;
                    boolean checkLen;
                    View view2;
                    boolean z = true;
                    Pdlog.m3273d(ResetPasswordDialog.this.getTAG(), "firstInput after changed");
                    ResetPasswordDialog.this.resetAutoClose();
                    if ((s != null ? s.length() : 0) == 0) {
                        view2 = ResetPasswordDialog.this.confirm;
                        view2.setEnabled(false);
                        return;
                    }
                    view = ResetPasswordDialog.this.confirm;
                    ResetPasswordDialog resetPasswordDialog = ResetPasswordDialog.this;
                    passwordInput = resetPasswordDialog.input;
                    validate = resetPasswordDialog.validate(passwordInput, true);
                    if (validate) {
                        checkLen = ResetPasswordDialog.this.checkLen();
                    }
                    z = false;
                    view.setEnabled(z);
                }
            });
            ViewExtKt.disableCopyAndPaste(this.input.getEditText());
            this.close.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$$special$$inlined$let$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResetPasswordDialog.this.dismiss();
                }
            });
            this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$$special$$inlined$let$lambda$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PasswordInput passwordInput;
                    passwordInput = ResetPasswordDialog.this.input;
                    Constans.INSTANCE.setSettingPassword(passwordInput.getEditText().getText().toString());
                    ToastUtils.show(ResetPasswordDialog.this.getContext(), C4188R.string.tips_password_ok);
                    ResetPasswordDialog.this.dismiss();
                }
            });
            setContentView(inflate);
        }
        setCancelable(false);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResetPasswordDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.TIME_OUT = 10000L;
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_reset_password, (ViewGroup) null);
        View findViewById = inflate.findViewById(C4188R.id.close);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "layout.findViewById(R.id.close)");
        this.close = findViewById;
        View findViewById2 = inflate.findViewById(C4188R.id.confirm);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "layout.findViewById(R.id.confirm)");
        this.confirm = findViewById2;
        View findViewById3 = inflate.findViewById(C4188R.id.input_new);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "layout.findViewById(R.id.input_new)");
        this.input = (PasswordInput) findViewById3;
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(attributes);
            this.input.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$$special$$inlined$let$lambda$4
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                }

                /* JADX WARN: Code restructure failed: missing block: B:11:0x0042, code lost:
                
                    if (r0 != false) goto L14;
                 */
                @Override // android.text.TextWatcher
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void afterTextChanged(Editable s) {
                    View view;
                    PasswordInput passwordInput;
                    boolean validate;
                    boolean checkLen;
                    View view2;
                    boolean z = true;
                    Pdlog.m3273d(ResetPasswordDialog.this.getTAG(), "firstInput after changed");
                    ResetPasswordDialog.this.resetAutoClose();
                    if ((s != null ? s.length() : 0) == 0) {
                        view2 = ResetPasswordDialog.this.confirm;
                        view2.setEnabled(false);
                        return;
                    }
                    view = ResetPasswordDialog.this.confirm;
                    ResetPasswordDialog resetPasswordDialog = ResetPasswordDialog.this;
                    passwordInput = resetPasswordDialog.input;
                    validate = resetPasswordDialog.validate(passwordInput, true);
                    if (validate) {
                        checkLen = ResetPasswordDialog.this.checkLen();
                    }
                    z = false;
                    view.setEnabled(z);
                }
            });
            ViewExtKt.disableCopyAndPaste(this.input.getEditText());
            this.close.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$$special$$inlined$let$lambda$5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ResetPasswordDialog.this.dismiss();
                }
            });
            this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$$special$$inlined$let$lambda$6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PasswordInput passwordInput;
                    passwordInput = ResetPasswordDialog.this.input;
                    Constans.INSTANCE.setSettingPassword(passwordInput.getEditText().getText().toString());
                    ToastUtils.show(ResetPasswordDialog.this.getContext(), C4188R.string.tips_password_ok);
                    ResetPasswordDialog.this.dismiss();
                }
            });
            setContentView(inflate);
        }
        setCancelable(false);
    }

    static /* synthetic */ boolean validate$default(ResetPasswordDialog resetPasswordDialog, PasswordInput passwordInput, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return resetPasswordDialog.validate(passwordInput, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean validate(PasswordInput input, boolean showError) {
        boolean matches = new Regex("[0-9a-zA-Z]+").matches(input.getEditText().getText().toString());
        if (showError) {
            if (!matches) {
                Sdk27PropertiesKt.setBackgroundResource(input.getRoot(), C4188R.drawable.shape_password_input_bg_error);
                ToastUtils.show(getContext(), C4188R.string.tips_password_limit);
            } else {
                Sdk27PropertiesKt.setBackgroundResource(input.getRoot(), C4188R.drawable.shape_password_input_bg);
            }
        }
        return matches;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkLen() {
        int length = this.input.getEditText().getText().length();
        return 6 <= length && 8 >= length;
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
        this.input.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.ResetPasswordDialog$show$1
            @Override // java.lang.Runnable
            public final void run() {
                PasswordInput passwordInput;
                passwordInput = ResetPasswordDialog.this.input;
                KeyboardUtils.showSoftInput(passwordInput.getEditText());
            }
        });
        reset();
    }

    private final void reset() {
        this.input.getEditText().setText("");
    }
}
