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
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MusicFileListAdapter extends RecyclerView.Adapter<CViewHolder> {
    private Context mContext;
    private Media mCurrentPlayingMedia = null;
    private List<Media> mMedias;
    private OnItemClickListener mOnItemClickListener;
    private List<Media> mSelectedMedias;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnItemClickListener {
        void onItemClick(Media media, View view, int i);

        void onPlayBtnClick(Media media, View view, int i);

        void onPlayingMediaChange(int i);

        void onPlusBtnClick(Media media, View view, int i);
    }

    public MusicFileListAdapter(Context context, List<Media> list, List<Media> list2) {
        this.mContext = context;
        this.mMedias = list;
        this.mSelectedMedias = list2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CViewHolder(LayoutInflater.from(this.mContext).inflate(C5441R.layout.module_musicplayer_item_music_file, viewGroup, false));
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
        Media media2 = this.mCurrentPlayingMedia;
        if (media2 != null && media == media2 && cViewHolder.playBtn.getDrawable().getConstantState() != cViewHolder.playBtn.getContext().getDrawable(C5441R.drawable.module_icon_pause).getConstantState()) {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_pause);
        }
        cViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.-$$Lambda$MusicFileListAdapter$DkvFTiZ6Kl9k_3cTVwS6kYobgjw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicFileListAdapter.this.lambda$onBindViewHolder$0$MusicFileListAdapter(media, cViewHolder, i, view);
            }
        });
        cViewHolder.musicCheckBox.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.MusicFileListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (cViewHolder.musicCheckBox.isChecked()) {
                    if (MusicFileListAdapter.this.mSelectedMedias.contains(media)) {
                        return;
                    }
                    MusicFileListAdapter.this.mSelectedMedias.add(media);
                    return;
                }
                MusicFileListAdapter.this.mSelectedMedias.remove(media);
            }
        });
        cViewHolder.playBtn.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.-$$Lambda$MusicFileListAdapter$igzoNJ-LN4mfeG-LLfIUKA6RUgk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicFileListAdapter.this.lambda$onBindViewHolder$1$MusicFileListAdapter(media, cViewHolder, i, view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$MusicFileListAdapter(Media media, CViewHolder cViewHolder, int i, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(media, cViewHolder.itemView, i);
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$1$MusicFileListAdapter(Media media, CViewHolder cViewHolder, int i, View view) {
        List<Media> list;
        int indexOf;
        OnItemClickListener onItemClickListener;
        OnItemClickListener onItemClickListener2 = this.mOnItemClickListener;
        if (onItemClickListener2 != null) {
            onItemClickListener2.onPlayBtnClick(media, cViewHolder.playBtn, i);
        }
        Media media2 = this.mCurrentPlayingMedia;
        if (media2 != null && (list = this.mMedias) != null && (indexOf = list.indexOf(media2)) != -1 && indexOf != i && (onItemClickListener = this.mOnItemClickListener) != null) {
            onItemClickListener.onPlayingMediaChange(indexOf);
        }
        if (cViewHolder.playBtn.getDrawable().getConstantState() == cViewHolder.playBtn.getContext().getDrawable(C5441R.drawable.module_icon_play).getConstantState()) {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_pause);
            this.mCurrentPlayingMedia = media;
        } else {
            cViewHolder.playBtn.setImageResource(C5441R.drawable.module_icon_play);
            this.mCurrentPlayingMedia = null;
        }
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
            this.musicCheckBox = (CheckBox) view.findViewById(C5441R.id.cb_music);
            this.musicNameTextView = (TextView) view.findViewById(C5441R.id.tv_music_name);
            this.musicInfoTextView = (TextView) view.findViewById(C5441R.id.tv_music_info);
            this.playBtn = (ImageButton) view.findViewById(C5441R.id.music_play_icon);
            this.msicNumber = (TextView) view.findViewById(C5441R.id.tv_song_number);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
