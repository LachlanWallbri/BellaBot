package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@Deprecated
/* loaded from: classes.dex */
public class NioUdtByteRendezvousChannel extends NioUdtByteConnectorChannel {
    public NioUdtByteRendezvousChannel() {
        super((SocketChannelUDT) NioUdtProvider.newRendezvousChannelUDT(TypeUDT.STREAM));
    }
}
