package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpServerUpgradeHandler extends HttpObjectAggregator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean handlingUpgrade;
    private final SourceCodec sourceCodec;
    private final UpgradeCodecFactory upgradeCodecFactory;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface SourceCodec {
        void upgradeFrom(ChannelHandlerContext channelHandlerContext);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface UpgradeCodec {
        boolean prepareUpgradeResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders);

        Collection<CharSequence> requiredUpgradeHeaders();

        void upgradeTo(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface UpgradeCodecFactory {
        UpgradeCodec newUpgradeCodec(CharSequence charSequence);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator, io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, Object obj, List list) throws Exception {
        decode(channelHandlerContext, (HttpObject) obj, (List<Object>) list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class UpgradeEvent implements ReferenceCounted {
        private final CharSequence protocol;
        private final FullHttpRequest upgradeRequest;

        UpgradeEvent(CharSequence charSequence, FullHttpRequest fullHttpRequest) {
            this.protocol = charSequence;
            this.upgradeRequest = fullHttpRequest;
        }

        public CharSequence protocol() {
            return this.protocol;
        }

        public FullHttpRequest upgradeRequest() {
            return this.upgradeRequest;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public int refCnt() {
            return this.upgradeRequest.refCnt();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public UpgradeEvent retain() {
            this.upgradeRequest.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public UpgradeEvent retain(int i) {
            this.upgradeRequest.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public UpgradeEvent touch() {
            this.upgradeRequest.touch();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public UpgradeEvent touch(Object obj) {
            this.upgradeRequest.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public boolean release() {
            return this.upgradeRequest.release();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return this.upgradeRequest.release(i);
        }

        public String toString() {
            return "UpgradeEvent [protocol=" + ((Object) this.protocol) + ", upgradeRequest=" + this.upgradeRequest + ']';
        }
    }

    public HttpServerUpgradeHandler(SourceCodec sourceCodec, UpgradeCodecFactory upgradeCodecFactory) {
        this(sourceCodec, upgradeCodecFactory, 0);
    }

    public HttpServerUpgradeHandler(SourceCodec sourceCodec, UpgradeCodecFactory upgradeCodecFactory, int i) {
        super(i);
        this.sourceCodec = (SourceCodec) ObjectUtil.checkNotNull(sourceCodec, "sourceCodec");
        this.upgradeCodecFactory = (UpgradeCodecFactory) ObjectUtil.checkNotNull(upgradeCodecFactory, "upgradeCodecFactory");
    }

    protected void decode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        FullHttpRequest fullHttpRequest;
        this.handlingUpgrade |= isUpgradeRequest(httpObject);
        if (!this.handlingUpgrade) {
            ReferenceCountUtil.retain(httpObject);
            list.add(httpObject);
            return;
        }
        if (httpObject instanceof FullHttpRequest) {
            fullHttpRequest = (FullHttpRequest) httpObject;
            ReferenceCountUtil.retain(httpObject);
            list.add(httpObject);
        } else {
            super.decode(channelHandlerContext, (ChannelHandlerContext) httpObject, list);
            if (list.isEmpty()) {
                return;
            }
            this.handlingUpgrade = false;
            fullHttpRequest = (FullHttpRequest) list.get(0);
        }
        if (upgrade(channelHandlerContext, fullHttpRequest)) {
            list.clear();
        }
    }

    private static boolean isUpgradeRequest(HttpObject httpObject) {
        return (httpObject instanceof HttpRequest) && ((HttpRequest) httpObject).headers().get(HttpHeaderNames.UPGRADE) != null;
    }

    private boolean upgrade(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        UpgradeCodec upgradeCodec;
        CharSequence charSequence;
        List<String> all;
        List<CharSequence> splitHeader = splitHeader(fullHttpRequest.headers().get(HttpHeaderNames.UPGRADE));
        int size = splitHeader.size();
        int i = 0;
        while (true) {
            upgradeCodec = null;
            if (i >= size) {
                charSequence = null;
                break;
            }
            CharSequence charSequence2 = splitHeader.get(i);
            UpgradeCodec newUpgradeCodec = this.upgradeCodecFactory.newUpgradeCodec(charSequence2);
            if (newUpgradeCodec != null) {
                charSequence = charSequence2;
                upgradeCodec = newUpgradeCodec;
                break;
            }
            i++;
        }
        if (upgradeCodec == null || (all = fullHttpRequest.headers().getAll(HttpHeaderNames.CONNECTION)) == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder(all.size() * 10);
        Iterator<String> it = all.iterator();
        while (it.hasNext()) {
            sb.append((CharSequence) it.next());
            sb.append(',');
        }
        sb.setLength(sb.length() - 1);
        Collection<CharSequence> requiredUpgradeHeaders = upgradeCodec.requiredUpgradeHeaders();
        List<CharSequence> splitHeader2 = splitHeader(sb);
        if (!AsciiString.containsContentEqualsIgnoreCase(splitHeader2, HttpHeaderNames.UPGRADE) || !AsciiString.containsAllContentEqualsIgnoreCase(splitHeader2, requiredUpgradeHeaders)) {
            return false;
        }
        Iterator<CharSequence> it2 = requiredUpgradeHeaders.iterator();
        while (it2.hasNext()) {
            if (!fullHttpRequest.headers().contains(it2.next())) {
                return false;
            }
        }
        FullHttpResponse createUpgradeResponse = createUpgradeResponse(charSequence);
        if (!upgradeCodec.prepareUpgradeResponse(channelHandlerContext, fullHttpRequest, createUpgradeResponse.headers())) {
            return false;
        }
        UpgradeEvent upgradeEvent = new UpgradeEvent(charSequence, fullHttpRequest);
        try {
            ChannelFuture writeAndFlush = channelHandlerContext.writeAndFlush(createUpgradeResponse);
            this.sourceCodec.upgradeFrom(channelHandlerContext);
            upgradeCodec.upgradeTo(channelHandlerContext, fullHttpRequest);
            channelHandlerContext.pipeline().remove(this);
            channelHandlerContext.fireUserEventTriggered(upgradeEvent.retain());
            writeAndFlush.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            return true;
        } finally {
            upgradeEvent.release();
        }
    }

    private static FullHttpResponse createUpgradeResponse(CharSequence charSequence) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS, Unpooled.EMPTY_BUFFER, false);
        defaultFullHttpResponse.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE);
        defaultFullHttpResponse.headers().add(HttpHeaderNames.UPGRADE, charSequence);
        return defaultFullHttpResponse;
    }

    private static List<CharSequence> splitHeader(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        ArrayList arrayList = new ArrayList(4);
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!Character.isWhitespace(charAt)) {
                if (charAt == ',') {
                    arrayList.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(charAt);
                }
            }
        }
        if (sb.length() > 0) {
            arrayList.add(sb.toString());
        }
        return arrayList;
    }
}
