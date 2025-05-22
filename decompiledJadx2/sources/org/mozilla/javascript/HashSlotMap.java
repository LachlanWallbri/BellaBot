package org.mozilla.javascript;

import java.util.Iterator;
import java.util.LinkedHashMap;
import org.mozilla.javascript.ScriptableObject;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class HashSlotMap implements SlotMap {
    private final LinkedHashMap<Object, ScriptableObject.Slot> map = new LinkedHashMap<>();

    @Override // org.mozilla.javascript.SlotMap
    public int size() {
        return this.map.size();
    }

    @Override // org.mozilla.javascript.SlotMap
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot query(Object obj, int i) {
        if (obj == null) {
            obj = String.valueOf(i);
        }
        return this.map.get(obj);
    }

    @Override // org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot get(Object obj, int i, ScriptableObject.SlotAccess slotAccess) {
        Object valueOf = obj == null ? String.valueOf(i) : obj;
        ScriptableObject.Slot slot = this.map.get(valueOf);
        int i2 = C88311.$SwitchMap$org$mozilla$javascript$ScriptableObject$SlotAccess[slotAccess.ordinal()];
        if (i2 == 1) {
            return slot;
        }
        if (i2 == 2 || i2 == 3) {
            if (slot != null) {
                return slot;
            }
        } else if (i2 == 4) {
            if (slot instanceof ScriptableObject.GetterSlot) {
                return slot;
            }
        } else if (i2 == 5 && !(slot instanceof ScriptableObject.GetterSlot)) {
            return slot;
        }
        return createSlot(obj, i, valueOf, slotAccess);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.mozilla.javascript.HashSlotMap$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C88311 {
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

    private ScriptableObject.Slot createSlot(Object obj, int i, Object obj2, ScriptableObject.SlotAccess slotAccess) {
        ScriptableObject.Slot slot;
        ScriptableObject.Slot slot2 = this.map.get(obj2);
        if (slot2 != null) {
            if (slotAccess == ScriptableObject.SlotAccess.MODIFY_GETTER_SETTER && !(slot2 instanceof ScriptableObject.GetterSlot)) {
                slot = new ScriptableObject.GetterSlot(obj2, slot2.indexOrHash, slot2.getAttributes());
            } else if (slotAccess == ScriptableObject.SlotAccess.CONVERT_ACCESSOR_TO_DATA && (slot2 instanceof ScriptableObject.GetterSlot)) {
                slot = new ScriptableObject.Slot(obj2, slot2.indexOrHash, slot2.getAttributes());
            } else {
                if (slotAccess == ScriptableObject.SlotAccess.MODIFY_CONST) {
                    return null;
                }
                return slot2;
            }
            slot.value = slot2.value;
            this.map.put(obj2, slot);
            return slot;
        }
        ScriptableObject.Slot getterSlot = slotAccess == ScriptableObject.SlotAccess.MODIFY_GETTER_SETTER ? new ScriptableObject.GetterSlot(obj, i, 0) : new ScriptableObject.Slot(obj, i, 0);
        if (slotAccess == ScriptableObject.SlotAccess.MODIFY_CONST) {
            getterSlot.setAttributes(13);
        }
        addSlot(getterSlot);
        return getterSlot;
    }

    @Override // org.mozilla.javascript.SlotMap
    public void addSlot(ScriptableObject.Slot slot) {
        this.map.put(slot.name == null ? String.valueOf(slot.indexOrHash) : slot.name, slot);
    }

    @Override // org.mozilla.javascript.SlotMap
    public void remove(Object obj, int i) {
        Object valueOf = obj == null ? String.valueOf(i) : obj;
        ScriptableObject.Slot slot = this.map.get(valueOf);
        if (slot != null) {
            if ((slot.getAttributes() & 4) != 0) {
                if (Context.getContext().isStrictMode()) {
                    throw ScriptRuntime.typeError1("msg.delete.property.with.configurable.false", obj);
                }
            } else {
                this.map.remove(valueOf);
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<ScriptableObject.Slot> iterator() {
        return this.map.values().iterator();
    }
}
