package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Immutable
/* loaded from: classes3.dex */
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f1969c;

    /* renamed from: d */
    private final int f1970d;

    /* renamed from: k0 */
    private final long f1971k0;

    /* renamed from: k1 */
    private final long f1972k1;

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SipHashFunction(int i, int i2, long j, long j2) {
        Preconditions.checkArgument(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        Preconditions.checkArgument(i2 > 0, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.f1969c = i;
        this.f1970d = i2;
        this.f1971k0 = j;
        this.f1972k1 = j2;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new SipHasher(this.f1969c, this.f1970d, this.f1971k0, this.f1972k1);
    }

    public String toString() {
        int i = this.f1969c;
        int i2 = this.f1970d;
        long j = this.f1971k0;
        long j2 = this.f1972k1;
        StringBuilder sb = new StringBuilder(81);
        sb.append("Hashing.sipHash");
        sb.append(i);
        sb.append(i2);
        sb.append("(");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        return this.f1969c == sipHashFunction.f1969c && this.f1970d == sipHashFunction.f1970d && this.f1971k0 == sipHashFunction.f1971k0 && this.f1972k1 == sipHashFunction.f1972k1;
    }

    public int hashCode() {
        return (int) ((((getClass().hashCode() ^ this.f1969c) ^ this.f1970d) ^ this.f1971k0) ^ this.f1972k1);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f1973b;

        /* renamed from: c */
        private final int f1974c;

        /* renamed from: d */
        private final int f1975d;
        private long finalM;

        /* renamed from: v0 */
        private long f1976v0;

        /* renamed from: v1 */
        private long f1977v1;

        /* renamed from: v2 */
        private long f1978v2;

        /* renamed from: v3 */
        private long f1979v3;

        SipHasher(int i, int i2, long j, long j2) {
            super(8);
            this.f1976v0 = 8317987319222330741L;
            this.f1977v1 = 7237128888997146477L;
            this.f1978v2 = 7816392313619706465L;
            this.f1979v3 = 8387220255154660723L;
            this.f1973b = 0L;
            this.finalM = 0L;
            this.f1974c = i;
            this.f1975d = i2;
            this.f1976v0 ^= j;
            this.f1977v1 ^= j2;
            this.f1978v2 ^= j;
            this.f1979v3 ^= j2;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void process(ByteBuffer byteBuffer) {
            this.f1973b += 8;
            processM(byteBuffer.getLong());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected void processRemaining(ByteBuffer byteBuffer) {
            this.f1973b += byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (byteBuffer.get() & 255) << i;
                i += 8;
            }
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        protected HashCode makeHash() {
            this.finalM ^= this.f1973b << 56;
            processM(this.finalM);
            this.f1978v2 ^= 255;
            sipRound(this.f1975d);
            return HashCode.fromLong(((this.f1976v0 ^ this.f1977v1) ^ this.f1978v2) ^ this.f1979v3);
        }

        private void processM(long j) {
            this.f1979v3 ^= j;
            sipRound(this.f1974c);
            this.f1976v0 = j ^ this.f1976v0;
        }

        private void sipRound(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.f1976v0;
                long j2 = this.f1977v1;
                this.f1976v0 = j + j2;
                this.f1978v2 += this.f1979v3;
                this.f1977v1 = Long.rotateLeft(j2, 13);
                this.f1979v3 = Long.rotateLeft(this.f1979v3, 16);
                long j3 = this.f1977v1;
                long j4 = this.f1976v0;
                this.f1977v1 = j3 ^ j4;
                this.f1979v3 ^= this.f1978v2;
                this.f1976v0 = Long.rotateLeft(j4, 32);
                long j5 = this.f1978v2;
                long j6 = this.f1977v1;
                this.f1978v2 = j5 + j6;
                this.f1976v0 += this.f1979v3;
                this.f1977v1 = Long.rotateLeft(j6, 17);
                this.f1979v3 = Long.rotateLeft(this.f1979v3, 21);
                long j7 = this.f1977v1;
                long j8 = this.f1978v2;
                this.f1977v1 = j7 ^ j8;
                this.f1979v3 ^= this.f1976v0;
                this.f1978v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
