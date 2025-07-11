package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
@JacksonStdImpl
/* loaded from: classes2.dex */
public class UntypedObjectDeserializer extends StdDeserializer<Object> implements ResolvableDeserializer, ContextualDeserializer {
    protected static final Object[] NO_OBJECTS = new Object[0];
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _listDeserializer;
    protected JavaType _listType;
    protected JsonDeserializer<Object> _mapDeserializer;
    protected JavaType _mapType;
    protected final boolean _nonMerging;
    protected JsonDeserializer<Object> _numberDeserializer;
    protected JsonDeserializer<Object> _stringDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return null;
    }

    @Deprecated
    public UntypedObjectDeserializer() {
        this((JavaType) null, (JavaType) null);
    }

    public UntypedObjectDeserializer(JavaType javaType, JavaType javaType2) {
        super((Class<?>) Object.class);
        this._listType = javaType;
        this._mapType = javaType2;
        this._nonMerging = false;
    }

    public UntypedObjectDeserializer(UntypedObjectDeserializer untypedObjectDeserializer, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, JsonDeserializer<?> jsonDeserializer3, JsonDeserializer<?> jsonDeserializer4) {
        super((Class<?>) Object.class);
        this._mapDeserializer = jsonDeserializer;
        this._listDeserializer = jsonDeserializer2;
        this._stringDeserializer = jsonDeserializer3;
        this._numberDeserializer = jsonDeserializer4;
        this._listType = untypedObjectDeserializer._listType;
        this._mapType = untypedObjectDeserializer._mapType;
        this._nonMerging = untypedObjectDeserializer._nonMerging;
    }

    protected UntypedObjectDeserializer(UntypedObjectDeserializer untypedObjectDeserializer, boolean z) {
        super((Class<?>) Object.class);
        this._mapDeserializer = untypedObjectDeserializer._mapDeserializer;
        this._listDeserializer = untypedObjectDeserializer._listDeserializer;
        this._stringDeserializer = untypedObjectDeserializer._stringDeserializer;
        this._numberDeserializer = untypedObjectDeserializer._numberDeserializer;
        this._listType = untypedObjectDeserializer._listType;
        this._mapType = untypedObjectDeserializer._mapType;
        this._nonMerging = z;
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        JavaType constructType = deserializationContext.constructType(Object.class);
        JavaType constructType2 = deserializationContext.constructType(String.class);
        TypeFactory typeFactory = deserializationContext.getTypeFactory();
        JavaType javaType = this._listType;
        if (javaType == null) {
            this._listDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, typeFactory.constructCollectionType(List.class, constructType)));
        } else {
            this._listDeserializer = _findCustomDeser(deserializationContext, javaType);
        }
        JavaType javaType2 = this._mapType;
        if (javaType2 == null) {
            this._mapDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, typeFactory.constructMapType(Map.class, constructType2, constructType)));
        } else {
            this._mapDeserializer = _findCustomDeser(deserializationContext, javaType2);
        }
        this._stringDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, constructType2));
        this._numberDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, typeFactory.constructType(Number.class)));
        JavaType unknownType = TypeFactory.unknownType();
        this._mapDeserializer = deserializationContext.handleSecondaryContextualization(this._mapDeserializer, null, unknownType);
        this._listDeserializer = deserializationContext.handleSecondaryContextualization(this._listDeserializer, null, unknownType);
        this._stringDeserializer = deserializationContext.handleSecondaryContextualization(this._stringDeserializer, null, unknownType);
        this._numberDeserializer = deserializationContext.handleSecondaryContextualization(this._numberDeserializer, null, unknownType);
    }

    protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        return deserializationContext.findNonContextualValueDeserializer(javaType);
    }

    protected JsonDeserializer<Object> _clearIfStdImpl(JsonDeserializer<Object> jsonDeserializer) {
        if (ClassUtil.isJacksonStdImpl(jsonDeserializer)) {
            return null;
        }
        return jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        boolean z = beanProperty == null && Boolean.FALSE.equals(deserializationContext.getConfig().getDefaultMergeable(Object.class));
        if (this._stringDeserializer == null && this._numberDeserializer == null && this._mapDeserializer == null && this._listDeserializer == null && getClass() == UntypedObjectDeserializer.class) {
            return Vanilla.instance(z);
        }
        return z != this._nonMerging ? new UntypedObjectDeserializer(this, z) : this;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        switch (jsonParser.getCurrentTokenId()) {
            case 1:
            case 2:
            case 5:
                JsonDeserializer<Object> jsonDeserializer = this._mapDeserializer;
                if (jsonDeserializer != null) {
                    return jsonDeserializer.deserialize(jsonParser, deserializationContext);
                }
                return mapObject(jsonParser, deserializationContext);
            case 3:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return mapArrayToArray(jsonParser, deserializationContext);
                }
                JsonDeserializer<Object> jsonDeserializer2 = this._listDeserializer;
                if (jsonDeserializer2 != null) {
                    return jsonDeserializer2.deserialize(jsonParser, deserializationContext);
                }
                return mapArray(jsonParser, deserializationContext);
            case 4:
            default:
                return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
            case 6:
                JsonDeserializer<Object> jsonDeserializer3 = this._stringDeserializer;
                if (jsonDeserializer3 != null) {
                    return jsonDeserializer3.deserialize(jsonParser, deserializationContext);
                }
                return jsonParser.getText();
            case 7:
                JsonDeserializer<Object> jsonDeserializer4 = this._numberDeserializer;
                if (jsonDeserializer4 != null) {
                    return jsonDeserializer4.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                JsonDeserializer<Object> jsonDeserializer5 = this._numberDeserializer;
                if (jsonDeserializer5 != null) {
                    return jsonDeserializer5.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId != 1 && currentTokenId != 3) {
            switch (currentTokenId) {
                case 5:
                    break;
                case 6:
                    JsonDeserializer<Object> jsonDeserializer = this._stringDeserializer;
                    if (jsonDeserializer != null) {
                        return jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    }
                    return jsonParser.getText();
                case 7:
                    JsonDeserializer<Object> jsonDeserializer2 = this._numberDeserializer;
                    if (jsonDeserializer2 != null) {
                        return jsonDeserializer2.deserialize(jsonParser, deserializationContext);
                    }
                    if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                        return _coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                case 8:
                    JsonDeserializer<Object> jsonDeserializer3 = this._numberDeserializer;
                    if (jsonDeserializer3 != null) {
                        return jsonDeserializer3.deserialize(jsonParser, deserializationContext);
                    }
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return jsonParser.getNumberValue();
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                case 12:
                    return jsonParser.getEmbeddedObject();
                default:
                    return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
            }
        }
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        if (this._nonMerging) {
            return deserialize(jsonParser, deserializationContext);
        }
        switch (jsonParser.getCurrentTokenId()) {
            case 1:
            case 2:
            case 5:
                JsonDeserializer<Object> jsonDeserializer = this._mapDeserializer;
                if (jsonDeserializer != null) {
                    return jsonDeserializer.deserialize(jsonParser, deserializationContext, obj);
                }
                if (obj instanceof Map) {
                    return mapObject(jsonParser, deserializationContext, (Map) obj);
                }
                return mapObject(jsonParser, deserializationContext);
            case 3:
                JsonDeserializer<Object> jsonDeserializer2 = this._listDeserializer;
                if (jsonDeserializer2 != null) {
                    return jsonDeserializer2.deserialize(jsonParser, deserializationContext, obj);
                }
                if (obj instanceof Collection) {
                    return mapArray(jsonParser, deserializationContext, (Collection) obj);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return mapArrayToArray(jsonParser, deserializationContext);
                }
                return mapArray(jsonParser, deserializationContext);
            case 4:
            default:
                return deserialize(jsonParser, deserializationContext);
            case 6:
                JsonDeserializer<Object> jsonDeserializer3 = this._stringDeserializer;
                if (jsonDeserializer3 != null) {
                    return jsonDeserializer3.deserialize(jsonParser, deserializationContext, obj);
                }
                return jsonParser.getText();
            case 7:
                JsonDeserializer<Object> jsonDeserializer4 = this._numberDeserializer;
                if (jsonDeserializer4 != null) {
                    return jsonDeserializer4.deserialize(jsonParser, deserializationContext, obj);
                }
                if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                JsonDeserializer<Object> jsonDeserializer5 = this._numberDeserializer;
                if (jsonDeserializer5 != null) {
                    return jsonDeserializer5.deserialize(jsonParser, deserializationContext, obj);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int i = 2;
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(2);
        }
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(deserialize);
            return arrayList;
        }
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(deserialize);
            arrayList2.add(deserialize2);
            return arrayList2;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        resetAndStart[0] = deserialize;
        resetAndStart[1] = deserialize2;
        int i2 = 2;
        while (true) {
            Object deserialize3 = deserialize(jsonParser, deserializationContext);
            i++;
            if (i2 >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i2 = 0;
            }
            int i3 = i2 + 1;
            resetAndStart[i2] = deserialize3;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList3 = new ArrayList(i);
                leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i3, arrayList3);
                return arrayList3;
            }
            i2 = i3;
        }
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            collection.add(deserialize(jsonParser, deserializationContext));
        }
        return collection;
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            str = jsonParser.nextFieldName();
        } else if (currentToken == JsonToken.FIELD_NAME) {
            str = jsonParser.getCurrentName();
        } else {
            if (currentToken != JsonToken.END_OBJECT) {
                return deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
            }
            str = null;
        }
        if (str == null) {
            return new LinkedHashMap(2);
        }
        jsonParser.nextToken();
        Object deserialize = deserialize(jsonParser, deserializationContext);
        String nextFieldName = jsonParser.nextFieldName();
        if (nextFieldName == null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(2);
            linkedHashMap.put(str, deserialize);
            return linkedHashMap;
        }
        jsonParser.nextToken();
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        String nextFieldName2 = jsonParser.nextFieldName();
        if (nextFieldName2 == null) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(str, deserialize);
            linkedHashMap2.put(nextFieldName, deserialize2);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(str, deserialize);
        linkedHashMap3.put(nextFieldName, deserialize2);
        do {
            jsonParser.nextToken();
            linkedHashMap3.put(nextFieldName2, deserialize(jsonParser, deserializationContext));
            nextFieldName2 = jsonParser.nextFieldName();
        } while (nextFieldName2 != null);
        return linkedHashMap3;
    }

    protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        while (true) {
            Object deserialize = deserialize(jsonParser, deserializationContext);
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            }
            int i2 = i + 1;
            resetAndStart[i] = deserialize;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2);
            }
            i = i2;
        }
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException {
        Object deserialize;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken == JsonToken.END_OBJECT) {
            return map;
        }
        String currentName = jsonParser.getCurrentName();
        do {
            jsonParser.nextToken();
            Object obj = map.get(currentName);
            if (obj != null) {
                deserialize = deserialize(jsonParser, deserializationContext, obj);
            } else {
                deserialize = deserialize(jsonParser, deserializationContext);
            }
            if (deserialize != obj) {
                map.put(currentName, deserialize);
            }
            currentName = jsonParser.nextFieldName();
        } while (currentName != null);
        return map;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    @JacksonStdImpl
    /* loaded from: classes2.dex */
    public static class Vanilla extends StdDeserializer<Object> {
        private static final long serialVersionUID = 1;
        public static final Vanilla std = new Vanilla();
        protected final boolean _nonMerging;

        public Vanilla() {
            this(false);
        }

        protected Vanilla(boolean z) {
            super((Class<?>) Object.class);
            this._nonMerging = z;
        }

        public static Vanilla instance(boolean z) {
            if (z) {
                return new Vanilla(true);
            }
            return std;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
            if (this._nonMerging) {
                return Boolean.FALSE;
            }
            return null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            switch (jsonParser.getCurrentTokenId()) {
                case 1:
                    if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                        return new LinkedHashMap(2);
                    }
                    break;
                case 2:
                    return new LinkedHashMap(2);
                case 3:
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                            return UntypedObjectDeserializer.NO_OBJECTS;
                        }
                        return new ArrayList(2);
                    }
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                        return mapArrayToArray(jsonParser, deserializationContext);
                    }
                    return mapArray(jsonParser, deserializationContext);
                case 4:
                default:
                    return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
                case 5:
                    break;
                case 6:
                    return jsonParser.getText();
                case 7:
                    if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                        return _coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                case 8:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return jsonParser.getNumberValue();
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                case 12:
                    return jsonParser.getEmbeddedObject();
            }
            return mapObject(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 1 && currentTokenId != 3) {
                switch (currentTokenId) {
                    case 5:
                        break;
                    case 6:
                        return jsonParser.getText();
                    case 7:
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                            return jsonParser.getBigIntegerValue();
                        }
                        return jsonParser.getNumberValue();
                    case 8:
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return jsonParser.getDecimalValue();
                        }
                        return jsonParser.getNumberValue();
                    case 9:
                        return Boolean.TRUE;
                    case 10:
                        return Boolean.FALSE;
                    case 11:
                        return null;
                    case 12:
                        return jsonParser.getEmbeddedObject();
                    default:
                        return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
                }
            }
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x001a, code lost:
        
            if (r0 != 5) goto L41;
         */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
            Object deserialize;
            if (this._nonMerging) {
                return deserialize(jsonParser, deserializationContext);
            }
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 1) {
                if (currentTokenId != 2) {
                    if (currentTokenId == 3) {
                        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                            return obj;
                        }
                        if (obj instanceof Collection) {
                            Collection collection = (Collection) obj;
                            do {
                                collection.add(deserialize(jsonParser, deserializationContext));
                            } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
                        }
                        return deserialize(jsonParser, deserializationContext);
                    }
                    if (currentTokenId != 4) {
                    }
                }
                return obj;
            }
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                return obj;
            }
            if (obj instanceof Map) {
                Map map = (Map) obj;
                String currentName = jsonParser.getCurrentName();
                do {
                    jsonParser.nextToken();
                    Object obj2 = map.get(currentName);
                    if (obj2 != null) {
                        deserialize = deserialize(jsonParser, deserializationContext, obj2);
                    } else {
                        deserialize = deserialize(jsonParser, deserializationContext);
                    }
                    if (deserialize != obj2) {
                        map.put(currentName, deserialize);
                    }
                    currentName = jsonParser.nextFieldName();
                } while (currentName != null);
                return obj;
            }
            return deserialize(jsonParser, deserializationContext);
        }

        protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Object deserialize = deserialize(jsonParser, deserializationContext);
            int i = 2;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(deserialize);
                return arrayList;
            }
            Object deserialize2 = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(deserialize);
                arrayList2.add(deserialize2);
                return arrayList2;
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            resetAndStart[0] = deserialize;
            resetAndStart[1] = deserialize2;
            int i2 = 2;
            while (true) {
                Object deserialize3 = deserialize(jsonParser, deserializationContext);
                i++;
                if (i2 >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i2 = 0;
                }
                int i3 = i2 + 1;
                resetAndStart[i2] = deserialize3;
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    ArrayList arrayList3 = new ArrayList(i);
                    leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i3, arrayList3);
                    return arrayList3;
                }
                i2 = i3;
            }
        }

        protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i = 0;
            while (true) {
                Object deserialize = deserialize(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i = 0;
                }
                int i2 = i + 1;
                resetAndStart[i] = deserialize;
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2);
                }
                i = i2;
            }
        }

        protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String text = jsonParser.getText();
            jsonParser.nextToken();
            Object deserialize = deserialize(jsonParser, deserializationContext);
            String nextFieldName = jsonParser.nextFieldName();
            if (nextFieldName == null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(2);
                linkedHashMap.put(text, deserialize);
                return linkedHashMap;
            }
            jsonParser.nextToken();
            Object deserialize2 = deserialize(jsonParser, deserializationContext);
            String nextFieldName2 = jsonParser.nextFieldName();
            if (nextFieldName2 == null) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
                linkedHashMap2.put(text, deserialize);
                linkedHashMap2.put(nextFieldName, deserialize2);
                return linkedHashMap2;
            }
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put(text, deserialize);
            linkedHashMap3.put(nextFieldName, deserialize2);
            do {
                jsonParser.nextToken();
                linkedHashMap3.put(nextFieldName2, deserialize(jsonParser, deserializationContext));
                nextFieldName2 = jsonParser.nextFieldName();
            } while (nextFieldName2 != null);
            return linkedHashMap3;
        }
    }
}
