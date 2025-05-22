package io.grpc.netty.shaded.io.netty.handler.ssl.util;

import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.security.SecureRandom;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ThreadLocalInsecureRandom extends SecureRandom {
    private static final SecureRandom INSTANCE = new ThreadLocalInsecureRandom();
    private static final long serialVersionUID = -8209473337192526191L;

    @Override // java.security.SecureRandom
    public String getAlgorithm() {
        return "insecure";
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j) {
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SecureRandom current() {
        return INSTANCE;
    }

    private ThreadLocalInsecureRandom() {
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        random().nextBytes(bArr);
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i) {
        byte[] bArr = new byte[i];
        random().nextBytes(bArr);
        return bArr;
    }

    @Override // java.util.Random
    public int nextInt() {
        return random().nextInt();
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        return random().nextInt(i);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return random().nextBoolean();
    }

    @Override // java.util.Random
    public long nextLong() {
        return random().nextLong();
    }

    @Override // java.util.Random
    public float nextFloat() {
        return random().nextFloat();
    }

    @Override // java.util.Random
    public double nextDouble() {
        return random().nextDouble();
    }

    @Override // java.util.Random
    public double nextGaussian() {
        return random().nextGaussian();
    }

    private static Random random() {
        return PlatformDependent.threadLocalRandom();
    }
}
