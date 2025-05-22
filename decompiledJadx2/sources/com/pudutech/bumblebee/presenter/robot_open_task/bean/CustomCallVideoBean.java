package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ICustomCallBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ8\u0010\u0018\u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0019J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001J\u0019\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0007HÖ\u0001R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006&"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallVideoBean;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "Landroid/os/Parcelable;", "urls", "", "", "playCount", "", "cancelBtnTime", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCancelBtnTime", "()Ljava/lang/Integer;", "setCancelBtnTime", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPlayCount", "setPlayCount", "getUrls", "()Ljava/util/List;", "setUrls", "(Ljava/util/List;)V", "component1", "component2", "component3", "copy", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallVideoBean;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CustomCallVideoBean implements ICustomCallBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private Integer cancelBtnTime;
    private Integer playCount;
    private List<String> urls;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new CustomCallVideoBean(in.createStringArrayList(), in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null, in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new CustomCallVideoBean[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CustomCallVideoBean copy$default(CustomCallVideoBean customCallVideoBean, List list, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = customCallVideoBean.urls;
        }
        if ((i & 2) != 0) {
            num = customCallVideoBean.playCount;
        }
        if ((i & 4) != 0) {
            num2 = customCallVideoBean.cancelBtnTime;
        }
        return customCallVideoBean.copy(list, num, num2);
    }

    public final List<String> component1() {
        return this.urls;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getPlayCount() {
        return this.playCount;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getCancelBtnTime() {
        return this.cancelBtnTime;
    }

    public final CustomCallVideoBean copy(List<String> urls, Integer playCount, Integer cancelBtnTime) {
        return new CustomCallVideoBean(urls, playCount, cancelBtnTime);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallVideoBean)) {
            return false;
        }
        CustomCallVideoBean customCallVideoBean = (CustomCallVideoBean) other;
        return Intrinsics.areEqual(this.urls, customCallVideoBean.urls) && Intrinsics.areEqual(this.playCount, customCallVideoBean.playCount) && Intrinsics.areEqual(this.cancelBtnTime, customCallVideoBean.cancelBtnTime);
    }

    public int hashCode() {
        List<String> list = this.urls;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        Integer num = this.playCount;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.cancelBtnTime;
        return hashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallVideoBean(urls=" + this.urls + ", playCount=" + this.playCount + ", cancelBtnTime=" + this.cancelBtnTime + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeStringList(this.urls);
        Integer num = this.playCount;
        if (num != null) {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        } else {
            parcel.writeInt(0);
        }
        Integer num2 = this.cancelBtnTime;
        if (num2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(num2.intValue());
        }
    }

    public CustomCallVideoBean(List<String> list, Integer num, Integer num2) {
        this.urls = list;
        this.playCount = num;
        this.cancelBtnTime = num2;
    }

    public final List<String> getUrls() {
        return this.urls;
    }

    public final void setUrls(List<String> list) {
        this.urls = list;
    }

    public final Integer getPlayCount() {
        return this.playCount;
    }

    public final void setPlayCount(Integer num) {
        this.playCount = num;
    }

    public final Integer getCancelBtnTime() {
        return this.cancelBtnTime;
    }

    public final void setCancelBtnTime(Integer num) {
        this.cancelBtnTime = num;
    }
}
