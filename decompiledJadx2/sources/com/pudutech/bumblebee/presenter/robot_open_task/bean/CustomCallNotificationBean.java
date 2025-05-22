package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ICustomCallBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\bHÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallNotificationBean;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "Landroid/os/Parcelable;", "destination", "", "state", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "type", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "(Ljava/lang/String;Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;)V", "getDestination", "()Ljava/lang/String;", "getState", "()Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "getType", "()Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CustomCallNotificationBean implements ICustomCallBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String destination;
    private final CustomCallState state;
    private final CustomCallOperationType type;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new CustomCallNotificationBean(in.readString(), (CustomCallState) Enum.valueOf(CustomCallState.class, in.readString()), (CustomCallOperationType) Enum.valueOf(CustomCallOperationType.class, in.readString()));
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new CustomCallNotificationBean[i];
        }
    }

    public static /* synthetic */ CustomCallNotificationBean copy$default(CustomCallNotificationBean customCallNotificationBean, String str, CustomCallState customCallState, CustomCallOperationType customCallOperationType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customCallNotificationBean.destination;
        }
        if ((i & 2) != 0) {
            customCallState = customCallNotificationBean.state;
        }
        if ((i & 4) != 0) {
            customCallOperationType = customCallNotificationBean.type;
        }
        return customCallNotificationBean.copy(str, customCallState, customCallOperationType);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final CustomCallState getState() {
        return this.state;
    }

    /* renamed from: component3, reason: from getter */
    public final CustomCallOperationType getType() {
        return this.type;
    }

    public final CustomCallNotificationBean copy(String destination, CustomCallState state, CustomCallOperationType type) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new CustomCallNotificationBean(destination, state, type);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallNotificationBean)) {
            return false;
        }
        CustomCallNotificationBean customCallNotificationBean = (CustomCallNotificationBean) other;
        return Intrinsics.areEqual(this.destination, customCallNotificationBean.destination) && Intrinsics.areEqual(this.state, customCallNotificationBean.state) && Intrinsics.areEqual(this.type, customCallNotificationBean.type);
    }

    public int hashCode() {
        String str = this.destination;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        CustomCallState customCallState = this.state;
        int hashCode2 = (hashCode + (customCallState != null ? customCallState.hashCode() : 0)) * 31;
        CustomCallOperationType customCallOperationType = this.type;
        return hashCode2 + (customCallOperationType != null ? customCallOperationType.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallNotificationBean(destination=" + this.destination + ", state=" + this.state + ", type=" + this.type + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.destination);
        parcel.writeString(this.state.name());
        parcel.writeString(this.type.name());
    }

    public CustomCallNotificationBean(String destination, CustomCallState state, CustomCallOperationType type) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.destination = destination;
        this.state = state;
        this.type = type;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final CustomCallState getState() {
        return this.state;
    }

    public final CustomCallOperationType getType() {
        return this.type;
    }
}
