package io.netty.handler.codec.compression;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
final class Lz4Constants {
    static final int BLOCK_TYPE_COMPRESSED = 32;
    static final int BLOCK_TYPE_NON_COMPRESSED = 16;
    static final int CHECKSUM_OFFSET = 17;
    static final int COMPRESSED_LENGTH_OFFSET = 9;
    static final int COMPRESSION_LEVEL_BASE = 10;
    static final int DECOMPRESSED_LENGTH_OFFSET = 13;
    static final int DEFAULT_BLOCK_SIZE = 65536;
    static final int DEFAULT_SEED = -1756908916;
    static final int HEADER_LENGTH = 21;
    static final long MAGIC_NUMBER = 5501767354678207339L;
    static final int MAX_BLOCK_SIZE = 33554432;
    static final int MIN_BLOCK_SIZE = 64;
    static final int TOKEN_OFFSET = 8;

    private Lz4Constants() {
    }
}
