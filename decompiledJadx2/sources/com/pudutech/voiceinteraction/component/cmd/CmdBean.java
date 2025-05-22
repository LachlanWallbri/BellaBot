package com.pudutech.voiceinteraction.component.cmd;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CmdBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012&\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J)\u0010\u0012\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003J=\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032(\b\u0002\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR:\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "", "intent", "Lcom/pudutech/voiceinteraction/component/cmd/TriggerIntent;", "v", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "(Lcom/pudutech/voiceinteraction/component/cmd/TriggerIntent;Ljava/util/HashMap;)V", "getIntent", "()Lcom/pudutech/voiceinteraction/component/cmd/TriggerIntent;", "setIntent", "(Lcom/pudutech/voiceinteraction/component/cmd/TriggerIntent;)V", "getV", "()Ljava/util/HashMap;", "setV", "(Ljava/util/HashMap;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CmdBean {
    private TriggerIntent intent;
    private HashMap<String, String> v;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CmdBean copy$default(CmdBean cmdBean, TriggerIntent triggerIntent, HashMap hashMap, int i, Object obj) {
        if ((i & 1) != 0) {
            triggerIntent = cmdBean.intent;
        }
        if ((i & 2) != 0) {
            hashMap = cmdBean.v;
        }
        return cmdBean.copy(triggerIntent, hashMap);
    }

    /* renamed from: component1, reason: from getter */
    public final TriggerIntent getIntent() {
        return this.intent;
    }

    public final HashMap<String, String> component2() {
        return this.v;
    }

    public final CmdBean copy(TriggerIntent intent, HashMap<String, String> v) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        return new CmdBean(intent, v);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CmdBean)) {
            return false;
        }
        CmdBean cmdBean = (CmdBean) other;
        return Intrinsics.areEqual(this.intent, cmdBean.intent) && Intrinsics.areEqual(this.v, cmdBean.v);
    }

    public int hashCode() {
        TriggerIntent triggerIntent = this.intent;
        int hashCode = (triggerIntent != null ? triggerIntent.hashCode() : 0) * 31;
        HashMap<String, String> hashMap = this.v;
        return hashCode + (hashMap != null ? hashMap.hashCode() : 0);
    }

    public String toString() {
        return "CmdBean(intent=" + this.intent + ", v=" + this.v + ")";
    }

    public CmdBean(TriggerIntent intent, HashMap<String, String> hashMap) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        this.intent = intent;
        this.v = hashMap;
    }

    public final TriggerIntent getIntent() {
        return this.intent;
    }

    public final HashMap<String, String> getV() {
        return this.v;
    }

    public final void setIntent(TriggerIntent triggerIntent) {
        Intrinsics.checkParameterIsNotNull(triggerIntent, "<set-?>");
        this.intent = triggerIntent;
    }

    public final void setV(HashMap<String, String> hashMap) {
        this.v = hashMap;
    }
}
