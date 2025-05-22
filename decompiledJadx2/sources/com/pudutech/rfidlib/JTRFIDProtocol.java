package com.pudutech.rfidlib;

import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.UStringsKt;

/* compiled from: JTRFIDProtocol.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/rfidlib/JTRFIDProtocol;", "Lcom/pudutech/rfidlib/RFIDProtocol;", "()V", "parserData", "", "rawData", "", "listener", "Lcom/pudutech/rfidlib/RFIDListener;", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class JTRFIDProtocol implements RFIDProtocol {
    @Override // com.pudutech.rfidlib.RFIDProtocol
    public void parserData(byte[] rawData, RFIDListener listener) {
        Intrinsics.checkParameterIsNotNull(rawData, "rawData");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        ByteBuffer wrap = ByteBuffer.wrap(rawData);
        byte[] bArr = {85, PrinterUtils.BarCode.CODE128, 68, 58, 32};
        byte[] bArr2 = new byte[bArr.length];
        wrap.get(bArr2, 0, bArr.length);
        if (Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = new byte[0];
            for (int i = 0; i < 4; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append((char) wrap.get());
                sb.append((char) wrap.get());
                bArr3 = ArraysKt.plus(bArr3, (byte) UStringsKt.toUInt(sb.toString(), 16));
            }
            listener.onUID(true, bArr3);
        }
    }
}
