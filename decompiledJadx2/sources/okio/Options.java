package okio;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: Options.kt */
    @Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, m3961d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x00f9, code lost:
        
            continue;
         */
        @JvmStatic
        /* renamed from: of */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Options m3998of(ByteString... byteStrings) {
            Intrinsics.checkParameterIsNotNull(byteStrings, "byteStrings");
            int i = 0;
            if (byteStrings.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, null);
            }
            List mutableList = ArraysKt.toMutableList(byteStrings);
            CollectionsKt.sort(mutableList);
            ArrayList arrayList = new ArrayList(byteStrings.length);
            for (ByteString byteString : byteStrings) {
                arrayList.add(-1);
            }
            Object[] array = arrayList.toArray(new Integer[0]);
            if (array != null) {
                Integer[] numArr = (Integer[]) array;
                List mutableListOf = CollectionsKt.mutableListOf((Integer[]) Arrays.copyOf(numArr, numArr.length));
                int length = byteStrings.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    mutableListOf.set(CollectionsKt.binarySearch$default(mutableList, byteStrings[i2], 0, 0, 6, (Object) null), Integer.valueOf(i3));
                    i2++;
                    i3++;
                }
                if (!(((ByteString) mutableList.get(0)).size() > 0)) {
                    throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
                }
                int i4 = 0;
                while (i4 < mutableList.size()) {
                    ByteString byteString2 = (ByteString) mutableList.get(i4);
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (i6 < mutableList.size()) {
                        ByteString byteString3 = (ByteString) mutableList.get(i6);
                        if (!byteString3.startsWith(byteString2)) {
                            break;
                        }
                        if (!(byteString3.size() != byteString2.size())) {
                            throw new IllegalArgumentException(("duplicate option: " + byteString3).toString());
                        }
                        if (((Number) mutableListOf.get(i6)).intValue() > ((Number) mutableListOf.get(i4)).intValue()) {
                            mutableList.remove(i6);
                            mutableListOf.remove(i6);
                        } else {
                            i6++;
                        }
                    }
                    i4 = i5;
                }
                Buffer buffer = new Buffer();
                Companion companion = this;
                buildTrieRecursive$default(companion, 0L, buffer, 0, mutableList, 0, 0, mutableListOf, 53, null);
                int[] iArr = new int[(int) companion.getIntCount(buffer)];
                while (!buffer.exhausted()) {
                    iArr[i] = buffer.readInt();
                    i++;
                }
                return new Options((ByteString[]) byteStrings.clone(), iArr, null);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        static /* bridge */ /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) {
            companion.buildTrieRecursive((i4 & 1) != 0 ? 0L : j, buffer, (i4 & 4) != 0 ? 0 : i, list, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? list.size() : i3, list2);
        }

        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) {
            int i;
            int i2;
            int i3;
            int i4;
            Buffer buffer;
            int i5 = byteStringOffset;
            if (!(fromIndex < toIndex)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int i6 = fromIndex; i6 < toIndex; i6++) {
                if (!(byteStrings.get(i6).size() >= i5)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            ByteString byteString = byteStrings.get(fromIndex);
            ByteString byteString2 = byteStrings.get(toIndex - 1);
            if (i5 == byteString.size()) {
                int intValue = indexes.get(fromIndex).intValue();
                int i7 = fromIndex + 1;
                ByteString byteString3 = byteStrings.get(i7);
                i = i7;
                i2 = intValue;
                byteString = byteString3;
            } else {
                i = fromIndex;
                i2 = -1;
            }
            if (byteString.getByte(i5) != byteString2.getByte(i5)) {
                int i8 = 1;
                for (int i9 = i + 1; i9 < toIndex; i9++) {
                    if (byteStrings.get(i9 - 1).getByte(i5) != byteStrings.get(i9).getByte(i5)) {
                        i8++;
                    }
                }
                Companion companion = this;
                long intCount = nodeOffset + companion.getIntCount(node) + 2 + (i8 * 2);
                node.writeInt(i8);
                node.writeInt(i2);
                for (int i10 = i; i10 < toIndex; i10++) {
                    byte b = byteStrings.get(i10).getByte(i5);
                    if (i10 == i || b != byteStrings.get(i10 - 1).getByte(i5)) {
                        node.writeInt(b & 255);
                    }
                }
                Buffer buffer2 = new Buffer();
                int i11 = i;
                while (i11 < toIndex) {
                    byte b2 = byteStrings.get(i11).getByte(i5);
                    int i12 = i11 + 1;
                    int i13 = i12;
                    while (true) {
                        if (i13 >= toIndex) {
                            i3 = toIndex;
                            break;
                        } else {
                            if (b2 != byteStrings.get(i13).getByte(i5)) {
                                i3 = i13;
                                break;
                            }
                            i13++;
                        }
                    }
                    if (i12 == i3 && i5 + 1 == byteStrings.get(i11).size()) {
                        node.writeInt(indexes.get(i11).intValue());
                        i4 = i3;
                        buffer = buffer2;
                    } else {
                        node.writeInt(((int) (intCount + companion.getIntCount(buffer2))) * (-1));
                        i4 = i3;
                        buffer = buffer2;
                        companion.buildTrieRecursive(intCount, buffer2, i5 + 1, byteStrings, i11, i3, indexes);
                    }
                    i11 = i4;
                    buffer2 = buffer;
                }
                node.writeAll(buffer2);
                return;
            }
            int min = Math.min(byteString.size(), byteString2.size());
            int i14 = 0;
            for (int i15 = i5; i15 < min && byteString.getByte(i15) == byteString2.getByte(i15); i15++) {
                i14++;
            }
            Companion companion2 = this;
            long intCount2 = 1 + nodeOffset + companion2.getIntCount(node) + 2 + i14;
            node.writeInt(-i14);
            node.writeInt(i2);
            int i16 = i5 + i14;
            while (i5 < i16) {
                node.writeInt(byteString.getByte(i5) & 255);
                i5++;
            }
            if (i + 1 == toIndex) {
                if (!(i16 == byteStrings.get(i).size())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                node.writeInt(indexes.get(i).intValue());
            } else {
                Buffer buffer3 = new Buffer();
                node.writeInt(((int) (companion2.getIntCount(buffer3) + intCount2)) * (-1));
                companion2.buildTrieRecursive(intCount2, buffer3, i16, byteStrings, i, toIndex, indexes);
                node.writeAll(buffer3);
            }
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / 4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bc, code lost:
    
        continue;
     */
    /* renamed from: of */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Options m3997of(ByteString... byteStringArr) {
        if (byteStringArr.length == 0) {
            return new Options(new ByteString[0], new int[]{0, -1});
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(byteStringArr));
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(-1);
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.set(Collections.binarySearch(arrayList, byteStringArr[i2]), Integer.valueOf(i2));
        }
        if (((ByteString) arrayList.get(0)).size() == 0) {
            throw new IllegalArgumentException("the empty byte string is not a supported option");
        }
        int i3 = 0;
        while (i3 < arrayList.size()) {
            ByteString byteString = (ByteString) arrayList.get(i3);
            int i4 = i3 + 1;
            int i5 = i4;
            while (i5 < arrayList.size()) {
                ByteString byteString2 = (ByteString) arrayList.get(i5);
                if (!byteString2.startsWith(byteString)) {
                    break;
                }
                if (byteString2.size() == byteString.size()) {
                    throw new IllegalArgumentException("duplicate option: " + byteString2);
                }
                if (((Integer) arrayList2.get(i5)).intValue() > ((Integer) arrayList2.get(i3)).intValue()) {
                    arrayList.remove(i5);
                    arrayList2.remove(i5);
                } else {
                    i5++;
                }
            }
            i3 = i4;
        }
        Buffer buffer = new Buffer();
        buildTrieRecursive(0L, buffer, 0, arrayList, 0, arrayList.size(), arrayList2);
        int intCount = intCount(buffer);
        int[] iArr = new int[intCount];
        for (int i6 = 0; i6 < intCount; i6++) {
            iArr[i6] = buffer.readInt();
        }
        if (!buffer.exhausted()) {
            throw new AssertionError();
        }
        return new Options((ByteString[]) byteStringArr.clone(), iArr);
    }

    private static void buildTrieRecursive(long j, Buffer buffer, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        Buffer buffer2;
        int i7 = i2;
        if (i7 >= i3) {
            throw new AssertionError();
        }
        for (int i8 = i7; i8 < i3; i8++) {
            if (list.get(i8).size() < i) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i2);
        ByteString byteString2 = list.get(i3 - 1);
        int i9 = -1;
        if (i == byteString.size()) {
            i9 = list2.get(i7).intValue();
            i7++;
            byteString = list.get(i7);
        }
        int i10 = i7;
        if (byteString.getByte(i) != byteString2.getByte(i)) {
            int i11 = 1;
            for (int i12 = i10 + 1; i12 < i3; i12++) {
                if (list.get(i12 - 1).getByte(i) != list.get(i12).getByte(i)) {
                    i11++;
                }
            }
            long intCount = j + intCount(buffer) + 2 + (i11 * 2);
            buffer.writeInt(i11);
            buffer.writeInt(i9);
            for (int i13 = i10; i13 < i3; i13++) {
                byte b = list.get(i13).getByte(i);
                if (i13 == i10 || b != list.get(i13 - 1).getByte(i)) {
                    buffer.writeInt(b & 255);
                }
            }
            Buffer buffer3 = new Buffer();
            int i14 = i10;
            while (i14 < i3) {
                byte b2 = list.get(i14).getByte(i);
                int i15 = i14 + 1;
                int i16 = i15;
                while (true) {
                    if (i16 >= i3) {
                        i5 = i3;
                        break;
                    } else {
                        if (b2 != list.get(i16).getByte(i)) {
                            i5 = i16;
                            break;
                        }
                        i16++;
                    }
                }
                if (i15 == i5 && i + 1 == list.get(i14).size()) {
                    buffer.writeInt(list2.get(i14).intValue());
                    i6 = i5;
                    buffer2 = buffer3;
                } else {
                    buffer.writeInt((int) ((intCount(buffer3) + intCount) * (-1)));
                    i6 = i5;
                    buffer2 = buffer3;
                    buildTrieRecursive(intCount, buffer3, i + 1, list, i14, i5, list2);
                }
                buffer3 = buffer2;
                i14 = i6;
            }
            Buffer buffer4 = buffer3;
            buffer.write(buffer4, buffer4.size());
            return;
        }
        int i17 = 0;
        int min = Math.min(byteString.size(), byteString2.size());
        for (int i18 = i; i18 < min && byteString.getByte(i18) == byteString2.getByte(i18); i18++) {
            i17++;
        }
        long intCount2 = 1 + j + intCount(buffer) + 2 + i17;
        buffer.writeInt(-i17);
        buffer.writeInt(i9);
        int i19 = i;
        while (true) {
            i4 = i + i17;
            if (i19 >= i4) {
                break;
            }
            buffer.writeInt(byteString.getByte(i19) & 255);
            i19++;
        }
        if (i10 + 1 == i3) {
            if (i4 != list.get(i10).size()) {
                throw new AssertionError();
            }
            buffer.writeInt(list2.get(i10).intValue());
        } else {
            Buffer buffer5 = new Buffer();
            buffer.writeInt((int) ((intCount(buffer5) + intCount2) * (-1)));
            buildTrieRecursive(intCount2, buffer5, i4, list, i10, i3, list2);
            buffer.write(buffer5, buffer5.size());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }
}
