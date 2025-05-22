package com.pudutech.location.net;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowCardBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/location/net/Data;", "", "card_id", "", RequestParameters.SUBRESOURCE_LOCATION, "", "(Ljava/lang/String;Ljava/util/List;)V", "getCard_id", "()Ljava/lang/String;", "getLocation", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Data {
    private final String card_id;
    private final List<String> location;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Data copy$default(Data data, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = data.card_id;
        }
        if ((i & 2) != 0) {
            list = data.location;
        }
        return data.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCard_id() {
        return this.card_id;
    }

    public final List<String> component2() {
        return this.location;
    }

    public final Data copy(String card_id, List<String> location) {
        Intrinsics.checkParameterIsNotNull(card_id, "card_id");
        Intrinsics.checkParameterIsNotNull(location, "location");
        return new Data(card_id, location);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Data)) {
            return false;
        }
        Data data = (Data) other;
        return Intrinsics.areEqual(this.card_id, data.card_id) && Intrinsics.areEqual(this.location, data.location);
    }

    public int hashCode() {
        String str = this.card_id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<String> list = this.location;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "Data(card_id=" + this.card_id + ", location=" + this.location + ")";
    }

    public Data(String card_id, List<String> location) {
        Intrinsics.checkParameterIsNotNull(card_id, "card_id");
        Intrinsics.checkParameterIsNotNull(location, "location");
        this.card_id = card_id;
        this.location = location;
    }

    public final String getCard_id() {
        return this.card_id;
    }

    public final List<String> getLocation() {
        return this.location;
    }
}
