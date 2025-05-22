package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class PropertyBuilder {
    private static final Object NO_DEFAULT_MARKER = Boolean.FALSE;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonInclude.Value _defaultInclusion;
    protected final boolean _useRealPropertyDefaults;

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        this._config = serializationConfig;
        this._beanDesc = beanDescription;
        JsonInclude.Value merge = JsonInclude.Value.merge(beanDescription.findPropertyInclusion(JsonInclude.Value.empty()), serializationConfig.getDefaultPropertyInclusion(beanDescription.getBeanClass(), JsonInclude.Value.empty()));
        this._defaultInclusion = JsonInclude.Value.merge(serializationConfig.getDefaultPropertyInclusion(), merge);
        this._useRealPropertyDefaults = merge.getValueInclusion() == JsonInclude.Include.NON_DEFAULT;
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public BeanPropertyWriter buildWriter(SerializerProvider serializerProvider, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, TypeSerializer typeSerializer2, AnnotatedMember annotatedMember, boolean z) throws JsonMappingException {
        JavaType javaType2;
        boolean z2;
        Object obj;
        Object arrayComparator;
        Object defaultBean;
        Class<?>[] findViews;
        Object findNullSerializer;
        boolean z3 = false;
        try {
            JavaType findSerializationType = findSerializationType(annotatedMember, z, javaType);
            if (typeSerializer2 != null) {
                if (findSerializationType == null) {
                    findSerializationType = javaType;
                }
                if (findSerializationType.getContentType() == null) {
                    serializerProvider.reportBadPropertyDefinition(this._beanDesc, beanPropertyDefinition, "serialization type " + findSerializationType + " has no content", new Object[0]);
                }
                JavaType withContentTypeHandler = findSerializationType.withContentTypeHandler(typeSerializer2);
                withContentTypeHandler.getContentType();
                javaType2 = withContentTypeHandler;
            } else {
                javaType2 = findSerializationType;
            }
            Object obj2 = null;
            JavaType javaType3 = javaType2 == null ? javaType : javaType2;
            AnnotatedMember accessor = beanPropertyDefinition.getAccessor();
            if (accessor == null) {
                return (BeanPropertyWriter) serializerProvider.reportBadPropertyDefinition(this._beanDesc, beanPropertyDefinition, "could not determine property type", new Object[0]);
            }
            JsonInclude.Value withOverrides = this._config.getDefaultInclusion(javaType3.getRawClass(), accessor.getRawType(), this._defaultInclusion).withOverrides(beanPropertyDefinition.findInclusion());
            JsonInclude.Include valueInclusion = withOverrides.getValueInclusion();
            if (valueInclusion == JsonInclude.Include.USE_DEFAULTS) {
                valueInclusion = JsonInclude.Include.ALWAYS;
            }
            int i = C16231.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[valueInclusion.ordinal()];
            if (i == 1) {
                if (this._useRealPropertyDefaults && (defaultBean = getDefaultBean()) != null) {
                    if (serializerProvider.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                        annotatedMember.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    try {
                        obj2 = annotatedMember.getValue(defaultBean);
                    } catch (Exception e) {
                        _throwWrapped(e, beanPropertyDefinition.getName(), defaultBean);
                    }
                } else {
                    obj2 = BeanUtil.getDefaultValue(javaType3);
                    z3 = true;
                }
                if (obj2 != null) {
                    if (obj2.getClass().isArray()) {
                        arrayComparator = ArrayBuilders.getArrayComparator(obj2);
                        obj = arrayComparator;
                        z2 = z3;
                        findViews = beanPropertyDefinition.findViews();
                        if (findViews == null) {
                        }
                        BeanPropertyWriter beanPropertyWriter = new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, this._beanDesc.getClassAnnotations(), javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, findViews);
                        findNullSerializer = this._annotationIntrospector.findNullSerializer(annotatedMember);
                        if (findNullSerializer != null) {
                        }
                        NameTransformer findUnwrappingNameTransformer = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember);
                        if (findUnwrappingNameTransformer != null) {
                        }
                    } else {
                        z2 = z3;
                        obj = obj2;
                        findViews = beanPropertyDefinition.findViews();
                        if (findViews == null) {
                        }
                        BeanPropertyWriter beanPropertyWriter2 = new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, this._beanDesc.getClassAnnotations(), javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, findViews);
                        findNullSerializer = this._annotationIntrospector.findNullSerializer(annotatedMember);
                        if (findNullSerializer != null) {
                        }
                        NameTransformer findUnwrappingNameTransformer2 = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember);
                        if (findUnwrappingNameTransformer2 != null) {
                        }
                    }
                }
                obj = obj2;
                z2 = r9;
                findViews = beanPropertyDefinition.findViews();
                if (findViews == null) {
                }
                BeanPropertyWriter beanPropertyWriter22 = new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, this._beanDesc.getClassAnnotations(), javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, findViews);
                findNullSerializer = this._annotationIntrospector.findNullSerializer(annotatedMember);
                if (findNullSerializer != null) {
                }
                NameTransformer findUnwrappingNameTransformer22 = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember);
                if (findUnwrappingNameTransformer22 != null) {
                }
            } else {
                if (i != 2) {
                    if (i == 3) {
                        arrayComparator = BeanPropertyWriter.MARKER_FOR_EMPTY;
                    } else if (i == 4) {
                        arrayComparator = serializerProvider.includeFilterInstance(beanPropertyDefinition, withOverrides.getValueFilter());
                        if (arrayComparator != null) {
                            z3 = serializerProvider.includeFilterSuppressNulls(arrayComparator);
                            obj = arrayComparator;
                            z2 = z3;
                            findViews = beanPropertyDefinition.findViews();
                            if (findViews == null) {
                                findViews = this._beanDesc.findDefaultViews();
                            }
                            BeanPropertyWriter beanPropertyWriter222 = new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, this._beanDesc.getClassAnnotations(), javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, findViews);
                            findNullSerializer = this._annotationIntrospector.findNullSerializer(annotatedMember);
                            if (findNullSerializer != null) {
                                beanPropertyWriter222.assignNullSerializer(serializerProvider.serializerInstance(annotatedMember, findNullSerializer));
                            }
                            NameTransformer findUnwrappingNameTransformer222 = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember);
                            return findUnwrappingNameTransformer222 != null ? beanPropertyWriter222.unwrappingWriter(findUnwrappingNameTransformer222) : beanPropertyWriter222;
                        }
                    } else {
                        r9 = i == 5;
                        if (javaType3.isContainerType() && !this._config.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)) {
                            arrayComparator = BeanPropertyWriter.MARKER_FOR_EMPTY;
                        }
                        obj = obj2;
                    }
                    obj = arrayComparator;
                } else {
                    if (javaType3.isReferenceType()) {
                        arrayComparator = BeanPropertyWriter.MARKER_FOR_EMPTY;
                        obj = arrayComparator;
                    }
                    obj = obj2;
                }
                z2 = r9;
                findViews = beanPropertyDefinition.findViews();
                if (findViews == null) {
                }
                BeanPropertyWriter beanPropertyWriter2222 = new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, this._beanDesc.getClassAnnotations(), javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, findViews);
                findNullSerializer = this._annotationIntrospector.findNullSerializer(annotatedMember);
                if (findNullSerializer != null) {
                }
                NameTransformer findUnwrappingNameTransformer2222 = this._annotationIntrospector.findUnwrappingNameTransformer(annotatedMember);
                if (findUnwrappingNameTransformer2222 != null) {
                }
            }
        } catch (JsonMappingException e2) {
            if (beanPropertyDefinition == null) {
                return (BeanPropertyWriter) serializerProvider.reportBadDefinition(javaType, ClassUtil.exceptionMessage(e2));
            }
            return (BeanPropertyWriter) serializerProvider.reportBadPropertyDefinition(this._beanDesc, beanPropertyDefinition, ClassUtil.exceptionMessage(e2), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* renamed from: com.fasterxml.jackson.databind.ser.PropertyBuilder$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C16231 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = new int[JsonInclude.Include.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_ABSENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_EMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.CUSTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.ALWAYS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) throws JsonMappingException {
        JavaType refineSerializationType = this._annotationIntrospector.refineSerializationType(this._config, annotated, javaType);
        if (refineSerializationType != javaType) {
            Class<?> rawClass = refineSerializationType.getRawClass();
            Class<?> rawClass2 = javaType.getRawClass();
            if (!rawClass.isAssignableFrom(rawClass2) && !rawClass2.isAssignableFrom(rawClass)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + rawClass.getName() + " not a super-type of (declared) class " + rawClass2.getName());
            }
            javaType = refineSerializationType;
            z = true;
        }
        JsonSerialize.Typing findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
        if (findSerializationTyping != null && findSerializationTyping != JsonSerialize.Typing.DEFAULT_TYPING) {
            z = findSerializationTyping == JsonSerialize.Typing.STATIC;
        }
        if (z) {
            return javaType.withStaticTyping();
        }
        return null;
    }

    protected Object getDefaultBean() {
        Object obj = this._defaultBean;
        if (obj == null) {
            obj = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (obj == null) {
                obj = NO_DEFAULT_MARKER;
            }
            this._defaultBean = obj;
        }
        if (obj == NO_DEFAULT_MARKER) {
            return null;
        }
        return this._defaultBean;
    }

    @Deprecated
    protected Object getPropertyDefaultValue(String str, AnnotatedMember annotatedMember, JavaType javaType) {
        Object defaultBean = getDefaultBean();
        if (defaultBean == null) {
            return getDefaultValue(javaType);
        }
        try {
            return annotatedMember.getValue(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    @Deprecated
    protected Object getDefaultValue(JavaType javaType) {
        return BeanUtil.getDefaultValue(javaType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
    
        r3 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected Object _throwWrapped(Exception exc, String str, Object obj) {
        Exception exc2;
        while (exc2.getCause() != null) {
            exc2 = exc2.getCause();
        }
        ClassUtil.throwIfError(exc2);
        ClassUtil.throwIfRTE(exc2);
        throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
    }
}
