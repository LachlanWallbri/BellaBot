package com.pudutech.bumblebee.business.map_task;

import kotlin.Metadata;

/* compiled from: DestinationType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/map_task/DestinationType;", "", "()V", "dining_outlet", "", "getDining_outlet", "()Ljava/lang/String;", DestinationType.dishwashing, "getDishwashing", "table", "getTable", "transit", "getTransit", "unknown", "getUnknown", "usher", "getUsher", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DestinationType {
    public static final DestinationType INSTANCE = new DestinationType();
    private static final String unknown = "unknown";
    private static final String table = "table";
    private static final String dining_outlet = "dining_outlet";
    private static final String transit = "transit";
    private static final String dishwashing = dishwashing;
    private static final String dishwashing = dishwashing;
    private static final String usher = "usher";

    private DestinationType() {
    }

    public final String getUnknown() {
        return unknown;
    }

    public final String getTable() {
        return table;
    }

    public final String getDining_outlet() {
        return dining_outlet;
    }

    public final String getTransit() {
        return transit;
    }

    public final String getDishwashing() {
        return dishwashing;
    }

    public final String getUsher() {
        return usher;
    }
}
