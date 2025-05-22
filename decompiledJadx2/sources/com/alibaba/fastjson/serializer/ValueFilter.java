package com.alibaba.fastjson.serializer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ValueFilter extends SerializeFilter {
    Object process(Object obj, String str, Object obj2);
}
