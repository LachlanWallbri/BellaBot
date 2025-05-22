package com.pudutech.bumblebee.presenter.utils.cloner.cloning;

import com.pudutech.bumblebee.presenter.utils.cloner.cloning.ICloningStrategy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;
import org.objenesis.instantiator.ObjectInstantiator;

/* loaded from: classes4.dex */
public class Cloner {
    private static final Field[] EMPTY_FIELD_ARRAY = new Field[0];
    private static IDeepCloner IGNORE_CLONER;
    private static IDeepCloner NULL_CLONER;
    private boolean cloneAnonymousParent;
    private boolean cloneSynthetics;
    private Map<Class, IDeepCloner> cloners;
    private boolean cloningEnabled;
    private List<ICloningStrategy> cloningStrategies;
    private IDeepCloner deepCloner;
    private IDumpCloned dumpCloned;
    private final Map<Class<?>, IFastCloner> fastCloners;
    private final ConcurrentHashMap<Class<?>, List<Field>> fieldsCache;
    private final Set<Class<?>> ignored;
    private final Set<Class<?>> ignoredInstanceOf;
    private Map<Object, Object> ignoredInstances;
    private final ConcurrentHashMap<Class<?>, Boolean> immutables;
    private final IInstantiationStrategy instantiationStrategy;
    private final Set<Class<?>> nullInstead;
    private final Set<Class<? extends Annotation>> nullInsteadFieldAnnotations;
    private boolean nullTransient;

    protected boolean considerImmutable(Class<?> cls) {
        return false;
    }

    protected void registerKnownConstants() {
    }

    public IDumpCloned getDumpCloned() {
        return this.dumpCloned;
    }

    public void setDumpCloned(IDumpCloned iDumpCloned) {
        this.dumpCloned = iDumpCloned;
    }

    public Cloner() {
        this.ignored = new HashSet();
        this.ignoredInstanceOf = new HashSet();
        this.nullInstead = new HashSet();
        this.nullInsteadFieldAnnotations = new HashSet();
        this.fastCloners = new HashMap();
        this.fieldsCache = new ConcurrentHashMap<>();
        this.dumpCloned = null;
        this.cloningEnabled = true;
        this.nullTransient = false;
        this.cloneSynthetics = true;
        this.deepCloner = new IDeepCloner() { // from class: com.pudutech.bumblebee.presenter.utils.cloner.cloning.Cloner.1
            @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
            public <T> T deepClone(T t, Map<Object, Object> map) {
                return (T) Cloner.this.cloneInternal(t, map);
            }
        };
        this.immutables = new ConcurrentHashMap<>();
        this.cloneAnonymousParent = true;
        this.cloners = new ConcurrentHashMap();
        this.instantiationStrategy = ObjenesisInstantiationStrategy.getInstance();
        init();
    }

    public Cloner(IInstantiationStrategy iInstantiationStrategy) {
        this.ignored = new HashSet();
        this.ignoredInstanceOf = new HashSet();
        this.nullInstead = new HashSet();
        this.nullInsteadFieldAnnotations = new HashSet();
        this.fastCloners = new HashMap();
        this.fieldsCache = new ConcurrentHashMap<>();
        this.dumpCloned = null;
        this.cloningEnabled = true;
        this.nullTransient = false;
        this.cloneSynthetics = true;
        this.deepCloner = new IDeepCloner() { // from class: com.pudutech.bumblebee.presenter.utils.cloner.cloning.Cloner.1
            @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
            public <T> T deepClone(T t, Map<Object, Object> map) {
                return (T) Cloner.this.cloneInternal(t, map);
            }
        };
        this.immutables = new ConcurrentHashMap<>();
        this.cloneAnonymousParent = true;
        this.cloners = new ConcurrentHashMap();
        this.instantiationStrategy = iInstantiationStrategy;
        init();
    }

    public boolean isNullTransient() {
        return this.nullTransient;
    }

    public void setNullTransient(boolean z) {
        this.nullTransient = z;
    }

