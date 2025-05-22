package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes6.dex */
class SubscriberMethodFinder {
    private static final int MODIFIERS_IGNORE = 1032;
    private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
    private static final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SubscriberMethod> findSubscriberMethods(Class<?> cls, String str) {
        List<SubscriberMethod> list;
        ThreadMode threadMode;
        String str2 = cls.getName() + FilenameUtils.EXTENSION_SEPARATOR + str;
        synchronized (methodCache) {
            list = methodCache.get(str2);
        }
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name = cls2.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getMethods()) {
                String name2 = method.getName();
                if (name2.startsWith(str)) {
                    int modifiers = method.getModifiers();
                    if ((modifiers & 1) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            String substring = name2.substring(str.length());
                            if (substring.length() == 0) {
                                threadMode = ThreadMode.PostThread;
                            } else if (substring.equals("MainThread")) {
                                threadMode = ThreadMode.MainThread;
                            } else if (substring.equals("BackgroundThread")) {
                                threadMode = ThreadMode.BackgroundThread;
                            } else if (substring.equals("Async")) {
                                threadMode = ThreadMode.Async;
                            } else if (!skipMethodVerificationForClasses.containsKey(cls2)) {
                                throw new EventBusException("Illegal onEvent method, check for typos: " + method);
                            }
                            Class<?> cls3 = parameterTypes[0];
                            sb.setLength(0);
                            sb.append(name2);
                            sb.append(Typography.greater);
                            sb.append(cls3.getName());
                            if (hashSet.add(sb.toString())) {
                                arrayList.add(new SubscriberMethod(method, threadMode, cls3));
                            }
                        } else {
                            continue;
                        }
                    } else if (!skipMethodVerificationForClasses.containsKey(cls2)) {
                        Log.d(EventBus.TAG, "Skipping method (not public, static or abstract): " + cls2 + "." + name2);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new EventBusException("Subscriber " + cls + " has no public methods called " + str);
        }
        synchronized (methodCache) {
            methodCache.put(str2, arrayList);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearCaches() {
        synchronized (methodCache) {
            methodCache.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void skipMethodVerificationFor(Class<?> cls) {
        if (!methodCache.isEmpty()) {
            throw new IllegalStateException("This method must be called before registering anything");
        }
        skipMethodVerificationForClasses.put(cls, cls);
    }

    public static void clearSkipMethodVerifications() {
        skipMethodVerificationForClasses.clear();
    }
}
