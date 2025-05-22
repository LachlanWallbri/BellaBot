package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PeerCredentials {
    private final int[] gids;
    private final int pid;
    private final int uid;

    PeerCredentials(int i, int i2, int... iArr) {
        this.pid = i;
        this.uid = i2;
        this.gids = iArr == null ? EmptyArrays.EMPTY_INTS : iArr;
    }

    public int pid() {
        return this.pid;
    }

    public int uid() {
        return this.uid;
    }

    public int[] gids() {
        return (int[]) this.gids.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("UserCredentials[pid=");
        sb.append(this.pid);
        sb.append("; uid=");
        sb.append(this.uid);
        sb.append("; gids=[");
        int[] iArr = this.gids;
        if (iArr.length > 0) {
            sb.append(iArr[0]);
            for (int i = 1; i < this.gids.length; i++) {
                sb.append(", ");
                sb.append(this.gids[i]);
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
