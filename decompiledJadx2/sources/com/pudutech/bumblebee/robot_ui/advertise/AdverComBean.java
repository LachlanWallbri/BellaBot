package com.pudutech.bumblebee.robot_ui.advertise;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverDateBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverComBean;", "Landroid/os/Parcelable;", "adverBaseBean", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "type", "", "(Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;I)V", "getAdverBaseBean", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "setAdverBaseBean", "(Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;)V", "getType", "()I", "setType", "(I)V", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class AdverComBean implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private AdverBaseBean adverBaseBean;
    private int type;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new AdverComBean((AdverBaseBean) in.readParcelable(AdverComBean.class.getClassLoader()), in.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AdverComBean[i];
        }
    }

    public static /* synthetic */ AdverComBean copy$default(AdverComBean adverComBean, AdverBaseBean adverBaseBean, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            adverBaseBean = adverComBean.adverBaseBean;
        }
        if ((i2 & 2) != 0) {
            i = adverComBean.type;
        }
        return adverComBean.copy(adverBaseBean, i);
    }

    /* renamed from: component1, reason: from getter */
    public final AdverBaseBean getAdverBaseBean() {
        return this.adverBaseBean;
    }

    /* renamed from: component2, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public final AdverComBean copy(AdverBaseBean adverBaseBean, int type) {
        Intrinsics.checkParameterIsNotNull(adverBaseBean, "adverBaseBean");
        return new AdverComBean(adverBaseBean, type);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdverComBean)) {
            return false;
        }
        AdverComBean adverComBean = (AdverComBean) other;
        return Intrinsics.areEqual(this.adverBaseBean, adverComBean.adverBaseBean) && this.type == adverComBean.type;
    }

    public int hashCode() {
        AdverBaseBean adverBaseBean = this.adverBaseBean;
        return ((adverBaseBean != null ? adverBaseBean.hashCode() : 0) * 31) + Integer.hashCode(this.type);
    }

    public String toString() {
        return "AdverComBean(adverBaseBean=" + this.adverBaseBean + ", type=" + this.type + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeParcelable(this.adverBaseBean, flags);
        parcel.writeInt(this.type);
    }

    public AdverComBean(AdverBaseBean adverBaseBean, int i) {
        Intrinsics.checkParameterIsNotNull(adverBaseBean, "adverBaseBean");
        this.adverBaseBean = adverBaseBean;
        this.type = i;
    }

    public final AdverBaseBean getAdverBaseBean() {
        return this.adverBaseBean;
    }

    public final void setAdverBaseBean(AdverBaseBean adverBaseBean) {
        Intrinsics.checkParameterIsNotNull(adverBaseBean, "<set-?>");
        this.adverBaseBean = adverBaseBean;
    }

    public /* synthetic */ AdverComBean(AdverBaseBean adverBaseBean, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(adverBaseBean, (i2 & 2) != 0 ? -1 : i);
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
