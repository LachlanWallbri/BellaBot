package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ICustomCallBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJD\u0010\u001c\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u0007HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\u0019\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0007HÖ\u0001R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallImgBean;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "Landroid/os/Parcelable;", "urls", "", "", "switchTime", "", "cancelBtnTime", "showTimeout", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCancelBtnTime", "()Ljava/lang/Integer;", "setCancelBtnTime", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getShowTimeout", "setShowTimeout", "getSwitchTime", "setSwitchTime", "getUrls", "()Ljava/util/List;", "setUrls", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "copy", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallImgBean;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CustomCallImgBean implements ICustomCallBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private Integer cancelBtnTime;
    private Integer showTimeout;
    private Integer switchTime;
    private List<String> urls;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new CustomCallImgBean(in.createStringArrayList(), in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null, in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null, in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new CustomCallImgBean[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CustomCallImgBean copy$default(CustomCallImgBean customCallImgBean, List list, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = customCallImgBean.urls;
        }
        if ((i & 2) != 0) {
            num = customCallImgBean.switchTime;
        }
        if ((i & 4) != 0) {
            num2 = customCallImgBean.cancelBtnTime;
        }
        if ((i & 8) != 0) {
            num3 = customCallImgBean.showTimeout;
        }
        return customCallImgBean.copy(list, num, num2, num3);
    }

    public final List<String> component1() {
        return this.urls;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getSwitchTime() {
        return this.switchTime;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getCancelBtnTime() {
        return this.cancelBtnTime;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getShowTimeout() {
        return this.showTimeout;
    }

    public final CustomCallImgBean copy(List<String> urls, Integer switchTime, Integer cancelBtnTime, Integer showTimeout) {
        return new CustomCallImgBean(urls, switchTime, cancelBtnTime, showTimeout);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallImgBean)) {
            return false;
        }
        CustomCallImgBean customCallImgBean = (CustomCallImgBean) other;
        return Intrinsics.areEqual(this.urls, customCallImgBean.urls) && Intrinsics.areEqual(this.switchTime, customCallImgBean.switchTime) && Intrinsics.areEqual(this.cancelBtnTime, customCallImgBean.cancelBtnTime) && Intrinsics.areEqual(this.showTimeout, customCallImgBean.showTimeout);
    }

    public int hashCode() {
        List<String> list = this.urls;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        Integer num = this.switchTime;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.cancelBtnTime;
        int hashCode3 = (hashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.showTimeout;
        return hashCode3 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallImgBean(urls=" + this.urls + ", switchTime=" + this.switchTime + ", cancelBtnTime=" + this.cancelBtnTime + ", showTimeout=" + this.showTimeout + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeStringList(this.urls);
        Integer num = this.switchTime;
        if (num != null) {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        } else {
            parcel.writeInt(0);
        }
        Integer num2 = this.cancelBtnTime;
        if (num2 != null) {
            parcel.writeInt(1);
            parcel.writeInt(num2.intValue());
        } else {
            parcel.writeInt(0);
        }
        Integer num3 = this.showTimeout;
        if (num3 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num3.intValue());
        }
    }

    public CustomCallImgBean(List<String> list, Integer num, Integer num2, Integer num3) {
        this.urls = list;
        this.switchTime = num;
        this.cancelBtnTime = num2;
        this.showTimeout = num3;
    }

    public final List<String> getUrls() {
        return this.urls;
    }

    public final void setUrls(List<String> list) {
        this.urls = list;
    }

    public final Integer getSwitchTime() {
        return this.switchTime;
    }

    public final void setSwitchTime(Integer num) {
        this.switchTime = num;
    }

    public final Integer getCancelBtnTime() {
        return this.cancelBtnTime;
    }

    public final void setCancelBtnTime(Integer num) {
        this.cancelBtnTime = num;
    }

    public final Integer getShowTimeout() {
        return this.showTimeout;
    }

    public final void setShowTimeout(Integer num) {
        this.showTimeout = num;
    }
}