    public void setCloneSynthetics(boolean z) {
        this.cloneSynthetics = z;
    }

    private void init() {
        registerKnownJdkImmutableClasses();
        registerKnownConstants();
        registerFastCloners();
    }

    protected void registerFastCloners() {
        this.fastCloners.put(GregorianCalendar.class, new FastClonerCalendar());
        this.fastCloners.put(ArrayList.class, new FastClonerArrayList());
        this.fastCloners.put(LinkedList.class, new FastClonerLinkedList());
        this.fastCloners.put(HashSet.class, new FastClonerHashSet());
        this.fastCloners.put(HashMap.class, new FastClonerHashMap());
        this.fastCloners.put(TreeMap.class, new FastClonerTreeMap());
        this.fastCloners.put(TreeSet.class, new FastClonerTreeSet());
        this.fastCloners.put(LinkedHashMap.class, new FastClonerLinkedHashMap());
        this.fastCloners.put(ConcurrentHashMap.class, new FastClonerConcurrentHashMap());
        this.fastCloners.put(ConcurrentLinkedQueue.class, new FastClonerConcurrentLinkedQueue());
        FastClonerArrayListSubList fastClonerArrayListSubList = new FastClonerArrayListSubList();
        registerInaccessibleClassToBeFastCloned("java.util.AbstractList$SubList", fastClonerArrayListSubList);
        registerInaccessibleClassToBeFastCloned("java.util.ArrayList$SubList", fastClonerArrayListSubList);
        registerInaccessibleClassToBeFastCloned("java.util.SubList", fastClonerArrayListSubList);
        registerInaccessibleClassToBeFastCloned("java.util.RandomAccessSubList", fastClonerArrayListSubList);
    }

    protected void registerInaccessibleClassToBeFastCloned(String str, IFastCloner iFastCloner) {
        try {
            this.fastCloners.put(getClass().getClassLoader().loadClass(str), iFastCloner);
        } catch (ClassNotFoundException unused) {
        }
    }

    protected Object fastClone(Object obj, Map<Object, Object> map) {
        IFastCloner iFastCloner = this.fastCloners.get(obj.getClass());
        if (iFastCloner != null) {
            return iFastCloner.clone(obj, this.deepCloner, map);
        }
        return null;
    }

    public void registerConstant(Object obj) {
        if (this.ignoredInstances == null) {
            this.ignoredInstances = new IdentityHashMap();
        }
        this.ignoredInstances.put(obj, obj);
    }

    public void registerConstant(Class<?> cls, String str) {
        try {
            for (Field field : allFields(cls)) {
                if (field.getName().equals(str)) {
                    field.setAccessible(true);
                    registerConstant(field.get(null));
                    return;
                }
            }
            throw new CloningException("No such field : " + str);
        } catch (IllegalAccessException e) {
            throw new CloningException(e);
        } catch (IllegalArgumentException e2) {
            throw new CloningException(e2);
        } catch (SecurityException e3) {
            throw new CloningException(e3);
        }
    }

    protected void registerKnownJdkImmutableClasses() {
        registerImmutable(String.class);
        registerImmutable(Integer.class);
        registerImmutable(Long.class);
        registerImmutable(Boolean.class);
        registerImmutable(Class.class);
        registerImmutable(Float.class);
        registerImmutable(Double.class);
        registerImmutable(Character.class);
        registerImmutable(Byte.class);
        registerImmutable(Short.class);
        registerImmutable(Void.class);
        registerImmutable(BigDecimal.class);
        registerImmutable(BigInteger.class);
        registerImmutable(URI.class);
        registerImmutable(URL.class);
        registerImmutable(UUID.class);
        registerImmutable(Pattern.class);
    }

    public void registerCloningStrategy(ICloningStrategy iCloningStrategy) {
        if (iCloningStrategy == null) {
            throw new NullPointerException("strategy can't be null");
        }
        if (this.cloningStrategies == null) {
            this.cloningStrategies = new ArrayList();
        }
        this.cloningStrategies.add(iCloningStrategy);
    }

