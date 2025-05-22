package com.pudutech.peanut.robot_ui.p063ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.peanut.robot_ui.C5508R;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialModeMusicAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0006R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SpecialModeMusicAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectMusicItem;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "setContext", "onClickListener", "Landroid/view/View$OnClickListener;", "convert", "", "helper", "item", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SpecialModeMusicAdapter extends BaseQuickAdapter<SelectMusicItem, BaseViewHolder> {
    private final String TAG;
    private Context context;
    private final View.OnClickListener onClickListener;

    public final Context getContext() {
        return this.context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpecialModeMusicAdapter(Context context) {
        super(C5508R.layout.item_select_special_music);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = getClass().getSimpleName();
        this.onClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.adapter.SpecialModeMusicAdapter$onClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                String str;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.adapter.SelectMusicItem");
                }
                SelectMusicItem selectMusicItem = (SelectMusicItem) tag;
                List<SelectMusicItem> data = SpecialModeMusicAdapter.this.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "data");
                for (SelectMusicItem selectMusicItem2 : data) {
                    if (Intrinsics.areEqual(selectMusicItem, selectMusicItem2)) {
                        selectMusicItem2.setPlaying(!selectMusicItem2.isPlaying());
                        Music music = new Music();
                        music.setPath(selectMusicItem.getMedia().getPath());
                        str = SpecialModeMusicAdapter.this.TAG;
                        Pdlog.m3273d(str, "MODE_SPECIAL music is " + selectMusicItem.getMedia().getPath());
                        MusicPlayerHelper.getInstance().switchSong(music);
                    } else {
                        selectMusicItem2.setPlaying(false);
                    }
                }
                SpecialModeMusicAdapter.this.notifyDataSetChanged();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder helper, SelectMusicItem item) {
        Intrinsics.checkParameterIsNotNull(helper, "helper");
        Intrinsics.checkParameterIsNotNull(item, "item");
        TextView textView = (TextView) helper.getView(C5508R.id.name_text);
        View icon = helper.getView(C5508R.id.select_ic);
        ImageView iconPlay = (ImageView) helper.getView(C5508R.id.play_btn_iv);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
        textView.setText(item.getMedia().getFileName());
        if (item.isSelect()) {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(0);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(icon, "icon");
            icon.setVisibility(8);
        }
        Intrinsics.checkExpressionValueIsNotNull(iconPlay, "iconPlay");
        iconPlay.setTag(item);
        iconPlay.setOnClickListener(this.onClickListener);
        if (item.isPlaying()) {
            iconPlay.setImageResource(C5508R.drawable.icon_special_music_pause);
        } else {
            iconPlay.setImageResource(C5508R.drawable.icon_special_music_play);
        }
    }
}
