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
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.dialog.ShowConfrimDialog;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShowConfrimDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u001cB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0010\u0010\u0016\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0010\u0010\u0017\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u0015J\u0010\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\fJ\b\u0010\u001b\u001a\u00020\u0011H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/ShowConfrimDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "mBtnCancel", "Landroid/widget/Button;", "mBtnSure", "mCallback", "Lcom/pudutech/peanut/robot_ui/ui/dialog/ShowConfrimDialog$Callback;", "mContentTV", "Landroid/widget/TextView;", "mContext", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "init", "setBtnCancel", "str", "", "setBtnSure", "setContent", AIUIConstant.KEY_CONTENT, "setListener", "callback", "show", "Callback", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ShowConfrimDialog extends Dialog {
    private Button mBtnCancel;
    private Button mBtnSure;
    private Callback mCallback;
    private TextView mContentTV;
    private Context mContext;

    /* compiled from: ShowConfrimDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/ShowConfrimDialog$Callback;", "", "onCancel", "", "dialog", "Landroid/app/Dialog;", "onSure", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface Callback {
        void onCancel(Dialog dialog);

        void onSure(Dialog dialog);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowConfrimDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowConfrimDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.layout_confrim_dialog, (ViewGroup) null);
        this.mContentTV = (TextView) inflate.findViewById(C5508R.id.show_tips);
        this.mBtnSure = (Button) inflate.findViewById(C5508R.id.btn_sure);
        this.mBtnCancel = (Button) inflate.findViewById(C5508R.id.btn_cancel);
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "window.decorView");
        decorView.setSystemUiVisibility(3846);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        inflate.findViewById(C5508R.id.btn_cancel).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.ShowConfrimDialog$build$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                ShowConfrimDialog.Callback callback;
                ShowConfrimDialog.Callback callback2;
                callback = ShowConfrimDialog.this.mCallback;
                if (callback != null) {
                    callback2 = ShowConfrimDialog.this.mCallback;
                    if (callback2 == null) {
                        Intrinsics.throwNpe();
                    }
                    callback2.onCancel(ShowConfrimDialog.this);
                }
            }
        });
        inflate.findViewById(C5508R.id.btn_sure).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.ShowConfrimDialog$build$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                ShowConfrimDialog.Callback callback;
                ShowConfrimDialog.Callback callback2;
                callback = ShowConfrimDialog.this.mCallback;
                if (callback != null) {
                    callback2 = ShowConfrimDialog.this.mCallback;
                    if (callback2 == null) {
                        Intrinsics.throwNpe();
                    }
                    callback2.onSure(ShowConfrimDialog.this);
                }
            }
        });
        setContentView(inflate);
        window.setLayout(-1, -1);
        setCanceledOnTouchOutside(true);
    }

    public final void setListener(Callback callback) {
        this.mCallback = callback;
    }

    public final void setContent(String content) {
        TextView textView = this.mContentTV;
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setText(content);
    }

    public final void setBtnCancel(String str) {
        Button button = this.mBtnCancel;
        if (button == null) {
            Intrinsics.throwNpe();
        }
        button.setText(str);
    }

    public final void setBtnSure(String str) {
        Button button = this.mBtnSure;
        if (button == null) {
            Intrinsics.throwNpe();
        }
        button.setText(str);
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
    }
}
