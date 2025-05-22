package com.pudutech.gatecontrollerlib;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* compiled from: FrameBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\u0016\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\f\u0010\r\u001a\u00020\u0004*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/FrameBuilder;", "", "()V", TypedValues.Attributes.S_FRAME, "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "getSeq", "", "openGate", "timeout", "", MapElement.Key.DIR, "", "sumCheck", "Companion", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FrameBuilder {
    private static int seq;
    private byte[] frame;

    public FrameBuilder() {
        this.frame = new byte[]{80, 85, 68, 85, 71, 65, 84, 69, 0};
        this.frame = ArraysKt.plus(this.frame, getSeq());
    }

    private final byte getSeq() {
        int i = seq;
        seq = i + 1;
        if (seq >= 256) {
            seq = 0;
        }
        return (byte) i;
    }

    public final FrameBuilder openGate(int timeout, boolean dir) {
        if (timeout < 0) {
            timeout = 0;
        }
        this.frame = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(this.frame, TarConstants.LF_CHR), (byte) 3), (byte) (timeout / 256)), (byte) (timeout % 256)), dir ? (byte) 1 : (byte) 0);
        return this;
    }

    public final byte[] build() {
        return sumCheck(this.frame);
    }

    private final byte[] sumCheck(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i = Integer.remainderUnsigned(UInt.m4595constructorimpl(i + UInt.m4595constructorimpl(b)), 256);
        }
        return ArraysKt.plus(bArr, (byte) i);
    }
}
