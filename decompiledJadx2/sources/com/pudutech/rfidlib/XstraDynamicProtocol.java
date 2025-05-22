package com.pudutech.rfidlib;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: XstraDynamicProtocol.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/rfidlib/XstraDynamicProtocol;", "Lcom/pudutech/rfidlib/RFIDProtocol;", "()V", "parserData", "", "rawData", "", "listener", "Lcom/pudutech/rfidlib/RFIDListener;", "XDErrorCode", "XDFunctionCode", "XDStateCode", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class XstraDynamicProtocol implements RFIDProtocol {
    @Override // com.pudutech.rfidlib.RFIDProtocol
    public void parserData(byte[] rawData, RFIDListener listener) {
        Intrinsics.checkParameterIsNotNull(rawData, "rawData");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        ByteBuffer wrap = ByteBuffer.wrap(rawData);
        if (wrap.get() != 36) {
            listener.onError(0);
            return;
        }
        if (wrap.remaining() < wrap.get() + (wrap.get() * 128)) {
            listener.onError(1);
            return;
        }
        int m4761constructorimpl = UShort.m4761constructorimpl(wrap.get()) & UShort.MAX_VALUE;
        UShort.m4761constructorimpl(wrap.get());
        int i = wrap.get() + (wrap.get() * 128);
        byte[] bArr = new byte[i];
        wrap.get(bArr, 0, i);
        if (m4761constructorimpl == 32) {
            listener.onUID(i > 0, bArr);
        } else if (m4761constructorimpl == 33) {
            listener.onReadBlockData(i > 0, bArr);
        } else {
            listener.onError(2);
        }
    }

    /* compiled from: XstraDynamicProtocol.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/rfidlib/XstraDynamicProtocol$XDErrorCode;", "", "()V", "CRC_ERROR", "", "DATA_LENGTH_ERROR", "FUNCTION_CODE_ERROR", "HEAD_ERROR", "NO_CARD", "PACKAGE_LENGTH_ERROR", "PASSWORD_ERROR", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class XDErrorCode {
        public static final int CRC_ERROR = 4;
        public static final int DATA_LENGTH_ERROR = 3;
        public static final int FUNCTION_CODE_ERROR = 2;
        public static final int HEAD_ERROR = 0;
        public static final XDErrorCode INSTANCE = new XDErrorCode();
        public static final int NO_CARD = 5;
        public static final int PACKAGE_LENGTH_ERROR = 1;
        public static final int PASSWORD_ERROR = 6;

        private XDErrorCode() {
        }
    }

    /* compiled from: XstraDynamicProtocol.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/rfidlib/XstraDynamicProtocol$XDFunctionCode;", "", "()V", "ReadBlock", "", "ReadUID", "WriteBlock", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class XDFunctionCode {
        public static final XDFunctionCode INSTANCE = new XDFunctionCode();
        public static final int ReadBlock = 33;
        public static final int ReadUID = 32;
        public static final int WriteBlock = 34;

        private XDFunctionCode() {
        }
    }

    /* compiled from: XstraDynamicProtocol.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/rfidlib/XstraDynamicProtocol$XDStateCode;", "", "()V", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class XDStateCode {
        public static final XDStateCode INSTANCE = new XDStateCode();

        private XDStateCode() {
        }
    }
}
