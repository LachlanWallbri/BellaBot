package com.pudutech.voiceinteraction.component.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NLPActionType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0086\u0001\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "", "nlpActionType", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getNlpActionType", "()Ljava/lang/String;", "setNlpActionType", "(Ljava/lang/String;)V", "OPEN_DOOR", "CLOSE_DOOR", "SET_OUT", "RETURN_TO_BASE", "CHARGE", "OPEN_STATIONARY_MODE", "CLOSE_STATIONARY_MODE", "OPEN_PROJECTION", "CLOSE_PROJECTION", "DELIVERY", "GO_WAY", "LEAD_WAY", "OTHER", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum NLPActionType {
    OPEN_DOOR("open_door"),
    CLOSE_DOOR("close_door"),
    SET_OUT("set_out"),
    RETURN_TO_BASE("return_to_base"),
    CHARGE("charge"),
    OPEN_STATIONARY_MODE("open_stationary_mode"),
    CLOSE_STATIONARY_MODE("close_stationary_mode"),
    OPEN_PROJECTION("open_projection"),
    CLOSE_PROJECTION("close_projection"),
    DELIVERY("delivery"),
    GO_WAY("go_way"),
    LEAD_WAY("lead_way"),
    OTHER("other");


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String nlpActionType;

    NLPActionType(String str) {
        this.nlpActionType = str;
    }

    public final String getNlpActionType() {
        return this.nlpActionType;
    }

    public final void setNlpActionType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.nlpActionType = str;
    }

    /* compiled from: NLPActionType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/NLPActionType$Companion;", "", "()V", "match", "Lcom/pudutech/voiceinteraction/component/config/NLPActionType;", "type", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NLPActionType match(String type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return Intrinsics.areEqual(type, NLPActionType.OPEN_DOOR.getNlpActionType()) ? NLPActionType.OPEN_DOOR : Intrinsics.areEqual(type, NLPActionType.CLOSE_DOOR.getNlpActionType()) ? NLPActionType.CLOSE_DOOR : Intrinsics.areEqual(type, NLPActionType.SET_OUT.getNlpActionType()) ? NLPActionType.SET_OUT : Intrinsics.areEqual(type, NLPActionType.RETURN_TO_BASE.getNlpActionType()) ? NLPActionType.RETURN_TO_BASE : Intrinsics.areEqual(type, NLPActionType.CHARGE.getNlpActionType()) ? NLPActionType.CHARGE : Intrinsics.areEqual(type, NLPActionType.OPEN_STATIONARY_MODE.getNlpActionType()) ? NLPActionType.OPEN_STATIONARY_MODE : Intrinsics.areEqual(type, NLPActionType.CLOSE_STATIONARY_MODE.getNlpActionType()) ? NLPActionType.CLOSE_STATIONARY_MODE : Intrinsics.areEqual(type, NLPActionType.CLOSE_PROJECTION.getNlpActionType()) ? NLPActionType.CLOSE_PROJECTION : Intrinsics.areEqual(type, NLPActionType.OPEN_PROJECTION.getNlpActionType()) ? NLPActionType.OPEN_PROJECTION : Intrinsics.areEqual(type, NLPActionType.DELIVERY.getNlpActionType()) ? NLPActionType.DELIVERY : Intrinsics.areEqual(type, NLPActionType.LEAD_WAY.getNlpActionType()) ? NLPActionType.LEAD_WAY : Intrinsics.areEqual(type, NLPActionType.GO_WAY.getNlpActionType()) ? NLPActionType.GO_WAY : NLPActionType.OTHER;
        }
    }
}
