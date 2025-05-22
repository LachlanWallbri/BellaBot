package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface TypeResolutionContext {
    JavaType resolveType(Type type);

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public static class Basic implements TypeResolutionContext {
        private final TypeBindings _bindings;
        private final TypeFactory _typeFactory;

        public Basic(TypeFactory typeFactory, TypeBindings typeBindings) {
            this._typeFactory = typeFactory;
            this._bindings = typeBindings;
        }

        @Override // com.fasterxml.jackson.databind.introspect.TypeResolutionContext
        public JavaType resolveType(Type type) {
            if (type instanceof Class) {
                return this._typeFactory.constructType(type);
            }
            return this._typeFactory.constructType(type, this._bindings);
        }
    }
}
