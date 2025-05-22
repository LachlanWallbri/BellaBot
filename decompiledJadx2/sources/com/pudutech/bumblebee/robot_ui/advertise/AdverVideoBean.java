package com.pudutech.bumblebee.robot_ui.advertise;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverDateBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\u0019\u0010\u000b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\rHÖ\u0001R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverVideoBean;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "Landroid/os/Parcelable;", "urls", "", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVideoInfoBean;", "(Ljava/util/List;)V", "getUrls", "()Ljava/util/List;", "setUrls", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class AdverVideoBean implements AdverBaseBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private List<AdVideoInfoBean> urls;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            int readInt = in.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((AdVideoInfoBean) AdVideoInfoBean.CREATOR.createFromParcel(in));
                readInt--;
            }
            return new AdverVideoBean(arrayList);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AdverVideoBean[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AdverVideoBean copy$default(AdverVideoBean adverVideoBean, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = adverVideoBean.urls;
        }
        return adverVideoBean.copy(list);
    }

    public final List<AdVideoInfoBean> component1() {
        return this.urls;
    }

    public final AdverVideoBean copy(List<AdVideoInfoBean> urls) {
        Intrinsics.checkParameterIsNotNull(urls, "urls");
        return new AdverVideoBean(urls);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof AdverVideoBean) && Intrinsics.areEqual(this.urls, ((AdverVideoBean) other).urls);
        }
        return true;
    }

    public int hashCode() {
        List<AdVideoInfoBean> list = this.urls;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "AdverVideoBean(urls=" + this.urls + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        List<AdVideoInfoBean> list = this.urls;
        parcel.writeInt(list.size());
        Iterator<AdVideoInfoBean> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, 0);
        }
    }

    public AdverVideoBean(List<AdVideoInfoBean> urls) {
        Intrinsics.checkParameterIsNotNull(urls, "urls");
        this.urls = urls;
    }

    public final List<AdVideoInfoBean> getUrls() {
        return this.urls;
    }

    public final void setUrls(List<AdVideoInfoBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.urls = list;
    }
}
