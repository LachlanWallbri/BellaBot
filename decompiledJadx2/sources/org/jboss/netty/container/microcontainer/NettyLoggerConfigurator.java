package org.jboss.netty.container.microcontainer;

import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.JBossLoggerFactory;

/* loaded from: classes7.dex */
public class NettyLoggerConfigurator {
    public NettyLoggerConfigurator() {
        InternalLoggerFactory.setDefaultFactory(new JBossLoggerFactory());
    }
}
