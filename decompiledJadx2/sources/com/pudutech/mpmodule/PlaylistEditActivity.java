package com.pudutech.mpmodule;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.pudutech.mpmodule.InputPlaylistNameDialogFragment;
import com.pudutech.mpmodule.PlaylistListAdapter;
import com.pudutech.mpmodule.bean.PlayMode;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.p060ui.BaseActivity;
import com.pudutech.mpmodule.utils.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlaylistEditActivity extends BaseActivity {
    private PlaylistListAdapter mPlaylistListAdapter;

    @BindView(2131427586)
    RecyclerView mPlaylistRecyclerView;
    private List<Playlist> mPlaylists;

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setRootView() {
        setContentView(C5441R.layout.module_musicplayer_activity_playlist_edit);
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initData() {
        this.mPlaylists = DatabaseManagerFactory.getDatabaseManager().queryAllPlaylists();
        final List<PlayMode> queryAllPlayModes = DatabaseManagerFactory.getDatabaseManager().queryAllPlayModes();
        Collections.sort(this.mPlaylists, new Comparator<Playlist>() { // from class: com.pudutech.mpmodule.PlaylistEditActivity.1
            @Override // java.util.Comparator
            public int compare(Playlist playlist, Playlist playlist2) {
                int i = 0;
                while (true) {
                    if (i >= queryAllPlayModes.size()) {
                        i = 0;
                        break;
                    }
                    if (TextUtils.equals(((PlayMode) queryAllPlayModes.get(i)).getModeId(), playlist.getDefaultMode().getId())) {
                        break;
                    }
                    i++;
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= queryAllPlayModes.size()) {
                        i2 = 0;
                        break;
                    }
                    if (TextUtils.equals(((PlayMode) queryAllPlayModes.get(i2)).getModeId(), playlist2.getDefaultMode().getId())) {
                        break;
                    }
                    i2++;
                }
                return i - i2;
            }
        });
        if (this.mPlaylists == null) {
            this.mPlaylists = Lists.newArrayList();
        }
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void initWidget() {
        this.mPlaylistRecyclerView.setHasFixedSize(true);
        this.mPlaylistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mPlaylistListAdapter = new PlaylistListAdapter(this, this.mPlaylists);
        this.mPlaylistRecyclerView.setAdapter(this.mPlaylistListAdapter);
        this.mPlaylistRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
    }

    @Override // com.pudutech.mpmodule.p060ui.BaseActivity
    protected void setListeners() {
        this.mPlaylistListAdapter.setOnItemClickListener(new PlaylistListAdapter.OnItemClickListener() { // from class: com.pudutech.mpmodule.PlaylistEditActivity.2
            @Override // com.pudutech.mpmodule.PlaylistListAdapter.OnItemClickListener
            public void onItemClick(Playlist playlist, View view, int i) {
                PlaylistInfoEditActivity.actionActivity(PlaylistEditActivity.this, playlist.getListId(), playlist.getListName());
            }

            @Override // com.pudutech.mpmodule.PlaylistListAdapter.OnItemClickListener
            public void onAddBtnClick(View view) {
                PlaylistEditActivity.this.showInputPlaylistNameDialogFragmet();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInputPlaylistNameDialogFragmet() {
        InputPlaylistNameDialogFragment inputPlaylistNameDialogFragment = new InputPlaylistNameDialogFragment();
        inputPlaylistNameDialogFragment.setOnConfirmInputListener(new InputPlaylistNameDialogFragment.OnConfirmInputListener() { // from class: com.pudutech.mpmodule.-$$Lambda$PlaylistEditActivity$OrJaE0HZKt2FNrbsAvPxAiRtMD0
            @Override // com.pudutech.mpmodule.InputPlaylistNameDialogFragment.OnConfirmInputListener
            public final void onConfirmInput(String str) {
                PlaylistEditActivity.this.lambda$showInputPlaylistNameDialogFragmet$0$PlaylistEditActivity(str);
            }
        });
        inputPlaylistNameDialogFragment.show(getSupportFragmentManager(), PlaylistEditActivity.class.getSimpleName());
    }

    public /* synthetic */ void lambda$showInputPlaylistNameDialogFragmet$0$PlaylistEditActivity(String str) {
        Playlist playlist = new Playlist(UUID.randomUUID().toString(), str, true);
        this.mPlaylistListAdapter.addPlaylist(playlist);
        DatabaseManagerFactory.getDatabaseManager().savePlaylist(playlist);
        Toast.makeText(this, getString(C5441R.string.pdStr10_35), 0).show();
    }
}
