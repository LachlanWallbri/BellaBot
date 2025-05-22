package io.netty.handler.codec.redis;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ArrayRedisMessage extends AbstractReferenceCounted implements RedisMessage {
    private final List<RedisMessage> children;
    public static final ArrayRedisMessage NULL_INSTANCE = new ArrayRedisMessage() { // from class: io.netty.handler.codec.redis.ArrayRedisMessage.1
        @Override // io.netty.handler.codec.redis.ArrayRedisMessage
        public boolean isNull() {
            return true;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public boolean release() {
            return false;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return false;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public ArrayRedisMessage retain() {
            return this;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public ArrayRedisMessage retain(int i) {
            return this;
        }

        @Override // io.netty.handler.codec.redis.ArrayRedisMessage
        public String toString() {
            return "NullArrayRedisMessage";
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public ArrayRedisMessage touch() {
            return this;
        }

        @Override // io.netty.handler.codec.redis.ArrayRedisMessage, io.netty.util.ReferenceCounted
        public ArrayRedisMessage touch(Object obj) {
            return this;
        }
    };
    public static final ArrayRedisMessage EMPTY_INSTANCE = new ArrayRedisMessage() { // from class: io.netty.handler.codec.redis.ArrayRedisMessage.2
        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public boolean release() {
            return false;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return false;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public ArrayRedisMessage retain() {
            return this;
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public ArrayRedisMessage retain(int i) {
            return this;
        }

        @Override // io.netty.handler.codec.redis.ArrayRedisMessage
        public String toString() {
            return "EmptyArrayRedisMessage";
        }

        @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
        public ArrayRedisMessage touch() {
            return this;
        }

        @Override // io.netty.handler.codec.redis.ArrayRedisMessage, io.netty.util.ReferenceCounted
        public ArrayRedisMessage touch(Object obj) {
            return this;
        }
    };

    public boolean isNull() {
        return false;
    }

    private ArrayRedisMessage() {
        this.children = Collections.emptyList();
    }

    public ArrayRedisMessage(List<RedisMessage> list) {
        this.children = (List) ObjectUtil.checkNotNull(list, "children");
    }

    public final List<RedisMessage> children() {
        return this.children;
    }

    @Override // io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        Iterator<RedisMessage> it = this.children.iterator();
        while (it.hasNext()) {
            ReferenceCountUtil.release(it.next());
        }
    }

    @Override // io.netty.util.ReferenceCounted
    public ArrayRedisMessage touch(Object obj) {
        Iterator<RedisMessage> it = this.children.iterator();
        while (it.hasNext()) {
            ReferenceCountUtil.touch(it.next());
        }
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[children=" + this.children.size() + ']';
    }
}
