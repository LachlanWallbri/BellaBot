package com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.util.KeyboardUtils;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CheckPermissionDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010)\u001a\u00020\u0018H\u0002J\b\u0010*\u001a\u00020\u0018H\u0016J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010,\u001a\u00020\u0018H\u0014J\b\u0010-\u001a\u00020\u0018H\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR(\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR*\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\t0!j\b\u0012\u0004\u0012\u00020\t`\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/CheckPermissionDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "editView", "Landroid/widget/EditText;", "errorTextView", "Landroid/widget/TextView;", "handler", "Landroid/os/Handler;", "onPermissionCheckResult", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "b", "", "getOnPermissionCheckResult", "()Lkotlin/jvm/functions/Function1;", "setOnPermissionCheckResult", "(Lkotlin/jvm/functions/Function1;)V", "onPswResult", "getOnPswResult", "setOnPswResult", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getPassword", "()Ljava/util/ArrayList;", "setPassword", "(Ljava/util/ArrayList;)V", "view_layout", "Landroid/view/View;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "init", "onStart", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CheckPermissionDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private EditText editView;
    private TextView errorTextView;
    private final Handler handler;
    private Function1<? super Boolean, Unit> onPermissionCheckResult;
    private Function1<? super String, Unit> onPswResult;
    private ArrayList<String> password;
    private View view_layout;

    public final Function1<Boolean, Unit> getOnPermissionCheckResult() {
        return this.onPermissionCheckResult;
    }

    public final void setOnPermissionCheckResult(Function1<? super Boolean, Unit> function1) {
        this.onPermissionCheckResult = function1;
    }

    public final Function1<String, Unit> getOnPswResult() {
        return this.onPswResult;
    }

    public final void setOnPswResult(Function1<? super String, Unit> function1) {
        this.onPswResult = function1;
    }

    public final ArrayList<String> getPassword() {
        return this.password;
    }

    public final void setPassword(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.password = arrayList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckPermissionDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.password = CollectionsKt.arrayListOf("pudu666", "pudupw");
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.CheckPermissionDialog$handler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                TextView textView;
                textView = CheckPermissionDialog.this.errorTextView;
                if (textView == null) {
                    return true;
                }
                textView.setVisibility(8);
                return true;
            }
        });
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CheckPermissionDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.password = CollectionsKt.arrayListOf("pudu666", "pudupw");
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.CheckPermissionDialog$handler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                TextView textView;
                textView = CheckPermissionDialog.this.errorTextView;
                if (textView == null) {
                    return true;
                }
                textView.setVisibility(8);
                return true;
            }
        });
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "this.window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "this.window.decorView");
        decorView.setSystemUiVisibility(2822);
    }

    private final void build() {
        Button button;
        View findViewById;
        LayoutInflater layoutInflater = getLayoutInflater();
        if (this.view_layout == null) {
            this.view_layout = layoutInflater.inflate(C5508R.layout.fragment_debug_setup_pwd_dialog, (ViewGroup) null);
        }
        requestWindowFeature(1);
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(48);
            window.setAttributes(attributes);
            View view = this.view_layout;
            this.errorTextView = view != null ? (TextView) view.findViewById(C5508R.id.tv_tips) : null;
            View view2 = this.view_layout;
            this.editView = view2 != null ? (EditText) view2.findViewById(C5508R.id.input_pwd) : null;
            View view3 = this.view_layout;
            if (view3 != null && (findViewById = view3.findViewById(C5508R.id.cancel)) != null) {
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.CheckPermissionDialog$build$$inlined$let$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view4) {
                        TextView textView;
                        View view5;
                        EditText editText;
                        textView = CheckPermissionDialog.this.errorTextView;
                        if (textView != null) {
                            textView.setVisibility(8);
                        }
                        view5 = CheckPermissionDialog.this.view_layout;
                        if (view5 != null && (editText = (EditText) view5.findViewById(C5508R.id.input_pwd)) != null) {
                            editText.setText("");
                        }
                        CheckPermissionDialog.this.dismiss();
                    }
                });
            }
            View view4 = this.view_layout;
            if (view4 != null && (button = (Button) view4.findViewById(C5508R.id.input_done)) != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.CheckPermissionDialog$build$$inlined$let$lambda$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view5) {
                        EditText editText;
                        String str;
                        TextView textView;
                        Handler handler;
                        TextView textView2;
                        Handler handler2;
                        editText = CheckPermissionDialog.this.editView;
                        String valueOf = String.valueOf(editText != null ? editText.getText() : null);
                        str = CheckPermissionDialog.this.TAG;
                        Pdlog.m3273d(str, "pwd = " + valueOf);
                        Function1<String, Unit> onPswResult = CheckPermissionDialog.this.getOnPswResult();
                        if (onPswResult != null) {
                            onPswResult.invoke(valueOf);
                        }
                        if (CheckPermissionDialog.this.getPassword().contains(valueOf)) {
                            if (CheckPermissionDialog.this.getOnPermissionCheckResult() != null) {
                                textView2 = CheckPermissionDialog.this.errorTextView;
                                if (textView2 != null) {
                                    textView2.setVisibility(8);
                                }
                                handler2 = CheckPermissionDialog.this.handler;
                                handler2.sendEmptyMessageDelayed(1, 10L);
                                Function1<Boolean, Unit> onPermissionCheckResult = CheckPermissionDialog.this.getOnPermissionCheckResult();
                                if (onPermissionCheckResult == null) {
                                    Intrinsics.throwNpe();
                                }
                                onPermissionCheckResult.invoke(true);
                                return;
                            }
                            return;
                        }
                        if (CheckPermissionDialog.this.getOnPermissionCheckResult() != null) {
                            textView = CheckPermissionDialog.this.errorTextView;
                            if (textView != null) {
                                textView.setVisibility(0);
                            }
                            handler = CheckPermissionDialog.this.handler;
                            handler.sendEmptyMessageDelayed(1, 2000L);
                            Function1<Boolean, Unit> onPermissionCheckResult2 = CheckPermissionDialog.this.getOnPermissionCheckResult();
                            if (onPermissionCheckResult2 == null) {
                                Intrinsics.throwNpe();
                            }
                            onPermissionCheckResult2.invoke(false);
                        }
                    }
                });
            }
            setContentView(this.view_layout);
            setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.CheckPermissionDialog$build$$inlined$let$lambda$3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    String str;
                    EditText editText;
                    try {
                        editText = CheckPermissionDialog.this.editView;
                        if (editText != null) {
                            KeyboardUtils.hideSoftInput(editText);
                        }
                    } catch (Exception e) {
                        str = CheckPermissionDialog.this.TAG;
                        Pdlog.m3274e(str, "build : " + Log.getStackTraceString(e));
                    }
                }
            });
        }
        setCancelable(false);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        EditText editText = this.editView;
        if (editText != null) {
            KeyboardUtils.hideSoftInput(editText);
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
            editText.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.CheckPermissionDialog$show$1
                @Override // java.lang.Runnable
                public final void run() {
                    EditText editText2;
                    editText2 = CheckPermissionDialog.this.editView;
                    if (editText2 != null) {
                        KeyboardUtils.showSoftInput(editText2);
                    }
                }
            });
        }
    }
}
