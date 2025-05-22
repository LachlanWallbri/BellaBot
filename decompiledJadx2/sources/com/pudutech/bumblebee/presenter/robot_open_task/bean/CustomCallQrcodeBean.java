package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ICustomCallBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J7\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u001cHÖ\u0001J\t\u0010\"\u001a\u00020\u0004HÖ\u0001J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001cHÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeBean;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "Landroid/os/Parcelable;", "destination", "", "qrcode", AIUIConstant.KEY_CONTENT, "tts", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeTTSBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeTTSBean;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getDestination", "setDestination", "getQrcode", "setQrcode", "getTts", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeTTSBean;", "setTts", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallQrcodeTTSBean;)V", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CustomCallQrcodeBean implements ICustomCallBean, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private String content;
    private String destination;
    private String qrcode;
    private CustomCallQrcodeTTSBean tts;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new CustomCallQrcodeBean(in.readString(), in.readString(), in.readString(), in.readInt() != 0 ? (CustomCallQrcodeTTSBean) CustomCallQrcodeTTSBean.CREATOR.createFromParcel(in) : null);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new CustomCallQrcodeBean[i];
        }
    }

    public static /* synthetic */ CustomCallQrcodeBean copy$default(CustomCallQrcodeBean customCallQrcodeBean, String str, String str2, String str3, CustomCallQrcodeTTSBean customCallQrcodeTTSBean, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customCallQrcodeBean.destination;
        }
        if ((i & 2) != 0) {
            str2 = customCallQrcodeBean.qrcode;
        }
        if ((i & 4) != 0) {
            str3 = customCallQrcodeBean.content;
        }
        if ((i & 8) != 0) {
            customCallQrcodeTTSBean = customCallQrcodeBean.tts;
        }
        return customCallQrcodeBean.copy(str, str2, str3, customCallQrcodeTTSBean);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final String getQrcode() {
        return this.qrcode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component4, reason: from getter */
    public final CustomCallQrcodeTTSBean getTts() {
        return this.tts;
    }

    public final CustomCallQrcodeBean copy(String destination, String qrcode, String content, CustomCallQrcodeTTSBean tts) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        return new CustomCallQrcodeBean(destination, qrcode, content, tts);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallQrcodeBean)) {
            return false;
        }
        CustomCallQrcodeBean customCallQrcodeBean = (CustomCallQrcodeBean) other;
        return Intrinsics.areEqual(this.destination, customCallQrcodeBean.destination) && Intrinsics.areEqual(this.qrcode, customCallQrcodeBean.qrcode) && Intrinsics.areEqual(this.content, customCallQrcodeBean.content) && Intrinsics.areEqual(this.tts, customCallQrcodeBean.tts);
    }

    public int hashCode() {
        String str = this.destination;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.qrcode;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.content;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        CustomCallQrcodeTTSBean customCallQrcodeTTSBean = this.tts;
        return hashCode3 + (customCallQrcodeTTSBean != null ? customCallQrcodeTTSBean.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallQrcodeBean(destination=" + this.destination + ", qrcode=" + this.qrcode + ", content=" + this.content + ", tts=" + this.tts + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.destination);
        parcel.writeString(this.qrcode);
        parcel.writeString(this.content);
        CustomCallQrcodeTTSBean customCallQrcodeTTSBean = this.tts;
        if (customCallQrcodeTTSBean == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            customCallQrcodeTTSBean.writeToParcel(parcel, 0);
        }
    }

    public CustomCallQrcodeBean(String destination, String str, String str2, CustomCallQrcodeTTSBean customCallQrcodeTTSBean) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destination = destination;
        this.qrcode = str;
        this.content = str2;
        this.tts = customCallQrcodeTTSBean;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final void setDestination(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destination = str;
    }

    public final String getQrcode() {
        return this.qrcode;
    }

    public final void setQrcode(String str) {
        this.qrcode = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final CustomCallQrcodeTTSBean getTts() {
        return this.tts;
    }

    public final void setTts(CustomCallQrcodeTTSBean customCallQrcodeTTSBean) {
        this.tts = customCallQrcodeTTSBean;
    }
}
