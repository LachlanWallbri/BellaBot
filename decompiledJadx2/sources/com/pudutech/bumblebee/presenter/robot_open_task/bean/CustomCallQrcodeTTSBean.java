package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ICustomCallBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000bJ2\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u001cJ\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0006HÖ\u0001J\t\u0010#\u001a\u00020\u0004HÖ\u0001J\u0019\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u0006HÖ\u0001R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeTTSBean;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "Landroid/os/Parcelable;", "text", "", "playType", "", "intervalTime", "", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;)V", "getIntervalTime", "()Ljava/lang/Long;", "setIntervalTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getPlayType", "()Ljava/lang/Integer;", "setPlayType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeTTSBean;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CustomCallQrcodeTTSBean implements ICustomCallBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private Long intervalTime;
    private Integer playType;
    private String text;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new CustomCallQrcodeTTSBean(in.readString(), in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null, in.readInt() != 0 ? Long.valueOf(in.readLong()) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new CustomCallQrcodeTTSBean[i];
        }
    }

    public static /* synthetic */ CustomCallQrcodeTTSBean copy$default(CustomCallQrcodeTTSBean customCallQrcodeTTSBean, String str, Integer num, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customCallQrcodeTTSBean.text;
        }
        if ((i & 2) != 0) {
            num = customCallQrcodeTTSBean.playType;
        }
        if ((i & 4) != 0) {
            l = customCallQrcodeTTSBean.intervalTime;
        }
        return customCallQrcodeTTSBean.copy(str, num, l);
    }

    /* renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getPlayType() {
        return this.playType;
    }

    /* renamed from: component3, reason: from getter */
    public final Long getIntervalTime() {
        return this.intervalTime;
    }

    public final CustomCallQrcodeTTSBean copy(String text, Integer playType, Long intervalTime) {
        return new CustomCallQrcodeTTSBean(text, playType, intervalTime);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallQrcodeTTSBean)) {
            return false;
        }
        CustomCallQrcodeTTSBean customCallQrcodeTTSBean = (CustomCallQrcodeTTSBean) other;
        return Intrinsics.areEqual(this.text, customCallQrcodeTTSBean.text) && Intrinsics.areEqual(this.playType, customCallQrcodeTTSBean.playType) && Intrinsics.areEqual(this.intervalTime, customCallQrcodeTTSBean.intervalTime);
    }

    public int hashCode() {
        String str = this.text;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.playType;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Long l = this.intervalTime;
        return hashCode2 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallQrcodeTTSBean(text=" + this.text + ", playType=" + this.playType + ", intervalTime=" + this.intervalTime + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.text);
        Integer num = this.playType;
        if (num != null) {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        } else {
            parcel.writeInt(0);
        }
        Long l = this.intervalTime;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeLong(l.longValue());
        }
    }

    public CustomCallQrcodeTTSBean(String str, Integer num, Long l) {
        this.text = str;
        this.playType = num;
        this.intervalTime = l;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        this.text = str;
    }

    public final Integer getPlayType() {
        return this.playType;
    }

    public final void setPlayType(Integer num) {
        this.playType = num;
    }

    public final Long getIntervalTime() {
        return this.intervalTime;
    }

    public final void setIntervalTime(Long l) {
        this.intervalTime = l;
    }
}
