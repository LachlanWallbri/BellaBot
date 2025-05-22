package org.hash.mock.debug.view.sheet.listener;

import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SingleClickListener implements View.OnClickListener {
    private View.OnClickListener mListener;
    private SingleClickHelper singleClickhelper = new SingleClickHelper();

    public SingleClickListener(View.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener;
        if (!this.singleClickhelper.clickEnable() || (onClickListener = this.mListener) == null) {
            return;
        }
        onClickListener.onClick(view);
    }
}
