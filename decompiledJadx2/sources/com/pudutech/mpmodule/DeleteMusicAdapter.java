package com.pudutech.mpmodule;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.DatabaseManagerFactory;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.utils.Lists;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class DeleteMusicAdapter extends RecyclerView.Adapter<CViewHolder> {
    public static final int SELECT_ALL = 11;
    public static final int SELECT_NONE = 22;
    private static final String TAG = "DeleteMusicAdapter";
    public static final int TYPE_EDIT = 2;
    public static final int TYPE_NORMAL = 1;
    private Context mContext;
    private List<Media> mMedias;
    private OnItemClickListener mOnItemClickListener;
    private List<Media> mSelectedMedias;
    private int mCurrentType = 1;
    private int mCurrentSelectMode = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnItemClickListener {
        void onCheckedChanged(Media media, boolean z, int i);
    }

    public DeleteMusicAdapter(Context context, List<Media> list, List<Media> list2) {
        this.mContext = context;
        this.mMedias = list;
        this.mSelectedMedias = list2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mCurrentType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.mContext).inflate(C5441R.layout.module_musicplayer_item_delete_music_info, viewGroup, false);
        CheckBox checkBox = (CheckBox) inflate.findViewById(C5441R.id.music_checkbox);
        if (i == 2) {
            checkBox.setVisibility(0);
        } else {
            checkBox.setVisibility(4);
        }
        return new CViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final CViewHolder cViewHolder, final int i) {
        final Media media = this.mMedias.get(i);
        cViewHolder.musicCheckBox.setChecked(isSelected(media));
        cViewHolder.msicNumber.setText(String.valueOf(i + 1));
        cViewHolder.musicNameTextView.setText(media.getFileName());
        String artist = TextUtils.isEmpty(media.getArtist()) ? "<unknown>" : media.getArtist();
        String title = TextUtils.isEmpty(media.getTitle()) ? "<unknown>" : media.getTitle();
        StringBuilder sb = new StringBuilder(artist);
        sb.append(" - ");
        sb.append(title);
        cViewHolder.musicInfoTextView.setText(sb);
        cViewHolder.musicCheckBox.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.DeleteMusicAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DeleteMusicAdapter.this.mOnItemClickListener != null) {
                    DeleteMusicAdapter.this.mOnItemClickListener.onCheckedChanged(media, cViewHolder.musicCheckBox.isChecked(), i);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (Lists.isEmpty(this.mMedias)) {
            return 0;
        }
        return this.mMedias.size();
    }

    private boolean isSelected(Media media) {
        return this.mSelectedMedias.contains(media);
    }

    public void changeListCheckedMode(int i) {
        this.mCurrentSelectMode = i;
        int i2 = this.mCurrentSelectMode;
        if (i2 == 11) {
            this.mSelectedMedias.clear();
            this.mSelectedMedias.addAll(this.mMedias);
        } else if (i2 == 22) {
            this.mSelectedMedias.clear();
        }
        notifyDataSetChanged();
    }

    public void switchToEditMode(boolean z) {
        if (getItemCount() > 0) {
            if (z) {
                if (this.mCurrentType != 2) {
                    this.mCurrentType = 2;
                    notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (this.mCurrentType != 1) {
                this.mCurrentType = 1;
                notifyDataSetChanged();
            }
        }
    }

    public void notifyItemsRemoved() {
        if (this.mSelectedMedias.size() == 0) {
            return;
        }
        HashMap hashMap = new HashMap();
        List<Playlist> queryAllPlaylists = DatabaseManagerFactory.getDatabaseManager().queryAllPlaylists();
        if (queryAllPlaylists != null && !queryAllPlaylists.isEmpty()) {
            for (int i = 0; i < queryAllPlaylists.size(); i++) {
                hashMap.put(Integer.valueOf(i), queryAllPlaylists.get(i));
            }
        }
        for (Media media : this.mSelectedMedias) {
            File file = new File(media.getPath());
            try {
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!hashMap.isEmpty()) {
                for (Integer num : hashMap.keySet()) {
                    Playlist playlist = (Playlist) hashMap.get(num);
                    List<Media> mediaList = playlist.getMediaList();
                    mediaList.remove(media);
                    playlist.setMediaList(mediaList);
                    hashMap.put(num, playlist);
                }
            }
            int indexOf = this.mMedias.indexOf(media);
            this.mMedias.remove(indexOf);
            notifyItemRemoved(indexOf);
            notifyItemRangeChanged(indexOf, this.mMedias.size() - indexOf);
        }
        DatabaseManagerFactory.getDatabaseManager().savePlaylist(new ArrayList(hashMap.values()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public final class CViewHolder extends RecyclerView.ViewHolder {
        TextView msicNumber;
        CheckBox musicCheckBox;
        TextView musicInfoTextView;
        TextView musicNameTextView;

        CViewHolder(View view) {
            super(view);
            this.musicCheckBox = (CheckBox) view.findViewById(C5441R.id.music_checkbox);
            this.musicNameTextView = (TextView) view.findViewById(C5441R.id.tv_music_name);
            this.musicInfoTextView = (TextView) view.findViewById(C5441R.id.tv_music_info);
            this.msicNumber = (TextView) view.findViewById(C5441R.id.tv_song_number);
        }
    }

    public boolean checkIsAllSelect() {
        return this.mSelectedMedias.size() == this.mMedias.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
