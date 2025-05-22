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
import android.widget.TextView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ValidationDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u00102\u001a\u00020\u0019H\u0002J\b\u00103\u001a\u00020\u0019H\u0016J\u0010\u00104\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u00105\u001a\u00020\u0019J\b\u00106\u001a\u00020\u0019H\u0002J\b\u00107\u001a\u00020\u0019H\u0016R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR*\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\t0\u001fj\b\u0012\u0004\u0012\u00020\t` X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R7\u0010%\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020\u0019\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "TIME_OUT", "", "_context", "editView", "Landroid/widget/EditText;", "isCheckResultClose", "", "()Z", "setCheckResultClose", "(Z)V", "mClickCall", "Lkotlin/Function0;", "", "getMClickCall", "()Lkotlin/jvm/functions/Function0;", "setMClickCall", "(Lkotlin/jvm/functions/Function0;)V", "mPasswordConfig", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMPasswordConfig", "()Ljava/util/ArrayList;", "setMPasswordConfig", "(Ljava/util/ArrayList;)V", "onPermissionCheckResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "getOnPermissionCheckResult", "()Lkotlin/jvm/functions/Function1;", "setOnPermissionCheckResult", "(Lkotlin/jvm/functions/Function1;)V", "runnable", "Ljava/lang/Runnable;", "tv_tips", "Landroid/widget/TextView;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "init", "reset", "setAutoClose", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ValidationDialog extends Dialog {
    private final String TAG;
    private final long TIME_OUT;
    private Context _context;
    private EditText editView;
    private boolean isCheckResultClose;
    private Function0<Unit> mClickCall;
    private ArrayList<String> mPasswordConfig;
    private Function1<? super Boolean, Unit> onPermissionCheckResult;
    private final Runnable runnable;
    private TextView tv_tips;

    public final String getTAG() {
        return this.TAG;
    }

    public final Function0<Unit> getMClickCall() {
        return this.mClickCall;
    }

    public final void setMClickCall(Function0<Unit> function0) {
        this.mClickCall = function0;
    }

    public final Function1<Boolean, Unit> getOnPermissionCheckResult() {
        return this.onPermissionCheckResult;
    }

    public final void setOnPermissionCheckResult(Function1<? super Boolean, Unit> function1) {
        this.onPermissionCheckResult = function1;
    }

    public final ArrayList<String> getMPasswordConfig() {
        return this.mPasswordConfig;
    }

    public final void setMPasswordConfig(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.mPasswordConfig = arrayList;
    }

    /* renamed from: isCheckResultClose, reason: from getter */
    public final boolean getIsCheckResultClose() {
        return this.isCheckResultClose;
    }

    public final void setCheckResultClose(boolean z) {
        this.isCheckResultClose = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ValidationDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.mPasswordConfig = CollectionsKt.arrayListOf("pudu666", "pudupw");
        this.isCheckResultClose = true;
        this.TIME_OUT = 10000L;
        this.runnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ValidationDialog$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0<Unit> mClickCall = ValidationDialog.this.getMClickCall();
                if (mClickCall != null) {
                    mClickCall.invoke();
                }
                ValidationDialog.this.dismiss();
            }
        };
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ValidationDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.mPasswordConfig = CollectionsKt.arrayListOf("pudu666", "pudupw");
        this.isCheckResultClose = true;
        this.TIME_OUT = 10000L;
        this.runnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ValidationDialog$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0<Unit> mClickCall = ValidationDialog.this.getMClickCall();
                if (mClickCall != null) {
                    mClickCall.invoke();
                }
                ValidationDialog.this.dismiss();
            }
        };
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        Button button;
        View findViewById;
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_validation, (ViewGroup) null);
        this.tv_tips = (TextView) inflate.findViewById(C4188R.id.validation_title);
        requestWindowFeature(1);
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(48);
            window.setAttributes(attributes);
            this.editView = inflate != null ? (EditText) inflate.findViewById(C4188R.id.validation_psw) : null;
            EditText editText = this.editView;
            if (editText != null) {
                editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ValidationDialog$build$$inlined$let$lambda$1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        Pdlog.m3273d(ValidationDialog.this.getTAG(), "onTextChanged");
                        ValidationDialog.this.setAutoClose();
                    }
                });
            }
            if (inflate != null && (findViewById = inflate.findViewById(C4188R.id.validation_cancel)) != null) {
                findViewById.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ValidationDialog$build$$inlined$let$lambda$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        Function0<Unit> mClickCall = ValidationDialog.this.getMClickCall();
                        if (mClickCall != null) {
                            mClickCall.invoke();
                        }
                        ValidationDialog.this.dismiss();
                    }
                }, 3, null));
            }
            if (inflate != null && (button = (Button) inflate.findViewById(C4188R.id.validation_sure)) != null) {
                button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ValidationDialog$build$$inlined$let$lambda$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        EditText editText2;
                        Context context;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        editText2 = ValidationDialog.this.editView;
                        String valueOf = String.valueOf(editText2 != null ? editText2.getText() : null);
                        ValidationDialog.this.setAutoClose();
                        Pdlog.m3273d(ValidationDialog.this.getTAG(), "pwd = " + valueOf);
                        if (ValidationDialog.this.getMPasswordConfig().contains(valueOf)) {
                            Function1<Boolean, Unit> onPermissionCheckResult = ValidationDialog.this.getOnPermissionCheckResult();
                            if (onPermissionCheckResult != null) {
                                onPermissionCheckResult.invoke(true);
                                return;
                            }
                            return;
                        }
                        Function1<Boolean, Unit> onPermissionCheckResult2 = ValidationDialog.this.getOnPermissionCheckResult();
                        if (onPermissionCheckResult2 != null) {
                            onPermissionCheckResult2.invoke(false);
                        }
                        context = ValidationDialog.this._context;
                        ToastUtils.show(context, C4188R.string.pdStr16_126);
                    }
                }, 3, null));
            }
            setContentView(inflate);
        }
        setCancelable(false);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        EditText editText = this.editView;
        if (editText != null) {
            KeyboardUtils.hideSoftInput(editText);
            editText.removeCallbacks(this.runnable);
        }
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
        EditText editText = this.editView;
        if (editText != null) {
            editText.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ValidationDialog$show$1
                @Override // java.lang.Runnable
                public final void run() {
                    EditText editText2;
                    editText2 = ValidationDialog.this.editView;
                    if (editText2 != null) {
                        KeyboardUtils.showSoftInput(editText2);
                    }
                }
            });
        }
        reset();
        setAutoClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAutoClose() {
        EditText editText = this.editView;
        if (editText != null) {
            editText.removeCallbacks(this.runnable);
        }
        EditText editText2 = this.editView;
        if (editText2 != null) {
            editText2.postDelayed(this.runnable, this.TIME_OUT);
        }
    }

    public final void reset() {
        EditText editText = this.editView;
        if (editText != null) {
            editText.setText("");
        }
    }
}
