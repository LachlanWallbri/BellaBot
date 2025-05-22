package com.pudutech.mpmodule;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pudutech.mpmodule.p060ui.widget.CStateRecyclerView;
import com.pudutech.mpmodule.p060ui.widget.CTopBar;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class DeleteMusicActivity_ViewBinding implements Unbinder {
    private DeleteMusicActivity target;
    private View view7f0b005a;
    private View view7f0b005b;
    private View view7f0b00fd;

    public DeleteMusicActivity_ViewBinding(DeleteMusicActivity deleteMusicActivity) {
        this(deleteMusicActivity, deleteMusicActivity.getWindow().getDecorView());
    }

    public DeleteMusicActivity_ViewBinding(final DeleteMusicActivity deleteMusicActivity, View view) {
        this.target = deleteMusicActivity;
        deleteMusicActivity.mTopBar = (CTopBar) Utils.findRequiredViewAsType(view, C5441R.id.c_topBar, "field 'mTopBar'", CTopBar.class);
        deleteMusicActivity.mMusicListView = (CStateRecyclerView) Utils.findRequiredViewAsType(view, C5441R.id.native_music_list, "field 'mMusicListView'", CStateRecyclerView.class);
        deleteMusicActivity.mCheckAll = (CheckBox) Utils.findRequiredViewAsType(view, C5441R.id.check_all_box, "field 'mCheckAll'", CheckBox.class);
        View findRequiredView = Utils.findRequiredView(view, C5441R.id.btn_edit_list, "field 'mEditBtn' and method 'onClickListener'");
        deleteMusicActivity.mEditBtn = (LinearLayout) Utils.castView(findRequiredView, C5441R.id.btn_edit_list, "field 'mEditBtn'", LinearLayout.class);
        this.view7f0b005b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deleteMusicActivity.onClickListener(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, C5441R.id.btn_del_checked, "field 'mDelCheckedBtn' and method 'onClickListener'");
        deleteMusicActivity.mDelCheckedBtn = (LinearLayout) Utils.castView(findRequiredView2, C5441R.id.btn_del_checked, "field 'mDelCheckedBtn'", LinearLayout.class);
        this.view7f0b005a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deleteMusicActivity.onClickListener(view2);
            }
        });
        deleteMusicActivity.mBtachSelectRegion = (LinearLayout) Utils.findRequiredViewAsType(view, C5441R.id.batch_select_region, "field 'mBtachSelectRegion'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, C5441R.id.reset_to_normal_text, "method 'onClickListener'");
        this.view7f0b00fd = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deleteMusicActivity.onClickListener(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeleteMusicActivity deleteMusicActivity = this.target;
        if (deleteMusicActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deleteMusicActivity.mTopBar = null;
        deleteMusicActivity.mMusicListView = null;
        deleteMusicActivity.mCheckAll = null;
        deleteMusicActivity.mEditBtn = null;
        deleteMusicActivity.mDelCheckedBtn = null;
        deleteMusicActivity.mBtachSelectRegion = null;
        this.view7f0b005b.setOnClickListener(null);
        this.view7f0b005b = null;
        this.view7f0b005a.setOnClickListener(null);
        this.view7f0b005a = null;
        this.view7f0b00fd.setOnClickListener(null);
        this.view7f0b00fd = null;
    }
}
