package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
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
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: InputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010#\u001a\u00020\u0019H\u0002J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010%\u001a\u00020\u0019H\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R7\u0010\u0014\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0011\"\u0004\b \u0010\u0013R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/InputDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "btnButton", "Landroid/widget/Button;", ES6Iterator.VALUE_PROPERTY, "btnText", "getBtnText", "()Ljava/lang/String;", "setBtnText", "(Ljava/lang/String;)V", "onInputResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnInputResult", "()Lkotlin/jvm/functions/Function1;", "setOnInputResult", "(Lkotlin/jvm/functions/Function1;)V", "title", "getTitle", "setTitle", "titleTv", "Landroid/widget/TextView;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "init", "show", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class InputDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private Button btnButton;
    private String btnText;
    private Function1<? super String, Unit> onInputResult;
    private String title;
    private TextView titleTv;

    public final Function1<String, Unit> getOnInputResult() {
        return this.onInputResult;
    }

    public final void setOnInputResult(Function1<? super String, Unit> function1) {
        this.onInputResult = function1;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.title = value;
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setText(value);
        }
    }

    public final String getBtnText() {
        return this.btnText;
    }

    public final void setBtnText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.btnText = value;
        Button button = this.btnButton;
        if (button != null) {
            button.setText(value);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.btnText = "";
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.btnText = "";
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        Button button;
        View findViewById;
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_input, (ViewGroup) null);
        requestWindowFeature(1);
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            final EditText editText = inflate != null ? (EditText) inflate.findViewById(C4188R.id.input_string) : null;
            this.titleTv = inflate != null ? (TextView) inflate.findViewById(C4188R.id.title_tv) : null;
            this.btnButton = inflate != null ? (Button) inflate.findViewById(C4188R.id.input_done) : null;
            if (inflate != null && (findViewById = inflate.findViewById(C4188R.id.cancel)) != null) {
                findViewById.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.InputDialog$build$$inlined$let$lambda$1
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
                        InputDialog.this.dismiss();
                    }
                }, 3, null));
            }
            if (editText != null) {
                KeyboardUtils.showSoftInput(editText);
                ViewExtKt.disableCopyAndPaste(editText);
            }
            if (inflate != null && (button = (Button) inflate.findViewById(C4188R.id.input_done)) != null) {
                button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.InputDialog$build$$inlined$let$lambda$2
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
                        String str;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        EditText editText2 = editText;
                        String valueOf = String.valueOf(editText2 != null ? editText2.getText() : null);
                        str = this.TAG;
                        Pdlog.m3273d(str, "input " + valueOf);
                        Function1<String, Unit> onInputResult = this.getOnInputResult();
                        if (onInputResult != null) {
                            onInputResult.invoke(valueOf);
                        }
                    }
                }, 3, null));
            }
            setContentView(inflate);
            setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.InputDialog$build$1$4
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    EditText editText2 = editText;
                    if (editText2 != null) {
                        KeyboardUtils.hideSoftInput(editText2);
                    }
                }
            });
        }
        setCancelable(false);
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
}
