package com.pudutech.bumblebee.business.ims;

import com.pudutech.bumblebee.business.ims.interf.I_IMSClient;
import com.pudutech.bumblebee.business.ims.lora.LoRaClient;
import kotlin.Metadata;

/* compiled from: IMSClientFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/IMSClientFactory;", "", "()V", "getIMSClient", "Lcom/pudutech/bumblebee/business/ims/interf/I_IMSClient;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSClientFactory {
    public static final IMSClientFactory INSTANCE = new IMSClientFactory();

    private IMSClientFactory() {
    }

    public final I_IMSClient getIMSClient() {
        return LoRaClient.INSTANCE.getINSTANCE();
    }
}
