package com.pudutech.mpmodule;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pudutech.mpmodule.p060ui.widget.CStateRecyclerView;
import com.pudutech.mpmodule.p060ui.widget.CTopBar;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlaylistInfoEditActivity_ViewBinding implements Unbinder {
    private PlaylistInfoEditActivity target;
    private View view7f0b0056;
    private View view7f0b005a;
    private View view7f0b005b;
    private View view7f0b00fd;

    public PlaylistInfoEditActivity_ViewBinding(PlaylistInfoEditActivity playlistInfoEditActivity) {
        this(playlistInfoEditActivity, playlistInfoEditActivity.getWindow().getDecorView());
    }

    public PlaylistInfoEditActivity_ViewBinding(final PlaylistInfoEditActivity playlistInfoEditActivity, View view) {
        this.target = playlistInfoEditActivity;
        playlistInfoEditActivity.mTopBar = (CTopBar) Utils.findRequiredViewAsType(view, C5441R.id.c_topBar, "field 'mTopBar'", CTopBar.class);
        playlistInfoEditActivity.mMusicListRecyclerView = (CStateRecyclerView) Utils.findRequiredViewAsType(view, C5441R.id.crv_music_list, "field 'mMusicListRecyclerView'", CStateRecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, C5441R.id.btn_del_checked, "field 'mDelCheckedBtn' and method 'onClickListener'");
        playlistInfoEditActivity.mDelCheckedBtn = (LinearLayout) Utils.castView(findRequiredView, C5441R.id.btn_del_checked, "field 'mDelCheckedBtn'", LinearLayout.class);
        this.view7f0b005a = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                playlistInfoEditActivity.onClickListener(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, C5441R.id.btn_edit_list, "field 'mEditBtn' and method 'onClickListener'");
        playlistInfoEditActivity.mEditBtn = (LinearLayout) Utils.castView(findRequiredView2, C5441R.id.btn_edit_list, "field 'mEditBtn'", LinearLayout.class);
        this.view7f0b005b = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                playlistInfoEditActivity.onClickListener(view2);
            }
        });
        playlistInfoEditActivity.mCheckAll = (CheckBox) Utils.findRequiredViewAsType(view, C5441R.id.check_all_box, "field 'mCheckAll'", CheckBox.class);
        playlistInfoEditActivity.mSelectAll = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.select_all_text, "field 'mSelectAll'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, C5441R.id.reset_to_normal_text, "field 'mResetAll' and method 'onClickListener'");
        playlistInfoEditActivity.mResetAll = (TextView) Utils.castView(findRequiredView3, C5441R.id.reset_to_normal_text, "field 'mResetAll'", TextView.class);
        this.view7f0b00fd = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                playlistInfoEditActivity.onClickListener(view2);
            }
        });
        playlistInfoEditActivity.mAddMusicText = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.add_music_text, "field 'mAddMusicText'", TextView.class);
        playlistInfoEditActivity.mEditMusicText = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.edit_list_text, "field 'mEditMusicText'", TextView.class);
        playlistInfoEditActivity.mDeleteMusicText = (TextView) Utils.findRequiredViewAsType(view, C5441R.id.delete_music_text, "field 'mDeleteMusicText'", TextView.class);
        playlistInfoEditActivity.mBtachSelectRegion = (LinearLayout) Utils.findRequiredViewAsType(view, C5441R.id.batch_select_region, "field 'mBtachSelectRegion'", LinearLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, C5441R.id.btn_add_music, "field 'mAddMusicBtn' and method 'onClickListener'");
        playlistInfoEditActivity.mAddMusicBtn = (LinearLayout) Utils.castView(findRequiredView4, C5441R.id.btn_add_music, "field 'mAddMusicBtn'", LinearLayout.class);
        this.view7f0b0056 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                playlistInfoEditActivity.onClickListener(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PlaylistInfoEditActivity playlistInfoEditActivity = this.target;
        if (playlistInfoEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        playlistInfoEditActivity.mTopBar = null;
        playlistInfoEditActivity.mMusicListRecyclerView = null;
        playlistInfoEditActivity.mDelCheckedBtn = null;
        playlistInfoEditActivity.mEditBtn = null;
        playlistInfoEditActivity.mCheckAll = null;
        playlistInfoEditActivity.mSelectAll = null;
        playlistInfoEditActivity.mResetAll = null;
        playlistInfoEditActivity.mAddMusicText = null;
        playlistInfoEditActivity.mEditMusicText = null;
        playlistInfoEditActivity.mDeleteMusicText = null;
        playlistInfoEditActivity.mBtachSelectRegion = null;
        playlistInfoEditActivity.mAddMusicBtn = null;
        this.view7f0b005a.setOnClickListener(null);
        this.view7f0b005a = null;
        this.view7f0b005b.setOnClickListener(null);
        this.view7f0b005b = null;
        this.view7f0b00fd.setOnClickListener(null);
        this.view7f0b00fd = null;
        this.view7f0b0056.setOnClickListener(null);
        this.view7f0b0056 = null;
    }
}
