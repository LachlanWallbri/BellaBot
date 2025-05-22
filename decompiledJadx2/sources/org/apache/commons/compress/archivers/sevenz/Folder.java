package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/* loaded from: classes8.dex */
class Folder {
    static final Folder[] EMPTY_FOLDER_ARRAY = new Folder[0];
    BindPair[] bindPairs;
    Coder[] coders;
    long crc;
    boolean hasCrc;
    int numUnpackSubStreams;
    long[] packedStreams;
    long totalInputStreams;
    long totalOutputStreams;
    long[] unpackSizes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterable<Coder> getOrderedCoders() throws IOException {
        Coder[] coderArr;
        long[] jArr = this.packedStreams;
        if (jArr == null || (coderArr = this.coders) == null || jArr.length == 0 || coderArr.length == 0) {
            return Collections.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        int i = (int) this.packedStreams[0];
        while (i >= 0) {
            Coder[] coderArr2 = this.coders;
            if (i >= coderArr2.length) {
                break;
            }
            if (linkedList.contains(coderArr2[i])) {
                throw new IOException("folder uses the same coder more than once in coder chain");
            }
            linkedList.addLast(this.coders[i]);
            int findBindPairForOutStream = findBindPairForOutStream(i);
            i = findBindPairForOutStream != -1 ? (int) this.bindPairs[findBindPairForOutStream].inIndex : -1;
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int findBindPairForInStream(int i) {
        if (this.bindPairs == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            BindPair[] bindPairArr = this.bindPairs;
            if (i2 >= bindPairArr.length) {
                return -1;
            }
            if (bindPairArr[i2].inIndex == i) {
                return i2;
            }
            i2++;
        }
    }

    int findBindPairForOutStream(int i) {
        if (this.bindPairs == null) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            BindPair[] bindPairArr = this.bindPairs;
            if (i2 >= bindPairArr.length) {
                return -1;
            }
            if (bindPairArr[i2].outIndex == i) {
                return i2;
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getUnpackSize() {
        long j = this.totalOutputStreams;
        if (j == 0) {
            return 0L;
        }
        for (int i = ((int) j) - 1; i >= 0; i--) {
            if (findBindPairForOutStream(i) < 0) {
                return this.unpackSizes[i];
            }
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getUnpackSizeForCoder(Coder coder) {
        if (this.coders == null) {
            return 0L;
        }
        int i = 0;
        while (true) {
            Coder[] coderArr = this.coders;
            if (i >= coderArr.length) {
                return 0L;
            }
            if (coderArr[i] == coder) {
                return this.unpackSizes[i];
            }
            i++;
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Folder with ");
        sb.append(this.coders.length);
        sb.append(" coders, ");
        sb.append(this.totalInputStreams);
        sb.append(" input streams, ");
        sb.append(this.totalOutputStreams);
        sb.append(" output streams, ");
        sb.append(this.bindPairs.length);
        sb.append(" bind pairs, ");
        sb.append(this.packedStreams.length);
        sb.append(" packed streams, ");
        sb.append(this.unpackSizes.length);
        sb.append(" unpack sizes, ");
        if (this.hasCrc) {
            str = "with CRC " + this.crc;
        } else {
            str = "without CRC";
        }
        sb.append(str);
        sb.append(" and ");
        sb.append(this.numUnpackSubStreams);
        sb.append(" unpack streams");
        return sb.toString();
    }
}
