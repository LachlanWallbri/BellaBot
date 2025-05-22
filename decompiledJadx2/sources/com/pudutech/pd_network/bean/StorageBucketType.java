package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: storage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageBucketType;", "", "type", "", "(I)V", "getType", "()I", "Default", "Media", "Lcom/pudutech/pd_network/bean/StorageBucketType$Default;", "Lcom/pudutech/pd_network/bean/StorageBucketType$Media;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class StorageBucketType {
    private final int type;

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageBucketType$Default;", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Default extends StorageBucketType {
        public static final Default INSTANCE = new Default();

        private Default() {
            super(0, null);
        }
    }

    private StorageBucketType(int i) {
        this.type = i;
    }

    public /* synthetic */ StorageBucketType(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getType() {
        return this.type;
    }

    /* compiled from: storage.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageBucketType$Media;", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Media extends StorageBucketType {
        public static final Media INSTANCE = new Media();

        private Media() {
            super(1, null);
        }
    }
}
