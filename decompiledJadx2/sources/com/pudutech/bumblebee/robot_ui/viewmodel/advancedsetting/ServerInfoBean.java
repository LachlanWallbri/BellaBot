package com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ServerInfoBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J<\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010\u001eJ\t\u0010\u001f\u001a\u00020\u0004HÖ\u0001J\u0013\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\b\u0010#\u001a\u00020\u0006H\u0016J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\b\u0010%\u001a\u00020\tH\u0016J\t\u0010&\u001a\u00020\u0006HÖ\u0001J\u0019\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006,"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/ServerInfoBean;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/IRadioBtnTvBean;", "Landroid/os/Parcelable;", "id", "", "serverName", "", "host", "isSelect", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Z)V", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "()Z", "setSelect", "(Z)V", "getServerName", "setServerName", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Z)Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/ServerInfoBean;", "describeContents", "equals", "other", "", "getName", "hashCode", "isCheck", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class ServerInfoBean implements IRadioBtnTvBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private String host;
    private Integer id;
    private boolean isSelect;
    private String serverName;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new ServerInfoBean(in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null, in.readString(), in.readString(), in.readInt() != 0);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new ServerInfoBean[i];
        }
    }

    public ServerInfoBean() {
        this(null, null, null, false, 15, null);
    }

    public static /* synthetic */ ServerInfoBean copy$default(ServerInfoBean serverInfoBean, Integer num, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = serverInfoBean.id;
        }
        if ((i & 2) != 0) {
            str = serverInfoBean.serverName;
        }
        if ((i & 4) != 0) {
            str2 = serverInfoBean.host;
        }
        if ((i & 8) != 0) {
            z = serverInfoBean.isSelect;
        }
        return serverInfoBean.copy(num, str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getServerName() {
        return this.serverName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHost() {
        return this.host;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final ServerInfoBean copy(Integer id, String serverName, String host, boolean isSelect) {
        return new ServerInfoBean(id, serverName, host, isSelect);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerInfoBean)) {
            return false;
        }
        ServerInfoBean serverInfoBean = (ServerInfoBean) other;
        return Intrinsics.areEqual(this.id, serverInfoBean.id) && Intrinsics.areEqual(this.serverName, serverInfoBean.serverName) && Intrinsics.areEqual(this.host, serverInfoBean.host) && this.isSelect == serverInfoBean.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Integer num = this.id;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.serverName;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.host;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "ServerInfoBean(id=" + this.id + ", serverName=" + this.serverName + ", host=" + this.host + ", isSelect=" + this.isSelect + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        int i;
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        Integer num = this.id;
        if (num != null) {
            parcel.writeInt(1);
            i = num.intValue();
        } else {
            i = 0;
        }
        parcel.writeInt(i);
        parcel.writeString(this.serverName);
        parcel.writeString(this.host);
        parcel.writeInt(this.isSelect ? 1 : 0);
    }

    public ServerInfoBean(Integer num, String str, String str2, boolean z) {
        this.id = num;
        this.serverName = str;
        this.host = str2;
        this.isSelect = z;
    }

    public /* synthetic */ ServerInfoBean(Integer num, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? false : z);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final void setServerName(String str) {
        this.serverName = str;
    }

    public final String getHost() {
        return this.host;
    }

    public final void setHost(String str) {
        this.host = str;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean
    public boolean isCheck() {
        return this.isSelect;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean
    public String getName() {
        String str = this.serverName;
        return str != null ? str : "";
    }
}
