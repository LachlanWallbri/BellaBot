package com.pudutech.rfidlib;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RFIDListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/rfidlib/RFIDListener;", "", "onData", "", "rawData", "", "onError", "err_no", "", "onReadBlockData", "success", "", "blockData", "onUID", "cardID", "onWriteBlockData", "RFIDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface RFIDListener {

    /* compiled from: RFIDListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static void onData(RFIDListener rFIDListener, byte[] rawData) {
            Intrinsics.checkParameterIsNotNull(rawData, "rawData");
        }

        public static void onError(RFIDListener rFIDListener, int i) {
        }

        public static void onReadBlockData(RFIDListener rFIDListener, boolean z, byte[] bArr) {
        }

        public static void onUID(RFIDListener rFIDListener, boolean z, byte[] bArr) {
        }

        public static void onWriteBlockData(RFIDListener rFIDListener, boolean z) {
        }
    }

    void onData(byte[] rawData);

    void onError(int err_no);

    void onReadBlockData(boolean success, byte[] blockData);

    void onUID(boolean success, byte[] cardID);

    void onWriteBlockData(boolean success);
}
