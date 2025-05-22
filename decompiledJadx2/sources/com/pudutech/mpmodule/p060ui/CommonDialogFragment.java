package com.pudutech.mpmodule.p060ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.pudutech.mpmodule.C5441R;
import com.pudutech.mpmodule.C5442R2;
import com.pudutech.mpmodule.p060ui.widget.CTextButton;
import com.pudutech.mpmodule.utils.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CommonDialogFragment extends DialogFragment {
    private Builder builder;

    @BindView(2131427420)
    CTextButton mNegativeBtn;

    @BindView(2131427421)
    CTextButton mPositiveBtn;

    @BindView(C5442R2.id.tv_title)
    TextView mTitleTextView;
    private Unbinder unbinder;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C5441R.layout.module_musicplayer_dialog_fragment_common, viewGroup);
        this.unbinder = ButterKnife.bind(this, inflate);
        initWidget();
        return inflate;
    }

    private void initWidget() {
        Builder builder = this.builder;
        if (builder == null) {
            return;
        }
        this.mTitleTextView.setText(builder.title);
        if (StringUtil.isNotEmpty(this.builder.positiveBtnText)) {
            this.mPositiveBtn.setVisibility(0);
            this.mPositiveBtn.setText(this.builder.positiveBtnText);
        }
        if (this.builder.positiveBtnClickListener != null) {
            this.mPositiveBtn.setOnClickListener(this.builder.positiveBtnClickListener);
        }
        if (StringUtil.isNotEmpty(this.builder.negativeBtnText)) {
            this.mNegativeBtn.setVisibility(0);
            this.mNegativeBtn.setText(this.builder.negativeBtnText);
        }
        if (this.builder.negativeBtnClickListener != null) {
            this.mNegativeBtn.setOnClickListener(this.builder.negativeBtnClickListener);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public CommonDialogFragment setBuilder(Builder builder) {
        this.builder = builder;
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class Builder {
        private View.OnClickListener negativeBtnClickListener;
        private String negativeBtnText;
        private View.OnClickListener positiveBtnClickListener;
        private String positiveBtnText;
        private CharSequence title;

        public Builder build() {
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        public Builder setPositiveBtn(String str, View.OnClickListener onClickListener) {
            this.positiveBtnText = str;
            this.positiveBtnClickListener = onClickListener;
            return this;
        }

        public Builder setNegativeBtn(String str, View.OnClickListener onClickListener) {
            this.negativeBtnText = str;
            this.negativeBtnClickListener = onClickListener;
            return this;
        }
    }
}
