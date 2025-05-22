package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ObjArray implements Serializable {
    private static final int FIELDS_STORE_SIZE = 5;
    static final long serialVersionUID = 4174889037736658296L;
    private transient Object[] data;

    /* renamed from: f0 */
    private transient Object f10201f0;

    /* renamed from: f1 */
    private transient Object f10202f1;

    /* renamed from: f2 */
    private transient Object f10203f2;

    /* renamed from: f3 */
    private transient Object f10204f3;

    /* renamed from: f4 */
    private transient Object f10205f4;
    private boolean sealed;
    private int size;

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal() {
        this.sealed = true;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final int size() {
        return this.size;
    }

    public final void setSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i2 = this.size;
        if (i < i2) {
            for (int i3 = i; i3 != i2; i3++) {
                setImpl(i3, null);
            }
        } else if (i > i2 && i > 5) {
            ensureCapacity(i);
        }
        this.size = i;
    }

    public final Object get(int i) {
        if (i < 0 || i >= this.size) {
            throw onInvalidIndex(i, this.size);
        }
        return getImpl(i);
    }

    public final void set(int i, Object obj) {
        if (i < 0 || i >= this.size) {
            throw onInvalidIndex(i, this.size);
        }
        if (this.sealed) {
            throw onSeledMutation();
        }
        setImpl(i, obj);
    }

    private Object getImpl(int i) {
        if (i == 0) {
            return this.f10201f0;
        }
        if (i == 1) {
            return this.f10202f1;
        }
        if (i == 2) {
            return this.f10203f2;
        }
        if (i == 3) {
            return this.f10204f3;
        }
        if (i == 4) {
            return this.f10205f4;
        }
        return this.data[i - 5];
    }

    private void setImpl(int i, Object obj) {
        if (i == 0) {
            this.f10201f0 = obj;
            return;
        }
        if (i == 1) {
            this.f10202f1 = obj;
            return;
        }
        if (i == 2) {
            this.f10203f2 = obj;
            return;
        }
        if (i == 3) {
            this.f10204f3 = obj;
        } else if (i == 4) {
            this.f10205f4 = obj;
        } else {
            this.data[i - 5] = obj;
        }
    }

    public int indexOf(Object obj) {
        int i = this.size;
        for (int i2 = 0; i2 != i; i2++) {
            Object impl = getImpl(i2);
            if (impl == obj || (impl != null && impl.equals(obj))) {
                return i2;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int i = this.size;
        while (i != 0) {
            i--;
            Object impl = getImpl(i);
            if (impl == obj || (impl != null && impl.equals(obj))) {
                return i;
            }
        }
        return -1;
    }

    public final Object peek() {
        int i = this.size;
        if (i == 0) {
            throw onEmptyStackTopRead();
        }
        return getImpl(i - 1);
    }

    public final Object pop() {
        Object obj;
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i = this.size - 1;
        if (i == -1) {
            throw onEmptyStackTopRead();
        }
        if (i == 0) {
            obj = this.f10201f0;
            this.f10201f0 = null;
        } else if (i == 1) {
            obj = this.f10202f1;
            this.f10202f1 = null;
        } else if (i == 2) {
            obj = this.f10203f2;
            this.f10203f2 = null;
        } else if (i == 3) {
            obj = this.f10204f3;
            this.f10204f3 = null;
        } else if (i == 4) {
            obj = this.f10205f4;
            this.f10205f4 = null;
        } else {
            Object[] objArr = this.data;
            int i2 = i - 5;
            obj = objArr[i2];
            objArr[i2] = null;
        }
        this.size = i;
        return obj;
    }

    public final void push(Object obj) {
        add(obj);
    }

    public final void add(Object obj) {
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i = this.size;
        if (i >= 5) {
            ensureCapacity(i + 1);
        }
        this.size = i + 1;
        setImpl(i, obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0017, code lost:
    
        if (r8 != 4) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void add(int i, Object obj) {
        int i2 = this.size;
        if (i < 0 || i > i2) {
            throw onInvalidIndex(i, i2 + 1);
        }
        if (this.sealed) {
            throw onSeledMutation();
        }
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                    }
                    if (i2 != 3) {
                        this.f10204f3 = obj;
                        this.size = i2 + 1;
                    }
                    Object obj2 = this.f10204f3;
                    this.f10204f3 = obj;
                    obj = obj2;
                    if (i2 == 4) {
                        this.f10205f4 = obj;
                        this.size = i2 + 1;
                    }
                    Object obj3 = this.f10205f4;
                    this.f10205f4 = obj;
                    obj = obj3;
                    i = 5;
                    ensureCapacity(i2 + 1);
                    if (i != i2) {
                        Object[] objArr = this.data;
                        int i3 = i - 5;
                        System.arraycopy(objArr, i3, objArr, i3 + 1, i2 - i);
                    }
                    this.data[i - 5] = obj;
                    this.size = i2 + 1;
                }
                if (i2 != 2) {
                    this.f10203f2 = obj;
                    this.size = i2 + 1;
                } else {
                    Object obj4 = this.f10203f2;
                    this.f10203f2 = obj;
                    obj = obj4;
                    if (i2 != 3) {
                    }
                }
            }
        } else if (i2 == 0) {
            this.f10201f0 = obj;
            this.size = i2 + 1;
        } else {
            Object obj5 = this.f10201f0;
            this.f10201f0 = obj;
            obj = obj5;
        }
        if (i2 == 1) {
            this.f10202f1 = obj;
            this.size = i2 + 1;
        } else {
            Object obj6 = this.f10202f1;
            this.f10202f1 = obj;
            obj = obj6;
            if (i2 != 2) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
    
        if (r7 != 4) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void remove(int i) {
        int i2 = this.size;
        if (i < 0 || i >= i2) {
            throw onInvalidIndex(i, i2);
        }
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i3 = i2 - 1;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                    }
                    if (i3 != 3) {
                        this.f10204f3 = null;
                        this.size = i3;
                    }
                    this.f10204f3 = this.f10205f4;
                    if (i3 == 4) {
                        this.f10205f4 = null;
                        this.size = i3;
                    }
                    this.f10205f4 = this.data[0];
                    i = 5;
                    if (i != i3) {
                        Object[] objArr = this.data;
                        int i4 = i - 5;
                        System.arraycopy(objArr, i4 + 1, objArr, i4, i3 - i);
                    }
                    this.data[i3 - 5] = null;
                    this.size = i3;
                }
                if (i3 != 2) {
                    this.f10203f2 = null;
                    this.size = i3;
                } else {
                    this.f10203f2 = this.f10204f3;
                    if (i3 != 3) {
                    }
                }
            }
        } else {
            if (i3 == 0) {
                this.f10201f0 = null;
                this.size = i3;
            }
            this.f10201f0 = this.f10202f1;
        }
        if (i3 == 1) {
            this.f10202f1 = null;
            this.size = i3;
        } else {
            this.f10202f1 = this.f10203f2;
            if (i3 != 2) {
            }
        }
    }

    public final void clear() {
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i = this.size;
        for (int i2 = 0; i2 != i; i2++) {
            setImpl(i2, null);
        }
        this.size = 0;
    }

    public final Object[] toArray() {
        Object[] objArr = new Object[this.size];
        toArray(objArr, 0);
        return objArr;
    }

    public final void toArray(Object[] objArr) {
        toArray(objArr, 0);
    }

    public final void toArray(Object[] objArr, int i) {
        int i2 = this.size;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                System.arraycopy(this.data, 0, objArr, i + 5, i2 - 5);
                            }
                            objArr[i + 4] = this.f10205f4;
                        }
                        objArr[i + 3] = this.f10204f3;
                    }
                    objArr[i + 2] = this.f10203f2;
                }
                objArr[i + 1] = this.f10202f1;
            }
            objArr[i + 0] = this.f10201f0;
        }
    }

    private void ensureCapacity(int i) {
        int i2 = i - 5;
        if (i2 <= 0) {
            throw new IllegalArgumentException();
        }
        Object[] objArr = this.data;
        if (objArr == null) {
            if (10 >= i2) {
                i2 = 10;
            }
            this.data = new Object[i2];
            return;
        }
        int length = objArr.length;
        if (length < i2) {
            int i3 = length > 5 ? length * 2 : 10;
            if (i3 >= i2) {
                i2 = i3;
            }
            Object[] objArr2 = new Object[i2];
            int i4 = this.size;
            if (i4 > 5) {
                System.arraycopy(this.data, 0, objArr2, 0, i4 - 5);
            }
            this.data = objArr2;
        }
    }

    private static RuntimeException onInvalidIndex(int i, int i2) {
        throw new IndexOutOfBoundsException(i + " ∉ [0, " + i2 + ')');
    }

    private static RuntimeException onEmptyStackTopRead() {
        throw new RuntimeException("Empty stack");
    }

    private static RuntimeException onSeledMutation() {
        throw new IllegalStateException("Attempt to modify sealed array");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int i = this.size;
        for (int i2 = 0; i2 != i; i2++) {
            objectOutputStream.writeObject(getImpl(i2));
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int i = this.size;
        if (i > 5) {
            this.data = new Object[i - 5];
        }
        for (int i2 = 0; i2 != i; i2++) {
            setImpl(i2, objectInputStream.readObject());
        }
    }
}
