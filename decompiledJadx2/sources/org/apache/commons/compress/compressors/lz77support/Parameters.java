package org.apache.commons.compress.compressors.lz77support;

/* loaded from: classes8.dex */
public final class Parameters {
    public static final int TRUE_MIN_BACK_REFERENCE_LENGTH = 3;
    private final boolean lazyMatching;
    private final int lazyThreshold;
    private final int maxBackReferenceLength;
    private final int maxCandidates;
    private final int maxLiteralLength;
    private final int maxOffset;
    private final int minBackReferenceLength;
    private final int niceBackReferenceLength;
    private final int windowSize;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPowerOfTwo(int i) {
        return (i & (i + (-1))) == 0;
    }

    public static Builder builder(int i) {
        return new Builder(i);
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private Boolean lazyMatches;
        private Integer lazyThreshold;
        private int maxBackReferenceLength;
        private Integer maxCandidates;
        private int maxLiteralLength;
        private int maxOffset;
        private int minBackReferenceLength;
        private Integer niceBackReferenceLength;
        private final int windowSize;

        private Builder(int i) {
            if (i < 2 || !Parameters.isPowerOfTwo(i)) {
                throw new IllegalArgumentException("windowSize must be a power of two");
            }
            this.windowSize = i;
            this.minBackReferenceLength = 3;
            int i2 = i - 1;
            this.maxBackReferenceLength = i2;
            this.maxOffset = i2;
            this.maxLiteralLength = i;
        }

        public Builder withMinBackReferenceLength(int i) {
            this.minBackReferenceLength = Math.max(3, i);
            int i2 = this.windowSize;
            int i3 = this.minBackReferenceLength;
            if (i2 < i3) {
                throw new IllegalArgumentException("minBackReferenceLength can't be bigger than windowSize");
            }
            if (this.maxBackReferenceLength < i3) {
                this.maxBackReferenceLength = i3;
            }
            return this;
        }

        public Builder withMaxBackReferenceLength(int i) {
            int i2 = this.minBackReferenceLength;
            if (i >= i2) {
                i2 = Math.min(i, this.windowSize - 1);
            }
            this.maxBackReferenceLength = i2;
            return this;
        }

        public Builder withMaxOffset(int i) {
            this.maxOffset = i < 1 ? this.windowSize - 1 : Math.min(i, this.windowSize - 1);
            return this;
        }

        public Builder withMaxLiteralLength(int i) {
            int min;
            if (i < 1) {
                min = this.windowSize;
            } else {
                min = Math.min(i, this.windowSize);
            }
            this.maxLiteralLength = min;
            return this;
        }

        public Builder withNiceBackReferenceLength(int i) {
            this.niceBackReferenceLength = Integer.valueOf(i);
            return this;
        }

        public Builder withMaxNumberOfCandidates(int i) {
            this.maxCandidates = Integer.valueOf(i);
            return this;
        }

        public Builder withLazyMatching(boolean z) {
            this.lazyMatches = Boolean.valueOf(z);
            return this;
        }

        public Builder withLazyThreshold(int i) {
            this.lazyThreshold = Integer.valueOf(i);
            return this;
        }

        public Builder tunedForSpeed() {
            this.niceBackReferenceLength = Integer.valueOf(Math.max(this.minBackReferenceLength, this.maxBackReferenceLength / 8));
            this.maxCandidates = Integer.valueOf(Math.max(32, this.windowSize / 1024));
            this.lazyMatches = false;
            this.lazyThreshold = Integer.valueOf(this.minBackReferenceLength);
            return this;
        }

        public Builder tunedForCompressionRatio() {
            Integer valueOf = Integer.valueOf(this.maxBackReferenceLength);
            this.lazyThreshold = valueOf;
            this.niceBackReferenceLength = valueOf;
            this.maxCandidates = Integer.valueOf(Math.max(32, this.windowSize / 16));
            this.lazyMatches = true;
            return this;
        }

        public Parameters build() {
            int i;
            int i2;
            Integer num = this.niceBackReferenceLength;
            int intValue = num != null ? num.intValue() : Math.max(this.minBackReferenceLength, this.maxBackReferenceLength / 2);
            Integer num2 = this.maxCandidates;
            int intValue2 = num2 != null ? num2.intValue() : Math.max(256, this.windowSize / 128);
            Boolean bool = this.lazyMatches;
            boolean z = bool == null || bool.booleanValue();
            if (z) {
                Integer num3 = this.lazyThreshold;
                if (num3 == null) {
                    i2 = intValue;
                    return new Parameters(this.windowSize, this.minBackReferenceLength, this.maxBackReferenceLength, this.maxOffset, this.maxLiteralLength, intValue, intValue2, z, i2);
                }
                i = num3.intValue();
            } else {
                i = this.minBackReferenceLength;
            }
            i2 = i;
            return new Parameters(this.windowSize, this.minBackReferenceLength, this.maxBackReferenceLength, this.maxOffset, this.maxLiteralLength, intValue, intValue2, z, i2);
        }
    }

    private Parameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8) {
        this.windowSize = i;
        this.minBackReferenceLength = i2;
        this.maxBackReferenceLength = i3;
        this.maxOffset = i4;
        this.maxLiteralLength = i5;
        this.niceBackReferenceLength = i6;
        this.maxCandidates = i7;
        this.lazyMatching = z;
        this.lazyThreshold = i8;
    }

    public int getWindowSize() {
        return this.windowSize;
    }

    public int getMinBackReferenceLength() {
        return this.minBackReferenceLength;
    }

    public int getMaxBackReferenceLength() {
        return this.maxBackReferenceLength;
    }

    public int getMaxOffset() {
        return this.maxOffset;
    }

    public int getMaxLiteralLength() {
        return this.maxLiteralLength;
    }

    public int getNiceBackReferenceLength() {
        return this.niceBackReferenceLength;
    }

    public int getMaxCandidates() {
        return this.maxCandidates;
    }

    public boolean getLazyMatching() {
        return this.lazyMatching;
    }

    public int getLazyMatchingThreshold() {
        return this.lazyThreshold;
    }
}
