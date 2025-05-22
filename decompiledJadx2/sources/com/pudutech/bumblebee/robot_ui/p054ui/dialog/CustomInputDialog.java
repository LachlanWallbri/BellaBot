package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CustomInputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010*\u001a\u00020%H\u0016J\b\u0010+\u001a\u00020\u0006H\u0016J\u0010\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0014J\b\u0010/\u001a\u000200H\u0014J\u0010\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020\u0006H\u0002R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0018\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR7\u0010 \u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomInputDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "btnButton", "Landroid/widget/Button;", ES6Iterator.VALUE_PROPERTY, "btnText", "getBtnText", "()Ljava/lang/String;", "setBtnText", "(Ljava/lang/String;)V", AIUIConstant.KEY_CONTENT, "getContent", "setContent", "contentEt", "Landroid/widget/EditText;", "contentHint", "getContentHint", "setContentHint", "inputSize", "getInputSize", "()I", "setInputSize", "(I)V", "onInputResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnInputResult", "()Lkotlin/jvm/functions/Function1;", "setOnInputResult", "(Lkotlin/jvm/functions/Function1;)V", "dismiss", "getLayoutId", "initView", "view", "Landroid/view/View;", "isOpenReset", "", "setCount", "contentLength", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomInputDialog extends BumbleBaseDialog {
    private final String TAG;
    private Button btnButton;
    private String btnText;
    private String content;
    private EditText contentEt;
    private String contentHint;
    private int inputSize;
    private Function1<? super String, Unit> onInputResult;

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    protected boolean isOpenReset() {
        return true;
    }

    public final Function1<String, Unit> getOnInputResult() {
        return this.onInputResult;
    }

    public final void setOnInputResult(Function1<? super String, Unit> function1) {
        this.onInputResult = function1;
    }

    public final String getContentHint() {
        return this.contentHint;
    }

    public final void setContentHint(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.contentHint = value;
        EditText editText = this.contentEt;
        if (editText != null) {
            editText.setHint(value);
        }
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.content = value;
        EditText editText = this.contentEt;
        if (editText != null) {
            editText.setText(value);
        }
        EditText editText2 = this.contentEt;
        if (editText2 != null) {
            editText2.setSelection(value.length());
        }
        setCount(this.content.length());
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

    public final int getInputSize() {
        return this.inputSize;
    }

    public final void setInputSize(int i) {
        this.inputSize = i;
        EditText editText = this.contentEt;
        if (editText != null) {
            editText.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(i)});
        }
        setCount(this.content.length());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomInputDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.contentHint = "";
        this.content = "";
        this.btnText = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomInputDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.contentHint = "";
        this.content = "";
        this.btnText = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        this.contentEt = (EditText) view.findViewById(C4188R.id.etCustomVoice);
        this.btnButton = (Button) view.findViewById(C4188R.id.btnConfirm);
        View findViewById = view.findViewById(C4188R.id.ivClose);
        if (findViewById != null) {
            findViewById.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomInputDialog$initView$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    CustomInputDialog.this.dismiss();
                }
            }, 3, null));
        }
        EditText editText = this.contentEt;
        if (editText != null) {
            KeyboardUtils.showSoftInput(editText);
            ViewExtKt.disableCopyAndPaste(editText);
            editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomInputDialog$initView$$inlined$let$lambda$1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    String obj;
                    if (s == null || (obj = s.toString()) == null) {
                        return;
                    }
                    CustomInputDialog.this.setCount(obj.length());
                }
            });
        }
        Button button = this.btnButton;
        if (button != null) {
            button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomInputDialog$initView$3
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    EditText editText2;
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    editText2 = CustomInputDialog.this.contentEt;
                    String valueOf = String.valueOf(editText2 != null ? editText2.getText() : null);
                    str = CustomInputDialog.this.TAG;
                    Pdlog.m3273d(str, "input " + valueOf);
                    Function1<String, Unit> onInputResult = CustomInputDialog.this.getOnInputResult();
                    if (onInputResult != null) {
                        onInputResult.invoke(valueOf);
                    }
                }
            }, 3, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCount(int contentLength) {
        TextView tvCount = (TextView) findViewById(C4188R.id.tvCount);
        Intrinsics.checkExpressionValueIsNotNull(tvCount, "tvCount");
        StringBuilder sb = new StringBuilder();
        sb.append(contentLength);
        sb.append('/');
        sb.append(this.inputSize);
        tvCount.setText(sb.toString());
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_custom_input;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        EditText editText = this.contentEt;
        if (editText != null) {
            KeyboardUtils.hideSoftInput(editText);
        }
        super.dismiss();
    }
}
