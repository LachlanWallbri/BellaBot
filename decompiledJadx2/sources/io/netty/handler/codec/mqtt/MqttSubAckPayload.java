package io.netty.handler.codec.mqtt;

import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class MqttSubAckPayload {
    private final List<Integer> grantedQoSLevels;

    public MqttSubAckPayload(int... iArr) {
        if (iArr == null) {
            throw new NullPointerException("grantedQoSLevels");
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        this.grantedQoSLevels = Collections.unmodifiableList(arrayList);
    }

    public MqttSubAckPayload(Iterable<Integer> iterable) {
        Integer next;
        if (iterable == null) {
            throw new NullPointerException("grantedQoSLevels");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            arrayList.add(next);
        }
        this.grantedQoSLevels = Collections.unmodifiableList(arrayList);
    }

    public List<Integer> grantedQoSLevels() {
        return this.grantedQoSLevels;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[grantedQoSLevels=" + this.grantedQoSLevels + ']';
    }
}
