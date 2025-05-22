package com.pudutech.peanut.robot_ui.p063ui.adapter;

import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import kotlin.Metadata;

/* compiled from: PromotionsAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \f*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\fB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u000b\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\u0004H\u0016R\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/PromotionMultipleItem;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "mItemType", "", "mData", "(ILjava/lang/Object;)V", "getMData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "data", "getItemType", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PromotionMultipleItem<T> implements MultiItemEntity {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_THREE = 2;
    public static final int TYPE_TWO = 2;
    private final T mData;
    private final int mItemType;

    public PromotionMultipleItem(int i, T t) {
        this.mItemType = i;
        this.mData = t;
    }

    public final T getMData() {
        return this.mData;
    }

    public final T data() {
        return this.mData;
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    /* renamed from: getItemType, reason: from getter */
    public int getMItemType() {
        return this.mItemType;
    }
}
