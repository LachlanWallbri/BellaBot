package org.jboss.netty.container.spring;

import org.jboss.netty.logging.CommonsLoggerFactory;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class NettyLoggerConfigurator {
    public NettyLoggerConfigurator() {
        InternalLoggerFactory.setDefaultFactory(new CommonsLoggerFactory());
    }
}
