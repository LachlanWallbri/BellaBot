package org.xerial.snappy;

/* loaded from: classes9.dex */
public enum SnappyErrorCode {
    UNKNOWN(0),
    FAILED_TO_LOAD_NATIVE_LIBRARY(1),
    PARSING_ERROR(2),
    NOT_A_DIRECT_BUFFER(3),
    OUT_OF_MEMORY(4),
    FAILED_TO_UNCOMPRESS(5),
    EMPTY_INPUT(6),
    INCOMPATIBLE_VERSION(7),
    INVALID_CHUNK_SIZE(8),
    UNSUPPORTED_PLATFORM(9);


    /* renamed from: id */
    public final int f10297id;

    SnappyErrorCode(int i) {
        this.f10297id = i;
    }

    public static SnappyErrorCode getErrorCode(int i) {
        for (SnappyErrorCode snappyErrorCode : values()) {
            if (snappyErrorCode.f10297id == i) {
                return snappyErrorCode;
            }
        }
        return UNKNOWN;
    }

    public static String getErrorMessage(int i) {
        return getErrorCode(i).name();
    }
}
