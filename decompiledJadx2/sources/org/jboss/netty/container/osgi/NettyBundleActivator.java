package org.jboss.netty.container.osgi;

import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.OsgiLoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/* loaded from: classes7.dex */
public class NettyBundleActivator implements BundleActivator {
    private OsgiLoggerFactory loggerFactory;

    public void start(BundleContext bundleContext) throws Exception {
        this.loggerFactory = new OsgiLoggerFactory(bundleContext);
        InternalLoggerFactory.setDefaultFactory(this.loggerFactory);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        OsgiLoggerFactory osgiLoggerFactory = this.loggerFactory;
        if (osgiLoggerFactory != null) {
            InternalLoggerFactory.setDefaultFactory(osgiLoggerFactory.getFallback());
            this.loggerFactory.destroy();
            this.loggerFactory = null;
        }
    }
}
