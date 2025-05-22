package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.mpmodule.media.Media;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialModeMusicAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\b\"\u0004\b\u000b\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectMusicItem;", "", "media", "Lcom/pudutech/mpmodule/media/Media;", "isSelect", "", "isPlaying", "(Lcom/pudutech/mpmodule/media/Media;ZZ)V", "()Z", "setPlaying", "(Z)V", "setSelect", "getMedia", "()Lcom/pudutech/mpmodule/media/Media;", "setMedia", "(Lcom/pudutech/mpmodule/media/Media;)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SelectMusicItem {
    private boolean isPlaying;
    private boolean isSelect;
    private Media media;

    public static /* synthetic */ SelectMusicItem copy$default(SelectMusicItem selectMusicItem, Media media, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            media = selectMusicItem.media;
        }
        if ((i & 2) != 0) {
            z = selectMusicItem.isSelect;
        }
        if ((i & 4) != 0) {
            z2 = selectMusicItem.isPlaying;
        }
        return selectMusicItem.copy(media, z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final Media getMedia() {
        return this.media;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsPlaying() {
        return this.isPlaying;
    }

    public final SelectMusicItem copy(Media media, boolean isSelect, boolean isPlaying) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        return new SelectMusicItem(media, isSelect, isPlaying);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectMusicItem)) {
            return false;
        }
        SelectMusicItem selectMusicItem = (SelectMusicItem) other;
        return Intrinsics.areEqual(this.media, selectMusicItem.media) && this.isSelect == selectMusicItem.isSelect && this.isPlaying == selectMusicItem.isPlaying;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Media media = this.media;
        int hashCode = (media != null ? media.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isPlaying;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        return i2 + i3;
    }

    public String toString() {
        return "SelectMusicItem(media=" + this.media + ", isSelect=" + this.isSelect + ", isPlaying=" + this.isPlaying + ")";
    }

    public SelectMusicItem(Media media, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(media, "media");
        this.media = media;
        this.isSelect = z;
        this.isPlaying = z2;
    }

    public final Media getMedia() {
        return this.media;
    }

    public final boolean isPlaying() {
        return this.isPlaying;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setMedia(Media media) {
        Intrinsics.checkParameterIsNotNull(media, "<set-?>");
        this.media = media;
    }

    public final void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
