package com.pudutech.mpmodule;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;
import com.pudutech.base.Pdlog;
import com.pudutech.importmusic.ImportMusicActivity;
import com.pudutech.mpmodule.DeleteMusicAdapter;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.media.MediaLoader;
import com.pudutech.mpmodule.p060ui.BaseActivity;
import com.pudutech.mpmodule.p060ui.NotiDialog;
import com.pudutech.mpmodule.p060ui.widget.CStateRecyclerView;
import com.pudutech.mpmodule.p060ui.widget.CTopBar;
import com.pudutech.mpmodule.utils.FileUtil;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.RecyclerViewAnimUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class DeleteMusicActivity extends BaseActivity {

    @BindView(2131427409)
    LinearLayout mBtachSelectRegion;

    @BindView(2131427431)
    CheckBox mCheckAll;

    @BindView(2131427418)
    LinearLayout mDelCheckedBtn;
    private DeleteMusicAdapter mDeleteMusicAdapter;

    @BindView(2131427419)
    LinearLayout mEditBtn;
    private CountDownTimer mLoadingTimer = null;
    private MediaLoader mMediaLoader = null;
    private List<Media> mMedias;

    @BindView(2131427551)
    CStateRecyclerView mMusicListView;
    private NotiDialog mNotiDialog;
    private List<Media> mSelectedMedias;

    @BindView(2131427424)
    CTopBar mTopBar;

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setRootView() {
        setContentView(C5441R.layout.module_musicplayer_activity_delete_music);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initData() {
        this.mMedias = Lists.newArrayList();
        this.mSelectedMedias = Lists.newArrayList();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestData();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initWidget() {
        this.mTopBar.setTitle(getString(C5441R.string.pdStr10_61));
        this.mMusicListView.getRealRecyclerView().setHasFixedSize(true);
        this.mMusicListView.getRealRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        this.mDeleteMusicAdapter = new DeleteMusicAdapter(this, this.mMedias, this.mSelectedMedias);
        this.mMusicListView.getRealRecyclerView().setAdapter(this.mDeleteMusicAdapter);
        this.mMusicListView.getRealRecyclerView().addItemDecoration(new DividerItemDecoration(this, 1));
        RecyclerViewAnimUtil.getInstance().closeDefaultAnimator(this.mMusicListView.getRealRecyclerView());
        refreshRecyclerViewStatus();
        refreshDelBtnEnabled();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setListeners() {
        this.mDeleteMusicAdapter.setOnItemClickListener(new DeleteMusicAdapter.OnItemClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicActivity.1
            @Override // com.pudutech.mpmodule.DeleteMusicAdapter.OnItemClickListener
            public void onCheckedChanged(Media media, boolean z, int i) {
                DeleteMusicActivity.this.refreshSelectedMedias(media, z);
                DeleteMusicActivity.this.refreshDelBtnEnabled();
                if (DeleteMusicActivity.this.mDeleteMusicAdapter.checkIsAllSelect()) {
                    DeleteMusicActivity.this.mCheckAll.setChecked(true);
                } else {
                    DeleteMusicActivity.this.mCheckAll.setChecked(false);
                }
            }
        });
        this.mMusicListView.setOnBtnClickListener(new CStateRecyclerView.onBtnClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicActivity.2
            @Override // com.pudutech.mpmodule.ui.widget.CStateRecyclerView.onBtnClickListener
            public void onBtnClick() {
                DeleteMusicActivity deleteMusicActivity = DeleteMusicActivity.this;
                deleteMusicActivity.startActivity(new Intent(deleteMusicActivity, (Class<?>) ImportMusicActivity.class));
            }
        });
        this.mMusicListView.setmViewEmptyCallback(new CStateRecyclerView.onViewEmptyCallback() { // from class: com.pudutech.mpmodule.DeleteMusicActivity.3
            @Override // com.pudutech.mpmodule.ui.widget.CStateRecyclerView.onViewEmptyCallback
            public void onViewEmpty() {
                DeleteMusicActivity.this.refreshRecyclerViewStatus();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({2131427418, 2131427419, 2131427581})
    public void onClickListener(View view) {
        if (view.getId() == C5441R.id.btn_edit_list) {
            this.mCheckAll.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (DeleteMusicActivity.this.mCheckAll.isChecked()) {
                        DeleteMusicActivity.this.mDeleteMusicAdapter.changeListCheckedMode(11);
                    } else {
                        DeleteMusicActivity.this.mDeleteMusicAdapter.changeListCheckedMode(22);
                    }
                    DeleteMusicActivity.this.refreshDelBtnEnabled();
                }
            });
            this.mDeleteMusicAdapter.switchToEditMode(true);
            this.mEditBtn.setVisibility(4);
            this.mDelCheckedBtn.setVisibility(0);
            this.mBtachSelectRegion.setVisibility(0);
            return;
        }
        if (view.getId() == C5441R.id.btn_del_checked) {
            this.mNotiDialog = new NotiDialog(this) { // from class: com.pudutech.mpmodule.DeleteMusicActivity.5
                @Override // com.pudutech.mpmodule.p060ui.NotiDialog
                public void setContent() {
                    TextView textView = (TextView) findViewById(C5441R.id.title_text);
                    DeleteMusicActivity deleteMusicActivity = DeleteMusicActivity.this;
                    int i = C5441R.string.pdStr10_46;
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(DeleteMusicActivity.this.mSelectedMedias == null ? 0 : DeleteMusicActivity.this.mSelectedMedias.size());
                    textView.setText(deleteMusicActivity.getString(i, objArr));
                }
            };
            this.mNotiDialog.setContentView(C5441R.layout.module_dialog_content_delete_confirm);
            this.mNotiDialog.addClickCallBack(new NotiDialog.ClickCallBack() { // from class: com.pudutech.mpmodule.DeleteMusicActivity.6
                @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                public void onDeny() {
                    DeleteMusicActivity.this.mNotiDialog.dismiss();
                }

                @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                public void onConfirm() {
                    DeleteMusicActivity.this.mDeleteMusicAdapter.notifyItemsRemoved();
                    DeleteMusicActivity.this.mSelectedMedias.clear();
                    DeleteMusicActivity.this.refreshDelBtnEnabled();
                    Toast.makeText(DeleteMusicActivity.this.getApplicationContext(), DeleteMusicActivity.this.getString(C5441R.string.pdStr10_38), 0).show();
                    DeleteMusicActivity.this.mNotiDialog.dismiss();
                    DeleteMusicActivity.this.mCheckAll.setOnCheckedChangeListener(null);
                    DeleteMusicActivity.this.mCheckAll.setChecked(false);
                    DeleteMusicActivity.this.mDeleteMusicAdapter.switchToEditMode(false);
                    if (Lists.isEmpty(DeleteMusicActivity.this.mMedias)) {
                        return;
                    }
                    DeleteMusicActivity.this.setTopWidgetVisibility(true);
                }
            });
            this.mNotiDialog.setContent();
            this.mNotiDialog.show();
            return;
        }
        if (view.getId() == C5441R.id.reset_to_normal_text) {
            this.mCheckAll.setOnCheckedChangeListener(null);
            this.mCheckAll.setChecked(false);
            this.mSelectedMedias.clear();
            refreshDelBtnEnabled();
            this.mDeleteMusicAdapter.switchToEditMode(false);
            this.mEditBtn.setVisibility(0);
            this.mDelCheckedBtn.setVisibility(4);
            this.mBtachSelectRegion.setVisibility(4);
        }
    }

    protected void requestData() {
        MediaLoader mediaLoader = this.mMediaLoader;
        if (mediaLoader != null) {
            mediaLoader.release();
        }
        this.mMediaLoader = new MediaLoader(getApplicationContext());
        this.mMediaLoader.loadMedias(new C54127());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: com.pudutech.mpmodule.DeleteMusicActivity$7 */
    /* loaded from: classes6.dex */
    public class C54127 implements MediaLoader.OnMediaLoadListener {
        C54127() {
        }

        @Override // com.pudutech.mpmodule.media.MediaLoader.OnMediaLoadListener
        public void onLoadStart() {
            DeleteMusicActivity deleteMusicActivity = DeleteMusicActivity.this;
            deleteMusicActivity.mNotiDialog = new NotiDialog(deleteMusicActivity);
            DeleteMusicActivity.this.mNotiDialog.setContentView(C5441R.layout.module_dialog_load_process_layout);
            DeleteMusicActivity.this.mNotiDialog.show();
            if (DeleteMusicActivity.this.mLoadingTimer != null) {
                DeleteMusicActivity.this.mLoadingTimer.cancel();
            }
            DeleteMusicActivity.this.mLoadingTimer = new CountDownTimer(20000L, 30000L) { // from class: com.pudutech.mpmodule.DeleteMusicActivity.7.1
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    Pdlog.m3273d(DeleteMusicActivity.this.TAG, "Loading File over time, show tip dialog.");
                    if (DeleteMusicActivity.this.isFinishing() || DeleteMusicActivity.this.isDestroyed()) {
                        Pdlog.m3273d(DeleteMusicActivity.this.TAG, "Activity is finishing, ignore load file over time call back");
                        return;
                    }
                    if (DeleteMusicActivity.this.mNotiDialog != null && DeleteMusicActivity.this.mNotiDialog.isShowing()) {
                        DeleteMusicActivity.this.mNotiDialog.dismiss();
                        DeleteMusicActivity.this.mNotiDialog = null;
                    }
                    if (DeleteMusicActivity.this.mMediaLoader != null) {
                        DeleteMusicActivity.this.mMediaLoader.release();
                    }
                    DeleteMusicActivity.this.mNotiDialog = new NotiDialog(DeleteMusicActivity.this);
                    DeleteMusicActivity.this.mNotiDialog.setContentView(C5441R.layout.module_dialog_content_load_file_overtime_layout);
                    DeleteMusicActivity.this.mNotiDialog.addClickCallBack(new NotiDialog.ClickCallBack() { // from class: com.pudutech.mpmodule.DeleteMusicActivity.7.1.1
                        @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                        public void onDeny() {
                        }

                        @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                        public void onConfirm() {
                            DeleteMusicActivity.this.mNotiDialog.dismiss();
                            DeleteMusicActivity.this.finish();
                        }
                    });
                    DeleteMusicActivity.this.mNotiDialog.show();
                }
            };
            DeleteMusicActivity.this.mLoadingTimer.start();
        }

        @Override // com.pudutech.mpmodule.media.MediaLoader.OnMediaLoadListener
        public void onLoadSuccessful(List<Media> list) {
            if (DeleteMusicActivity.this.mLoadingTimer != null) {
                DeleteMusicActivity.this.mLoadingTimer.cancel();
            }
            if (DeleteMusicActivity.this.isFinishing() || DeleteMusicActivity.this.isDestroyed()) {
                Pdlog.m3273d(DeleteMusicActivity.this.TAG, "Activity has been destroied, ignore load music result");
                return;
            }
            if (DeleteMusicActivity.this.mNotiDialog != null && DeleteMusicActivity.this.mNotiDialog.isShowing()) {
                DeleteMusicActivity.this.mNotiDialog.dismiss();
                DeleteMusicActivity.this.mNotiDialog = null;
            }
            Pdlog.m3273d(DeleteMusicActivity.this.TAG, "load native songs complete...");
            if (Lists.isNotEmpty(list)) {
                DeleteMusicActivity.this.mMedias.clear();
                DeleteMusicActivity.this.mMedias.addAll(list);
                DeleteMusicActivity.this.mDeleteMusicAdapter.notifyDataSetChanged();
                FileUtil.verifyStoragePermissions(DeleteMusicActivity.this);
            }
            DeleteMusicActivity.this.refreshRecyclerViewStatus();
        }

        @Override // com.pudutech.mpmodule.media.MediaLoader.OnMediaLoadListener
        public void onLoadFinished() {
            if (DeleteMusicActivity.this.mMediaLoader != null) {
                DeleteMusicActivity.this.mMediaLoader.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSelectedMedias(Media media, boolean z) {
        if (z) {
            if (this.mSelectedMedias.contains(media)) {
                return;
            }
            this.mSelectedMedias.add(media);
            return;
        }
        this.mSelectedMedias.remove(media);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDelBtnEnabled() {
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("mDelCheckedBtn refresh BtnEnabled ");
        sb.append(this.mSelectedMedias.size() > 0);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        this.mDelCheckedBtn.setEnabled(this.mSelectedMedias.size() > 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshRecyclerViewStatus() {
        if (Lists.isEmpty(this.mMedias)) {
            Pdlog.m3273d(this.TAG, "loaded list is null");
            setTopWidgetVisibility(false);
            CStateRecyclerView cStateRecyclerView = this.mMusicListView;
            if (cStateRecyclerView != null) {
                cStateRecyclerView.showEmpty(getString(C5441R.string.pdStr10_52), getString(C5441R.string.pdStr10_54));
                return;
            }
            return;
        }
        Pdlog.m3273d(this.TAG, "show loaded list");
        setTopWidgetVisibility(true);
        CStateRecyclerView cStateRecyclerView2 = this.mMusicListView;
        if (cStateRecyclerView2 != null) {
            cStateRecyclerView2.showData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTopWidgetVisibility(boolean z) {
        if (z) {
            this.mBtachSelectRegion.setVisibility(4);
            this.mEditBtn.setVisibility(0);
            this.mDelCheckedBtn.setVisibility(4);
        } else {
            this.mBtachSelectRegion.setVisibility(4);
            this.mEditBtn.setVisibility(4);
            this.mDelCheckedBtn.setVisibility(4);
        }
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void onRelease() {
        NotiDialog notiDialog = this.mNotiDialog;
        if (notiDialog != null && notiDialog.isShowing()) {
            this.mNotiDialog.dismiss();
            this.mNotiDialog = null;
        }
        CountDownTimer countDownTimer = this.mLoadingTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        MediaLoader mediaLoader = this.mMediaLoader;
        if (mediaLoader != null) {
            mediaLoader.release();
        }
    }
}
