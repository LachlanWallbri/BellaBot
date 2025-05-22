package com.pudutech.module_robot_selfcheck.p058ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.pudutech.disinfect.baselib.dialog.BaseDialog;
import com.pudutech.disinfect.baselib.ext.view.ToastUtils;
import com.pudutech.disinfect.baselib.util.KeyboardUtils;
import com.pudutech.disinfect.baselib.widget.CTextButton;
import com.pudutech.module_robot_selfcheck.C5365R;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CheckPwdDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0001H\u0016J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016R(\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0012j\b\u0012\u0004\u0012\u00020\u0005`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/CheckPwdDialog;", "Lcom/pudutech/disinfect/baselib/dialog/BaseDialog;", "()V", "onPswResult", "Lkotlin/Function1;", "", "", "getOnPswResult", "()Lkotlin/jvm/functions/Function1;", "setOnPswResult", "(Lkotlin/jvm/functions/Function1;)V", "onVerifySuccessfulListener", "Lkotlin/Function0;", "getOnVerifySuccessfulListener", "()Lkotlin/jvm/functions/Function0;", "setOnVerifySuccessfulListener", "(Lkotlin/jvm/functions/Function0;)V", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getPassword", "()Ljava/util/ArrayList;", "setPassword", "(Ljava/util/ArrayList;)V", "bindView", "rootView", "Landroid/view/View;", "dialog", "dismissDialog", "getDialogLayoutId", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CheckPwdDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private Function1<? super String, Unit> onPswResult;
    private Function0<Unit> onVerifySuccessfulListener;
    private ArrayList<String> password = CollectionsKt.arrayListOf("pudu666", "pudupw");

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
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

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final Function0<Unit> getOnVerifySuccessfulListener() {
        return this.onVerifySuccessfulListener;
    }

    public final void setOnVerifySuccessfulListener(Function0<Unit> function0) {
        this.onVerifySuccessfulListener = function0;
    }

    public final ArrayList<String> getPassword() {
        return this.password;
    }

    public final void setPassword(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.password = arrayList;
    }

    public final Function1<String, Unit> getOnPswResult() {
        return this.onPswResult;
    }

    public final void setOnPswResult(Function1<? super String, Unit> function1) {
        this.onPswResult = function1;
    }

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
    public int getDialogLayoutId() {
        return C5365R.layout.dialog_check_password;
    }

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
    public void dismissDialog() {
        super.dismissDialog();
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C5365R.id.etPwd));
    }

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        if (rootView != null) {
            KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(C5365R.id.etPwd), 0);
            ((ImageView) _$_findCachedViewById(C5365R.id.ivClose)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.CheckPwdDialog$bindView$$inlined$apply$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CheckPwdDialog.this.dismissDialog();
                }
            });
            ((CTextButton) _$_findCachedViewById(C5365R.id.btnComplete)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.CheckPwdDialog$bindView$$inlined$apply$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    EditText etPwd = (EditText) CheckPwdDialog.this._$_findCachedViewById(C5365R.id.etPwd);
                    Intrinsics.checkExpressionValueIsNotNull(etPwd, "etPwd");
                    String obj = etPwd.getText().toString();
                    Function1<String, Unit> onPswResult = CheckPwdDialog.this.getOnPswResult();
                    if (onPswResult != null) {
                        onPswResult.invoke(obj);
                        return;
                    }
                    if (CheckPwdDialog.this.getPassword().contains(obj)) {
                        CheckPwdDialog.this.dismissDialog();
                        Function0<Unit> onVerifySuccessfulListener = CheckPwdDialog.this.getOnVerifySuccessfulListener();
                        if (onVerifySuccessfulListener != null) {
                            onVerifySuccessfulListener.invoke();
                            return;
                        }
                        return;
                    }
                    ToastUtils.INSTANCE.showShortToast("密码输入错误");
                }
            });
        }
    }
}
