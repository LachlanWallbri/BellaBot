package io.grpc;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import io.grpc.Codec;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DecompressorRegistry {
    static final Joiner ACCEPT_ENCODING_JOINER = Joiner.m608on(',');
    private static final DecompressorRegistry DEFAULT_INSTANCE = emptyInstance().with(new Codec.Gzip(), true).with(Codec.Identity.NONE, false);
    private final byte[] advertisedDecompressors;
    private final Map<String, DecompressorInfo> decompressors;

    public static DecompressorRegistry emptyInstance() {
        return new DecompressorRegistry();
    }

    public static DecompressorRegistry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public DecompressorRegistry with(Decompressor decompressor, boolean z) {
        return new DecompressorRegistry(decompressor, z, this);
    }

    private DecompressorRegistry(Decompressor decompressor, boolean z, DecompressorRegistry decompressorRegistry) {
        String messageEncoding = decompressor.getMessageEncoding();
        Preconditions.checkArgument(!messageEncoding.contains(","), "Comma is currently not allowed in message encoding");
        int size = decompressorRegistry.decompressors.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap(decompressorRegistry.decompressors.containsKey(decompressor.getMessageEncoding()) ? size : size + 1);
        for (DecompressorInfo decompressorInfo : decompressorRegistry.decompressors.values()) {
            String messageEncoding2 = decompressorInfo.decompressor.getMessageEncoding();
            if (!messageEncoding2.equals(messageEncoding)) {
                linkedHashMap.put(messageEncoding2, new DecompressorInfo(decompressorInfo.decompressor, decompressorInfo.advertised));
            }
        }
        linkedHashMap.put(messageEncoding, new DecompressorInfo(decompressor, z));
        this.decompressors = Collections.unmodifiableMap(linkedHashMap);
        this.advertisedDecompressors = ACCEPT_ENCODING_JOINER.join(getAdvertisedMessageEncodings()).getBytes(Charset.forName("US-ASCII"));
    }

    private DecompressorRegistry() {
        this.decompressors = new LinkedHashMap(0);
        this.advertisedDecompressors = new byte[0];
    }

    public Set<String> getKnownMessageEncodings() {
        return this.decompressors.keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getRawAdvertisedMessageEncodings() {
        return this.advertisedDecompressors;
    }

    public Set<String> getAdvertisedMessageEncodings() {
        HashSet hashSet = new HashSet(this.decompressors.size());
        for (Map.Entry<String, DecompressorInfo> entry : this.decompressors.entrySet()) {
            if (entry.getValue().advertised) {
                hashSet.add(entry.getKey());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Nullable
    public Decompressor lookupDecompressor(String str) {
        DecompressorInfo decompressorInfo = this.decompressors.get(str);
        if (decompressorInfo != null) {
            return decompressorInfo.decompressor;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class DecompressorInfo {
        final boolean advertised;
        final Decompressor decompressor;

        DecompressorInfo(Decompressor decompressor, boolean z) {
            this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "decompressor");
            this.advertised = z;
        }
    }
}
