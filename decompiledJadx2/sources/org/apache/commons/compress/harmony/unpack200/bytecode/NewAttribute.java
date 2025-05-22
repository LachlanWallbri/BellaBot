package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class NewAttribute extends BCIRenumberedAttribute {
    private final List body;
    private final int layoutIndex;
    private final List lengths;
    private ClassConstantPool pool;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    protected int[] getStartPCs() {
        return null;
    }

    public NewAttribute(CPUTF8 cputf8, int i) {
        super(cputf8);
        this.lengths = new ArrayList();
        this.body = new ArrayList();
        this.layoutIndex = i;
    }

    public int getLayoutIndex() {
        return this.layoutIndex;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        int i = 0;
        for (int i2 = 0; i2 < this.lengths.size(); i2++) {
            i += ((Integer) this.lengths.get(i2)).intValue();
        }
        return i;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        int i;
        for (int i2 = 0; i2 < this.lengths.size(); i2++) {
            int intValue = ((Integer) this.lengths.get(i2)).intValue();
            Object obj = this.body.get(i2);
            long j = 0;
            if (obj instanceof Long) {
                j = ((Long) obj).longValue();
            } else {
                if (obj instanceof ClassFileEntry) {
                    i = this.pool.indexOf((ClassFileEntry) obj);
                } else if (obj instanceof BCValue) {
                    i = ((BCValue) obj).actualValue;
                }
                j = i;
            }
            if (intValue == 1) {
                dataOutputStream.writeByte((int) j);
            } else if (intValue == 2) {
                dataOutputStream.writeShort((int) j);
            } else if (intValue == 4) {
                dataOutputStream.writeInt((int) j);
            } else if (intValue == 8) {
                dataOutputStream.writeLong(j);
            }
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return this.attributeName.underlyingString();
    }

    public void addInteger(int i, long j) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(Long.valueOf(j));
    }

    public void addBCOffset(int i, int i2) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(new BCOffset(i2));
    }

    public void addBCIndex(int i, int i2) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(new BCIndex(i2));
    }

    public void addBCLength(int i, int i2) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(new BCLength(i2));
    }

    public void addToBody(int i, Object obj) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        for (int i = 0; i < this.body.size(); i++) {
            Object obj = this.body.get(i);
            if (obj instanceof ClassFileEntry) {
                ((ClassFileEntry) obj).resolve(classConstantPool);
            }
        }
        this.pool = classConstantPool;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        int i = 1;
        int i2 = 1;
        for (int i3 = 0; i3 < this.body.size(); i3++) {
            if (this.body.get(i3) instanceof ClassFileEntry) {
                i2++;
            }
        }
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[i2];
        classFileEntryArr[0] = getAttributeName();
        for (int i4 = 0; i4 < this.body.size(); i4++) {
            Object obj = this.body.get(i4);
            if (obj instanceof ClassFileEntry) {
                classFileEntryArr[i] = (ClassFileEntry) obj;
                i++;
            }
        }
        return classFileEntryArr;
    }

    /* loaded from: classes9.dex */
    private static class BCOffset extends BCValue {
        private int index;
        private final int offset;

        public BCOffset(int i) {
            super();
            this.offset = i;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes9.dex */
    private static class BCIndex extends BCValue {
        private final int index;

        public BCIndex(int i) {
            super();
            this.index = i;
        }
    }

    /* loaded from: classes9.dex */
    private static class BCLength extends BCValue {
        private final int length;

        public BCLength(int i) {
            super();
            this.length = i;
        }
    }

    /* loaded from: classes9.dex */
    private static abstract class BCValue {
        int actualValue;

        private BCValue() {
        }

        public void setActualValue(int i) {
            this.actualValue = i;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    public void renumber(List list) {
        if (this.renumbered) {
            return;
        }
        Object obj = null;
        for (Object obj2 : this.body) {
            if (obj2 instanceof BCIndex) {
                BCIndex bCIndex = (BCIndex) obj2;
                bCIndex.setActualValue(((Integer) list.get(bCIndex.index)).intValue());
            } else if (obj2 instanceof BCOffset) {
                BCOffset bCOffset = (BCOffset) obj2;
                if (obj instanceof BCIndex) {
                    int i = ((BCIndex) obj).index + bCOffset.offset;
                    bCOffset.setIndex(i);
                    bCOffset.setActualValue(((Integer) list.get(i)).intValue());
                } else if (obj instanceof BCOffset) {
                    int i2 = ((BCOffset) obj).index + bCOffset.offset;
                    bCOffset.setIndex(i2);
                    bCOffset.setActualValue(((Integer) list.get(i2)).intValue());
                } else {
                    bCOffset.setActualValue(((Integer) list.get(bCOffset.offset)).intValue());
                }
            } else if (obj2 instanceof BCLength) {
                BCLength bCLength = (BCLength) obj2;
                BCIndex bCIndex2 = (BCIndex) obj;
                bCLength.setActualValue(((Integer) list.get(bCIndex2.index + bCLength.length)).intValue() - bCIndex2.actualValue);
            }
            obj = obj2;
        }
        this.renumbered = true;
    }
}
