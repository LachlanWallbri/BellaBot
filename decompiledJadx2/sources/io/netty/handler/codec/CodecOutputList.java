package io.netty.handler.codec;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import java.util.AbstractList;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class CodecOutputList extends AbstractList<Object> implements RandomAccess {
    private Object[] array;
    private boolean insertSinceRecycled;
    private final CodecOutputListRecycler recycler;
    private int size;
    private static final CodecOutputListRecycler NOOP_RECYCLER = new CodecOutputListRecycler() { // from class: io.netty.handler.codec.CodecOutputList.1
        @Override // io.netty.handler.codec.CodecOutputList.CodecOutputListRecycler
        public void recycle(CodecOutputList codecOutputList) {
        }
    };
    private static final FastThreadLocal<CodecOutputLists> CODEC_OUTPUT_LISTS_POOL = new FastThreadLocal<CodecOutputLists>() { // from class: io.netty.handler.codec.CodecOutputList.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.util.concurrent.FastThreadLocal
        public CodecOutputLists initialValue() throws Exception {
            return new CodecOutputLists(16);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public interface CodecOutputListRecycler {
        void recycle(CodecOutputList codecOutputList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public static final class CodecOutputLists implements CodecOutputListRecycler {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int count;
        private int currentIdx;
        private final CodecOutputList[] elements;
        private final int mask;

        CodecOutputLists(int i) {
            this.elements = new CodecOutputList[MathUtil.safeFindNextPositivePowerOfTwo(i)];
            int i2 = 0;
            while (true) {
                CodecOutputList[] codecOutputListArr = this.elements;
                if (i2 < codecOutputListArr.length) {
                    codecOutputListArr[i2] = new CodecOutputList(this, 16);
                    i2++;
                } else {
                    this.count = codecOutputListArr.length;
                    this.currentIdx = codecOutputListArr.length;
                    this.mask = codecOutputListArr.length - 1;
                    return;
                }
            }
        }

        public CodecOutputList getOrCreate() {
            int i = this.count;
            if (i == 0) {
                return new CodecOutputList(CodecOutputList.NOOP_RECYCLER, 4);
            }
            this.count = i - 1;
            int i2 = (this.currentIdx - 1) & this.mask;
            CodecOutputList codecOutputList = this.elements[i2];
            this.currentIdx = i2;
            return codecOutputList;
        }

        @Override // io.netty.handler.codec.CodecOutputList.CodecOutputListRecycler
        public void recycle(CodecOutputList codecOutputList) {
            int i = this.currentIdx;
            this.elements[i] = codecOutputList;
            this.currentIdx = this.mask & (i + 1);
            this.count++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodecOutputList newInstance() {
        return CODEC_OUTPUT_LISTS_POOL.get().getOrCreate();
    }

    private CodecOutputList(CodecOutputListRecycler codecOutputListRecycler, int i) {
        this.recycler = codecOutputListRecycler;
        this.array = new Object[i];
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i) {
        checkIndex(i);
        return this.array[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        ObjectUtil.checkNotNull(obj, "element");
        try {
            insert(this.size, obj);
        } catch (IndexOutOfBoundsException unused) {
            expandArray();
            insert(this.size, obj);
        }
        this.size++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i, Object obj) {
        ObjectUtil.checkNotNull(obj, "element");
        checkIndex(i);
        Object obj2 = this.array[i];
        insert(i, obj);
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, Object obj) {
        ObjectUtil.checkNotNull(obj, "element");
        checkIndex(i);
        if (this.size == this.array.length) {
            expandArray();
        }
        int i2 = this.size;
        if (i != i2 - 1) {
            Object[] objArr = this.array;
            System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
        }
        insert(i, obj);
        this.size++;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object remove(int i) {
        checkIndex(i);
        Object[] objArr = this.array;
        Object obj = objArr[i];
        int i2 = (this.size - i) - 1;
        if (i2 > 0) {
            System.arraycopy(objArr, i + 1, objArr, i, i2);
        }
        Object[] objArr2 = this.array;
        int i3 = this.size - 1;
        this.size = i3;
        objArr2[i3] = null;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.size = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean insertSinceRecycled() {
        return this.insertSinceRecycled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recycle() {
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
        this.size = 0;
        this.insertSinceRecycled = false;
        this.recycler.recycle(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getUnsafe(int i) {
        return this.array[i];
    }

    private void checkIndex(int i) {
        if (i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void insert(int i, Object obj) {
        this.array[i] = obj;
        this.insertSinceRecycled = true;
    }

    private void expandArray() {
        Object[] objArr = this.array;
        int length = objArr.length << 1;
        if (length < 0) {
            throw new OutOfMemoryError();
        }
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        this.array = objArr2;
    }
}
