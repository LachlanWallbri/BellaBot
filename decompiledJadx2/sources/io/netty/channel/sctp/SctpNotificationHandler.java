package io.netty.channel.sctp;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.Notification;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import com.sun.nio.sctp.SendFailedNotification;
import com.sun.nio.sctp.ShutdownNotification;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class SctpNotificationHandler extends AbstractNotificationHandler<Object> {
    private final SctpChannel sctpChannel;

    public SctpNotificationHandler(SctpChannel sctpChannel) {
        if (sctpChannel == null) {
            throw new NullPointerException("sctpChannel");
        }
        this.sctpChannel = sctpChannel;
    }

    public HandlerResult handleNotification(AssociationChangeNotification associationChangeNotification, Object obj) {
        fireEvent(associationChangeNotification);
        return HandlerResult.CONTINUE;
    }

    public HandlerResult handleNotification(PeerAddressChangeNotification peerAddressChangeNotification, Object obj) {
        fireEvent(peerAddressChangeNotification);
        return HandlerResult.CONTINUE;
    }

    public HandlerResult handleNotification(SendFailedNotification sendFailedNotification, Object obj) {
        fireEvent(sendFailedNotification);
        return HandlerResult.CONTINUE;
    }

    public HandlerResult handleNotification(ShutdownNotification shutdownNotification, Object obj) {
        fireEvent(shutdownNotification);
        this.sctpChannel.close();
        return HandlerResult.RETURN;
    }

    private void fireEvent(Notification notification) {
        this.sctpChannel.pipeline().fireUserEventTriggered((Object) notification);
    }
}
