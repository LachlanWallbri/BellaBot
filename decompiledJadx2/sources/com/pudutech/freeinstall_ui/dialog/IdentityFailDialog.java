package com.pudutech.freeinstall_ui.dialog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdentityFailDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0001H\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006 "}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/IdentityFailDialog;", "Lcom/pudutech/freeinstall_ui/dialog/BaseDialog;", "()V", "bitmapResource", "", "getBitmapResource", "()Ljava/lang/Integer;", "setBitmapResource", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "button", "", "getButton", "()Ljava/lang/String;", "setButton", "(Ljava/lang/String;)V", AIUIConstant.KEY_CONTENT, "getContent", "setContent", "onBtnClickListener", "Lkotlin/Function0;", "", "getOnBtnClickListener", "()Lkotlin/jvm/functions/Function0;", "setOnBtnClickListener", "(Lkotlin/jvm/functions/Function0;)V", "bindView", "rootView", "Landroid/view/View;", "dialog", "getDialogLayoutId", "onResume", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class IdentityFailDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private Integer bitmapResource;
    private String button;
    private String content;
    private Function0<Unit> onBtnClickListener;

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
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

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final Function0<Unit> getOnBtnClickListener() {
        return this.onBtnClickListener;
    }

    public final void setOnBtnClickListener(Function0<Unit> function0) {
        this.onBtnClickListener = function0;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final Integer getBitmapResource() {
        return this.bitmapResource;
    }

    public final void setBitmapResource(Integer num) {
        this.bitmapResource = num;
    }

    public final String getButton() {
        return this.button;
    }

    public final void setButton(String str) {
        this.button = str;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public int getDialogLayoutId() {
        return C5362R.layout.dialog_identity_fail;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        ((ImageView) _$_findCachedViewById(C5362R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.IdentityFailDialog$bindView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IdentityFailDialog.this.dismissDialog();
            }
        });
        ((TextView) _$_findCachedViewById(C5362R.id.tv_retry)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.IdentityFailDialog$bindView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0<Unit> onBtnClickListener = IdentityFailDialog.this.getOnBtnClickListener();
                if (onBtnClickListener != null) {
                    onBtnClickListener.invoke();
                }
            }
        });
        if (this.button != null) {
            TextView tv_retry = (TextView) _$_findCachedViewById(C5362R.id.tv_retry);
            Intrinsics.checkExpressionValueIsNotNull(tv_retry, "tv_retry");
            tv_retry.setText(this.button);
        }
        if (this.content != null) {
            TextView tv_content = (TextView) _$_findCachedViewById(C5362R.id.tv_content);
            Intrinsics.checkExpressionValueIsNotNull(tv_content, "tv_content");
            tv_content.setText(this.content);
        }
        Integer num = this.bitmapResource;
        if (num != null) {
            ((ImageView) _$_findCachedViewById(C5362R.id.iv_charge_fail)).setImageResource(num.intValue());
        }
    }
}
