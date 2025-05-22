package io.grpc;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalDecompressorRegistry {
    private InternalDecompressorRegistry() {
    }

    public static byte[] getRawAdvertisedMessageEncodings(DecompressorRegistry decompressorRegistry) {
        return decompressorRegistry.getRawAdvertisedMessageEncodings();
    }
}
