package org.mozilla.javascript;

import java.util.Iterator;
import org.mozilla.javascript.ScriptableObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class SlotMapContainer implements SlotMap {
    private static final int LARGE_HASH_SIZE = 2000;
    protected SlotMap map;

    public long readLock() {
        return 0L;
    }

    public void unlockRead(long j) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SlotMapContainer(int i) {
        if (i > 2000) {
            this.map = new HashSlotMap();
        } else {
            this.map = new EmbeddedSlotMap();
        }
    }

    @Override // org.mozilla.javascript.SlotMap
    public int size() {
        return this.map.size();
    }

    public int dirtySize() {
        return this.map.size();
    }

    @Override // org.mozilla.javascript.SlotMap
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot get(Object obj, int i, ScriptableObject.SlotAccess slotAccess) {
        if (slotAccess != ScriptableObject.SlotAccess.QUERY) {
            checkMapSize();
        }
        return this.map.get(obj, i, slotAccess);
    }

    @Override // org.mozilla.javascript.SlotMap
    public ScriptableObject.Slot query(Object obj, int i) {
        return this.map.query(obj, i);
    }

    @Override // org.mozilla.javascript.SlotMap
    public void addSlot(ScriptableObject.Slot slot) {
        checkMapSize();
        this.map.addSlot(slot);
    }

    @Override // org.mozilla.javascript.SlotMap
    public void remove(Object obj, int i) {
        this.map.remove(obj, i);
    }

    @Override // java.lang.Iterable
    public Iterator<ScriptableObject.Slot> iterator() {
        return this.map.iterator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkMapSize() {
        SlotMap slotMap = this.map;
        if (!(slotMap instanceof EmbeddedSlotMap) || slotMap.size() < 2000) {
            return;
        }
        HashSlotMap hashSlotMap = new HashSlotMap();
        Iterator<ScriptableObject.Slot> it = this.map.iterator();
        while (it.hasNext()) {
            hashSlotMap.addSlot(it.next());
        }
        this.map = hashSlotMap;
    }
}
