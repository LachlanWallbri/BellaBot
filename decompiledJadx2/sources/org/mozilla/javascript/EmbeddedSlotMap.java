package org.mozilla.javascript;

import java.util.Iterator;
import org.mozilla.javascript.ScriptableObject;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class EmbeddedSlotMap implements SlotMap {
    private static final int INITIAL_SLOT_SIZE = 4;
    private int count;
    private ScriptableObject.Slot firstAdded;
    private ScriptableObject.Slot lastAdded;
    private ScriptableObject.Slot[] slots;

    private static int getSlotIndex(int i, int i2) {
        return (i - 1) & i2;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    private static final class Iter implements Iterator<ScriptableObject.Slot> {
        private ScriptableObject.Slot next;

        Iter(ScriptableObject.Slot slot) {
            this.next = slot;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public ScriptableObject.Slot next() {
            ScriptableObject.Slot slot = this.next;
            this.next = slot.orderedNext;
            return slot;
        }
    }

    @Override // org.mozilla.javascript.SlotMap
    public int size() {
        return this.count;
    }

    @Override // org.mozilla.javascript.SlotMap
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<ScriptableObject.Slot> iterator() {
        return new Iter(this.firstAdded);
    }

    @Override // org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot query(Object obj, int i) {
        if (this.slots == null) {
            return null;
        }
        if (obj != null) {
            i = obj.hashCode();
        }
        for (ScriptableObject.Slot slot = this.slots[getSlotIndex(this.slots.length, i)]; slot != null; slot = slot.next) {
            Object obj2 = slot.name;
            if (i == slot.indexOrHash && (obj2 == obj || (obj != null && obj.equals(obj2)))) {
                return slot;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0056, code lost:
    
        if (r1 != null) goto L39;
     */
    @Override // org.mozilla.javascript.SlotMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ScriptableObject.Slot get(Object obj, int i, ScriptableObject.SlotAccess slotAccess) {
        ScriptableObject.Slot slot = null;
        if (this.slots == null && slotAccess == ScriptableObject.SlotAccess.QUERY) {
            return null;
        }
        if (obj != null) {
            i = obj.hashCode();
        }
        ScriptableObject.Slot[] slotArr = this.slots;
        if (slotArr != null) {
            slot = this.slots[getSlotIndex(slotArr.length, i)];
            while (slot != null) {
                Object obj2 = slot.name;
                if (i == slot.indexOrHash && (obj2 == obj || (obj != null && obj.equals(obj2)))) {
                    break;
                }
                slot = slot.next;
            }
            int i2 = C88301.$SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess[slotAccess.ordinal()];
            if (i2 != 1) {
                if (i2 != 2 && i2 != 3) {
                    if (i2 == 4) {
                        if (slot instanceof ScriptableObject.GetterSlot) {
                            return slot;
                        }
                    } else if (i2 == 5 && !(slot instanceof ScriptableObject.GetterSlot)) {
                        return slot;
                    }
                }
            }
            return slot;
        }
        return createSlot(obj, i, slotAccess, slot);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.mozilla.javascript.EmbeddedSlotMap$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C88301 {
        static final /* synthetic */ int[] $SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess;

        static {
            int[] iArr = new int[ScriptableObject.SlotAccess.values().length];
            $SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess = iArr;
            try {
                iArr[ScriptableObject.SlotAccess.QUERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess[ScriptableObject.SlotAccess.MODIFY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess[ScriptableObject.SlotAccess.MODIFY_CONST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess[ScriptableObject.SlotAccess.MODIFY_GETTER_SETTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess[ScriptableObject.SlotAccess.CONVERT_ACCESSOR_TO_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0038, code lost:
    
        if (r8 != org.mozilla.javascript.ScriptableObject.SlotAccess.MODIFY_GETTER_SETTER) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x003c, code lost:
    
        if ((r0 instanceof org.mozilla.javascript.ScriptableObject.GetterSlot) != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x003e, code lost:
    
        r8 = new org.mozilla.javascript.ScriptableObject.GetterSlot(r6, r7, r0.getAttributes());
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        r8.value = r0.value;
        r8.next = r0.next;
        r6 = r5.firstAdded;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
    
        if (r0 != r6) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0065, code lost:
    
        r5.firstAdded = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
    
        r8.orderedNext = r0.orderedNext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007b, code lost:
    
        if (r0 != r5.lastAdded) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007d, code lost:
    
        r5.lastAdded = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007f, code lost:
    
        if (r2 != r0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0081, code lost:
    
        r5.slots[r9] = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0088, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0086, code lost:
    
        r2.next = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0068, code lost:
    
        if (r6 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x006c, code lost:
    
        if (r6.orderedNext == r0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x006e, code lost:
    
        r6 = r6.orderedNext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0071, code lost:
    
        if (r6 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0073, code lost:
    
        r6.orderedNext = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x004a, code lost:
    
        if (r8 != org.mozilla.javascript.ScriptableObject.SlotAccess.CONVERT_ACCESSOR_TO_DATA) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x004e, code lost:
    
        if ((r0 instanceof org.mozilla.javascript.ScriptableObject.GetterSlot) == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0050, code lost:
    
        r8 = new org.mozilla.javascript.ScriptableObject.Slot(r6, r7, r0.getAttributes());
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x008b, code lost:
    
        if (r8 != org.mozilla.javascript.ScriptableObject.SlotAccess.MODIFY_CONST) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x008d, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008f, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ScriptableObject.Slot createSlot(Object obj, int i, ScriptableObject.SlotAccess slotAccess, ScriptableObject.Slot slot) {
        if (this.count == 0) {
            this.slots = new ScriptableObject.Slot[4];
        } else if (slot != null) {
            int slotIndex = getSlotIndex(this.slots.length, i);
            ScriptableObject.Slot slot2 = this.slots[slotIndex];
            ScriptableObject.Slot slot3 = slot2;
            while (slot2 != null && (slot2.indexOrHash != i || (slot2.name != obj && (obj == null || !obj.equals(slot2.name))))) {
                slot3 = slot2;
                slot2 = slot2.next;
            }
        }
        int i2 = (this.count + 1) * 4;
        ScriptableObject.Slot[] slotArr = this.slots;
        if (i2 > slotArr.length * 3) {
            ScriptableObject.Slot[] slotArr2 = new ScriptableObject.Slot[slotArr.length * 2];
            copyTable(slotArr, slotArr2);
            this.slots = slotArr2;
        }
        ScriptableObject.Slot getterSlot = slotAccess == ScriptableObject.SlotAccess.MODIFY_GETTER_SETTER ? new ScriptableObject.GetterSlot(obj, i, 0) : new ScriptableObject.Slot(obj, i, 0);
        if (slotAccess == ScriptableObject.SlotAccess.MODIFY_CONST) {
            getterSlot.setAttributes(13);
        }
        insertNewSlot(getterSlot);
        return getterSlot;
    }

    @Override // org.mozilla.javascript.SlotMap
    public void addSlot(ScriptableObject.Slot slot) {
        if (this.slots == null) {
            this.slots = new ScriptableObject.Slot[4];
        }
        insertNewSlot(slot);
    }

    private void insertNewSlot(ScriptableObject.Slot slot) {
        this.count++;
        ScriptableObject.Slot slot2 = this.lastAdded;
        if (slot2 != null) {
            slot2.orderedNext = slot;
        }
        if (this.firstAdded == null) {
            this.firstAdded = slot;
        }
        this.lastAdded = slot;
        addKnownAbsentSlot(this.slots, slot);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if ((r1.getAttributes() & 4) == 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        if (org.mozilla.javascript.Context.getContext().isStrictMode() != false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
    
        throw org.mozilla.javascript.ScriptRuntime.typeError1("msg.delete.property.with.configurable.false", r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004d, code lost:
    
        r5.count--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        if (r2 != r1) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
    
        r5.slots[r0] = r1.next;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
    
        r6 = r5.firstAdded;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
    
        if (r1 != r6) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:
    
        r6 = null;
        r5.firstAdded = r1.orderedNext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0077, code lost:
    
        if (r1 != r5.lastAdded) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0079, code lost:
    
        r5.lastAdded = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007b, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
    
        if (r6.orderedNext == r1) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006e, code lost:
    
        r6 = r6.orderedNext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0071, code lost:
    
        r6.orderedNext = r1.orderedNext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x005c, code lost:
    
        r2.next = r1.next;
     */
    @Override // org.mozilla.javascript.SlotMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void remove(Object obj, int i) {
        if (obj != null) {
            i = obj.hashCode();
        }
        if (this.count != 0) {
            int slotIndex = getSlotIndex(this.slots.length, i);
            ScriptableObject.Slot slot = this.slots[slotIndex];
            ScriptableObject.Slot slot2 = slot;
            while (slot != null && (slot.indexOrHash != i || (slot.name != obj && (obj == null || !obj.equals(slot.name))))) {
                slot2 = slot;
                slot = slot.next;
            }
        }
    }

    private void copyTable(ScriptableObject.Slot[] slotArr, ScriptableObject.Slot[] slotArr2) {
        for (ScriptableObject.Slot slot : slotArr) {
            while (slot != null) {
                ScriptableObject.Slot slot2 = slot.next;
                slot.next = null;
                addKnownAbsentSlot(slotArr2, slot);
                slot = slot2;
            }
        }
    }

    private void addKnownAbsentSlot(ScriptableObject.Slot[] slotArr, ScriptableObject.Slot slot) {
        int slotIndex = getSlotIndex(slotArr.length, slot.indexOrHash);
        ScriptableObject.Slot slot2 = slotArr[slotIndex];
        slotArr[slotIndex] = slot;
        slot.next = slot2;
    }
}
