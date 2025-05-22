package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiffUtil {
    private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator<Snake>() { // from class: androidx.recyclerview.widget.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Snake snake, Snake snake2) {
            int i = snake.f129x - snake2.f129x;
            return i == 0 ? snake.f130y - snake2.f130y : i;
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public Object getChangePayload(int i, int i2) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(T t, T t2);

        public abstract boolean areItemsTheSame(T t, T t2);

        public Object getChangePayload(T t, T t2) {
            return null;
        }
    }

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback callback) {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int abs = oldListSize + newListSize + Math.abs(oldListSize - newListSize);
        int i = abs * 2;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake diffPartial = diffPartial(callback, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, iArr, iArr2, abs);
            if (diffPartial != null) {
                if (diffPartial.size > 0) {
                    arrayList.add(diffPartial);
                }
                diffPartial.f129x += range.oldListStart;
                diffPartial.f130y += range.newListStart;
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                if (diffPartial.reverse) {
                    range2.oldListEnd = diffPartial.f129x;
                    range2.newListEnd = diffPartial.f130y;
                } else if (diffPartial.removal) {
                    range2.oldListEnd = diffPartial.f129x - 1;
                    range2.newListEnd = diffPartial.f130y;
                } else {
                    range2.oldListEnd = diffPartial.f129x;
                    range2.newListEnd = diffPartial.f130y - 1;
                }
                arrayList2.add(range2);
                if (diffPartial.reverse) {
                    if (diffPartial.removal) {
                        range.oldListStart = diffPartial.f129x + diffPartial.size + 1;
                        range.newListStart = diffPartial.f130y + diffPartial.size;
                    } else {
                        range.oldListStart = diffPartial.f129x + diffPartial.size;
                        range.newListStart = diffPartial.f130y + diffPartial.size + 1;
                    }
                } else {
                    range.oldListStart = diffPartial.f129x + diffPartial.size;
                    range.newListStart = diffPartial.f130y + diffPartial.size;
                }
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, SNAKE_COMPARATOR);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
    
        if (r24[r13 - 1] < r24[r13 + r5]) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ba, code lost:
    
        if (r25[r12 - 1] < r25[r12 + 1]) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e3 A[LOOP:4: B:54:0x00cf->B:58:0x00e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ee A[EDGE_INSN: B:59:0x00ee->B:60:0x00ee BREAK  A[LOOP:4: B:54:0x00cf->B:58:0x00e3], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Snake diffPartial(Callback callback, int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        int i6;
        int i7;
        boolean z;
        int i8;
        int i9;
        int i10;
        boolean z2;
        int i11;
        int i12 = i2 - i;
        int i13 = i4 - i3;
        int i14 = 1;
        if (i12 < 1 || i13 < 1) {
            return null;
        }
        int i15 = i12 - i13;
        int i16 = ((i12 + i13) + 1) / 2;
        int i17 = (i5 - i16) - 1;
        int i18 = i5 + i16 + 1;
        Arrays.fill(iArr, i17, i18, 0);
        Arrays.fill(iArr2, i17 + i15, i18 + i15, i12);
        boolean z3 = i15 % 2 != 0;
        int i19 = 0;
        while (i19 <= i16) {
            int i20 = -i19;
            int i21 = i20;
            while (i21 <= i19) {
                if (i21 != i20) {
                    if (i21 != i19) {
                        int i22 = i5 + i21;
                    }
                    i10 = iArr[(i5 + i21) - i14] + i14;
                    z2 = true;
                    for (i11 = i10 - i21; i10 < i12 && i11 < i13 && callback.areItemsTheSame(i + i10, i3 + i11); i11++) {
                        i10++;
                    }
                    int i23 = i5 + i21;
                    iArr[i23] = i10;
                    if (!z3 && i21 >= (i15 - i19) + 1 && i21 <= (i15 + i19) - 1 && iArr[i23] >= iArr2[i23]) {
                        Snake snake = new Snake();
                        snake.f129x = iArr2[i23];
                        snake.f130y = snake.f129x - i21;
                        snake.size = iArr[i23] - iArr2[i23];
                        snake.removal = z2;
                        snake.reverse = false;
                        return snake;
                    }
                    i21 += 2;
                    i14 = 1;
                }
                i10 = iArr[i5 + i21 + i14];
                z2 = false;
                while (i10 < i12) {
                    i10++;
                }
                int i232 = i5 + i21;
                iArr[i232] = i10;
                if (!z3) {
                }
                i21 += 2;
                i14 = 1;
            }
            int i24 = i20;
            while (i24 <= i19) {
                int i25 = i24 + i15;
                if (i25 != i19 + i15) {
                    if (i25 != i20 + i15) {
                        int i26 = i5 + i25;
                        i6 = 1;
                    } else {
                        i6 = 1;
                    }
                    i7 = iArr2[(i5 + i25) + i6] - i6;
                    z = true;
                    i8 = i7 - i25;
                    while (i7 > 0 && i8 > 0) {
                        i9 = i12;
                        if (callback.areItemsTheSame((i + i7) - 1, (i3 + i8) - 1)) {
                            break;
                        }
                        i7--;
                        i8--;
                        i12 = i9;
                    }
                    i9 = i12;
                    int i27 = i5 + i25;
                    iArr2[i27] = i7;
                    if (z3 && i25 >= i20 && i25 <= i19 && iArr[i27] >= iArr2[i27]) {
                        Snake snake2 = new Snake();
                        snake2.f129x = iArr2[i27];
                        snake2.f130y = snake2.f129x - i25;
                        snake2.size = iArr[i27] - iArr2[i27];
                        snake2.removal = z;
                        snake2.reverse = true;
                        return snake2;
                    }
                    i24 += 2;
                    i12 = i9;
                } else {
                    i6 = 1;
                }
                i7 = iArr2[(i5 + i25) - i6];
                z = false;
                i8 = i7 - i25;
                while (i7 > 0) {
                    i9 = i12;
                    if (callback.areItemsTheSame((i + i7) - 1, (i3 + i8) - 1)) {
                    }
                }
                i9 = i12;
                int i272 = i5 + i25;
                iArr2[i272] = i7;
                if (z3) {
                }
                i24 += 2;
                i12 = i9;
            }
            i19++;
            i12 = i12;
            i14 = 1;
        }
        throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Snake {
        boolean removal;
        boolean reverse;
        int size;

        /* renamed from: x */
        int f129x;

        /* renamed from: y */
        int f130y;

        Snake() {
        }
    }

    /* loaded from: classes.dex */
    static class Diagonal {
        public final int size;

        /* renamed from: x */
        public final int f127x;

        /* renamed from: y */
        public final int f128y;

        Diagonal(int i, int i2, int i3) {
            this.f127x = i;
            this.f128y = i2;
            this.size = i3;
        }

        int endX() {
            return this.f127x + this.size;
        }

        int endY() {
            return this.f128y + this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int i, int i2, int i3, int i4) {
            this.oldListStart = i;
            this.oldListEnd = i2;
            this.newListStart = i3;
            this.newListEnd = i4;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;

        DiffResult(Callback callback, List<Snake> list, int[] iArr, int[] iArr2, boolean z) {
            this.mSnakes = list;
            this.mOldItemStatuses = iArr;
            this.mNewItemStatuses = iArr2;
            Arrays.fill(iArr, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addRootSnake();
            findMatchingItems();
        }

        private void addRootSnake() {
            Snake snake = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if (snake != null && snake.f129x == 0 && snake.f130y == 0) {
                return;
            }
            Snake snake2 = new Snake();
            snake2.f129x = 0;
            snake2.f130y = 0;
            snake2.removal = false;
            snake2.size = 0;
            snake2.reverse = false;
            this.mSnakes.add(0, snake2);
        }

        private void findMatchingItems() {
            int i = this.mOldListSize;
            int i2 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i3 = snake.f129x + snake.size;
                int i4 = snake.f130y + snake.size;
                if (this.mDetectMoves) {
                    while (i > i3) {
                        findAddition(i, i2, size);
                        i--;
                    }
                    while (i2 > i4) {
                        findRemoval(i, i2, size);
                        i2--;
                    }
                }
                for (int i5 = 0; i5 < snake.size; i5++) {
                    int i6 = snake.f129x + i5;
                    int i7 = snake.f130y + i5;
                    int i8 = this.mCallback.areContentsTheSame(i6, i7) ? 1 : 2;
                    this.mOldItemStatuses[i6] = (i7 << 5) | i8;
                    this.mNewItemStatuses[i7] = (i6 << 5) | i8;
                }
                i = snake.f129x;
                i2 = snake.f130y;
            }
        }

        private void findAddition(int i, int i2, int i3) {
            if (this.mOldItemStatuses[i - 1] != 0) {
                return;
            }
            findMatchingItem(i, i2, i3, false);
        }

        private void findRemoval(int i, int i2, int i3) {
            if (this.mNewItemStatuses[i2 - 1] != 0) {
                return;
            }
            findMatchingItem(i, i2, i3, true);
        }

        public int convertOldPositionToNew(int i) {
            if (i < 0 || i >= this.mOldListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i + ", old list size = " + this.mOldListSize);
            }
            int i2 = this.mOldItemStatuses[i];
            if ((i2 & 31) == 0) {
                return -1;
            }
            return i2 >> 5;
        }

        public int convertNewPositionToOld(int i) {
            if (i < 0 || i >= this.mNewListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + i + ", new list size = " + this.mNewListSize);
            }
            int i2 = this.mNewItemStatuses[i];
            if ((i2 & 31) == 0) {
                return -1;
            }
            return i2 >> 5;
        }

        private boolean findMatchingItem(int i, int i2, int i3, boolean z) {
            int i4;
            int i5;
            int i6;
            if (z) {
                i2--;
                i5 = i;
                i4 = i2;
            } else {
                i4 = i - 1;
                i5 = i4;
            }
            while (i3 >= 0) {
                Snake snake = this.mSnakes.get(i3);
                int i7 = snake.f129x + snake.size;
                int i8 = snake.f130y + snake.size;
                if (z) {
                    for (int i9 = i5 - 1; i9 >= i7; i9--) {
                        if (this.mCallback.areItemsTheSame(i9, i4)) {
                            i6 = this.mCallback.areContentsTheSame(i9, i4) ? 8 : 4;
                            this.mNewItemStatuses[i4] = (i9 << 5) | 16;
                            this.mOldItemStatuses[i9] = (i4 << 5) | i6;
                            return true;
                        }
                    }
                } else {
                    for (int i10 = i2 - 1; i10 >= i8; i10--) {
                        if (this.mCallback.areItemsTheSame(i4, i10)) {
                            i6 = this.mCallback.areContentsTheSame(i4, i10) ? 8 : 4;
                            int i11 = i - 1;
                            this.mOldItemStatuses[i11] = (i10 << 5) | 16;
                            this.mNewItemStatuses[i10] = (i11 << 5) | i6;
                            return true;
                        }
                    }
                }
                i5 = snake.f129x;
                i2 = snake.f130y;
                i3--;
            }
            return false;
        }

        public void dispatchUpdatesTo(RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            ArrayList arrayList = new ArrayList();
            int i = this.mOldListSize;
            int i2 = this.mNewListSize;
            for (int size = this.mSnakes.size() - 1; size >= 0; size--) {
                Snake snake = this.mSnakes.get(size);
                int i3 = snake.size;
                int i4 = snake.f129x + i3;
                int i5 = snake.f130y + i3;
                if (i4 < i) {
                    dispatchRemovals(arrayList, batchingListUpdateCallback, i4, i - i4, i4);
                }
                if (i5 < i2) {
                    dispatchAdditions(arrayList, batchingListUpdateCallback, i4, i2 - i5, i5);
                }
                for (int i6 = i3 - 1; i6 >= 0; i6--) {
                    if ((this.mOldItemStatuses[snake.f129x + i6] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(snake.f129x + i6, 1, this.mCallback.getChangePayload(snake.f129x + i6, snake.f130y + i6));
                    }
                }
                i = snake.f129x;
                i2 = snake.f130y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        private static PostponedUpdate removePostponedUpdate(List<PostponedUpdate> list, int i, boolean z) {
            int size = list.size() - 1;
            while (size >= 0) {
                PostponedUpdate postponedUpdate = list.get(size);
                if (postponedUpdate.posInOwnerList == i && postponedUpdate.removal == z) {
                    list.remove(size);
                    while (size < list.size()) {
                        list.get(size).currentPos += z ? 1 : -1;
                        size++;
                    }
                    return postponedUpdate;
                }
                size--;
            }
            return null;
        }

        private void dispatchAdditions(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onInserted(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = i3 + i4;
                int i6 = this.mNewItemStatuses[i5] & 31;
                if (i6 == 0) {
                    listUpdateCallback.onInserted(i, 1);
                    Iterator<PostponedUpdate> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().currentPos++;
                    }
                } else if (i6 == 4 || i6 == 8) {
                    int i7 = this.mNewItemStatuses[i5] >> 5;
                    listUpdateCallback.onMoved(removePostponedUpdate(list, i7, true).currentPos, i);
                    if (i6 == 4) {
                        listUpdateCallback.onChanged(i, 1, this.mCallback.getChangePayload(i7, i5));
                    }
                } else if (i6 == 16) {
                    list.add(new PostponedUpdate(i5, i, false));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + i5 + " " + Long.toBinaryString(i6));
                }
            }
        }

        private void dispatchRemovals(List<PostponedUpdate> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onRemoved(i, i2);
                return;
            }
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                int i5 = i3 + i4;
                int i6 = this.mOldItemStatuses[i5] & 31;
                if (i6 == 0) {
                    listUpdateCallback.onRemoved(i + i4, 1);
                    Iterator<PostponedUpdate> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().currentPos--;
                    }
                } else if (i6 == 4 || i6 == 8) {
                    int i7 = this.mOldItemStatuses[i5] >> 5;
                    PostponedUpdate removePostponedUpdate = removePostponedUpdate(list, i7, false);
                    listUpdateCallback.onMoved(i + i4, removePostponedUpdate.currentPos - 1);
                    if (i6 == 4) {
                        listUpdateCallback.onChanged(removePostponedUpdate.currentPos - 1, 1, this.mCallback.getChangePayload(i5, i7));
                    }
                } else if (i6 == 16) {
                    list.add(new PostponedUpdate(i5, i + i4, true));
                } else {
                    throw new IllegalStateException("unknown flag for pos " + i5 + " " + Long.toBinaryString(i6));
                }
            }
        }

        List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int i, int i2, boolean z) {
            this.posInOwnerList = i;
            this.currentPos = i2;
            this.removal = z;
        }
    }

    /* loaded from: classes.dex */
    static class CenteredArray {
        private final int[] mData;
        private final int mMid;

        CenteredArray(int i) {
            this.mData = new int[i];
            this.mMid = this.mData.length / 2;
        }

        int get(int i) {
            return this.mData[i + this.mMid];
        }

        int[] backingData() {
            return this.mData;
        }

        void set(int i, int i2) {
            this.mData[i + this.mMid] = i2;
        }

        public void fill(int i) {
            Arrays.fill(this.mData, i);
        }
    }
}
