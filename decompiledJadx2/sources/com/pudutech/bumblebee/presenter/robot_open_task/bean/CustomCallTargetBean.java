package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ICustomCallBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "Landroid/os/Parcelable;", "destination", "", TmpConstant.SERVICE_CALLTYPE, "", AIUIConstant.KEY_CONTENT, "(Ljava/lang/String;ILcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;)V", "getCallType", "()I", "getContent", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "setContent", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;)V", "getDestination", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CustomCallTargetBean implements ICustomCallBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final int callType;
    private ICustomCallBean content;
    private final String destination;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new CustomCallTargetBean(in.readString(), in.readInt(), (ICustomCallBean) in.readParcelable(CustomCallTargetBean.class.getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new CustomCallTargetBean[i];
        }
    }

    public static /* synthetic */ CustomCallTargetBean copy$default(CustomCallTargetBean customCallTargetBean, String str, int i, ICustomCallBean iCustomCallBean, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = customCallTargetBean.destination;
        }
        if ((i2 & 2) != 0) {
            i = customCallTargetBean.callType;
        }
        if ((i2 & 4) != 0) {
            iCustomCallBean = customCallTargetBean.content;
        }
        return customCallTargetBean.copy(str, i, iCustomCallBean);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final int getCallType() {
        return this.callType;
    }

    /* renamed from: component3, reason: from getter */
    public final ICustomCallBean getContent() {
        return this.content;
    }

    public final CustomCallTargetBean copy(String destination, int callType, ICustomCallBean content) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        return new CustomCallTargetBean(destination, callType, content);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallTargetBean)) {
            return false;
        }
        CustomCallTargetBean customCallTargetBean = (CustomCallTargetBean) other;
        return Intrinsics.areEqual(this.destination, customCallTargetBean.destination) && this.callType == customCallTargetBean.callType && Intrinsics.areEqual(this.content, customCallTargetBean.content);
    }

    public int hashCode() {
        String str = this.destination;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.callType) * 31;
        ICustomCallBean iCustomCallBean = this.content;
        return hashCode + (iCustomCallBean != null ? iCustomCallBean.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallTargetBean(destination=" + this.destination + ", callType=" + this.callType + ", content=" + this.content + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.destination);
        parcel.writeInt(this.callType);
        parcel.writeParcelable(this.content, flags);
    }

    public CustomCallTargetBean(String destination, int i, ICustomCallBean iCustomCallBean) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destination = destination;
        this.callType = i;
        this.content = iCustomCallBean;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final int getCallType() {
        return this.callType;
    }

    public final ICustomCallBean getContent() {
        return this.content;
    }

    public final void setContent(ICustomCallBean iCustomCallBean) {
        this.content = iCustomCallBean;
    }
}
