package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class EventBus {
    private static final String DEFAULT_METHOD_NAME = "onEvent";
    private static volatile EventBus defaultInstance;
    private boolean subscribed;
    static ExecutorService executorService = Executors.newCachedThreadPool();
    public static String TAG = MoveError.LEVEL_EVENT;
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    private final ThreadLocal<PostingThreadState> currentPostingThreadState = new ThreadLocal<PostingThreadState>() { // from class: de.greenrobot.event.EventBus.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public PostingThreadState initialValue() {
            return new PostingThreadState();
        }
    };
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
    private final Map<Object, List<Class<?>>> typesBySubscriber = new HashMap();
    private final Map<Class<?>, Object> stickyEvents = new ConcurrentHashMap();
    private final HandlerPoster mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
    private final BackgroundPoster backgroundPoster = new BackgroundPoster(this);
    private final AsyncPoster asyncPoster = new AsyncPoster(this);
    private final SubscriberMethodFinder subscriberMethodFinder = new SubscriberMethodFinder();
    private boolean logSubscriberExceptions = true;

    /* loaded from: classes6.dex */
    interface PostCallback {
        void onPostCompleted(List<SubscriberExceptionEvent> list);
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        eventTypesCache.clear();
    }

    public static void skipMethodVerificationFor(Class<?> cls) {
        SubscriberMethodFinder.skipMethodVerificationFor(cls);
    }

    public static void clearSkipMethodNameVerifications() {
        SubscriberMethodFinder.clearSkipMethodVerifications();
    }

    public void configureLogSubscriberExceptions(boolean z) {
        if (this.subscribed) {
            throw new EventBusException("This method must be called before any registration");
        }
        this.logSubscriberExceptions = z;
    }

    public void register(Object obj) {
        register(obj, DEFAULT_METHOD_NAME, false, 0);
    }

    public void register(Object obj, int i) {
        register(obj, DEFAULT_METHOD_NAME, false, i);
    }

    @Deprecated
    public void register(Object obj, String str) {
        register(obj, str, false, 0);
    }

    public void registerSticky(Object obj) {
        register(obj, DEFAULT_METHOD_NAME, true, 0);
    }

    public void registerSticky(Object obj, int i) {
        register(obj, DEFAULT_METHOD_NAME, true, i);
    }

    @Deprecated
    public void registerSticky(Object obj, String str) {
        register(obj, str, true, 0);
    }

    private synchronized void register(Object obj, String str, boolean z, int i) {
        Iterator<SubscriberMethod> it = this.subscriberMethodFinder.findSubscriberMethods(obj.getClass(), str).iterator();
        while (it.hasNext()) {
            subscribe(obj, it.next(), z, i);
        }
    }

    @Deprecated
    public void register(Object obj, Class<?> cls, Class<?>... clsArr) {
        register(obj, DEFAULT_METHOD_NAME, false, cls, clsArr);
    }

    @Deprecated
    public void register(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        register(obj, str, false, cls, clsArr);
    }

    @Deprecated
    public void registerSticky(Object obj, Class<?> cls, Class<?>... clsArr) {
        register(obj, DEFAULT_METHOD_NAME, true, cls, clsArr);
    }

    @Deprecated
    public void registerSticky(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        register(obj, str, true, cls, clsArr);
    }

    private synchronized void register(Object obj, String str, boolean z, Class<?> cls, Class<?>... clsArr) {
        for (SubscriberMethod subscriberMethod : this.subscriberMethodFinder.findSubscriberMethods(obj.getClass(), str)) {
            if (cls == subscriberMethod.eventType) {
                subscribe(obj, subscriberMethod, z, 0);
            } else if (clsArr != null) {
                int length = clsArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (clsArr[i] == subscriberMethod.eventType) {
                        subscribe(obj, subscriberMethod, z, 0);
                        break;
                    }
                    i++;
                }
            }
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod, boolean z, int i) {
        Object obj2;
        this.subscribed = true;
        Class<?> cls = subscriberMethod.eventType;
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod, i);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else {
            Iterator<Subscription> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                if (it.next().equals(subscription)) {
                    throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
                }
            }
        }
        int size = copyOnWriteArrayList.size();
        for (int i2 = 0; i2 <= size; i2++) {
            if (i2 == size || subscription.priority > copyOnWriteArrayList.get(i2).priority) {
                copyOnWriteArrayList.add(i2, subscription);
                break;
            }
        }
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList<>();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (z) {
            synchronized (this.stickyEvents) {
                obj2 = this.stickyEvents.get(cls);
            }
            if (obj2 != null) {
                postToSubscription(subscription, obj2, Looper.getMainLooper() == Looper.myLooper());
            }
        }
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.typesBySubscriber.containsKey(obj);
    }

    @Deprecated
    public synchronized void unregister(Object obj, Class<?>... clsArr) {
        if (clsArr.length == 0) {
            throw new IllegalArgumentException("Provide at least one event class");
        }
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            for (Class<?> cls : clsArr) {
                unubscribeByEventType(obj, cls);
                list.remove(cls);
            }
            if (list.isEmpty()) {
                this.typesBySubscriber.remove(obj);
            }
        } else {
            Log.w(TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    private void unubscribeByEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                Subscription subscription = copyOnWriteArrayList.get(i);
                if (subscription.subscriber == obj) {
                    subscription.active = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public synchronized void unregister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list != null) {
            Iterator<Class<?>> it = list.iterator();
            while (it.hasNext()) {
                unubscribeByEventType(obj, it.next());
            }
            this.typesBySubscriber.remove(obj);
        } else {
            Log.w(TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void post(Object obj) {
        PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        List<Object> list = postingThreadState.eventQueue;
        list.add(obj);
        if (postingThreadState.isPosting) {
            return;
        }
        postingThreadState.isMainThread = Looper.getMainLooper() == Looper.myLooper();
        postingThreadState.isPosting = true;
        if (postingThreadState.canceled) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        while (!list.isEmpty()) {
            try {
                postSingleEvent(list.remove(0), postingThreadState);
            } finally {
                postingThreadState.isPosting = false;
                postingThreadState.isMainThread = false;
            }
        }
    }

    public void cancelEventDelivery(Object obj) {
        PostingThreadState postingThreadState = this.currentPostingThreadState.get();
        if (!postingThreadState.isPosting) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        }
        if (obj == null) {
            throw new EventBusException("Event may not be null");
        }
        if (postingThreadState.event != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        }
        if (postingThreadState.subscription.subscriberMethod.threadMode != ThreadMode.PostThread) {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
        postingThreadState.canceled = true;
    }

    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public <T> T getStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.get(cls));
        }
        return cast;
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        T cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.remove(cls));
        }
        return cast;
    }

    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.stickyEvents.get(cls))) {
                return false;
            }
            this.stickyEvents.remove(cls);
            return true;
        }
    }

    public void removeAllStickyEvents() {
        synchronized (this.stickyEvents) {
            this.stickyEvents.clear();
        }
    }

    private void postSingleEvent(Object obj, PostingThreadState postingThreadState) throws Error {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        Class<?> cls = obj.getClass();
        List<Class<?>> findEventTypes = findEventTypes(cls);
        int size = findEventTypes.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            Class<?> cls2 = findEventTypes.get(i);
            synchronized (this) {
                copyOnWriteArrayList = this.subscriptionsByEventType.get(cls2);
            }
            if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                Iterator<Subscription> it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    Subscription next = it.next();
                    postingThreadState.event = obj;
                    postingThreadState.subscription = next;
                    try {
                        postToSubscription(next, obj, postingThreadState.isMainThread);
                        if (postingThreadState.canceled) {
                            break;
                        }
                    } finally {
                        postingThreadState.event = null;
                        postingThreadState.subscription = null;
                        postingThreadState.canceled = false;
                    }
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        Log.d(TAG, "No subscribers registered for event " + cls);
        if (cls == NoSubscriberEvent.class || cls == SubscriberExceptionEvent.class) {
            return;
        }
        post(new NoSubscriberEvent(this, obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: de.greenrobot.event.EventBus$2 */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class C59652 {
        static final /* synthetic */ int[] $SwitchMap$de$greenrobot$event$ThreadMode = new int[ThreadMode.values().length];

        static {
            try {
                $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$de$greenrobot$event$ThreadMode[ThreadMode.Async.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        int i = C59652.$SwitchMap$de$greenrobot$event$ThreadMode[subscription.subscriberMethod.threadMode.ordinal()];
        if (i == 1) {
            invokeSubscriber(subscription, obj);
            return;
        }
        if (i == 2) {
            if (z) {
                invokeSubscriber(subscription, obj);
                return;
            } else {
                this.mainThreadPoster.enqueue(subscription, obj);
                return;
            }
        }
        if (i == 3) {
            if (z) {
                this.backgroundPoster.enqueue(subscription, obj);
                return;
            } else {
                invokeSubscriber(subscription, obj);
                return;
            }
        }
        if (i == 4) {
            this.asyncPoster.enqueue(subscription, obj);
            return;
        }
        throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
    }

    private List<Class<?>> findEventTypes(Class<?> cls) {
        List<Class<?>> list;
        synchronized (eventTypesCache) {
            list = eventTypesCache.get(cls);
            if (list == null) {
                list = new ArrayList<>();
                for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    addInterfaces(list, cls2.getInterfaces());
                }
                eventTypesCache.put(cls, list);
            }
        }
        return list;
    }

    static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            invokeSubscriber(subscription, obj);
        }
    }

    void invokeSubscriber(Subscription subscription, Object obj) throws Error {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (obj instanceof SubscriberExceptionEvent) {
                Log.e(TAG, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", cause);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Log.e(TAG, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
                return;
            }
            if (this.logSubscriberExceptions) {
                Log.e(TAG, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.subscriber.getClass(), cause);
            }
            post(new SubscriberExceptionEvent(this, cause, obj, subscription.subscriber));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class PostingThreadState {
        boolean canceled;
        Object event;
        List<Object> eventQueue = new ArrayList();
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;

        PostingThreadState() {
        }
    }
}
