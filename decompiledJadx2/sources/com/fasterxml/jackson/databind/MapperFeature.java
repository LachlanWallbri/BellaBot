package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.ConfigFeature;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public enum MapperFeature implements ConfigFeature {
    USE_ANNOTATIONS(true),
    USE_GETTERS_AS_SETTERS(true),
    PROPAGATE_TRANSIENT_MARKER(false),
    AUTO_DETECT_CREATORS(true),
    AUTO_DETECT_FIELDS(true),
    AUTO_DETECT_GETTERS(true),
    AUTO_DETECT_IS_GETTERS(true),
    AUTO_DETECT_SETTERS(true),
    REQUIRE_SETTERS_FOR_GETTERS(false),
    ALLOW_FINAL_FIELDS_AS_MUTATORS(true),
    INFER_PROPERTY_MUTATORS(true),
    INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES(true),
    CAN_OVERRIDE_ACCESS_MODIFIERS(true),
    OVERRIDE_PUBLIC_ACCESS_MODIFIERS(true),
    USE_STATIC_TYPING(false),
    USE_BASE_TYPE_AS_DEFAULT_IMPL(false),
    DEFAULT_VIEW_INCLUSION(true),
    SORT_PROPERTIES_ALPHABETICALLY(false),
    ACCEPT_CASE_INSENSITIVE_PROPERTIES(false),
    ACCEPT_CASE_INSENSITIVE_ENUMS(false),
    ACCEPT_CASE_INSENSITIVE_VALUES(false),
    USE_WRAPPER_NAME_AS_PROPERTY_NAME(false),
    USE_STD_BEAN_NAMING(false),
    ALLOW_EXPLICIT_PROPERTY_RENAMING(false),
    ALLOW_COERCION_OF_SCALARS(true),
    IGNORE_DUPLICATE_MODULE_REGISTRATIONS(true),
    IGNORE_MERGE_FOR_UNMERGEABLE(true),
    BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES(false);

    private final boolean _defaultState;
    private final int _mask = 1 << ordinal();

    MapperFeature(boolean z) {
        this._defaultState = z;
    }

    @Override // com.fasterxml.jackson.databind.cfg.ConfigFeature
    public boolean enabledByDefault() {
        return this._defaultState;
    }

    @Override // com.fasterxml.jackson.databind.cfg.ConfigFeature
    public int getMask() {
        return this._mask;
    }

    @Override // com.fasterxml.jackson.databind.cfg.ConfigFeature
    public boolean enabledIn(int i) {
        return (i & this._mask) != 0;
    }
}
