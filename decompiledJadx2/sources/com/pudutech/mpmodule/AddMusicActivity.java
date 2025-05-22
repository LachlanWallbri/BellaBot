package com.pudutech.mpmodule;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.OnClick;
import com.freddy.event.CEventCenter;
import com.pudutech.base.Pdlog;
import com.pudutech.importmusic.ImportMusicActivity;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpmodule.MusicFileListAdapter;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.event.Events;
import com.pudutech.mpmodule.event.obj.AddMusicEventObj;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.media.MediaLoader;
import com.pudutech.mpmodule.p060ui.BaseActivity;
import com.pudutech.mpmodule.p060ui.NotiDialog;
import com.pudutech.mpmodule.p060ui.widget.CStateRecyclerView;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.RecyclerViewAnimUtil;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class AddMusicActivity extends BaseActivity {

    @BindView(2131427394)
    TextView mAddMusicConfirm;

    @BindView(2131427580)
    CStateRecyclerView mFileListRecyclerView;
    private CountDownTimer mLoadingTimer = null;
    private MediaLoader mMediaLoader = null;
    private List<Media> mMedias;
    private MusicFileListAdapter mMusicFileListAdapter;
    private NotiDialog mNotiDialog;
    private Playlist mPlaylist;
    private List<Media> mSelectedMedias;

    public static void actionActivity(Context context, Playlist playlist) {
        Intent intent = new Intent(context, (Class<?>) AddMusicActivity.class);
        intent.putExtra(Playlist.KEY_PLAYLIST, playlist);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setRootView() {
        setContentView(C5441R.layout.module_musicplayer_activity_add_music);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initData() {
        this.mMedias = Lists.newArrayList();
        this.mSelectedMedias = Lists.newArrayList();
        this.mPlaylist = (Playlist) getIntent().getSerializableExtra(Playlist.KEY_PLAYLIST);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initWidget() {
        this.mFileListRecyclerView.getRealRecyclerView().setHasFixedSize(true);
        this.mFileListRecyclerView.getRealRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        this.mMusicFileListAdapter = new MusicFileListAdapter(this, this.mMedias, this.mSelectedMedias);
        this.mFileListRecyclerView.getRealRecyclerView().setAdapter(this.mMusicFileListAdapter);
        this.mFileListRecyclerView.getRealRecyclerView().addItemDecoration(new DividerItemDecoration(this, 1));
        RecyclerViewAnimUtil.getInstance().closeDefaultAnimator(this.mFileListRecyclerView.getRealRecyclerView());
        this.mAddMusicConfirm.getPaint().setFakeBoldText(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnClick({2131427417})
    public void onClickListener(View view) {
        if (view.getId() != C5441R.id.btn_confirm_inactivity || this.mPlaylist == null) {
            return;
        }
        addMediaToPlaylist(this.mSelectedMedias);
        resetSelectedStatus();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        requestData();
    }

    protected void requestData() {
        MediaLoader mediaLoader = this.mMediaLoader;
        if (mediaLoader != null) {
            mediaLoader.release();
        }
        this.mMediaLoader = new MediaLoader(getApplicationContext());
        this.mMediaLoader.loadMedias(new C54011());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* renamed from: com.pudutech.mpmodule.AddMusicActivity$1 */
    /* loaded from: classes6.dex */
    public class C54011 implements MediaLoader.OnMediaLoadListener {
        C54011() {
        }

        @Override // com.pudutech.mpmodule.media.MediaLoader.OnMediaLoadListener
        public void onLoadStart() {
            AddMusicActivity addMusicActivity = AddMusicActivity.this;
            addMusicActivity.mNotiDialog = new NotiDialog(addMusicActivity);
            AddMusicActivity.this.mNotiDialog.setContentView(C5441R.layout.module_dialog_load_process_layout);
            AddMusicActivity.this.mNotiDialog.show();
            if (AddMusicActivity.this.mLoadingTimer != null) {
                AddMusicActivity.this.mLoadingTimer.cancel();
            }
            AddMusicActivity.this.mLoadingTimer = new CountDownTimer(20000L, 30000L) { // from class: com.pudutech.mpmodule.AddMusicActivity.1.1
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    Pdlog.m3273d(AddMusicActivity.this.TAG, "Loading File over time, show tip dialog.");
                    if (AddMusicActivity.this.isFinishing() || AddMusicActivity.this.isDestroyed()) {
                        Pdlog.m3273d(AddMusicActivity.this.TAG, "Activity is finishing, ignore load file over time call back");
                        return;
                    }
                    if (AddMusicActivity.this.mNotiDialog != null && AddMusicActivity.this.mNotiDialog.isShowing()) {
                        AddMusicActivity.this.mNotiDialog.dismiss();
                        AddMusicActivity.this.mNotiDialog = null;
                    }
                    if (AddMusicActivity.this.mMediaLoader != null) {
                        AddMusicActivity.this.mMediaLoader.release();
                    }
                    AddMusicActivity.this.mNotiDialog = new NotiDialog(AddMusicActivity.this);
                    AddMusicActivity.this.mNotiDialog.setContentView(C5441R.layout.module_dialog_content_load_file_overtime_layout);
                    AddMusicActivity.this.mNotiDialog.addClickCallBack(new NotiDialog.ClickCallBack() { // from class: com.pudutech.mpmodule.AddMusicActivity.1.1.1
                        @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                        public void onDeny() {
                        }

                        @Override // com.pudutech.mpmodule.ui.NotiDialog.ClickCallBack
                        public void onConfirm() {
                            AddMusicActivity.this.mNotiDialog.dismiss();
                            AddMusicActivity.this.finish();
                        }
                    });
                    AddMusicActivity.this.mNotiDialog.show();
                }
            };
            AddMusicActivity.this.mLoadingTimer.start();
        }

        @Override // com.pudutech.mpmodule.media.MediaLoader.OnMediaLoadListener
        public void onLoadSuccessful(List<Media> list) {
            if (AddMusicActivity.this.mLoadingTimer != null) {
                AddMusicActivity.this.mLoadingTimer.cancel();
            }
            if (AddMusicActivity.this.isFinishing() || AddMusicActivity.this.isDestroyed()) {
                Pdlog.m3273d(AddMusicActivity.this.TAG, "Activity has been destroied, ignore load music result");
                return;
            }
            if (AddMusicActivity.this.mNotiDialog != null && AddMusicActivity.this.mNotiDialog.isShowing()) {
                AddMusicActivity.this.mNotiDialog.dismiss();
                AddMusicActivity.this.mNotiDialog = null;
            }
            Pdlog.m3273d(AddMusicActivity.this.TAG, "load native songs complete...");
            if (Lists.isNotEmpty(list)) {
                AddMusicActivity.this.mMedias.clear();
                AddMusicActivity.this.mMedias.addAll(list);
                AddMusicActivity.this.mMusicFileListAdapter.notifyDataSetChanged();
                if (AddMusicActivity.this.mFileListRecyclerView != null) {
                    AddMusicActivity.this.mFileListRecyclerView.showData();
                    Pdlog.m3273d(AddMusicActivity.this.TAG, "show loaded list");
                    return;
                }
                return;
            }
            if (AddMusicActivity.this.mFileListRecyclerView != null) {
                AddMusicActivity.this.mFileListRecyclerView.showEmpty(AddMusicActivity.this.getString(C5441R.string.pdStr10_52), AddMusicActivity.this.getString(C5441R.string.pdStr10_54));
                Pdlog.m3273d(AddMusicActivity.this.TAG, "loaded list is null");
            }
        }

        @Override // com.pudutech.mpmodule.media.MediaLoader.OnMediaLoadListener
        public void onLoadFinished() {
            if (AddMusicActivity.this.mMediaLoader != null) {
                AddMusicActivity.this.mMediaLoader.release();
            }
        }
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setListeners() {
        this.mMusicFileListAdapter.setOnItemClickListener(new MusicFileListAdapter.OnItemClickListener() { // from class: com.pudutech.mpmodule.AddMusicActivity.2
            @Override // com.pudutech.mpmodule.MusicFileListAdapter.OnItemClickListener
            public void onPlusBtnClick(Media media, View view, int i) {
            }

            @Override // com.pudutech.mpmodule.MusicFileListAdapter.OnItemClickListener
            public void onItemClick(Media media, View view, int i) {
                AddMusicActivity.this.refreshSelectedMedias(media, i);
            }

            @Override // com.pudutech.mpmodule.MusicFileListAdapter.OnItemClickListener
            public void onPlayBtnClick(Media media, View view, int i) {
                Pdlog.m3273d(AddMusicActivity.this.TAG, "onPlayBtnClick...");
                Music music = new Music();
                music.setPath(media.getPath());
                MusicPlayerHelper.getInstance().switchSong(music);
            }

            @Override // com.pudutech.mpmodule.MusicFileListAdapter.OnItemClickListener
            public void onPlayingMediaChange(int i) {
                MusicFileListAdapter.CViewHolder cViewHolder = (MusicFileListAdapter.CViewHolder) AddMusicActivity.this.mFileListRecyclerView.getRealRecyclerView().findViewHolderForAdapterPosition(i);
                Pdlog.m3273d(AddMusicActivity.this.TAG, "onPlayPositionChange position: " + i + "viewholder: " + cViewHolder);
                if (cViewHolder == null) {
                    AddMusicActivity.this.mMusicFileListAdapter.notifyItemChanged(i);
                } else {
                    cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_play);
                }
            }
        });
        this.mFileListRecyclerView.setOnBtnClickListener(new CStateRecyclerView.onBtnClickListener() { // from class: com.pudutech.mpmodule.AddMusicActivity.3
            @Override // com.pudutech.mpmodule.ui.widget.CStateRecyclerView.onBtnClickListener
            public void onBtnClick() {
                AddMusicActivity addMusicActivity = AddMusicActivity.this;
                addMusicActivity.startActivity(new Intent(addMusicActivity, (Class<?>) ImportMusicActivity.class));
            }
        });
        this.mFileListRecyclerView.setmViewEmptyCallback(new CStateRecyclerView.onViewEmptyCallback() { // from class: com.pudutech.mpmodule.AddMusicActivity.4
            @Override // com.pudutech.mpmodule.ui.widget.CStateRecyclerView.onViewEmptyCallback
            public void onViewEmpty() {
                if (AddMusicActivity.this.mFileListRecyclerView != null) {
                    AddMusicActivity.this.mFileListRecyclerView.showEmpty(AddMusicActivity.this.getString(C5441R.string.pdStr10_52), AddMusicActivity.this.getString(C5441R.string.pdStr10_54));
                }
            }
        });
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        MusicPlayerHelper.getInstance().pausePlay();
        MusicPlayerHelper.getInstance().reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSelectedMedias(Media media, int i) {
        if (this.mSelectedMedias.contains(media)) {
            this.mSelectedMedias.remove(media);
        } else {
            this.mSelectedMedias.add(media);
        }
        this.mMusicFileListAdapter.notifyItemChanged(i);
    }

    private void addMediaToPlaylist(List<Media> list) {
        if (Lists.isEmpty(list)) {
            Toasty.warning(getApplicationContext(), getString(C5441R.string.pdStr10_26)).show();
            return;
        }
        List<Media> mediaList = this.mPlaylist.getMediaList();
        ArrayList arrayList = new ArrayList(list);
        arrayList.retainAll(mediaList);
        list.removeAll(arrayList);
        mediaList.addAll(0, list);
        this.mPlaylist.setMediaList(mediaList);
        DatabaseManagerFactory.getDatabaseManager().savePlaylist(this.mPlaylist);
        finish();
        AddMusicEventObj addMusicEventObj = new AddMusicEventObj();
        addMusicEventObj.setMedias(list);
        CEventCenter.dispatchEvent(Events.EVENT_ADD_MUSIC, 0, 0, addMusicEventObj);
    }

    private void resetSelectedStatus() {
        this.mMusicFileListAdapter.notifyDataSetChanged();
        this.mSelectedMedias.clear();
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void onRelease() {
        MusicPlayerHelper.getInstance().pausePlay();
        MusicPlayerHelper.getInstance().reset();
        CountDownTimer countDownTimer = this.mLoadingTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        MediaLoader mediaLoader = this.mMediaLoader;
        if (mediaLoader != null) {
            mediaLoader.release();
        }
        NotiDialog notiDialog = this.mNotiDialog;
        if (notiDialog == null || !notiDialog.isShowing()) {
            return;
        }
        this.mNotiDialog.setOnDismissListener(null);
        this.mNotiDialog.dismiss();
        this.mNotiDialog = null;
    }
}
