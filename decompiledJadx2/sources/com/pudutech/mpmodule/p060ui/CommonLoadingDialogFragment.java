package com.pudutech.mpmodule.p060ui;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.pudutech.mpmodule.C5441R;
import com.pudutech.mpmodule.utils.CThreadPoolExecutor;
import com.pudutech.mpmodule.utils.DensityUtil;
import com.pudutech.widget.loading.CLoadingView;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CommonLoadingDialogFragment extends DialogFragment {

    @BindView(2131427423)
    CLoadingView mLoadingView;
    private Unbinder unbinder;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C5441R.layout.module_musicplayer_dialog_fragment_common_loading, viewGroup);
        this.unbinder = ButterKnife.bind(this, inflate);
        CThreadPoolExecutor.runOnMainThread(new Runnable() { // from class: com.pudutech.mpmodule.ui.-$$Lambda$CommonLoadingDialogFragment$CGqLCmErI3Wtj0C9np_Hlu2t9fw
            @Override // java.lang.Runnable
            public final void run() {
                CommonLoadingDialogFragment.this.lambda$onCreateView$0$CommonLoadingDialogFragment();
            }
        }, 100L);
        return inflate;
    }

    public /* synthetic */ void lambda$onCreateView$0$CommonLoadingDialogFragment() {
        this.mLoadingView.show();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), C5441R.style.style_common_dialog_fragment);
        dialog.requestWindowFeature(1);
        dialog.setContentView(LayoutInflater.from(getActivity()).inflate(C5441R.layout.module_musicplayer_dialog_fragment_common_loading, (ViewGroup) null));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = DensityUtil.dp2px(148.0f);
        attributes.height = DensityUtil.dp2px(148.0f);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setAttributes(attributes);
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mLoadingView.hide();
        this.unbinder.unbind();
    }
}
