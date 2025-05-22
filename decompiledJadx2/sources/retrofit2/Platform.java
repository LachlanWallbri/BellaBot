package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
class Platform {
    private static final Platform PLATFORM = findPlatform();

    /* JADX INFO: Access modifiers changed from: package-private */
    public int defaultCallAdapterFactoriesSize() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Executor defaultCallbackExecutor() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int defaultConverterFactoriesSize() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDefaultMethod(Method method) {
        return false;
    }

    Platform() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException unused2) {
            return new Platform();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
        if (executor != null) {
            return Collections.singletonList(new ExecutorCallAdapterFactory(executor));
        }
        return Collections.singletonList(DefaultCallAdapterFactory.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<? extends Converter.Factory> defaultConverterFactories() {
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Java8 extends Platform {
        @Override // retrofit2.Platform
        int defaultCallAdapterFactoriesSize() {
            return 2;
        }

        @Override // retrofit2.Platform
        int defaultConverterFactoriesSize() {
            return 1;
        }

        Java8() {
        }

        @Override // retrofit2.Platform
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Override // retrofit2.Platform
        Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
            Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            declaredConstructor.setAccessible(true);
            return ((MethodHandles.Lookup) declaredConstructor.newInstance(cls, -1)).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }

        @Override // retrofit2.Platform
        List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(CompletableFutureCallAdapterFactory.INSTANCE);
            if (executor != null) {
                arrayList.add(new ExecutorCallAdapterFactory(executor));
            } else {
                arrayList.add(DefaultCallAdapterFactory.INSTANCE);
            }
            return Collections.unmodifiableList(arrayList);
        }

        @Override // retrofit2.Platform
        List<? extends Converter.Factory> defaultConverterFactories() {
            return Collections.singletonList(OptionalConverterFactory.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
      classes9.dex
     */
    /* loaded from: classes2.dex */
    public static class Android extends Platform {
        Android() {
        }

        @Override // retrofit2.Platform
        boolean isDefaultMethod(Method method) {
            if (Build.VERSION.SDK_INT < 24) {
                return false;
            }
            return method.isDefault();
        }

        @Override // retrofit2.Platform
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        @Override // retrofit2.Platform
        List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
            if (executor == null) {
                throw new AssertionError();
            }
            ExecutorCallAdapterFactory executorCallAdapterFactory = new ExecutorCallAdapterFactory(executor);
            return Build.VERSION.SDK_INT >= 24 ? Arrays.asList(CompletableFutureCallAdapterFactory.INSTANCE, executorCallAdapterFactory) : Collections.singletonList(executorCallAdapterFactory);
        }

        @Override // retrofit2.Platform
        int defaultCallAdapterFactoriesSize() {
            return Build.VERSION.SDK_INT >= 24 ? 2 : 1;
        }

        @Override // retrofit2.Platform
        List<? extends Converter.Factory> defaultConverterFactories() {
            if (Build.VERSION.SDK_INT >= 24) {
                return Collections.singletonList(OptionalConverterFactory.INSTANCE);
            }
            return Collections.emptyList();
        }

        @Override // retrofit2.Platform
        int defaultConverterFactoriesSize() {
            return Build.VERSION.SDK_INT >= 24 ? 1 : 0;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes8.dex
          classes9.dex
         */
        /* loaded from: classes2.dex */
        static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {
            }

            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                this.handler.post(runnable);
            }
        }
    }
}
