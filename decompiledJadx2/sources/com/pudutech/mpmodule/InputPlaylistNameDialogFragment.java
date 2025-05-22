package com.pudutech.mpmodule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import com.pudutech.mpmodule.C5442R2;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.utils.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class InputPlaylistNameDialogFragment extends DialogFragment {
    private OnConfirmInputListener mOnConfirmInputListener;

    @BindView(2131427491)
    EditText mPlaylistNameEditText;

    @BindView(C5442R2.id.tv_tips)
    TextView mTipsTextView;
    private String playlistName;
    private Unbinder unbinder;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnConfirmInputListener {
        void onConfirmInput(String str);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C5441R.layout.module_musicplayer_dialog_fragment_input_playlist_name, viewGroup);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({2131427415, 2131427416})
    public void onClickListener(View view) {
        if (view.getId() == C5441R.id.btn_cancel) {
            dismiss();
            return;
        }
        if (view.getId() == C5441R.id.btn_confirm && checkInputData()) {
            if (DatabaseManagerFactory.getDatabaseManager().isExistsSamePlaylist(this.playlistName)) {
                setTipsText(getString(C5441R.string.pdStr10_34));
                return;
            }
            dismiss();
            OnConfirmInputListener onConfirmInputListener = this.mOnConfirmInputListener;
            if (onConfirmInputListener != null) {
                onConfirmInputListener.onConfirmInput(this.playlistName);
            }
        }
    }

    private void setTipsText(String str) {
        this.mTipsTextView.setVisibility(StringUtil.isEmpty(str) ? 8 : 0);
        this.mTipsTextView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.TEXT_CHANGED, value = {2131427491})
    public void onPlaylistNameEditTextTextChanged() {
        setTipsText(null);
    }

    private boolean checkInputData() {
        this.playlistName = this.mPlaylistNameEditText.getText().toString();
        if (!StringUtil.isEmpty(this.playlistName)) {
            return true;
        }
        setTipsText(getString(C5441R.string.pdStr10_23));
        return false;
    }

    public void setOnConfirmInputListener(OnConfirmInputListener onConfirmInputListener) {
        this.mOnConfirmInputListener = onConfirmInputListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }
}
