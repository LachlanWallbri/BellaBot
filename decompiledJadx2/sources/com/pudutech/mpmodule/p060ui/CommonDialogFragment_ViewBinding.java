package com.pudutech.mpmodule.p060ui;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pudutech.mpmodule.C5441R;
import com.pudutech.mpmodule.p060ui.widget.CTextButton;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CommonDialogFragment_ViewBinding implements Unbinder {
    private CommonDialogFragment target;

    public CommonDialogFragment_ViewBinding(CommonDialogFragment commonDialogFragment, View view) {
        this.target = commonDialogFragment;
        commonDialogFragment.mTitleTextView = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.tv_title, "field 'mTitleTextView'", TextView.class);
        commonDialogFragment.mPositiveBtn = (CTextButton) Utils.findRequiredViewAsType(view, C5441R.id.btn_positive, "field 'mPositiveBtn'", CTextButton.class);
        commonDialogFragment.mNegativeBtn = (CTextButton) Utils.findRequiredViewAsType(view, C5441R.id.btn_negative, "field 'mNegativeBtn'", CTextButton.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommonDialogFragment commonDialogFragment = this.target;
        if (commonDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commonDialogFragment.mTitleTextView = null;
        commonDialogFragment.mPositiveBtn = null;
        commonDialogFragment.mNegativeBtn = null;
    }
}
