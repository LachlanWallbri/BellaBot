package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.netty.shaded.io.netty.handler.codec.Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class AbstractHttp2Headers implements Http2Headers {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public /* bridge */ /* synthetic */ Http2Headers addObject(CharSequence charSequence, Iterable iterable) {
        return addObject2(charSequence, (Iterable<?>) iterable);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public /* bridge */ /* synthetic */ Http2Headers setObject(CharSequence charSequence, Iterable iterable) {
        return setObject2(charSequence, (Iterable<?>) iterable);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public Iterator<CharSequence> valueIterator(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Set<CharSequence> names() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public CharSequence get(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public CharSequence get(CharSequence charSequence, CharSequence charSequence2) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public CharSequence getAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public CharSequence getAndRemove(CharSequence charSequence, CharSequence charSequence2) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public List<CharSequence> getAll(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public List<CharSequence> getAllAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Boolean getBoolean(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean getBoolean(CharSequence charSequence, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Byte getByte(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public byte getByte(CharSequence charSequence, byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Character getChar(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public char getChar(CharSequence charSequence, char c) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Short getShort(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public short getShort(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Integer getInt(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public int getInt(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Long getLong(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public long getLong(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Float getFloat(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public float getFloat(CharSequence charSequence, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Double getDouble(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public double getDouble(CharSequence charSequence, double d) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Long getTimeMillis(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public long getTimeMillis(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Boolean getBooleanAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean getBooleanAndRemove(CharSequence charSequence, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Byte getByteAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public byte getByteAndRemove(CharSequence charSequence, byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Character getCharAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public char getCharAndRemove(CharSequence charSequence, char c) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Short getShortAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public short getShortAndRemove(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Integer getIntAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public int getIntAndRemove(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Long getLongAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public long getLongAndRemove(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Float getFloatAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public float getFloatAndRemove(CharSequence charSequence, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Double getDoubleAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public double getDoubleAndRemove(CharSequence charSequence, double d) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Long getTimeMillisAndRemove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public long getTimeMillisAndRemove(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean contains(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean contains(CharSequence charSequence, CharSequence charSequence2) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public boolean contains(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsObject(CharSequence charSequence, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsBoolean(CharSequence charSequence, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsByte(CharSequence charSequence, byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsChar(CharSequence charSequence, char c) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsShort(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsInt(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsLong(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsFloat(CharSequence charSequence, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsDouble(CharSequence charSequence, double d) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean containsTimeMillis(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers add(CharSequence charSequence, CharSequence... charSequenceArr) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers add(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers add(CharSequence charSequence, CharSequence charSequence2) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers add(CharSequence charSequence, Iterable<? extends CharSequence> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addObject(CharSequence charSequence, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: addObject, reason: avoid collision after fix types in other method */
    public Http2Headers addObject2(CharSequence charSequence, Iterable<?> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addObject(CharSequence charSequence, Object... objArr) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addBoolean(CharSequence charSequence, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addByte(CharSequence charSequence, byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addChar(CharSequence charSequence, char c) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addShort(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addInt(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addLong(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addFloat(CharSequence charSequence, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addDouble(CharSequence charSequence, double d) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers addTimeMillis(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers set(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers set(CharSequence charSequence, CharSequence charSequence2) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers set(CharSequence charSequence, Iterable<? extends CharSequence> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers set(CharSequence charSequence, CharSequence... charSequenceArr) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setObject(CharSequence charSequence, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: setObject, reason: avoid collision after fix types in other method */
    public Http2Headers setObject2(CharSequence charSequence, Iterable<?> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setObject(CharSequence charSequence, Object... objArr) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setBoolean(CharSequence charSequence, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setByte(CharSequence charSequence, byte b) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setChar(CharSequence charSequence, char c) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setShort(CharSequence charSequence, short s) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setInt(CharSequence charSequence, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setLong(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setFloat(CharSequence charSequence, float f) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setDouble(CharSequence charSequence, double d) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setTimeMillis(CharSequence charSequence, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers setAll(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public boolean remove(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.Headers
    public Http2Headers clear() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers, io.grpc.netty.shaded.io.netty.handler.codec.Headers, java.lang.Iterable
    public Iterator<Map.Entry<CharSequence, CharSequence>> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public Http2Headers method(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence method() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public Http2Headers scheme(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence scheme() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public Http2Headers authority(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence authority() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public Http2Headers path(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence path() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public Http2Headers status(CharSequence charSequence) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers
    public CharSequence status() {
        throw new UnsupportedOperationException();
    }
}
