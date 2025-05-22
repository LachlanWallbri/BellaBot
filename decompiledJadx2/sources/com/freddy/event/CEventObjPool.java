package com.freddy.event;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class CEventObjPool extends ObjectPool<CEvent> {
    public CEventObjPool(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.freddy.event.ObjectPool
    public CEvent[] createObjPool(int i) {
        return new CEvent[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.freddy.event.ObjectPool
    public CEvent createNewObj() {
        return new CEvent();
    }
}
