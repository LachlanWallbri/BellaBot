package com.pudutech.mpmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.utils.Lists;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlaylistInfoListAdapter extends RecyclerView.Adapter<CViewHolder> {
    public static final int SELECT_ALL = 11;
    public static final int SELECT_NONE = 22;
    private static final String TAG = "PlaylistInfoListAdapter";
    public static final int TYPE_EDIT = 2;
    public static final int TYPE_NORMAL = 1;
    private Context mContext;
    private List<Media> mMedias;
    private OnItemClickListener mOnItemClickListener;
    private List<Media> mSelectedMedias;
    private int mCurrentType = 1;
    private int mCurrentSelectMode = 0;
    private Media mcurrentPlayingMedia = null;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    interface OnItemClickListener {
        void onCheckedChanged(Media media, boolean z, int i);

        void onItemClick(Media media, View view, int i);

        void onPlayBtnClick(Media media, View view, int i);

        void onPlayingMediaChange(int i);
    }

    public PlaylistInfoListAdapter(Context context, List<Media> list, List<Media> list2) {
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
        View inflate = LayoutInflater.from(this.mContext).inflate(C5441R.layout.module_musicplayer_item_playlist_info, viewGroup, false);
        CheckBox checkBox = (CheckBox) inflate.findViewById(C5441R.id.music_checkbox);
        ImageButton imageButton = (ImageButton) inflate.findViewById(C5441R.id.tv_play_icon);
        if (i == 2) {
            checkBox.setVisibility(0);
            imageButton.setVisibility(4);
        } else {
            checkBox.setVisibility(4);
            imageButton.setVisibility(0);
        }
        return new CViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final CViewHolder cViewHolder, final int i) {
        final Media media = this.mMedias.get(i);
        cViewHolder.musicCheckBox.setChecked(isSelected(media));
        cViewHolder.msicNumber.setText(String.valueOf(i + 1));
        cViewHolder.musicNameTextView.setText(media.getFileName());
        StringBuilder sb = new StringBuilder(media.getArtist());
        sb.append(" - ");
        sb.append(media.getTitle());
        cViewHolder.musicInfoTextView.setText(sb);
        if (cViewHolder.playBtn.getDrawable().getConstantState() != cViewHolder.playBtn.getContext().getDrawable(C5441R.drawable.module_icon_play).getConstantState()) {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_play);
        }
        Media media2 = this.mcurrentPlayingMedia;
        if (media2 != null && media == media2 && cViewHolder.playBtn.getDrawable().getConstantState() != cViewHolder.playBtn.getContext().getDrawable(C5441R.drawable.module_icon_pause).getConstantState()) {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_pause);
        }
        cViewHolder.musicCheckBox.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.PlaylistInfoListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PlaylistInfoListAdapter.this.mOnItemClickListener != null) {
                    PlaylistInfoListAdapter.this.mOnItemClickListener.onCheckedChanged(media, cViewHolder.musicCheckBox.isChecked(), i);
                }
            }
        });
        cViewHolder.playBtn.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.-$$Lambda$PlaylistInfoListAdapter$xXNwLnMiJP7r52sN9QoochWTtDk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaylistInfoListAdapter.this.lambda$onBindViewHolder$0$PlaylistInfoListAdapter(media, cViewHolder, i, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$PlaylistInfoListAdapter(Media media, CViewHolder cViewHolder, int i, View view) {
        List<Media> list;
        int indexOf;
        OnItemClickListener onItemClickListener;
        OnItemClickListener onItemClickListener2 = this.mOnItemClickListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onPlayBtnClick(media, cViewHolder.playBtn, i);
        }
        Media media2 = this.mcurrentPlayingMedia;
        if (media2 != null && (list = this.mMedias) != null && (indexOf = list.indexOf(media2)) != -1 && indexOf != i && (onItemClickListener = this.mOnItemClickListener) != null) {
            onItemClickListener.onPlayingMediaChange(indexOf);
        }
        if (cViewHolder.playBtn.getDrawable().getConstantState() == cViewHolder.playBtn.getContext().getDrawable(C5441R.drawable.module_icon_play).getConstantState()) {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_pause);
            this.mcurrentPlayingMedia = media;
        } else {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_play);
            this.mcurrentPlayingMedia = null;
        }
    }

    public boolean checkIsAllSelect() {
        return this.mSelectedMedias.size() == this.mMedias.size();
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (Lists.isEmpty(this.mMedias)) {
            return 0;
        }
        return this.mMedias.size();
    }

    public void switchToEditMode(boolean z) {
        if (getItemCount() <= 0) {
            if (this.mCurrentType != 1) {
                this.mCurrentType = 1;
                notifyDataSetChanged();
                return;
            }
            return;
        }
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

    private boolean isSelected(Media media) {
        return this.mSelectedMedias.contains(media);
    }

    public void clearPlayingMediaStatus() {
        List<Media> list;
        int indexOf;
        OnItemClickListener onItemClickListener;
        Media media = this.mcurrentPlayingMedia;
        if (media != null && (list = this.mMedias) != null && (indexOf = list.indexOf(media)) != -1 && (onItemClickListener = this.mOnItemClickListener) != null) {
            onItemClickListener.onPlayingMediaChange(indexOf);
        }
        this.mcurrentPlayingMedia = null;
    }

    public void notifyItemsRemoved() {
        if (this.mSelectedMedias.size() == 0) {
            return;
        }
        Iterator<Media> it = this.mSelectedMedias.iterator();
        while (it.hasNext()) {
            int indexOf = this.mMedias.indexOf(it.next());
            this.mMedias.remove(indexOf);
            notifyItemRemoved(indexOf);
            notifyItemRangeChanged(indexOf, this.mMedias.size() - indexOf);
        }
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
        ImageButton playBtn;

        CViewHolder(View view) {
            super(view);
            this.musicCheckBox = (CheckBox) view.findViewById(C5441R.id.music_checkbox);
            this.musicNameTextView = (TextView) view.findViewById(C5441R.id.tv_music_name);
            this.musicInfoTextView = (TextView) view.findViewById(C5441R.id.tv_music_info);
            this.msicNumber = (TextView) view.findViewById(C5441R.id.tv_song_number);
            this.playBtn = (ImageButton) view.findViewById(C5441R.id.tv_play_icon);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
