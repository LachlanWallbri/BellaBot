package com.pudutech.bumblebee.presenter.report_cloud.protocol.upgrade;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Upgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/upgrade/Upgrade;", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/Head;", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "status", "", "getStatus", "()Z", "setStatus", "(Z)V", "vername_from", "getVername_from", "setVername_from", "vername_to", "getVername_to", "setVername_to", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Upgrade extends Head {
    private boolean status;
    private String name = "";
    private String vername_from = "";
    private String vername_to = "";

    public Upgrade() {
        this.type = "upgrade";
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getVername_from() {
        return this.vername_from;
    }

    public final void setVername_from(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.vername_from = str;
    }

    public final String getVername_to() {
        return this.vername_to;
    }

    public final void setVername_to(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.vername_to = str;
    }

    public final boolean getStatus() {
        return this.status;
    }

    public final void setStatus(boolean z) {
        this.status = z;
    }
}
