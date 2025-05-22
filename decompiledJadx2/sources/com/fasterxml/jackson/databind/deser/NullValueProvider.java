package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.AccessPattern;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface NullValueProvider {
    AccessPattern getNullAccessPattern();

    Object getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;
}
