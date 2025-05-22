package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.AbstractReferenceCounted;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class InternalAttribute extends AbstractReferenceCounted implements InterfaceHttpData {
    private final Charset charset;
    private int size;
    private final List<ByteBuf> value = new ArrayList();

    @Override // io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return "InternalAttribute";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalAttribute(Charset charset) {
        this.charset = charset;
    }

    @Override // io.netty.handler.codec.http.multipart.InterfaceHttpData
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.InternalAttribute;
    }

    public void addValue(String str) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(str, this.charset);
        this.value.add(copiedBuffer);
        this.size += copiedBuffer.readableBytes();
    }

    public void addValue(String str, int i) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(str, this.charset);
        this.value.add(i, copiedBuffer);
        this.size += copiedBuffer.readableBytes();
    }

    public void setValue(String str, int i) {
        if (str == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(str, this.charset);
        ByteBuf byteBuf = this.value.set(i, copiedBuffer);
        if (byteBuf != null) {
            this.size -= byteBuf.readableBytes();
            byteBuf.release();
        }
        this.size += copiedBuffer.readableBytes();
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof InternalAttribute) {
            return getName().equalsIgnoreCase(((InternalAttribute) obj).getName());
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (!(interfaceHttpData instanceof InternalAttribute)) {
            throw new ClassCastException("Cannot compare " + getHttpDataType() + " with " + interfaceHttpData.getHttpDataType());
        }
        return compareTo((InternalAttribute) interfaceHttpData);
    }

    public int compareTo(InternalAttribute internalAttribute) {
        return getName().compareToIgnoreCase(internalAttribute.getName());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<ByteBuf> it = this.value.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString(this.charset));
        }
        return sb.toString();
    }

    public int size() {
        return this.size;
    }

    public ByteBuf toByteBuf() {
        return Unpooled.compositeBuffer().addComponents(this.value).writerIndex(size()).readerIndex(0);
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public InterfaceHttpData retain() {
        Iterator<ByteBuf> it = this.value.iterator();
        while (it.hasNext()) {
            it.next().retain();
        }
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public InterfaceHttpData retain(int i) {
        Iterator<ByteBuf> it = this.value.iterator();
        while (it.hasNext()) {
            it.next().retain(i);
        }
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public InterfaceHttpData touch() {
        Iterator<ByteBuf> it = this.value.iterator();
        while (it.hasNext()) {
            it.next().touch();
        }
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public InterfaceHttpData touch(Object obj) {
        Iterator<ByteBuf> it = this.value.iterator();
        while (it.hasNext()) {
            it.next().touch(obj);
        }
        return this;
    }
}
