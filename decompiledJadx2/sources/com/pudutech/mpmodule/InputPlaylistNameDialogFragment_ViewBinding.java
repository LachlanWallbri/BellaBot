package com.pudutech.mpmodule;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class InputPlaylistNameDialogFragment_ViewBinding implements Unbinder {
    private InputPlaylistNameDialogFragment target;
    private View view7f0b0057;
    private View view7f0b0058;
    private View view7f0b00a3;
    private TextWatcher view7f0b00a3TextWatcher;

    public InputPlaylistNameDialogFragment_ViewBinding(final InputPlaylistNameDialogFragment inputPlaylistNameDialogFragment, View view) {
        this.target = inputPlaylistNameDialogFragment;
        View findRequiredView = Utils.findRequiredView(view, C5441R.id.et_playlist_name, "field 'mPlaylistNameEditText' and method 'onPlaylistNameEditTextTextChanged'");
        inputPlaylistNameDialogFragment.mPlaylistNameEditText = (EditText) Utils.castView(findRequiredView, C5441R.id.et_playlist_name, "field 'mPlaylistNameEditText'", EditText.class);
        this.view7f0b00a3 = findRequiredView;
        this.view7f0b00a3TextWatcher = new TextWatcher() { // from class: com.pudutech.mpmodule.InputPlaylistNameDialogFragment_ViewBinding.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                inputPlaylistNameDialogFragment.onPlaylistNameEditTextTextChanged();
            }
        };
        ((TextView) findRequiredView).addTextChangedListener(this.view7f0b00a3TextWatcher);
        inputPlaylistNameDialogFragment.mTipsTextView = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.tv_tips, "field 'mTipsTextView'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, C5441R.id.btn_cancel, "method 'onClickListener'");
        this.view7f0b0057 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.InputPlaylistNameDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputPlaylistNameDialogFragment.onClickListener(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, C5441R.id.btn_confirm, "method 'onClickListener'");
        this.view7f0b0058 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.InputPlaylistNameDialogFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputPlaylistNameDialogFragment.onClickListener(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        InputPlaylistNameDialogFragment inputPlaylistNameDialogFragment = this.target;
        if (inputPlaylistNameDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        inputPlaylistNameDialogFragment.mPlaylistNameEditText = null;
        inputPlaylistNameDialogFragment.mTipsTextView = null;
        ((TextView) this.view7f0b00a3).removeTextChangedListener(this.view7f0b00a3TextWatcher);
        this.view7f0b00a3TextWatcher = null;
        this.view7f0b00a3 = null;
        this.view7f0b0057.setOnClickListener(null);
        this.view7f0b0057 = null;
        this.view7f0b0058.setOnClickListener(null);
        this.view7f0b0058 = null;
    }
}
