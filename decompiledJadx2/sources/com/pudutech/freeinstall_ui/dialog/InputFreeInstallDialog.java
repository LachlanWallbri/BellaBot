package com.pudutech.freeinstall_ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
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
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.freeinstall_ui.utils.KeyboardUtils;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.simpleframework.xml.strategy.Name;

/* compiled from: InputFreeInstallDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010%\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u001bH\u0016J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010(\u001a\u00020\u001bH\u0002J\u0010\u0010)\u001a\u00020\u001b2\b\b\u0002\u0010*\u001a\u00020\u0006J\b\u0010+\u001a\u00020\u001bH\u0016J\u000e\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tR\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0011\"\u0004\b\"\u0010\u0013R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/InputFreeInstallDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "_context", "btnButton", "Landroid/widget/Button;", ES6Iterator.VALUE_PROPERTY, "btnText", "getBtnText", "()Ljava/lang/String;", "setBtnText", "(Ljava/lang/String;)V", "input", "Landroid/widget/EditText;", "onInputResult", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnInputResult", "()Lkotlin/jvm/functions/Function1;", "setOnInputResult", "(Lkotlin/jvm/functions/Function1;)V", "title", "getTitle", "setTitle", "titleTv", "Landroid/widget/TextView;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismiss", "init", "initListener", "setMaxLength", Name.LENGTH, "show", "stringFilter", "edit", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class InputFreeInstallDialog extends Dialog {
    private final String TAG;
    private Context _context;
    private Button btnButton;
    private String btnText;
    private EditText input;
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
    public InputFreeInstallDialog(Context context) {
        super(context, C5362R.style.dialogstyle);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.title = "";
        this.btnText = "";
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputFreeInstallDialog(Context context, int i) {
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
        final View inflate = getLayoutInflater().inflate(C5362R.layout.dialog_input_free_install, (ViewGroup) null);
        requestWindowFeature(1);
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setGravity(48);
            window.setAttributes(attributes);
            this.input = inflate != null ? (EditText) inflate.findViewById(C5362R.id.input_string) : null;
            this.titleTv = inflate != null ? (TextView) inflate.findViewById(C5362R.id.title_tv) : null;
            this.btnButton = inflate != null ? (Button) inflate.findViewById(C5362R.id.input_done) : null;
            if (inflate != null && (findViewById = inflate.findViewById(C5362R.id.cancel)) != null) {
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog$build$$inlined$let$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        InputFreeInstallDialog.this.dismiss();
                    }
                });
            }
            EditText editText = this.input;
            if (inflate != null && (button = (Button) inflate.findViewById(C5362R.id.input_done)) != null) {
                button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog$build$$inlined$let$lambda$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EditText editText2;
                        String str;
                        KeyboardUtils.hideSoftInput(view);
                        editText2 = InputFreeInstallDialog.this.input;
                        String valueOf = String.valueOf(editText2 != null ? editText2.getText() : null);
                        if (valueOf != null) {
                            String obj = StringsKt.trim((CharSequence) valueOf).toString();
                            str = InputFreeInstallDialog.this.TAG;
                            Pdlog.m3273d(str, "input " + obj);
                            Function1<String, Unit> onInputResult = InputFreeInstallDialog.this.getOnInputResult();
                            if (onInputResult != null) {
                                onInputResult.invoke(obj);
                                return;
                            }
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                });
            }
            setContentView(inflate);
            setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog$build$$inlined$let$lambda$3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    EditText editText2;
                    editText2 = InputFreeInstallDialog.this.input;
                    if (editText2 != null) {
                        KeyboardUtils.hideSoftInput(editText2);
                    }
                }
            });
            initListener();
        }
        setCancelable(false);
    }

    private final void initListener() {
        EditText editText = this.input;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.freeinstall_ui.dialog.InputFreeInstallDialog$initListener$1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
                
                    r1 = r0.this$0.input;
                 */
                @Override // android.text.TextWatcher
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    EditText editText2;
                    EditText editText3;
                    EditText editText4;
                    editText2 = InputFreeInstallDialog.this.input;
                    String valueOf = String.valueOf(editText2 != null ? editText2.getText() : null);
                    String stringFilter = InputFreeInstallDialog.this.stringFilter(valueOf);
                    String str = stringFilter;
                    if (!TextUtils.equals(str, valueOf) && editText4 != null) {
                        editText4.setText(str);
                    }
                    editText3 = InputFreeInstallDialog.this.input;
                    if (editText3 != null) {
                        editText3.setSelection(stringFilter.length());
                    }
                }
            });
        }
    }

    public final String stringFilter(String edit) {
        Intrinsics.checkParameterIsNotNull(edit, "edit");
        String replaceAll = Pattern.compile("[^a-zA-Z0-9一-龥]").matcher(edit).replaceAll("");
        Intrinsics.checkExpressionValueIsNotNull(replaceAll, "matcher.replaceAll(\"\")");
        if (replaceAll != null) {
            return StringsKt.trim((CharSequence) replaceAll).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        EditText editText = this.input;
        if (editText != null) {
            KeyboardUtils.hideSoftInput(editText);
        }
        super.dismiss();
    }

    public static /* synthetic */ void setMaxLength$default(InputFreeInstallDialog inputFreeInstallDialog, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        inputFreeInstallDialog.setMaxLength(i);
    }

    public final void setMaxLength(int length) {
        EditText editText = this.input;
        if (editText != null) {
            editText.setFilters(new InputFilter[]{new MaxTextTwoLengthFilter(this._context, length)});
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
        EditText editText = this.input;
        if (editText != null) {
            KeyboardUtils.showSoftInput(editText);
        }
    }
}
