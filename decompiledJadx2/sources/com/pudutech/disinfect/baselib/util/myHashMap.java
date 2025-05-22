package com.pudutech.disinfect.baselib.util;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: myHashMap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/myHashMap;", "K", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/HashMap;", "()V", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class myHashMap<K, T> extends HashMap<K, T> {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<K, T>> entrySet() {
        return getEntries();
    }

    public /* bridge */ Set getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set getKeys() {
        return super.keySet();
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection getValues() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<T> values() {
        return getValues();
    }

    @Override // java.util.AbstractMap
    public String toString() {
        String str = "";
        for (Map.Entry<K, T> entry : entrySet()) {
            K key = entry.getKey();
            T value = entry.getValue();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(StringsKt.trimIndent(String.valueOf(key) + ": " + value + ' '));
            str = sb.toString();
        }
        if (str == null) {
            Intrinsics.throwNpe();
        }
        return str;
    }
}
