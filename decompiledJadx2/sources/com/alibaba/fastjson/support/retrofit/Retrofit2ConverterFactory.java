package com.alibaba.fastjson.support.retrofit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.api.client.json.Json;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Retrofit2ConverterFactory extends Converter.Factory {
    private Feature[] features;
    private SerializeConfig serializeConfig;
    private SerializerFeature[] serializerFeatures;
    private static final MediaType MEDIA_TYPE = MediaType.parse(Json.MEDIA_TYPE);
    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];
    private ParserConfig parserConfig = ParserConfig.getGlobalInstance();
    private int featureValues = JSON.DEFAULT_PARSER_FEATURE;

    @Override // retrofit2.Converter.Factory
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new ResponseBodyConverter(type);
    }

    @Override // retrofit2.Converter.Factory
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new RequestBodyConverter();
    }

    public ParserConfig getParserConfig() {
        return this.parserConfig;
    }

    public Retrofit2ConverterFactory setParserConfig(ParserConfig parserConfig) {
        this.parserConfig = parserConfig;
        return this;
    }

    public int getParserFeatureValues() {
        return this.featureValues;
    }

    public Retrofit2ConverterFactory setParserFeatureValues(int i) {
        this.featureValues = i;
        return this;
    }

    public Feature[] getParserFeatures() {
        return this.features;
    }

    public Retrofit2ConverterFactory setParserFeatures(Feature[] featureArr) {
        this.features = featureArr;
        return this;
    }

    public SerializeConfig getSerializeConfig() {
        return this.serializeConfig;
    }

    public Retrofit2ConverterFactory setSerializeConfig(SerializeConfig serializeConfig) {
        this.serializeConfig = serializeConfig;
        return this;
    }

    public SerializerFeature[] getSerializerFeatures() {
        return this.serializerFeatures;
    }

    public Retrofit2ConverterFactory setSerializerFeatures(SerializerFeature[] serializerFeatureArr) {
        this.serializerFeatures = serializerFeatureArr;
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    final class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private Type type;

        ResponseBodyConverter(Type type) {
            this.type = type;
        }

        @Override // retrofit2.Converter
        public T convert(ResponseBody responseBody) throws IOException {
            try {
                return (T) JSON.parseObject(responseBody.string(), this.type, Retrofit2ConverterFactory.this.parserConfig, Retrofit2ConverterFactory.this.featureValues, Retrofit2ConverterFactory.this.features != null ? Retrofit2ConverterFactory.this.features : Retrofit2ConverterFactory.EMPTY_SERIALIZER_FEATURES);
            } finally {
                responseBody.close();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    final class RequestBodyConverter<T> implements Converter<T, RequestBody> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.Converter
        public /* bridge */ /* synthetic */ RequestBody convert(Object obj) throws IOException {
            return convert((RequestBodyConverter<T>) obj);
        }

        RequestBodyConverter() {
        }

        @Override // retrofit2.Converter
        public RequestBody convert(T t) throws IOException {
            return RequestBody.create(Retrofit2ConverterFactory.MEDIA_TYPE, JSON.toJSONBytes(t, Retrofit2ConverterFactory.this.serializeConfig == null ? SerializeConfig.globalInstance : Retrofit2ConverterFactory.this.serializeConfig, Retrofit2ConverterFactory.this.serializerFeatures == null ? SerializerFeature.EMPTY : Retrofit2ConverterFactory.this.serializerFeatures));
        }
    }
}
