package com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseRouteEditDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010%\u001a\u00020\u0013H\u0002J\b\u0010&\u001a\u00020\u0013H\u0016J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010(\u001a\u00020\u0013J\b\u0010)\u001a\u00020\u0013H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R7\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n $*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/CruiseRouteEditDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "_context", "etCruiseRoute", "Landroid/widget/EditText;", "oldName", "", "getOldName", "()Ljava/lang/String;", "setOldName", "(Ljava/lang/String;)V", "onCancel", "Lkotlin/Function0;", "", "getOnCancel", "()Lkotlin/jvm/functions/Function0;", "setOnCancel", "(Lkotlin/jvm/functions/Function0;)V", "onConfirm", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "text", "getOnConfirm", "()Lkotlin/jvm/functions/Function1;", "setOnConfirm", "(Lkotlin/jvm/functions/Function1;)V", "runnable", "Ljava/lang/Runnable;", AIUIConstant.KEY_TAG, "kotlin.jvm.PlatformType", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "init", "reset", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseRouteEditDialog extends Dialog {
    private Context _context;
    private EditText etCruiseRoute;
    private String oldName;
    private Function0<Unit> onCancel;
    private Function1<? super String, Unit> onConfirm;
    private final Runnable runnable;
    private final String tag;

    public final Function0<Unit> getOnCancel() {
        return this.onCancel;
    }

    public final void setOnCancel(Function0<Unit> function0) {
        this.onCancel = function0;
    }

    public final Function1<String, Unit> getOnConfirm() {
        return this.onConfirm;
    }

    public final void setOnConfirm(Function1<? super String, Unit> function1) {
        this.onConfirm = function1;
    }

    public final String getOldName() {
        return this.oldName;
    }

    public final void setOldName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.oldName = str;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseRouteEditDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.tag = getClass().getSimpleName();
        this.oldName = "";
        this.runnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.CruiseRouteEditDialog$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0<Unit> onCancel = CruiseRouteEditDialog.this.getOnCancel();
                if (onCancel != null) {
                    onCancel.invoke();
                }
                CruiseRouteEditDialog.this.dismiss();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseRouteEditDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.tag = getClass().getSimpleName();
        this.oldName = "";
        this.runnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.CruiseRouteEditDialog$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0<Unit> onCancel = CruiseRouteEditDialog.this.getOnCancel();
                if (onCancel != null) {
                    onCancel.invoke();
                }
                CruiseRouteEditDialog.this.dismiss();
            }
        };
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_cruise_route_edit, (ViewGroup) null);
        requestWindowFeature(1);
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(48);
            window.setAttributes(attributes);
            Button button = inflate != null ? (Button) inflate.findViewById(C4188R.id.validation_sure) : null;
            View findViewById = inflate != null ? inflate.findViewById(C4188R.id.validation_cancel) : null;
            this.etCruiseRoute = inflate != null ? (EditText) inflate.findViewById(C4188R.id.etCruiseRoute) : null;
            EditText editText = this.etCruiseRoute;
            if (editText != null) {
                editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.CruiseRouteEditDialog$build$$inlined$let$lambda$1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String str;
                        str = CruiseRouteEditDialog.this.tag;
                        Pdlog.m3273d(str, "onTextChanged");
                    }
                });
            }
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.CruiseRouteEditDialog$build$$inlined$let$lambda$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CruiseRouteEditDialog.this.dismiss();
                        Function0<Unit> onCancel = CruiseRouteEditDialog.this.getOnCancel();
                        if (onCancel != null) {
                            onCancel.invoke();
                        }
                    }
                });
            }
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.CruiseRouteEditDialog$build$$inlined$let$lambda$3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EditText editText2;
                        Function1<String, Unit> onConfirm;
                        editText2 = CruiseRouteEditDialog.this.etCruiseRoute;
                        String valueOf = String.valueOf(editText2 != null ? editText2.getText() : null);
                        if ((valueOf.length() > 0) && (onConfirm = CruiseRouteEditDialog.this.getOnConfirm()) != null) {
                            onConfirm.invoke(valueOf);
                        }
                        CruiseRouteEditDialog.this.dismiss();
                    }
                });
            }
            setContentView(inflate);
        }
        setCancelable(false);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        EditText editText = this.etCruiseRoute;
        if (editText != null) {
            KeyboardUtils.hideSoftInput(editText);
            editText.removeCallbacks(this.runnable);
        }
        reset();
        super.dismiss();
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
        EditText editText = this.etCruiseRoute;
        if (editText != null) {
            editText.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.CruiseRouteEditDialog$show$1
                @Override // java.lang.Runnable
                public final void run() {
                    EditText editText2;
                    editText2 = CruiseRouteEditDialog.this.etCruiseRoute;
                    if (editText2 != null) {
                        KeyboardUtils.showSoftInput(editText2);
                    }
                }
            });
        }
        if (this.oldName.length() > 0) {
            EditText editText2 = this.etCruiseRoute;
            if (editText2 != null) {
                editText2.setText(this.oldName);
            }
            EditText editText3 = this.etCruiseRoute;
            if (editText3 != null) {
                editText3.setSelection(this.oldName.length());
            }
        }
    }

    public final void reset() {
        EditText editText = this.etCruiseRoute;
        if (editText != null) {
            editText.setText("");
        }
        this.oldName = "";
    }
}
