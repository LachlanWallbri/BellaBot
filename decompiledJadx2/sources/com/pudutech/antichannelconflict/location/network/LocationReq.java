package com.pudutech.antichannelconflict.location.network;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B±\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¾\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00107J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0005HÖ\u0001J\b\u0010<\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017¨\u0006="}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/network/LocationReq;", "", TransferTable.COLUMN_KEY, "", "accesstype", "", "imei", "idfa", "smac", "serverip", "cdma", "imsi", "network", "tel", "bts", "nearbts", "mmac", "macs", "output", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccesstype", "()I", "getBts", "()Ljava/lang/String;", "getCdma", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIdfa", "getImei", "getImsi", "getKey", "getMacs", "getMmac", "getNearbts", "getNetwork", "getOutput", "getServerip", "getSmac", "getTel", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/pudutech/antichannelconflict/location/network/LocationReq;", "equals", "", "other", "hashCode", "toString", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LocationReq {
    private final int accesstype;
    private final String bts;
    private final Integer cdma;
    private final String idfa;
    private final String imei;
    private final String imsi;
    private final String key;
    private final String macs;
    private final String mmac;
    private final String nearbts;
    private final String network;
    private final String output;
    private final String serverip;
    private final String smac;
    private final String tel;

    /* renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* renamed from: component10, reason: from getter */
    public final String getTel() {
        return this.tel;
    }

    /* renamed from: component11, reason: from getter */
    public final String getBts() {
        return this.bts;
    }

    /* renamed from: component12, reason: from getter */
    public final String getNearbts() {
        return this.nearbts;
    }

    /* renamed from: component13, reason: from getter */
    public final String getMmac() {
        return this.mmac;
    }

    /* renamed from: component14, reason: from getter */
    public final String getMacs() {
        return this.macs;
    }

    /* renamed from: component15, reason: from getter */
    public final String getOutput() {
        return this.output;
    }

    /* renamed from: component2, reason: from getter */
    public final int getAccesstype() {
        return this.accesstype;
    }

    /* renamed from: component3, reason: from getter */
    public final String getImei() {
        return this.imei;
    }

    /* renamed from: component4, reason: from getter */
    public final String getIdfa() {
        return this.idfa;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSmac() {
        return this.smac;
    }

    /* renamed from: component6, reason: from getter */
    public final String getServerip() {
        return this.serverip;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getCdma() {
        return this.cdma;
    }

    /* renamed from: component8, reason: from getter */
    public final String getImsi() {
        return this.imsi;
    }

    /* renamed from: component9, reason: from getter */
    public final String getNetwork() {
        return this.network;
    }

    public final LocationReq copy(String key, int accesstype, String imei, String idfa, String smac, String serverip, Integer cdma, String imsi, String network, String tel, String bts, String nearbts, String mmac, String macs, String output) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return new LocationReq(key, accesstype, imei, idfa, smac, serverip, cdma, imsi, network, tel, bts, nearbts, mmac, macs, output);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationReq)) {
            return false;
        }
        LocationReq locationReq = (LocationReq) other;
        return Intrinsics.areEqual(this.key, locationReq.key) && this.accesstype == locationReq.accesstype && Intrinsics.areEqual(this.imei, locationReq.imei) && Intrinsics.areEqual(this.idfa, locationReq.idfa) && Intrinsics.areEqual(this.smac, locationReq.smac) && Intrinsics.areEqual(this.serverip, locationReq.serverip) && Intrinsics.areEqual(this.cdma, locationReq.cdma) && Intrinsics.areEqual(this.imsi, locationReq.imsi) && Intrinsics.areEqual(this.network, locationReq.network) && Intrinsics.areEqual(this.tel, locationReq.tel) && Intrinsics.areEqual(this.bts, locationReq.bts) && Intrinsics.areEqual(this.nearbts, locationReq.nearbts) && Intrinsics.areEqual(this.mmac, locationReq.mmac) && Intrinsics.areEqual(this.macs, locationReq.macs) && Intrinsics.areEqual(this.output, locationReq.output);
    }

    public int hashCode() {
        String str = this.key;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.accesstype) * 31;
        String str2 = this.imei;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.idfa;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.smac;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.serverip;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        Integer num = this.cdma;
        int hashCode6 = (hashCode5 + (num != null ? num.hashCode() : 0)) * 31;
        String str6 = this.imsi;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.network;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.tel;
        int hashCode9 = (hashCode8 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.bts;
        int hashCode10 = (hashCode9 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.nearbts;
        int hashCode11 = (hashCode10 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.mmac;
        int hashCode12 = (hashCode11 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.macs;
        int hashCode13 = (hashCode12 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.output;
        return hashCode13 + (str13 != null ? str13.hashCode() : 0);
    }

    public LocationReq(String key, int i, String str, String str2, String str3, String str4, Integer num, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.key = key;
        this.accesstype = i;
        this.imei = str;
        this.idfa = str2;
        this.smac = str3;
        this.serverip = str4;
        this.cdma = num;
        this.imsi = str5;
        this.network = str6;
        this.tel = str7;
        this.bts = str8;
        this.nearbts = str9;
        this.mmac = str10;
        this.macs = str11;
        this.output = str12;
    }

    public /* synthetic */ LocationReq(String str, int i, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? 0 : num, (i2 & 128) != 0 ? "" : str6, (i2 & 256) != 0 ? "LTE" : str7, (i2 & 512) != 0 ? "" : str8, (i2 & 1024) != 0 ? "" : str9, (i2 & 2048) != 0 ? "" : str10, (i2 & 4096) != 0 ? "" : str11, (i2 & 8192) != 0 ? "" : str12, (i2 & 16384) != 0 ? "json" : str13);
    }

    public final int getAccesstype() {
        return this.accesstype;
    }

    public final Integer getCdma() {
        return this.cdma;
    }

    public final String getIdfa() {
        return this.idfa;
    }

    public final String getImei() {
        return this.imei;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getServerip() {
        return this.serverip;
    }

    public final String getSmac() {
        return this.smac;
    }

    public final String getBts() {
        return this.bts;
    }

    public final String getImsi() {
        return this.imsi;
    }

    public final String getMacs() {
        return this.macs;
    }

    public final String getMmac() {
        return this.mmac;
    }

    public final String getNearbts() {
        return this.nearbts;
    }

    public final String getNetwork() {
        return this.network;
    }

    public final String getOutput() {
        return this.output;
    }

    public final String getTel() {
        return this.tel;
    }

    public String toString() {
        String str = "?";
        for (Field f : getClass().getDeclaredFields()) {
            if (!Intrinsics.areEqual(f.get(this), "")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                Intrinsics.checkExpressionValueIsNotNull(f, "f");
                sb.append(f.getName());
                sb.append("=");
                sb.append(f.get(this));
                sb.append("&");
                str = sb.toString();
            }
        }
        return str.toString();
    }
}
