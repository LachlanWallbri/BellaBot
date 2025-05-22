package com.pudutech.bumblebee.business.ims.config;

import java.math.BigDecimal;
import kotlin.Metadata;

/* compiled from: LoRaChannelConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/config/LoRaChannelConfig;", "", "()V", "defaultFrequency", "Ljava/math/BigDecimal;", "getDefaultFrequency", "()Ljava/math/BigDecimal;", "eachGroupFrequency", "getEachGroupFrequency", "endFrequency", "getEndFrequency", "maxFrequency", "getMaxFrequency", "minFrequency", "getMinFrequency", "startFrequency", "getStartFrequency", "stepFrequency", "getStepFrequency", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaChannelConfig {
    public static final LoRaChannelConfig INSTANCE = new LoRaChannelConfig();
    private static final BigDecimal minFrequency = new BigDecimal(String.valueOf(464.0f));
    private static final BigDecimal maxFrequency = new BigDecimal(String.valueOf(515.0f));
    private static final BigDecimal startFrequency = new BigDecimal(String.valueOf(475.0f));
    private static final BigDecimal endFrequency = new BigDecimal(String.valueOf(505.0f));
    private static final BigDecimal defaultFrequency = new BigDecimal(String.valueOf(490.0f));
    private static final BigDecimal eachGroupFrequency = new BigDecimal(String.valueOf(1.4f));
    private static final BigDecimal stepFrequency = new BigDecimal(String.valueOf(0.2f));

    private LoRaChannelConfig() {
    }

    public final BigDecimal getMinFrequency() {
        return minFrequency;
    }

    public final BigDecimal getMaxFrequency() {
        return maxFrequency;
    }

    public final BigDecimal getStartFrequency() {
        return startFrequency;
    }

    public final BigDecimal getEndFrequency() {
        return endFrequency;
    }

    public final BigDecimal getDefaultFrequency() {
        return defaultFrequency;
    }

    public final BigDecimal getEachGroupFrequency() {
        return eachGroupFrequency;
    }

    public final BigDecimal getStepFrequency() {
        return stepFrequency;
    }
}
