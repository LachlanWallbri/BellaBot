package com.pudutech.mpmodule;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlaylistEditActivity_ViewBinding implements Unbinder {
    private PlaylistEditActivity target;

    public PlaylistEditActivity_ViewBinding(PlaylistEditActivity playlistEditActivity) {
        this(playlistEditActivity, playlistEditActivity.getWindow().getDecorView());
    }

    public PlaylistEditActivity_ViewBinding(PlaylistEditActivity playlistEditActivity, View view) {
        this.target = playlistEditActivity;
        playlistEditActivity.mPlaylistRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, C5441R.id.rv_playlist, "field 'mPlaylistRecyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PlaylistEditActivity playlistEditActivity = this.target;
        if (playlistEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        playlistEditActivity.mPlaylistRecyclerView = null;
    }
}
