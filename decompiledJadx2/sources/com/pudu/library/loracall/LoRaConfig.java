package com.pudu.library.loracall;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoRaConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003JY\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\bHÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0014\"\u0004\b\u001b\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000e¨\u0006,"}, m3961d2 = {"Lcom/pudu/library/loracall/LoRaConfig;", "", "ID_VENDOR", "", "ID_PRODUCT", "PRODUCT", "INTERFACE_NUN", "BAUD_RATE", "", "FLAGS", "deviceType", "RESET_PIN", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;)V", "getBAUD_RATE", "()I", "setBAUD_RATE", "(I)V", "getFLAGS", "setFLAGS", "getID_PRODUCT", "()Ljava/lang/String;", "setID_PRODUCT", "(Ljava/lang/String;)V", "getID_VENDOR", "getINTERFACE_NUN", "setINTERFACE_NUN", "getPRODUCT", "setPRODUCT", "getRESET_PIN", "getDeviceType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoRaConfig {
    private int BAUD_RATE;
    private int FLAGS;
    private String ID_PRODUCT;
    private final String ID_VENDOR;
    private String INTERFACE_NUN;
    private String PRODUCT;
    private final String RESET_PIN;
    private final int deviceType;

    /* renamed from: component1, reason: from getter */
    public final String getID_VENDOR() {
        return this.ID_VENDOR;
    }

    /* renamed from: component2, reason: from getter */
    public final String getID_PRODUCT() {
        return this.ID_PRODUCT;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPRODUCT() {
        return this.PRODUCT;
    }

    /* renamed from: component4, reason: from getter */
    public final String getINTERFACE_NUN() {
        return this.INTERFACE_NUN;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBAUD_RATE() {
        return this.BAUD_RATE;
    }

    /* renamed from: component6, reason: from getter */
    public final int getFLAGS() {
        return this.FLAGS;
    }

    /* renamed from: component7, reason: from getter */
    public final int getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: component8, reason: from getter */
    public final String getRESET_PIN() {
        return this.RESET_PIN;
    }

    public final LoRaConfig copy(String ID_VENDOR, String ID_PRODUCT, String PRODUCT, String INTERFACE_NUN, int BAUD_RATE, int FLAGS, int deviceType, String RESET_PIN) {
        Intrinsics.checkParameterIsNotNull(ID_VENDOR, "ID_VENDOR");
        Intrinsics.checkParameterIsNotNull(ID_PRODUCT, "ID_PRODUCT");
        Intrinsics.checkParameterIsNotNull(PRODUCT, "PRODUCT");
        Intrinsics.checkParameterIsNotNull(INTERFACE_NUN, "INTERFACE_NUN");
        Intrinsics.checkParameterIsNotNull(RESET_PIN, "RESET_PIN");
        return new LoRaConfig(ID_VENDOR, ID_PRODUCT, PRODUCT, INTERFACE_NUN, BAUD_RATE, FLAGS, deviceType, RESET_PIN);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoRaConfig)) {
            return false;
        }
        LoRaConfig loRaConfig = (LoRaConfig) other;
        return Intrinsics.areEqual(this.ID_VENDOR, loRaConfig.ID_VENDOR) && Intrinsics.areEqual(this.ID_PRODUCT, loRaConfig.ID_PRODUCT) && Intrinsics.areEqual(this.PRODUCT, loRaConfig.PRODUCT) && Intrinsics.areEqual(this.INTERFACE_NUN, loRaConfig.INTERFACE_NUN) && this.BAUD_RATE == loRaConfig.BAUD_RATE && this.FLAGS == loRaConfig.FLAGS && this.deviceType == loRaConfig.deviceType && Intrinsics.areEqual(this.RESET_PIN, loRaConfig.RESET_PIN);
    }

    public int hashCode() {
        String str = this.ID_VENDOR;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.ID_PRODUCT;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.PRODUCT;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.INTERFACE_NUN;
        int hashCode4 = (((((((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + Integer.hashCode(this.BAUD_RATE)) * 31) + Integer.hashCode(this.FLAGS)) * 31) + Integer.hashCode(this.deviceType)) * 31;
        String str5 = this.RESET_PIN;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "LoRaConfig(ID_VENDOR=" + this.ID_VENDOR + ", ID_PRODUCT=" + this.ID_PRODUCT + ", PRODUCT=" + this.PRODUCT + ", INTERFACE_NUN=" + this.INTERFACE_NUN + ", BAUD_RATE=" + this.BAUD_RATE + ", FLAGS=" + this.FLAGS + ", deviceType=" + this.deviceType + ", RESET_PIN=" + this.RESET_PIN + ")";
    }

    public LoRaConfig(String ID_VENDOR, String ID_PRODUCT, String PRODUCT, String INTERFACE_NUN, int i, int i2, int i3, String RESET_PIN) {
        Intrinsics.checkParameterIsNotNull(ID_VENDOR, "ID_VENDOR");
        Intrinsics.checkParameterIsNotNull(ID_PRODUCT, "ID_PRODUCT");
        Intrinsics.checkParameterIsNotNull(PRODUCT, "PRODUCT");
        Intrinsics.checkParameterIsNotNull(INTERFACE_NUN, "INTERFACE_NUN");
        Intrinsics.checkParameterIsNotNull(RESET_PIN, "RESET_PIN");
        this.ID_VENDOR = ID_VENDOR;
        this.ID_PRODUCT = ID_PRODUCT;
        this.PRODUCT = PRODUCT;
        this.INTERFACE_NUN = INTERFACE_NUN;
        this.BAUD_RATE = i;
        this.FLAGS = i2;
        this.deviceType = i3;
        this.RESET_PIN = RESET_PIN;
    }

    public final String getID_VENDOR() {
        return this.ID_VENDOR;
    }

    public final String getID_PRODUCT() {
        return this.ID_PRODUCT;
    }

    public final void setID_PRODUCT(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ID_PRODUCT = str;
    }

    public final String getPRODUCT() {
        return this.PRODUCT;
    }

    public final void setPRODUCT(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.PRODUCT = str;
    }

    public final String getINTERFACE_NUN() {
        return this.INTERFACE_NUN;
    }

    public final void setINTERFACE_NUN(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.INTERFACE_NUN = str;
    }

    public final int getBAUD_RATE() {
        return this.BAUD_RATE;
    }

    public final void setBAUD_RATE(int i) {
        this.BAUD_RATE = i;
    }

    public final int getFLAGS() {
        return this.FLAGS;
    }

    public final void setFLAGS(int i) {
        this.FLAGS = i;
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public /* synthetic */ LoRaConfig(String str, String str2, String str3, String str4, int i, int i2, int i3, String str5, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "10c4" : str, (i4 & 2) != 0 ? "ea60" : str2, (i4 & 4) != 0 ? "CP2102N USB to UART Bridge Controller" : str3, (i4 & 8) != 0 ? "ttyUSB0" : str4, (i4 & 16) != 0 ? 115200 : i, (i4 & 32) != 0 ? 0 : i2, i3, (i4 & 128) != 0 ? "/sys/class/pudutech/pudu_lora_power/pudu_lora_reset_pin" : str5);
    }

    public final String getRESET_PIN() {
        return this.RESET_PIN;
    }
}
