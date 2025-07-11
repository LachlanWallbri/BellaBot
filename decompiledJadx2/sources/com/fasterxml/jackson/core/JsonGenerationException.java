package com.fasterxml.jackson.core;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class JsonGenerationException extends JsonProcessingException {
    private static final long serialVersionUID = 123;
    protected transient JsonGenerator _processor;

    @Deprecated
    public JsonGenerationException(Throwable th) {
        super(th);
    }

    @Deprecated
    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    @Deprecated
    public JsonGenerationException(String str, Throwable th) {
        super(str, null, th);
    }

    public JsonGenerationException(Throwable th, JsonGenerator jsonGenerator) {
        super(th);
        this._processor = jsonGenerator;
    }

    public JsonGenerationException(String str, JsonGenerator jsonGenerator) {
        super(str, (JsonLocation) null);
        this._processor = jsonGenerator;
    }

    public JsonGenerationException(String str, Throwable th, JsonGenerator jsonGenerator) {
        super(str, null, th);
        this._processor = jsonGenerator;
    }

    public JsonGenerationException withGenerator(JsonGenerator jsonGenerator) {
        this._processor = jsonGenerator;
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException
    public JsonGenerator getProcessor() {
        return this._processor;
    }
}
