package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ByteBufferCodec implements ObjectSerializer, ObjectDeserializer {
    public static final ByteBufferCodec instance = new ByteBufferCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 14;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) ((ByteBufferBean) defaultJSONParser.parseObject((Class) ByteBufferBean.class)).byteBuffer();
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        byte[] array = byteBuffer.array();
        SerializeWriter serializeWriter = jSONSerializer.out;
        serializeWriter.write(123);
        serializeWriter.writeFieldName(TmpConstant.TYPE_VALUE_ARRAY);
        serializeWriter.writeByteArray(array);
        serializeWriter.writeFieldValue(',', "limit", byteBuffer.limit());
        serializeWriter.writeFieldValue(',', RequestParameters.POSITION, byteBuffer.position());
        serializeWriter.write(125);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ByteBufferBean {
        public byte[] array;
        public int limit;
        public int position;

        public ByteBuffer byteBuffer() {
            ByteBuffer wrap = ByteBuffer.wrap(this.array);
            wrap.limit(this.limit);
            wrap.position(this.position);
            return wrap;
        }
    }
}
