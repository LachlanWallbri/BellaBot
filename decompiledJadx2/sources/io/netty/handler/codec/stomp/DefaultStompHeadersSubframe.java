package io.netty.handler.codec.stomp;

import io.netty.handler.codec.DecoderResult;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class DefaultStompHeadersSubframe implements StompHeadersSubframe {
    protected final StompCommand command;
    protected DecoderResult decoderResult;
    protected final DefaultStompHeaders headers;

    public DefaultStompHeadersSubframe(StompCommand stompCommand) {
        this(stompCommand, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultStompHeadersSubframe(StompCommand stompCommand, DefaultStompHeaders defaultStompHeaders) {
        this.decoderResult = DecoderResult.SUCCESS;
        if (stompCommand == null) {
            throw new NullPointerException("command");
        }
        this.command = stompCommand;
        this.headers = defaultStompHeaders == null ? new DefaultStompHeaders() : defaultStompHeaders;
    }

    @Override // io.netty.handler.codec.stomp.StompHeadersSubframe
    public StompCommand command() {
        return this.command;
    }

    @Override // io.netty.handler.codec.stomp.StompHeadersSubframe
    public StompHeaders headers() {
        return this.headers;
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public DecoderResult decoderResult() {
        return this.decoderResult;
    }

    @Override // io.netty.handler.codec.DecoderResultProvider
    public void setDecoderResult(DecoderResult decoderResult) {
        this.decoderResult = decoderResult;
    }

    public String toString() {
        return "StompFrame{command=" + this.command + ", headers=" + this.headers + '}';
    }
}
