package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Text;

/* loaded from: classes9.dex */
class SignatureScanner {
    private final SignatureBuilder builder;
    private final Constructor constructor;
    private final ParameterFactory factory;
    private final ParameterMap registry;
    private final Class type;

    public SignatureScanner(Constructor constructor, ParameterMap parameterMap, Support support) throws Exception {
        this.builder = new SignatureBuilder(constructor);
        this.factory = new ParameterFactory(support);
        this.type = constructor.getDeclaringClass();
        this.constructor = constructor;
        this.registry = parameterMap;
        scan(this.type);
    }

    public boolean isValid() {
        return this.builder.isValid();
    }

    public List<Signature> getSignatures() throws Exception {
        return this.builder.build();
    }

    private void scan(Class cls) throws Exception {
        Class<?>[] parameterTypes = this.constructor.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            scan(parameterTypes[i], i);
        }
    }

    private void scan(Class cls, int i) throws Exception {
        Annotation[][] parameterAnnotations = this.constructor.getParameterAnnotations();
        for (int i2 = 0; i2 < parameterAnnotations[i].length; i2++) {
            Iterator<Parameter> it = process(parameterAnnotations[i][i2], i).iterator();
            while (it.hasNext()) {
                this.builder.insert(it.next(), i);
            }
        }
    }

    private List<Parameter> process(Annotation annotation, int i) throws Exception {
        if (annotation instanceof Attribute) {
            return create(annotation, i);
        }
        if (annotation instanceof Element) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementList) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementArray) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementMap) {
            return create(annotation, i);
        }
        if (annotation instanceof ElementListUnion) {
            return union(annotation, i);
        }
        if (annotation instanceof ElementMapUnion) {
            return union(annotation, i);
        }
        if (annotation instanceof ElementUnion) {
            return union(annotation, i);
        }
        if (annotation instanceof Text) {
            return create(annotation, i);
        }
        return Collections.emptyList();
    }

    private List<Parameter> union(Annotation annotation, int i) throws Exception {
        Signature signature = new Signature(this.constructor);
        for (Annotation annotation2 : extract(annotation)) {
            Parameter parameterFactory = this.factory.getInstance(this.constructor, annotation, annotation2, i);
            String path = parameterFactory.getPath();
            if (signature.contains(path)) {
                throw new UnionException("Annotation name '%s' used more than once in %s for %s", path, annotation, this.type);
            }
            signature.set(path, parameterFactory);
            register(parameterFactory);
        }
        return signature.getAll();
    }

    private List<Parameter> create(Annotation annotation, int i) throws Exception {
        Parameter parameterFactory = this.factory.getInstance(this.constructor, annotation, i);
        if (parameterFactory != null) {
            register(parameterFactory);
        }
        return Collections.singletonList(parameterFactory);
    }

    private Annotation[] extract(Annotation annotation) throws Exception {
        Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
        if (declaredMethods.length != 1) {
            throw new UnionException("Annotation '%s' is not a valid union for %s", annotation, this.type);
        }
        return (Annotation[]) declaredMethods[0].invoke(annotation, new Object[0]);
    }

    private void register(Parameter parameter) throws Exception {
        String path = parameter.getPath();
        Object key = parameter.getKey();
        if (this.registry.containsKey(key)) {
            validate(parameter, key);
        }
        if (this.registry.containsKey(path)) {
            validate(parameter, path);
        }
        this.registry.put(path, parameter);
        this.registry.put(key, parameter);
    }

    private void validate(Parameter parameter, Object obj) throws Exception {
        Parameter parameter2 = this.registry.get(obj);
        if (parameter.isText() != parameter2.isText()) {
            Annotation annotation = parameter.getAnnotation();
            Annotation annotation2 = parameter2.getAnnotation();
            String path = parameter.getPath();
            if (!annotation.equals(annotation2)) {
                throw new ConstructorException("Annotations do not match for '%s' in %s", path, this.type);
            }
            if (parameter2.getType() != parameter.getType()) {
                throw new ConstructorException("Parameter types do not match for '%s' in %s", path, this.type);
            }
        }
    }
}
