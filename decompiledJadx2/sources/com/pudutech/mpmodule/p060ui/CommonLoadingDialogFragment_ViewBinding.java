package com.pudutech.mpmodule.p060ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pudutech.mpmodule.C5441R;
import com.pudutech.widget.loading.CLoadingView;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CommonLoadingDialogFragment_ViewBinding implements Unbinder {
    private CommonLoadingDialogFragment target;

    public CommonLoadingDialogFragment_ViewBinding(CommonLoadingDialogFragment commonLoadingDialogFragment, View view) {
        this.target = commonLoadingDialogFragment;
        commonLoadingDialogFragment.mLoadingView = (CLoadingView) Utils.findRequiredViewAsType(view, C5441R.id.c_loadingView, "field 'mLoadingView'", CLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommonLoadingDialogFragment commonLoadingDialogFragment = this.target;
        if (commonLoadingDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commonLoadingDialogFragment.mLoadingView = null;
    }
}
