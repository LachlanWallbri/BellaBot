package com.nineoldandroids.animation;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.nineoldandroids.util.FloatProperty;
import com.nineoldandroids.util.IntProperty;
import com.nineoldandroids.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public class PropertyValuesHolder implements Cloneable {
    private Object mAnimatedValue;
    private TypeEvaluator mEvaluator;
    private Method mGetter;
    KeyframeSet mKeyframeSet;
    protected Property mProperty;
    final ReentrantReadWriteLock mPropertyMapLock;
    String mPropertyName;
    Method mSetter;
    final Object[] mTmpValueArray;
    Class mValueType;
    private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
    private static final TypeEvaluator sFloatEvaluator = new FloatEvaluator();
    private static Class[] FLOAT_VARIANTS = {Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
    private static Class[] INTEGER_VARIANTS = {Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
    private static Class[] DOUBLE_VARIANTS = {Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
    private static final HashMap<Class, HashMap<String, Method>> sSetterPropertyMap = new HashMap<>();
    private static final HashMap<Class, HashMap<String, Method>> sGetterPropertyMap = new HashMap<>();

    private PropertyValuesHolder(String str) {
        this.mSetter = null;
        this.mGetter = null;
        this.mKeyframeSet = null;
        this.mPropertyMapLock = new ReentrantReadWriteLock();
        this.mTmpValueArray = new Object[1];
        this.mPropertyName = str;
    }

    private PropertyValuesHolder(Property property) {
        this.mSetter = null;
        this.mGetter = null;
        this.mKeyframeSet = null;
        this.mPropertyMapLock = new ReentrantReadWriteLock();
        this.mTmpValueArray = new Object[1];
        this.mProperty = property;
        if (property != null) {
            this.mPropertyName = property.getName();
        }
    }

    public static PropertyValuesHolder ofInt(String str, int... iArr) {
        return new IntPropertyValuesHolder(str, iArr);
    }

    public static PropertyValuesHolder ofInt(Property<?, Integer> property, int... iArr) {
        return new IntPropertyValuesHolder(property, iArr);
    }

    public static PropertyValuesHolder ofFloat(String str, float... fArr) {
        return new FloatPropertyValuesHolder(str, fArr);
    }

    public static PropertyValuesHolder ofFloat(Property<?, Float> property, float... fArr) {
        return new FloatPropertyValuesHolder(property, fArr);
    }

    public static PropertyValuesHolder ofObject(String str, TypeEvaluator typeEvaluator, Object... objArr) {
        PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(str);
        propertyValuesHolder.setObjectValues(objArr);
        propertyValuesHolder.setEvaluator(typeEvaluator);
        return propertyValuesHolder;
    }

    public static <V> PropertyValuesHolder ofObject(Property property, TypeEvaluator<V> typeEvaluator, V... vArr) {
        PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(property);
        propertyValuesHolder.setObjectValues(vArr);
        propertyValuesHolder.setEvaluator(typeEvaluator);
        return propertyValuesHolder;
    }

    public static PropertyValuesHolder ofKeyframe(String str, Keyframe... keyframeArr) {
        KeyframeSet ofKeyframe = KeyframeSet.ofKeyframe(keyframeArr);
        if (ofKeyframe instanceof IntKeyframeSet) {
            return new IntPropertyValuesHolder(str, (IntKeyframeSet) ofKeyframe);
        }
        if (ofKeyframe instanceof FloatKeyframeSet) {
            return new FloatPropertyValuesHolder(str, (FloatKeyframeSet) ofKeyframe);
        }
        PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(str);
        propertyValuesHolder.mKeyframeSet = ofKeyframe;
        propertyValuesHolder.mValueType = keyframeArr[0].getType();
        return propertyValuesHolder;
    }

    public static PropertyValuesHolder ofKeyframe(Property property, Keyframe... keyframeArr) {
        KeyframeSet ofKeyframe = KeyframeSet.ofKeyframe(keyframeArr);
        if (ofKeyframe instanceof IntKeyframeSet) {
            return new IntPropertyValuesHolder(property, (IntKeyframeSet) ofKeyframe);
        }
        if (ofKeyframe instanceof FloatKeyframeSet) {
            return new FloatPropertyValuesHolder(property, (FloatKeyframeSet) ofKeyframe);
        }
        PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(property);
        propertyValuesHolder.mKeyframeSet = ofKeyframe;
        propertyValuesHolder.mValueType = keyframeArr[0].getType();
        return propertyValuesHolder;
    }

    public void setIntValues(int... iArr) {
        this.mValueType = Integer.TYPE;
        this.mKeyframeSet = KeyframeSet.ofInt(iArr);
    }

    public void setFloatValues(float... fArr) {
        this.mValueType = Float.TYPE;
        this.mKeyframeSet = KeyframeSet.ofFloat(fArr);
    }

    public void setKeyframes(Keyframe... keyframeArr) {
        int length = keyframeArr.length;
        Keyframe[] keyframeArr2 = new Keyframe[Math.max(length, 2)];
        this.mValueType = keyframeArr[0].getType();
        for (int i = 0; i < length; i++) {
            keyframeArr2[i] = keyframeArr[i];
        }
        this.mKeyframeSet = new KeyframeSet(keyframeArr2);
    }

    public void setObjectValues(Object... objArr) {
        this.mValueType = objArr[0].getClass();
        this.mKeyframeSet = KeyframeSet.ofObject(objArr);
    }

    private Method getPropertyFunction(Class cls, String str, Class cls2) {
        Class<?>[] clsArr;
        String methodName = getMethodName(str, this.mPropertyName);
        Method method = null;
        if (cls2 == null) {
            try {
                return cls.getMethod(methodName, null);
            } catch (NoSuchMethodException e) {
                try {
                    method = cls.getDeclaredMethod(methodName, null);
                    method.setAccessible(true);
                } catch (NoSuchMethodException unused) {
                    Log.e("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.mPropertyName + ": " + e);
                }
                return method;
            }
        }
        Class<?>[] clsArr2 = new Class[1];
        if (this.mValueType.equals(Float.class)) {
            clsArr = FLOAT_VARIANTS;
        } else if (this.mValueType.equals(Integer.class)) {
            clsArr = INTEGER_VARIANTS;
        } else {
            clsArr = this.mValueType.equals(Double.class) ? DOUBLE_VARIANTS : new Class[]{this.mValueType};
        }
        Method method2 = null;
        for (Class<?> cls3 : clsArr) {
            clsArr2[0] = cls3;
            try {
                try {
                    Method method3 = cls.getMethod(methodName, clsArr2);
                    this.mValueType = cls3;
                    return method3;
                } catch (NoSuchMethodException unused2) {
                }
            } catch (NoSuchMethodException unused3) {
                method2 = cls.getDeclaredMethod(methodName, clsArr2);
                method2.setAccessible(true);
                this.mValueType = cls3;
                return method2;
            }
        }
        Log.e("PropertyValuesHolder", "Couldn't find setter/getter for property " + this.mPropertyName + " with value type " + this.mValueType);
        return method2;
    }

    private Method setupSetterOrGetter(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        try {
            this.mPropertyMapLock.writeLock().lock();
            HashMap<String, Method> hashMap2 = hashMap.get(cls);
            Method method = hashMap2 != null ? hashMap2.get(this.mPropertyName) : null;
            if (method == null) {
                method = getPropertyFunction(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap<>();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.mPropertyName, method);
            }
            return method;
        } finally {
            this.mPropertyMapLock.writeLock().unlock();
        }
    }

    void setupSetter(Class cls) {
        this.mSetter = setupSetterOrGetter(cls, sSetterPropertyMap, TmpConstant.PROPERTY_IDENTIFIER_SET, this.mValueType);
    }

    private void setupGetter(Class cls) {
        this.mGetter = setupSetterOrGetter(cls, sGetterPropertyMap, TmpConstant.PROPERTY_IDENTIFIER_GET, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupSetterAndGetter(Object obj) {
        Property property = this.mProperty;
        if (property != null) {
            try {
                property.get(obj);
                Iterator<Keyframe> it = this.mKeyframeSet.mKeyframes.iterator();
                while (it.hasNext()) {
                    Keyframe next = it.next();
                    if (!next.hasValue()) {
                        next.setValue(this.mProperty.get(obj));
                    }
                }
                return;
            } catch (ClassCastException unused) {
                Log.e("PropertyValuesHolder", "No such property (" + this.mProperty.getName() + ") on target object " + obj + ". Trying reflection instead");
                this.mProperty = null;
            }
        }
        Class<?> cls = obj.getClass();
        if (this.mSetter == null) {
            setupSetter(cls);
        }
        Iterator<Keyframe> it2 = this.mKeyframeSet.mKeyframes.iterator();
        while (it2.hasNext()) {
            Keyframe next2 = it2.next();
            if (!next2.hasValue()) {
                if (this.mGetter == null) {
                    setupGetter(cls);
                }
                try {
                    next2.setValue(this.mGetter.invoke(obj, new Object[0]));
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }
    }

    private void setupValue(Object obj, Keyframe keyframe) {
        Property property = this.mProperty;
        if (property != null) {
            keyframe.setValue(property.get(obj));
        }
        try {
            if (this.mGetter == null) {
                setupGetter(obj.getClass());
            }
            keyframe.setValue(this.mGetter.invoke(obj, new Object[0]));
        } catch (IllegalAccessException e) {
            Log.e("PropertyValuesHolder", e.toString());
        } catch (InvocationTargetException e2) {
            Log.e("PropertyValuesHolder", e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupStartValue(Object obj) {
        setupValue(obj, this.mKeyframeSet.mKeyframes.get(0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupEndValue(Object obj) {
        setupValue(obj, this.mKeyframeSet.mKeyframes.get(this.mKeyframeSet.mKeyframes.size() - 1));
    }

    @Override // 
    /* renamed from: clone */
    public PropertyValuesHolder mo4288clone() {
        try {
            PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) super.clone();
            propertyValuesHolder.mPropertyName = this.mPropertyName;
            propertyValuesHolder.mProperty = this.mProperty;
            propertyValuesHolder.mKeyframeSet = this.mKeyframeSet.mo4286clone();
            propertyValuesHolder.mEvaluator = this.mEvaluator;
            return propertyValuesHolder;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAnimatedValue(Object obj) {
        Property property = this.mProperty;
        if (property != null) {
            property.set(obj, getAnimatedValue());
        }
        if (this.mSetter != null) {
            try {
                this.mTmpValueArray[0] = getAnimatedValue();
                this.mSetter.invoke(obj, this.mTmpValueArray);
            } catch (IllegalAccessException e) {
                Log.e("PropertyValuesHolder", e.toString());
            } catch (InvocationTargetException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init() {
        if (this.mEvaluator == null) {
            Class cls = this.mValueType;
            this.mEvaluator = cls == Integer.class ? sIntEvaluator : cls == Float.class ? sFloatEvaluator : null;
        }
        TypeEvaluator typeEvaluator = this.mEvaluator;
        if (typeEvaluator != null) {
            this.mKeyframeSet.setEvaluator(typeEvaluator);
        }
    }

    public void setEvaluator(TypeEvaluator typeEvaluator) {
        this.mEvaluator = typeEvaluator;
        this.mKeyframeSet.setEvaluator(typeEvaluator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateValue(float f) {
        this.mAnimatedValue = this.mKeyframeSet.getValue(f);
    }

    public void setPropertyName(String str) {
        this.mPropertyName = str;
    }

    public void setProperty(Property property) {
        this.mProperty = property;
    }

    public String getPropertyName() {
        return this.mPropertyName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getAnimatedValue() {
        return this.mAnimatedValue;
    }

    public String toString() {
        return this.mPropertyName + ": " + this.mKeyframeSet.toString();
    }

    static String getMethodName(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        return str + Character.toUpperCase(str2.charAt(0)) + str2.substring(1);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    static class IntPropertyValuesHolder extends PropertyValuesHolder {
        int mIntAnimatedValue;
        IntKeyframeSet mIntKeyframeSet;
        private IntProperty mIntProperty;

        public IntPropertyValuesHolder(String str, IntKeyframeSet intKeyframeSet) {
            super(str);
            this.mValueType = Integer.TYPE;
            this.mKeyframeSet = intKeyframeSet;
            this.mIntKeyframeSet = (IntKeyframeSet) this.mKeyframeSet;
        }

        public IntPropertyValuesHolder(Property property, IntKeyframeSet intKeyframeSet) {
            super(property);
            this.mValueType = Integer.TYPE;
            this.mKeyframeSet = intKeyframeSet;
            this.mIntKeyframeSet = (IntKeyframeSet) this.mKeyframeSet;
            if (property instanceof IntProperty) {
                this.mIntProperty = (IntProperty) this.mProperty;
            }
        }

        public IntPropertyValuesHolder(String str, int... iArr) {
            super(str);
            setIntValues(iArr);
        }

        public IntPropertyValuesHolder(Property property, int... iArr) {
            super(property);
            setIntValues(iArr);
            if (property instanceof IntProperty) {
                this.mIntProperty = (IntProperty) this.mProperty;
            }
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        public void setIntValues(int... iArr) {
            super.setIntValues(iArr);
            this.mIntKeyframeSet = (IntKeyframeSet) this.mKeyframeSet;
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void calculateValue(float f) {
            this.mIntAnimatedValue = this.mIntKeyframeSet.getIntValue(f);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        Object getAnimatedValue() {
            return Integer.valueOf(this.mIntAnimatedValue);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        /* renamed from: clone */
        public IntPropertyValuesHolder mo4288clone() {
            IntPropertyValuesHolder intPropertyValuesHolder = (IntPropertyValuesHolder) super.mo4288clone();
            intPropertyValuesHolder.mIntKeyframeSet = (IntKeyframeSet) intPropertyValuesHolder.mKeyframeSet;
            return intPropertyValuesHolder;
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void setAnimatedValue(Object obj) {
            IntProperty intProperty = this.mIntProperty;
            if (intProperty != null) {
                intProperty.setValue(obj, this.mIntAnimatedValue);
                return;
            }
            if (this.mProperty != null) {
                this.mProperty.set(obj, Integer.valueOf(this.mIntAnimatedValue));
                return;
            }
            if (this.mSetter != null) {
                try {
                    this.mTmpValueArray[0] = Integer.valueOf(this.mIntAnimatedValue);
                    this.mSetter.invoke(obj, this.mTmpValueArray);
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void setupSetter(Class cls) {
            if (this.mProperty != null) {
                return;
            }
            super.setupSetter(cls);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    static class FloatPropertyValuesHolder extends PropertyValuesHolder {
        float mFloatAnimatedValue;
        FloatKeyframeSet mFloatKeyframeSet;
        private FloatProperty mFloatProperty;

        public FloatPropertyValuesHolder(String str, FloatKeyframeSet floatKeyframeSet) {
            super(str);
            this.mValueType = Float.TYPE;
            this.mKeyframeSet = floatKeyframeSet;
            this.mFloatKeyframeSet = (FloatKeyframeSet) this.mKeyframeSet;
        }

        public FloatPropertyValuesHolder(Property property, FloatKeyframeSet floatKeyframeSet) {
            super(property);
            this.mValueType = Float.TYPE;
            this.mKeyframeSet = floatKeyframeSet;
            this.mFloatKeyframeSet = (FloatKeyframeSet) this.mKeyframeSet;
            if (property instanceof FloatProperty) {
                this.mFloatProperty = (FloatProperty) this.mProperty;
            }
        }

        public FloatPropertyValuesHolder(String str, float... fArr) {
            super(str);
            setFloatValues(fArr);
        }

        public FloatPropertyValuesHolder(Property property, float... fArr) {
            super(property);
            setFloatValues(fArr);
            if (property instanceof FloatProperty) {
                this.mFloatProperty = (FloatProperty) this.mProperty;
            }
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        public void setFloatValues(float... fArr) {
            super.setFloatValues(fArr);
            this.mFloatKeyframeSet = (FloatKeyframeSet) this.mKeyframeSet;
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void calculateValue(float f) {
            this.mFloatAnimatedValue = this.mFloatKeyframeSet.getFloatValue(f);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        Object getAnimatedValue() {
            return Float.valueOf(this.mFloatAnimatedValue);
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public FloatPropertyValuesHolder mo4288clone() {
            FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder) super.mo4288clone();
            floatPropertyValuesHolder.mFloatKeyframeSet = (FloatKeyframeSet) floatPropertyValuesHolder.mKeyframeSet;
            return floatPropertyValuesHolder;
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void setAnimatedValue(Object obj) {
            FloatProperty floatProperty = this.mFloatProperty;
            if (floatProperty != null) {
                floatProperty.setValue(obj, this.mFloatAnimatedValue);
                return;
            }
            if (this.mProperty != null) {
                this.mProperty.set(obj, Float.valueOf(this.mFloatAnimatedValue));
                return;
            }
            if (this.mSetter != null) {
                try {
                    this.mTmpValueArray[0] = Float.valueOf(this.mFloatAnimatedValue);
                    this.mSetter.invoke(obj, this.mTmpValueArray);
                } catch (IllegalAccessException e) {
                    Log.e("PropertyValuesHolder", e.toString());
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                }
            }
        }

        @Override // com.nineoldandroids.animation.PropertyValuesHolder
        void setupSetter(Class cls) {
            if (this.mProperty != null) {
                return;
            }
            super.setupSetter(cls);
        }
    }
}
