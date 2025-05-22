package com.pudutech.freeinstall_ui.dialog;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoadingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0016J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\u000e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bR\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/LoadingDialog;", "Lcom/pudutech/freeinstall_ui/dialog/BaseDialog;", "()V", "onCloseClick", "Lkotlin/Function0;", "", "getOnCloseClick", "()Lkotlin/jvm/functions/Function0;", "setOnCloseClick", "(Lkotlin/jvm/functions/Function0;)V", "title", "", "visible", "", "bindView", "rootView", "Landroid/view/View;", "dialog", "closeVisible", "getDialogLayoutId", "", "onResume", "setTitle", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LoadingDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private Function0<Unit> onCloseClick;
    private String title = "";
    private boolean visible = true;

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

    public final Function0<Unit> getOnCloseClick() {
        return this.onCloseClick;
    }

    public final void setOnCloseClick(Function0<Unit> function0) {
        this.onCloseClick = function0;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public int getDialogLayoutId() {
        return C5362R.layout.dialog_loading;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        ((ImageView) _$_findCachedViewById(C5362R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.LoadingDialog$bindView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoadingDialog.this.dismissDialog();
                Function0<Unit> onCloseClick = LoadingDialog.this.getOnCloseClick();
                if (onCloseClick != null) {
                    onCloseClick.invoke();
                }
            }
        });
        if (this.title.length() > 0) {
            TextView tv_tips = (TextView) _$_findCachedViewById(C5362R.id.tv_tips);
            Intrinsics.checkExpressionValueIsNotNull(tv_tips, "tv_tips");
            tv_tips.setText(this.title);
        }
        if (this.visible) {
            ImageView iv_close = (ImageView) _$_findCachedViewById(C5362R.id.iv_close);
            Intrinsics.checkExpressionValueIsNotNull(iv_close, "iv_close");
            iv_close.setVisibility(0);
        } else {
            ImageView iv_close2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_close);
            Intrinsics.checkExpressionValueIsNotNull(iv_close2, "iv_close");
            iv_close2.setVisibility(8);
        }
    }

    public final void setTitle(String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        this.title = title;
    }

    public final void closeVisible(boolean visible) {
        this.visible = visible;
    }
}
