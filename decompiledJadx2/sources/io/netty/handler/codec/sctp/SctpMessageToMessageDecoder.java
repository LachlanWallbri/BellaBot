package io.netty.handler.codec.sctp;

import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageDecoder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class SctpMessageToMessageDecoder extends MessageToMessageDecoder<SctpMessage> {
    @Override // io.netty.handler.codec.MessageToMessageDecoder
    public boolean acceptInboundMessage(Object obj) throws Exception {
        if (!(obj instanceof SctpMessage)) {
            return false;
        }
        if (((SctpMessage) obj).isComplete()) {
            return true;
        }
        throw new CodecException(String.format("Received SctpMessage is not complete, please add %s in the pipeline before this handler", SctpMessageCompletionHandler.class.getSimpleName()));
    }
}
