package com.pudutech.pdmqtt.service;

import com.pudutech.pdmqtt.OnMessageChangeListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageChangeListenerExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/OnMessageChangeEntry;", "", "clientKey", "", "uniqueKey", "lisId", "lis", "Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/pdmqtt/OnMessageChangeListener;)V", "getClientKey", "()Ljava/lang/String;", "getLis", "()Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "getLisId", "getUniqueKey", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class OnMessageChangeEntry {
    private final String clientKey;
    private final OnMessageChangeListener lis;
    private final String lisId;
    private final String uniqueKey;

    public static /* synthetic */ OnMessageChangeEntry copy$default(OnMessageChangeEntry onMessageChangeEntry, String str, String str2, String str3, OnMessageChangeListener onMessageChangeListener, int i, Object obj) {
        if ((i & 1) != 0) {
            str = onMessageChangeEntry.clientKey;
        }
        if ((i & 2) != 0) {
            str2 = onMessageChangeEntry.uniqueKey;
        }
        if ((i & 4) != 0) {
            str3 = onMessageChangeEntry.lisId;
        }
        if ((i & 8) != 0) {
            onMessageChangeListener = onMessageChangeEntry.lis;
        }
        return onMessageChangeEntry.copy(str, str2, str3, onMessageChangeListener);
    }

    /* renamed from: component1, reason: from getter */
    public final String getClientKey() {
        return this.clientKey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUniqueKey() {
        return this.uniqueKey;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLisId() {
        return this.lisId;
    }

    /* renamed from: component4, reason: from getter */
    public final OnMessageChangeListener getLis() {
        return this.lis;
    }

    public final OnMessageChangeEntry copy(String clientKey, String uniqueKey, String lisId, OnMessageChangeListener lis) {
        Intrinsics.checkParameterIsNotNull(clientKey, "clientKey");
        Intrinsics.checkParameterIsNotNull(uniqueKey, "uniqueKey");
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        return new OnMessageChangeEntry(clientKey, uniqueKey, lisId, lis);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnMessageChangeEntry)) {
            return false;
        }
        OnMessageChangeEntry onMessageChangeEntry = (OnMessageChangeEntry) other;
        return Intrinsics.areEqual(this.clientKey, onMessageChangeEntry.clientKey) && Intrinsics.areEqual(this.uniqueKey, onMessageChangeEntry.uniqueKey) && Intrinsics.areEqual(this.lisId, onMessageChangeEntry.lisId) && Intrinsics.areEqual(this.lis, onMessageChangeEntry.lis);
    }

    public int hashCode() {
        String str = this.clientKey;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.uniqueKey;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.lisId;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        OnMessageChangeListener onMessageChangeListener = this.lis;
        return hashCode3 + (onMessageChangeListener != null ? onMessageChangeListener.hashCode() : 0);
    }

    public String toString() {
        return "OnMessageChangeEntry(clientKey=" + this.clientKey + ", uniqueKey=" + this.uniqueKey + ", lisId=" + this.lisId + ", lis=" + this.lis + ")";
    }

    public OnMessageChangeEntry(String clientKey, String uniqueKey, String lisId, OnMessageChangeListener lis) {
        Intrinsics.checkParameterIsNotNull(clientKey, "clientKey");
        Intrinsics.checkParameterIsNotNull(uniqueKey, "uniqueKey");
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.clientKey = clientKey;
        this.uniqueKey = uniqueKey;
        this.lisId = lisId;
        this.lis = lis;
    }

    public final String getClientKey() {
        return this.clientKey;
    }

    public final String getUniqueKey() {
        return this.uniqueKey;
    }

    public final String getLisId() {
        return this.lisId;
    }

    public final OnMessageChangeListener getLis() {
        return this.lis;
    }
}
