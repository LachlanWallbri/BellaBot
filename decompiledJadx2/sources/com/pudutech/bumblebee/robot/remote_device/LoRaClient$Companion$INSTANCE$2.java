package com.pudutech.bumblebee.robot.remote_device;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: LoRaClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bumblebee/robot/remote_device/LoRaClient;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class LoRaClient$Companion$INSTANCE$2 extends Lambda implements Function0<LoRaClient> {
    public static final LoRaClient$Companion$INSTANCE$2 INSTANCE = new LoRaClient$Companion$INSTANCE$2();

    LoRaClient$Companion$INSTANCE$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final LoRaClient invoke() {
        return new LoRaClient(null);
    }
}
