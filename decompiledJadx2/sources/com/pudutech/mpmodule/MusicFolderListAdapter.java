package com.pudutech.mpmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.mpmodule.media.MediaFolder;
import com.pudutech.mpmodule.utils.Lists;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MusicFolderListAdapter extends RecyclerView.Adapter<CViewHolder> {
    private Context mContext;
    private List<MediaFolder> mMediaFolders;
    private OnItemClickListener mOnItemClickListener;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    interface OnItemClickListener {
        void onItemClick(MediaFolder mediaFolder, View view, int i);
    }

    public MusicFolderListAdapter(Context context, List<MediaFolder> list) {
        this.mContext = context;
        this.mMediaFolders = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CViewHolder(LayoutInflater.from(this.mContext).inflate(C5441R.layout.module_musicplayer_item_music_folder, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final CViewHolder cViewHolder, final int i) {
        final MediaFolder mediaFolder = this.mMediaFolders.get(i);
        cViewHolder.folderNameTextView.setText(mediaFolder.getName());
        cViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.-$$Lambda$MusicFolderListAdapter$OXbRL-kxC2nRY-XVXzKMJlrEuS8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicFolderListAdapter.this.lambda$onBindViewHolder$0$MusicFolderListAdapter(mediaFolder, cViewHolder, i, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$MusicFolderListAdapter(MediaFolder mediaFolder, CViewHolder cViewHolder, int i, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(mediaFolder, cViewHolder.itemView, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (Lists.isEmpty(this.mMediaFolders)) {
            return 0;
        }
        return this.mMediaFolders.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public final class CViewHolder extends RecyclerView.ViewHolder {
        TextView folderNameTextView;

        CViewHolder(View view) {
            super(view);
            this.folderNameTextView = (TextView) view.findViewById(C5441R.id.tv_folder_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
