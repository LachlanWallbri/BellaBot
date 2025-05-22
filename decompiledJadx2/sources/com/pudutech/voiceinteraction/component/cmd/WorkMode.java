package com.pudutech.voiceinteraction.component.cmd;

import kotlin.Metadata;

/* compiled from: CmdBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/WorkMode;", "", "mode", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMode", "()Ljava/lang/String;", "Delivery", "Guide", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum WorkMode {
    Delivery("delivery"),
    Guide("tout");

    private final String mode;

    WorkMode(String str) {
        this.mode = str;
    }

    public final String getMode() {
        return this.mode;
    }
}
