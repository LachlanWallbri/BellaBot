package com.pudutech.module_robot_selfcheck.p058ui.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.pudutech.disinfect.baselib.dialog.BaseDialog;
import com.pudutech.module_robot_selfcheck.C5365R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TipsDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0001H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/dialog/TipsDialog;", "Lcom/pudutech/disinfect/baselib/dialog/BaseDialog;", "tips", "", "(Ljava/lang/String;)V", "bindView", "", "rootView", "Landroid/view/View;", "dialog", "getDialogLayoutId", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TipsDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private final String tips;

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

    public TipsDialog(String tips) {
        Intrinsics.checkParameterIsNotNull(tips, "tips");
        this.tips = tips;
    }

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
    public int getDialogLayoutId() {
        return C5365R.layout.dialog_show_msg_tips;
    }

    @Override // com.pudutech.disinfect.baselib.dialog.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        if (rootView != null) {
            TextView tvTips = (TextView) _$_findCachedViewById(C5365R.id.tvTips);
            Intrinsics.checkExpressionValueIsNotNull(tvTips, "tvTips");
            tvTips.setText(this.tips);
            ((Button) _$_findCachedViewById(C5365R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ui.dialog.TipsDialog$bindView$$inlined$apply$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View it) {
                    Function1<View, Unit> onClickListener = TipsDialog.this.getOnClickListener();
                    if (onClickListener != null) {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        onClickListener.invoke(it);
                    }
                    TipsDialog.this.dismissDialog();
                }
            });
        }
    }
}
