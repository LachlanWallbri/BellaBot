package io.minio;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes7.dex */
public class Result<T> {

    /* renamed from: ex */
    private final Exception f8437ex;
    private final T type;

    public Result(T t) {
        this.type = t;
        this.f8437ex = null;
    }

    public Result(Exception exc) {
        this.type = null;
        this.f8437ex = exc;
    }

    public T get() throws ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, JsonMappingException, JsonParseException, NoSuchAlgorithmException, ServerException, XmlParserException {
        Exception exc = this.f8437ex;
        if (exc == null) {
            return this.type;
        }
        if (exc instanceof ErrorResponseException) {
            throw ((ErrorResponseException) exc);
        }
        if (exc instanceof IllegalArgumentException) {
            throw ((IllegalArgumentException) exc);
        }
        if (exc instanceof InsufficientDataException) {
            throw ((InsufficientDataException) exc);
        }
        if (exc instanceof InternalException) {
            throw ((InternalException) exc);
        }
        if (exc instanceof InvalidKeyException) {
            throw ((InvalidKeyException) exc);
        }
        if (exc instanceof InvalidResponseException) {
            throw ((InvalidResponseException) exc);
        }
        if (exc instanceof IOException) {
            throw ((IOException) exc);
        }
        if (exc instanceof JsonMappingException) {
            throw ((JsonMappingException) exc);
        }
        if (exc instanceof JsonParseException) {
            throw ((JsonParseException) exc);
        }
        if (exc instanceof NoSuchAlgorithmException) {
            throw ((NoSuchAlgorithmException) exc);
        }
        if (exc instanceof ServerException) {
            throw ((ServerException) exc);
        }
        if (exc instanceof XmlParserException) {
            throw ((XmlParserException) exc);
        }
        throw new RuntimeException("Exception not handled", exc);
    }
}
