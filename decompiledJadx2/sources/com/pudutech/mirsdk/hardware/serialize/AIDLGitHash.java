package com.pudutech.mirsdk.hardware.serialize;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: AIDLGitHash.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/AIDLGitHash;", "", "()V", "getGitHash", "", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AIDLGitHash {
    public static final AIDLGitHash INSTANCE = new AIDLGitHash();

    public final String getGitHash() {
        return "{\"HardwareAIDL\": \"commit: a8ae60d, auth: “songleiquan”<“songleiquan@pudutech.com”>, time: “Fri May 14 07:26:31 2021 -0400”\"}";
    }

    private AIDLGitHash() {
    }
}
