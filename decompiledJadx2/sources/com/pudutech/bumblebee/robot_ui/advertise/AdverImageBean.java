package com.pudutech.bumblebee.robot_ui.advertise;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverDateBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0019\u001a\u00020\bHÆ\u0003J.\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u001bJ\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0006HÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006'"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverImageBean;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "Landroid/os/Parcelable;", "url", "", "switchTime", "", "loadCompleted", "", "(Ljava/lang/String;Ljava/lang/Integer;Z)V", "getLoadCompleted", "()Z", "setLoadCompleted", "(Z)V", "getSwitchTime", "()Ljava/lang/Integer;", "setSwitchTime", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Z)Lcom/pudutech/bumblebee/robot_ui/advertise/AdverImageBean;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class AdverImageBean implements AdverBaseBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private boolean loadCompleted;
    private Integer switchTime;
    private String url;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new AdverImageBean(in.readString(), in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null, in.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AdverImageBean[i];
        }
    }

    public static /* synthetic */ AdverImageBean copy$default(AdverImageBean adverImageBean, String str, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = adverImageBean.url;
        }
        if ((i & 2) != 0) {
            num = adverImageBean.switchTime;
        }
        if ((i & 4) != 0) {
            z = adverImageBean.loadCompleted;
        }
        return adverImageBean.copy(str, num, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getSwitchTime() {
        return this.switchTime;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getLoadCompleted() {
        return this.loadCompleted;
    }

    public final AdverImageBean copy(String url, Integer switchTime, boolean loadCompleted) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new AdverImageBean(url, switchTime, loadCompleted);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdverImageBean)) {
            return false;
        }
        AdverImageBean adverImageBean = (AdverImageBean) other;
        return Intrinsics.areEqual(this.url, adverImageBean.url) && Intrinsics.areEqual(this.switchTime, adverImageBean.switchTime) && this.loadCompleted == adverImageBean.loadCompleted;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.url;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.switchTime;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        boolean z = this.loadCompleted;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "AdverImageBean(url=" + this.url + ", switchTime=" + this.switchTime + ", loadCompleted=" + this.loadCompleted + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        int i;
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.url);
        Integer num = this.switchTime;
        if (num != null) {
            parcel.writeInt(1);
            i = num.intValue();
        } else {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeInt(this.loadCompleted ? 1 : 0);
    }

    public AdverImageBean(String url, Integer num, boolean z) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.url = url;
        this.switchTime = num;
        this.loadCompleted = z;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public final Integer getSwitchTime() {
        return this.switchTime;
    }

    public final void setSwitchTime(Integer num) {
        this.switchTime = num;
    }

    public /* synthetic */ AdverImageBean(String str, Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, num, (i & 4) != 0 ? false : z);
    }

    public final boolean getLoadCompleted() {
        return this.loadCompleted;
    }

    public final void setLoadCompleted(boolean z) {
        this.loadCompleted = z;
    }
}
