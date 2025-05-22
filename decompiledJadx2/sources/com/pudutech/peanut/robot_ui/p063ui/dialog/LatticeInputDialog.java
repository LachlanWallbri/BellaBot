package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.util.KeyboardUtils;
import com.pudutech.peanut.robot_ui.util.ScreenUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LatticeInputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0010H\u0016J\u001a\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u001d2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010+\u001a\u00020\u0010H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR7\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006,"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/LatticeInputDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", AIUIConstant.KEY_CONTENT, "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "onContentChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnContentChange", "()Lkotlin/jvm/functions/Function1;", "setOnContentChange", "(Lkotlin/jvm/functions/Function1;)V", "dismiss", "dismissAllowingStateLoss", "initView", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onMultiWindowModeChanged", "isInMultiWindowMode", "", "onResume", "onViewCreated", "view", "translucent", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LatticeInputDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    private Function1<? super String, Unit> onContentChange;
    private final String TAG = getClass().getSimpleName();
    private String content = "";

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

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Pdlog.m3273d(this.TAG, "onCreateView");
        return inflater.inflate(C5508R.layout.dialog_latice_input, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Pdlog.m3273d(this.TAG, "onCreateDialog");
        setStyle(1, C5508R.style.AppTheme);
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
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LatticeInputDialog$onViewCreated$1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                LatticeInputDialog.this.translucent();
            }
        });
        translucent();
        initView();
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView");
        setCancelable(false);
        ((EditText) _$_findCachedViewById(C5508R.id.content_input)).setText(this.content);
        ((EditText) _$_findCachedViewById(C5508R.id.content_input)).setSelection(this.content.length());
        ((TextView) _$_findCachedViewById(C5508R.id.input_done)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LatticeInputDialog$initView$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                super.onSingleClick();
                str = LatticeInputDialog.this.TAG;
                Pdlog.m3273d(str, "input_done " + LatticeInputDialog.this.getContent());
                EditText content_input = (EditText) LatticeInputDialog.this._$_findCachedViewById(C5508R.id.content_input);
                Intrinsics.checkExpressionValueIsNotNull(content_input, "content_input");
                String obj = content_input.getText().toString();
                Function1<String, Unit> onContentChange = LatticeInputDialog.this.getOnContentChange();
                if (onContentChange != null) {
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    onContentChange.invoke(StringsKt.trim((CharSequence) obj).toString());
                }
                LatticeInputDialog.this.dismissAllowingStateLoss();
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.input_preview)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LatticeInputDialog$initView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.cancel)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LatticeInputDialog$initView$3
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                super.onSingleClick();
                str = LatticeInputDialog.this.TAG;
                Pdlog.m3273d(str, "cancel");
                LatticeInputDialog.this.dismissAllowingStateLoss();
            }
        });
        Dialog dialog = getDialog();
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog!!");
        KeyboardUtils.registerSoftInputChangedListener(dialog.getWindow(), new KeyboardUtils.OnSoftInputChangedListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.LatticeInputDialog$initView$4
            @Override // com.pudutech.peanut.robot_ui.util.KeyboardUtils.OnSoftInputChangedListener
            public final void onSoftInputChanged(int i) {
                String str;
                str = LatticeInputDialog.this.TAG;
                Pdlog.m3273d(str, "registerSoftInputChangedListener : " + i + " ; window h = " + ScreenUtils.getScreenHeight(LatticeInputDialog.this.getContext()));
                if (LatticeInputDialog.this.getContext() == null) {
                    return;
                }
                LatticeInputDialog.this.translucent();
            }
        });
        KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(C5508R.id.content_input));
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C5508R.id.content_input));
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C5508R.id.content_input));
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
