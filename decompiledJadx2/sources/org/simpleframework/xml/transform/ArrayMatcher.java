package org.simpleframework.xml.transform;

/* loaded from: classes9.dex */
class ArrayMatcher implements Matcher {
    private final Matcher primary;

    public ArrayMatcher(Matcher matcher) {
        this.primary = matcher;
    }

    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(Class cls) throws Exception {
        Class<?> componentType = cls.getComponentType();
        if (componentType == Character.TYPE) {
            return new CharacterArrayTransform(componentType);
        }
        if (componentType == Character.class) {
            return new CharacterArrayTransform(componentType);
        }
        if (componentType == String.class) {
            return new StringArrayTransform();
        }
        return matchArray(componentType);
    }

    private Transform matchArray(Class cls) throws Exception {
        Transform match = this.primary.match(cls);
        if (match == null) {
            return null;
        }
        return new ArrayTransform(match, cls);
    }
}
