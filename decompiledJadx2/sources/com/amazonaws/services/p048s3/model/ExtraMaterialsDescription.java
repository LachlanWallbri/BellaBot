package com.amazonaws.services.p048s3.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ExtraMaterialsDescription implements Serializable {
    public static final ExtraMaterialsDescription NONE = new ExtraMaterialsDescription(Collections.EMPTY_MAP);
    private final Map<String, String> extra;
    private final ConflictResolution resolve;

    /* loaded from: classes.dex */
    public enum ConflictResolution {
        FAIL_FAST,
        OVERRIDE,
        OVERRIDDEN
    }

    public ExtraMaterialsDescription(Map<String, String> map) {
        this(map, ConflictResolution.FAIL_FAST);
    }

    public ExtraMaterialsDescription(Map<String, String> map, ConflictResolution conflictResolution) {
        if (map == null || conflictResolution == null) {
            throw new IllegalArgumentException();
        }
        this.extra = Collections.unmodifiableMap(new HashMap(map));
        this.resolve = conflictResolution;
    }

    public Map<String, String> getMaterialDescription() {
        return this.extra;
    }

    public ConflictResolution getConflictResolution() {
        return this.resolve;
    }

    public Map<String, String> mergeInto(Map<String, String> map) {
        if (this.extra.size() == 0) {
            return map;
        }
        if (map == null || map.size() == 0) {
            return this.extra;
        }
        int i = C13171.f1188xb3b15629[this.resolve.ordinal()];
        if (i == 1) {
            int size = map.size() + this.extra.size();
            HashMap hashMap = new HashMap(map);
            hashMap.putAll(this.extra);
            if (size != hashMap.size()) {
                throw new IllegalArgumentException("The supplemental material descriptions contains conflicting entries");
            }
            return Collections.unmodifiableMap(hashMap);
        }
        if (i == 2) {
            HashMap hashMap2 = new HashMap(this.extra);
            hashMap2.putAll(map);
            return Collections.unmodifiableMap(hashMap2);
        }
        if (i == 3) {
            HashMap hashMap3 = new HashMap(map);
            hashMap3.putAll(this.extra);
            return Collections.unmodifiableMap(hashMap3);
        }
        throw new UnsupportedOperationException();
    }

    /* renamed from: com.amazonaws.services.s3.model.ExtraMaterialsDescription$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C13171 {

        /* renamed from: $SwitchMap$com$amazonaws$services$s3$model$ExtraMaterialsDescription$ConflictResolution */
        static final /* synthetic */ int[] f1188xb3b15629 = new int[ConflictResolution.values().length];

        static {
            try {
                f1188xb3b15629[ConflictResolution.FAIL_FAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1188xb3b15629[ConflictResolution.OVERRIDDEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1188xb3b15629[ConflictResolution.OVERRIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
