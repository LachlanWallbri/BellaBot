package com.pudutech.pd_network.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: gateway.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayPlace;", "", AIUIConstant.KEY_TAG, "", "(Ljava/lang/String;)V", "getTag", "()Ljava/lang/String;", "China", "Companion", "UnKnow", "Lcom/pudutech/pd_network/bean/GatewayPlace$UnKnow;", "Lcom/pudutech/pd_network/bean/GatewayPlace$China;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class GatewayPlace {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String tag;

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayPlace$UnKnow;", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class UnKnow extends GatewayPlace {
        public static final UnKnow INSTANCE = new UnKnow();

        private UnKnow() {
            super("UnKnow", null);
        }
    }

    private GatewayPlace(String str) {
        this.tag = str;
    }

    public /* synthetic */ GatewayPlace(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final String getTag() {
        return this.tag;
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayPlace$China;", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "()V", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class China extends GatewayPlace {
        public static final China INSTANCE = new China();

        private China() {
            super("China", null);
        }
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayPlace$Companion;", "", "()V", "fromTag", "Lcom/pudutech/pd_network/bean/GatewayPlace;", AIUIConstant.KEY_TAG, "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final GatewayPlace fromTag(String tag) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            if (Intrinsics.areEqual(tag, China.INSTANCE.getTag())) {
                return China.INSTANCE;
            }
            return UnKnow.INSTANCE;
        }
    }
}
