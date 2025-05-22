package io.grpc.netty.shaded.io.netty.util;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Constructor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ResourceLeakDetectorFactory {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ResourceLeakDetectorFactory.class);
    private static volatile ResourceLeakDetectorFactory factoryInstance = new DefaultResourceLeakDetectorFactory();

    @Deprecated
    public abstract <T> ResourceLeakDetector<T> newResourceLeakDetector(Class<T> cls, int i, long j);

    public static ResourceLeakDetectorFactory instance() {
        return factoryInstance;
    }

    public static void setResourceLeakDetectorFactory(ResourceLeakDetectorFactory resourceLeakDetectorFactory) {
        factoryInstance = (ResourceLeakDetectorFactory) ObjectUtil.checkNotNull(resourceLeakDetectorFactory, "factory");
    }

    public final <T> ResourceLeakDetector<T> newResourceLeakDetector(Class<T> cls) {
        return newResourceLeakDetector(cls, ResourceLeakDetector.SAMPLING_INTERVAL);
    }

    public <T> ResourceLeakDetector<T> newResourceLeakDetector(Class<T> cls, int i) {
        ObjectUtil.checkPositive(i, "samplingInterval");
        return newResourceLeakDetector(cls, i, Long.MAX_VALUE);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class DefaultResourceLeakDetectorFactory extends ResourceLeakDetectorFactory {
        private final Constructor<?> customClassConstructor;
        private final Constructor<?> obsoleteCustomClassConstructor;

        DefaultResourceLeakDetectorFactory() {
            String str;
            try {
                str = SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.customResourceLeakDetector");
            } catch (Throwable th) {
                ResourceLeakDetectorFactory.logger.error("Could not access System property: io.netty.customResourceLeakDetector", th);
                str = null;
            }
            if (str == null) {
                this.customClassConstructor = null;
                this.obsoleteCustomClassConstructor = null;
            } else {
                this.obsoleteCustomClassConstructor = obsoleteCustomClassConstructor(str);
                this.customClassConstructor = customClassConstructor(str);
            }
        }

        private static Constructor<?> obsoleteCustomClassConstructor(String str) {
            try {
                Class<?> cls = Class.forName(str, true, PlatformDependent.getSystemClassLoader());
                if (ResourceLeakDetector.class.isAssignableFrom(cls)) {
                    return cls.getConstructor(Class.class, Integer.TYPE, Long.TYPE);
                }
                ResourceLeakDetectorFactory.logger.error("Class {} does not inherit from ResourceLeakDetector.", str);
                return null;
            } catch (Throwable th) {
                ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector class provided: {}", str, th);
                return null;
            }
        }

        private static Constructor<?> customClassConstructor(String str) {
            try {
                Class<?> cls = Class.forName(str, true, PlatformDependent.getSystemClassLoader());
                if (ResourceLeakDetector.class.isAssignableFrom(cls)) {
                    return cls.getConstructor(Class.class, Integer.TYPE);
                }
                ResourceLeakDetectorFactory.logger.error("Class {} does not inherit from ResourceLeakDetector.", str);
                return null;
            } catch (Throwable th) {
                ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector class provided: {}", str, th);
                return null;
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ResourceLeakDetectorFactory
        public <T> ResourceLeakDetector<T> newResourceLeakDetector(Class<T> cls, int i, long j) {
            Constructor<?> constructor = this.obsoleteCustomClassConstructor;
            if (constructor != null) {
                try {
                    ResourceLeakDetector<T> resourceLeakDetector = (ResourceLeakDetector) constructor.newInstance(cls, Integer.valueOf(i), Long.valueOf(j));
                    ResourceLeakDetectorFactory.logger.debug("Loaded custom ResourceLeakDetector: {}", this.obsoleteCustomClassConstructor.getDeclaringClass().getName());
                    return resourceLeakDetector;
                } catch (Throwable th) {
                    ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector provided: {} with the given resource: {}", this.obsoleteCustomClassConstructor.getDeclaringClass().getName(), cls, th);
                }
            }
            ResourceLeakDetector<T> resourceLeakDetector2 = new ResourceLeakDetector<>((Class<?>) cls, i, j);
            ResourceLeakDetectorFactory.logger.debug("Loaded default ResourceLeakDetector: {}", resourceLeakDetector2);
            return resourceLeakDetector2;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ResourceLeakDetectorFactory
        public <T> ResourceLeakDetector<T> newResourceLeakDetector(Class<T> cls, int i) {
            Constructor<?> constructor = this.customClassConstructor;
            if (constructor != null) {
                try {
                    ResourceLeakDetector<T> resourceLeakDetector = (ResourceLeakDetector) constructor.newInstance(cls, Integer.valueOf(i));
                    ResourceLeakDetectorFactory.logger.debug("Loaded custom ResourceLeakDetector: {}", this.customClassConstructor.getDeclaringClass().getName());
                    return resourceLeakDetector;
                } catch (Throwable th) {
                    ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector provided: {} with the given resource: {}", this.customClassConstructor.getDeclaringClass().getName(), cls, th);
                }
            }
            ResourceLeakDetector<T> resourceLeakDetector2 = new ResourceLeakDetector<>(cls, i);
            ResourceLeakDetectorFactory.logger.debug("Loaded default ResourceLeakDetector: {}", resourceLeakDetector2);
            return resourceLeakDetector2;
        }
    }
}
