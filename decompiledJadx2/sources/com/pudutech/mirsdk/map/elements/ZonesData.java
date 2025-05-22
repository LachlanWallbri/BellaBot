package com.pudutech.mirsdk.map.elements;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ZonesData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u001e\u0010!\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001d¨\u0006$"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/ZonesData;", "", "()V", "id", "", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "maxSpeed", "", "getMaxSpeed", "()Ljava/lang/Double;", "setMaxSpeed", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "nodes", "", "", "getNodes", "()[[D", "setNodes", "([[D)V", "[[D", "shieldMode", "", "getShieldMode", "()Ljava/lang/Integer;", "setShieldMode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "type", "getType", ES6Iterator.VALUE_PROPERTY, "getValue", "setValue", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ZonesData {
    private String id;
    private Double maxSpeed;
    private double[][] nodes;
    private Integer shieldMode;
    private final String type;
    private Integer value;

    public final String getType() {
        return this.type;
    }

    public final double[][] getNodes() {
        return this.nodes;
    }

    public final void setNodes(double[][] dArr) {
        this.nodes = dArr;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public final void setMaxSpeed(Double d) {
        this.maxSpeed = d;
    }

    public final Integer getValue() {
        return this.value;
    }

    public final void setValue(Integer num) {
        this.value = num;
    }

    public final Integer getShieldMode() {
        return this.shieldMode;
    }

    public final void setShieldMode(Integer num) {
        this.shieldMode = num;
    }
}