    public void registerStaticFields(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            for (Field field : allFields(cls)) {
                if (Modifier.isStatic(field.getModifiers()) && !field.getType().isPrimitive()) {
                    registerConstant(cls, field.getName());
                }
            }
        }
    }

    public void setExtraStaticFields(Set<Class<?>> set) {
        registerStaticFields((Class[]) set.toArray());
    }

    public void dontClone(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.ignored.add(cls);
        }
    }

    public void dontCloneInstanceOf(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.ignoredInstanceOf.add(cls);
        }
    }

    public void setDontCloneInstanceOf(Class<?>... clsArr) {
        dontCloneInstanceOf(clsArr);
    }

    public void nullInsteadOfClone(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.nullInstead.add(cls);
        }
    }

    public void setExtraNullInsteadOfClone(Set<Class<?>> set) {
        this.nullInstead.addAll(set);
    }

    @SafeVarargs
    public final void nullInsteadOfCloneFieldAnnotation(Class<? extends Annotation>... clsArr) {
        for (Class<? extends Annotation> cls : clsArr) {
            this.nullInsteadFieldAnnotations.add(cls);
        }
    }

    public void setExtraNullInsteadOfCloneFieldAnnotation(Set<Class<? extends Annotation>> set) {
        this.nullInsteadFieldAnnotations.addAll(set);
    }

    public void registerImmutable(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.ignored.add(cls);
        }
    }

    public void setExtraImmutables(Set<Class<?>> set) {
        this.ignored.addAll(set);
    }

    public void registerFastCloner(Class<?> cls, IFastCloner iFastCloner) {
        if (this.fastCloners.containsKey(cls)) {
            throw new IllegalArgumentException(cls + " already fast-cloned!");
        }
        this.fastCloners.put(cls, iFastCloner);
    }

    public void unregisterFastCloner(Class<?> cls) {
        this.fastCloners.remove(cls);
    }

    protected <T> T newInstance(Class<T> cls) {
        return (T) this.instantiationStrategy.newInstance(cls);
    }

    public <T> T fastCloneOrNewInstance(Class<T> cls) {
        T t = (T) fastClone(cls, null);
        return t != null ? t : (T) newInstance(cls);
    }

    public <T> T deepClone(T t) {
        if (t == null) {
            return null;
        }
        if (!this.cloningEnabled) {
            return t;
        }
        IDumpCloned iDumpCloned = this.dumpCloned;
        if (iDumpCloned != null) {
            iDumpCloned.startCloning(t.getClass());
        }
        return (T) cloneInternal(t, new ClonesMap());
    }

    public <T> T deepCloneDontCloneInstances(T t, Object... objArr) {
        if (t == null) {
            return null;
        }
        if (!this.cloningEnabled) {
            return t;
        }
        IDumpCloned iDumpCloned = this.dumpCloned;
        if (iDumpCloned != null) {
            iDumpCloned.startCloning(t.getClass());
        }
        ClonesMap clonesMap = new ClonesMap();
        for (Object obj : objArr) {
            clonesMap.put(obj, obj);
        }
        return (T) cloneInternal(t, clonesMap);
    }

    public <T> T shallowClone(T t) {
        if (t == null) {
            return null;
        }
        return !this.cloningEnabled ? t : (T) cloneInternal(t, null);
    }

    protected Class<?> getImmutableAnnotation() {
        return Immutable.class;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isImmutable(Class<?> cls) {
        Boolean bool = this.immutables.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        if (considerImmutable(cls)) {
            return true;
        }
        Class<?> immutableAnnotation = getImmutableAnnotation();
        for (Annotation annotation : cls.getDeclaredAnnotations()) {
            if (annotation.annotationType() == immutableAnnotation) {
                this.immutables.put(cls, Boolean.TRUE);
                return true;
            }
        }
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Annotation annotation2 : superclass.getDeclaredAnnotations()) {
                if (annotation2.annotationType() == Immutable.class && ((Immutable) annotation2).subClass()) {
                    this.immutables.put(cls, Boolean.TRUE);
                    return true;
                }
            }
        }
        this.immutables.put(cls, Boolean.FALSE);
        return false;
    }

    protected <T> T cloneInternal(T t, Map<Object, Object> map) {
        T t2;
        if (t == null || t == this) {
            return null;
        }
        if (map != null && (t2 = (T) map.get(t)) != null) {
            return t2;
        }
        Class<?> cls = t.getClass();
        IDeepCloner iDeepCloner = this.cloners.get(cls);
        if (iDeepCloner == null) {
            iDeepCloner = findDeepCloner(cls);
            this.cloners.put(cls, iDeepCloner);
        }
        if (iDeepCloner == IGNORE_CLONER) {
            return t;
        }
        if (iDeepCloner == NULL_CLONER) {
            return null;
        }
        return (T) iDeepCloner.deepClone(t, map);
    }

    private IDeepCloner findDeepCloner(Class<?> cls) {
        if (Enum.class.isAssignableFrom(cls)) {
            return IGNORE_CLONER;
        }
        if (IFreezable.class.isAssignableFrom(cls)) {
            return new IFreezableCloner(cls);
        }
        if (this.nullInstead.contains(cls)) {
            return NULL_CLONER;
        }
        if (this.ignored.contains(cls)) {
            return IGNORE_CLONER;
        }
        if (isImmutable(cls)) {
            return IGNORE_CLONER;
        }
        if (cls.isArray()) {
            return new CloneArrayCloner(cls);
        }
        IFastCloner iFastCloner = this.fastCloners.get(cls);
        if (iFastCloner != null) {
            return new FastClonerCloner(iFastCloner);
        }
        Iterator<Class<?>> it = this.ignoredInstanceOf.iterator();
        while (it.hasNext()) {
            if (it.next().isAssignableFrom(cls)) {
                return IGNORE_CLONER;
            }
        }
        return new CloneObjectCloner(cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class CloneArrayCloner implements IDeepCloner {
        private Class<?> componentType;
        private boolean immutable;
        private boolean primitive;

        CloneArrayCloner(Class<?> cls) {
            this.primitive = cls.getComponentType().isPrimitive();
            this.immutable = Cloner.this.isImmutable(cls.getComponentType());
            this.componentType = cls.getComponentType();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
        public <T> T deepClone(T t, Map<Object, Object> map) {
            if (Cloner.this.dumpCloned != null) {
                Cloner.this.dumpCloned.startCloning(t.getClass());
            }
            int length = Array.getLength(t);
            T t2 = (T) Array.newInstance(this.componentType, length);
            if (map != null) {
                map.put(t, t2);
            }
            int i = 0;
            if (this.primitive || this.immutable) {
                System.arraycopy(t, 0, t2, 0, length);
            } else if (map == null) {
                while (i < length) {
                    Array.set(t2, i, Array.get(t, i));
                    i++;
                }
            } else {
                while (i < length) {
                    Array.set(t2, i, Cloner.this.cloneInternal(Array.get(t, i), map));
                    i++;
                }
            }
            return t2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class FastClonerCloner implements IDeepCloner {
        private IDeepCloner cloneInternal;
        private IFastCloner fastCloner;

        FastClonerCloner(IFastCloner iFastCloner) {
            this.fastCloner = iFastCloner;
            this.cloneInternal = Cloner.this.deepCloner;
        }

        @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
        public <T> T deepClone(T t, Map<Object, Object> map) {
            T t2 = (T) this.fastCloner.clone(t, this.cloneInternal, map);
            if (map != null) {
                map.put(t, t2);
            }
            return t2;
        }
    }

    static {
        IGNORE_CLONER = new IgnoreClassCloner();
        NULL_CLONER = new NullClassCloner();
    }

    /* loaded from: classes4.dex */
    private static class IgnoreClassCloner implements IDeepCloner {
        private IgnoreClassCloner() {
        }

        @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
        public <T> T deepClone(T t, Map<Object, Object> map) {
            throw new CloningException("Don't call this directly");
        }
    }

    /* loaded from: classes4.dex */
    private static class NullClassCloner implements IDeepCloner {
        private NullClassCloner() {
        }

        @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
        public <T> T deepClone(T t, Map<Object, Object> map) {
            throw new CloningException("Don't call this directly");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class IFreezableCloner implements IDeepCloner {
        IDeepCloner cloner;

        public IFreezableCloner(Class<?> cls) {
            this.cloner = new CloneObjectCloner(cls);
        }

        @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
        public <T> T deepClone(T t, Map<Object, Object> map) {
            return ((t instanceof IFreezable) && ((IFreezable) t).isFrozen()) ? t : (T) this.cloner.deepClone(t, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class CloneObjectCloner implements IDeepCloner {
        private final Field[] fields;
        private final ObjectInstantiator<?> instantiator;
        private final int numFields;
        private final boolean[] shouldClone;

        CloneObjectCloner(Class<?> cls) {
            int i;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Class<?> cls2 = cls;
            do {
                for (Field field : cls2.getDeclaredFields()) {
                    boolean z = true;
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    int modifiers = field.getModifiers();
                    if (!Modifier.isStatic(modifiers) && ((!Cloner.this.nullTransient || !Modifier.isTransient(modifiers)) && !isFieldNullInsteadBecauseOfAnnotation(field))) {
                        arrayList.add(field);
                        if ((!Cloner.this.cloneSynthetics && field.isSynthetic()) || (!Cloner.this.cloneAnonymousParent && Cloner.this.isAnonymousParent(field))) {
                            z = false;
                        }
                        arrayList2.add(Boolean.valueOf(z));
                    }
                }
                cls2 = cls2.getSuperclass();
                if (cls2 == Object.class) {
                    break;
                }
            } while (cls2 != null);
            this.fields = (Field[]) arrayList.toArray(Cloner.EMPTY_FIELD_ARRAY);
            this.numFields = this.fields.length;
            this.shouldClone = new boolean[this.numFields];
            for (i = 0; i < arrayList2.size(); i++) {
                this.shouldClone[i] = ((Boolean) arrayList2.get(i)).booleanValue();
            }
            this.instantiator = Cloner.this.instantiationStrategy.getInstantiatorOf(cls);
        }

        private boolean isFieldNullInsteadBecauseOfAnnotation(Field field) {
            if (!Cloner.this.nullInsteadFieldAnnotations.isEmpty()) {
                for (Annotation annotation : field.getAnnotations()) {
                    if (Cloner.this.nullInsteadFieldAnnotations.contains(annotation.annotationType())) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDeepCloner
        public <T> T deepClone(T t, Map<Object, Object> map) {
            try {
                if (Cloner.this.dumpCloned != null) {
                    Cloner.this.dumpCloned.startCloning(t.getClass());
                }
                T t2 = (T) this.instantiator.newInstance();
                int i = 0;
                if (map != null) {
                    map.put(t, t2);
                    while (i < this.numFields) {
                        Field field = this.fields[i];
                        Object obj = field.get(t);
                        Object applyCloningStrategy = this.shouldClone[i] ? Cloner.this.applyCloningStrategy(map, t, obj, field) : obj;
                        field.set(t2, applyCloningStrategy);
                        if (Cloner.this.dumpCloned != null && applyCloningStrategy != obj) {
                            Cloner.this.dumpCloned.cloning(field, t.getClass());
                        }
                        i++;
                    }
                } else {
                    while (i < this.numFields) {
                        Field field2 = this.fields[i];
                        field2.set(t2, field2.get(t));
                        i++;
                    }
                }
                return t2;
            } catch (IllegalAccessException e) {
                throw new CloningException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object applyCloningStrategy(Map<Object, Object> map, Object obj, Object obj2, Field field) {
        List<ICloningStrategy> list = this.cloningStrategies;
        if (list != null) {
            Iterator<ICloningStrategy> it = list.iterator();
            while (it.hasNext()) {
                ICloningStrategy.Strategy strategyFor = it.next().strategyFor(obj, field);
                if (strategyFor == ICloningStrategy.Strategy.NULL_INSTEAD_OF_CLONE) {
                    return null;
                }
                if (strategyFor == ICloningStrategy.Strategy.SAME_INSTANCE_INSTEAD_OF_CLONE) {
                    return obj2;
                }
            }
        }
        return cloneInternal(obj2, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAnonymousParent(Field field) {
        return "this$0".equals(field.getName());
    }

    public <T, E extends T> void copyPropertiesOfInheritedClass(T t, E e) {
        if (t == null) {
            throw new IllegalArgumentException("src can't be null");
        }
        if (e == null) {
            throw new IllegalArgumentException("dest can't be null");
        }
        Class<?> cls = t.getClass();
        Class<?> cls2 = e.getClass();
        if (cls.isArray()) {
            if (!cls2.isArray()) {
                throw new IllegalArgumentException("can't copy from array to non-array class " + cls2);
            }
            int length = Array.getLength(t);
            for (int i = 0; i < length; i++) {
                Array.set(e, i, Array.get(t, i));
            }
            return;
        }
        List<Field> allFields = allFields(cls);
        List<Field> allFields2 = allFields(e.getClass());
        for (Field field : allFields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                try {
                    Object obj = field.get(t);
                    field.setAccessible(true);
                    if (allFields2.contains(field)) {
                        field.set(e, obj);
                    }
                } catch (IllegalAccessException e2) {
                    throw new CloningException(e2);
                } catch (IllegalArgumentException e3) {
                    throw new CloningException(e3);
                }
            }
        }
    }

    private void addAll(List<Field> list, Field[] fieldArr) {
        for (Field field : fieldArr) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            list.add(field);
        }
    }

    protected List<Field> allFields(Class<?> cls) {
        List<Field> list = this.fieldsCache.get(cls);
        if (list == null) {
            list = new LinkedList<>();
            addAll(list, cls.getDeclaredFields());
            Class<?> cls2 = cls;
            while (true) {
                cls2 = cls2.getSuperclass();
                if (cls2 == Object.class || cls2 == null) {
                    break;
                }
                addAll(list, cls2.getDeclaredFields());
            }
            this.fieldsCache.putIfAbsent(cls, list);
        }
        return list;
    }

    public boolean isDumpClonedClasses() {
        return this.dumpCloned != null;
    }

    public void setDumpClonedClasses(boolean z) {
        if (z) {
            this.dumpCloned = new IDumpCloned() { // from class: com.pudutech.bumblebee.presenter.utils.cloner.cloning.Cloner.2
                @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDumpCloned
                public void startCloning(Class<?> cls) {
                    System.out.println("clone>" + cls);
                }

                @Override // com.pudutech.bumblebee.presenter.utils.cloner.cloning.IDumpCloned
                public void cloning(Field field, Class<?> cls) {
                    System.out.println("cloned field>" + field + "  -- of class " + cls);
                }
            };
        } else {
            this.dumpCloned = null;
        }
    }

    public boolean isCloningEnabled() {
        return this.cloningEnabled;
    }

    public void setCloningEnabled(boolean z) {
        this.cloningEnabled = z;
    }

    public void setCloneAnonymousParent(boolean z) {
        this.cloneAnonymousParent = z;
    }

    public boolean isCloneAnonymousParent() {
        return this.cloneAnonymousParent;
    }

    public static Cloner standard() {
        return new Cloner();
    }

    public static Cloner shared() {
        return new Cloner(new ObjenesisInstantiationStrategy());
    }

    /* loaded from: classes4.dex */
    private class ClonesMap extends IdentityHashMap<Object, Object> {
        private ClonesMap() {
        }

        @Override // java.util.IdentityHashMap, java.util.AbstractMap, java.util.Map
        public Object get(Object obj) {
            Object obj2;
            return (Cloner.this.ignoredInstances == null || (obj2 = Cloner.this.ignoredInstances.get(obj)) == null) ? super.get(obj) : obj2;
        }
    }
}
