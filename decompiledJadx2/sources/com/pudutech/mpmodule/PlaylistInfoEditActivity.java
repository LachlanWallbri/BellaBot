package com.pudutech.mpmodule;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;
import com.freddy.event.CEventCenter;
import com.freddy.event.I_CEventListener;
import com.pudutech.base.Pdlog;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback;
import com.pudutech.mpmodule.PlaylistInfoListAdapter;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.bean.PreviousPlayBean;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.event.Events;
import com.pudutech.mpmodule.event.obj.AddMusicEventObj;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.p060ui.BaseActivity;
import com.pudutech.mpmodule.p060ui.NotiDialog;
import com.pudutech.mpmodule.p060ui.widget.CItemTouchHelper;
import com.pudutech.mpmodule.p060ui.widget.CStateRecyclerView;
import com.pudutech.mpmodule.p060ui.widget.CTopBar;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.RecyclerViewAnimUtil;
import com.pudutech.mpmodule.utils.StringUtil;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlaylistInfoEditActivity extends BaseActivity implements I_CEventListener {
    private static final String TAG = "PlaylistInfoEditActivit";
    private boolean isOnlyState = false;

    @BindView(2131427414)
    LinearLayout mAddMusicBtn;

    @BindView(2131427395)
    TextView mAddMusicText;

    @BindView(2131427409)
    LinearLayout mBtachSelectRegion;

    @BindView(2131427431)
    CheckBox mCheckAll;

    @BindView(2131427418)
    LinearLayout mDelCheckedBtn;
    private NotiDialog mDeleteDialog;

    @BindView(2131427458)
    TextView mDeleteMusicText;

    @BindView(2131427419)
    LinearLayout mEditBtn;

    @BindView(2131427483)
    TextView mEditMusicText;
    private List<Media> mMedias;

    @BindView(2131427447)
    CStateRecyclerView mMusicListRecyclerView;
    private Playlist mPlaylist;
    private PlaylistInfoListAdapter mPlaylistInfoListAdapter;

    @BindView(2131427581)
    TextView mResetAll;

    @BindView(2131427609)
    TextView mSelectAll;
    private List<Media> mSelectedMedias;

    @BindView(2131427424)
    CTopBar mTopBar;
    private String playlistId;
    private String playlistName;

    public static void actionActivity(Context context, String str, String str2) {
        Intent intent = new Intent(context, (Class<?>) PlaylistInfoEditActivity.class);
        intent.putExtra(Playlist.KEY_LIST_ID, str);
        intent.putExtra(Playlist.KEY_LIST_NAME, str2);
        context.startActivity(intent);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setRootView() {
        setContentView(C5441R.layout.module_musicplayer_activity_playlist_info_edit);
        CEventCenter.registerEventListener(this, Events.EVENT_ADD_MUSIC);
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new IMusicPlayerStateCallback() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.1
            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onInitialized() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onInitialized()");
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onPrepared()");
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPause() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onPause()");
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onStop() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onStop()");
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onError() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onError()");
                if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
                    PlaylistInfoEditActivity playlistInfoEditActivity = PlaylistInfoEditActivity.this;
                    Toast.makeText(playlistInfoEditActivity, playlistInfoEditActivity.getString(C5441R.string.pdStr10_55), 0).show();
                }
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onReset() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onReset()");
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onRelease() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onRelease()");
            }

            @Override // com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onCompletion() {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "IMusicPlayerStateCallback -> onCompletion()");
            }
        });
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initData() {
        this.playlistId = getIntent().getStringExtra(Playlist.KEY_LIST_ID);
        this.playlistName = getIntent().getStringExtra(Playlist.KEY_LIST_NAME);
        this.mPlaylist = DatabaseManagerFactory.getDatabaseManager().queryPlaylistById(this.playlistId);
        Playlist playlist = this.mPlaylist;
        if (playlist != null) {
            this.mMedias = playlist.getMediaList();
        }
        this.mSelectedMedias = Lists.newArrayList();
    }

    private String getTopTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.equals(getString(C5441R.string.pdStr10_29))) {
            return getString(C5441R.string.pdStr10_70);
        }
        if (str.equals(getString(C5441R.string.pdStr10_30))) {
            return getString(C5441R.string.pdStr10_71);
        }
        if (str.equals(getString(C5441R.string.pdStr10_31))) {
            return getString(C5441R.string.pdStr10_72);
        }
        if (str.equals(getString(C5441R.string.pdStr10_32))) {
            return getString(C5441R.string.pdStr10_73);
        }
        if (str.equals(getString(C5441R.string.pdStr10_33))) {
            return getString(C5441R.string.pdStr10_74);
        }
        if (str.equals(getString(C5441R.string.pdStr10_68))) {
            return getString(C5441R.string.pdStr10_75);
        }
        if (str.equals(getString(C5441R.string.pdStr10_76))) {
            return getString(C5441R.string.pdStr10_78);
        }
        if (str.equals(getString(C5441R.string.pdStr10_77))) {
            return getString(C5441R.string.pdStr10_79);
        }
        if (str.equals(getString(C5441R.string.pdStr10_84))) {
            return getString(C5441R.string.pdStr10_85);
        }
        if (str.equals(getString(C5441R.string.pdStr10_86))) {
            return getString(C5441R.string.pdStr10_87);
        }
        if (str.equals(getString(C5441R.string.pdStr10_88))) {
            return getString(C5441R.string.pdStr10_89);
        }
        return str.equals(getString(C5441R.string.pdStr10_82)) ? getString(C5441R.string.pdStr10_83) : "";
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initWidget() {
        this.mTopBar.setTitle(getTopTitle(this.playlistName));
        this.mMusicListRecyclerView.getRealRecyclerView().setHasFixedSize(true);
        this.mMusicListRecyclerView.getRealRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        this.mPlaylistInfoListAdapter = new PlaylistInfoListAdapter(this, this.mMedias, this.mSelectedMedias);
        CItemTouchHelper cItemTouchHelper = new CItemTouchHelper(this.mPlaylistInfoListAdapter, this.mMedias);
        cItemTouchHelper.setOnSelectedChangedCallback(new CItemTouchHelper.OnSelectedChangedCallback() { // from class: com.pudutech.mpmodule.-$$Lambda$PlaylistInfoEditActivity$Q5RYEicCjDST0ZM-lyhqNcvYheU
            @Override // com.pudutech.mpmodule.ui.widget.CItemTouchHelper.OnSelectedChangedCallback
            public final void onSelectedChanged() {
                PlaylistInfoEditActivity.this.lambda$initWidget$0$PlaylistInfoEditActivity();
            }
        });
        new ItemTouchHelper(cItemTouchHelper).attachToRecyclerView(this.mMusicListRecyclerView.getRealRecyclerView());
        this.mMusicListRecyclerView.getRealRecyclerView().setAdapter(this.mPlaylistInfoListAdapter);
        this.mMusicListRecyclerView.getRealRecyclerView().addItemDecoration(new DividerItemDecoration(this, 1));
        RecyclerViewAnimUtil.getInstance().closeDefaultAnimator(this.mMusicListRecyclerView.getRealRecyclerView());
        refreshWholeViewStatus();
        refreshDelBtnEnabled();
        this.mAddMusicText.getPaint().setFakeBoldText(true);
        this.mEditMusicText.getPaint().setFakeBoldText(true);
        this.mDeleteMusicText.getPaint().setFakeBoldText(true);
        this.mSelectAll.getPaint().setFakeBoldText(true);
        this.mResetAll.getPaint().setFakeBoldText(true);
    }

    public /* synthetic */ void lambda$initWidget$0$PlaylistInfoEditActivity() {
        DatabaseManagerFactory.getDatabaseManager().savePlaylist(this.mPlaylist);
        this.mPlaylistInfoListAdapter.notifyDataSetChanged();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setListeners() {
        this.mPlaylistInfoListAdapter.setOnItemClickListener(new PlaylistInfoListAdapter.OnItemClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.2
            @Override // com.pudutech.mpmodule.PlaylistInfoListAdapter.OnItemClickListener
            public void onItemClick(Media media, View view, int i) {
            }

            @Override // com.pudutech.mpmodule.PlaylistInfoListAdapter.OnItemClickListener
            public void onCheckedChanged(Media media, boolean z, int i) {
                PlaylistInfoEditActivity.this.refreshSelectedMedias(media, z, i);
                PlaylistInfoEditActivity.this.refreshDelBtnEnabled();
                if (PlaylistInfoEditActivity.this.mPlaylistInfoListAdapter.checkIsAllSelect()) {
                    PlaylistInfoEditActivity.this.mCheckAll.setChecked(true);
                } else {
                    PlaylistInfoEditActivity.this.mCheckAll.setChecked(false);
                }
            }

            @Override // com.pudutech.mpmodule.PlaylistInfoListAdapter.OnItemClickListener
            public void onPlayBtnClick(Media media, View view, int i) {
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "onPlayBtnClick...");
                Music music = new Music();
                music.setPath(media.getPath());
                MusicPlayerHelper.getInstance().switchSong(music);
            }

            @Override // com.pudutech.mpmodule.PlaylistInfoListAdapter.OnItemClickListener
            public void onPlayingMediaChange(int i) {
                PlaylistInfoListAdapter.CViewHolder cViewHolder = (PlaylistInfoListAdapter.CViewHolder) PlaylistInfoEditActivity.this.mMusicListRecyclerView.getRealRecyclerView().findViewHolderForAdapterPosition(i);
                Pdlog.m3273d(PlaylistInfoEditActivity.TAG, "onPlayPositionChange position: " + i + "viewholder: " + cViewHolder);
                if (cViewHolder == null) {
                    PlaylistInfoEditActivity.this.mPlaylistInfoListAdapter.notifyItemChanged(i);
                } else {
                    cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_play);
                }
            }
        });
        this.mMusicListRecyclerView.setOnBtnClickListener(new CStateRecyclerView.onBtnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.3
            @Override // com.pudutech.mpmodule.ui.widget.CStateRecyclerView.onBtnClickListener
            public void onBtnClick() {
                AddMusicActivity.actionActivity(PlaylistInfoEditActivity.this.getApplicationContext(), PlaylistInfoEditActivity.this.mPlaylist);
            }
        });
        this.mMusicListRecyclerView.setmViewEmptyCallback(new CStateRecyclerView.onViewEmptyCallback() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.4
            @Override // com.pudutech.mpmodule.ui.widget.CStateRecyclerView.onViewEmptyCallback
            public void onViewEmpty() {
                PlaylistInfoEditActivity.this.refreshWholeViewStatus();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWholeViewStatus() {
        if (Lists.isEmpty(this.mMedias)) {
            setTopWidgetVisibility(false);
            CStateRecyclerView cStateRecyclerView = this.mMusicListRecyclerView;
            if (cStateRecyclerView != null) {
                cStateRecyclerView.showEmpty(getString(C5441R.string.pdStr10_52), getString(C5441R.string.pdStr10_42));
                return;
            }
            return;
        }
        setTopWidgetVisibility(true);
        CStateRecyclerView cStateRecyclerView2 = this.mMusicListRecyclerView;
        if (cStateRecyclerView2 != null) {
            cStateRecyclerView2.showData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTopWidgetVisibility(boolean z) {
        if (z) {
            this.mBtachSelectRegion.setVisibility(4);
            this.mAddMusicBtn.setVisibility(0);
            this.mEditBtn.setVisibility(0);
            this.mDelCheckedBtn.setVisibility(4);
            return;
        }
        this.mBtachSelectRegion.setVisibility(4);
        this.mAddMusicBtn.setVisibility(4);
        this.mEditBtn.setVisibility(4);
        this.mDelCheckedBtn.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDelBtnEnabled() {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("mDelCheckedBtn refresh BtnEnabled ");
        sb.append(this.mSelectedMedias.size() > 0);
        objArr[0] = sb.toString();
        Pdlog.m3273d(TAG, objArr);
        this.mDelCheckedBtn.setEnabled(this.mSelectedMedias.size() > 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSelectedMedias(Media media, boolean z, int i) {
        if (z) {
            if (this.mSelectedMedias.contains(media)) {
                return;
            }
            this.mSelectedMedias.add(media);
            return;
        }
        this.mSelectedMedias.remove(media);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({2131427418, 2131427414, 2131427419, 2131427581})
    public void onClickListener(View view) {
        if (view.getId() == C5441R.id.btn_del_checked) {
            this.mDeleteDialog = new NotiDialog(this) { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.5
                @Override // com.pudutech.mpmodule.p060ui.NotiDialog
                public void setContent() {
                    TextView textView = (TextView) findViewById(C5441R.id.title_text);
                    PlaylistInfoEditActivity playlistInfoEditActivity = PlaylistInfoEditActivity.this;
                    int i = C5441R.string.pdStr10_46;
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(PlaylistInfoEditActivity.this.mSelectedMedias == null ? 0 : PlaylistInfoEditActivity.this.mSelectedMedias.size());
                    textView.setText(playlistInfoEditActivity.getString(i, objArr));
                }
            };
            this.mDeleteDialog.setContentView(C5441R.layout.module_dialog_content_delete_confirm);
            this.mDeleteDialog.addClickCallBack(new NotiDialog.ClickCallBack() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.6
                @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                public void onDeny() {
                    PlaylistInfoEditActivity.this.mDeleteDialog.dismiss();
                }

                @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                public void onConfirm() {
                    PlaylistInfoEditActivity.this.resetPlaybackRecords();
                    PlaylistInfoEditActivity.this.mPlaylistInfoListAdapter.notifyItemsRemoved();
                    PlaylistInfoEditActivity.this.mSelectedMedias.clear();
                    PlaylistInfoEditActivity.this.refreshDelBtnEnabled();
                    Toast.makeText(PlaylistInfoEditActivity.this.getApplicationContext(), PlaylistInfoEditActivity.this.getString(C5441R.string.pdStr10_38), 0).show();
                    DatabaseManagerFactory.getDatabaseManager().savePlaylist(PlaylistInfoEditActivity.this.mPlaylist);
                    PlaylistInfoEditActivity.this.mDeleteDialog.dismiss();
                    PlaylistInfoEditActivity.this.mCheckAll.setOnCheckedChangeListener(null);
                    PlaylistInfoEditActivity.this.mCheckAll.setChecked(false);
                    PlaylistInfoEditActivity.this.mPlaylistInfoListAdapter.switchToEditMode(false);
                    if (Lists.isEmpty(PlaylistInfoEditActivity.this.mMedias)) {
                        return;
                    }
                    PlaylistInfoEditActivity.this.setTopWidgetVisibility(true);
                }
            });
            this.mDeleteDialog.setContent();
            this.mDeleteDialog.show();
            return;
        }
        if (view.getId() == C5441R.id.btn_add_music) {
            AddMusicActivity.actionActivity(this, this.mPlaylist);
            return;
        }
        if (view.getId() == C5441R.id.btn_edit_list) {
            this.mCheckAll.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoEditActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (PlaylistInfoEditActivity.this.mCheckAll.isChecked()) {
                        PlaylistInfoEditActivity.this.mPlaylistInfoListAdapter.changeListCheckedMode(11);
                    } else {
                        PlaylistInfoEditActivity.this.mPlaylistInfoListAdapter.changeListCheckedMode(22);
                    }
                    PlaylistInfoEditActivity.this.refreshDelBtnEnabled();
                }
            });
            this.mPlaylistInfoListAdapter.switchToEditMode(true);
            this.mEditBtn.setVisibility(4);
            this.mDelCheckedBtn.setVisibility(0);
            this.mBtachSelectRegion.setVisibility(0);
            MusicPlayerHelper.getInstance().pausePlay();
            MusicPlayerHelper.getInstance().reset();
            this.mPlaylistInfoListAdapter.clearPlayingMediaStatus();
            return;
        }
        if (view.getId() == C5441R.id.reset_to_normal_text) {
            this.mCheckAll.setOnCheckedChangeListener(null);
            this.mCheckAll.setChecked(false);
            this.mSelectedMedias.clear();
            refreshDelBtnEnabled();
            this.mPlaylistInfoListAdapter.switchToEditMode(false);
            this.mEditBtn.setVisibility(0);
            this.mDelCheckedBtn.setVisibility(4);
            this.mBtachSelectRegion.setVisibility(4);
        }
    }

    private Media findNextPlayMusic(Iterator<Media> it, int i, PreviousPlayBean previousPlayBean, boolean z) {
        if (!it.hasNext()) {
            return null;
        }
        Media next = it.next();
        if (i >= this.mSelectedMedias.size()) {
            if (z) {
                return next;
            }
            return null;
        }
        Media media = this.mSelectedMedias.get(i);
        if (!next.equals(this.mSelectedMedias.get(i))) {
            return z ? next : findNextPlayMusic(it, i, previousPlayBean, z);
        }
        it.remove();
        boolean z2 = true;
        int i2 = i + 1;
        if (!z && !previousPlayBean.getPlayedId().equals(media.getId())) {
            z2 = false;
        }
        return findNextPlayMusic(it, i2, previousPlayBean, z2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mpmodule.p060ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        MusicPlayerHelper.getInstance().pausePlay();
        MusicPlayerHelper.getInstance().reset();
        this.mPlaylistInfoListAdapter.clearPlayingMediaStatus();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void onRelease() {
        MusicPlayerHelper.getInstance().pausePlay();
        MusicPlayerHelper.getInstance().reset();
        CEventCenter.unregisterEventListener(this, Events.EVENT_ADD_MUSIC);
        NotiDialog notiDialog = this.mDeleteDialog;
        if (notiDialog == null || !notiDialog.isShowing()) {
            return;
        }
        this.mDeleteDialog.dismiss();
        this.mDeleteDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPlaybackRecords() {
        Media findNextPlayMusic;
        PreviousPlayBean queryPreviousPlayByModeId = DatabaseManagerFactory.getDatabaseManager().queryPreviousPlayByModeId(this.mPlaylist.getDefaultMode().getId());
        if (queryPreviousPlayByModeId == null || (findNextPlayMusic = findNextPlayMusic(new ArrayDeque(this.mPlaylist.getMediaList()).iterator(), 0, queryPreviousPlayByModeId, false)) == null) {
            return;
        }
        queryPreviousPlayByModeId.setPlayedId(findNextPlayMusic.getId());
        queryPreviousPlayByModeId.setSeekTime(0L);
        DatabaseManagerFactory.getDatabaseManager().insertOrReplacePreviousPlay(queryPreviousPlayByModeId);
    }

    @Override // com.freddy.event.I_CEventListener
    public void onCEvent(String str, int i, int i2, Object obj) {
        if (StringUtil.equals(str, Events.EVENT_ADD_MUSIC)) {
            this.mMedias.addAll(0, ((AddMusicEventObj) obj).getMedias());
            this.mPlaylist.setMediaList(this.mMedias);
            DatabaseManagerFactory.getDatabaseManager().savePlaylist(this.mPlaylist);
            this.mPlaylistInfoListAdapter.notifyDataSetChanged();
            refreshWholeViewStatus();
            DatabaseManagerFactory.getDatabaseManager().deletePreviousPlayByModeId(this.mPlaylist.getDefaultMode().getId());
        }
    }
}
