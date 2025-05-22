package com.pudutech.mpmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.utils.Lists;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlaylistListAdapter extends RecyclerView.Adapter<CViewHolder> {
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<Playlist> mPlaylists;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnItemClickListener {
        void onAddBtnClick(View view);

        void onItemClick(Playlist playlist, View view, int i);
    }

    public PlaylistListAdapter(Context context, List<Playlist> list) {
        this.mContext = context;
        this.mPlaylists = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CViewHolder(LayoutInflater.from(this.mContext).inflate(C5441R.layout.module_musicplayer_item_playlist, viewGroup, false), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final CViewHolder cViewHolder, final int i) {
        if (cViewHolder.viewType == 0) {
            final Playlist playlist = this.mPlaylists.get(i);
            cViewHolder.playListNumber.setText(String.valueOf(i + 1));
            Pdlog.m3273d("PDD2", playlist.toString());
            cViewHolder.listNameView.setText(playlist.getListName());
            cViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.-$$Lambda$PlaylistListAdapter$Gqd6ahrrxf6IFnHpSM09FqRIXPM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlaylistListAdapter.this.lambda$onBindViewHolder$0$PlaylistListAdapter(playlist, cViewHolder, i, view);
                }
            });
            return;
        }
        cViewHolder.listNameView.setText("+");
        cViewHolder.listNameView.setTextColor(-1);
        cViewHolder.listNameView.setTextSize(2, 128.0f);
        cViewHolder.listNameView.getPaint().setFakeBoldText(true);
        cViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.-$$Lambda$PlaylistListAdapter$s9RXGcxkyyrkpbBIYgiAutYoYpg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaylistListAdapter.this.lambda$onBindViewHolder$1$PlaylistListAdapter(cViewHolder, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$PlaylistListAdapter(Playlist playlist, CViewHolder cViewHolder, int i, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(playlist, cViewHolder.itemView, i);
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$PlaylistListAdapter(CViewHolder cViewHolder, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onAddBtnClick(cViewHolder.itemView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (Lists.isEmpty(this.mPlaylists)) {
            return 0;
        }
        return this.mPlaylists.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i < this.mPlaylists.size() ? 0 : 1;
    }

    public void addPlaylist(Playlist playlist) {
        if (playlist == null) {
            return;
        }
        this.mPlaylists.add(playlist);
        notifyItemInserted(this.mPlaylists.size() - 1);
    }

    public void removePlaylist(Playlist playlist) {
        int indexOf;
        if (playlist == null || (indexOf = this.mPlaylists.indexOf(playlist)) == -1) {
            return;
        }
        this.mPlaylists.remove(indexOf);
        notifyItemRemoved(indexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public final class CViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemWrapView;
        TextView listNameView;
        TextView playListNumber;
        int viewType;

        public CViewHolder(View view, int i) {
            super(view);
            this.viewType = i;
            this.itemWrapView = (RelativeLayout) view.findViewById(C5441R.id.cv_item_wrap);
            this.listNameView = (TextView) view.findViewById(C5441R.id.tv_play_mode_name);
            this.playListNumber = (TextView) view.findViewById(C5441R.id.tv_play_mode_number);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
