package com.pudutech.antichannelconflict.location.network;

import kotlin.Metadata;

/* compiled from: LocationBTS.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003Jc\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\b\u0010%\u001a\u00020&H\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006'"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/network/LocationBTS;", "", "mcc", "", "mnc", "lac", "band", "arfcn", "cellid", "dBm", "pci", "rsrq", "(IIIIIIIII)V", "getArfcn", "()I", "getBand", "getCellid", "getDBm", "getLac", "getMcc", "getMnc", "getPci", "getRsrq", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LocationBTS {
    private final int arfcn;
    private final int band;
    private final int cellid;
    private final int dBm;
    private final int lac;
    private final int mcc;
    private final int mnc;
    private final int pci;
    private final int rsrq;

    /* renamed from: component1, reason: from getter */
    public final int getMcc() {
        return this.mcc;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMnc() {
        return this.mnc;
    }

    /* renamed from: component3, reason: from getter */
    public final int getLac() {
        return this.lac;
    }

    /* renamed from: component4, reason: from getter */
    public final int getBand() {
        return this.band;
    }

    /* renamed from: component5, reason: from getter */
    public final int getArfcn() {
        return this.arfcn;
    }

    /* renamed from: component6, reason: from getter */
    public final int getCellid() {
        return this.cellid;
    }

    /* renamed from: component7, reason: from getter */
    public final int getDBm() {
        return this.dBm;
    }

    /* renamed from: component8, reason: from getter */
    public final int getPci() {
        return this.pci;
    }

    /* renamed from: component9, reason: from getter */
    public final int getRsrq() {
        return this.rsrq;
    }

    public final LocationBTS copy(int mcc, int mnc, int lac, int band, int arfcn, int cellid, int dBm, int pci, int rsrq) {
        return new LocationBTS(mcc, mnc, lac, band, arfcn, cellid, dBm, pci, rsrq);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationBTS)) {
            return false;
        }
        LocationBTS locationBTS = (LocationBTS) other;
        return this.mcc == locationBTS.mcc && this.mnc == locationBTS.mnc && this.lac == locationBTS.lac && this.band == locationBTS.band && this.arfcn == locationBTS.arfcn && this.cellid == locationBTS.cellid && this.dBm == locationBTS.dBm && this.pci == locationBTS.pci && this.rsrq == locationBTS.rsrq;
    }

    public int hashCode() {
        return (((((((((((((((this.mcc * 31) + this.mnc) * 31) + this.lac) * 31) + this.band) * 31) + this.arfcn) * 31) + this.cellid) * 31) + this.dBm) * 31) + this.pci) * 31) + this.rsrq;
    }

    public LocationBTS(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.mcc = i;
        this.mnc = i2;
        this.lac = i3;
        this.band = i4;
        this.arfcn = i5;
        this.cellid = i6;
        this.dBm = i7;
        this.pci = i8;
        this.rsrq = i9;
    }

    public final int getArfcn() {
        return this.arfcn;
    }

    public final int getBand() {
        return this.band;
    }

    public final int getCellid() {
        return this.cellid;
    }

    public final int getDBm() {
        return this.dBm;
    }

    public final int getLac() {
        return this.lac;
    }

    public final int getMcc() {
        return this.mcc;
    }

    public final int getMnc() {
        return this.mnc;
    }

    public final int getPci() {
        return this.pci;
    }

    public final int getRsrq() {
        return this.rsrq;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mcc);
        sb.append(',');
        sb.append(this.mnc);
        sb.append(',');
        sb.append(this.lac);
        sb.append(',');
        sb.append(this.cellid);
        sb.append(',');
        sb.append(this.dBm);
        return sb.toString();
    }
}
