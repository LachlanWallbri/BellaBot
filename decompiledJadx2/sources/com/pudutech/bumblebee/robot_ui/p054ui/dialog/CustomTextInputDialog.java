package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: CustomTextInputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0012\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J&\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u0016H\u0016J\b\u00100\u001a\u00020\u0016H\u0016J\u001a\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020$2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u00103\u001a\u00020\u00162\u0006\u00104\u001a\u00020\fH\u0002J\b\u00105\u001a\u00020\u0016H\u0002J\b\u00106\u001a\u00020.H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R7\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u00067"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomTextInputDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", AIUIConstant.KEY_CONTENT, "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "maxLength", "", "getMaxLength", "()I", "setMaxLength", "(I)V", "onContentChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnContentChange", "()Lkotlin/jvm/functions/Function1;", "setOnContentChange", "(Lkotlin/jvm/functions/Function1;)V", "dismiss", "dismissAllowingStateLoss", "dismissLoadDialog", "initView", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onMultiWindowModeChanged", "isInMultiWindowMode", "", "onResume", "onStart", "onViewCreated", "view", "setCount", "contentLength", "translucent", "wordLengthOverStep", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomTextInputDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    private Function1<? super String, Unit> onContentChange;
    private final String TAG = getClass().getSimpleName();
    private String content = "";
    private int maxLength = 10;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final Function1<String, Unit> getOnContentChange() {
        return this.onContentChange;
    }

    public final void setOnContentChange(Function1<? super String, Unit> function1) {
        this.onContentChange = function1;
    }

    public final int getMaxLength() {
        return this.maxLength;
    }

    public final void setMaxLength(int i) {
        this.maxLength = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Pdlog.m3273d(this.TAG, "onCreateView");
        return inflater.inflate(C4188R.layout.dialog_text_input, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Pdlog.m3273d(this.TAG, "onCreateDialog");
        setStyle(1, C4188R.style.AppThemeDialog);
        Dialog onCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog!!");
        Window window = dialog.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        View decorView = window.getDecorView();
        if (decorView == null) {
            Intrinsics.throwNpe();
        }
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTextInputDialog$onViewCreated$1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                CustomTextInputDialog.this.translucent();
            }
        });
        translucent();
        initView();
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView");
        setCancelable(false);
        setCount(this.content.length());
        final EditText editText = (EditText) _$_findCachedViewById(C4188R.id.etContent);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.maxLength)});
        editText.setText(this.content);
        editText.setSelection(this.content.length());
        editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTextInputDialog$initView$$inlined$apply$lambda$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
            
                if (r5 != null) goto L13;
             */
            @Override // android.text.TextWatcher
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void afterTextChanged(Editable s) {
                String str;
                boolean wordLengthOverStep;
                String obj;
                CustomTextInputDialog customTextInputDialog = this;
                if (s != null && (obj = s.toString()) != null) {
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    str = StringsKt.trim((CharSequence) obj).toString();
                }
                str = "";
                customTextInputDialog.setContent(str);
                CustomTextInputDialog customTextInputDialog2 = this;
                customTextInputDialog2.setCount(customTextInputDialog2.getContent().length());
                wordLengthOverStep = this.wordLengthOverStep();
                if (wordLengthOverStep) {
                    Context context = editText.getContext();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.getString(C4188R.string.pdStr7_76);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_76)");
                    Object[] objArr = {String.valueOf(this.getMaxLength())};
                    String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    ToastUtils.showXY(context, format, 48, 0, 0);
                }
            }
        });
        ViewExtKt.disableCopyAndPaste(editText);
        ((Button) _$_findCachedViewById(C4188R.id.btnTextConfirm)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTextInputDialog$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                String str2;
                Function1<String, Unit> onContentChange;
                try {
                    EditText etContent = (EditText) CustomTextInputDialog.this._$_findCachedViewById(C4188R.id.etContent);
                    Intrinsics.checkExpressionValueIsNotNull(etContent, "etContent");
                    String obj = etContent.getText().toString();
                    if (obj != null) {
                        String obj2 = StringsKt.trim((CharSequence) obj).toString();
                        str2 = CustomTextInputDialog.this.TAG;
                        Pdlog.m3273d(str2, "input_done " + obj2);
                        if ((!StringsKt.isBlank(obj2)) && (onContentChange = CustomTextInputDialog.this.getOnContentChange()) != null) {
                            if (obj2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            onContentChange.invoke(StringsKt.trim((CharSequence) obj2).toString());
                        }
                        CustomTextInputDialog.this.dismissAllowingStateLoss();
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                } catch (Exception e) {
                    CustomTextInputDialog.this.dismissLoadDialog();
                    str = CustomTextInputDialog.this.TAG;
                    Pdlog.m3273d(str, "input_done---Exception:" + e);
                }
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.ivTextClose)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTextInputDialog$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = CustomTextInputDialog.this.TAG;
                Pdlog.m3273d(str, "cancel");
                CustomTextInputDialog.this.dismissAllowingStateLoss();
            }
        });
        KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(C4188R.id.etContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCount(int contentLength) {
        TextView tvCount = (TextView) _$_findCachedViewById(C4188R.id.tvCount);
        Intrinsics.checkExpressionValueIsNotNull(tvCount, "tvCount");
        StringBuilder sb = new StringBuilder();
        sb.append(contentLength);
        sb.append('/');
        sb.append(this.maxLength);
        tvCount.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean wordLengthOverStep() {
        return this.content.length() > this.maxLength;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissLoadDialog() {
        ConstraintLayout textLoadingView = (ConstraintLayout) _$_findCachedViewById(C4188R.id.textLoadingView);
        Intrinsics.checkExpressionValueIsNotNull(textLoadingView, "textLoadingView");
        if (textLoadingView.isShown()) {
            ConstraintLayout textLoadingView2 = (ConstraintLayout) _$_findCachedViewById(C4188R.id.textLoadingView);
            Intrinsics.checkExpressionValueIsNotNull(textLoadingView2, "textLoadingView");
            textLoadingView2.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etContent));
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        ((ProgressBar) _$_findCachedViewById(C4188R.id.pbTextLoading)).setIndeterminateDrawable(getResources().getDrawable(C4188R.drawable.ic_base_load));
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etContent));
        Pdlog.m3273d(this.TAG, "onDismiss");
        super.dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        translucent();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        super.onDismiss(dialog);
        Pdlog.m3273d(this.TAG, "onDismiss");
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Window window;
        Window window2;
        super.onResume();
        translucent();
        Dialog dialog = getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
        }
        Dialog dialog2 = getDialog();
        if (dialog2 == null || (window = dialog2.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translucent() {
        Window window;
        if (Build.VERSION.SDK_INT >= 19) {
            Dialog dialog = getDialog();
            View decorView = (dialog == null || (window = dialog.getWindow()) == null) ? null : window.getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(3846);
            }
        }
    }
}
