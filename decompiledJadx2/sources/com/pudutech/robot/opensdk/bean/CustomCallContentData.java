package com.pudutech.robot.opensdk.bean;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomCallContentBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\u001d\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJt\u0010*\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\u0007HÖ\u0001J\t\u00100\u001a\u00020\u0004HÖ\u0001R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R.\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u00061"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;", "", "urls", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "switchTime", "", "playCount", "qrcode", "text", "cancelBtnTime", "showTimeout", "(Ljava/util/ArrayList;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCancelBtnTime", "()Ljava/lang/Integer;", "setCancelBtnTime", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPlayCount", "setPlayCount", "getQrcode", "()Ljava/lang/String;", "setQrcode", "(Ljava/lang/String;)V", "getShowTimeout", "setShowTimeout", "getSwitchTime", "setSwitchTime", "getText", "setText", "getUrls", "()Ljava/util/ArrayList;", "setUrls", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/util/ArrayList;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/pudutech/robot/opensdk/bean/CustomCallContentData;", "equals", "", "other", "hashCode", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CustomCallContentData {
    private Integer cancelBtnTime;
    private Integer playCount;
    private String qrcode;
    private Integer showTimeout;
    private Integer switchTime;
    private String text;
    private ArrayList<String> urls;

    public static /* synthetic */ CustomCallContentData copy$default(CustomCallContentData customCallContentData, ArrayList arrayList, Integer num, Integer num2, String str, String str2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = customCallContentData.urls;
        }
        if ((i & 2) != 0) {
            num = customCallContentData.switchTime;
        }
        Integer num5 = num;
        if ((i & 4) != 0) {
            num2 = customCallContentData.playCount;
        }
        Integer num6 = num2;
        if ((i & 8) != 0) {
            str = customCallContentData.qrcode;
        }
        String str3 = str;
        if ((i & 16) != 0) {
            str2 = customCallContentData.text;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            num3 = customCallContentData.cancelBtnTime;
        }
        Integer num7 = num3;
        if ((i & 64) != 0) {
            num4 = customCallContentData.showTimeout;
        }
        return customCallContentData.copy(arrayList, num5, num6, str3, str4, num7, num4);
    }

    public final ArrayList<String> component1() {
        return this.urls;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getSwitchTime() {
        return this.switchTime;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getPlayCount() {
        return this.playCount;
    }

    /* renamed from: component4, reason: from getter */
    public final String getQrcode() {
        return this.qrcode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getCancelBtnTime() {
        return this.cancelBtnTime;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getShowTimeout() {
        return this.showTimeout;
    }

    public final CustomCallContentData copy(ArrayList<String> urls, Integer switchTime, Integer playCount, String qrcode, String text, Integer cancelBtnTime, Integer showTimeout) {
        return new CustomCallContentData(urls, switchTime, playCount, qrcode, text, cancelBtnTime, showTimeout);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallContentData)) {
            return false;
        }
        CustomCallContentData customCallContentData = (CustomCallContentData) other;
        return Intrinsics.areEqual(this.urls, customCallContentData.urls) && Intrinsics.areEqual(this.switchTime, customCallContentData.switchTime) && Intrinsics.areEqual(this.playCount, customCallContentData.playCount) && Intrinsics.areEqual(this.qrcode, customCallContentData.qrcode) && Intrinsics.areEqual(this.text, customCallContentData.text) && Intrinsics.areEqual(this.cancelBtnTime, customCallContentData.cancelBtnTime) && Intrinsics.areEqual(this.showTimeout, customCallContentData.showTimeout);
    }

    public int hashCode() {
        ArrayList<String> arrayList = this.urls;
        int hashCode = (arrayList != null ? arrayList.hashCode() : 0) * 31;
        Integer num = this.switchTime;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.playCount;
        int hashCode3 = (hashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str = this.qrcode;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.text;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num3 = this.cancelBtnTime;
        int hashCode6 = (hashCode5 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Integer num4 = this.showTimeout;
        return hashCode6 + (num4 != null ? num4.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallContentData(urls=" + this.urls + ", switchTime=" + this.switchTime + ", playCount=" + this.playCount + ", qrcode=" + this.qrcode + ", text=" + this.text + ", cancelBtnTime=" + this.cancelBtnTime + ", showTimeout=" + this.showTimeout + ")";
    }

    public CustomCallContentData(ArrayList<String> arrayList, Integer num, Integer num2, String str, String str2, Integer num3, Integer num4) {
        this.urls = arrayList;
        this.switchTime = num;
        this.playCount = num2;
        this.qrcode = str;
        this.text = str2;
        this.cancelBtnTime = num3;
        this.showTimeout = num4;
    }

    public final ArrayList<String> getUrls() {
        return this.urls;
    }

    public final void setUrls(ArrayList<String> arrayList) {
        this.urls = arrayList;
    }

    public final Integer getSwitchTime() {
        return this.switchTime;
    }

    public final void setSwitchTime(Integer num) {
        this.switchTime = num;
    }

    public final Integer getPlayCount() {
        return this.playCount;
    }

    public final void setPlayCount(Integer num) {
        this.playCount = num;
    }

    public final String getQrcode() {
        return this.qrcode;
    }

    public final void setQrcode(String str) {
        this.qrcode = str;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        this.text = str;
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
